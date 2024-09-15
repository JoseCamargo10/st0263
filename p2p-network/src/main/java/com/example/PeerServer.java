package com.example;

import com.example.p2pnetwork.P2PServiceGrpc;
import com.example.P2PServer.P2PServiceImpl;
import com.example.p2pnetwork.P2PServiceProto.GreetingRequest;
import com.example.p2pnetwork.P2PServiceProto.GreetingResponse;
import com.example.p2pnetwork.P2PServiceProto.PeerInfo;
import com.google.protobuf.Empty;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

import com.example.p2pnetwork.P2PServiceProto.LeaveRequest;

import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.*;
import io.grpc.*;

public class PeerServer {
    private final String peerID;
    private final int port;
    private Server server;
    private final ConcurrentHashMap<Integer, PeerInfo> peers = new ConcurrentHashMap<>();

    // Constructor
    public PeerServer(String peerID, int port) {
        this.peerID = peerID;
        this.port = port;
    }

    // Start the server to accept connections
    public void startServer() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new P2PServiceImpl())  // Añadir el servicio gRPC
                .build()
                .start();
        System.out.println(peerID + " server started on port " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down gRPC server for " + peerID);
            PeerServer.this.stopServer();
            System.err.println("Server shut down.");
        }));
    }

     // Stop gRPC server
    public void stopServer() {
        if (server != null) {
            server.shutdown();
        }
    }

    // Keep server alive
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    private static class P2PServiceImpl extends P2PServiceGrpc.P2PServiceImplBase {
        @Override
        public void sendGreeting(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {
            String message = request.getMessage();
            System.out.println("Received greeting: " + message);

            // Response to peer
            GreetingResponse response = GreetingResponse.newBuilder()
                    .setReply("Greeting received!")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }   

    private static class P2PServiceImpl extends P2PServiceGrpc.P2PServiceImplBase {
        private final ConcurrentHashMap<Integer, PeerInfo> peers;

        // Constructor to initialize peers map
        public P2PServiceImpl(ConcurrentHashMap<Integer, PeerInfo> peers) {
            this.peers = peers;
        }

        @Override
        public void joinNetwork(JoinRequest request, StreamObserver<JoinResponse> responseObserver) {
            PeerInfo newPeer = request.getNewPeer();
            peers.put(newPeer.getPeerID(), newPeer);

            PeerInfo predecessor = findPredecessor(newPeer);
            PeerInfo successor = findSuccessor(newPeer);

            if (predecessor != null) {
                notifyPeer(predecessor, P2PServiceGrpc.P2PServiceBlockingStub::updateSuccessor, newPeer);
            }
            if (successor != null) {
                notifyPeer(successor, P2PServiceGrpc.P2PServiceBlockingStub::updatePredecessor, newPeer);
            }

            PeerPosition position = PeerPosition.newBuilder()
                    .setPredecessor(predecessor)
                    .setSuccessor(successor)
                    .build();

            JoinResponse response = JoinResponse.newBuilder()
                    .setPosition(position)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void notifyLeave(LeaveRequest request, StreamObserver<Empty> responseObserver) {
            PeerInfo leavingPeer = request.getLeavingPeer();
            peers.remove(leavingPeer.getPeerID());

            PeerInfo predecessor = findPredecessor(leavingPeer);
            PeerInfo successor = findSuccessor(leavingPeer);

            if (predecessor != null) {
                notifyPeer(predecessor, P2PServiceGrpc.P2PServiceBlockingStub::updateSuccessor, successor);
            }
            if (successor != null) {
                notifyPeer(successor, P2PServiceGrpc.P2PServiceBlockingStub::updatePredecessor, predecessor);
            }

            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        }


        private PeerInfo findPredecessor(PeerInfo newPeer) {
            // Logic to find the predecessor of newPeer
            // For simplicity, assume the predecessor is the peer with the largest ID smaller than newPeer.getPeerID()
            return peers.values().stream()
                    .filter(peer -> peer.getPeerID() < newPeer.getPeerID())
                    .max(Comparator.comparingInt(PeerInfo::getPeerID))
                    .orElse(null);
        }

        private PeerInfo findSuccessor(PeerInfo newPeer) {
            // Logic to find the successor of newPeer
            // For simplicity, assume the successor is the peer with the smallest ID larger than newPeer.getPeerID()
            return peers.values().stream()
                    .filter(peer -> peer.getPeerID() > newPeer.getPeerID())
                    .min(Comparator.comparingInt(PeerInfo::getPeerID))
                    .orElse(null);
        }

        private void notifyPeer(PeerInfo peerInfo, BiConsumer<P2PServiceGrpc.P2PServiceBlockingStub, PeerInfo> method, PeerInfo newPeer) {
            ManagedChannel channel = ManagedChannelBuilder.forAddress(peerInfo.getAddress(), peerInfo.getPort())
                    .usePlaintext()
                    .build();

            P2PServiceGrpc.P2PServiceBlockingStub blockingStub = P2PServiceGrpc.newBlockingStub(channel);

            try {
                method.accept(blockingStub, newPeer);
            } finally {
                try {
                    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
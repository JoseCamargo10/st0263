package com.example;

import com.example.p2pnetwork.P2PServiceGrpc;
import com.example.P2PServer.P2PServiceImpl;
import com.example.p2pnetwork.P2PServiceProto.GreetingRequest;
import com.example.p2pnetwork.P2PServiceProto.GreetingResponse;
import com.example.p2pnetwork.P2PServiceProto.UploadInfoRequest;
import com.example.p2pnetwork.P2PServiceProto.UploadInfoResponse;
import com.example.p2pnetwork.P2PServiceProto.HashTableEntry;
import com.example.p2pnetwork.P2PServiceProto.HashTableResponse;
import com.example.p2pnetwork.P2PServiceProto.Empty;
import com.example.p2pnetwork.P2PServiceProto.DisconnectRequest;
import com.example.p2pnetwork.P2PServiceProto.DisconnectResponse;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import io.grpc.*;
import io.netty.handler.codec.http.multipart.FileUpload;

public class PeerServer {

    private final int peerID;
    private final int port;
    private Server server;

    // Constructor
    public PeerServer(int peerID, int port) {
        this.peerID = peerID;
        this.port = port;
    }

    // Start the server to accept connections
    public void startServer() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new P2PServiceImpl())
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
            notifyPeersAboutDisconnection();
            server.shutdown();
        }
    }

    // Keep server alive
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    // Send other peers a notification about a disconnection and update the hashtable
    private void notifyPeersAboutDisconnection() {
        ExecutorService executor = Executors.newFixedThreadPool(12); 
        try {
            for (int i = 50051; i < 50062; i++) {
                if (i != port) {
                    final int lambda_i = i;
                    Callable<Void> task = () -> {
                        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", lambda_i)
                                .usePlaintext()
                                .build();
    
                        P2PServiceGrpc.P2PServiceBlockingStub blockingStub = P2PServiceGrpc.newBlockingStub(channel);
    
                        DisconnectRequest request = DisconnectRequest.newBuilder()
                                .setPeerID(peerID)
                                .build();
    
                        try {
                            DisconnectResponse response = blockingStub.notifyDisconnection(request);
                            System.out.println("Peer " + lambda_i + " responded: " + response.getMessage());
                        } catch (StatusRuntimeException e) {
                            System.err.println("RPC failed on port " + lambda_i + ": " + e.getStatus());
                        } finally {
                            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
                        }
                        return null;
                    };
                    executor.submit(task);
                }
            }
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    // Generate file's key
    public static int generateFileKey(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hash = md.digest(key.getBytes());
            BigInteger hashInt = new BigInteger(1, hash);
            return hashInt.mod(BigInteger.valueOf(128)).intValue();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
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

        // Info about a new file uploaded on the network
        @Override
        public void sendUploadInfo(UploadInfoRequest request, StreamObserver<UploadInfoResponse> responseObserver) {
            int key = request.getKey();
            List<Integer> peers = request.getPeersList();

            PeerClient.updatePeerInfo(key, peers);

            System.out.println("File: " + key + " | in peers: " + peers);

            UploadInfoResponse response = UploadInfoResponse.newBuilder().setMessage("Thank You!").build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        // Message to request hashtable
        @Override
        public void getHashTable(Empty request, StreamObserver<HashTableResponse> responseObserver) {
            HashTableResponse.Builder responseBuilder = HashTableResponse.newBuilder();

            // Runs through hashtable and adds the info to response
            for (Map.Entry<Integer, List<Integer>> entry : PeerClient.getPeerInfo().entrySet()) {
                HashTableEntry.Builder entryBuilder = HashTableEntry.newBuilder().setKey(entry.getKey())
                        .addAllPeers(entry.getValue());
                responseBuilder.addEntries(entryBuilder.build());
            }

            HashTableResponse response = responseBuilder.build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        // Message of disconnection
        @Override
        public void notifyDisconnection(DisconnectRequest request, StreamObserver<DisconnectResponse> responseObserver) {
            int disconnectedPeerID = request.getPeerID();

            // Remove the peer from the hash table in all peers
            PeerClient.removePeer(disconnectedPeerID);

            // Create response
            DisconnectResponse response = DisconnectResponse.newBuilder()
                    .setMessage("Peer " + disconnectedPeerID + " disconnected.")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
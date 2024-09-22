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
                .addService(new P2PServiceImpl()) // Añadir el servicio gRPC
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
    }
}

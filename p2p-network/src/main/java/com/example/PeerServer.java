package com.example;

import com.example.p2pnetwork.P2PServiceGrpc;
import com.example.P2PServer.P2PServiceImpl;
import com.example.p2pnetwork.P2PServiceProto.GreetingRequest;
import com.example.p2pnetwork.P2PServiceProto.GreetingResponse;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;

public class PeerServer {
    private final String peerID;
    private final int port;
    private Server server;

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
}

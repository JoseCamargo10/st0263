package com.example;

import com.example.p2pnetwork.P2PServiceGrpc;
import com.example.p2pnetwork.P2PServiceProto.MessageRequest;
import com.example.p2pnetwork.P2PServiceProto.MessageResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class P2PClient {
    public static void main(String[] args) throws Exception {
        // Create a channel to the server
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

        // Create a stub for the service
        P2PServiceGrpc.P2PServiceBlockingStub stub = P2PServiceGrpc.newBlockingStub(channel);

        // Create a request
        MessageRequest request = MessageRequest.newBuilder().setMessage("Hello from the client!").build();

        // Call the gRPC
        MessageResponse response = stub.sendMessage(request);

        // Print response
        System.out.println("Response from server: " + response.getResponse());

        // Shutdown the channel
        channel.shutdownNow();
    }
}

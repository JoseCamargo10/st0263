package com.example;

import com.example.p2pnetwork.P2PServiceGrpc;
import com.example.p2pnetwork.P2PServiceProto.GreetingRequest;
import com.example.p2pnetwork.P2PServiceProto.GreetingResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.concurrent.TimeUnit;

public class PeerClient {
    private final String peerID;
    private final int port;

    public PeerClient(String peerID, int port) {
        this.peerID = peerID;
        this.port = port;
    }

    // Método para conectar a otro peer y enviar un saludo
    public void sendGreetingToPeer(String targetHost, int targetPort, String message) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(targetHost, targetPort)
                .usePlaintext()
                .build();

        P2PServiceGrpc.P2PServiceBlockingStub blockingStub = P2PServiceGrpc.newBlockingStub(channel);

        GreetingRequest request = GreetingRequest.newBuilder()
                .setMessage(message)
                .build();

        GreetingResponse response = blockingStub.sendGreeting(request);
        System.out.println("Response from peer: " + response.getReply());

        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

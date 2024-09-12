package com.example;

import com.example.p2pnetwork.P2PServiceGrpc;
import com.example.p2pnetwork.P2PServiceProto.MessageRequest;
import com.example.p2pnetwork.P2PServiceProto.MessageResponse;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class P2PServer {
    public static void main(String[] args) throws Exception {
        // Create the server and bind it to port 50051
        Server server = ServerBuilder.forPort(50051).addService(new P2PServiceImpl()).build().start();
        System.out.println("Server started on port 50051");
        server.awaitTermination();
    }

    // Implements services defined in the proto files
    static class P2PServiceImpl extends P2PServiceGrpc.P2PServiceImplBase {
        @Override
        public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
            String message = request.getMessage();
            System.out.println("Received message: " + message);

            // Create a response
            MessageResponse response = MessageResponse.newBuilder().setResponse("Chema la bulla").build();

            // Send the response
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}

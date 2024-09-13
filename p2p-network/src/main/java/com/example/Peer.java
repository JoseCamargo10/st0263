package com.example;

import com.example.p2pnetwork.P2PServiceGrpc;
import com.example.p2pnetwork.P2PServiceProto.PeerInfo;
import com.example.p2pnetwork.P2PServiceProto.PeerPosition;
import com.example.p2pnetwork.P2PServiceProto.JoinRequest;
import com.example.p2pnetwork.P2PServiceProto.JoinResponse;
import com.example.p2pnetwork.P2PServiceProto.LeaveRequest;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.*;

import sun.awt.util.ThreadGroupUtils;

public class Peer {
    private int peerID;
    private PeerInfo predecessor;
    private PeerInfo successor;
    private Server peerServer;  // gRPC server inside each peer
    private final Map<Integer, PeerInfo> peers = new HashMap<>();

    // Constructor
    public Peer(int peerID) {
        this.peerID = peerID;
        this.predecessor = null;
        this.successor = null;
    }

    // Client
    public void runClient() {
        // Client's logic, maybe GUI or cmd
        System.out.println("COMPONENT CLIENT!");
    }

    // Server
    public void startServer(int port) throws IOException {
        peerServer = ServerBuilder.forPort(port).addService(new P2PServer.P2PServiceImpl()).build().start();
        System.out.println("INITIATED SERVER ON PORT: " + port);
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("SHUTTING SERVER DOWN...");
            Peer.this.stopServer();
        }));
    }

    // Method to stop server
    public void stopServer() {
        if (peerServer != null) {
            peerServer.shutdown();
        }
    }

    // Implementation of gRPC service and core Server functionality
    private class P2PServiceImpl extends P2PServiceGrpc.P2PServiceImplBase {

        @Override
        public void joinNetwork(JoinRequest request, StreamObserver<JoinResponse> responseObserver) {
            PeerInfo newPeer = request.getNewPeer();
            int newPeerID = newPeer.getPeerID();

            //Add the new peer on peers' map
            peers.put(newPeerID, newPeer);

            // Look for successor and predecessor
            PeerInfo successor = findNeighbor(newPeerID, "successor");
            PeerInfo predecessor = findNeighbor(newPeerID, "predecessor");
            System.out.println(successor);
            System.out.println(predecessor);
        }

        private PeerInfo findNeighbor(int newPeerID, String neighbor) {
            List<Integer> sortedPeerIDs = new ArrayList<>(peers.keySet());
            sortedPeerIDs.add(peerID);  // Add the actual peer
            Collections.sort(sortedPeerIDs);

            int index = sortedPeerIDs.indexOf(newPeerID);
            int targetIndex;
            if (neighbor.equals("successor")) {
                targetIndex = (index + 1) % sortedPeerIDs.size(); // Next peer for successor
            } else {
                targetIndex = (index - 1 + sortedPeerIDs.size()) % sortedPeerIDs.size();    // Previous peer for predecessor
            }

            int targetPeerID = sortedPeerIDs.get(targetIndex);

            return peers.getOrDefault(targetPeerID, PeerInfo.newBuilder().setPeerID(peerID)
            .setAddress("localhost").setPort(50051).build());
        }
    }

    // Code to generate automatic IDs using SHA-1
    public static int generatePeerID(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hash = md.digest(key.getBytes());
            BigInteger hashInt = new BigInteger(1, hash);
            return hashInt.mod(BigInteger.valueOf(64)).intValue();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter your full name: ");
        String text = userInput.nextLine();
        int key = generatePeerID(text);
        System.out.println("Your key is: " + key);
        
        System.out.print("Enter a port number for the peer: ");
        int port = userInput.nextInt();
        userInput.close();

        Peer peer = new Peer(key);
        peer.startServer(port);
        //peer.runClient();
        peer.peerServer.awaitTermination();
    }
}

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
import javax.swing.*;

import com.sun.security.ntlm.Client;

import sun.awt.util.ThreadGroupUtils;

public class Peer {
    private final int peerID;
    private final int port;
    private PeerServer server;
    private PeerClient client;
    /*private PeerInfo predecessor;
    private PeerInfo successor;*/

    public Peer(int peerID, int port) {
        this.peerID = peerID;
        this.port = port;
        this.server = new PeerServer(peerID, port);
        this.client = new PeerClient(peerID, port);
        /*this.predecessor = null;  // Initially, no predecessor
        this.successor = null;*/    // Initially, no successor
    }

    
    // Getter and Setter for predecessor
    /*public PeerInfo getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(PeerInfo predecessor) {
        this.predecessor = predecessor;
    }

    // Getter and Setter for successor
    public PeerInfo getSuccessor() {
        return successor;
    }

    public void setSuccessor(PeerInfo successor) {
        this.successor = successor;
    }*/


    // Start server anc client
    public void start() throws IOException, InterruptedException {
        // Start server
        server.startServer();
        // Start client interface
        client.userInterface(peerID, port);

        // Keep server alive
        new Thread(() -> {
            try {
                server.blockUntilShutdown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Método para enviar un saludo a otro peer
    public void sendGreeting(String targetHost, int targetPort, String message) {
        client.sendGreetingToPeer(targetHost, targetPort, message);
    }

    public static int generatePeerID(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hash = md.digest(key.getBytes());
            BigInteger hashInt = new BigInteger(1, hash);
            return hashInt.mod(BigInteger.valueOf(128)).intValue();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // New frame
        JFrame frame = new JFrame("P2P Network | Register");
        frame.setSize(1000,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to contain the components
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setVisible(true);
        panel.setLayout(null);

        // Title label
        JLabel titleLabel = new JLabel("PEER REGISTER");
        titleLabel.setBounds(10, 20, 200, 25);
        panel.add(titleLabel);

        // PEERID
        JLabel idLabel = new JLabel("PEERID:");
        idLabel.setBounds(10, 60, 80, 25);
        panel.add(idLabel);
        JTextField idField = new JTextField(20);
        idField.setBounds(100, 60, 165, 25);
        panel.add(idField);

        // PORT
        JLabel portLabel = new JLabel("PORT:");
        portLabel.setBounds(10, 100, 80, 25);
        panel.add(portLabel);
        JTextField portField = new JTextField(20);
        portField.setBounds(100, 100, 165, 25);
        panel.add(portField);

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(10, 140, 100, 25);
        panel.add(registerButton);

        // Action
        registerButton.addActionListener(e -> {
            int peerID = generatePeerID(idField.getText());
            int port = Integer.parseInt(portField.getText());
            Peer peer = new Peer(peerID, port);
            frame.dispose();
            try {
                peer.start();
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }

    // First try of DHT INCOMPLETE and INCORRECT
    /*private int peerID;
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
    }*/
}

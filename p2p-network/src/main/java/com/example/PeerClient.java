package com.example;

import java.awt.Frame;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.p2pnetwork.P2PServiceGrpc;
import com.example.p2pnetwork.P2PServiceProto.GreetingRequest;
import com.example.p2pnetwork.P2PServiceProto.GreetingResponse;
import com.example.p2pnetwork.P2PServiceProto.UploadInfoRequest;
import com.example.p2pnetwork.P2PServiceProto.UploadInfoResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class PeerClient {
    private final int peerID;
    private final int port;
    private final Map<Integer, List<Integer>> hashTable = new HashMap<>();

    public PeerClient(int peerID, int port) {
        this.peerID = peerID;
        this.port = port; 
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

    // GUI
    public void userInterface (int peerID, int port) {
        // New frame
        JFrame frame = new JFrame("P2P Network | Peer ID: " + peerID + " | Port: " + port);
        frame.setSize(1000,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to contain the components
        JPanel panel = new JPanel();
        frame.add(panel);
        mainMenu(panel);

        frame.setVisible(true);
    }

    // Main Menu interface
    private void mainMenu(JPanel panel) {
        panel.setLayout(null);

        // Title label
        JLabel titleLabel = new JLabel("MENU");
        titleLabel.setBounds(10, 20, 80, 25);
        panel.add(titleLabel);

        // Message Button
        JButton messageButton = new JButton("Send Message");
        messageButton.setBounds(10, 60, 150, 25);
        panel.add(messageButton);
        messageButton.addActionListener(e -> {
            clean(panel);
            messageMenu(panel);
        });

        // Upload Button
        JButton uploadButton = new JButton("Upload File");
        uploadButton.setBounds(180,60,150,25);
        panel.add(uploadButton);
        uploadButton.addActionListener(e -> {
            clean(panel);
            uploadFileMenu(panel);
        });

        // Download Button
        JButton downloadButton = new JButton("Download File");
        downloadButton.setBounds(10,105,150,25);
        panel.add(downloadButton);
        downloadButton.addActionListener(e -> {
            clean(panel);
            downloadFileMenu(panel);
        });  
    }

    // Download File Menu
    private void downloadFileMenu(JPanel panel) {
        JLabel fileLabel = new JLabel("File to download:");
        fileLabel.setBounds(10, 20, 100, 25);
        panel.add(fileLabel);
        JTextField fileField = new JTextField(20);
        fileField.setBounds(120, 20, 165, 25);
        panel.add(fileField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(10, 60, 100, 25);
        panel.add(searchButton);

        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(120, 60, 300, 25);
        panel.add(resultLabel);

        searchButton.addActionListener(e -> {
            String fileNameText = fileField.getText();
            int key = generateFileKey(fileNameText);
            
            // Busca el archivo en la tabla hash
            List<Integer> ports = hashTable.get(key);
            
            // Muestra el resultado
            if (ports != null && !ports.isEmpty()) {
                resultLabel.setText("File found at peers: " + ports.toString());
            } else {
                resultLabel.setText("File not found.");
            }
        });
    }

    // Upload File Menu
    private void uploadFileMenu(JPanel panel) {
        JLabel fileLabel = new JLabel("File to upload:");
        fileLabel.setBounds(10, 20, 100, 25);
        panel.add(fileLabel);
        JTextField fileField = new JTextField(20);
        fileField.setBounds(120, 20, 165, 25);
        panel.add(fileField);

        JButton uploadButton = new JButton("Upload");
        uploadButton.setBounds(10, 60, 100, 25);
        panel.add(uploadButton);

        uploadButton.addActionListener(e -> {
            String fileNameText = fileField.getText();
            int key = generateFileKey(fileNameText);
            List<Integer> peers = hashTable.getOrDefault(key, new ArrayList<>());

            if (!peers.contains(peerID)) {
                peers.add(peerID);
            }

            hashTable.put(key, peers);
            uploadFile(key, peers, port);
            System.out.println(hashTable);
            clean(panel);
            mainMenu(panel);
        });
    }

    // Message Menu
    private void messageMenu(JPanel panel) {
        JLabel targetPortLabel = new JLabel("Target Port:");
        targetPortLabel.setBounds(10, 20, 80, 25);
        panel.add(targetPortLabel);
        JTextField targetPortField = new JTextField(20);
        targetPortField.setBounds(100, 20, 165, 25);
        panel.add(targetPortField);

        JLabel messageLabel = new JLabel("Message:");
        messageLabel.setBounds(10, 60, 80, 25);
        panel.add(messageLabel);
        JTextField messageField = new JTextField(20);
        messageField.setBounds(100, 60, 165, 25);
        panel.add(messageField);

        JButton sendButton = new JButton("Send");
        sendButton.setBounds(10, 100, 100, 25);
        panel.add(sendButton);

        sendButton.addActionListener(e -> {
            int targetPort = Integer.parseInt(targetPortField.getText());
            String message = messageField.getText();
            sendGreetingToPeer("localhost", targetPort, message);
            clean(panel);
            mainMenu(panel);
        });

        panel.revalidate();
        panel.repaint();
    }

    // Clean the panel
    private void clean(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    // Method to say hi to another peer
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

    // Method to upload a file
    public void uploadFile(int key, List<Integer> peers, int port) {
        ExecutorService executor = Executors.newFixedThreadPool(12);  // Threads for connections

        try {
            for (int i = 50051; i < 50062; i++) {
                if (i != port) {
                    final int lambda_i = i;
                    Callable<Void> task = () -> {
                        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", lambda_i)
                                .usePlaintext()
                                .build();

                        P2PServiceGrpc.P2PServiceBlockingStub blockingStub = P2PServiceGrpc.newBlockingStub(channel);

                        UploadInfoRequest request = UploadInfoRequest.newBuilder().setKey(key).addAllPeers(peers).build();

                        try {
                            UploadInfoResponse response = blockingStub.sendUploadInfo(request);
                            System.out.println("Peer says: " + response);
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
}
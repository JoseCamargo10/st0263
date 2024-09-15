package com.example;

import java.awt.Frame;

import com.example.p2pnetwork.P2PServiceGrpc;
import com.example.p2pnetwork.P2PServiceProto.GreetingRequest;
import com.example.p2pnetwork.P2PServiceProto.GreetingResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class PeerClient {
    private final String peerID;
    private final int port;

    public PeerClient(String peerID, int port) {
        this.peerID = peerID;
        this.port = port;
    }

    // GUI
    public void userInterface (String peerID, int port) {
        // New frame
        JFrame frame = new JFrame("P2P Network | " + peerID + " | " + port);
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

        // Upload Button
        JButton uploadButton = new JButton("Upload File");
        uploadButton.setBounds(180,60,150,25);
        panel.add(uploadButton);

        // Download Button
        JButton downloadButton = new JButton("Download File");
        downloadButton.setBounds(10,105,150,25);
        panel.add(downloadButton);

        messageButton.addActionListener(e -> {
            clean(panel);
            messageMenu(panel);
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
}

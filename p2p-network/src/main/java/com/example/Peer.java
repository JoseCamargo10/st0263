package com.example;

import com.example.p2pnetwork.P2PServiceGrpc;

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
    private final PeerServer server;
    private final PeerClient client;

    public Peer(int peerID, int port) {
        this.peerID = peerID;
        this.port = port;
        this.server = new PeerServer(peerID, port);
        this.client = new PeerClient(peerID, port);
    }


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
}

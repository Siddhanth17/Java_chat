package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(5000)) {
            System.out.println("Chat Server is running on port 5000...");
            while (true) {
                Socket client = server.accept();
                System.out.println("New client connected: " + client.getInetAddress());
                new ClientHandler(client).start();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
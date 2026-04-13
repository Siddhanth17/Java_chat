package server;

import java.net.*;

public class ChatServer {
    public static void main(String[] args){
        ServerSocket server = new ServerSocket(5000);
        System.out.println("Chat Server is running on port 5000...");
        while(true){
            Socket client = server.accept();
            System.out.println("New client connected: " + client.getInetAddress());
            new ClientHandler(client).start();
        }
    
    }
}
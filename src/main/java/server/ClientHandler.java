package server;
import java.io.*;
import java.net.*;

public class ClientHandler extends Thread{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;

    public ClientHandler(Socket socket) throws Exception{
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void run(){
        try{
            String msg;

            while((msg = in.readLine()) != null){
                System.out.println("Received: " + msg);

                String[] parts = msg.split(":", 3);
                String type = parts[0];

                if(type.equals("LOGIN")){
                    username = parts[1];
                }
            }
        }
    }

}
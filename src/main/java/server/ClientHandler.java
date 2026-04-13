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
                    UserManager.addUser(username, this);
                    System.out.println(username + "joined");

                    for (ClientHandler user : UserManager.getAllUsers()) {
                        user.out.println("🟢 " + username + " joined the chat");
                    }

                    // ================= MESSAGE =================
                    else if (type.equals("MSG")) {
                        String receiver = parts[1];
                        String content = parts[2];
                    
                        // 📢 Broadcast
                        if (receiver.equals("ALL")) {
                            for (ClientHandler user : UserManager.getAllUsers()) {
                                user.out.println(username + ": " + content);
                            }
                        }
                    
                        // 🔒 Private message
                        else {
                            ClientHandler user = UserManager.getUser(receiver);
                            if (user != null) {
                                user.out.println("(Private) " + username + ": " + content);
                            } else {
                                out.println("❌ User not found");
                            }
                        }
                    }

                    // ================= LOGOUT =================
                    else if (type.equals("LOGOUT")) {
                        handleLogout();
                        break;
                    }

                    catch (Exception e) {
                        // ⚪ Unexpected disconnect
                        System.out.println(username + " disconnected unexpectedly");
                        cleanup();
                    }
                }

                
            }
        }
    }

}
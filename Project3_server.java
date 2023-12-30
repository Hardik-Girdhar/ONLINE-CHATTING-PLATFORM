import java.util.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
public class Project3_server {
    ArrayList<Socket> sockets=new ArrayList<>();

    public void broadcast(String message)
    {
        for(Socket socket:sockets)
        {
            try{
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            writer.println(message);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    }

    public void run()
    {
        try {
            ServerSocket serverSocket=new ServerSocket(4444);
            System.out.println("Server started on port 4444");

            while(true)
            {
                Socket clientSocket=serverSocket.accept();
                sockets.add(clientSocket);
                System.out.println("Client connected");

                Project3_clienthandler clientHandler=new Project3_clienthandler(clientSocket, this);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Project3_server().run();
    }
}

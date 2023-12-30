import java.util.*;
import java.net.*;
import java.nio.Buffer;

import javax.swing.*;
import java.awt.*;
import java.io.*;
public class Project3_clienthandler implements Runnable{
    Socket clientSocket;
    BufferedReader bReader;
    PrintWriter pWriter;
    Project3_server server;

    public Project3_clienthandler(Socket clientSocket, Project3_server server)
    {
        this.clientSocket=clientSocket;
        this.server=server;
        try {
            this.bReader=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.pWriter=new PrintWriter(clientSocket.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String name="";
        while(true){
        pWriter.println("SUBMITNAME");
        try {
            name=bReader.readLine();
            if(name==null) return;
            if(!name.isEmpty()) break;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    pWriter.println("NAMEACCEPTED");
    server.broadcast(name + " has connected." );

    while(true)
    {
        try {
            String userMessage= bReader.readLine();
            if(userMessage==null) return;
            server.broadcast("MESSAGE " +name+" : " +userMessage);
        } catch (IOException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
        
    }

    public static void main(String[] args) {
        
    }
}

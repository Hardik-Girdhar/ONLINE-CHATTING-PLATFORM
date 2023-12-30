import java.util.*;
import java.net.*;

import javax.management.remote.JMXProviderException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Project3_client {
    JFrame frame=new JFrame("Chat Application");
        JTextField sendMessageTextField = new JTextField(40);
        JTextArea messageTextArea = new JTextArea(8,40);
        BufferedReader bufferedReader;
        PrintWriter pWriter;

    public Project3_client()
    {
        sendMessageTextField.setEditable(false);
        messageTextArea.setEditable(false);
        frame.getContentPane().add(sendMessageTextField,"North");
        frame.getContentPane().add(messageTextArea,"Center");
        frame.pack();

        sendMessageTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pWriter.println(sendMessageTextField.getText());
                sendMessageTextField.setText("null");
            }
            
        });

    }

    public void connect()
    {
        String serveraddress=JOptionPane.showInputDialog(frame, "Enter the server IP: " + "Connect to server" ,
        JOptionPane.QUESTION_MESSAGE);

        try {
            Socket socket=new Socket(serveraddress , 4444);
            bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pWriter=new PrintWriter(socket.getOutputStream(), true);

            while(true)
            {
                String line=bufferedReader.readLine();
                if(line.startsWith("SUBMITNAME"))
                {
                    String name=JOptionPane.showInputDialog(frame, "Enter your name: ",
                    "Name Selection",JOptionPane.PLAIN_MESSAGE);
                    pWriter.println(name);
                }
                else if(line.startsWith("NAMEACCEPTED"))
                {
                sendMessageTextField.setEditable(true);
                }
                else if(line.startsWith("MESSAGE"))
                {
                    messageTextArea.append(line.substring(7)+"\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    public static void main(String[] args) {
        Project3_client client=new Project3_client();
        client.frame.setVisible(true);
        client.connect();
    }
}

# ONLINE-CHATTING-PLATFORM

Create a chat application that allows multiple users to connect and chat with each other in real-time. The application should have a GUI using Swing that displays a chat window for each user connected to the chat room. The chat window should allow users to enter messages and view messages from other users.
Here's a breakdown of how you could approach each of the topics:

**Networking:**

Implement a server that listens for incoming connections from clients and routes messages between connected clients.
Implement a client that can connect to the server, send messages to the server, and receive messages from the server.

**Multithreading:**

Use a separate thread to listen for incoming messages from the server so that the GUI remains responsive and doesn't block while waiting for incoming messages.
Use a separate thread to send messages to the server so that the GUI remains responsive and doesn't block while sending messages.
In the server implementation, use a thread for each client that receives messages from that specific client and reroutes it to the server to broadcast it

**GUI using Swing:**

Create a main window that allows users to enter their username and connect to the chat room.
Create a chat window that displays the messages sent and received by the user.
Use Swing components such as JTextArea and JTextField to implement the chat window and allow users to enter messages.

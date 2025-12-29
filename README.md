TCP Socket Chat Application
A Java-based multi-client chat application that allows multiple users to communicate in real time using TCP sockets.
The server acts as a central relay that accepts client connections and forwards messages between them.
The client provides a Swing-based graphical interface for sending and receiving messages.

Features

👥 Multi-client support

🌍 Public message broadcasting

🔁 Full-duplex communication

📦 Reliable and ordered message delivery using TCP

🧵 Multi-threaded server

🖥️ Swing-based GUI

Files

ChatServer.java — TCP server (connection handling and message broadcasting)

ClientUI.java — Swing-based chat client

How It Works

Multiple clients connect to the server

Each client is handled in a separate thread

Messages sent by one client are broadcast to all connected clients

Clients do not communicate directly; all messages pass through the server

How to Run
1️⃣ Compile
javac ChatServer.java ClientUI.java

2️⃣ Run the server
java ChatServer

3️⃣ Run clients (multiple times)
java ClientUI

Usage

Type a message and click Send

All connected clients will receive the message in real time

Technologies Used

Java

TCP Sockets

Multithreading

Java Swing

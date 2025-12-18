import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    // Store all connected clients
    static List<PrintWriter> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server running on port 5000...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket.getInetAddress());

                // Handle client in a new thread
                new ClientHandler(socket).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler extends Thread {
        Socket socket;
        BufferedReader in;
        PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                synchronized (clients) {
                    clients.add(out);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    broadcast(message);
                }
            } catch (IOException e) {
                System.out.println("Client disconnected");
            } finally {
                synchronized (clients) {
                    clients.remove(out);
                }
            }
        }

        private void broadcast(String message) {
            synchronized (clients) {
                for (PrintWriter writer : clients) {
                    writer.println(message);
                }
            }
        }
    }
}

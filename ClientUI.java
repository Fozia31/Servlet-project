import java.io.*;
import java.net.*;
import javax.swing.*;

public class ClientUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("TCP Client Chat");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBounds(20, 20, 440, 220);
        frame.add(scrollPane);

        JTextField messageField = new JTextField();
        messageField.setBounds(20, 260, 300, 30);
        frame.add(messageField);

        JButton sendBtn = new JButton("Send");
        sendBtn.setBounds(350, 260, 100, 30);
        frame.add(sendBtn);

        frame.setVisible(true);

        try {
            Socket socket = new Socket("127.0.0.1", 5000);
            chatArea.append("Connected to server\n");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // Listening thread
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        chatArea.append(msg + "\n");
                    }
                } catch (IOException e) {
                    chatArea.append("Disconnected from server\n");
                }
            }).start();

            // Send button action
            sendBtn.addActionListener(e -> {
                String msg = messageField.getText();
                if (!msg.isEmpty()) {
                    out.println(msg);
                    messageField.setText("");
                }
            });

        } catch (IOException e) {
            chatArea.append("Unable to connect to server\n");
        }
    }
}

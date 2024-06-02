package Program;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientController {
    private ServerController serverController;
    private JFrame frame;
    private JTextArea chatHistoryArea;
    private JTextField messageField;
    private JTextField userField;
    private JButton sendButton;
    private JButton clearButton;
    private JButton loginButton;
    private JButton exitButton;
    public boolean isChatWorking;

    public ClientController(ServerController serverController, String userName, Color chatColor, Color chatTextColor, int xPosition) {
        this.serverController = serverController;
        frame = new JFrame("Client Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 500);
        frame.setBounds(xPosition, 0, 300, 500);

        chatHistoryArea = new JTextArea();
        chatHistoryArea.setEditable(false);
        chatHistoryArea.setBackground(chatColor);

        JScrollPane scrollPane = new JScrollPane(chatHistoryArea);
        frame.add(scrollPane, BorderLayout.CENTER);
        chatHistoryArea.setForeground(chatTextColor);

        messageField = new JTextField();
        sendButton = new JButton("Send");
        clearButton = new JButton("Clear");
        loginButton = new JButton("Login");
        exitButton = new JButton("Mute");

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());
        messagePanel.add(messageField, BorderLayout.CENTER);
        messagePanel.add(sendButton, BorderLayout.EAST);
        messagePanel.add(clearButton, BorderLayout.SOUTH);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new FlowLayout());
        loginPanel.add(loginButton);
        loginPanel.add(exitButton);

        userField = new JTextField(userName, 0);
        messagePanel.add(userField, BorderLayout.NORTH);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(messagePanel, BorderLayout.SOUTH);
        frame.add(loginPanel, BorderLayout.NORTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearChatHistory();
            }

            private void clearChatHistory() {
                messageField.setText("");
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageField.setText("");
                login();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isChatWorking = false;
                JOptionPane.showMessageDialog(frame, "Сервер отключён.\n");
            }
        });

        frame.setVisible(true);
    }

    private void sendMessage() {
        if (ServerWindow.isServerWorking && isChatWorking) {
            String user = userField.getText();
            String message = messageField.getText();
            if (!message.isEmpty()) {
                serverController.logMessage(user + " : " + message);
                serverController.logMessage("\n");
                messageField.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Сервер отключён.\n");
        }
    }

    private void login() {
        if (ServerWindow.isServerWorking) {
            isChatWorking = true;
            JOptionPane.showMessageDialog(frame, "Сервер запущен.\n");
            updateChatHistory();
            chatTimer();
        } else {
            JOptionPane.showMessageDialog(frame, "Сервер отключён.\n");
        }
    }

    private void chatTimer() {
        Timer timer = new Timer(1000, e -> updateChatHistory());
        timer.start();
    }

    private void updateChatHistory() {
        if (serverController.hasUpdates()) {
            String history = serverController.getChatHistory();
            chatHistoryArea.setText(history);
        }
    }
}

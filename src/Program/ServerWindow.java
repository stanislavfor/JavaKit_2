package Program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    private ServerController serverController;
    public static JTextArea chatHistoryArea;
    public static boolean isServerWorking = false;


    public ServerWindow() {
        serverController = new ServerController();

        JFrame frame = new JFrame("Server Window");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 600);

            chatHistoryArea = new JTextArea();
            chatHistoryArea.setForeground(Color.BLUE);
            chatHistoryArea.setEditable(false);
            chatHistoryArea.setBackground(Color.CYAN);
            JScrollPane scrollPane = new JScrollPane(chatHistoryArea);
            frame.add(scrollPane, BorderLayout.CENTER);

            // Панель для кнопок управления сервером
            JPanel buttonPanel = new JPanel();
            JButton startButton = new JButton("Start");
            startButton.setBackground(Color.GREEN);
            JButton stopButton = new JButton("Stop");
            stopButton.setBackground(Color.RED);
            buttonPanel.add(startButton);
            buttonPanel.add(stopButton);
            frame.add(buttonPanel, BorderLayout.SOUTH);


            // Обработчик нажатия кнопки запуска сервера
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    serverController.startServer();
                }
            });

            // Обработчик нажатия кнопки остановки сервера
            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    serverController.stopServer();
                }
            });

            frame.setVisible(true);
    }



}

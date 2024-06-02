package Program;

import java.io.*;

import static Program.ServerWindow.chatHistoryArea;
import static Program.ServerWindow.isServerWorking;


public class ServerController {

    private final File logFile = new File("src/File/log.txt");
    private long lastModified = 0;

    public void startServer() {
        if (!isServerWorking) {
            isServerWorking = true;
        }
        if (logFile.exists()) {
            getChatHistory();
        }
        chatHistoryArea.append(":сервер стартовал\n");
    }

    public void stopServer() {
        isServerWorking = false;
        chatHistoryArea.append(":сервер остановлен\n");
    }

    public void logMessage(String message) {
        // Запись сообщения в историю чата и файл log.txt
        chatHistoryArea.append(message);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean hasUpdates() {
        return logFile.lastModified() > lastModified;
    }

    public String getChatHistory() {
        StringBuilder history = new StringBuilder();
        if (logFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    history.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return history.toString();
    }



}

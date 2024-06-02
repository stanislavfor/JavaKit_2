import Program.*;

public class Main {

    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        ServerController serverController = new ServerController();
        new ClientFirstGUI(serverController);
        new ClientSecondGUI(serverController);
        new ClientThirdGUI(serverController);
//        ClientFirstGUI clientFirstGUI = new ClientFirstGUI(serverController);
//        ClientSecondGUI clientSecondGUI = new ClientSecondGUI(serverController);

    }
}
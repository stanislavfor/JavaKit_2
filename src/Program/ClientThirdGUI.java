package Program;

import javax.swing.*;
import java.awt.*;

public class ClientThirdGUI extends JFrame {

    private ClientController clientController;

    public ClientThirdGUI(ServerController serverController) {
        clientController = new ClientController(serverController, "third_user", Color.ORANGE, Color.BLACK, 310);
    }
}

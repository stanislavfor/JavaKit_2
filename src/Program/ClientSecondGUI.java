
package Program;

import javax.swing.*;
import java.awt.*;

public class ClientSecondGUI extends JFrame {
    private ClientController clientController;

    public ClientSecondGUI(ServerController serverController) {
        clientController = new ClientController(serverController, "second_user", Color.GREEN, Color.BLACK, 620);
    }
}




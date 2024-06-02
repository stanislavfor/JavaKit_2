// ClientFirstGUI.java
package Program;

import javax.swing.*;
import java.awt.*;

public class ClientFirstGUI extends JFrame {
    private ClientController clientController;

    public ClientFirstGUI(ServerController serverController) {
        clientController = new ClientController(serverController, "first_user", Color.MAGENTA, Color.WHITE, 930);
    }
}




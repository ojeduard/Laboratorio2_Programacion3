package Controller;

import Model.*;
import View.*;

public class Controller {
    public void startApp(){
        // Viewing and Controlling the application's GUI
        dbGUI view = new dbGUI();
        view.setVisible(true);
    }
}

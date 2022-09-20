package Controller;

import Model.*;
import View.*;

public class Controller {

    private Department deptMan = new Department();

    public void startApp(){
        // Viewing and Controlling the application's GUI
        dbGUI view = new dbGUI();
        view.setVisible(true);
    }

    public String[] getEmpAsStringArray(String ID) {
        deptMan.loadFromXML("xmlFI87.xml");
        return deptMan.getEmpAsStringArray(ID);
    }
}

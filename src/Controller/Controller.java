package Controller;

import Model.*;
import View.*;

public class Controller {

    private EmployeeManager empMan = new Department();

    public void startApp(){
        // Viewing and Controlling the application's GUI
        dbGUI view = new dbGUI();
        view.setVisible(true);
    }

    public String[] getEmpAsStringArray(String ID) {
        return empMan.getEmpAsStringArray(ID);
    }
}

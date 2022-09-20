package Controller;

import Model.*;
import View.*;

public class Controller {

    private EmployeeManager empMan = new EmployeeManager();

    public void startApp(){
        // Viewing and Controlling the application's GUI
        dbGUI view = new dbGUI();
        view.setVisible(true);
    }

    public String[] getEmpAsStringArray(String ID) {
        empMan.loadFromXML("empDB.xml");
        return empMan.getEmpAsStringArray(ID);
    }

    public void addingEmployees(String id, String name, String phone, String salary, String dptoCode){
        empMan.add(new Employee(id, name, Integer.parseInt(phone), Double.parseDouble(salary), dptoCode));
        empMan.createXML("Employees.xml");
    }
}

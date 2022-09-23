package Controller;

import Model.*;
import View.*;

public class Controller {

    private EmployeeManager empMan;
    private DepartmentManager depts;

    public void startApp(){
        // Viewing and Controlling the application's GUI
        dbGUI view = new dbGUI();
        view.setVisible(true);
    }

    public String[] getDepAsStringArray(String code) {
        depts = new DepartmentManager();
        depts.loadFromXML("Department.xml");
        return depts.getDepAsStringArray(code);
    }

    public String[] getEmpAsStringArray(String ID) {
        empMan = new EmployeeManager();
        empMan.loadFromXML("Employees.xml");
        return empMan.getEmpAsStringArray(ID);
    }

    public Boolean addingEmployees(String id, String name, String phone, String salary, String dptoCode){
        empMan = new EmployeeManager();
        empMan.loadFromXML("Employees.xml");
        if(empMan.add(new Employee(id, name, Integer.parseInt(phone), Double.parseDouble(salary), dptoCode))){
            empMan.createXML("Employees.xml");
            return true;
        }
        return false;
    }

    public Boolean addingDeparment(String code, String name, String address, String latitude, String longitude){
        depts = new DepartmentManager();
        depts.loadFromXML("Department.xml");
        if(depts.add(new Department(code, name, address, new Coordinates(Double.parseDouble(latitude), Double.parseDouble(longitude))))){
            depts.createXML("Department.xml");
            return true;
        }
        return false;
    }

    public Boolean deletingDep(String code) {
        depts = new DepartmentManager();
        depts.loadFromXML("Department.xml");
        if(depts.delete(code)) {
            depts.createXML("Department.xml");
            return true;
        }
        return false;
    }

    public Boolean deletingEmployee(String id) {
        empMan = new EmployeeManager();
        empMan.loadFromXML("Employees.xml");
        if(empMan.delete(id)) {
            empMan.createXML("Employees.xml");
            return true;
        }
        return false;
    }

    public void modifyEmp(String id, String name, String phone, String salary, String deptCode) {
        empMan = new EmployeeManager();
        empMan.loadFromXML("Employees.xml");
        empMan.getEmployeeByID(id).modify(name, Integer.parseInt(phone), Double.parseDouble(salary), deptCode);
        empMan.createXML("Employees.xml");
    }

    public void modifyDept(String code, String name, String address, String latitude, String longitude) {
        depts = new DepartmentManager();
        depts.loadFromXML("Department.xml");
        depts.getDepartmentbyCode(code).modify(name, address, new Coordinates(Double.parseDouble(latitude),Double.parseDouble(longitude)));
        depts.createXML("Department.xml");
    }
}

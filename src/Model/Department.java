package Model;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Department {
    String deptName;
    String deptCode;
    String address;
    Coordinates coordinates;

    public Department(String deptCode, String deptName, String address, Coordinates coordinates) {
        this.deptName = deptName;
        this.deptCode = deptCode;
        this.address = address;
        this.coordinates = coordinates;
    }

    public Department(){
        deptName = new String();
        deptCode = new String();
        address = new String();
        coordinates = new Coordinates(0, 0);
    }
    
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String[] getAsStringArray() {
        return new String[]{ deptCode,deptName,address, String.valueOf(coordinates.getX()),String.valueOf(coordinates.getY())};
    }

    @Override
    public String toString() {return deptName + ", " + deptCode + ", " + address + ", " + coordinates;
    }

//    public static void main(String[] args) {
//        Employee employee1 = new Employee( "116500136", "Aaron", 85089546, 3000, "AFZ24");
//        Employee employee2 = new Employee( "116500146", "Alonso", 22615443, 2500, "SJO326");
//        Employee employee3 = new Employee( "416500136", "Cesar", 40586543, 1800, "USA413");
//
////        EmployeeManager employeeList = new EmployeeManager();
////        employeeList.add(employee1);
////        employeeList.add(employee2);
////        employeeList.add(employee3);
//
//        Department dep = new Department("HR", "AFZ224", "Heredia", new Coordinates(100, 250));
//
//        dep.loadFromXML("Ejemplo.xml");
//
//        System.out.println(dep.toString());
//    }
}
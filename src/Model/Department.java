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
    String deptCode;
    String deptName;
    String address;
    Coordinates coordinates;

    public Department(String deptCode, String deptName, String address, Coordinates coordinates) {
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.address = address;
        this.coordinates = coordinates;
    }

    public Department(){
        deptCode = new String();
        deptName = new String();
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
}
package Model;

import java.io.File;
import java.io.IOException;
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

public class Department extends EmployeeManager {
    String deptName;
    String deptCode;
    String address;
    Coordinates coordinates;

    public Department(String deptName, String deptCode, String address, Coordinates coordinates) {
        super();
        this.deptName = deptName;
        this.deptCode = deptCode;
        this.address = address;
        this.coordinates = coordinates;
    }

    public Department(){
        super();
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

    @Override
    public Boolean createXML(String filename) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // creation of root element
            Element root = document.createElement("departmentDB");
            document.appendChild(root);

            // writting elements
            // deptCode for dept element
            Attr attr = document.createAttribute("deptCode");
            attr.setValue(this.getDeptCode());
            root.setAttributeNode(attr);

            // deptName element
            Element deptName = document.createElement("deptName");
            deptName.appendChild(document.createTextNode(this.getDeptName()));
            root.appendChild(deptName);

            // address element
            Element address = document.createElement("address");
            address.appendChild(document.createTextNode(this.getAddress()));
            root.appendChild(address);

            // x coordinate element
            Element xCoordinate = document.createElement("xCoordinate");
            xCoordinate.appendChild(document.createTextNode(Double.toString(this.getCoordinates().getX())));
            root.appendChild(xCoordinate);

            // y coordinate element
            Element yCoordinate = document.createElement("yCoordinate");
            yCoordinate.appendChild(document.createTextNode(Double.toString(this.getCoordinates().getY())));
            root.appendChild(yCoordinate);

            for(Employee employee : employeeList) {

                Element employeeXML = document.createElement("employee");
                root.appendChild(employeeXML);

                Attr attrEmp = document.createAttribute("id");
                attrEmp.setValue(employee.getId());
                employeeXML.setAttributeNode(attrEmp);

                // name element
                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(employee.getName()));
                employeeXML.appendChild(name);

                // phone element
                Element phone = document.createElement("phone");
                phone.appendChild(document.createTextNode(Integer.toString(employee.getPhone())));
                employeeXML.appendChild(phone);

                // salary element
                Element salary = document.createElement("salary");
                salary.appendChild(document.createTextNode(Double.toString(employee.getSalary())));
                employeeXML.appendChild(salary);

                // deptCode elements
                Element deptCode = document.createElement("deptCode");
                deptCode.appendChild(document.createTextNode(employee.getdeptCode()));
                employeeXML.appendChild(deptCode);

                // create the xml file
                // transform the DOM Object to an XML File
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(new File(filename));

                transformer.transform(domSource, streamResult);

            }
        } catch (ParserConfigurationException e) {
            return false;
        } catch (TransformerException tfe) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean loadFromXML(String filename) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filename);

            NodeList nodeList = document.getDocumentElement().getChildNodes();

            this.deptCode = document.getDocumentElement().getAttributes().item(0).getNodeValue();
            this.deptName = nodeList.item(1).getChildNodes().item(0).getNodeValue();
            this.address = nodeList.item(3).getChildNodes().item(0).getNodeValue();
            double xCoordinate = Double.parseDouble(nodeList.item(5).getChildNodes().item(0).getNodeValue());
            double yCoordinate = Double.parseDouble(nodeList.item(7).getChildNodes().item(0).getNodeValue());
            coordinates = new Coordinates(xCoordinate,yCoordinate);

            for (int i = 8; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;

                    // Get the value of the ID attribute.
                    String id = node.getAttributes().getNamedItem("id").getNodeValue();

                    // Get the value of all sub-elements.
                    String name = elem.getElementsByTagName("name")
                            .item(0).getChildNodes().item(0).getNodeValue();
                    int phone =  Integer.parseInt(elem.getElementsByTagName("phone").item(0)
                            .getChildNodes().item(0).getNodeValue());
                    double salary = Double.parseDouble(elem.getElementsByTagName("salary")
                            .item(0).getChildNodes().item(0).getNodeValue());
                    String deptCode = elem.getElementsByTagName("deptCode")
                            .item(0).getChildNodes().item(0).getNodeValue();

                    employeeList.add(new Employee(id, name, phone, salary, deptCode));
                }
            }
        } catch (ParserConfigurationException e) {
            return false;
        } catch (SAXException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String str;

        str = deptName + ", " + deptCode + ", " + address + ", " + coordinates + "\n\n";

        for(Employee employee : employeeList) {
            str = str + employee.toString() + '\n';
        }
        return str;
    }
}
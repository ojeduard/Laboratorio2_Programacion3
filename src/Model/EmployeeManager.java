package Model;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeManager {
    protected ArrayList<Employee> employeeList;

    public EmployeeManager() {
        employeeList = new ArrayList<>();
    }

    public Boolean validateID(String id) {
        for(Employee employee : employeeList) {
            if(employee.getId().equals(id))
                return false;
        }
        return true;
    }

    public Boolean add(Employee employee) {
        if(this.validateID(employee.getId())) {
            employeeList.add(employee);
            return true;
        }
        return false;
    }

    public Boolean delete(String id) {
        for (Employee employee : employeeList) {
            if (validateID(id)) {
                if (employee.getId().equals(id)) {
                    employeeList.remove(employee);
                    return true;
                }
            }
        }
        return false;
    }

    public Employee getEmployeeByID(String id) {
        for(Employee employee : employeeList) {
            if(employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    public Boolean createXML(String filename) {
        try {
            // To create this one is necessary to import DocumentBuilder from javax.xml
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // creation of root element
            Element root = document.createElement("employeesDB");
            document.appendChild(root);

            //writting elements
            for(Employee employee : employeeList) {

                Element employeeXML = document.createElement("employee");
                root.appendChild(employeeXML);

                Attr attr = document.createAttribute("id");
                attr.setValue(employee.getId());
                employeeXML.setAttributeNode(attr);

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

    public Boolean loadFromXML(String filename) {

        try {
            // To create this one is necessary to import DocumentBuilder from javax.xml
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(filename);

            NodeList nodeList = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {

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

    public String[][] getAsStringMatrix() {
        String[][] mat = new String[employeeList.size()][employeeList.size()];
        for(int i = 0; i < employeeList.size(); i++) {
            for(int j = 0; j < employeeList.size(); j++) {
                String[] empArr = employeeList.get(i).getAsStringArray();
                mat[i][j] = empArr[j];
            }
        }
        return mat;
    }

    public String[] getEmpAsStringArray(String ID) {
        if(validateID(ID)) {
            Employee emp = getEmployeeByID(ID);
            return emp.getAsStringArray();
        }
        return null;
    }

    @Override
    public String toString() {
        String str = "";
        for(Employee employee : employeeList) {
            str = str + employee.toString() + '\n';
        }
        return str;
    }
}



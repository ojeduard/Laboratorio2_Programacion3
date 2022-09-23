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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DepartmentManager {
    protected ArrayList<Department> departmentList;

    public DepartmentManager() {departmentList = new ArrayList<>();}

    public DepartmentManager(ArrayList<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public Boolean validateCode(String code) {
        for(Department department : departmentList) {
            if(department.getDeptCode().equals(code))
                return false;
        }
        return true;
    }

    public Boolean add(Department department) {
        if(this.validateCode(department.getDeptCode())) {
            departmentList.add(department);
            return true;
        }
        return false;
    }

    public Boolean delete(String code) {
        for (Department department : departmentList) {
            if (!validateCode(code)) {
                if (department.getDeptCode().equals(code)) {
                    departmentList.remove(department);
                    return true;
                }
            }
        }
        return false;
    }

    public Department getDepartmentbyCode(String code) {
        for(Department department : departmentList) {
            if(department.getDeptCode().equals(code)) {
                return department;
            }
        }
        return null;
    }

    public Boolean createXML(String filename) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Files.deleteIfExists(Paths.get(filename));

            // creation of root element
            Element root = document.createElement("DEPARTMENT");
            document.appendChild(root);

            for(Department department : departmentList) {

                Element departmentXML = document.createElement("department");
                root.appendChild(departmentXML);

                Attr attr = document.createAttribute("code");
                attr.setValue(department.getDeptCode());
                departmentXML.setAttributeNode(attr);

                // name element
                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(department.getDeptName()));
                departmentXML.appendChild(name);

                // address element
                Element address = document.createElement("address");
                address.appendChild(document.createTextNode(department.getAddress()));
                departmentXML.appendChild(address);

                // coordinates element
                Element latitude = document.createElement("latitude");
                latitude.appendChild(document.createTextNode(Double.toString(department.getCoordinates().getX())));
                departmentXML.appendChild(latitude);

                Element longitude = document.createElement("longitude");
                longitude.appendChild(document.createTextNode(Double.toString(department.getCoordinates().getY())));
                departmentXML.appendChild(longitude);


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
        } catch (IOException e) {
            throw new RuntimeException(e);
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
                    String code = node.getAttributes().getNamedItem("code").getNodeValue();

                    // Get the value of all sub-elements.
                    String name = elem.getElementsByTagName("name")
                            .item(0).getChildNodes().item(0).getNodeValue();
                    String address =  elem.getElementsByTagName("address").item(0)
                            .getChildNodes().item(0).getNodeValue();
                    double latitude = Double.parseDouble(elem.getElementsByTagName("latitude")
                            .item(0).getChildNodes().item(0).getNodeValue());
                    double longitude = Double.parseDouble(elem.getElementsByTagName("longitude")
                            .item(0).getChildNodes().item(0).getNodeValue());

                    departmentList.add(new Department(name, code, address,new Coordinates(latitude, longitude)));
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
        String[][] mat = new String[departmentList.size()][departmentList.size()];
        for(int i = 0; i < departmentList.size(); i++) {
            for(int j = 0; j < departmentList.size(); j++) {
                String[] empArr = departmentList.get(i).getAsStringArray();
                mat[i][j] = empArr[j];
            }
        }
        return mat;
    }

    public String[] getDepAsStringArray(String code) {
        if(!validateCode(code)) {
            Department dep = getDepartmentbyCode(code);
            return dep.getAsStringArray();
        }
        return null;
    }

    @Override
    public String toString() {
        String str = "";
        for(Department department : departmentList) {
            str = str + department.toString() + '\n';
        }
        return str;
    }
}

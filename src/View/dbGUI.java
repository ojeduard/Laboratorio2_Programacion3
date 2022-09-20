package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class dbGUI extends JFrame{
    private JTabbedPane tabPane;
    private JPanel employeeTab;
    private JPanel departmentTab;
    private JPanel aboutTab;
    private JPanel main;
    private JTextField nameField;
    private JButton searchButton;
    private JButton deleteEmployeeButton;
    private JButton reportButton;
    private JButton addEmployeeButton;
    private JTable table1;

    private  Controller controller = new Controller();

    public dbGUI(){
        setContentPane(this.main);
        setSize(600, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


//    private void createTable(){
//        Object [] [] data = {
//                {},
//                {},
//                {}};
//        table1.setModel(new DefaultTableModel(
//                data,
//                new String [] {"ID", "Name", "Phone", "Salary", "Department"}
//        ));
//    }

}

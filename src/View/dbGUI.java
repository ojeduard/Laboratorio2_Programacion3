package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

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

        String[] columnNames = {"ID", "Name", "Phone", "Salary", "Department"};
        table1.setModel(new DefaultTableModel(new String[][]{{},{}},columnNames));

        setContentPane(this.main);
        setSize(600, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

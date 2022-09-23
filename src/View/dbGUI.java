package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton addDepartmentBtn;
    private JButton deleteDep;
    private JButton searchDep;
    private JButton reportDep;
    private JTextField textFleldDep;

    private JTable tableDep;
    private JButton editDep;
    private JButton editEmp;

    private  Controller controller = new Controller();
    EmployeeView employeeView = new EmployeeView();
    DepartmentView departmentView = new DepartmentView();
    EmployeeView2 employeeView2 = new EmployeeView2();
    DepartmentView2 departmentView2 = new DepartmentView2();

    public dbGUI(){

        setContentPane(this.main);
        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] columnNames = {"ID", "Name", "Phone", "Salary", "Dept. Code"};
                String[][] data = new String[][]{controller.getEmpAsStringArray(nameField.getText())};
                table1.setModel(new DefaultTableModel(data,columnNames));
                table1.setRowSelectionAllowed(true);
            }
        });

        searchDep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] columnNames = {"Code", "Name", "Address", "Latitude", "Longitude"};
                String[][] data = new String[][]{controller.getDepAsStringArray(textFleldDep.getText())};
                tableDep.setModel(new DefaultTableModel(data,columnNames));
                tableDep.setRowSelectionAllowed(true);
            }
        });

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeView.setVisible(true);
            }
        });

        deleteEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexR = table1.getSelectedRow();
                int indexC = table1.getSelectedColumn();

                if(indexR == -1 || indexC == -1) {
                    JOptionPane.showMessageDialog(main, "ERROR: No selected employee");
                }
                else {
                    String value = table1.getModel().getValueAt(table1.getSelectedRow(), table1.getSelectedColumn()).toString();
                    controller.deletingEmployee(value);
                    ((DefaultTableModel) table1.getModel()).setRowCount(0);
                    JOptionPane.showMessageDialog(main, "Employee deleted succesfully");
                }
            }
        });

        addDepartmentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                departmentView.setVisible(true);
            }
        });

        deleteDep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexR = tableDep.getSelectedRow();
                int indexC = tableDep.getSelectedColumn();

                if(indexR == -1 || indexC == -1) {
                    JOptionPane.showMessageDialog(main, "ERROR: No selected department");
                }
                else {
                    String value = tableDep.getModel().getValueAt(tableDep.getSelectedRow(), tableDep.getSelectedColumn()).toString();
                    controller.deletingDep(value);
                    ((DefaultTableModel) tableDep.getModel()).setRowCount(0);
                    JOptionPane.showMessageDialog(main, "Department deleted succesfully");
                }
            }
        });


        editEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int indexR = table1.getSelectedRow();
                int indexC = table1.getSelectedColumn();

                if(indexR == -1 || indexC == -1) {
                    JOptionPane.showMessageDialog(main, "ERROR: No selected employee");
                }
                else {
                    String value = table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString();
                    String[] empArray = controller.getEmpAsStringArray(value);
                    employeeView2.jLabelEmp.setText(empArray[0]);
                    employeeView2.textName.setText(empArray[1]);
                    employeeView2.textPhone.setText(empArray[2]);
                    employeeView2.textSalary.setText(empArray[3]);
                    employeeView2.textCodeDep.setText(empArray[4]);
                    employeeView2.setVisible(true);
                }
            }
        });
        editDep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexR = tableDep.getSelectedRow();
                int indexC = tableDep.getSelectedColumn();

                if(indexR == -1 || indexC == -1) {
                    JOptionPane.showMessageDialog(main, "ERROR: No selected department");
                }
                else {
                    String value = tableDep.getModel().getValueAt(tableDep.getSelectedRow(), 0).toString();
                    String[] depArray = controller.getDepAsStringArray(value);
                    departmentView2.jlabelDep.setText(depArray[0]);
                    departmentView2.textName.setText(depArray[1]);
                    departmentView2.textAddress.setText(depArray[2]);
                    departmentView2.textLatitude.setText(depArray[3]);
                    departmentView2.textLongitude.setText(depArray[4]);
                    departmentView2.setVisible(true);
                }

            }
        });
    }
}

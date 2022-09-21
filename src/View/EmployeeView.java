package View;

import Controller.Controller;
import Model.EmployeeManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeView extends JFrame{
    private JPanel addEmployeeView;
    private JTextField textID;
    private JTextField textName;
    private JTextField textPhone;
    private JTextField textSalary;
    private JTextField textCodeDep;
    private JButton saveButton;
    private JButton cancelButton;

    private Controller controller = new Controller();

    EmployeeView(){
        setContentPane(this.addEmployeeView);
        setSize(600, 400);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addingEmployees(textID.getText(), textName.getText(), textPhone.getText(),textSalary.getText(), textCodeDep.getText());
                JOptionPane.showMessageDialog(saveButton, "Successfully Added!");
                dispose();
                textID.setText(null);
                textName.setText(null);
                textPhone.setText(null);
                textSalary.setText(null);
                textCodeDep.setText(null);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

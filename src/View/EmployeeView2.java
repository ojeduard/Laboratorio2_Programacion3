package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeView2 extends JFrame{
    private JPanel addEmployeeView;
    public JTextField textID;
    public JTextField textName;
    public JTextField textPhone;
    public JTextField textSalary;
    public JTextField textCodeDep;
    private JButton saveButton;
    private JButton cancelButton;
    public JLabel jLabelEmp;

    private Controller controller = new Controller();

    EmployeeView2(){
        setContentPane(this.addEmployeeView);
        setSize(600, 400);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.modifyEmp(jLabelEmp.getText(), textName.getText(), textPhone.getText(),textSalary.getText(), textCodeDep.getText());
                JOptionPane.showMessageDialog(saveButton, "Successfully Edited");
                dispose();

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

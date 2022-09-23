package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepartmentView2 extends JFrame{
    public JTextField textName;
    public JTextField textAddress;
    public JTextField textLatitude;
    public JTextField textLongitude;
    private JButton cancelButton;
    private JButton saveButton;
    private JPanel addDepartmentView;
    public JLabel jlabelDep;
    private Controller controller = new Controller();

    DepartmentView2(){
        setContentPane(this.addDepartmentView);
        setSize(600, 400);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controller.modifyDept(jlabelDep.getText(), textName.getText(), textAddress.getText(),textLatitude.getText(), textLongitude.getText());
                JOptionPane.showMessageDialog(saveButton, "Successfully Edited!");

                dispose();

                textName.setText(null);
                textAddress.setText(null);
                textLatitude.setText(null);
                textLongitude.setText(null);
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

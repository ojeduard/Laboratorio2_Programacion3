package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepartmentView extends JFrame{
    private JTextField textCode;
    private JTextField textName;
    private JTextField textAddress;
    private JTextField textLatitude;
    private JTextField textLongitude;
    private JButton cancelButton;
    private JButton saveButton;
    private JPanel addDepartmentView;
    private Controller controller = new Controller();

    DepartmentView(){
        setContentPane(this.addDepartmentView);
        setSize(600, 400);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(controller.addingDeparment(textCode.getText(), textName.getText(), textAddress.getText(),textLatitude.getText(), textLongitude.getText())){
                    JOptionPane.showMessageDialog(saveButton, "Successfully Added!");
                }else {
                    JOptionPane.showMessageDialog(saveButton, "ERROR", "Adding Department", JOptionPane.WARNING_MESSAGE);
                }

                dispose();
                textCode.setText(null);
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

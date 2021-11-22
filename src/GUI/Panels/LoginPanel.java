package GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private  JLabel userNameLabel;
    private  JLabel passwordLabel;
    private  JTextField userNameField;
    private  JTextField passwordField;

    public LoginPanel(){

        setLayout(new GridLayout(2,2));
        userNameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        userNameField = new JTextField(20);
        passwordField = new JTextField(20);

        /*
        Testing purposes only
         */
        {
            userNameField.setText("admin");
            passwordField.setText("admin");
        }
        add(userNameLabel);
        add(userNameField);
        add(passwordLabel);
        add(passwordField);
    }

    public void setDefault(){
        userNameField.setText("");
        passwordField.setText("");
    }

    public JTextField getUserNameField() {
        return userNameField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }
}

package GUI.Start;

import javax.swing.*;
import java.awt.*;

/**
 * <h3>Belépőfelület, a {@link StartFrame}, kezdőablak komponense</h3>
 */
public class LoginPanel extends JPanel {
    /**
     * Felhasználónévhez tartozó címke
     */
    private  JLabel userNameLabel;

    /**
     * Jelszóhoz tartozó címke
     */
    private  JLabel passwordLabel;

    /**
     * Felhasználónévhez tartozó szövegdoboz
     */
    private  JTextField userNameField;

    /**
     * Jelszóhoz tartozó szövegdoboz
     */
    private  JPasswordField passwordField;

    /**
     * <h3>Konstruktor</h3>
     * Összerakja a komponensekből a panelt.
     */
    public LoginPanel(){
        setLayout(new GridLayout(2,2));
        userNameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        userNameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        add(userNameLabel);
        add(userNameField);
        add(passwordLabel);
        add(passwordField);
    }

    /**
     * Kiüríti a szövedobozokat
     */
    public void setDefault(){
        userNameField.setText("");
        passwordField.setText("");
    }

    /**
     * Felhasználónévhez tartozó mező gettere
     * @return felhasználónév mező
     */
    public JTextField getUserNameField() {
        return userNameField;
    }
    /**
     * Jelszómező gettere
     * @return jelszómező
     */
    public JTextField getPasswordField() {
        return passwordField;
    }
}

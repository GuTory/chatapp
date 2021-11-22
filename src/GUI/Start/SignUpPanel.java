package GUI.Start;

import javax.swing.*;

public class SignUpPanel extends JPanel {
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel ageLabel;
    private JTextField ageField;

    public SignUpPanel(){
        nameLabel = new JLabel("Full name:");
        ageLabel = new JLabel("Your age:");
        nameField = new JTextField(20);
        ageField = new JTextField(20);
        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
    }

    public void setDefault(){
        nameField.setText("");
        ageField.setText("");
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getAgeField() {
        return ageField;
    }
}

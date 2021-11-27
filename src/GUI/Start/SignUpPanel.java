package GUI.Start;

import javax.swing.*;

/**
 * <h3>Regisztrációs felület, a {@link StartFrame}, kezdőablak komponense</h3>
 */
public class SignUpPanel extends JPanel {
    /**
     * Rendes névhez tartozó címke, ami a névmező előtt jelenik meg
     */
    private JLabel nameLabel;

    /**
     * Ide kell bevinni regisztrációkor a nevünket
     */
    private JTextField nameField;

    /**
     * Korhoz tartozó címke, ami a kormező előtt jelenik meg.
     */
    private JLabel ageLabel;

    /**
     * Ide kell beírni az életkorunkat.
     */
    private JTextField ageField;

    /**
     * <h3>Konstruktor</h3>
     * Összerakja a komponensekből a panelt.
     */
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

    /**
     * Kiüríti a szövegdobozokat
     */
    public void setDefault(){
        nameField.setText("");
        ageField.setText("");
    }

    /**
     * Névmező gettere
     * @return névmező
     */
    public JTextField getNameField() {
        return nameField;
    }

    /**
     * Kormező gettere
     * @return Kormező
     */
    public JTextField getAgeField() {
        return ageField;
    }
}

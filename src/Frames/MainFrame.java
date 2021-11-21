package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class MainFrame extends JFrame {
    JButton loginButton;
    JButton signUpButton;
    JLabel userNameLabel;
    JLabel passwordLabel;
    JTextField userNameField;
    JTextField passwordField;

    public MainFrame(){
        super("Chat App");
        try {
            ImageIcon icon = new ImageIcon("red.png");
            super.setIconImage(icon.getImage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(250, 100));
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign up");
        userNameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password:");
        userNameField = new JTextField(20);
        passwordField = new JTextField(20);
        setLayout(new GridLayout(3,2));
        add(userNameLabel);
        add(userNameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(signUpButton);

        LoginListener loginListener = new LoginListener();
        SignUpListener signUpListener = new SignUpListener();
        loginButton.addActionListener(loginListener);
        signUpButton.addActionListener(signUpListener);
    }

    private class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("login");
        }
    }

    private class SignUpListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("signup");
        }
    }



    public static void main(String[] args) {
        JFrame myframe = new MainFrame();
        myframe.setVisible(true);
    }
}

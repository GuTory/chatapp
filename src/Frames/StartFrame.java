package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Listeners.LoginListener;
import Listeners.SignUpListener;

public class StartFrame extends JFrame implements Runnable {
    JButton loginButton;
    JButton signUpButton;
    JLabel userNameLabel;
    JLabel passwordLabel;
    JTextField userNameField;
    JTextField passwordField;

    public StartFrame(){
        super("Chat App");
        try {
            ImageIcon icon = new ImageIcon("chat.png");
            super.setIconImage(icon.getImage());
        } catch (Exception e){
            e.printStackTrace();
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(250, 100));
        setResizable(false);
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

    @Override
    public void run() {
        JFrame myframe = new StartFrame();
        myframe.setVisible(true);
    }
}

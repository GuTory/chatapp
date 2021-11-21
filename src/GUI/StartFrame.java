package GUI;

import Data.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class StartFrame extends JFrame implements Runnable {
    private static LinkedList<ChatFrame> chatFrames;
    private static LinkedList<User> users;

    private  Dimension dim;
    private  JButton loginButton;
    private  JButton signUpButton;
    private  JLabel userNameLabel;
    private  JLabel passwordLabel;
    private  JTextField userNameField;
    private  JTextField passwordField;
    private  JLabel messageLabel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel ageLabel;
    private JTextField ageField;

    private  JPanel mainpanel;
    private JPanel additionalPanel;

    public StartFrame(){
        super("Chat App");
        try {
            ImageIcon icon = new ImageIcon("chat.png");
            super.setIconImage(icon.getImage());
        } catch (Exception e){
            e.printStackTrace();
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        dim = new Dimension(480, 210);
        setMinimumSize(dim);
        //setResizable(false);
        userNameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        userNameField = new JTextField(20);
        passwordField = new JTextField(20);
        signUpButton = new JButton("Sign up");
        loginButton = new JButton("Login");
        nameLabel = new JLabel("Full name:");
        ageLabel = new JLabel("Your age:");
        nameField = new JTextField(20);
        ageField = new JTextField(20);
        mainpanel = new JPanel();
        additionalPanel = new JPanel();
        messageLabel = new JLabel("messages show up here");

        mainpanel.setLayout(new GridLayout(3,2));
        mainpanel.add(userNameLabel);
        mainpanel.add(userNameField);
        mainpanel.add(passwordLabel);
        mainpanel.add(passwordField);
        mainpanel.add(loginButton);
        mainpanel.add(signUpButton);
        mainpanel.add(messageLabel);

        additionalPanel.setLayout(new GridLayout(2,2));
        additionalPanel.add(nameLabel);
        additionalPanel.add(nameField);
        additionalPanel.add(ageLabel);
        additionalPanel.add(ageField);

        setLayout(new FlowLayout());

        add(mainpanel);
        add(loginButton);
        add(signUpButton);
        add(additionalPanel);
        add(messageLabel);

        LoginListener loginListener = new LoginListener();
        SignUpListener signUpListener = new SignUpListener();
        loginButton.addActionListener(loginListener);
        signUpButton.addActionListener(signUpListener);
    }

    private class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = userNameField.getText();
            String password = passwordField.getText();
            for(User u: users){
                if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                    if(!LoggedIn(username)){
                        messageLabel.setText("");
                        openChatFrame(u);
                    } else {
                        messageLabel.setText("You are already logged in!");
                    }
                    break;
                } else {
                    messageLabel.setText("Invalid username or password!");
                }
            }
        }

        private boolean LoggedIn(String username){
            for(ChatFrame u : chatFrames){
                if(u.getUser().getUsername().equals(username)){
                    return true;
                }
            }
            return false;
        }
    }

    private class SignUpListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("signup");
        }
    }

    void initfordebug(){
        User admin = new User("admin","admin", "Kritya", 20);
        User user1 = new User("user1", "1234", "Zalán", 22);
        users.add(admin);
        users.add(user1);
    }

    void openChatFrame(User u){
        ChatFrame chatFrame = new ChatFrame(u);
        chatFrames.add(chatFrame);
        Thread newthread = new Thread(chatFrame);
        newthread.start();
    }

    @Override
    public void run() {
        JFrame myframe = new StartFrame();
        myframe.setVisible(true);
        chatFrames = new LinkedList<>();
        users = new LinkedList<>();
        initfordebug();
    }
}

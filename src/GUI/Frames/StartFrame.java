package GUI.Frames;

import Data.User;
import GUI.Panels.LoginPanel;
import GUI.Panels.SignUpPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.LinkedList;

public class StartFrame extends JFrame implements Runnable {
    private static LinkedList<ChatFrame> chatFrames;
    private static LinkedList<User> users;

    private  Dimension dim;

    private  JButton loginButton;
    private  JButton signUpButton;

    private LoginPanel loginPanel;
    private SignUpPanel signUpPanel;
    private  JLabel messageLabel;

    public StartFrame(){
        super("Texting purposes only...");
        try {
            ImageIcon icon = new ImageIcon("chat.png");
            super.setIconImage(icon.getImage());

            chatFrames = new LinkedList<>();
            users = new LinkedList<>();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"));
            users = (LinkedList<User>) ois.readObject();
        } catch (Exception e){
            e.printStackTrace();
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        dim = new Dimension(480, 200);
        setMinimumSize(dim);
        setResizable(false);


        signUpButton = new JButton("Sign up");
        loginButton = new JButton("Login");

        loginPanel = new LoginPanel();
        signUpPanel = new SignUpPanel();
        messageLabel = new JLabel("Hi, there! (" + users.size() + " users registered)");

        loginPanel.setLayout(new GridLayout(2,2));
        signUpPanel.setLayout(new GridLayout(2,2));
        setLayout(new FlowLayout());

        add(loginPanel);
        add(loginButton);
        add(signUpButton);
        add(signUpPanel);
        add(messageLabel);

        loginButton.addActionListener(new LoginListener());
        signUpButton.addActionListener( new SignUpListener());
        this.addWindowListener(new CloseWindowAdapter());
    }

    private class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginPanel.getUserNameField().getText();
            String password = loginPanel.getPasswordField().getText();
            for(User u: users){
                if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                    if(!LoggedIn(username)){
                        messageLabel.setText("Welcome, " + u.getName() + "!");
                        openChatFrame(u);
                        loginPanel.setDefault();
                        signUpPanel.setDefault();
                    } else {
                        messageLabel.setText("You are already logged in!");
                    }
                    break;
                } else {
                    messageLabel.setText("Invalid username or password!");
                }
                /*

                HARD DELETE

                u.getFriends().clear();
                */
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
            try {
                String username = loginPanel.getUserNameField().getText();
                String password = loginPanel.getPasswordField().getText();
                String name = signUpPanel.getNameField().getText();
                int age = Integer.parseInt(signUpPanel.getAgeField().getText());
                for(User u: users){
                    if(u.getUsername().equals(username)){
                        messageLabel.setText("This username is already taken!");
                        return;
                    }
                }
                User newUser = new User(username, password, name, age);

                /*
                    FOR TESTING PURPOSES ONLY
                */
                for(User u: users){
                    if(u.getUsername().equals("admin")){
                        newUser.addFriend(u);
                        System.out.println("friend added?");
                    }
                }

                users.add(newUser);
                messageLabel.setText("Welcome, " + name + "!");
                openChatFrame(newUser);
                loginPanel.setDefault();
                signUpPanel.setDefault();
            } catch (Exception ee){
                messageLabel.setText("Please fill all textfields and enter valid information!");
            }

        }
    }

    private class CloseWindowAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"));
                oos.writeObject(users);
                oos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class ChatWindowAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            chatFrames.remove((ChatFrame) e.getSource());
        }
    }

    void openChatFrame(User u){
        ChatFrame chatFrame = new ChatFrame(u);
        chatFrame.setStartFrame(this);
        ChatWindowAdapter cwa = new ChatWindowAdapter();
        chatFrame.addWindowListener(cwa);
        chatFrames.add(chatFrame);
        Thread newthread = new Thread(chatFrame);
        newthread.start();
    }

    public static LinkedList<User> getUsers() {
        return users;
    }

    public static LinkedList<ChatFrame> getChatFrames() {
        return chatFrames;
    }

    @Override
    public void run() {
        JFrame myframe = new StartFrame();
        myframe.setVisible(true);
    }
}

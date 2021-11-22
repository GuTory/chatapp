package GUI.Start;

import Data.User;
import GUI.Chat.ChatFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.LinkedList;

public class StartFrame extends JFrame implements Runnable {
    private static LinkedList<ChatFrame> chatFrames;
    private static LinkedList<User> users;

    private  Dimension dim;

    private ButtonPanel buttonPanel;


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



        buttonPanel = new ButtonPanel(this);
        loginPanel = new LoginPanel();
        signUpPanel = new SignUpPanel();
        messageLabel = new JLabel("Hi, there! (" + users.size() + " users registered)");

        loginPanel.setLayout(new GridLayout(2,2));
        signUpPanel.setLayout(new GridLayout(2,2));
        setLayout(new FlowLayout());

        add(loginPanel);
        add(signUpPanel);
        add(buttonPanel);
        add(messageLabel);

        this.addWindowListener(new CloseWindowAdapter());
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

    public void openChatFrame(User u){
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

    public JLabel getMessageLabel() {
        return messageLabel;
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public SignUpPanel getSignUpPanel() {
        return signUpPanel;
    }

    @Override
    public void run() {
        JFrame myframe = new StartFrame();
        myframe.setVisible(true);
    }
}

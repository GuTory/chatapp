package GUI.Chat;

import Data.User;
import GUI.Start.StartFrame;

import javax.swing.*;
import java.awt.*;

public class ChatFrame extends JFrame implements Runnable {
    private StartFrame startFrame;
    private User user;

    private MyMenuBar menuBar;
    private JPanel southPanel;
    private FriendListPanel friendPanel;
    private JTextField messageField;
    private JButton sendButton;

    public ChatFrame(User u){
        super(u.getName() + "'s chats");
        user = u;
        setLayout(new BorderLayout());
        try {
            ImageIcon icon = new ImageIcon("chat.png");
            super.setIconImage(icon.getImage());
        } catch (Exception e){
            e.printStackTrace();
        }

        southPanel = new JPanel();
        messageField = new JTextField(40);
        sendButton = new JButton("Send");
        southPanel.add(messageField,BorderLayout.SOUTH);
        southPanel.add(sendButton, BorderLayout.SOUTH);

        menuBar = new MyMenuBar(this);
        setJMenuBar(menuBar);

        friendPanel = new FriendListPanel(user);

        /*
            Texting purposes only
         */
        add(new JLabel("Im not lost"), BorderLayout.CENTER);

        add(friendPanel, BorderLayout.WEST);
        add(southPanel, BorderLayout.SOUTH);
        setMinimumSize(new Dimension(800, 540));
        setVisible(true);
    }

    public User getUser() {
        return user;
    }

    @Override
    public void run() {

    }

    public void setStartFrame(StartFrame startFrame) {
        this.startFrame = startFrame;
    }

    public FriendListPanel getFriendPanel() {
        return friendPanel;
    }
}

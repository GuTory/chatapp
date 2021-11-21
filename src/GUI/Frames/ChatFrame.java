package GUI.Frames;

import javax.swing.*;
import Data.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ChatFrame extends JFrame implements Runnable {
    private StartFrame startFrame;
    private User user;

    JMenuBar menuBar;
    private JLabel nameLabel;
    private JPanel southPanel;
    private JTextField messageField;
    private JButton sendButton;

    public ChatFrame(User u){
        super(u.getName() + "'s chats");
        user = u;
        try {
            ImageIcon icon = new ImageIcon("chat.png");
            super.setIconImage(icon.getImage());
        } catch (Exception e){
            e.printStackTrace();
        }
        setLayout(new BorderLayout());
        nameLabel = new JLabel(user.getName());
        messageField = new JTextField(20);
        sendButton = new JButton("Send");
        southPanel = new JPanel();

        menuBar = new JMenuBar();
        JMenuItem addFriends = new JMenuItem("Add Friends");
        addFriends.addActionListener(new AddFriendListener());
        JMenuItem deleteAccount = new JMenuItem("Delete Account");
        deleteAccount.addActionListener(new DeleteAccountListener());
        menuBar.add(addFriends);
        menuBar.add(deleteAccount);
        setJMenuBar(menuBar);

        add(nameLabel, BorderLayout.NORTH);
        southPanel.add(messageField,BorderLayout.SOUTH);
        southPanel.add(sendButton, BorderLayout.SOUTH);
        add(southPanel);
        setMinimumSize(new Dimension(800, 540));
        setVisible(true);
    }

    private class AddFriendListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("add friends");
        }
    }

    private class DeleteAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            startFrame.getUsers().remove(user);
            dispose();

        }
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
}

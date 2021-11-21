package GUI.Frames;

import javax.swing.*;
import Data.*;
import GUI.Panels.FriendPanel;

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
    private JPanel friendPanel;
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

        nameLabel = new JLabel(user.getName() + " " + user.getFriends().size());
        messageField = new JTextField(20);
        sendButton = new JButton("Send");
        southPanel = new JPanel();
        menuBar = new JMenuBar();
        JMenuItem addFriends = new JMenuItem("Add Friends");
        JMenuItem deleteAccount = new JMenuItem("Delete Account");

        addFriends.addActionListener(new AddFriendListener());
        deleteAccount.addActionListener(new DeleteAccountListener());
        menuBar.add(addFriends);
        menuBar.add(deleteAccount);
        setJMenuBar(menuBar);

        add(nameLabel, BorderLayout.NORTH);
        southPanel.add(messageField,BorderLayout.SOUTH);
        southPanel.add(sendButton, BorderLayout.SOUTH);
        /*
        friendPanel = new JPanel();
        //friendPanel.add(new FriendPanel(new User("a", "b","PITU", 20)));

        friendPanel.setLayout(new GridLayout(user.getFriends().size(),1));



        for(User user : user.getFriends().keySet()){
            FriendPanel fp = new FriendPanel(user);
            friendPanel.add(fp);
        }


        add(friendPanel, BorderLayout.WEST);
        friendPanel.setVisible(true);

        */
        add(southPanel, BorderLayout.SOUTH);
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
            StartFrame.getUsers().remove(user);
            for (User u: user.getFriends().keySet()){
                u.removeFriend(user);
            }
            StartFrame.getChatFrames().remove(this);
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

package GUI.Frames;

import javax.swing.*;
import Data.*;
import GUI.Panels.FriendPanel;
import GUI.Panels.MyMenuBar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ChatFrame extends JFrame implements Runnable {
    private StartFrame startFrame;
    private User user;

    MyMenuBar menuBar;
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

        messageField = new JTextField(20);
        sendButton = new JButton("Send");
        southPanel = new JPanel();
        menuBar = new MyMenuBar(this);
        setJMenuBar(menuBar);

        southPanel.add(messageField,BorderLayout.SOUTH);
        southPanel.add(sendButton, BorderLayout.SOUTH);

        friendPanel = new JPanel();
        friendPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        if(user.getFriends().size()>0){
            friendPanel.setLayout(new BoxLayout(friendPanel, BoxLayout.Y_AXIS));
            for(User user : user.getFriends().keySet()){
                FriendPanel fp = new FriendPanel(user);
                friendPanel.add(fp);
            }
        } else {
            friendPanel.add(new JLabel("No friends to show :( Make some Friends!"));
        }

        Dimension minSize = new Dimension(5, 5);
        Dimension prefSize = new Dimension(5, Short.MAX_VALUE);
        Dimension maxSize = new Dimension(5, Short.MAX_VALUE);
        friendPanel.add(new Box.Filler(minSize, prefSize, maxSize));

        /*
            Texting purposes only
         */
        add(new JLabel("Im not lost"), BorderLayout.CENTER);

        add(friendPanel, BorderLayout.WEST);
        friendPanel.setVisible(true);


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
}

package GUI.Frames;

import javax.swing.*;
import Data.*;

import java.awt.*;

public class ChatFrame  extends JFrame implements Runnable {
    private User user;

    private JLabel nameLabel;

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
        add(nameLabel, BorderLayout.NORTH);
        setMinimumSize(new Dimension(800, 540));
    }

    public User getUser() {
        return user;
    }

    @Override
    public void run() {
        ChatFrame chatFrame = new ChatFrame(user);
        setVisible(true);
    }
}

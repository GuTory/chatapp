package GUI.Chat;

import Data.User;
import GUI.Start.StartFrame;

import javax.swing.*;
import java.awt.*;

public class ChatFrame extends JFrame implements Runnable {
    private StartFrame startFrame;
    private User user;

    private MyMenuBar menuBar;
    private ChatPanel chatPanel;

    public ChatFrame(User u){
        super(u.getName() + "'s chats");
        user = u;
        setMinimumSize(new Dimension(800, 540));
        try {
            ImageIcon icon = new ImageIcon("chat.png");
            super.setIconImage(icon.getImage());
        } catch (Exception e){
            e.printStackTrace();
        }
        menuBar = new MyMenuBar(this);
        setJMenuBar(menuBar);
        chatPanel = new ChatPanel(this, user);
        add(chatPanel);
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

    public ChatPanel getChatPanel() {
        return chatPanel;
    }
}

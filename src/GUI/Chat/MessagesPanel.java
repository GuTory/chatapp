package GUI.Chat;

import Data.Message;
import Data.User;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MessagesPanel extends JPanel {
    ChatFrame frame;
    User friend;

    JList<Message> messageJList;
    DefaultListModel<Message> listModel;

    public MessagesPanel(ChatFrame frame, User friend){
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentY(Component.BOTTOM_ALIGNMENT);
        this.frame = frame;
        this.friend = friend;
        messageJList = new JList<>();
        messageJList.setBackground(new Color(238,238,238));
        listModel = new DefaultListModel<>();
        messageJList.setModel(listModel);
        add(messageJList);
        loadMessages();
    }

    public void refresh(User newUser){
        friend = newUser;
        listModel.removeAllElements();
        loadMessages();
    }

    public void loadMessages(){
        LinkedList<Message> messages = frame.getUser().getFriends().get(friend);
        if(messages != null){
            listModel.addAll(messages);
            revalidate();
            repaint();
        }
    }
}

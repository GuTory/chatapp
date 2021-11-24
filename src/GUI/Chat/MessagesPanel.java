package GUI.Chat;

import Data.Message;
import Data.User;

import javax.swing.*;
import java.util.LinkedList;

public class MessagesPanel extends JPanel {
    ChatFrame frame;
    User friend;

    JList<Message> messageJList;
    DefaultListModel<Message> listModel;

    public MessagesPanel(ChatFrame frame, User friend){
        this.frame = frame;
        this.friend = friend;
        messageJList = new JList<>();
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
        LinkedList<Message> messages = frame.getUser().friends.get(friend);
        if(messages != null){
            listModel.addAll(messages);
            revalidate();
            repaint();
        }
    }
}

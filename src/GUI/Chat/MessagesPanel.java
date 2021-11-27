package GUI.Chat;

import Data.Message;
import Data.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

public class MessagesPanel extends JPanel implements ListSelectionListener {
    ChatFrame frame;
    User friend;

    private JList<Message> messageJList;
    private DefaultListModel<Message> listModel;

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
        messageJList.addListSelectionListener(this);
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            Message selected = messageJList.getSelectedValue();
            if(selected.getAttachment() != null){
                try {
                    Desktop dtp = Desktop.getDesktop();
                    dtp.open(selected.getAttachment());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public JList<Message> getMessageJList() {
        return messageJList;
    }

    public DefaultListModel<Message> getListModel() {
        return listModel;
    }
}

package GUI.Chat;

import Data.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class FriendList extends JPanel implements ListSelectionListener {
    private ChatPanel panel;

    private JList<User> list;
    private DefaultListModel<User> model;

    public FriendList(ChatPanel frame){
        this.panel = frame;
        list = new JList<>();
        model = new DefaultListModel<>();
        list.setModel(model);
        list.addListSelectionListener(this);
        model.addAll(panel.getFrame().getUser().getFriends().keySet());
        add(list);
    }

    public JList<User> getList() {
        return list;
    }

    public DefaultListModel<User> getModel() {
        return model;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            User selected = list.getSelectedValue();
            panel.getMessagesPanel().refresh(selected);
        }
    }


}

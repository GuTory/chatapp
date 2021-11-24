package GUI.Chat;

import Data.User;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {
    ChatFrame frame;
    User user;


    private JScrollPane westPane;
    private FriendList friendList;
    private JScrollPane centerPane;
    private MessagesPanel messagesPanel;
    private MessagingPanel messagingPanel;

    public ChatPanel(ChatFrame f, User u){
        frame = f;
        user = u;
        setLayout(new BorderLayout());

        messagingPanel = new MessagingPanel(this);

        friendList = new FriendList(this);

        westPane = new JScrollPane(friendList);
        /*
            Texting purposes only
         */
        User select = friendList.getList().getSelectedValue();
        messagesPanel = new MessagesPanel(frame , select);

        centerPane = new JScrollPane(messagesPanel);
        add(westPane, BorderLayout.WEST);
        add(centerPane, BorderLayout.CENTER);
    }

    public void refreshFriendList(){
        friendList.getModel().removeAllElements();
        friendList.getModel().addAll(user.getFriends().keySet());
        westPane.revalidate();
        westPane.repaint();
    }

    public MessagesPanel getMessagesPanel() {
        return messagesPanel;
    }

    public FriendList getFriendList() {
        return friendList;
    }
}

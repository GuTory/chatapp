package GUI.Chat;

import Data.User;

import javax.swing.*;
import java.awt.*;

/**
 * <h3>Csevegéseket megjelenítő panel</h3>
 * A {@link ChatFrame} nagyobbik részét alkotó panel, a menubaron kívül mindent
 */
public class ChatPanel extends JPanel {
    /**
     * Az ablak, amihez tartozik
     */
    ChatFrame frame;

    /**
     * A felhazs
     */


    private JScrollPane westPane;
    private FriendList friendList;
    private JScrollPane centerPane;
    private MessagesPanel messagesPanel;
    private MessagingPanel messagingPanel;

    public ChatPanel(ChatFrame f){
        frame = f;
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
        friendList.getModel().addAll(frame.getUser().getFriends().keySet());
        frame.pack();
        westPane.revalidate();
        westPane.repaint();
    }

    public ChatFrame getFrame() {
        return frame;
    }

    public MessagesPanel getMessagesPanel() {
        return messagesPanel;
    }

    public FriendList getFriendList() {
        return friendList;
    }
}

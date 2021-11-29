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
     * Nyugati panel, itt lesz a barátok listája
     */
    private JScrollPane westPane;

    /**
     * Barátokat tároló és megjelenítő komponens, bele lesz helyezve a westpane-be, hogy görgethető legyen
     */
    private FriendList friendList;

    /**
     * Központi komponens, itt lesznek az üzenetek, ebbe lesz belehelyezve az üzenetpanel, csak így görgethető is
     */
    private JScrollPane centerPane;

    /**
     * Üzenetpanel, ami tárolja és megjeleníti az üzeneteket.
     */
    private MessagesPanel messagesPanel;

    /**
     * Üzenőpanel, ami a felület alján lesz, ide lehet majd beírni az üzenetünket és elküldeni azt, vagy fájlt csatolni
     */
    private MessagingPanel messagingPanel;

    /**
     * <h3>Konstruktor</h3>
     * Inicializálja az elemeit, összerakja magát
     * @param f frame, amihez tartozik
     */
    public ChatPanel(ChatFrame f){
        frame = f;
        setLayout(new BorderLayout());
        messagingPanel = new MessagingPanel(this);
        friendList = new FriendList(this);
        westPane = new JScrollPane(friendList);
        User select = friendList.getList().getSelectedValue();
        messagesPanel = new MessagesPanel(frame , select);

        centerPane = new JScrollPane(messagesPanel);
        add(westPane, BorderLayout.WEST);
        add(centerPane, BorderLayout.CENTER);
    }

    /**
     * Baráti lista frissítése
     */
    public void refreshFriendList(){
        friendList.getModel().removeAllElements();
        friendList.getModel().addAll(frame.getUser().getFriends().keySet());
        frame.pack();
        westPane.revalidate();
        westPane.repaint();
    }

    /**
     * frame gettere, teszteléshez kell (JUnit)
     * @return frame, amihez tartozik
     */
    public ChatFrame getFrame() {
        return frame;
    }

    /**
     * üzenetpanel gettere
     * @return üzenetpanel
     */
    public MessagesPanel getMessagesPanel() {
        return messagesPanel;
    }

    /**
     * Baráti lista gettere
     * @return baráti lista getter
     */
    public FriendList getFriendList() {
        return friendList;
    }

    /**
     * Üzenőpanel gettere, teszteléshez kell (JUnit)
     * @return
     */
    public  MessagingPanel getMessagingPanel(){
        return messagingPanel;
    }
}

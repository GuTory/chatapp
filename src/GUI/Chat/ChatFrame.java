package GUI.Chat;

import Data.User;
import GUI.Start.StartFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Csevegőablak, a felhasználó előtt ez jelenik meg bejelentkezés után
 */
public class ChatFrame extends JFrame implements Runnable {

    /**
     * A bejelentkezési felület, amihez tartozik a példány
     */
    private StartFrame startFrame;

    /**
     * Felhasználó, aki be van jelentkezve ide
     */
    private User user;

    /**
     * Menubar, a csevegőablak fejléce
     */
    private MyMenuBar menuBar;

    /**
     * Csevegőfelület, itt vannak az üzenetek, barátok és új üzeneteket is ezen a felületen lehet küldeni
     */
    private ChatPanel chatPanel;

    /**
     * <h3>Konstruktor</h3>
     * Inicializálja az elemeket, összerakja magát a panel komponensekből
     * @param u: felhasználó, aki bejelentkezett
     */
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
        chatPanel = new ChatPanel(this);
        add(chatPanel);
        setVisible(true);

        while (user.getFriendRequests().size()>0){
            user.getFriendRequests().actionPerformed(user.getFriendRequests().get());
            pack();
        }
        chatPanel.refreshFriendList();
    }

    /**
     * Bejelentkezett {@link User} gettere
     * @return bejelentkezett felhasználó
     */
    public User getUser() {
        return user;
    }


    /**
     * Szálként futás megvalósítása, konstruktorban van implementálva
     */
    @Override
    public void run() {

    }

    /**
     * Kezdőablak settere
     * @param startFrame: frame, amihez tartozik
     */
    public void setStartFrame(StartFrame startFrame) {
        this.startFrame = startFrame;
    }

    /**
     * Csevegőpanel gettere, panelfrissítéshez és teszteléshez kell
     * @return csevegőpanel
     */
    public ChatPanel getChatPanel() {
        return chatPanel;
    }
}

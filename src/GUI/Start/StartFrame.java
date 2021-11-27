package GUI.Start;

import Data.User;
import GUI.Chat.ChatFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.LinkedList;

/**
 * <H3>Nyitóablak</H3>
 * A program indításakor megjelenő bejelentkező/ regisztráló felület.
 * Ha bezárjuk, a program leáll.
 * Ha bejelentkezünk egy fiókba, akkor a {@link ChatFrame} nyílik meg, és ez az ablak is nyitva marad
 * Bejelentkezéshez elég a felhasználót és jelszót megadni, regisztrációhoz még a teljes név és életkor is kell, különben nem engedi a program a bejelentkezést.
 */

public class StartFrame extends JFrame implements Runnable {
    /**
     * Az éppen futó csevegőablakok, akik éppen be vannak jelentkezve.
     */
    private static LinkedList<ChatFrame> chatFrames;

    /**
     * Regisztrált felhasználók
     */
    private static LinkedList<User> users;

    /**
     * Ablak méretezéséhez használt dimenzió
     */
    private  Dimension dim;

    /**
     * Belépő és regisztrációs gombokat tartalmazó {@link JPanel}
     */
    private ButtonPanel buttonPanel;

    /**
     * Belépéshez használt szövegdobozokat és címkéket tartalmazó {@link JPanel}
     */
    private LoginPanel loginPanel;

    /**
     * Regisztrációhoz tartozó szövegdobozokat tartalmazó {@link JPanel}
     */
    private SignUpPanel signUpPanel;

    /**
     * Üzeneteket megjelenítő címke
     */
    private  JLabel messageLabel;

    /**
     * <h3>Alap konstruktor</h3>
     * A kiszerializált adatokat beolvassa, felépíti a fenti komponensekből a bejelentkező-felületet.
     */
    public StartFrame(){
        super("For texting purposes only...");
        try {
            ImageIcon icon = new ImageIcon("chat.png");
            super.setIconImage(icon.getImage());

            chatFrames = new LinkedList<>();
            users = new LinkedList<>();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"));
            users = (LinkedList<User>) ois.readObject();
        } catch (Exception e){
            e.printStackTrace();
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        dim = new Dimension(480, 200);
        setMinimumSize(dim);
        setResizable(false);

        buttonPanel = new ButtonPanel(this);
        loginPanel = new LoginPanel();
        signUpPanel = new SignUpPanel();
        messageLabel = new JLabel("Hi, there! (" + users.size() + " users registered)");

        loginPanel.setLayout(new GridLayout(2,2));
        signUpPanel.setLayout(new GridLayout(2,2));
        setLayout(new FlowLayout());

        add(loginPanel);
        add(signUpPanel);
        add(buttonPanel);
        add(messageLabel);

        this.addWindowListener(new CloseWindowAdapter());
    }

    /**
     * Be van-e jelentkezve a keresett felhasználó?
     * @param u keresett felhasználó
     * @return a megnyitott csevegőablak, amivel a felhasználó be van jelentkezve
     */
    public static ChatFrame loggedInFrame(User u){
        for(ChatFrame frame : chatFrames){
            if (frame.getUser().equals(u)){
                return frame;
            }
        }
        return null;
    }

    /**
     * Felhaszmálók listájának gettere
     * @return felhaszmálók listája
     */
    public static LinkedList<User> getUsers() {
        return users;
    }

    /**
     * Futó ablakok gettere
     * @return futó ablakok listája
     */
    public static LinkedList<ChatFrame> getChatFrames() {
        return chatFrames;
    }

    /**
     * <h3>Csevegőablak indítása</h3>
     * Bejelentkezés egy felhasználóval, {@link ChatFrame}, csevegőablak nyitása
     * @param u a bejelentkezett felhasználó
     */
    public void openChatFrame(User u){
        ChatFrame chatFrame = new ChatFrame(u);
        chatFrame.setStartFrame(this);
        ChatWindowAdapter cwa = new ChatWindowAdapter();
        chatFrame.addWindowListener(cwa);
        chatFrames.add(chatFrame);
        Thread newthread = new Thread(chatFrame);
        newthread.start();
    }

    /**
     * Üzenetcímke gettere
     * @return üzenetcímke
     */
    public JLabel getMessageLabel() {
        return messageLabel;
    }

    /**
     * Bejelentkezőpanel gettere
     * @return bejelentkezőpanel
     */
    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    /**
     * Regisztrálópanel gettere
     * @return regisztrálópanel
     */
    public SignUpPanel getSignUpPanel() {
        return signUpPanel;
    }

    /**
     * <h3>Többszálúság, {@link Runnable} interfész implementáció</h3>
     */
    @Override
    public void run() {
        JFrame myframe = new StartFrame();
        myframe.setVisible(true);
    }

    /**
     * <h3>Szerializálásért felelős WindowListener</h3>
     * Az ablak becsukásakor automatikusan kiírja a felhasználókkal és kapcsolataikkal kapcsolatos információkat a  <b>users.dat</b>  állományba.
     */
    private class CloseWindowAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"));
                oos.writeObject(users);
                oos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * <h3>Csevegőablak bezárása</h3>
     * Csevegőablak bezárásakor a futó ablakok listájából eltávolítja a becsukott ablakot.
     */
    private class ChatWindowAdapter extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            chatFrames.remove((ChatFrame) e.getSource());
        }
    }
}

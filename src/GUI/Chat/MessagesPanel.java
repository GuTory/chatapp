package GUI.Chat;

import Data.Message;
import Data.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Üzenetpanel, a Chatframe komponense
 */
public class MessagesPanel extends JPanel implements ListSelectionListener {

    /**
     * Frame, amihez tartozik
     */
    ChatFrame frame;

    /**
     * Barát, aki éppne ki van választva
     */
    User friend;

    /**
     * Üzenetek megjelenítésére alkalmas JList
     */
    private JList<Message> messageJList;

    /**
     * Üzenetmegjelenítéshez használt ListModel
     */
    private DefaultListModel<Message> listModel;

    /**
     * <h3>Konstruktor</h3>
     * Inicializálja a komponenst, felépíti a ListModelt
     * @param frame frame, amihez tartozik
     * @param friend barát
     */
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

    /**
     * Frissíti a listát
     * @param newUser
     */
    public void refresh(User newUser){
        friend = newUser;
        listModel.removeAllElements();
        loadMessages();
    }

    /**
     * Betölti az üzeneteket a ListModelbe
     */
    public void loadMessages(){
        LinkedList<Message> messages = frame.getUser().getFriends().get(friend);
        if(messages != null){
            listModel.addAll(messages);
            revalidate();
            repaint();
        }
    }

    /**
     * ListSelectionListener implementáció, ha megváltozik a kiválasztott elem a listában,
     * akkor ha van csatolmány fájl, akkor megnyitja
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            Message selected = messageJList.getSelectedValue();
            if(selected.getAttachment() != null){
                try {
                    Desktop dtp = Desktop.getDesktop();
                    dtp.open(selected.getAttachment());
                } catch (IOException ioException) {
                     JOptionPane.showConfirmDialog(frame ,"No such File");
                    ioException.printStackTrace();
                }
            }
        }
    }

    /**
     * Jlist gettere
     * @return jlist
     */
    public JList<Message> getMessageJList() {
        return messageJList;
    }

    /**
     * ListModel gettere
     * @return listmodel
     */
    public DefaultListModel<Message> getListModel() {
        return listModel;
    }
}

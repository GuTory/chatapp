package GUI.Chat;

import Data.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Barátokat megjelenítő osztály, a bejelentkezett felhasználó barátainak a shallow copy-jait tárolja
 */
public class FriendList extends JPanel implements ListSelectionListener {
    /**
     * Panel, amihez tartozik
     */
    private ChatPanel panel;

    /**
     * Barátok fizikai megjelenítésére szolgáló JList
     */
    private JList<User> list;

    /**
     * Barátokat tároló (csak a rá mutató pointereket) ListModel
     */
    private DefaultListModel<User> model;

    /**
     * <h3>Konstruktor</h3>
     * Inicializálja a komponenseket
     * @param panel panel, amihez tartozik
     */
    public FriendList(ChatPanel panel){
        this.panel = panel;
        list = new JList<>();
        model = new DefaultListModel<>();
        list.setModel(model);
        list.addListSelectionListener(this);
        model.addAll(this.panel.getFrame().getUser().getFriends().keySet());
        add(list);
    }

    /**
     * JList gettere
     * @return  Jlist
     */
    public JList<User> getList() {
        return list;
    }

    /**
     * ListModel gettere
     * @return
     */
    public DefaultListModel<User> getModel() {
        return model;
    }

    /**
     * ListSelectionListener implementáció, ha megváltoztatják a kiválasztott listaelemet, akkor frissíti az üzeneteket
     * @param e Actionevent
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            User selected = list.getSelectedValue();
            panel.getMessagesPanel().refresh(selected);
        }
    }


}

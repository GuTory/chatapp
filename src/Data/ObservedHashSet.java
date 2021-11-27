package Data;

import GUI.Chat.ChatFrame;
import GUI.Start.StartFrame;

import javax.swing.*;
import java.io.Serializable;
import java.util.HashSet;

/**
 * <h3>Baráti kéréseket tároló halmaz, ami "le van hallgatva"</h3>
 * A halmazba való illesztéskor vagy a legközelebbi bejelentkezéskor a felhasználónak (akihez tartozik) pop-up ablakok jelennek meg, hogy elfogadja-e a barátkéréseket
 */
public class ObservedHashSet implements Serializable {
    /**
     * Felhasználó, akihez tartozik a halmaz
     */
    User user;

    /**
     * Barátnak jelentkező felhasználók halmaza
     */
    HashSet<User> friendRequests;

    /**
     * <h3>Konstruktor</h3>
     * @param user Ehhez a felhasználóhoz fog tartozni egy páldány
     */
    public ObservedHashSet(User user){
        this.user = user;
        friendRequests = new HashSet<>();
    }

    /**
     * Hozzáadja a felhasználót a barátság kérők közé
     * @param user barátság kérő
     */
    public void push(User user){
        friendRequests.add(user);
        if(StartFrame.loggedInFrame(this.user)!= null){
            actionPerformed(user);
        }
    }

    /**
     * Saját implementációjú Listener, pop-up window-t jelenít meg, ha az a felhasználó, akinek a másik a barátja szeretne lenni, be van jelentkezve.
     * @param user keresett felhasználó (akinek a barátja akar lenni a másik)
     */
    public void actionPerformed(User user){
        ChatFrame frame = StartFrame.loggedInFrame(user);
        int accept = JOptionPane.showOptionDialog(frame,user.getName() + " sent you a friend request. Do you accept it?",
                "Friend request",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,null,null);
        switch (accept){
            case 0:
                this.user.addFriend(user);
                break;
            case 1:
                System.out.println("denied");
        }
        friendRequests.remove(user);
    }

    /**
     * @return barátkérések mérete
     */
    public int size(){
        return friendRequests.size();
    }

    /**
     * Barátságkérők közül egyet lekérő getter
     * @return egy barátkérő user
     */
    public User get(){
        return friendRequests.iterator().next();
    }
}

package GUI.Chat;

import Data.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FriendListPanel extends JPanel {
    private User user;
    private ArrayList<FriendPanel> panelList;

    public FriendListPanel(User u){
        user = u;
        panelList = new ArrayList<>();
        setAlignmentX(Component.LEFT_ALIGNMENT);
        if(user.getFriends().size()>0){
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            for(User user : user.getFriends().keySet()){
                FriendPanel fp = new FriendPanel(user);
                panelList.add(fp);
            }
        } else {
            add(new JLabel("No friends to show :( Make some Friends!"));
        }

    }

    public void addUserToList(User u){
        FriendPanel fp = new FriendPanel(u);
        add(fp);
    }


    public void removeUserFromList(User u){
        for(int i=0; i< this.getComponents().length; i++){
            if(getComponents()[i].toString().equals(user.getUsername())){
                remove(getComponents()[i]);
            }
        }
    }
}

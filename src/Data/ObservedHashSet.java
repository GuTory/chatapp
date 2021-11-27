package Data;

import GUI.Chat.ChatFrame;
import GUI.Start.StartFrame;

import javax.swing.*;
import java.io.Serializable;
import java.util.HashSet;

public class ObservedHashSet implements Serializable {
    User user;

    HashSet<User> friendRequests;

    public ObservedHashSet(User user){
        this.user = user;
        friendRequests = new HashSet<>();
    }

    public void push(User user){
        friendRequests.add(user);

        if(StartFrame.loggedInFrame(this.user)!= null){
            actionPerformed(user);
        }
    }

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

    public int size(){
        return friendRequests.size();
    }

    public User get(){
        return friendRequests.iterator().next();
    }
}

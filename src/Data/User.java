package Data;

import GUI.Chat.ChatFrame;
import GUI.Start.StartFrame;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

public class User implements Serializable {
    private String username;
    private String password;
    private String name;
    private int age;
    public HashMap<User, LinkedList<Message>> friends;

    public User(String un, String pw, String n, int a){
        username = un;
        password = pw;
        name = n;
        age = a;
        friends = new HashMap<>();
    }

    synchronized public void addFriend(User u){
        if(!friends.containsKey(u)){
            LinkedList<Message> messages = new LinkedList<>();
            for(ChatFrame f: StartFrame.getChatFrames()){
                if (f.getUser().getUsername().equals(u.getUsername()) ||
                        f.getUser().getUsername().equals(username)){
                    f.getFriendPanel().addUserToList(this);
                    f.getFriendPanel().revalidate();
                    f.getFriendPanel().repaint();
                }
            }
            friends.put(u, messages);
            u.friends.put(this, messages);
        }
    }

    synchronized public void removeFriend(User u){
        friends.remove(u);
        u.friends.remove(u);
        for(ChatFrame f: StartFrame.getChatFrames()){
            if (f.getUser().getUsername().equals(u.getUsername())){
                f.getFriendPanel().removeUserFromList(this);
                //törölni kell a megfelelőt
                f.getFriendPanel().revalidate();
                f.getFriendPanel().repaint();
            } else if(f.getUser().getUsername().equals(username)){
                f.getFriendPanel().removeUserFromList(u);
                //törölni kell a megfelelőt
                f.getFriendPanel().revalidate();
                f.getFriendPanel().repaint();
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public HashMap<User, LinkedList<Message>> getFriends() {
        return friends;
    }
}

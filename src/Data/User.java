package Data;

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
            friends.put(u, messages);
            u.friends.put(this, messages);
        }
    }

    synchronized public void removeFriend(User u){
        friends.remove(u);
        u.friends.remove(u);
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

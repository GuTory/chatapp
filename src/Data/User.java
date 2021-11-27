package Data;

import GUI.Chat.ChatFrame;
import GUI.Start.StartFrame;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * <h2>Felhasznlókat implementáló osztály</h2>
 * Minden komponense és ő maga szerializálható, hiszen az ővelük kapcsolatos adatokat akarjuk elmenteni minden egyes alkalommal.
 */
public class User implements Serializable {

    /**
     * Felhasználónév
     */
    private String username;

    /**
     * Jelszó
     */
    private String password;

    /**
     * Név
     */
    private String name;

    /**
     * életkor
     */
    private int age;

    /**
     * Barátok és a velük váltott üzeneteket tároló HashMap
     */
    private HashMap<User, LinkedList<Message>> friends;

    /**
     * Baráti kéréseket tároló {@link ObservedHashSet}
     */
    private ObservedHashSet friendRequests;

    /**
     * <h3>Konstruktor</h3>
     * Csak minden paraméterrel rendelkező felhasználókat lehet regisztrálni, azaz nincs default konstruktor
     * @param un felhasználónév
     * @param pw jelszó
     * @param n Rendes név
     * @param a életkor
     */
    public User(String un, String pw, String n, int a){
        username = un;
        password = pw;
        name = n;
        age = a;
        friends = new HashMap<>();
        friendRequests = new ObservedHashSet(this);
    }

    /**
     * Barát tényleges hozzáadása
     * @param u leendő barát
     */
     public void addFriend(User u){
        if(!friends.containsKey(u)){
            LinkedList<Message> messages = new LinkedList<>();
            friends.put(u, messages);
            u.friends.put(this, messages);
            ChatFrame frame1 = StartFrame.loggedInFrame(this);
            ChatFrame frame2 = StartFrame.loggedInFrame(u);
            if(frame1 != null){
                frame1.getChatPanel().refreshFriendList();
            }
            if(frame2 != null){
                frame2.getChatPanel().refreshFriendList();
            }
        }
    }

    /**
     * barátkérelem benyújtása <i>this</i>, azaz azon példány felé, akin ezt a függvényt hívták
     * @param u
     */
    public void addFriendRequest(User u){
        friendRequests.push(u);
    }

    /**
     * Név gettere
     * @return név
     */
    public String getName() {
        return name;
    }

    /**
     * Felhasználónév gettere
     * @return felhasználónév
     */
    public String getUsername() {
        return username;
    }

    /**
     * Jelszó gettere
     * @return jelszó
     */
    public String getPassword() {
        return password;
    }

    /**
     * Kor gettere
     * @return kor
     */
    public int getAge() {
        return age;
    }

    /**
     * Szöveges reprezentáció
     * @return szöveges reprezentáció
     */
    public String toString(){
        return username + ": " + name;
    }

    /**
     * Barátok HashMap gettere
     * @return friends HashMap
     */
    public HashMap<User, LinkedList<Message>> getFriends() {
        return friends;
    }

    /**
     * Felhasználók megegyezőségének vizsgálata
     * @param u másik user, akivel megegyeznek, ha azonos a felhasználónevük
     * @return igaz, ha megegyezik a felhasználónevük
     */
    public boolean equals(User u){
        return username.equals(u.getUsername());
    }

    /**
     * Baráti kérések halmazának gettere
     * @return baráti kérések halmaza
     */
    public ObservedHashSet getFriendRequests() {
        return friendRequests;
    }

    /**
     * Barát eltávolítása
     * @param user eltávolítanidó barát
     */
    public void removeFriend(User user) {
        friends.remove(user);
        user.friends.remove(this);
    }
}

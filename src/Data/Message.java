package Data;

import java.io.File;
import java.io.Serializable;

/**
 * <h3>Üzenet osztály</h3>
 */
public class Message implements Serializable {

    /**
     * Küldő felhasználó
     */
    private User sender;

    /**
     * Fogadó felhasználó
     */
    private User reciever;

    /**
     * Szöveges üzenet
     */
    private String message;

    /**
     * Fájl csatolmány, ha van. Csatolmány nem lehet szöveges üzenet nélkül
     */
    private File attachment;

    /**
     * <H3>Konstruktor</h3>
     * @param sender A küldő felhasználó
     * @param reciever A fogadó felhasználó
     * @param message Szöveges üzenet
     * @param attachment Csatolmány, lehet null is
     */
    public Message(User sender, User reciever, String message, File attachment){
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
        this.attachment = attachment;
    }

    /**
     * Az üzenet szöveges formátuma
     * @return az üzenet szöveges formátuma
     */
    public String toString(){
        if(attachment ==null){
            return sender.getUsername() + ": " + message;
        } else {
            return sender.getUsername() + " sent a file: " + attachment.toString() + ", with a message: " + message;
        }
    }


    /**
     * Küldő felhasználó gettere (leginkább teszteléshez kell)
     * @return küldő felhasználó
     */
    public User getSender() {
        return sender;
    }

    /**
     * Szöveges üzenet gettere (leginkább teszteléshez kell)
     * @return küldő felhasználó
     */
    public String getMessage() {
        return message;
    }

    /**
     * Csatolmány gettere
     * @return csatolmány
     */
    public File getAttachment(){
        return attachment;
    }
}

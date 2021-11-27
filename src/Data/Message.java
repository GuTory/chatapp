package Data;

import java.io.File;
import java.io.Serializable;

public class Message implements Serializable {
    private User sender;
    private User reciever;

    private String message;
    private File attachment;

    public Message(User sender, User reciever, String message, File attachment){
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
        this.attachment = attachment;
    }

    public String toString(){
        if(attachment ==null){
            return sender.getUsername() + ": " + message;
        } else {
            return sender.getUsername() + " sent a file: " + attachment.toString() + ", with a message: " + message;
        }
    }

    public User getSender() {
        return sender;
    }

    public User getReciever() {
        return reciever;
    }

    public String getMessage() {
        return message;
    }

    public File getAttachment(){
        return attachment;
    }
}

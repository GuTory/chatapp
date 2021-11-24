package Data;

import GUI.Chat.MessagesPanel;

import java.io.Serializable;

public class Message implements Serializable {
    private User sender;
    private User reciever;

    private String message;
    private Attachment attachment;

    public Message(User sender, User reciever, String message, boolean attachment){
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
        if(!attachment){
            this.attachment = null;
        } else {
            //.... GZIP?
        }
    }

    public String toString(){
        return sender.getUsername() + ": " + message;
    }
}

package Data;

import java.io.Serializable;

public class Message implements Serializable {
    private User sender;
    private User reciever;

    private String message;
    private Attachment attachment;
}

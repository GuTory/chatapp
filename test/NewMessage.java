import Data.Message;
import Data.User;
import GUI.Chat.ChatFrame;
import GUI.Chat.MessagingPanel;
import GUI.Start.StartFrame;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.LinkedList;

public class NewMessage {
    private static StartFrame frame;
    private static ChatFrame chatFrame;
    private static User admin;
    private static User fusti;
    private static User talm;

    @Before
    public void loginWithtalm(){
        frame = new StartFrame();
        LinkedList<User> users = frame.getUsers();
        admin = users.get(0);
        fusti = users.get(1);
        talm = users.get(2);

        Assert.assertEquals("Kristóf",admin.getName());
        Assert.assertEquals("Füstös Gergely",fusti.getName());
        Assert.assertEquals("Török Álmos",talm.getName());
    }

    @Test
    public void sendMessageTofusti(){
        frame.openChatFrame(talm);
        chatFrame = frame.getChatFrames().get(0);
        Assert.assertEquals(talm, chatFrame.getUser());
        JList<User> friendList = chatFrame.getChatPanel().getFriendList().getList();
        friendList.setSelectedIndex(0);
        User selected = friendList.getSelectedValue();
        Assert.assertEquals(fusti, selected);
    }

    @After
    public void messageRecieved(){
        MessagingPanel messagingPanel = chatFrame.getChatPanel().getMessagingPanel();
        JTextField textField = messagingPanel.getMessageField();
        JButton button = messagingPanel.getSendButton();
        String message ="Heyo ez egy teszt";
        textField.setText(message);
        button.doClick();
        Message newMessage = talm.getFriends().get(fusti).get(0);
        Assert.assertEquals(talm.getUsername() + ": " + message, newMessage.toString());
    }
}

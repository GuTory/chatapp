import Data.Message;
import Data.User;
import GUI.Chat.ChatFrame;
import GUI.Chat.MessagesPanel;
import GUI.Start.StartFrame;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;


public class Messages {
    private static StartFrame frame;
    private static User testuser;
    private static ChatFrame testframe;
    private static MessagesPanel messagesPanel;

    @BeforeClass
    public static void setup(){
        frame = new StartFrame();
        testuser = frame.getUsers().get(0);
        frame.openChatFrame(testuser);
        testframe = frame.getChatFrames().get(0);
        messagesPanel = testframe.getChatPanel().getMessagesPanel();
        testframe.getChatPanel().getFriendList().getList().setSelectedIndex(0);
    }

    @Test
    public void messages(){
        ListModel<Message> listModel = messagesPanel.getListModel();
        Message testedMessage = listModel.getElementAt(0);
        Assert.assertEquals(testedMessage.getAttachment().toString(), "D:\\Downloads\\komhal.pdf");
        Assert.assertEquals(testedMessage.getMessage(), "nosza");
        Assert.assertEquals(testedMessage.getSender(), testuser);

        testedMessage = listModel.getElementAt(1);

        Assert.assertNull(testedMessage.getAttachment());
        Assert.assertEquals(testedMessage.getMessage(), "komhál pls");
    }
}

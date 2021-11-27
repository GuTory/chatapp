import Data.User;
import GUI.Chat.ChatFrame;
import GUI.Start.StartFrame;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;

public class LoginAndSingUpTest {
    private static StartFrame frame;
    private static User testuser;

    @BeforeClass
    public static void setup(){
        frame = new StartFrame();
        testuser = frame.getUsers().get(0);
    }

    @Test
    public void login(){
        Assert.assertEquals(  frame.getUsers().size(),3);

        Assert.assertEquals(testuser.getUsername(),"admin");
        Assert.assertEquals(testuser.getPassword(),"admin");
        Assert.assertEquals(testuser.getName(), "Kristóf");
        Assert.assertEquals(testuser.getFriends().size(),1);

    }

    @Test
    public void friends(){
        frame.openChatFrame(testuser);
        ChatFrame testframe = frame.getChatFrames().get(0);

        Assert.assertTrue(testframe.getChatPanel().getFriendList().getList().isSelectionEmpty());

        testframe.getChatPanel().getFriendList().getList().setSelectedIndex(0);
        User user = testframe.getChatPanel().getFriendList().getList().getSelectedValue();
        Iterator<User> iterator = testuser.getFriends().keySet().iterator();

        Assert.assertSame(user, iterator.next());
    }
}

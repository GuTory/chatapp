import Data.User;
import GUI.Chat.ChatFrame;
import GUI.Start.StartFrame;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Friends {
    private static StartFrame frame;
    private static User testuser;

    @BeforeClass
    public static void setup(){
        frame = new StartFrame();
        testuser = frame.getUsers().get(0);
    }

    @Test
    public void testMe(){
        Assert.assertEquals(frame.getUsers().size(),3);
        Assert.assertEquals("admin",testuser.getUsername());
        Assert.assertEquals("admin",testuser.getPassword());
        Assert.assertEquals("Kristóf",testuser.getName());
        Assert.assertEquals(20, testuser.getAge());
        Assert.assertEquals(testuser.getFriends().size(),1);
        frame.openChatFrame(testuser);
        List<ChatFrame> frames = frame.getChatFrames();
        Assert.assertEquals( testuser,frames.get(0).getUser());
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

        User friendUser = frame.getUsers().get(2);
        HashSet<User> friendSet = new HashSet<>();
        friendSet.add(friendUser);
        Assert.assertEquals(testuser.getFriends().keySet(), friendSet);
    }
}

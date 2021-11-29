import Data.User;
import GUI.Chat.ChatFrame;
import GUI.Start.StartFrame;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;

public class Login {
    private static StartFrame frame;
    private static User testuser;

    @BeforeClass
    public static void setup(){
        frame = new StartFrame();
        testuser = frame.getUsers().get(0);
    }

    @Test
    public void wrongLogin(){
        JTextField loginField = frame.getLoginPanel().getUserNameField();
        JTextField passwordField = frame.getLoginPanel().getPasswordField();
        loginField.setText("admin");
        passwordField.setText("adminn");
        int before = StartFrame.getChatFrames().size();
        JButton button = frame.getButtonPanel().getLoginButton();
        button.doClick();
        int after = StartFrame.getChatFrames().size();
        Assert.assertEquals(before, after);
    }

    @Test
    public void login(){
        JTextField loginField = frame.getLoginPanel().getUserNameField();
        JTextField passwordField = frame.getLoginPanel().getPasswordField();
        loginField.setText("admin");
        passwordField.setText("admin");
        JButton button = frame.getButtonPanel().getLoginButton();
        Assert.assertEquals(0, StartFrame.getChatFrames().size());
        button.doClick();
        System.out.println(StartFrame.getChatFrames().size());
        Assert.assertEquals(1, StartFrame.getChatFrames().size());
        ChatFrame chatFrame = frame.getChatFrames().get(0);
        Assert.assertEquals(testuser, chatFrame.getUser());
    }
}

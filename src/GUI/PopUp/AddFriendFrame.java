package GUI.PopUp;

import GUI.Chat.ChatFrame;

import javax.swing.*;

public class AddFriendFrame extends JDialog {
    private ChatFrame frame;

    private JButton addButton;


    public AddFriendFrame(ChatFrame frame){
        this.frame = frame;
        JOptionPane op = new JOptionPane("helo");
        op.createDialog(frame, "Add Friend");
    }
}

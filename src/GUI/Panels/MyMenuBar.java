package GUI.Panels;

import Data.User;
import GUI.Frames.ChatFrame;
import GUI.Frames.StartFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMenuBar extends JMenuBar {
    ChatFrame frame;


    public MyMenuBar(ChatFrame f){
        frame = f;
        JMenuItem self = new JMenuItem("You are logged in as " + frame.getUser().getName());
        JMenuItem addFriends = new JMenuItem("Add Friends");
        JMenuItem deleteAccount = new JMenuItem("Delete Account");
        JMenuItem exit = new JMenuItem("Exit");

        self.addActionListener(new SelfListener());
        addFriends.addActionListener(new AddFriendListener());
        deleteAccount.addActionListener(new DeleteAccountListener());
        exit.addActionListener(new ExitListener());
        add(self);
        add(addFriends);
        add(deleteAccount);
        add(exit);
    }

    private class SelfListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("personal info");
        }
    }

    private class AddFriendListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("add friends");
        }
    }

    private class DeleteAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            StartFrame.getUsers().remove(frame.getUser());
            for (User u: frame.getUser().getFriends().keySet()){
                u.removeFriend(frame.getUser());
            }
            StartFrame.getChatFrames().remove(frame);
            frame.dispose();
        }
    }

    private class ExitListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            StartFrame.getChatFrames().remove(frame);
            frame.dispose();
        }
    }
}

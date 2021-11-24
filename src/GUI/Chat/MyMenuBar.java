package GUI.Chat;

import Data.User;
import GUI.Start.StartFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyMenuBar extends JMenuBar {
    ChatFrame frame;

    JMenuItem self;
    JMenuItem addFriends;
    JMenuItem removeFriends;
    JMenuItem deleteAccount;
    JMenuItem exit;

    public MyMenuBar(ChatFrame f){
        frame = f;
        self = new JMenuItem( frame.getUser().getName());
        addFriends = new JMenuItem("Add Friends");
        removeFriends = new JMenuItem("Remove Friends");
        deleteAccount = new JMenuItem("Delete Account");
        exit = new JMenuItem("Exit");

        self.addActionListener(new SelfListener());
        addFriends.addActionListener(new AddFriendListener());
        removeFriends.addActionListener(new RemoveFriendListener());
        deleteAccount.addActionListener(new DeleteAccountListener());
        exit.addActionListener(new ExitListener());
        add(self);
        add(addFriends);
        add(removeFriends);
        add(deleteAccount);
        add(exit);
    }

    private class SelfListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            User alany = frame.getUser();
            JOptionPane op = new JOptionPane("Name: " + alany.getName() + '\n'
                                            +"Password: " + alany.getPassword() + '\n'
                                            +"Username: " + alany.getUsername() + '\n'
                                            +"Age: " + alany.getAge() + '\n');
            op.setLayout(new BoxLayout(op,BoxLayout.Y_AXIS));
            JDialog dg = op.createDialog ( frame, "Personal info");
            try {
                ImageIcon icon = new ImageIcon("chat.png");
                dg.setIconImage(icon.getImage());
            } catch (Exception ee){
                ee.printStackTrace();
            }
            dg.setVisible(true);
        }
    }

    private class AddFriendListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("add friends");
            for (ChatFrame f: StartFrame.getChatFrames()){
                f.getChatPanel().refreshFriendList();
            }
        }
    }

    private class RemoveFriendListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("remove friend");
        }
    }

    private class DeleteAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            StartFrame.getUsers().remove(frame.getUser());
            for (User u: frame.getUser().getFriends().keySet()){
                u.removeFriend(frame.getUser());
            }
            for(ChatFrame f : StartFrame.getChatFrames()){
                f.getChatPanel().refreshFriendList();
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

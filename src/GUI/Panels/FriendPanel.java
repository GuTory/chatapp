package GUI.Panels;

import Data.User;

import javax.swing.*;
import java.awt.event.ActionEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FriendPanel extends JPanel implements MouseListener {
    User user;

    JLabel label;

    public FriendPanel(User u){
           label = new JLabel(user.getName() + " (" + user.getUsername()+ ") " + user.getAge() + " years old");
           label.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("load messages");
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

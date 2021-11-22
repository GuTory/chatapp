package GUI.Start;

import Data.User;
import GUI.Chat.ChatFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class  ButtonPanel extends JPanel {
    private StartFrame frame;

    private  JButton loginButton;
    private  JButton signUpButton;

    public ButtonPanel(StartFrame f){
        frame = f;

        setLayout(new FlowLayout());
        signUpButton = new JButton("Sign up");
        loginButton = new JButton("Login");

        add(loginButton);
        add(signUpButton);

        loginButton.addActionListener(new LoginListener());
        signUpButton.addActionListener( new SignUpListener());
    }

    private class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = frame.getLoginPanel().getUserNameField().getText();
            String password = frame.getLoginPanel().getPasswordField().getText();
            for(User u: StartFrame.getUsers()){
                if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                    if(!LoggedIn(username)){
                        frame.getMessageLabel().setText("Welcome, " + u.getName() + "!");
                        frame.openChatFrame(u);
                        frame.getLoginPanel().setDefault();
                        frame.getSignUpPanel().setDefault();
                    } else {
                        frame.getMessageLabel().setText("You are already logged in!");
                    }
                    break;
                } else {
                    frame.getMessageLabel().setText("Invalid username or password!");
                }
                /*

                HARD DELETE

                u.getFriends().clear();
                */
            }
        }

        private boolean LoggedIn(String username){
            for(ChatFrame u : StartFrame.getChatFrames()){
                if(u.getUser().getUsername().equals(username)){
                    return true;
                }
            }
            return false;
        }
    }

    private class SignUpListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String username = frame.getLoginPanel().getUserNameField().getText();
                String password = frame.getLoginPanel().getPasswordField().getText();
                String name = frame.getSignUpPanel().getNameField().getText();
                int age = Integer.parseInt(frame.getSignUpPanel().getAgeField().getText());
                for(User u: StartFrame.getUsers()){
                    if(u.getUsername().equals(username)){
                        frame.getMessageLabel().setText("This username is already taken!");
                        return;
                    }
                }
                User newUser = new User(username, password, name, age);

                /*
                    FOR TESTING PURPOSES ONLY
                */
                for(User u: StartFrame.getUsers()){
                    if(u.getUsername().equals("admin")){
                        newUser.addFriend(u);
                        System.out.println("friend added?");
                    }
                }

                StartFrame.getUsers().add(newUser);
                frame.getMessageLabel().setText("Welcome, " + name + "!");
                frame.openChatFrame(newUser);
                frame.getLoginPanel().setDefault();
                frame.getSignUpPanel().setDefault();
            } catch (Exception ee){
                frame.getMessageLabel().setText("Please fill all textfields and enter valid information!");
            }

        }
    }
}

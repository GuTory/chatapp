package GUI.Start;

import Data.User;
import GUI.Chat.ChatFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h3><h3>Belépő és regisztráló gombokat tartalmazó panel, a {@link StartFrame}, kezdőablak komponense</h3></h3>
 */
public class  ButtonPanel extends JPanel {

    /**
     * A nyitóablak, amihez a panel tartozik
     */
    private StartFrame frame;

    /**
     * Bejelentkező gomb
     */
    private  JButton loginButton;

    /**
     * Regisztráló gomb
     */
    private  JButton signUpButton;

    /**
     * <h3>Konstruktor</h3>
     * Inicializálja a panelt és beállítja a listenereket.
     * @param f A nyitóablak, amihez tartozni fog a panel
     */
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

    /**
     * <h3>Belejentkezésért felelős {@link ActionListener}</h3>
     * Ha jó felhasználónév és jelszópárost adott meg a felhasználó, akkor megnyitja a csevegőablakát
     */
    private class LoginListener implements ActionListener {
        /**
         * A gomb megnyomásakor lefutó bejelentkeztetés, ha a feltételek fennállnak és jelzés, ha esetleg rosszak az adatok
         * @param e Actionevent
         */
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
            }
        }

        /**
         * Egy felhasználó egyszerre csak egyszer jelentkezhet be, a függvény kideríti, hogy a felhasználó be van-e.
         * @param username keresett felhasználónév
         * @return igaz, ha a felhasználó már be van jelentkezve, egyébként hamis
         */
        private boolean LoggedIn(String username){
            for(ChatFrame u : StartFrame.getChatFrames()){
                if(u.getUser().getUsername().equals(username)){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * <h3>Regisztrációért felelős {@link ActionListener}</h3>
     * Ha még nem szereplő felhasználónevet adott meg a felhasználó, és valid értékeket írt be a mezőkbe
     * (kor helyére számot írt és minden mező ki van töltve), akkor megnylik a csevegőablaka.
     */
    private class SignUpListener implements ActionListener {

        /**
         * A gomb megnyomásakor lefutó regisztráció, adatok ellenőrzése és jelzés, ha érvénytelenek az adatok.
         * @param e Actionevent
         */
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

package GUI.Chat;

import Data.Message;
import Data.User;
import GUI.Start.StartFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * <h3>Üzenőpanel, a {@link ChatFrame} komponense</h3>
 */
public class MessagingPanel extends JPanel {
    /**
     * Panel, amihez tartozik
     */
    private ChatPanel panel;

    /**
     * Textfield, amibe az üzenet szövegét be lehet illeszteni
     */
    private JTextField messageField;

    /**
     * Gomb, amivel el lehet küldeni az üzenetet a kiválasztott ismerősnek
     */
    private JButton sendButton;

    /**
     * Mindenkori csatolmány az új üzenethez, küldés után nullázódik
     */
    private File attachment;

    /**
     * ezzel a gombbal lehet elindítani a csatolás folyamatát
     */
    private JButton attachmentButton;


    /**
     * <h3>Konstruktor</h3>
     * Inicializálja a komponenseket és összerakja magát, ráállítja a gombokra a listenereket is
     * @param chatPanel panel, amihez tartozik
     */
    public MessagingPanel(ChatPanel chatPanel){
        panel = chatPanel;
        messageField = new JTextField(40);
        sendButton = new JButton("Send");
        attachmentButton = new JButton("Attach file");
        sendButton.addActionListener(new SendMessageListener());
        attachmentButton.addActionListener(new AddAttachmentListener());
        add(messageField);
        add(sendButton);
        add(attachmentButton);
        panel.add(this, BorderLayout.SOUTH);
    }

    /**
     * küldésgomb gettere, (JUnit) teszteléshez kell
     * @return küldésgomb
     */
    public JButton getSendButton() {
        return sendButton;
    }

    /**
     * üzenő szövegdoboz gettere
     * @return üzenődoboz
     */
    public JTextField getMessageField() {
        return messageField;
    }

    /**
     * Üzenetküldés Listener implementáció, ha nem üres a szövegdoboz és ki van választva egy barátunk, akkor elküldi neki az üzenetet
     */
    private class SendMessageListener implements ActionListener {

        @Override
        synchronized public void actionPerformed(ActionEvent e) {
            FriendList friendList = panel.getFriendList();
            User user = panel.getFrame().getUser();
            MessagesPanel messagesPanel = panel.getMessagesPanel();

            if(!friendList.getList().isSelectionEmpty()){
                User reciever = friendList.getList().getSelectedValue();
                if(!messageField.getText().equals("")){
                    Message newMessage = new Message(user, reciever, messageField.getText(), attachment);
                    user.getFriends().get(reciever).push(newMessage);
                    messagesPanel.refresh(reciever);
                    ChatFrame frame1 = StartFrame.loggedInFrame(reciever);
                    if(frame1 != null){
                        frame1.getChatPanel().getMessagesPanel().refresh(user);
                    }
                    messageField.setText("");
                }
            } else {
                JOptionPane op = new JOptionPane("Please select one of your Friends!");
                op.setLayout(new BoxLayout(op,BoxLayout.Y_AXIS));
                JDialog dg = op.createDialog ( panel.frame, "Warning");
                dg.setVisible(true);
            }
            attachment = null;
        }
    }

    /**
     * Fájl csatolás Listener implementációja
     */
    private class AddAttachmentListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(panel.frame);
            attachment = fileChooser.getSelectedFile();
            System.out.println(attachment.toString());
        }
    }
}

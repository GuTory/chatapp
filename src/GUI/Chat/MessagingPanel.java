package GUI.Chat;

import Data.Message;
import Data.User;
import GUI.Start.StartFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MessagingPanel extends JPanel {
    private ChatPanel panel;

    private JTextField messageField;
    private JButton sendButton;
    File attachment;
    private JButton attachmentButton;

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

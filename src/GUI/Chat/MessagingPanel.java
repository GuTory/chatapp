package GUI.Chat;

import Data.Message;
import Data.User;
import GUI.Start.StartFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessagingPanel extends JPanel {
    private ChatPanel panel;

    private JTextField messageField;
    private JButton sendButton;

    public MessagingPanel(ChatPanel chatPanel){
        panel = chatPanel;
        messageField = new JTextField(40);
        sendButton = new JButton("Send");
        sendButton.addActionListener(new SendMessageListener());
        add(messageField);
        add(sendButton);
        panel.add(this, BorderLayout.SOUTH);
    }

    private class SendMessageListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FriendList friendList = panel.getFriendList();
            User user = panel.user;
            MessagesPanel messagesPanel = panel.getMessagesPanel();

            if(!friendList.getList().isSelectionEmpty()){
                User reciever = friendList.getList().getSelectedValue();
                if(!messageField.getText().equals("")){
                    Message newMessage = new Message(user, reciever, messageField.getText(), false);
                    user.getFriends().get(reciever).push(newMessage);
                    messagesPanel.refresh(reciever);
                    for(ChatFrame frames : StartFrame.getChatFrames()){
                        if (frames.getUser().equals(reciever)){
                            frames.getChatPanel().getMessagesPanel().refresh(user);
                        }
                    }
                    messageField.setText("");
                }
            } else {
                JOptionPane op = new JOptionPane("Please select one of your Friends!");
                op.setLayout(new BoxLayout(op,BoxLayout.Y_AXIS));
                JDialog dg = op.createDialog ( panel.frame, "Warning");
                dg.setVisible(true);
            }
        }
    }
}

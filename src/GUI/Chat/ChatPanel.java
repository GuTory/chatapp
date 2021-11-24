package GUI.Chat;

import Data.Message;
import Data.User;
import GUI.Start.StartFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends JPanel implements ListSelectionListener {
    ChatFrame frame;
    User user;

    private JPanel southPanel;
    private JTextField messageField;
    private JButton sendButton;
    private JScrollPane westPane;
    private JScrollPane centerPane;
    private MessagesPanel messagesPanel;

    JList<User> list;
    DefaultListModel<User> model;

    public ChatPanel(ChatFrame f, User u){
        frame = f;
        user = u;
        setLayout(new BorderLayout());
        southPanel = new JPanel();
        messageField = new JTextField(40);
        sendButton = new JButton("Send");
        sendButton.addActionListener(new SendMessageListener());
        southPanel.add(messageField);
        southPanel.add(sendButton);
        list = new JList<>();
        model = new DefaultListModel<>();
        list.setModel(model);
        list.addListSelectionListener(this);
        model.addAll(user.friends.keySet());

        westPane = new JScrollPane(list);
        /*
            Texting purposes only
         */
        User select = list.getSelectedValue();
        messagesPanel = new MessagesPanel(frame , select);

        centerPane = new JScrollPane(messagesPanel);

        add(westPane, BorderLayout.WEST);
        add(southPanel, BorderLayout.SOUTH);
        add(centerPane, BorderLayout.CENTER);

    }

    public void refreshFriendList(){
        model.removeAllElements();
        model.addAll(user.friends.keySet());
        westPane.revalidate();
        westPane.repaint();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            User selected = list.getSelectedValue();
            messagesPanel.refresh(selected);
        }
    }

    private class SendMessageListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!list.isSelectionEmpty()){
                User reciever = list.getSelectedValue();

                if(!messageField.getText().equals("")){
                    // ha van fájl módosítani kell
                    Message newMessage = new Message(user, reciever, messageField.getText(), false);
                    user.friends.get(reciever).push(newMessage);
                    messagesPanel.refresh(reciever);
                    for(ChatFrame frames : StartFrame.getChatFrames()){
                        if (frames.getUser().equals(reciever)){
                            frames.getChatPanel().messagesPanel.refresh(user);
                        }
                    }
                    messageField.setText("");
                }
            } else {
                JOptionPane op = new JOptionPane("Please select one of your Friends!");
                op.setLayout(new BoxLayout(op,BoxLayout.Y_AXIS));
                JDialog dg = op.createDialog ( frame, "Warning");
                dg.setVisible(true);
            }
        }
    }
}

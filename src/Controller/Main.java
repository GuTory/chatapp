package Controller;

import Data.*;
import Frames.*;

import java.util.LinkedList;

public class Main {
    private static StartFrame startFrame;
    private static LinkedList<ChatFrame> chatFrames;
    private static LinkedList<User> users;



    static void initfordebug(){
        User u = new User("admin","admin", "Kritya", 20);
        User user = new User("user1", "1234", "GYZGY", 22);
        openChatFrame(u);
        openChatFrame(user);
    }

    static void openChatFrame(User u){
        ChatFrame chatFrame = new ChatFrame(u);
        chatFrames.add(chatFrame);
        Thread newthread = new Thread(chatFrame);
        newthread.start();
    }

    public static void main(String[] args) {
        startFrame = new StartFrame();
        users = new LinkedList<>();
        chatFrames = new LinkedList<>();
        Thread mainThread = new Thread(startFrame);
        mainThread.start();
        initfordebug();
    }
}

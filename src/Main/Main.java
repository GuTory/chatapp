package Main;

import GUI.Frames.StartFrame;

public class Main {
    public static void main(String[] args) {
        StartFrame startFrame = new StartFrame();
        Thread mainThread = new Thread(startFrame);
        mainThread.start();
    }
}

package Main;

import GUI.Start.StartFrame;

/**
 * Main osztály, a program indulási pontja
 */
public class Main {
    public static void main(String[] args) {
        StartFrame startFrame = new StartFrame();
        Thread mainThread = new Thread(startFrame);
        mainThread.start();
    }
}

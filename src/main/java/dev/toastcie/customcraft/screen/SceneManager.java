package dev.toastcie.customcraft.screen;

import javax.swing.*;
import java.util.Dictionary;
import java.util.Hashtable;

class SceneManager {
    private Dictionary<String, JPanel> scenes;

    public SceneManager() {
        scenes = new Hashtable<>();
    }

    public void addScene(String name, JPanel scene) {
        scenes.put(name, scene);
    }

    public void setActiveScene(String name, JFrame frame) {
        JPanel pan = scenes.get(name);

        int currentWidth = frame.getWidth();
        int currentHeight = frame.getHeight();

        if (pan == null) {
            System.err.println("Scene " + name + " not found!");
            return;
        }

        frame.setContentPane(pan);
        pan.requestFocusInWindow();
        frame.pack();
        frame.setSize(currentWidth, currentHeight);
        
    }
}

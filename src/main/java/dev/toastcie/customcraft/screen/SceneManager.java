package dev.toastcie.customcraft.screen;

import dev.toastcie.customcraft.screen.panels.ILoopPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

class SceneManager {
    ILoopPanel currentScene;
    private Dictionary<String, ILoopPanel> scenes;

    public SceneManager() {
        scenes = new Hashtable<>();
    }

    public void addScene(String name, ILoopPanel scene) {
        scenes.put(name, scene);
    }

    public void setActiveScene(String name) {
        currentScene = scenes.get(name);

        if (currentScene == null) {
            System.err.println("Scene " + name + " not found!");
            return;
        }

        currentScene = scenes.get(name);

    }

    public void gameLoop() {
        if (currentScene != null) {
            currentScene.loop();
        }
    }

    public void paint(Graphics g, JPanel panel) {
        if (currentScene != null) {
            currentScene.paint(g, panel);
        }
    }
}

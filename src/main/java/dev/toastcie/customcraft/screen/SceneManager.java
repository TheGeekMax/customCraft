package dev.toastcie.customcraft.screen;

import dev.toastcie.customcraft.annotations.screen.LoopPanel;
import dev.toastcie.customcraft.screen.panels.ILoopPanel;
import io.github.classgraph.ClassGraph;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

class SceneManager {
    ILoopPanel currentScene;
    private Dictionary<String, ILoopPanel> scenes;

    public SceneManager() {
        scenes = new Hashtable<>();

        //get all classes with LoopPanel annotation
        var scanResult = new ClassGraph()
                .enableAllInfo()
                .acceptPackages("dev.toastcie.customcraft.screen.panels")
                .scan();
        var classes = scanResult.getClassesWithAnnotation(LoopPanel.class.getName());
        for (var classInfo : classes) {
            try {
                Class<?> cls = Class.forName(classInfo.getName());
                LoopPanel annotation = cls.getAnnotation(LoopPanel.class);
                String sceneName = annotation.value();
                ILoopPanel sceneInstance = (ILoopPanel) cls.getDeclaredConstructor().newInstance();
                addScene(sceneName, sceneInstance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addScene(String name, ILoopPanel scene) {
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

    public void onClick(int x, int y) {
        if (currentScene != null) {
            currentScene.onClick(x, y);
        }
    }
}

package dev.toastcie.customcraft.screen;


import dev.toastcie.customcraft.screen.panels.GamePanel;

import javax.swing.*;

public class MainFrame extends JFrame {
    public static MainFrame instance;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final String TITLE = "CustomCraft";

    public SceneManager sceneManager;

    public MainFrame() {
        if (instance != null) {
            throw new IllegalStateException("MainFrame instance already exists!");
        }
        instance = this;

        initialize();

    }

    private void initialize() {
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        sceneManager = new SceneManager();

        sceneManager.addScene("GAME", new GamePanel());

        sceneManager.setActiveScene("GAME", this);
    }
}

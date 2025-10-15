package dev.toastcie.customcraft.screen;


import dev.toastcie.customcraft.keyevents.Keyboard;
import dev.toastcie.customcraft.screen.panels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame {
    public static MainFrame instance;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final String TITLE = "CustomCraft";
    public SceneManager sceneManager;

    public int screenWidth = WIDTH;
    public int screenHeight = HEIGHT;
    JPanel contentPanel;

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

        //add keyevent listener
        addKeyListener(new KeyListener() {
                           @Override
                           public void keyTyped(KeyEvent keyEvent) {
                           }

                           @Override
                           public void keyPressed(KeyEvent keyEvent) {
                               Keyboard.instance.keyPressed(keyEvent);
                           }

                           @Override
                           public void keyReleased(KeyEvent keyEvent) {
                               Keyboard.instance.keyReleased(keyEvent);
                           }
                       }
        );

        //change sizes var on resize
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                screenWidth = getContentPane().getWidth();
                screenHeight = getContentPane().getHeight();
            }
        });


        contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (sceneManager != null) {
                    sceneManager.paint(g, this);
                }
            }
        };
        contentPanel.setBackground(Color.BLACK);
        setContentPane(contentPanel);
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);

        sceneManager = new SceneManager();
        sceneManager.addScene("GAME", new GamePanel());
        sceneManager.setActiveScene("GAME");

        //start game loop timer
        Timer timer = new Timer(16, e -> {
            sceneManager.gameLoop();
            contentPanel.repaint();
        });
        timer.start();
    }
}

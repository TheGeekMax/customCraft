package dev.toastcie.customcraft.keyevents;

import java.awt.event.KeyEvent;

public class Keyboard {

    public static Keyboard instance;

    public KeyMovement movements;

    private Keyboard() {
        movements = new KeyMovement(KeyEvent.VK_Z, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_D);
    }

    public static Keyboard getInstance() {
        return instance;
    }

    public static void initialize() {
        instance = new Keyboard();
    }

    public void keyPressed(KeyEvent e) {
        movements.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        movements.keyReleased(e);
    }
}

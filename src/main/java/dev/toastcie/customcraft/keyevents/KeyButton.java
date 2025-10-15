package dev.toastcie.customcraft.keyevents;

import java.awt.event.KeyEvent;

public class KeyButton {
    private int keyCode;
    private boolean keyPressed;
    private boolean keyActivated;

    public KeyButton(int keyCode) {
        this.keyCode = keyCode;
        this.keyPressed = false;
        this.keyActivated = false;
    }

    public void keyPressed(KeyEvent e) {
        if (!keyPressed && e.getKeyCode() == this.keyCode) {
            keyPressed = true;
            keyActivated = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == this.keyCode) {
            keyPressed = false;
            keyActivated = false;
        }
    }

    public boolean activable() {
        if (keyActivated) {
            keyActivated = false;
            return true;
        }
        return false;
    }
}

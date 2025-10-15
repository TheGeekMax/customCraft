package dev.toastcie.customcraft.keyevents;

import dev.toastcie.customcraft.math.Vector2Int;

import java.awt.event.KeyEvent;

public class KeyMovement {
    //keycodes

    private int upKey;
    private int downKey;
    private int leftKey;
    private int rightKey;

    //keyPressed
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    private int dx;
    private int dy;

    public KeyMovement(int upKey, int downKey, int leftKey, int rightKey) {
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;

        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;

        dx = 0;
        dy = 0;
    }

    private void calculateDeltaMovement() {
        dx = 0;
        dy = 0;

        dy -= upPressed ? 1 : 0;
        dy += downPressed ? 1 : 0;
        dx -= leftPressed ? 1 : 0;
        dx += rightPressed ? 1 : 0;
    }

    public Vector2Int getDirection() {
        return new Vector2Int(dx, dy);
    }

    public void keyPressed(KeyEvent e) {
        upPressed = (e.getKeyCode() == upKey) || upPressed;
        downPressed = (e.getKeyCode() == downKey) || downPressed;
        leftPressed = (e.getKeyCode() == leftKey) || leftPressed;
        rightPressed = (e.getKeyCode() == rightKey) || rightPressed;

        calculateDeltaMovement();
    }

    public void keyReleased(KeyEvent e) {
        upPressed = e.getKeyCode() != upKey && upPressed;
        downPressed = e.getKeyCode() != downKey && downPressed;
        leftPressed = e.getKeyCode() != leftKey && leftPressed;
        rightPressed = e.getKeyCode() != rightKey && rightPressed;

        calculateDeltaMovement();
    }
}

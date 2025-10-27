package dev.toastcie.customcraft.screen.camera;

import dev.toastcie.customcraft.data.GlobalData;

public class PlayerCamera {

    private final int hitboxWidth = (int) (GlobalData.tileWidth * .8);
    private int x;
    private int y;


    public PlayerCamera(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getHitboxWidth() {
        return hitboxWidth;
    }

    public void move(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }
}

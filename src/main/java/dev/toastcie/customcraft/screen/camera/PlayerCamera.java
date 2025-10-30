package dev.toastcie.customcraft.screen.camera;

import dev.toastcie.customcraft.data.GlobalData;
import dev.toastcie.customcraft.level.Level;
import dev.toastcie.customcraft.tile.BaseTile;

public class PlayerCamera {

    private final int hitboxWidth = (int) (GlobalData.tileWidth * .8);
    private int x;
    private int y;

    private int baseSpeed = 8;
    private int diagBaseSpeed = 4;

    private int slowSpeed = 4;
    private int diagSlowSpeed = 2;
    private boolean inWater;


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

    public void move(int deltaX, int deltaY, Level level) {
        boolean diag = deltaY != 0 && deltaX != 0;
        boolean isSlowing = isSlowing(deltaX, deltaY, level);
        int curSpeed = isSlowing ?
                (diag ? diagSlowSpeed : slowSpeed) :
                (diag ? diagBaseSpeed : baseSpeed);

        this.inWater = isSlowing;

        for (int i = 0; i < curSpeed; i++) {
            int newX = this.x + Integer.signum(deltaX);
            int newY = this.y + Integer.signum(deltaY);

            // Check collisions
            if (!isColliding(newX, this.y, level)) {
                this.x = newX;
            }
            if (!isColliding(this.x, newY, level)) {
                this.y = newY;
            }
        }
    }

    private boolean isSlowing(int deltaX, int deltaY, Level level) {
        // Simple collision detection based on hitbox
        int left = x;
        int right = x + hitboxWidth;
        int top = y;
        int bottom = y + hitboxWidth;

        // Check corners
        return isTileSlow(left, top, level) &&
                isTileSlow(right, top, level) &&
                isTileSlow(left, bottom, level) &&
                isTileSlow(right, bottom, level);
    }

    private boolean isTileSlow(int left, int top, Level level) {
        int tileX = x / GlobalData.tileWidth;
        int tileY = y / GlobalData.tileWidth;

        BaseTile tile = level.getTile(tileX, tileY);
        BaseTile backgroundTile = level.getBackgroundTile(tileX, tileY);
        if (backgroundTile == null) return false;
        return ((tile != null && tile.isSlowing()) || backgroundTile.isSlowing());
    }

    public boolean isColliding(int x, int y, Level level) {
        // Simple collision detection based on hitbox
        int left = x;
        int right = x + hitboxWidth;
        int top = y;
        int bottom = y + hitboxWidth;

        // Check corners
        return isTileSolid(left, top, level) ||
                isTileSolid(right, top, level) ||
                isTileSolid(left, bottom, level) ||
                isTileSolid(right, bottom, level);
    }

    public boolean isTileSolid(int x, int y, Level level) {

        int tileX = x / GlobalData.tileWidth;
        int tileY = y / GlobalData.tileWidth;

        BaseTile tile = level.getTile(tileX, tileY);
        BaseTile backgroundTile = level.getBackgroundTile(tileX, tileY);
        if (backgroundTile == null) return true;
        return !((tile == null || tile.isWalkable()) && backgroundTile.isWalkable());
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }
}

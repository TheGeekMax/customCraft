package dev.toastcie.customcraft.screen.camera;

import dev.toastcie.customcraft.math.Rect;
import dev.toastcie.customcraft.math.Vector2;
import dev.toastcie.customcraft.screen.MainFrame;

public class CameraManager {
    private static CameraManager instance;
    public int tileWidth = 32;
    public int tileHeight = 32;
    private PlayerCamera playercamera;

    private int cameraX;
    private int cameraY;

    private CameraManager() {
        cameraX = 0;
        cameraY = 0;
    }

    public static CameraManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("CameraManager instance not initialized yet!");
        }
        return instance;
    }

    public static void initialize() {
        if (instance == null) instance = new CameraManager();
    }

    public void setPlayerCamera(PlayerCamera camera) {
        this.playercamera = camera;
    }

    public void calculateCaemeraPosition() {
        if (playercamera == null) return;

        //TODO calculate considering player position and screen size
        cameraX = playercamera.getX();
        cameraY = playercamera.getY();
    }

    public void setPosition(int x, int y) {
        if (playercamera == null) return;

        playercamera.setPosition(x, y);
        calculateCaemeraPosition();
    }

    public void move(int deltaX, int deltaY) {
        if (playercamera == null) return;

        playercamera.move(deltaX, deltaY);
        calculateCaemeraPosition();
    }


    public Vector2<Float> CanvasToCamera(int x, int y) {
        return new Vector2<>((x + cameraX * 1.0f) / tileWidth, (y + cameraY * 1.0f) / tileHeight);
    }

    public Vector2<Integer> CameraToCanvas(int x, int y) {
        return new Vector2<>(x * tileWidth - cameraX, y * tileHeight - cameraY);
    }

    public Rect<Integer> getCameraGridRect() {
        int left = cameraX / tileWidth;
        int top = cameraY / tileHeight;
        int right = (cameraX + MainFrame.instance.screenWidth) / tileWidth;
        int bottom = (cameraY + MainFrame.instance.screenHeight) / tileHeight;
        return new Rect<>(left, top, right - left, bottom - top);
    }


}

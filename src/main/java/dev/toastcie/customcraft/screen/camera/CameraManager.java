package dev.toastcie.customcraft.screen.camera;

import dev.toastcie.customcraft.data.GlobalData;
import dev.toastcie.customcraft.level.Level;
import dev.toastcie.customcraft.math.Rect;
import dev.toastcie.customcraft.math.Vector2;
import dev.toastcie.customcraft.screen.MainFrame;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class CameraManager {
    private static CameraManager instance;
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

        Vector2<Integer> pos = new Vector2<>(playercamera.getX(), playercamera.getY());

        //step 0, center on the player
        int tempcameraX = pos.getX() - MainFrame.instance.screenWidth / 2 + playercamera.getHitboxWidth() / 2;
        int tempcameraY = pos.getY() - MainFrame.instance.screenHeight / 2 + playercamera.getHitboxWidth() / 2;

        //step 1, cap to prevent overflow on top left (0,0)
        tempcameraX = max(0, tempcameraX);
        tempcameraY = max(0, tempcameraY);

        //step 2, cap to prevent overflow on bottom right
        Level active = GlobalData.getActiveLevel();
        Vector2<Integer> maxCoors = new Vector2<>(active.getWidth() * GlobalData.tileWidth - MainFrame.instance.screenWidth,
                active.getHeight() * GlobalData.tileWidth - MainFrame.instance.screenHeight);
        tempcameraX = min(tempcameraX, maxCoors.getX());
        tempcameraY = min(tempcameraY, maxCoors.getY());

        //set final position
        cameraX = tempcameraX;
        cameraY = tempcameraY;
    }

    public void setPosition(int x, int y) {
        if (playercamera == null) return;

        playercamera.setPosition(x, y);
        calculateCaemeraPosition();
    }

    public void move(int deltaX, int deltaY, Level level) {
        if (playercamera == null) return;

        playercamera.move(deltaX, deltaY, level);
        calculateCaemeraPosition();
    }


    public Vector2<Float> CanvasToCamera(int x, int y) {
        return new Vector2<>((x + cameraX * 1.0f) / GlobalData.tileWidth, (y + cameraY * 1.0f) / GlobalData.tileWidth);
    }

    public Vector2<Integer> CameraToCanvas(int x, int y) {
        return new Vector2<>(x * GlobalData.tileWidth - cameraX, y * GlobalData.tileWidth - cameraY);
    }

    public Rect<Integer> getCameraGridRect() {
        int left = cameraX / GlobalData.tileWidth;
        int top = cameraY / GlobalData.tileWidth;
        int right = (cameraX + MainFrame.instance.screenWidth) / GlobalData.tileWidth;
        int bottom = (cameraY + MainFrame.instance.screenHeight) / GlobalData.tileWidth;
        return new Rect<>(left, top, right - left, bottom - top);
    }

    public Vector2<Integer> CameraToGrid(int x, int y) {
        return new Vector2<>(x - cameraX, y - cameraY);
    }

    public int getPlayerBaseSpeed() {
        if (playercamera == null) return 0;
        return playercamera.getBaseSpeed();
    }
}

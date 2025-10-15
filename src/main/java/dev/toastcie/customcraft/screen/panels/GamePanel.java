package dev.toastcie.customcraft.screen.panels;

import dev.toastcie.customcraft.keyevents.Keyboard;
import dev.toastcie.customcraft.level.Generator;
import dev.toastcie.customcraft.level.Level;
import dev.toastcie.customcraft.level.generators.Superflat;
import dev.toastcie.customcraft.math.Rect;
import dev.toastcie.customcraft.math.Vector2;
import dev.toastcie.customcraft.math.Vector2Int;
import dev.toastcie.customcraft.screen.camera.CameraManager;
import dev.toastcie.customcraft.screen.camera.PlayerCamera;
import dev.toastcie.customcraft.tile.BaseTile;

import javax.swing.*;
import java.awt.*;

public class GamePanel implements ILoopPanel {

    public Level level;
    private CameraManager cameraManager;
    private PlayerCamera playerCamera;
    private Keyboard keyboard;
    private int speed = 5;

    public GamePanel() {
        this.cameraManager = CameraManager.getInstance();
        this.playerCamera = new PlayerCamera(0, 0);
        cameraManager.setPlayerCamera(playerCamera);

        this.keyboard = Keyboard.getInstance();

        //TODO remove this, and put it in level manager
        Generator gen = new Superflat();
        this.level = gen.generate(200, 200);

    }

    public void paint(Graphics g, JPanel panel) {
        Rect<Integer> view = cameraManager.getCameraGridRect();

        //checkerboard background
        int tileWidth = cameraManager.tileWidth;
        int tileHeight = cameraManager.tileHeight;

        int beginX = view.getX();
        int beginY = view.getY();
        int endX = view.getX() + view.getWidth();
        int endY = view.getY() + view.getHeight();
        for (int i = beginX - 1; i <= endX; i++) {
            for (int j = beginY - 1; j <= endY; j++) {
                BaseTile tile = level.getBackgroundTile(i, j);
                Vector2<Integer> canvasPos = cameraManager.CameraToCanvas(i, j);
                if (tile != null) {
                    g.drawImage(tile.getImage(level, i, j), canvasPos.getX(), canvasPos.getY(), tileWidth, tileHeight, null);
                } else {
                    //draw checkerboard
                    if ((i + j) % 2 == 0) {
                        g.setColor(new Color(200, 200, 200));
                    } else {
                        g.setColor(new Color(150, 150, 150));
                    }
                    g.fillRect(canvasPos.getX(), canvasPos.getY(), tileWidth, tileHeight);
                }
            }
        }
    }


    public void setLevel(Level level) {
        this.level = level;
    }

    public void loop() {
        Vector2Int direction = keyboard.movements.getDirection();
        //move player camera
        cameraManager.getInstance().move(direction.getX() * speed, direction.getY() * speed);
    }
}

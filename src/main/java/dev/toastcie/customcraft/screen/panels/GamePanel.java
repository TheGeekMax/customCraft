package dev.toastcie.customcraft.screen.panels;

import dev.toastcie.customcraft.data.GlobalData;
import dev.toastcie.customcraft.keyevents.Keyboard;
import dev.toastcie.customcraft.level.Generator;
import dev.toastcie.customcraft.level.Level;
import dev.toastcie.customcraft.level.generators.Checkerboard;
import dev.toastcie.customcraft.math.Rect;
import dev.toastcie.customcraft.math.Vector2;
import dev.toastcie.customcraft.math.Vector2Int;
import dev.toastcie.customcraft.screen.camera.CameraManager;
import dev.toastcie.customcraft.screen.camera.PlayerCamera;
import dev.toastcie.customcraft.tile.BaseTile;
import dev.toastcie.customcraft.tile.TileAtlas;

import javax.swing.*;
import java.awt.*;

public class GamePanel implements ILoopPanel {

    public Level level;
    private CameraManager cameraManager;
    private PlayerCamera playerCamera;
    private Keyboard keyboard;
    private int speed = 10;


    public GamePanel() {
        this.cameraManager = CameraManager.getInstance();
        this.playerCamera = new PlayerCamera(0, 0);
        cameraManager.setPlayerCamera(playerCamera);

        this.keyboard = Keyboard.getInstance();

        //TODO remove this, and put it in level manager
        Generator gen = new Checkerboard();
        this.level = gen.generate(50, 50);
        GlobalData.setActiveLevel(level);

    }

    public void paint(Graphics g, JPanel panel) {
        paintTerrain(g, panel);
        //draw user as a red box
        Vector2<Integer> playerCameraPos = cameraManager.CameraToCanvas(playerCamera.getX(), playerCamera.getY());
        g.setColor(Color.RED);
        Vector2<Integer> playerCameraGridPos = cameraManager.CameraToGrid(playerCamera.getX(), playerCamera.getY());
        g.fillRect(playerCameraGridPos.getX(), playerCameraGridPos.getY(), playerCamera.getHitboxWidth(), playerCamera.getHitboxWidth());
    }

    private void paintTerrain(Graphics g, JPanel panel) {
        Rect<Integer> view = cameraManager.getCameraGridRect();

        //checkerboard background
        int tileWidth = GlobalData.tileWidth;
        int tileHeight = GlobalData.tileWidth;

        int beginX = view.getX();
        int beginY = view.getY();
        int endX = view.getX() + view.getWidth();
        int endY = view.getY() + view.getHeight();

        int hwidth = tileWidth / 2;
        int hheight = tileHeight / 2;
        for (int i = beginX - 1; i <= endX; i++) {
            for (int j = beginY - 1; j <= endY; j++) {
                BaseTile bgtile = level.getBackgroundTile(i, j);
                BaseTile tile = level.getTile(i, j);
                Vector2<Integer> canvasPos = cameraManager.CameraToCanvas(i, j);
                if (bgtile != null) {
                    bgtile.place(g, level, canvasPos.getX(), canvasPos.getY(), i, j);
                } else {
                    //draw checkerboard
                    if ((i + j) % 2 == 0) {
                        g.setColor(new Color(200, 200, 200));
                    } else {
                        g.setColor(new Color(150, 150, 150));
                    }
                    g.fillRect(canvasPos.getX(), canvasPos.getY(), tileWidth, tileHeight);
                }
                if (tile != null) {
                    tile.place(g, level, canvasPos.getX(), canvasPos.getY(), i, j);
                }
            }
        }
    }

    @Override
    public void onClick(int mouseX, int mouseY) {
        Vector2<Float> canvasPos = cameraManager.CanvasToCamera(mouseX, mouseY);
        Vector2Int tilePos = new Vector2Int(canvasPos.getX().intValue(), canvasPos.getY().intValue());
        level.setBackgroundTile(tilePos.getX(), tilePos.getY(), TileAtlas.WATER_TILE);
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

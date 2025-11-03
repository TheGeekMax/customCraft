package dev.toastcie.customcraft.screen.showAnimation;

import dev.toastcie.customcraft.data.GlobalData;
import dev.toastcie.customcraft.tile.PictureManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UserPlayerAnimation implements IPlayerAnimation {

    private int playerFrame = 0;

    //sprites
    private BufferedImage topSprite_0;
    private BufferedImage topSprite_1;
    private BufferedImage bottomSprite_0;
    private BufferedImage bottomSprite_1;
    private BufferedImage leftSprite_0;
    private BufferedImage leftSprite_1;
    private BufferedImage rightSprite_0;
    private BufferedImage rightSprite_1;

    private BufferedImage topSpriteWater;
    private BufferedImage bottomSpriteWater;
    private BufferedImage leftSpriteWater;
    private BufferedImage rightSpriteWater;
    private BufferedImage waterOverlay;
    private BufferedImage lastSprite;
    private boolean isInWater = false;


    public UserPlayerAnimation(String topSpritePath_0, String topSpritePath_1,
                               String bottomSpritePath_0, String bottomSpritePath_1,
                               String leftSpritePath_0, String leftSpritePath_1,
                               String rightSpritePath_0, String rightSpritePath_1,

                               String topSpriteWaterPath, String bottomSpriteWaterPath,
                               String leftSpriteWaterPath, String rightSpriteWaterPath,
                               String waterOverlayPath) {
        //load sprites from paths
        topSprite_0 = PictureManager.instance.getImage(topSpritePath_0).getSprite();
        topSprite_1 = PictureManager.instance.getImage(topSpritePath_1).getSprite();
        bottomSprite_0 = PictureManager.instance.getImage(bottomSpritePath_0).getSprite();
        bottomSprite_1 = PictureManager.instance.getImage(bottomSpritePath_1).getSprite();
        leftSprite_0 = PictureManager.instance.getImage(leftSpritePath_0).getSprite();
        leftSprite_1 = PictureManager.instance.getImage(leftSpritePath_1).getSprite();
        rightSprite_0 = PictureManager.instance.getImage(rightSpritePath_0).getSprite();
        rightSprite_1 = PictureManager.instance.getImage(rightSpritePath_1).getSprite();

        //load water sprites
        topSpriteWater = PictureManager.instance.getImage(topSpriteWaterPath).getSprite();
        bottomSpriteWater = PictureManager.instance.getImage(bottomSpriteWaterPath).getSprite();
        leftSpriteWater = PictureManager.instance.getImage(leftSpriteWaterPath).getSprite();
        rightSpriteWater = PictureManager.instance.getImage(rightSpriteWaterPath).getSprite();
        waterOverlay = PictureManager.instance.getImage(waterOverlayPath).getSprite();


        lastSprite = bottomSprite_0;
    }

    @Override
    public void move(int dx, int dy, boolean inWater) {
        playerFrame = (playerFrame + 1) % 20;
        isInWater = inWater;
        if (inWater) {
            // Determine direction and set lastSprite accordingly for water sprites
            if (dx > 0) { // Moving right
                lastSprite = rightSpriteWater;
            } else if (dx < 0) { // Moving left
                lastSprite = leftSpriteWater;
            } else if (dy > 0) { // Moving down
                lastSprite = bottomSpriteWater;
            } else if (dy < 0) { // Moving up
                lastSprite = topSpriteWater;
            }
            return;
        }

        // Determine direction and set lastSprite accordingly
        if (dx > 0) { // Moving right
            lastSprite = (playerFrame < 10) ? rightSprite_0 : rightSprite_1;
        } else if (dx < 0) { // Moving left
            lastSprite = (playerFrame < 10) ? leftSprite_0 : leftSprite_1;
        } else if (dy > 0) { // Moving down
            lastSprite = (playerFrame < 10) ? bottomSprite_0 : bottomSprite_1;
        } else if (dy < 0) { // Moving up
            lastSprite = (playerFrame < 10) ? topSprite_0 : topSprite_1;
        }
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        //calculate dx and dy based on hitbox
        int hitboxOffset = (int) ((1 - GlobalData.hitboxScale) / 2 * GlobalData.tileWidth);
        int xOverlay = x - hitboxOffset;
        int yOverlay = y - hitboxOffset;
        if (isInWater) {
            g.drawImage(waterOverlay, xOverlay, yOverlay + (GlobalData.tileWidth / 4), GlobalData.tileWidth, GlobalData.tileWidth / 2, null);
            g.drawImage(lastSprite, xOverlay, yOverlay, GlobalData.tileWidth, GlobalData.tileWidth / 2, null);
        } else {
            g.drawImage(lastSprite, xOverlay, yOverlay, GlobalData.tileWidth, GlobalData.tileWidth, null);
        }
    }
}

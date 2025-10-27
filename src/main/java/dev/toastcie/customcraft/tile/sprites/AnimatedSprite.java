package dev.toastcie.customcraft.tile.sprites;

import dev.toastcie.customcraft.data.GlobalData;

import java.awt.image.BufferedImage;

public class AnimatedSprite implements ISprite {
    private final BufferedImage[] sprites;
    private final int timePerFrame;
    private final int spriteCount;

    public AnimatedSprite(int timePerFrame, BufferedImage... sprites) {
        this.sprites = sprites;
        this.timePerFrame = timePerFrame;
        this.spriteCount = sprites.length;
    }

    @Override
    public BufferedImage getSprite() {
        return this.sprites[((GlobalData.timer / timePerFrame) % spriteCount)];
    }
}

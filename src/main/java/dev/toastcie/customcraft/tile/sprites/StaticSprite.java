package dev.toastcie.customcraft.tile.sprites;

import java.awt.image.BufferedImage;

public class StaticSprite implements ISprite {
    private final BufferedImage sprite;

    public StaticSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    @Override
    public BufferedImage getSprite() {
        return this.sprite;
    }
}

package dev.toastcie.customcraft.tile;

import dev.toastcie.customcraft.level.Level;

import java.awt.image.BufferedImage;

public abstract class BaseTile {

    public final String id;
    public BufferedImage image;

    public BaseTile(String id, BufferedImage image) {
        this.id = id;
        this.image = image;
    }

    public String getId() {
        return this.id;
    }

    public BufferedImage getImage(Level level, int x, int y) {
        return this.image;
    }

}

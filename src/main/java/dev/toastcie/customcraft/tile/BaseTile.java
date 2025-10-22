package dev.toastcie.customcraft.tile;

import dev.toastcie.customcraft.level.Level;
import dev.toastcie.customcraft.math.Vector4;
import dev.toastcie.customcraft.tile.sprites.ISprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BaseTile {

    public final String id;
    protected final Vector4<ISprite> image;

    public BaseTile(String id, ISprite tl, ISprite tr, ISprite bl, ISprite br) {
        this.id = id;
        this.image = new Vector4<>(tl, tr, bl, br);
    }

    public String getId() {
        return this.id;
    }

    protected Vector4<BufferedImage> getImage(Level level, int x, int y) {
        return this.image.map(ISprite::getSprite);
    }


    public abstract void place(Graphics g, Level level, int x, int y, int i, int j);
}

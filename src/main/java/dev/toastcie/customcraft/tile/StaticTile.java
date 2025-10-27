package dev.toastcie.customcraft.tile;

import dev.toastcie.customcraft.data.GlobalData;
import dev.toastcie.customcraft.level.Level;
import dev.toastcie.customcraft.tile.sprites.ISprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class StaticTile extends BaseTile {
    private final BufferedImage image;

    public StaticTile(String id, ISprite tl, ISprite tr, ISprite bl, ISprite br) {
        super(id, tl, tr, bl, br);
        image = PictureManager.reconstructImage(tl.getSprite(), tr.getSprite(), bl.getSprite(), br.getSprite());
    }

    @Override
    public void place(Graphics g, Level level, int x, int y, int i, int j) {
        g.drawImage(image, x, y, GlobalData.tileWidth, GlobalData.tileWidth, null);
    }


}

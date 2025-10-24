package dev.toastcie.customcraft.tile;

import dev.toastcie.customcraft.data.GlobalData;
import dev.toastcie.customcraft.level.Level;
import dev.toastcie.customcraft.math.Vector4;
import dev.toastcie.customcraft.tile.sprites.ISprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ClasicTile extends BaseTile {

    public ClasicTile(String id, ISprite tl, ISprite tr, ISprite bl, ISprite br) {
        super(id, tl, tr, bl, br);
    }

    @Override
    public void place(Graphics g, Level level, int x, int y, int i, int j) {
        Vector4<BufferedImage> tiles = getImage(level, i, j);
        int hwidth = GlobalData.tileWidth / 2;
        g.drawImage(tiles.x, x, y, hwidth, hwidth, null);
        g.drawImage(tiles.y, x + hwidth, y, hwidth, hwidth, null);
        g.drawImage(tiles.z, x, y + hwidth, hwidth, hwidth, null);
        g.drawImage(tiles.w, x + hwidth, y + hwidth, hwidth, hwidth, null);
    }
}

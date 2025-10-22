package dev.toastcie.customcraft.tile.tiletype;

import dev.toastcie.customcraft.data.GlobalData;
import dev.toastcie.customcraft.level.Level;
import dev.toastcie.customcraft.math.Vector4;
import dev.toastcie.customcraft.tile.BaseTile;
import dev.toastcie.customcraft.tile.PictureManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GrassTile extends BaseTile {
    public GrassTile() {
        super("grass",
                PictureManager.instance.getImage("grass_0"),
                PictureManager.instance.getImage("grass_1"),
                PictureManager.instance.getImage("grass_2"),
                PictureManager.instance.getImage("grass_4")
        );
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

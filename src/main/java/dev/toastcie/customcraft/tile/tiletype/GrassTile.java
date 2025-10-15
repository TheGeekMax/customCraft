package dev.toastcie.customcraft.tile.tiletype;

import dev.toastcie.customcraft.tile.BaseTile;
import dev.toastcie.customcraft.tile.PictureManager;

public class GrassTile extends BaseTile {
    public GrassTile() {
        super("grass", PictureManager.reconstructImage(
                PictureManager.instance.getImage("grass_0"),
                PictureManager.instance.getImage("grass_1"),
                PictureManager.instance.getImage("grass_2"),
                PictureManager.instance.getImage("grass_3")
        ));
    }

}

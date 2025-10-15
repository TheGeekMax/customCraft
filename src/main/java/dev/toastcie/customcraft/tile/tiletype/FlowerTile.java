package dev.toastcie.customcraft.tile.tiletype;

import dev.toastcie.customcraft.tile.BaseTile;
import dev.toastcie.customcraft.tile.PictureManager;

public class FlowerTile extends BaseTile {

    public FlowerTile() {
        super("flower", PictureManager.reconstructImage(
                PictureManager.instance.getImage("flower"),
                PictureManager.instance.getImage("grass_0"),
                PictureManager.instance.getImage("grass_1"),
                PictureManager.instance.getImage("flower")
        ));
    }
}

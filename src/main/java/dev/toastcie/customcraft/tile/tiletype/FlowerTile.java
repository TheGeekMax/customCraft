package dev.toastcie.customcraft.tile.tiletype;

import dev.toastcie.customcraft.tile.PictureManager;
import dev.toastcie.customcraft.tile.StaticTile;

public class FlowerTile extends StaticTile {

    public FlowerTile() {
        super("flower",
                PictureManager.instance.getImage("flower"),
                PictureManager.instance.getImage("grass_0"),
                PictureManager.instance.getImage("grass_1"),
                PictureManager.instance.getImage("flower")
        );
    }
}

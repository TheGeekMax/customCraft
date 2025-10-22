package dev.toastcie.customcraft.tile.tiletype;

import dev.toastcie.customcraft.tile.PictureManager;
import dev.toastcie.customcraft.tile.StaticTile;

public class GrassTile extends StaticTile {
    public GrassTile() {
        super("grass",
                PictureManager.instance.getImage("grass_0"),
                PictureManager.instance.getImage("grass_1"),
                PictureManager.instance.getImage("grass_2"),
                PictureManager.instance.getImage("grass_3")
        );
    }

}

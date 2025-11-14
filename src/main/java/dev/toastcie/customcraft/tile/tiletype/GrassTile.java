package dev.toastcie.customcraft.tile.tiletype;

import dev.toastcie.customcraft.annotations.tile.TileInformation;
import dev.toastcie.customcraft.tile.ConnectedTile;
import dev.toastcie.customcraft.tile.PictureManager;
import dev.toastcie.customcraft.tile.rules.TileRules;

@TileInformation("GRASS_TILE")
public class GrassTile extends ConnectedTile {
    public GrassTile() {
        super("grass",
                TileRules.basicConnectedRules(
                        "grass",

                        PictureManager.instance.getImage("grass_0"),
                        PictureManager.instance.getImage("grass_1"),
                        PictureManager.instance.getImage("grass_2"),
                        PictureManager.instance.getImage("grass_3"),


                        // side sprites
                        PictureManager.instance.getImage("grass_t"),
                        PictureManager.instance.getImage("grass_r"),
                        PictureManager.instance.getImage("grass_b"),
                        PictureManager.instance.getImage("grass_l"),

                        // corner sprites
                        PictureManager.instance.getImage("grass_tl"),
                        PictureManager.instance.getImage("grass_tr"),
                        PictureManager.instance.getImage("grass_br"),
                        PictureManager.instance.getImage("grass_bl"),

                        PictureManager.instance.getImage("grass_0"),
                        false
                )
        );
    }
}

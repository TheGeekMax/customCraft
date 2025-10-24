package dev.toastcie.customcraft.tile.tiletype;

import dev.toastcie.customcraft.tile.ConnectedTile;
import dev.toastcie.customcraft.tile.PictureManager;
import dev.toastcie.customcraft.tile.rules.TileRules;

public class WaterTile extends ConnectedTile {

    public WaterTile() {
        super("water",
                TileRules.basicConnectedRules(
                        "water",
                        PictureManager.instance.getImage("water_anim_0"),
                        PictureManager.instance.getImage("water_flat"),
                        PictureManager.instance.getImage("water_flat"),
                        PictureManager.instance.getImage("water_anim_1"),

                        PictureManager.instance.getImage("water_t"),
                        PictureManager.instance.getImage("water_r"),
                        PictureManager.instance.getImage("water_b"),
                        PictureManager.instance.getImage("water_l"),

                        PictureManager.instance.getImage("water_tl"),
                        PictureManager.instance.getImage("water_tr"),
                        PictureManager.instance.getImage("water_br"),
                        PictureManager.instance.getImage("water_bl"),

                        PictureManager.instance.getImage("water_flat"),
                        false
                )
        );
    }
}

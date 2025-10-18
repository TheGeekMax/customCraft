package dev.toastcie.customcraft.tile.tiletype;

import dev.toastcie.customcraft.tile.ConnectedTile;
import dev.toastcie.customcraft.tile.PictureManager;
import dev.toastcie.customcraft.tile.rules.TileRules;

public class TreeTile extends ConnectedTile {
    public TreeTile() {
        super(
                "tree",
                null,
                TileRules.treeRulesBuilder(
                        "tree",
                        PictureManager.instance.getImage("tree_none_top_left"),
                        PictureManager.instance.getImage("tree_none_top_right"),
                        PictureManager.instance.getImage("tree_none_bottom_left"),
                        PictureManager.instance.getImage("tree_none_bottom_right"),
                        PictureManager.instance.getImage("tree_connected_tl_br"),
                        PictureManager.instance.getImage("tree_connected_tr_bl"),
                        PictureManager.instance.getImage("tree_connected_tr_bl"),
                        PictureManager.instance.getImage("tree_connected_tl_br")
                )
        );
    }
}

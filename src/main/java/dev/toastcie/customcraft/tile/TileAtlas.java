package dev.toastcie.customcraft.tile;

import dev.toastcie.customcraft.tile.tiletype.FlowerTile;
import dev.toastcie.customcraft.tile.tiletype.GrassTile;
import dev.toastcie.customcraft.tile.tiletype.TreeTile;
import dev.toastcie.customcraft.tile.tiletype.WaterTile;

public class TileAtlas {
    public static TileAtlas instance;
    public static BaseTile GRASS_TILE;
    public static BaseTile FLOWER_TILE;
    public static BaseTile TREE_TILE;

    public static BaseTile WATER_TILE;


    private TileAtlas() {
        instance = this;

        //generate all tiles here
        GRASS_TILE = new GrassTile();
        FLOWER_TILE = new FlowerTile();
        TREE_TILE = new TreeTile();
        WATER_TILE = new WaterTile();
    }

    public static void initialize() {
        if (instance == null) {
            new TileAtlas();
        }
    }

}

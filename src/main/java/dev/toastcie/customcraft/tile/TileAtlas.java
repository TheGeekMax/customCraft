package dev.toastcie.customcraft.tile;

import dev.toastcie.customcraft.tile.tiletype.FlowerTile;
import dev.toastcie.customcraft.tile.tiletype.GrassTile;

public class TileAtlas {
    public static TileAtlas instance;
    public static BaseTile GRASS_TILE;
    public static BaseTile FLOWER_TILE;


    private TileAtlas() {
        instance = this;

        //generate all tiles here
        GRASS_TILE = new GrassTile();
        FLOWER_TILE = new FlowerTile();
    }

    public static void initialize() {
        if (instance == null) {
            new TileAtlas();
        }
    }

}

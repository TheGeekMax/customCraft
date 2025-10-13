package dev.toastcie.customcraft.level;

import dev.toastcie.customcraft.tile.Tile;

public abstract class Generator {
    protected Level level;
    protected int tileWidth;
    protected int tileHeight;

    
    protected Tile[][] tiles;
    protected Tile[][] BackGroundTiles;

    public Generator() {

    }
}

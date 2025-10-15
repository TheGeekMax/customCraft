package dev.toastcie.customcraft.level;

import dev.toastcie.customcraft.tile.BaseTile;

public class Level {
    private BaseTile[][] baseTiles;
    private BaseTile[][] backGroundBaseTiles;
    private int width;
    private int height;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        this.baseTiles = new BaseTile[width][height];
        this.backGroundBaseTiles = new BaseTile[width][height];
    }

    public BaseTile getTile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return this.baseTiles[x][y];
    }

    public void setTile(int x, int y, BaseTile tile) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return;
        }
        this.baseTiles[x][y] = tile;
    }

    public BaseTile getBackgroundTile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return this.backGroundBaseTiles[x][y];
    }

    public void setBackgroundTile(int x, int y, BaseTile tile) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return;
        }
        this.backGroundBaseTiles[x][y] = tile;
    }
}

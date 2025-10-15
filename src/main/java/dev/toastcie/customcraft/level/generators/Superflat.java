package dev.toastcie.customcraft.level.generators;

import dev.toastcie.customcraft.level.Generator;
import dev.toastcie.customcraft.level.Level;
import dev.toastcie.customcraft.tile.TileAtlas;

public class Superflat extends Generator {
    @Override
    public Level generate(int width, int height) {
        Level level = new Level(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Example: Fill the level with a single tile type (e.g., grass)
                level.setBackgroundTile(x, y, TileAtlas.GRASS_TILE);
            }
        }
        level.setBackgroundTile(1, 1, TileAtlas.FLOWER_TILE);
        level.setBackgroundTile(1, 3, TileAtlas.FLOWER_TILE);
        level.setBackgroundTile(2, 2, TileAtlas.FLOWER_TILE);
        level.setBackgroundTile(3, 2, TileAtlas.FLOWER_TILE);
        level.setBackgroundTile(4, 2, TileAtlas.FLOWER_TILE);
        level.setBackgroundTile(5, 2, TileAtlas.FLOWER_TILE);
        level.setBackgroundTile(6, 2, TileAtlas.FLOWER_TILE);
        level.setBackgroundTile(7, 2, TileAtlas.FLOWER_TILE);


        this.level = level;
        return level;
    }
}

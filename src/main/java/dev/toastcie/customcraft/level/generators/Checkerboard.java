package dev.toastcie.customcraft.level.generators;

import dev.toastcie.customcraft.level.Generator;
import dev.toastcie.customcraft.level.Level;
import dev.toastcie.customcraft.tile.TileAtlas;

public class Checkerboard extends Generator {
    @Override
    public Level generate(int width, int height) {
        Level level = new Level(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if ((x + y) % 2 == 0)
                    level.setBackgroundTile(x, y, TileAtlas.WATER_TILE);
                else
                    level.setBackgroundTile(x, y, TileAtlas.GRASS_TILE);
            }
        }

        this.level = level;
        return level;
    }
}

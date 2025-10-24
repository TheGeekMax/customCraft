package dev.toastcie.customcraft.level.generators;

import dev.toastcie.customcraft.level.Generator;
import dev.toastcie.customcraft.level.Level;
import dev.toastcie.customcraft.tile.BaseTile;
import dev.toastcie.customcraft.tile.TileAtlas;

public class Superflat extends Generator {
    @Override
    public Level generate(int width, int height) {
        Level level = new Level(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                level.setBackgroundTile(x, y, TileAtlas.GRASS_TILE);
            }
        }

        //TODO supprimer
        BaseTile G = TileAtlas.GRASS_TILE;
        BaseTile F = TileAtlas.FLOWER_TILE;
        BaseTile T = TileAtlas.TREE_TILE;
        BaseTile W = TileAtlas.WATER_TILE;
        BaseTile E = null;


        BaseTile[][] fg = {
                {E, E, E, E, E, E, E, E, E, E},
                {E, E, E, E, E, E, E, E, E, E},
                {E, T, T, T, T, E, E, E, E, E},
                {E, T, T, T, T, E, E, E, E, E},
                {E, E, E, T, T, E, E, F, F, E},
                {E, E, E, E, E, E, F, E, F, E},
        };

        BaseTile[][] bg = {
                {G, G, G, G, G, G, G, G, G, G},
                {G, W, W, W, W, W, W, W, W, G},
                {G, G, G, G, G, G, G, W, W, G},
                {G, G, G, G, G, G, G, G, G, G},
                {G, G, G, G, G, G, G, G, G, G},
                {G, G, G, G, G, G, G, G, G, G},
        };

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                level.setTile(x, y, fg[y % fg.length][x % fg[0].length]);
                level.setBackgroundTile(x, y, bg[y % bg.length][x % bg[0].length]);
            }
        }


        this.level = level;
        return level;
    }
}

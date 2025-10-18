package dev.toastcie.customcraft.tile;

import dev.toastcie.customcraft.level.Level;
import dev.toastcie.customcraft.tile.rules.TileRules;

import java.awt.image.BufferedImage;

public abstract class ConnectedTile extends BaseTile {
    private TileRules rules;

    public ConnectedTile(String id, BufferedImage baseImage, TileRules rules) {
        super(id, baseImage);
        this.rules = rules;
    }

    @Override
    public BufferedImage getImage(Level level, int x, int y) {
        return rules.getFullImage(level, x, y);
    }
}

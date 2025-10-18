package dev.toastcie.customcraft.tile.rules;

import dev.toastcie.customcraft.level.Level;
import dev.toastcie.customcraft.tile.BaseTile;

import java.awt.image.BufferedImage;

public class TileRule implements Comparable<TileRule> {
    int weight;
    BufferedImage image;

    /**
     * binary code representing the neighboring tiles configuration
     * <p>
     * 0 1 2
     * 3 X 4
     * 5 6 7
     * </p>
     */
    int binaryCode;
    /**
     * the tile ID that this rule is checking for in neighboring tiles
     */
    String tileId;

    /**
     * indicates whether this rule applies to foreground (true) or background (false) tiles
     */
    boolean fg;

    public TileRule(BufferedImage image, int binaryCode, String tileId, int weight, boolean fg) {
        this.weight = weight;
        this.image = image;
        this.binaryCode = binaryCode;
        this.tileId = tileId;
        this.fg = fg;
    }

    public static TileRule builder(BufferedImage image, int binaryCode, String tileId, int weight, boolean fg) {
        return new TileRule(image, binaryCode, tileId, weight, fg);
    }

    public int getWeight() {
        return weight;
    }

    public BufferedImage getImage() {
        return image;
    }

    private String getTileId(Level level, int x, int y) {
        BaseTile tile = fg ? level.getTile(x, y) : level.getBackgroundTile(x, y);
        return tile != null ? tile.getId() : "";
    }

    public boolean valid(Level level, int x, int y) {
        int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
        int[] dy = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
        for (int i = 0; i < 9; i++) {
            if (i == 4) continue; // skip center

            boolean shouldMatch = ((binaryCode >> i) & 1) == 1;
            if (!shouldMatch) continue;
            String tileIdAtNeighbor = getTileId(level, x + dx[i], y + dy[i]);
            if (!tileIdAtNeighbor.equals(this.tileId)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(TileRule other) {
        return Integer.compare(this.weight, other.weight);
    }
}

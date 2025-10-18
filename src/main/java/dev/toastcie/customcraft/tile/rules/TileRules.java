package dev.toastcie.customcraft.tile.rules;

import dev.toastcie.customcraft.level.Level;
import dev.toastcie.customcraft.math.Vector4;
import dev.toastcie.customcraft.tile.PictureManager;
import dev.toastcie.customcraft.utils.SortedList;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TileRules {
    public Map<Vector4<Integer>, BufferedImage> cachedImages = new TreeMap<>();
    private List<TileRule> topLeftRules;
    private List<TileRule> topRightRules;
    private List<TileRule> bottomLeftRules;
    private List<TileRule> bottomRightRules;
    private BufferedImage defaultTopLeft;
    private BufferedImage defaultTopRight;
    private BufferedImage defaultBottomLeft;
    private BufferedImage defaultBottomRight;

    public TileRules(
            BufferedImage defaultTopLeft,
            BufferedImage defaultTopRight,
            BufferedImage defaultBottomLeft,
            BufferedImage defaultBottomRight
    ) {
        this.topLeftRules = new SortedList<>();
        this.topRightRules = new SortedList<>();
        this.bottomLeftRules = new SortedList<>();
        this.bottomRightRules = new SortedList<>();

        this.defaultTopLeft = defaultTopLeft;
        this.defaultTopRight = defaultTopRight;
        this.defaultBottomLeft = defaultBottomLeft;
        this.defaultBottomRight = defaultBottomRight;
    }

    public static TileRules treeRulesBuilder(
            String TreeId,
            
            BufferedImage defaultTopLeft,
            BufferedImage defaultTopRight,
            BufferedImage defaultBottomLeft,
            BufferedImage defaultBottomRight,

            BufferedImage linkedTopLeft,
            BufferedImage linkedTopRight,
            BufferedImage linkedBottomLeft,
            BufferedImage linkedBottomRight
    ) {
        TileRules treeRules = new TileRules(
                defaultTopLeft,
                defaultTopRight,
                defaultBottomLeft,
                defaultBottomRight);

        treeRules.addTopLeftRule(TileRule.builder(linkedTopLeft, 0b000_001_011, TreeId, 1, true))
                .addTopRightRule(TileRule.builder(linkedTopRight, 0b000_100_110, TreeId, 1, true))
                .addBottomLeftRule(TileRule.builder(linkedBottomLeft, 0b011_001_000, TreeId, 1, true))
                .addBottomRightRule(TileRule.builder(linkedBottomRight, 0b110_100_000, TreeId, 1, true));

        return treeRules.build();
    }

    public TileRules addTopLeftRule(TileRule... rules) {
        this.topLeftRules.addAll(Arrays.asList(rules));
        return this;
    }

    public TileRules addTopRightRule(TileRule... rules) {
        this.topRightRules.addAll(Arrays.asList(rules));
        return this;
    }

    public TileRules addBottomLeftRule(TileRule... rules) {
        this.bottomLeftRules.addAll(Arrays.asList(rules));
        return this;
    }

    public TileRules addBottomRightRule(TileRule... rules) {
        this.bottomRightRules.addAll(Arrays.asList(rules));
        return this;
    }

    public BufferedImage getFullImage(Level level, int x, int y) {
        int topLeft = -1;
        int topRight = -1;
        int bottomLeft = -1;
        int bottomRight = -1;

        for (int i = 0; i < topLeftRules.size(); i++) {
            if (topLeftRules.get(i).valid(level, x, y)) {
                topLeft = i;
                break;
            }
        }

        for (int i = 0; i < topRightRules.size(); i++) {
            if (topRightRules.get(i).valid(level, x, y)) {
                topRight = i;
                break;
            }
        }

        for (int i = 0; i < bottomLeftRules.size(); i++) {
            if (bottomLeftRules.get(i).valid(level, x, y)) {
                bottomLeft = i;
                break;
            }
        }

        for (int i = 0; i < bottomRightRules.size(); i++) {
            if (bottomRightRules.get(i).valid(level, x, y)) {
                bottomRight = i;
                break;
            }
        }

        return cachedImages.get(new Vector4<>(topLeft, topRight, bottomLeft, bottomRight));
    }

    public TileRules build() {
        //generate all possible combinations of neighboring tiles (2^8 = 256)
        for (int i = -1; i < topLeftRules.size(); i++) {
            for (int j = -1; j < topRightRules.size(); j++) {
                for (int k = -1; k < bottomLeftRules.size(); k++) {
                    for (int l = -1; l < bottomRightRules.size(); l++) {
                        Vector4<Integer> key = new Vector4<>(i, j, k, l);

                        BufferedImage topLeft = (i == -1) ? defaultTopLeft : topLeftRules.get(i).getImage();
                        BufferedImage topRight = (j == -1) ? defaultTopRight : topRightRules.get(j).getImage();
                        BufferedImage bottomLeft = (k == -1) ? defaultBottomLeft : bottomLeftRules.get(k).getImage();
                        BufferedImage bottomRight = (l == -1) ? defaultBottomRight : bottomRightRules.get(l).getImage();

                        BufferedImage fullImage = PictureManager.reconstructImage(topLeft, topRight, bottomLeft, bottomRight);
                        cachedImages.put(key, fullImage);
                    }
                }
            }
        }
        return this;
    }


}

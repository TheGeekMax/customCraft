package dev.toastcie.customcraft.tile;

import dev.toastcie.customcraft.math.Rect;
import dev.toastcie.customcraft.tile.sprites.AnimatedSprite;
import dev.toastcie.customcraft.tile.sprites.ISprite;
import dev.toastcie.customcraft.tile.sprites.StaticSprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class PictureManager {
    public static PictureManager instance;

    BufferedImage mainImage;
    Map<String, ISprite> images = new HashMap<>();

    private PictureManager(InputStream stream) throws IOException {
        if (instance != null) {
            throw new IllegalStateException("PictureManager instance already exists!");
        }
        instance = this;
        mainImage = ImageIO.read(stream);
        initializePictures();
    }

    public static void initialize(InputStream stream) {
        try {
            if (instance == null) {
                instance = new PictureManager(stream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage reconstructImage(BufferedImage topleft, BufferedImage topright, BufferedImage bottomleft, BufferedImage bottomright) {
        int width = topleft.getWidth() + topright.getWidth();
        int height = topleft.getHeight() + bottomleft.getHeight();
        BufferedImage combined = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        combined.createGraphics().drawImage(topleft, 0, 0, null);
        combined.createGraphics().drawImage(topright, topleft.getWidth(), 0, null);
        combined.createGraphics().drawImage(bottomleft, 0, topleft.getHeight(), null);
        combined.createGraphics().drawImage(bottomright, topleft.getWidth(), topleft.getHeight(), null);

        return combined;
    }

    public ISprite getImage(String name) {
        return images.get(name);
    }

    private void addStaticImage(String name, Rect<Integer> rect) {
        BufferedImage img = mainImage.getSubimage(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        images.put(name, new StaticSprite(img));
    }

    private void addAnimatedImage(String name, int timePerFrame, Rect<Integer>... rect) {
        BufferedImage[] frames = new BufferedImage[rect.length];
        for (int i = 0; i < rect.length; i++) {
            Rect<Integer> r = rect[i];
            frames[i] = mainImage.getSubimage(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        }
        images.put(name, new AnimatedSprite(timePerFrame, frames));
    }

    private void initializePictures() {
        //basic tiles
        addStaticImage("grass_0", new Rect<>(0, 0, 8, 8));
        addStaticImage("grass_1", new Rect<>(8, 0, 8, 8));
        addStaticImage("grass_2", new Rect<>(16, 0, 8, 8));
        addStaticImage("grass_3", new Rect<>(24, 0, 8, 8));

        addStaticImage("grass_tl", new Rect<>(88, 0, 8, 8));
        addStaticImage("grass_t", new Rect<>(96, 0, 8, 8));
        addStaticImage("grass_tr", new Rect<>(104, 0, 8, 8));
        addStaticImage("grass_l", new Rect<>(88, 8, 8, 8));
        addStaticImage("grass_r", new Rect<>(104, 8, 8, 8));
        addStaticImage("grass_bl", new Rect<>(88, 16, 8, 8));
        addStaticImage("grass_b", new Rect<>(96, 16, 8, 8));
        addStaticImage("grass_br", new Rect<>(104, 16, 8, 8));

        addStaticImage("flower", new Rect<>(8, 8, 8, 8));

        //tree
        addStaticImage("tree_none_top_left", new Rect<>(72, 0, 8, 8));
        addStaticImage("tree_none_top_right", new Rect<>(80, 0, 8, 8));
        addStaticImage("tree_none_bottom_left", new Rect<>(72, 8, 8, 8));
        addStaticImage("tree_none_bottom_right", new Rect<>(80, 24, 8, 8));

        addStaticImage("tree_connected_tl_br", new Rect<>(80, 8, 8, 8));
        addStaticImage("tree_connected_tr_bl", new Rect<>(80, 16, 8, 8));

        //water
        addStaticImage("water_tl", new Rect<>(112, 0, 8, 8));
        addStaticImage("water_t", new Rect<>(120, 0, 8, 8));
        addStaticImage("water_tr", new Rect<>(128, 0, 8, 8));
        addStaticImage("water_l", new Rect<>(112, 8, 8, 8));
        addStaticImage("water_flat", new Rect<>(120, 8, 8, 8));
        addStaticImage("water_r", new Rect<>(128, 8, 8, 8));
        addStaticImage("water_bl", new Rect<>(112, 16, 8, 8));
        addStaticImage("water_b", new Rect<>(120, 16, 8, 8));
        addStaticImage("water_br", new Rect<>(128, 16, 8, 8));

        addAnimatedImage("water_anim_0", 30,
                new Rect<>(136, 0, 8, 8),
                new Rect<>(152, 0, 8, 8),
                new Rect<>(144, 0, 8, 8),
                new Rect<>(160, 0, 8, 8));

        addAnimatedImage("water_anim_1", 30,
                new Rect<>(144, 0, 8, 8),
                new Rect<>(160, 0, 8, 8),
                new Rect<>(136, 0, 8, 8),
                new Rect<>(152, 0, 8, 8));

        //player
        addStaticImage("player_down_0", new Rect<>(0, 112, 16, 16));
        addStaticImage("player_down_1", new Rect<>(0, 128, 16, 16));
        addStaticImage("player_up_0", new Rect<>(16, 112, 16, 16));
        addStaticImage("player_up_1", new Rect<>(16, 128, 16, 16));
        addStaticImage("player_right_0", new Rect<>(32, 112, 16, 16));
        addStaticImage("player_right_1", new Rect<>(48, 112, 16, 16));
        addStaticImage("player_left_0", new Rect<>(32, 128, 16, 16));
        addStaticImage("player_left_1", new Rect<>(48, 128, 16, 16));

        addStaticImage("player_down_water", new Rect<>(0, 112, 16, 8));
        addStaticImage("player_up_water", new Rect<>(16, 112, 16, 8));
        addStaticImage("player_right_water", new Rect<>(32, 112, 16, 8));
        addStaticImage("player_left_water", new Rect<>(32, 128, 16, 8));
        addStaticImage("water_splash", new Rect<>(32, 104, 16, 8));

        //basics items
        addStaticImage("flowers_item", new Rect<>(0, 32, 8, 8));

    }
}

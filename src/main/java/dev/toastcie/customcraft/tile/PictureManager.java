package dev.toastcie.customcraft.tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class PictureManager {
    public static PictureManager instance;

    BufferedImage mainImage;
    Map<String, BufferedImage> images = new HashMap<>();

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

    public BufferedImage getImage(String name) {
        return images.get(name);
    }

    private void addImage(String name, int x, int y, int width, int height) {
        BufferedImage img = mainImage.getSubimage(x, y, width, height);
        images.put(name, img);
    }

    private void initializePictures() {
        //basic tiles
        addImage("grass_0", 0, 0, 8, 8);
        addImage("grass_1", 8, 0, 8, 8);
        addImage("grass_2", 16, 0, 8, 8);
        addImage("grass_3", 24, 0, 8, 8);

        addImage("flower", 8, 8, 8, 8);

        //tree
        addImage("tree_none_top_left", 72, 0, 8, 8);
        addImage("tree_none_top_right", 80, 0, 8, 8);
        addImage("tree_none_bottom_left", 72, 8, 8, 8);
        addImage("tree_none_bottom_right", 80, 24, 8, 8);

        addImage("tree_connected_tl_br", 80, 8, 8, 8);
        addImage("tree_connected_tr_bl", 80, 16, 8, 8);
    }
}

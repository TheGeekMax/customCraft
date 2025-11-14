package dev.toastcie.customcraft.tile;

import dev.toastcie.customcraft.annotations.tile.TileInformation;
import io.github.classgraph.ClassGraph;

import java.util.HashMap;
import java.util.Map;

public class TileAtlas {
    public static TileAtlas instance;
    public static Map<String, BaseTile> tiles;


    private TileAtlas() {
        instance = this;
        tiles = new HashMap<>();

        //get all classes with LoopPanel annotation
        var scanResult = new ClassGraph()
                .enableAllInfo()
                .acceptPackages("dev.toastcie.customcraft.tile.tiletype")
                .scan();
        var classes = scanResult.getClassesWithAnnotation(TileInformation.class.getName());
        for (var classInfo : classes) {
            try {
                Class<?> cls = Class.forName(classInfo.getName());
                TileInformation annotation = cls.getAnnotation(TileInformation.class);
                BaseTile sceneInstance = (BaseTile) cls.getDeclaredConstructor().newInstance();
                tiles.put(annotation.value(), sceneInstance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void initialize() {
        if (instance == null) {
            new TileAtlas();
        }
    }

    public static BaseTile get(String name) {
        return tiles.get(name);
    }

}

package dev.toastcie.customcraft;

import dev.toastcie.customcraft.keyevents.Keyboard;
import dev.toastcie.customcraft.screen.MainFrame;
import dev.toastcie.customcraft.screen.camera.CameraManager;
import dev.toastcie.customcraft.tile.PictureManager;
import dev.toastcie.customcraft.tile.TileAtlas;

public class GameScreen {

    GameScreen() {
        CameraManager.initialize();
        Keyboard.initialize();
        PictureManager.initialize(getClass().getResourceAsStream("/tiles.png"));
        TileAtlas.initialize();

        MainFrame frame = new MainFrame();
    }

    public static void main(String[] args) {
        new GameScreen();
    }
}
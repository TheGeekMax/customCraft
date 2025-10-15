package dev.toastcie.customcraft;

import dev.toastcie.customcraft.keyevents.Keyboard;
import dev.toastcie.customcraft.screen.MainFrame;
import dev.toastcie.customcraft.screen.camera.CameraManager;

public class GameScreen {

    GameScreen() {
        CameraManager.initialize();
        Keyboard.initialize();

        MainFrame frame = new MainFrame();
    }

    public static void main(String[] args) {
        new GameScreen();
    }
}
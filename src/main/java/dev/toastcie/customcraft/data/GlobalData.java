package dev.toastcie.customcraft.data;

import dev.toastcie.customcraft.level.Level;

public class GlobalData {
    public static int tileWidth = 48;
    public static int timer = 0;
    public static float hitboxScale = 0.8f;

    private static Level activeLevel;

    public static void updateTimer() {
        timer++;
    }

    public static Level getActiveLevel() {
        return activeLevel;
    }

    public static void setActiveLevel(Level level) {
        activeLevel = level;
    }
}

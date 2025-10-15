package dev.toastcie.customcraft.level;

public abstract class Generator {
    protected Level level;

    public Generator() {

    }

    public abstract Level generate(int width, int height);

    public Level getLevel() {
        return this.level;
    }
}

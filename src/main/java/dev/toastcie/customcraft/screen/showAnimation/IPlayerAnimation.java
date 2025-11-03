package dev.toastcie.customcraft.screen.showAnimation;

import java.awt.*;

public interface IPlayerAnimation {

    void move(int dx, int dy, boolean inWater);

    void draw(Graphics g, int x, int y);
}

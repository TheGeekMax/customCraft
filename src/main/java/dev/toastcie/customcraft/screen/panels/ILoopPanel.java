package dev.toastcie.customcraft.screen.panels;

import javax.swing.*;
import java.awt.*;

public interface ILoopPanel {
    void loop();

    void paint(Graphics g, JPanel panel);

    void onClick(int mouseX, int mouseY);
}

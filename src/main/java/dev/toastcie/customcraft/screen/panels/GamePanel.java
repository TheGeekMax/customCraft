package dev.toastcie.customcraft.screen.panels;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.PINK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}

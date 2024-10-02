package com.hrm.view;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {

    private int radius;
    private String iconPath;

    public RoundedPanel(int radius) {
        this.radius = radius;
        setOpaque(false); // Để JPanel trong suốt, không vẽ nền mặc định
    }

    public RoundedPanel(int radius, String iconPath) {
        this.radius = radius;
        this.iconPath = iconPath;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền với góc bo tròn
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g.drawImage(new ImageIcon(iconPath).getImage(), 6, 6, getWidth() - 12, getHeight() - 12, this);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ viền với góc bo tròn
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    }
}

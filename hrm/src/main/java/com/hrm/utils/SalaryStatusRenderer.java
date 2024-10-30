//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hrm.utils;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class SalaryStatusRenderer extends JPanel {
    private final Color color;

    public SalaryStatusRenderer(Color color) {
        this.color = color;
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = Math.min(this.getWidth(), this.getHeight()) - 10;
        int x = (this.getWidth() - size) / 2;
        int y = (this.getHeight() - size) / 2;
        g.setColor(this.color);
        g.fillOval(x, y, size, size);
    }
}

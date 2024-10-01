package com.hrm.view;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    private int radius;

    public RoundedButton(String text, int radius) {
        super(text);
        this.radius = radius;
        setContentAreaFilled(false); // Không vẽ nền mặc định
        setFocusPainted(false); // Không vẽ viền khi nút được chọn
        setBorderPainted(false); // Không vẽ viền mặc định
        setOpaque(false); // Để JButton trong suốt
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Tạo Graphics2D và bật tính năng khử răng cưa
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền bo tròn
        if (getModel().isPressed()) {
            g2.setColor(getBackground().darker()); // Màu nền khi nhấn
        } else {
            g2.setColor(getBackground());
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        // Vẽ văn bản
        super.paintComponent(g);

        // Giải phóng tài nguyên của Graphics2D
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Tạo Graphics2D và bật tính năng khử răng cưa
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ viền bo tròn
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

        // Giải phóng tài nguyên của Graphics2D
        g2.dispose();
    }
}

package com.hrm.view;

import javax.swing.*;
import java.awt.*;

import javax.swing.border.EmptyBorder;

public class RoundedTextField extends JTextField {

    private int radius;

    public RoundedTextField(int radius) {
        this.radius = radius;
        setOpaque(false); // Làm cho JTextField trong suốt
        setBorder(new EmptyBorder(2, 6, 2, 6)); // Thiết lập khoảng cách padding
        // bên trong
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Tạo Graphics2D và bật tính năng khử răng cưa
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ hình nền bo tròn
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        // Vẽ văn bản
        super.paintComponent(g);

        // Giải phóng tài nguyên của Graphics2D
        g2.dispose();
    }
}

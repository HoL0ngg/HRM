package com.hrm.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReportView1 extends JFrame {

    public ReportView1() {
        // Thiết lập JFrame
        setTitle("Báo cáo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2, 10, 10)); // 2 hàng, 2 cột

        // Tạo hai JLabel
        JLabel label1 = new JLabel("Label 1", SwingConstants.CENTER);
        JLabel label2 = new JLabel("Label 2", SwingConstants.CENTER);

        // Tạo hai hình ảnh có thể click
        ImageIcon icon1 = new ImageIcon("D:/WorkSpace/Java/qlns/src/icons/delete_icon.png");
        ImageIcon icon2 = new ImageIcon("D:/WorkSpace/Java/qlns/src/icons/delete_icon.png");
        
        JLabel imageLabel1 = new JLabel(icon1);
        JLabel imageLabel2 = new JLabel(icon2);

        // Thêm sự kiện click vào ảnh
        imageLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Bạn đã click vào hình 1");
            }
        });

        imageLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Bạn đã click vào hình 2");
            }
        });

        // Thêm tất cả vào JFrame
        add(label1);      // Hàng 1, cột 1
        add(label2);      // Hàng 1, cột 2
        add(imageLabel1); // Hàng 2, cột 1
        add(imageLabel2); // Hàng 2, cột 2
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReportView1 frame = new ReportView1();
            frame.setVisible(true);
        });
    }
}

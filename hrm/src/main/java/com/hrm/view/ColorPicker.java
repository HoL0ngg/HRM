/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hrm.view;
import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPicker {
    
    // Hàm để chọn màu và trả về giá trị RGB
    public static String getColorRGB() {
        // Mở hộp thoại chọn màu
        Color color = JColorChooser.showDialog(null, "Chọn Màu", Color.BLACK);
        
        // Kiểm tra nếu người dùng chọn màu hợp lệ
        if (color != null) {
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            // Trả về chuỗi RGB theo định dạng "(R, G, B)"
            return String.format("(%d, %d, %d)", red, green, blue);
        }
        return null; // Trường hợp người dùng hủy bỏ
    }

    public static void main(String[] args) {
        // Tạo một cửa sổ đơn giản với nút chọn màu
        JFrame frame = new JFrame("Chọn Màu");
        JButton button = new JButton("Chọn Màu");

        // Khi nhấn nút, chọn màu và in mã RGB
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rgb = getColorRGB();
                if (rgb != null) {
                    System.out.println("Mã màu RGB: " + rgb);
                } else {
                    System.out.println("Người dùng đã hủy bỏ việc chọn màu.");
                }
            }
        });

        // Thiết lập cửa sổ và hiển thị
        frame.setLayout(new FlowLayout());
        frame.add(button);
        frame.setSize(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


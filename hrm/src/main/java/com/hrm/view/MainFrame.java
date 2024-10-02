package com.hrm.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;

import javax.swing.border.EmptyBorder;

import com.hrm.model.Employee;

public class MainFrame extends JFrame {

    private JPanel contentPane;
    private Employee employee;

    public MainFrame(Employee employee) {
        this.employee = employee;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 760);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 143, 82));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        Image scaledImage = new ImageIcon("D:\\VSCode\\HRM\\hrm\\src\\main\\resources\\avatar.png").getImage()
                .getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        JLabel IconLabel = new JLabel();
        IconLabel.setBounds(900, 20, 45, 45);
        IconLabel.setIcon(new ImageIcon(scaledImage));
        contentPane.add(IconLabel);

        JLabel TenLabel = new JLabel(employee.getName());
        TenLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
        TenLabel.setBounds(980, 20, 125, 45);
        contentPane.add(TenLabel);

        setVisible(true);
    }
}
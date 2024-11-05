package com.hrm.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;

import com.hrm.controller.CongViecController;
import com.hrm.model.Employee;

public class CongViecFrame extends JFrame {
    private Employee employee;

    public CongViecFrame() {
        init();
    }

    public CongViecFrame(Employee employee) {
        this.employee = employee;
        init();
    }

    public Employee getEmployee() {
        return this.employee;
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        CongViecController controller = new CongViecController(this);

        // thanh navbar
        JPanel navBar = new JPanel();
        navBar.setLayout(null);
        navBar.setBackground(new Color(245, 143, 82));
        navBar.setBounds(0, 0, 800, 40);
        contentPane.add(navBar);

        Image AvaIcon = new ImageIcon(
                new File("hrm/src/main/resources/img/profile.png").getAbsolutePath())
                .getImage()
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH);

        JLabel AvaLabel = new JLabel(new ImageIcon(
                AvaIcon));
        AvaLabel.setBounds(560, 5, 30, 30);
        navBar.add(AvaLabel);

        JLabel TenLabel = new JLabel();
        TenLabel.setText(employee.getName());
        TenLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
        TenLabel.setBounds(600, 15, 210, 15);
        navBar.add(TenLabel);

        Image BackBtn = new ImageIcon(new File("hrm/src/main/resources/img/left-arrow.png").getAbsolutePath())
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel BackLabel = new JLabel(new ImageIcon(BackBtn));
        BackLabel.setBounds(10, 5, 30, 30);
        BackLabel.setName("quaylai");
        BackLabel.addMouseListener(controller);
        navBar.add(BackLabel);

        JLabel lblNewLabel = new JLabel("CONG VIEC");
        lblNewLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        lblNewLabel.setBounds(60, 14, 180, 18);
        navBar.add(lblNewLabel);

        // tao lich hop
        RoundedButton taolichhopPanel = new RoundedButton("Tao lich hop", 16);
        taolichhopPanel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        taolichhopPanel.setBounds(610, 52, 140, 26);
        taolichhopPanel.setBackground(Color.white);
        taolichhopPanel.setName("taolichhop");
        taolichhopPanel.addMouseListener(controller);
        contentPane.add(taolichhopPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new CongViecFrame(new Employee());
    }

}

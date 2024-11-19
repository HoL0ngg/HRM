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

        JLabel taolichop1 = new JLabel("Tao moi cong viec");
        taolichop1.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        taolichop1.setForeground(Color.blue);
        taolichop1.setBounds(38, 180, 220, 30);

        JLabel taolichop2 = new JLabel("Tao moi cong viec");
        taolichop2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        taolichop2.setForeground(Color.green);
        taolichop2.setBounds(38, 180, 220, 30);

        JLabel daucong = new JLabel("+");
        daucong.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 80));
        daucong.setForeground(Color.blue);

        JLabel daucong2 = new JLabel("+");
        daucong2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 80));
        daucong2.setForeground(Color.green);

        JLabel hihi = new JLabel("Phòng ban");
        hihi.setBounds(160, 120, 100, 20);
        hihi.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        hihi.setForeground(Color.blue);
        contentPane.add(hihi);

        RoundedPanel cong = new RoundedPanel(18);
        cong.setBounds(61, 80, 80, 80);
        cong.setForeground(Color.blue);
        cong.setBackground(Color.white);
        cong.add(daucong);

        RoundedPanel cong2 = new RoundedPanel(18);
        cong2.setBounds(61, 80, 80, 80);
        cong2.setForeground(Color.green);
        cong2.setBackground(Color.white);
        cong2.add(daucong2);

        RoundedPanel taocongviec = new RoundedPanel(18);
        taocongviec.setBounds(100, 160, 202, 300);
        taocongviec.setBackground(Color.WHITE);
        taocongviec.setForeground(Color.BLUE);
        taocongviec.setLayout(null);
        taocongviec.add(cong);
        taocongviec.add(taolichop1);
        contentPane.add(taocongviec);

        JLabel hehe = new JLabel("Nhân viên");
        hehe.setBounds(500, 120, 100, 20);
        hehe.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        hehe.setForeground(Color.green);
        contentPane.add(hehe);

        RoundedPanel taocongviec2 = new RoundedPanel(18);
        taocongviec2.setBounds(440, 160, 202, 300);
        taocongviec2.setBackground(Color.WHITE);
        taocongviec2.setForeground(Color.GREEN);
        taocongviec2.setLayout(null);
        taocongviec2.add(cong2);
        taocongviec2.add(taolichop2);
        contentPane.add(taocongviec2);

        setVisible(true);
    }

    public static void main(String[] args) {
        new CongViecFrame(new Employee());
    }

}

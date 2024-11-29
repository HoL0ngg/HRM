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

import com.hrm.controller.MucTieuController;
import com.hrm.model.Employee;

public class muctieuframe extends JFrame {
    private Employee employee;

    public muctieuframe() {
        init();
    }

    public muctieuframe(Employee employee) {
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
        MucTieuController controller = new MucTieuController(this);

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

        JLabel lblNewLabel = new JLabel("MUC TIEU");
        lblNewLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        lblNewLabel.setBounds(60, 14, 180, 18);
        navBar.add(lblNewLabel);

        // tao lich hop

        JLabel daucong = new JLabel("Muc tieu");
        daucong.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        daucong.setForeground(Color.black);

        JLabel daucong2 = new JLabel("Danh gia");
        daucong2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        daucong2.setForeground(Color.black);

        RoundedPanel cong = new RoundedPanel(18);
        cong.setBounds(210, 300, 80, 30);
        cong.setForeground(Color.blue);
        cong.setBackground(Color.blue);
        cong.add(daucong);

        RoundedPanel cong2 = new RoundedPanel(18);
        cong2.setBounds(460, 300, 80, 30);
        cong2.setForeground(Color.green);
        cong2.setBackground(Color.green);
        cong2.add(daucong2);

        // RoundedPanel taocongviec = new RoundedPanel(18);
        // taocongviec.setBounds(100, 160, 202, 300);
        // taocongviec.setBackground(Color.WHITE);
        // taocongviec.setLayout(null);
        // taocongviec.add(cong);
        //
        // contentPane.add(taocongviec);
        //
        //
        //
        // RoundedPanel taocongviec2 = new RoundedPanel(18);
        // taocongviec2.setBounds(440, 160, 202, 300);
        // taocongviec2.setBackground(Color.WHITE);
        //
        // taocongviec2.setLayout(null);
        // taocongviec2.add(cong2);
        //
        // contentPane.add(taocongviec2);
        contentPane.add(cong);
        contentPane.add(cong2);
        setVisible(true);
    }

    public static void main(String[] args) {
        new muctieuframe(new Employee());
    }

}
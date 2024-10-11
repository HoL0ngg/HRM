package com.hrm.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import java.util.Date;

import java.text.SimpleDateFormat;

public class ChamCongFrame extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JPanel navBar;
    private JLabel TenLabel;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JComboBox comboBox_1;

    public ChamCongFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Ma NV", "Ho va ten", "Ngay", "Gio vao", "Gio ra", "So gio lam viec", "Lam them gio"
                }) {
            Class[] columnTypes = new Class[] {
                    String.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });

        // kh cho di chuyen cac cot trong table
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 150, 800, 550);
        contentPane.add(scrollPane);

        navBar = new JPanel();
        navBar.setLayout(null);
        navBar.setBackground(new Color(245, 143, 82));
        navBar.setBounds(0, 0, 800, 40);
        contentPane.add(navBar);

        TenLabel = new JLabel("Ten");
        TenLabel.setBounds(700, 12, 100, 15);
        navBar.add(TenLabel);

        JLabel lblNewLabel = new JLabel("Cham cong");
        lblNewLabel.setBounds(60, 12, 100, 15);
        navBar.add(lblNewLabel);

        lblNewLabel_2 = new JLabel("Tu ngay");
        lblNewLabel_2.setBounds(20, 72, 80, 13);
        contentPane.add(lblNewLabel_2);

        lblNewLabel_3 = new JLabel("Den ngay");
        lblNewLabel_3.setBounds(240, 72, 80, 13);
        contentPane.add(lblNewLabel_3);

        JDateChooser NgayBatDau = new JDateChooser();
        NgayBatDau.setDate(new Date());
        NgayBatDau.setBounds(75, 65, 140, 30);

        contentPane.add(NgayBatDau);

        JDateChooser NgayKetThuc = new JDateChooser();
        NgayKetThuc.setDate(new Date()); // Đặt ngày mặc định
        NgayKetThuc.setBounds(300, 65, 140, 30);

        contentPane.add(NgayKetThuc);

        RoundedPanel TimKiemPanel = new RoundedPanel(20);
        TimKiemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        TimKiemPanel.setBounds(480, 65, 250, 30);
        Image TimKiemIcon = new ImageIcon(
                new File("hrm/src/main/resources/img/search.png").getAbsolutePath())
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel timkiemLabel = new JLabel(new ImageIcon(TimKiemIcon));
        timkiemLabel.setPreferredSize(new Dimension(35, 20));
        JTextField TimKiemField = new JTextField();
        TimKiemPanel.setBackground(Color.white);
        TimKiemField.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        TimKiemField.setPreferredSize(new Dimension(200, 20));
        TimKiemField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        TimKiemPanel.add(timkiemLabel);
        TimKiemPanel.add(TimKiemField);
        TimKiemPanel.setOpaque(true);
        contentPane.add(TimKiemPanel);

        Image FilterIcon = new ImageIcon(
                new File("hrm/src/main/resources/img/filter.png").getAbsolutePath())
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel FilterLabel = new JLabel(new ImageIcon(FilterIcon));
        FilterLabel.setBounds(735, 63, 35, 35);
        contentPane.add(FilterLabel);

        RoundedPanel xuatFilePanel = new RoundedPanel(20);
        xuatFilePanel.setBounds(660, 110, 100, 25);
        xuatFilePanel.setBackground(Color.white);
        JLabel xuatFile = new JLabel("Xuat file");
        xuatFile.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        xuatFilePanel.add(xuatFile);
        contentPane.add(xuatFilePanel);

        // Tranh focus vao JTextfield tu ban dau
        JPanel emptyJPanel = new JPanel();
        emptyJPanel.setBounds(0, 0, 0, 0);
        contentPane.add(emptyJPanel);
        setVisible(true);
        emptyJPanel.requestFocusInWindow();
    }
}

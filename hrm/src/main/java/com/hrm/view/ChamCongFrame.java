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

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

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
        lblNewLabel_2.setBounds(10, 68, 80, 13);
        contentPane.add(lblNewLabel_2);

        lblNewLabel_3 = new JLabel("Den ngay");
        lblNewLabel_3.setBounds(230, 68, 80, 13);
        contentPane.add(lblNewLabel_3);

        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(60, 65, 130, 30);
        contentPane.add(comboBox);

        comboBox_1 = new JComboBox();
        comboBox_1.setBounds(290, 65, 130, 30);
        contentPane.add(comboBox_1);

        RoundedPanel TimKiemPanel = new RoundedPanel(20);
        TimKiemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        TimKiemPanel.setBounds(440, 60, 250, 30);
        Image TimKiemIcon = new ImageIcon(
                new File("hrm/src/main/resources/img/search.png").getAbsolutePath())
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel timkiemLabel = new JLabel(new ImageIcon(TimKiemIcon));
        timkiemLabel.setPreferredSize(new Dimension(35, 20));
        JTextField TimKiemField = new JTextField();
        TimKiemPanel.setBackground(Color.white);
        TimKiemField.setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 0));
        TimKiemField.setPreferredSize(new Dimension(200, 20));
        TimKiemField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        TimKiemPanel.add(timkiemLabel);
        TimKiemPanel.add(TimKiemField);
        TimKiemPanel.setOpaque(true);
        contentPane.add(TimKiemPanel);

        setVisible(true);
    }
}

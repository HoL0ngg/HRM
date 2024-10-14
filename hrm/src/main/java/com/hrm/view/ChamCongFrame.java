package com.hrm.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.hrm.controller.ChamCongController;
import com.hrm.model.Employee;
import com.toedter.calendar.JDateChooser;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.util.Date;

public class ChamCongFrame extends JFrame {

        private JPanel contentPane;
        private JTable table;
        private Employee employee;

        public ChamCongFrame(Employee employee) {
                this.employee = employee;
                this.init();
        }

        private void init() {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(800, 700);
                setLocationRelativeTo(null);
                contentPane = new JPanel();
                contentPane.setBackground(Color.WHITE);
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

                setContentPane(contentPane);
                contentPane.setLayout(null);

                ChamCongController controller = new ChamCongController(this);

                table = new JTable();
                table.setModel(new DefaultTableModel(
                                new Object[][] {
                                },
                                new String[] {
                                                "Ma NV", "Ho va ten", "Ngay", "Gio vao", "Gio ra", "So gio lam viec",
                                                "Lam them gio"
                                }) {
                        Class[] columnTypes = new Class[] {
                                        String.class, Object.class, Object.class, Object.class, Object.class,
                                        Object.class, Object.class
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

                JPanel navBar = new JPanel();
                navBar.setLayout(null);
                navBar.setBackground(new Color(245, 143, 82));
                navBar.setBounds(0, 0, 800, 40);
                contentPane.add(navBar);

                JLabel TenLabel = new JLabel();
                // TenLabel.setText("Long cute");
                TenLabel.setText(employee.getName());
                TenLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
                TenLabel.setBounds(700, 15, 100, 15);
                navBar.add(TenLabel);

                JLabel QuayLaiLabel = new JLabel("Quay lai trang truoc");
                QuayLaiLabel.setBounds(380, 15, 150, 16);
                QuayLaiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
                QuayLaiLabel.setName("quaylai");
                QuayLaiLabel.addMouseListener(controller);
                navBar.add(QuayLaiLabel);

                JLabel lblNewLabel = new JLabel("BANG CHAM CONG");
                lblNewLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
                lblNewLabel.setBounds(50, 15, 160, 15);
                navBar.add(lblNewLabel);

                JLabel lblNewLabel_2 = new JLabel("Tu ngay");
                lblNewLabel_2.setBounds(20, 72, 80, 13);
                contentPane.add(lblNewLabel_2);

                JLabel lblNewLabel_3 = new JLabel("Den ngay");
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
                TimKiemField.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
                TimKiemField.setText("Tim kiem");
                TimKiemField.setForeground(Color.gray);
                TimKiemField.addFocusListener(new FocusListener() {
                        public void focusGained(FocusEvent e) {
                                if (String.valueOf(TimKiemField.getText()).equals("Tim kiem")) {
                                        TimKiemField.setText(""); // Xóa placeholder khi focus
                                        TimKiemField.setForeground(Color.BLACK);
                                }
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                                if (String.valueOf(TimKiemField.getText()).isEmpty()) {
                                        TimKiemField.setText("Tim kiem"); // Hiển thị lại placeholder khi
                                                                          // không có dữ liệu
                                        TimKiemField.setForeground(Color.GRAY);
                                }
                        }

                });

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
                FilterLabel.setName("filter");
                FilterLabel.addMouseListener(controller);
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

        public Employee getEmployee() {
                return employee;
        }

        public JTable getTable() {
                return table;
        }

        public void showPopUp(JLabel label) {
                ChamCongController controller = new ChamCongController(this);
                JPopupMenu popupMenu = new JPopupMenu();
                popupMenu.setPopupSize(80, 72);
                JPanel panel = new JPanel();
                panel.setLayout(null);
                panel.setBounds(0, 0, 80, 80);
                panel.setBackground(Color.white);
                JLabel giovao = new JLabel("Gio vao");
                JLabel giora = new JLabel("Gio ra");
                JLabel trangthai = new JLabel("Trang thai");
                giovao.setBounds(10, 5, 50, 14);
                giovao.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 13));
                giovao.setName("giovao");
                giovao.addMouseListener(controller);
                giora.setBounds(10, 28, 50, 14);
                giora.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 13));
                giora.setName("giora");
                giora.addMouseListener(controller);
                trangthai.setBounds(10, 50, 60, 14);
                trangthai.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 13));
                trangthai.setName("trangthai");
                trangthai.addMouseListener(controller);
                panel.add(giovao);
                panel.add(giora);
                panel.add(trangthai);
                popupMenu.add(panel);
                popupMenu.show(label, -60, label.getHeight());
        }
}

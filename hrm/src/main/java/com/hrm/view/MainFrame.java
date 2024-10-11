package com.hrm.view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.awt.Font;

import javax.swing.border.EmptyBorder;

import com.hrm.model.Employee;

public class MainFrame extends JFrame {

        private JPanel contentPane;
        private RoundedTextField TimKiemField;
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

                // Phan Thong tin
                Image AvatarIcon = new ImageIcon(new File("hrm/src/main/resources/img/avatar.png").getAbsolutePath())
                                .getImage()
                                .getScaledInstance(55, 55, Image.SCALE_SMOOTH);
                JLabel IconLabel = new JLabel();
                IconLabel.setBounds(900, 20, 55, 55);
                IconLabel.setIcon(new ImageIcon(AvatarIcon));
                contentPane.add(IconLabel);

                JLabel TenLabel = new JLabel(employee.getName());
                TenLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
                TenLabel.setBounds(960, 24, 125, 45);
                contentPane.add(TenLabel);

                // Thanh tim kiem
                Image TimKiemIcon = new ImageIcon(
                                new File("hrm/src/main/resources/img/search.png").getAbsolutePath())
                                .getImage()
                                .getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                JLabel timkiemLabel = new JLabel(new ImageIcon(TimKiemIcon));
                timkiemLabel.setBounds(280, 130, 40, 40);
                contentPane.add(timkiemLabel);
                TimKiemField = new RoundedTextField(15);
                TimKiemField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                TimKiemField.setText("Tim kiem");
                TimKiemField.setForeground(Color.gray);
                TimKiemField.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
                TimKiemField.setBounds(280, 130, 650, 40);
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
                contentPane.add(TimKiemField);

                // Font cho Menu
                Font MenuFont = new Font("Roboto", Font.PLAIN, 17);

                // Phan Menu
                RoundedPanel NhanVienPanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/people.png").getAbsolutePath());
                NhanVienPanel.setBounds(220, 235, 60, 60);
                JLabel NhanVienLabel = new JLabel("NHAN VIEN");
                NhanVienLabel.setBounds(215, 290, 98, 80);
                NhanVienLabel.setFont(MenuFont);
                contentPane.add(NhanVienPanel);
                contentPane.add(NhanVienLabel);

                RoundedPanel TuyenDungPanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/promotion.png").getAbsolutePath());
                TuyenDungPanel.setBounds(440, 235, 70, 70);
                JLabel TuyenDungLabel = new JLabel("TUYEN DUNG");
                TuyenDungLabel.setBounds(424, 290, 110, 80);
                TuyenDungLabel.setFont(MenuFont);
                contentPane.add(TuyenDungPanel);
                contentPane.add(TuyenDungLabel);

                RoundedPanel LuongPanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/financial-profit.png").getAbsolutePath());
                LuongPanel.setBounds(660, 235, 80, 80);
                JLabel LuongLabel = new JLabel("LUONG");
                LuongLabel.setBounds(670, 290, 80, 80);
                LuongLabel.setFont(MenuFont);
                contentPane.add(LuongPanel);
                contentPane.add(LuongLabel);

                RoundedPanel ChamCongPanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/business.png").getAbsolutePath());
                ChamCongPanel.setBounds(880, 235, 80, 80);
                JLabel ChamCongLabel = new JLabel("CHAM CONG");
                ChamCongLabel.setFont(MenuFont);
                ChamCongLabel.setBounds(870, 290, 110, 80);
                contentPane.add(ChamCongPanel);
                contentPane.add(ChamCongLabel);

                RoundedPanel ThongKePanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/graph2.png").getAbsolutePath());
                ThongKePanel.setBounds(220, 375, 80, 80);
                JLabel ThongKeLabel = new JLabel("THONG KE");
                ThongKeLabel.setBounds(218, 432, 90, 80);
                ThongKeLabel.setFont(MenuFont);
                contentPane.add(ThongKePanel);
                contentPane.add(ThongKeLabel);

                RoundedPanel PhatTrienPanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/promotion.png").getAbsolutePath());
                PhatTrienPanel.setBounds(440, 375, 80, 80);
                contentPane.add(PhatTrienPanel);

                RoundedPanel CongViecPanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/task.png").getAbsolutePath());
                CongViecPanel.setBounds(660, 375, 80, 80);
                contentPane.add(CongViecPanel);

                RoundedPanel CaiDatPanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/set-up.png").getAbsolutePath());
                CaiDatPanel.setBounds(880, 375, 80, 80);
                contentPane.add(CaiDatPanel);

                RoundedPanel MucTieuPanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/targeted.png").getAbsolutePath());
                MucTieuPanel.setBounds(220, 515, 80, 80);
                contentPane.add(MucTieuPanel);

                // Tranh focus vao JTextfield tu ban dau
                JPanel emptyJPanel = new JPanel();
                emptyJPanel.setBounds(0, 0, 0, 0);
                contentPane.add(emptyJPanel);
                setVisible(true);
                emptyJPanel.requestFocusInWindow();
        }
}
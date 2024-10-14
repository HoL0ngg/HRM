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

import com.hrm.controller.MainController;
import com.hrm.model.Employee;

public class MainFrame extends JFrame {

        private JPanel contentPane;
        private RoundedTextField TimKiemField;
        private Employee employee;

        public MainFrame(Employee employee) {
                this.employee = employee;
                this.init();
        }

        private void init() {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(1200, 760);
                setLocationRelativeTo(null);
                contentPane = new JPanel();
                contentPane.setBackground(new Color(245, 143, 82));
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);
                contentPane.setLayout(null);

                // Phan Thong tin
                Image AvatarIcon = new ImageIcon(new File("hrm/src/main/resources/img/profile.png").getAbsolutePath())
                                .getImage()
                                .getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                JLabel IconLabel = new JLabel();
                IconLabel.setBounds(900, 20, 55, 55);
                IconLabel.setIcon(new ImageIcon(AvatarIcon));
                contentPane.add(IconLabel);

                JLabel TenLabel = new JLabel(employee.getName());
                TenLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
                TenLabel.setBounds(962, 26, 125, 45);
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
                Font MenuFont = new Font("Segoe UI Emoji", Font.PLAIN, 18);

                // Xu ly su kien chuot
                MainController controller = new MainController(this);

                // Phan Menu
                RoundedPanel NhanVienPanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/team.png").getAbsolutePath());
                NhanVienPanel.setBounds(220, 235, 60, 60);
                NhanVienPanel.setName("NhanVienPanel");
                JLabel NhanVienLabel = new JLabel("Nhan vien");
                NhanVienLabel.setBounds(208, 272, 98, 80);
                NhanVienLabel.setFont(MenuFont);
                contentPane.add(NhanVienPanel);
                contentPane.add(NhanVienLabel);
                NhanVienPanel.addMouseListener(controller);

                RoundedPanel TuyenDungPanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/hiring.png").getAbsolutePath());
                TuyenDungPanel.setBounds(440, 235, 60, 60);
                TuyenDungPanel.setName("TuyenDungPanel");
                JLabel TuyenDungLabel = new JLabel("Tuyen dung");
                TuyenDungLabel.setBounds(420, 272, 110, 80);
                TuyenDungLabel.setFont(MenuFont);
                contentPane.add(TuyenDungPanel);
                contentPane.add(TuyenDungLabel);

                RoundedPanel LuongPanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/financial-profit.png").getAbsolutePath());
                LuongPanel.setBounds(660, 235, 60, 60);
                LuongPanel.setName("LuongPanel");
                JLabel LuongLabel = new JLabel("Luong");
                LuongLabel.setBounds(665, 272, 80, 80);
                LuongLabel.setFont(MenuFont);
                contentPane.add(LuongPanel);
                contentPane.add(LuongLabel);

                RoundedPanel ChamCongPanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/business.png").getAbsolutePath());
                ChamCongPanel.setBounds(880, 235, 60, 60);
                ChamCongPanel.setName("ChamCongPanel");
                JLabel ChamCongLabel = new JLabel("Cham cong");
                ChamCongLabel.setFont(MenuFont);
                ChamCongLabel.setBounds(865, 272, 100, 80);
                contentPane.add(ChamCongPanel);
                contentPane.add(ChamCongLabel);
                ChamCongPanel.addMouseListener(controller);

                RoundedPanel ThongKePanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/graph2.png").getAbsolutePath());
                ThongKePanel.setBounds(220, 375, 60, 60);
                ThongKePanel.setName("ThongKePanel");
                JLabel ThongKeLabel = new JLabel("Thong ke");
                ThongKeLabel.setBounds(212, 412, 90, 80);
                ThongKeLabel.setFont(MenuFont);
                contentPane.add(ThongKePanel);
                contentPane.add(ThongKeLabel);

                RoundedPanel PhatTrienPanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/promotion.png").getAbsolutePath());
                PhatTrienPanel.setBounds(440, 375, 60, 60);
                PhatTrienPanel.setName("PhatTrienPanel");
                JLabel PhatTrienLabel = new JLabel(
                                "<html><div style='text-align: center;'>Dao tao<br>va phat trien</div></html>");
                PhatTrienLabel.setFont(MenuFont);
                PhatTrienLabel.setBounds(422, 422, 100, 80);
                contentPane.add(PhatTrienLabel);
                contentPane.add(PhatTrienPanel);

                RoundedPanel CongViecPanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/task.png").getAbsolutePath());
                CongViecPanel.setBounds(660, 375, 60, 60);
                CongViecPanel.setName("CongViecPanel");
                JLabel CongViecLabel = new JLabel("Cong viec");
                CongViecLabel.setBounds(652, 412, 100, 80);
                CongViecLabel.setFont(MenuFont);
                contentPane.add(CongViecLabel);
                contentPane.add(CongViecPanel);

                RoundedPanel CaiDatPanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/set-up.png").getAbsolutePath());
                CaiDatPanel.setBounds(880, 375, 60, 60);
                CaiDatPanel.setName("CaiDatPanel");
                JLabel CaiDatLabel = new JLabel("Cai dat");
                CaiDatLabel.setBounds(884, 412, 100, 80);
                CaiDatLabel.setFont(MenuFont);
                contentPane.add(CaiDatLabel);
                contentPane.add(CaiDatPanel);

                RoundedPanel MucTieuPanel = new RoundedPanel(20,
                                new File("hrm/src/main/resources/img/targeted.png").getAbsolutePath());
                MucTieuPanel.setBounds(220, 515, 60, 60);
                MucTieuPanel.setName("MucTieuPanel");
                JLabel MuctieuLabel = new JLabel(
                                "<html><div style='text-align: center;'>Muc tieu<br>va danh gia</div></html>");
                MuctieuLabel.setBounds(205, 558, 100, 80);
                MuctieuLabel.setFont(MenuFont);
                contentPane.add(MuctieuLabel);
                contentPane.add(MucTieuPanel);

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

}
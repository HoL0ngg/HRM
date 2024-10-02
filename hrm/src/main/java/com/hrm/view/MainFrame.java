package com.hrm.view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

                // Phan Thong tin
                Image AvatarIcon = new ImageIcon("D:\\VSCode\\HRM\\hrm\\src\\main\\resources\\avatar.png").getImage()
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
                RoundedPanel TimKiemPanel = new RoundedPanel(20);
                TimKiemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                TimKiemPanel.setBounds(340, 140, 500, 46);
                Image TimKiemIcon = new ImageIcon(
                                "D:\\VSCode\\HRM\\hrm\\src\\main\\resources\\search-interface-symbol.png")
                                .getImage()
                                .getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                JLabel timkiemLabel = new JLabel(new ImageIcon(TimKiemIcon));
                timkiemLabel.setPreferredSize(new Dimension(35, 35));
                JTextField TimKiemField = new JTextField();
                TimKiemField.setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 0));
                TimKiemField.setPreferredSize(new Dimension(430, 36));
                TimKiemField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                TimKiemPanel.setBackground(Color.white);
                TimKiemPanel.add(timkiemLabel);
                TimKiemPanel.add(TimKiemField);
                contentPane.add(TimKiemPanel);

                // Font cho Menu
                Font MenuFont = new Font("Roboto", Font.PLAIN, 17);

                // Phan Menu
                RoundedPanel NhanVienPanel = new RoundedPanel(20,
                                "D:\\VSCode\\HRM\\hrm\\src\\main\\resources\\people.png");
                NhanVienPanel.setBounds(220, 235, 80, 80);
                JLabel NhanVienLabel = new JLabel("NHAN VIEN");
                NhanVienLabel.setBounds(215, 290, 98, 80);
                NhanVienLabel.setFont(MenuFont);
                contentPane.add(NhanVienPanel);
                contentPane.add(NhanVienLabel);

                RoundedPanel TuyenDungPanel = new RoundedPanel(20,
                                "D:\\VSCode\\HRM\\hrm\\src\\main\\resources\\promotion.png");
                TuyenDungPanel.setBounds(440, 235, 80, 80);
                JLabel TuyenDungLabel = new JLabel("TUYEN DUNG");
                TuyenDungLabel.setBounds(424, 290, 110, 80);
                TuyenDungLabel.setFont(MenuFont);
                contentPane.add(TuyenDungPanel);
                contentPane.add(TuyenDungLabel);

                RoundedPanel LuongPanel = new RoundedPanel(20,
                                "D:\\VSCode\\HRM\\hrm\\src\\main\\resources\\financial-profit.png");
                LuongPanel.setBounds(660, 235, 80, 80);
                JLabel LuongLabel = new JLabel("LUONG");
                LuongLabel.setBounds(670, 290, 80, 80);
                LuongLabel.setFont(MenuFont);
                contentPane.add(LuongPanel);
                contentPane.add(LuongLabel);

                RoundedPanel ChamCongPanel = new RoundedPanel(20,
                                "D:\\VSCode\\HRM\\hrm\\src\\main\\resources\\business.png");
                ChamCongPanel.setBounds(880, 235, 80, 80);
                JLabel ChamCongLabel = new JLabel("CHAM CONG");
                ChamCongLabel.setFont(MenuFont);
                ChamCongLabel.setBounds(870, 290, 110, 80);
                contentPane.add(ChamCongPanel);
                contentPane.add(ChamCongLabel);

                RoundedPanel ThongKePanel = new RoundedPanel(20,
                                "D:\\VSCode\\HRM\\hrm\\src\\main\\resources\\graph2.png");
                ThongKePanel.setBounds(220, 375, 80, 80);
                JLabel ThongKeLabel = new JLabel("THONG KE");
                ThongKeLabel.setBounds(218, 432, 90, 80);
                ThongKeLabel.setFont(MenuFont);
                contentPane.add(ThongKePanel);
                contentPane.add(ThongKeLabel);

                RoundedPanel PhatTrienPanel = new RoundedPanel(20,
                                "D:\\VSCode\\HRM\\hrm\\src\\main\\resources\\promotion.png");
                PhatTrienPanel.setBounds(440, 375, 80, 80);
                contentPane.add(PhatTrienPanel);

                RoundedPanel CongViecPanel = new RoundedPanel(20,
                                "D:\\VSCode\\HRM\\hrm\\src\\main\\resources\\task.png");
                CongViecPanel.setBounds(660, 375, 80, 80);
                contentPane.add(CongViecPanel);

                RoundedPanel CaiDatPanel = new RoundedPanel(20,
                                "D:\\VSCode\\HRM\\hrm\\src\\main\\resources\\set-up.png");
                CaiDatPanel.setBounds(880, 375, 80, 80);
                contentPane.add(CaiDatPanel);

                RoundedPanel MucTieuPanel = new RoundedPanel(20,
                                "D:\\VSCode\\HRM\\hrm\\src\\main\\resources\\targeted.png");
                MucTieuPanel.setBounds(220, 515, 80, 80);
                contentPane.add(MucTieuPanel);

                setVisible(true);
        }
}
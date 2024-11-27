package com.hrm.view;

import com.hrm.model.Employee;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SettingGUI extends JFrame {

    private Employee employee;

    public SettingGUI(Employee employee) {
        this.employee = employee;
        this.init();
    }

    public void init() {

        setTitle("Cài đặt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(Color.white);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0)); // Dùng BorderLayout để quản lý các panel, bỏ padding
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0)); // Không có padding thừa
        contentPane.setBackground(Color.WHITE); // Đặt màu nền cho contentPane là trắng
        setContentPane(contentPane);

// Navbar Panel
        JPanel navBarPanel = new JPanel(new BorderLayout());
        navBarPanel.setBackground(new Color(245, 143, 82));
        navBarPanel.setPreferredSize(new Dimension(1000, 50)); // Chiều cao cố định cho navbar
        navBarPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // Ngăn không cho navbar thay đổi chiều cao

        // Tiêu đề và nút Back (bên trái navbar)
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        titlePanel.setOpaque(false);

        Image backBtnImage = new ImageIcon(new File("src/main/resources/img/left-arrow.png").getAbsolutePath())
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel backLabel = new JLabel(new ImageIcon(backBtnImage));
        backLabel.setName("quaylai");

        // Sự kiện quay lại
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goBack();
            }
        });

        JLabel titleLabel = new JLabel("CÀI ĐẶT", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);

        titlePanel.add(backLabel);
        titlePanel.add(titleLabel);
        navBarPanel.add(titlePanel, BorderLayout.WEST); // Thêm title vào trái của navbar

        // Avatar và tên người dùng (bên phải navbar)
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        userPanel.setOpaque(false);

        String[] iconPaths = {
            "src/main/resources/img/bubble-chat.png",
            "src/main/resources/img/bell.png",
            "src/main/resources/img/set-up.png",
            "src/main/resources/img/profile.png"
        };

        for (String path : iconPaths) {
            Image iconImage = new ImageIcon(new File(path).getAbsolutePath())
                    .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            JLabel iconLabel = new JLabel(new ImageIcon(iconImage));
            userPanel.add(iconLabel);
        }

        JLabel userNameLabel = new JLabel(employee.getName());
        userNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        userNameLabel.setForeground(Color.WHITE);
        userPanel.add(userNameLabel);

        navBarPanel.add(userPanel, BorderLayout.EAST); // Thêm userPanel vào bên phải navbar
        contentPane.add(navBarPanel, BorderLayout.NORTH); // Thêm navbar vào phần Bắc của JFrame

        JPanel mainPanel = new JPanel();
        //mainPanel.setPreferredSize(new Dimension(1000, 400));

        JPanel content = new JPanel(new GridLayout(0, 1));
        content.setPreferredSize(new Dimension(1000, 500));
        initUI(content);
        mainPanel.add(content);
        
        contentPane.add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void initUI(JPanel panel) {
        // Tạo các tiêu đề chính
        panel.add(createSectionTitle("Tài khoản người dùng"));
        panel.add(createOption("Phân quyền người dùng"));
        panel.add(createOption("Trạng thái tài khoản"));
        panel.add(createOption("Cấu hình bảo mật"));

        panel.add(createSectionTitle("Cài đặt hiển thị"));
        panel.add(createOption("Thiết lập ngôn ngữ"));
        panel.add(createOption("Ẩn/hiện các chức năng"));
    }

    // Hàm tạo tiêu đề phần
    private JPanel createSectionTitle(String title) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(title);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(label);
        panel.setBackground(Color.WHITE);
        return panel;
    }

    // Hàm tạo từng tùy chọn
    private JPanel createOption(String optionText) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(optionText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(label);
        panel.setBackground(Color.WHITE);
        return panel;
    }

    private void goBack() {
        this.dispose();
        // Mở JFrame khác (ReportEffectWorkView)
        new MainFrame(employee).setVisible(true);
    }
}

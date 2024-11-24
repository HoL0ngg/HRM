package com.hrm.view;

import com.formdev.flatlaf.FlatLightLaf; // Import FlatLaf
import com.hrm.model.Employee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class BaoCaoFrame extends JFrame {
    private Employee employee;

    public BaoCaoFrame(Employee employee) {
    	// Cài đặt FlatLaf trước khi khởi tạo JFrame
        try {
            UIManager.setLookAndFeel(new FlatLightLaf()); // Chọn kiểu giao diện
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        this.employee = employee;
        this.init();
    }

    public void init() {
        // Cài đặt JFrame
        setTitle("Báo cáo nhân sự");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500); // Kích thước JFrame 800x500
        setLocationRelativeTo(null); // Căn giữa JFrame trên màn hình

        // Tạo contentPane chính với BorderLayout
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
        contentPane.add(navBarPanel, BorderLayout.NORTH); // Thêm navbar vào phần Bắc của JFrame

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

        JLabel titleLabel = new JLabel("BÁO CÁO", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
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
        userNameLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
        userNameLabel.setForeground(Color.WHITE);
        userPanel.add(userNameLabel);

        navBarPanel.add(userPanel, BorderLayout.EAST); // Thêm userPanel vào bên phải navbar

        // Tạo một JPanel để chứa ghi chú
        JPanel ghiChuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        ghiChuPanel.setBackground(Color.WHITE); // Đặt nền ghi chú thành trắng
        ghiChuPanel.setPreferredSize(new Dimension(800, 40)); // Đặt chiều cao cố định cho ghi chú
        ghiChuPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // Ngăn không cho ghi chú thay đổi chiều cao

        JLabel ghiChuLabel = new JLabel("Báo cáo và thống kê nhân sự tổng quan");
        ghiChuLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ghiChuPanel.add(ghiChuLabel);

        // Thêm ghi chú vào contentPane trực tiếp, không qua centerPanel
        contentPane.add(ghiChuPanel, BorderLayout.CENTER); // Thêm ghi chú vào phần CENTER của contentPane

        // Tạo một JPanel để chứa 3 panel dưới cùng
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Dùng FlowLayout để căn giữa các panel
        bottomPanel.setBackground(Color.WHITE); // Đặt nền bottomPanel thành trắng
        bottomPanel.add(createPanel("src/main/resources/img/Rp.png", "Tạo báo cáo nhân sự tổng quan ", () -> new ReportOverallView(employee).setVisible(true)));
        bottomPanel.add(createPanel("src/main/resources/img/Rp.png", "Tạo báo cáo hiệu suất tuyển dụng", () -> new ReportRecruitmentView(employee).setVisible(true)));
        bottomPanel.add(createPanel("src/main/resources/img/Rp.png", "Báo cáo đã lưu", () -> new ReportSaveView(employee).setVisible(true)));

        // Thêm bottomPanel vào phần dưới của contentPane
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        // Hiển thị JFrame
        setVisible(true);
    }

    // Hàm tạo 3 panel dưới cùng
    private JPanel createPanel(String imagePath, String labelText, Runnable action) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5, 0, 5, 0);

        File imageFile = new File(imagePath);
        ImageIcon imageIcon = imageFile.exists() ? new ImageIcon(imagePath) : new ImageIcon("src/main/resources/img/default.jpg");
        Image scaledImage = imageIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setCursor(Cursor.getDefaultCursor());
        panel.add(imageLabel, gbc);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(120, 2));
        panel.add(separator, gbc);

        JLabel label = new JLabel("<html><body style='width: 150px;'>" + labelText + "</body></html>");
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(Color.BLACK);
        panel.add(label, gbc);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Đóng frame hiện tại
                SwingUtilities.getWindowAncestor(panel).dispose();
                // Thực thi hành động
                action.run();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(new Color(240, 240, 240));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(Color.WHITE);
            }
        });

        panel.setPreferredSize(new Dimension(200, 300));
        panel.setBorder(new LineBorder(Color.GRAY));
        return panel;
    }

    // Hàm xử lý quay lại trang chủ
    private void goBack() {
        this.dispose();
        // Mở JFrame khác (ReportEffectWorkView)
        new MainFrame(employee).setVisible(true);
    }

//    public static void main(String[] args) {
//        // Cài đặt FlatLaf trước khi hiển thị JFrame
//        try {
//            UIManager.setLookAndFeel(new FlatLightLaf());
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
//
//        // Tạo đối tượng Employee mẫu (thay thế bằng dữ liệu thực nếu có)
//        Employee employee = new Employee();
//        employee.setName("Nguyễn Văn A"); // Đặt tên mẫu cho Employee
//
//        // Khởi chạy JFrame ReportOverrallView
//        javax.swing.SwingUtilities.invokeLater(() -> {
//            new BaoCaoFrame(employee).setVisible(true);
//        });
//    }
}

package com.hrm.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.hrm.view.RoundedPanel;
import com.formdev.flatlaf.FlatLightLaf;
import com.hrm.model.Employee;
import com.hrm.view.RoundedButton;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.text.Normalizer;

public class TrainingDevelopmentView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton addButton, deleteButton, editButton;
    private static Employee employee;
    
    public TrainingDevelopmentView(Employee employee) {
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
        // Thiết lập tiêu đề và kích thước cửa sổ
        setTitle("Đào tạo và phát triển");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Căn giữa cửa sổ
        
     // Tạo giao diện chính
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        // Navbar Panel
        JPanel navBarPanel = new JPanel(new BorderLayout());
        navBarPanel.setBackground(new Color(245, 143, 82));
        navBarPanel.setPreferredSize(new Dimension(1000, 50));

        // Panel bên trái cho tiêu đề và nút Back
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        titlePanel.setOpaque(false);
        titlePanel.setPreferredSize(new Dimension(700, 50));

        // Thêm nút Back vào bên trái titlePanel
        Image BackBtn = new ImageIcon(
                new File("src/main/resources/img/left-arrow.png").getAbsolutePath())
                .getImage()
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel BackLabel = new JLabel(new ImageIcon(BackBtn));
        BackLabel.setBounds(10, 5, 30, 30);
        BackLabel.setName("quaylai");

        // Thêm sự kiện cho nút quay lại trang chủ
        BackLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goBack();
            }
        });

        // Đặt lại vị trí cho BackLabel bên trái của title
        titlePanel.add(BackLabel);

        // Tiêu đề "BÁO CÁO"
        JLabel titleLabel = new JLabel("Đào tạo và phát triển", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

     // Panel bên phải cho avatar và tên
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        userPanel.setOpaque(false);
        userPanel.setPreferredSize(new Dimension(300, 50));

        // Avatar 1
        Image AvaIcon1 = new ImageIcon(new File("src/main/resources/img/profile.png").getAbsolutePath())
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel AvaLabel1 = new JLabel(new ImageIcon(AvaIcon1));

        // Avatar 2
        Image AvaIcon2 = new ImageIcon(new File("src/main/resources/img/set-up.png").getAbsolutePath())
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel AvaLabel2 = new JLabel(new ImageIcon(AvaIcon2));

        // Avatar 3
        Image AvaIcon3 = new ImageIcon(new File("src/main/resources/img/bell.png").getAbsolutePath())
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel AvaLabel3 = new JLabel(new ImageIcon(AvaIcon3));

        // Avatar 4
        Image AvaIcon4 = new ImageIcon(new File("src/main/resources/img/bubble-chat.png").getAbsolutePath())
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel AvaLabel4 = new JLabel(new ImageIcon(AvaIcon4));

        // Thêm các avatar vào userPanel
        
        userPanel.add(AvaLabel4);
        userPanel.add(AvaLabel3);
        userPanel.add(AvaLabel2);
        userPanel.add(AvaLabel1);

        // Thêm tên người dùng
        JLabel TenLabel = new JLabel(employee.getName());
        TenLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
        TenLabel.setForeground(Color.WHITE);
        userPanel.add(TenLabel);

        // Thêm userPanel vào navBarPanel
        navBarPanel.add(userPanel, BorderLayout.EAST);


        // Thêm titlePanel vào Navbar Panel
        navBarPanel.add(titlePanel, BorderLayout.WEST);
        contentPane.add(navBarPanel, BorderLayout.NORTH);

        // Panel chứa ghi chú
        JPanel ghiChuPanel = new JPanel();
        ghiChuPanel.setBackground(new Color(230, 230, 230));
        ghiChuPanel.setPreferredSize(new Dimension(1000, 40));
        ghiChuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel ghiChuLabel = new JLabel("Đào tạo và phát triển");
        ghiChuLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ghiChuPanel.add(ghiChuLabel);

     // Panel chứa các nút chức năng
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1)); // Viền nhẹ cho rõ ràng

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10); // Khoảng cách giữa các phần tử
        
        // Tạo ô tìm kiếm
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 30)); // Kích thước cố định cho ô tìm kiếm
        searchField.setBorder(new LineBorder(Color.GRAY, 1)); // Viền cho rõ ràng
        searchField.setForeground(Color.GRAY); // Màu chữ placeholder
        searchField.setText("Tìm kiếm"); // Đặt chữ placeholder ban đầu
        // Cài đặt vị trí cho ô tìm kiếm trong buttonPanel
        gbc.gridx = 0; // Vị trí cột đầu tiên
        gbc.gridy = 0; // Vị trí hàng đầu tiên
        gbc.weightx = 0.5; // Để căn chỉnh cân đối
        gbc.fill = GridBagConstraints.HORIZONTAL; // Đảm bảo ô tìm kiếm chiếm toàn bộ chiều ngang cột
        buttonPanel.add(searchField, gbc);

        // Đặt các nút kế tiếp
        gbc.gridx = 1; // Dịch chuyển qua cột bên phải
        gbc.weightx = 0; // Reset trọng số ngang
        gbc.fill = GridBagConstraints.NONE; // Không kéo dãn các nút
        gbc.insets = new Insets(0, 10, 0, 10); // Khoảng cách giữa các nút

     // Nút tìm kiếm với biểu tượng
     Image searchIcon = new ImageIcon(new File("hrm/src/main/resources/img/search-interface-symbol.png").getAbsolutePath())
             .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     JButton searchButton = new JButton("Thêm", new ImageIcon(searchIcon));
     searchButton.setBackground(new Color(68, 183, 254));
     searchButton.setBorder(new LineBorder(Color.BLACK, 1));
     searchButton.setPreferredSize(new Dimension(100, 40)); // Đảm bảo nút không quá to
     gbc.gridx++;
     buttonPanel.add(searchButton, gbc);

  // Nút reset với biểu tượng
     Image resetIcon = new ImageIcon(new File("hrm/src/main/resources/img/refresh_icon.png").getAbsolutePath())
             .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     JButton resetButton = new JButton("Xóa", new ImageIcon(resetIcon));
     resetButton.setBackground(new Color(244, 67, 54));
     resetButton.setBorder(new LineBorder(Color.BLACK, 1));
     resetButton.setPreferredSize(new Dimension(100, 40)); // Đảm bảo nút không quá to
     gbc.gridx++;
     buttonPanel.add(resetButton, gbc);

     // Thêm nút Lưu vào
     Image saveIcon = new ImageIcon(new File("hrm/src/main/resources/img/save_icon.png").getAbsolutePath())
             .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     JButton saveButton = new JButton("Sửa", new ImageIcon(saveIcon));
     saveButton.setBackground(new Color(0, 204, 0)); // Màu xanh
     saveButton.setBorder(new LineBorder(Color.BLACK, 1));
     saveButton.setPreferredSize(new Dimension(100, 40)); // Đảm bảo nút không quá to
     gbc.gridx++;
     buttonPanel.add(saveButton, gbc);
     
  // Tạo bảng với ScrollPane
     String[] columnNames = {"Đào tạo và phát triển", "Thành tựu"};
     Object[][] data = {
         {"Lộ trình đào tạo của nhân viên", "Giải thưởng nhân viên xuất sắc của năm"},
         {"Kết quả đào tạo", "Giải thưởng nhân viên cống hiến lâu năm"},
         {"Phát triển kỹ năng", "Giải thưởng sáng kiến sáng tạo"},
         {"Đề xuất đào tạo", "Giải thưởng nhóm làm việc hiệu quả"},
         {"Báo cáo tổng quan đào tạo", "Giải thưởng lãnh đạo xuất sắc"},
         {"Chi phí đào tạo và phát triển", "Giải thưởng chuyên môn xuất sắc"},
         {"Thành tích nổi bật"},
         {"Lịch thi khóa học đào tạo và phát triển"}
     };
     tableModel = new DefaultTableModel(data, columnNames);
     table = new JTable(tableModel);

     // Đặt bảng vào JScrollPane và giới hạn chiều cao
     JScrollPane scrollPane = new JScrollPane(table);
     scrollPane.setPreferredSize(new Dimension(780, 200)); // Chiều cao giới hạn
     scrollPane.setBorder(new LineBorder(Color.GRAY, 1));

     // Sắp xếp các phần tử trong centerPanel
     JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
     centerPanel.add(ghiChuPanel, BorderLayout.NORTH);
     centerPanel.add(buttonPanel, BorderLayout.CENTER);
     centerPanel.add(scrollPane, BorderLayout.SOUTH); // Đặt bảng vào dưới cùng
     contentPane.add(centerPanel, BorderLayout.CENTER);
        
    }
    
    private void goBack() {
    	this.dispose();
        // Mở JFrame khác (ReportEffectWorkView)
        new BaoCaoFrame(employee).setVisible(true);
    }
    
//    public static void main(String[] args) {
//      // Cài đặt FlatLaf trước khi hiển thị JFrame
//      try {
//          UIManager.setLookAndFeel(new FlatLightLaf());
//      } catch (UnsupportedLookAndFeelException e) {
//          e.printStackTrace();
//      }
//
//      // Tạo đối tượng Employee mẫu (thay thế bằng dữ liệu thực nếu có)
//      Employee employee = new Employee();
//      employee.setName("Nguyễn Văn A"); // Đặt tên mẫu cho Employee
//
//      // Khởi chạy JFrame ReportOverrallView
//      javax.swing.SwingUtilities.invokeLater(() -> {
//          new TrainingDevelopmentView(employee).setVisible(true);
//      });
//  }
}


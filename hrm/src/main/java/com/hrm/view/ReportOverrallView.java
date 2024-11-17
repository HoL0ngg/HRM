package com.hrm.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.hrm.Main;
import com.hrm.controller.ReportController;


import java.util.List;

import com.hrm.dao.ReportDAO;
import com.hrm.model.Employee;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ReportOverrallView extends JFrame {
	private JTable table;
    private DefaultTableModel tableModel;
    private ReportController reportController;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<Integer> monthFromComboBox;
    private JComboBox<Integer> monthToComboBox;
    private ReportDAO reportDAO; // Thêm đối tượng DAO

    public ReportOverrallView() {
        // Thiết lập cơ bản của JFrame
        setTitle("Đào tạo và phát triển");
        setSize(800, 650);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Khởi tạo controller và DAO
        reportController = new ReportController();
        reportDAO = new ReportDAO();

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
                goBackToHome();
            }
        });

        // Đặt lại vị trí cho BackLabel bên trái của title
        titlePanel.add(BackLabel);

        // Tiêu đề "BÁO CÁO"
        JLabel titleLabel = new JLabel("BÁO CÁO", SwingConstants.LEFT);
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
        JLabel TenLabel = new JLabel("Nguyen Van A");
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
        JLabel ghiChuLabel = new JLabel("Báo cáo và thống kê nhân sự tổng quan");
        ghiChuLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ghiChuPanel.add(ghiChuLabel);

     // Panel chứa các nút chức năng
     
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1)); // Viền nhẹ cho rõ ràng

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10); // Khoảng cách giữa các phần tử

     // Sử dụng GridBagLayout và căn lề trái cho các phần tử

     // Tháng từ ComboBox
     monthFromComboBox = new JComboBox<>(generateMonthOptions());
     monthFromComboBox.setPreferredSize(new Dimension(120, 30)); // Giảm chiều rộng
  
     gbc.anchor = GridBagConstraints.WEST; // Căn lề trái cho nhãn và ComboBox
     buttonPanel.add(new JLabel("Từ:"), gbc);
     
     buttonPanel.add(monthFromComboBox, gbc);

     // Tháng đến ComboBox
     monthToComboBox = new JComboBox<>(generateMonthOptions());
     monthToComboBox.setPreferredSize(new Dimension(120, 30)); // Giảm chiều rộng
     
     gbc.anchor = GridBagConstraints.WEST; // Căn lề trái cho nhãn và ComboBox
     buttonPanel.add(new JLabel("Đến"), gbc);
     
     buttonPanel.add(monthToComboBox, gbc);
     
     // Năm ComboBox
     yearComboBox = new JComboBox<>(generateYearOptions());
     yearComboBox.setPreferredSize(new Dimension(120, 30)); // Giảm chiều rộng
     gbc.anchor = GridBagConstraints.WEST; // Căn lề trái cho nhãn và ComboBox
     gbc.insets = new Insets(0, 0, 0, 10); // Giảm khoảng cách giữa các phần tử
     buttonPanel.add(new JLabel("Năm:"), gbc);
     buttonPanel.add(yearComboBox, gbc);

     // Nút tìm kiếm với biểu tượng
     Image searchIcon = new ImageIcon(new File("src/main/resources/img/search-interface-symbol.png").getAbsolutePath())
             .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     JButton searchButton = new JButton("Tìm kiếm", new ImageIcon(searchIcon));
     searchButton.setBackground(new Color(68, 183, 254));
     searchButton.setBorder(new LineBorder(Color.BLACK, 1));
     searchButton.setPreferredSize(new Dimension(100, 40)); // Đảm bảo nút không quá to
     gbc.gridx = 7;
     gbc.anchor = GridBagConstraints.CENTER; // Căn giữa cho nút tìm kiếm
     buttonPanel.add(searchButton, gbc);

     // Gắn hàm performSearch() vào nút tìm kiếm
     searchButton.addActionListener(e -> performSearch());


  // Nút reset với biểu tượng
     Image resetIcon = new ImageIcon(new File("src/main/resources/img/refresh_icon.png").getAbsolutePath())
             .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     JButton resetButton = new JButton("Reset", new ImageIcon(resetIcon));
     resetButton.setBackground(new Color(244, 67, 54));
     resetButton.setBorder(new LineBorder(Color.BLACK, 1));
     resetButton.setPreferredSize(new Dimension(100, 40)); // Đảm bảo nút không quá to
     gbc.gridx = 8;
     buttonPanel.add(resetButton, gbc);

     // Gắn hàm resetTableData() vào nút reset
     resetButton.addActionListener(e -> resetTableData());

  // Nút xuất Excel với biểu tượng
     Image exportIcon = new ImageIcon(new File("src/main/resources/img/edit_icon.png").getAbsolutePath())
             .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     JButton exportButton = new JButton("Xuất file", new ImageIcon(exportIcon));
     exportButton.setBackground(new Color(255, 193, 7));
     exportButton.setBorder(new LineBorder(Color.BLACK, 1));
     exportButton.setPreferredSize(new Dimension(100, 40)); // Đảm bảo nút không quá to
     gbc.gridx = 9;
     buttonPanel.add(exportButton, gbc);

     // Gắn hàm exportToExcel() vào nút xuất file
     exportButton.addActionListener(e -> exportToExcel());

     // Thêm nút Lưu vào
     Image saveIcon = new ImageIcon(new File("src/main/resources/img/save_icon.png").getAbsolutePath())
             .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     JButton saveButton = new JButton("Lưu vào", new ImageIcon(saveIcon));
     saveButton.setBackground(new Color(0, 204, 0)); // Màu xanh
     saveButton.setBorder(new LineBorder(Color.BLACK, 1));
     saveButton.setPreferredSize(new Dimension(100, 40)); // Đảm bảo nút không quá to
     gbc.gridx = 10; // Đặt vị trí cho nút "Lưu vào"
     buttonPanel.add(saveButton, gbc);

     // Sự kiện cho nút Lưu vào (chưa thực hiện gì)
     saveButton.addActionListener(e -> {
         // Không làm gì khi nhấn nút này
    	 showSuccessNotification();
     });

        // Panel chứa table
        tableModel = new DefaultTableModel(
                new String[]{"Năm", "Tháng", "Tổng số nhân viên", "Nhân viên mới", "Nhân viên đã nghỉ"},
                0
        );
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Sắp xếp các phần tử trong contentPane
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(ghiChuPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(scrollPane, BorderLayout.SOUTH);

        // Load dữ liệu ban đầu
        loadTableData(reportController.getEmployeeReport());
    }
    
    private void showSuccessNotification() {
        // Tạo một lớp overlay để che toàn bộ giao diện
        JPanel overlayPanel = new JPanel();
        overlayPanel.setBackground(new Color(0, 0, 0, 100)); // Màu đen mờ (overlay)
        overlayPanel.setBounds(0, 0, getWidth(), getHeight());
        
        // Tạo thông báo
        JPanel notificationPanel = new JPanel();
        notificationPanel.setBackground(new Color(255, 255, 255));
        notificationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        notificationPanel.setPreferredSize(new Dimension(300, 100));
        JLabel notificationLabel = new JLabel("Lưu thành công!", SwingConstants.CENTER);
        notificationLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        notificationLabel.setForeground(new Color(0, 128, 0)); // Màu xanh lá cho thông báo thành công
        notificationPanel.add(notificationLabel);
        
        // Đặt notificationPanel vào vị trí trung tâm của overlayPanel
        overlayPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        overlayPanel.add(notificationPanel, gbc);
        
        // Sử dụng JLayeredPane để hiển thị overlay phía trên JFrame
        JLayeredPane layeredPane = getLayeredPane();
        layeredPane.add(overlayPanel, JLayeredPane.MODAL_LAYER);
        
        // Cập nhật lại giao diện
        SwingUtilities.invokeLater(() -> {
            layeredPane.repaint();
            layeredPane.revalidate();
        });
        
        // Ẩn lớp overlay và thông báo sau 3 giây
        Timer timer = new Timer(3000, e -> {
            layeredPane.remove(overlayPanel);
            layeredPane.repaint();
            layeredPane.revalidate();
        });
        timer.setRepeats(false); // Chỉ chạy một lần
        timer.start();
    }


    
    private void goBackToHome() {
        JOptionPane.showMessageDialog(this, "Quay lại trang chủ...");
    }

    private Integer[] generateYearOptions() {
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        Integer[] years = new Integer[10];
        for (int i = 0; i < 10; i++) {
            years[i] = currentYear - i;
        }
        return years;
    }

    private Integer[] generateMonthOptions() {
        Integer[] months = new Integer[12];
        for (int i = 0; i < 12; i++) {
            months[i] = i + 1;
        }
        return months;
    }

    private void loadTableData(List<Object[]> data) {
        tableModel.setRowCount(0);
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    private void performSearch() {
        int year = (int) yearComboBox.getSelectedItem();
        int monthFrom = (int) monthFromComboBox.getSelectedItem();
        int monthTo = (int) monthToComboBox.getSelectedItem();

        if (monthFrom > monthTo) {
            JOptionPane.showMessageDialog(this, "Tháng bắt đầu không được lớn hơn tháng kết thúc.");
            return;
        }

        List<Object[]> searchData = reportController.searchReports(year, monthFrom, monthTo);
        loadTableData(searchData);
    }

    private void resetTableData() {
        yearComboBox.setSelectedIndex(0);
        monthFromComboBox.setSelectedIndex(0);
        monthToComboBox.setSelectedIndex(0);
        loadTableData(reportController.getEmployeeReport());
    }

    private void exportToExcel() {
        try {
            // Lấy năm và tháng từ các ComboBox
            int year = (int) yearComboBox.getSelectedItem();
            int monthFrom = (int) monthFromComboBox.getSelectedItem();
            int monthTo = (int) monthToComboBox.getSelectedItem();

            // Tạo tên file động
            String fileName = "Báo cáo nhân sự tổng quan từ tháng " + monthFrom + " - tháng " + monthTo + " năm " + year + ".xlsx";
            
            // Chuyển dữ liệu từ table vào danh sách dữ liệu để xuất
            List<Object[]> data = reportController.getEmployeeReport();
            String[] columnNames = {"Năm", "Tháng", "Tổng số nhân viên", "Nhân viên mới", "Nhân viên đã nghỉ"};

            // Xuất dữ liệu ra file Excel
            reportDAO.exportToExcel(data, columnNames, fileName);
            
            JOptionPane.showMessageDialog(this, "Dữ liệu đã được xuất ra Excel thành công!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất dữ liệu ra Excel: " + e.getMessage());
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReportOverrallView().setVisible(true));
    }
}

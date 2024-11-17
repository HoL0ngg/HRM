package com.hrm.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.hrm.Main;
import com.hrm.controller.ReportController;
import com.hrm.dao.ReportDAO;
import com.hrm.model.Employee;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReportEmEffectView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private ReportController reportController;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<Integer> monthFromComboBox;
    private JComboBox<Integer> monthToComboBox;
    private ReportDAO reportDAO; // Đối tượng DAO để lấy dữ liệu từ cơ sở dữ liệu

    public ReportEmEffectView() {
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

        // Navbar Panel (Thanh điều hướng)
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

        // Thêm titlePanel vào Navbar Panel
        navBarPanel.add(titlePanel, BorderLayout.WEST);
        contentPane.add(navBarPanel, BorderLayout.NORTH);

        // Panel chứa ghi chú
        JPanel ghiChuPanel = new JPanel();
        ghiChuPanel.setBackground(new Color(230, 230, 230));
        ghiChuPanel.setPreferredSize(new Dimension(1000, 40));
        ghiChuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel ghiChuLabel = new JLabel("Báo cáo hiệu suất tuyển dụng");
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
        monthFromComboBox = new JComboBox<>(generateMonthOptions());
        monthFromComboBox.setPreferredSize(new Dimension(120, 30)); // Giảm chiều rộng
        gbc.anchor = GridBagConstraints.WEST; 
        buttonPanel.add(new JLabel("Từ:"), gbc);
        buttonPanel.add(monthFromComboBox, gbc);

        monthToComboBox = new JComboBox<>(generateMonthOptions());
        monthToComboBox.setPreferredSize(new Dimension(120, 30)); 
        gbc.anchor = GridBagConstraints.WEST; 
        buttonPanel.add(new JLabel("Đến"), gbc);
        buttonPanel.add(monthToComboBox, gbc);

        yearComboBox = new JComboBox<>(generateYearOptions());
        yearComboBox.setPreferredSize(new Dimension(120, 30)); 
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.insets = new Insets(0, 0, 0, 10); 
        buttonPanel.add(new JLabel("Năm:"), gbc);
        buttonPanel.add(yearComboBox, gbc);

        // Nút tìm kiếm
        Image searchIcon = new ImageIcon(new File("src/main/resources/img/search-interface-symbol.png").getAbsolutePath())
                .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JButton searchButton = new JButton("Tìm kiếm", new ImageIcon(searchIcon));
        searchButton.setBackground(new Color(68, 183, 254));
        searchButton.setBorder(new LineBorder(Color.BLACK, 1));
        searchButton.setPreferredSize(new Dimension(100, 40)); 
        gbc.gridx = 7;
        gbc.anchor = GridBagConstraints.CENTER; 
        buttonPanel.add(searchButton, gbc);
        searchButton.addActionListener(e -> performSearch());

        // Nút reset
        Image resetIcon = new ImageIcon(new File("src/main/resources/img/refresh_icon.png").getAbsolutePath())
                .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JButton resetButton = new JButton("Reset", new ImageIcon(resetIcon));
        resetButton.setBackground(new Color(244, 67, 54));
        resetButton.setBorder(new LineBorder(Color.BLACK, 1));
        resetButton.setPreferredSize(new Dimension(100, 40)); 
        gbc.gridx = 8;
        buttonPanel.add(resetButton, gbc);
        resetButton.addActionListener(e -> resetTableData());

        // Nút xuất Excel
        Image exportIcon = new ImageIcon(new File("src/main/resources/img/edit_icon.png").getAbsolutePath())
                .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JButton exportButton = new JButton("Xuất file", new ImageIcon(exportIcon));
        exportButton.setBackground(new Color(255, 193, 7));
        exportButton.setBorder(new LineBorder(Color.BLACK, 1));
        exportButton.setPreferredSize(new Dimension(100, 40)); 
        gbc.gridx = 9;
        buttonPanel.add(exportButton, gbc);
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
            new String[]{"Tháng", "Tổng số ứng viên", "Tỷ lệ chuyển đổi P/T", "Tỷ lệ ứng viên từ chối", "Thời gian trung bình tuyển dụng"}, 
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

        // Load dữ liệu ban đầu từ cơ sở dữ liệu
        loadTableData(reportController.getEmployeeReport());
    }

    // Phương thức tải dữ liệu từ cơ sở dữ liệu vào bảng
    private void loadTableData(List<Object[]> data) {
        // Cập nhật tên cột để phù hợp với dữ liệu từ cơ sở dữ liệu
        String[] columnNames = {
            "Tháng", 
            "Tổng số ứng viên", 
            "Tỷ lệ chuyển đổi P/T", 
            "Tỷ lệ ứng viên từ chối", 
            "Thời gian trung bình tuyển dụng"
        };

        // Thiết lập các tên cột trong bảng
        tableModel.setColumnIdentifiers(columnNames);

        // Thêm dữ liệu vào bảng
        tableModel.setRowCount(0); // Xóa dữ liệu cũ
        for (Object[] row : data) {
            // Kiểm tra và chuyển đổi tỷ lệ thành phần trăm với 2 chữ số thập phân
            if (row[2] != null) {
                Double conversionRate = (row[2] instanceof Integer) ? ((Integer) row[2]).doubleValue() : (Double) row[2];
                row[2] = String.format("%.2f", conversionRate * 100) + "%";  // Tỷ lệ chuyển đổi P/T
            }
            if (row[3] != null) {
                Double rejectionRate = (row[3] instanceof Integer) ? ((Integer) row[3]).doubleValue() : (Double) row[3];
                row[3] = String.format("%.2f", rejectionRate * 100) + "%";  // Tỷ lệ ứng viên từ chối
            }

            // Thêm từng dòng dữ liệu vào bảng
            tableModel.addRow(row);
        }
    }


    // Phương thức tìm kiếm theo năm và tháng
    private void performSearch() {
        // Lấy năm và tháng từ các ComboBox
        int year = (int) yearComboBox.getSelectedItem();
        int monthFrom = (int) monthFromComboBox.getSelectedItem();
        int monthTo = (int) monthToComboBox.getSelectedItem();

        // Lấy dữ liệu từ cơ sở dữ liệu và cập nhật bảng
        List<Object[]> data = reportController.getEmployeeReportByMonthRange(year, monthFrom, monthTo);
        loadTableData(data);
    }

    // Phương thức xuất dữ liệu ra file Excel
    private void exportToExcel() {
        // Thực hiện xuất dữ liệu ra file Excel (cần thêm thư viện Apache POI hoặc các thư viện khác để xuất dữ liệu)
        try {
            // Xử lý file và dữ liệu
            // Tạo tên file theo tháng và năm chọn
            int year = (int) yearComboBox.getSelectedItem();
            int monthFrom = (int) monthFromComboBox.getSelectedItem();
            int monthTo = (int) monthToComboBox.getSelectedItem();

            // Tạo tên file động
            String fileName = "Báo cáo nhân sự tổng quan từ tháng " + monthFrom + " - tháng " + monthTo + " năm " + year + ".xlsx";
            
            // Dữ liệu cần xuất
            List<Object[]> data = reportController.getEmployeeReportByMonthRange(year, monthFrom, monthTo);
            
            // Bạn có thể thêm mã để xuất dữ liệu ra Excel tại đây
            JOptionPane.showMessageDialog(this, "Xuất file thành công: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xuất file!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Phương thức reset bảng về trạng thái ban đầu
    private void resetTableData() {
        yearComboBox.setSelectedIndex(0);
        monthFromComboBox.setSelectedIndex(0);
        monthToComboBox.setSelectedIndex(0);
        loadTableData(reportController.getEmployeeReport());
    }


    // Phương thức quay lại màn hình chính
    private void goBackToHome() {
//        Main.showHome();
    }

    // Phương thức tạo danh sách tháng (1 đến 12)
    private Integer[] generateMonthOptions() {
        Integer[] months = new Integer[12];
        for (int i = 0; i < 12; i++) {
            months[i] = i + 1;
        }
        return months;
    }

    // Phương thức tạo danh sách năm từ 2020 đến năm hiện tại
    private Integer[] generateYearOptions() {
        int currentYear = java.time.Year.now().getValue();
        Integer[] years = new Integer[currentYear - 2019];
        for (int i = 0; i < years.length; i++) {
            years[i] = 2020 + i;
        }
        return years;
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

    // Chạy ứng dụng
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ReportEmEffectView frame = new ReportEmEffectView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

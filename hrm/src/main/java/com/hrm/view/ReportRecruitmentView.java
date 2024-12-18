package com.hrm.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.hrm.controller.ReportController;
import com.hrm.dao.ReportDAO;
import com.hrm.model.Employee;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import com.formdev.flatlaf.FlatLightLaf;

public class ReportRecruitmentView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private ReportController reportController;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<Integer> monthFromComboBox;
    private JComboBox<Integer> monthToComboBox;
    private ReportDAO reportDAO; // Đối tượng DAO để lấy dữ liệu từ cơ sở dữ liệu
    private Employee employee;

    public ReportRecruitmentView(Employee employee) {
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
        // Thiết lập cơ bản của JFrame
        setTitle("Đào tạo và phát triển");
        setSize(800, 650);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Khởi tạo controller và DAO
        reportController = new ReportController();
        reportDAO = new ReportDAO();

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
    	    // Lấy dữ liệu từ giao diện
    	    String ghiChu = ghiChuLabel.getText(); // Ghi chú từ JLabel
    	    String monthFrom = monthFromComboBox.getSelectedItem().toString(); // Tháng bắt đầu
    	    String monthTo = monthToComboBox.getSelectedItem().toString();     // Tháng kết thúc
    	    String year = yearComboBox.getSelectedItem().toString();           // Năm

    	    // Kiểm tra nếu monthFrom và monthTo giống nhau
    	    String reportTitle;
    	    if (monthFrom.equals(monthTo)) {
    	        reportTitle = ghiChu + " Tháng " + monthFrom + " năm " + year;
    	    } else {
    	        reportTitle = ghiChu + " Từ Tháng " + monthFrom + " đến Tháng " + monthTo + " năm " + year;
    	    }

    	    // Dữ liệu khác
    	    int createdBy = employee.getId(); // ID người tạo
    	    String results = "Đã lưu"; // Trạng thái mặc định

    	    // Gọi phương thức lưu trong Controller
    	    new ReportController().saveReport(createdBy, reportTitle, results);
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
        loadTableData(reportController.getRecruitmentPerformanceReport());
    }

    // Phương thức tải dữ liệu từ cơ sở dữ liệu vào bảng
    private void loadTableData(List<Object[]> data) {
        // Cập nhật tên cột để phù hợp với dữ liệu từ cơ sở dữ liệu
        String[] columnNames = {
            "Năm", "Tháng", "Tổng số ứng viên", "Tỷ lệ chuyển đổi P/T", 
            "Tỷ lệ ứng viên từ chối", "Thời gian trung bình tuyển dụng"
        };

        // Thiết lập các tên cột trong bảng
        tableModel.setColumnIdentifiers(columnNames);

        // Thêm dữ liệu vào bảng
        tableModel.setRowCount(0); // Xóa dữ liệu cũ

        // Duyệt qua từng dòng dữ liệu và cập nhật tỷ lệ
        for (Object[] row : data) {
            // Kiểm tra và chuyển đổi tỷ lệ thành phần trăm với 2 chữ số thập phân
            if (row[3] != null) {
                try {
                    // Kiểm tra nếu dữ liệu là kiểu String và chuyển đổi thành Double
                    Double conversionRate = row[3] instanceof String ? Double.parseDouble((String) row[3]) : (Double) row[3];
                    row[3] = String.format("%.2f", conversionRate) + "%";  // Tỷ lệ chuyển đổi P/T
                } catch (NumberFormatException e) {
                    row[3] = "0%";  // Nếu không thể chuyển đổi, gán giá trị mặc định
                }
            }
            
            if (row[4] != null) {
                try {
                    // Kiểm tra nếu dữ liệu là kiểu String và chuyển đổi thành Double
                    Double rejectionRate = row[4] instanceof String ? Double.parseDouble((String) row[4]) : (Double) row[4];
                    row[4] = String.format("%.2f", rejectionRate) + "%";  // Tỷ lệ ứng viên từ chối
                } catch (NumberFormatException e) {
                    row[4] = "0%";  // Nếu không thể chuyển đổi, gán giá trị mặc định
                }
            }

            // Thêm từng dòng dữ liệu vào bảng
            tableModel.addRow(row);
        }
    }




    // Phương thức tìm kiếm theo năm và tháng
    private void performSearch() {
        int year = (int) yearComboBox.getSelectedItem();
        int monthFrom = (int) monthFromComboBox.getSelectedItem();
        int monthTo = (int) monthToComboBox.getSelectedItem();

        if (monthFrom > monthTo) {
            JOptionPane.showMessageDialog(this, "Tháng bắt đầu không được lớn hơn tháng kết thúc.");
            return;
        }

        List<Object[]> searchData = reportController.searchReports2(year, monthFrom, monthTo);
        loadTableData(searchData);
    }

    // Phương thức xuất dữ liệu ra file Excel
    private void exportToExcel() {
        try {
        	String ghiChu = "Báo cáo và thống kê nhân sự tổng quan"; // Ghi chú từ JLabel
    	    String monthFrom = monthFromComboBox.getSelectedItem().toString(); // Tháng bắt đầu
    	    String monthTo = monthToComboBox.getSelectedItem().toString();     // Tháng kết thúc
    	    String year = yearComboBox.getSelectedItem().toString();           // Năm

    	    // Kiểm tra nếu monthFrom và monthTo giống nhau
    	    String fileName;
    	    if (monthFrom.equals(monthTo)) {
    	    	fileName = ghiChu + " Tháng " + monthFrom + " năm " + year + ".xlsx";
    	    } else {
    	    	fileName = ghiChu + " Từ Tháng " + monthFrom + " đến Tháng " + monthTo + " năm " + year + ".xlsx";
    	    }
    	    
    	    int monthFromE = (int) monthFromComboBox.getSelectedItem();
    	    int monthToE = (int) monthToComboBox.getSelectedItem();
    	    int yearE = (int) yearComboBox.getSelectedItem();
            
    	    if (monthFromE > monthToE) {
                JOptionPane.showMessageDialog(this, "Tháng bắt đầu không được lớn hơn tháng kết thúc.");
                return;
            }
    	    
            // Chuyển dữ liệu từ table vào danh sách dữ liệu để xuất
            List<Object[]> data = reportController.searchReports2(yearE, monthFromE, monthToE);
            String[] columnNames = {"Năm", "Tháng", "Tổng số nhân viên", "Nhân viên mới", "Nhân viên đã nghỉ"};

            // Xuất dữ liệu ra file Excel
            reportDAO.exportToExcel(data, columnNames, fileName);
            
            JOptionPane.showMessageDialog(this, "Dữ liệu đã được xuất ra Excel thành công!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất dữ liệu ra Excel: " + e.getMessage());
        }
    }

    // Phương thức reset bảng về trạng thái ban đầu
    private void resetTableData() {
        yearComboBox.setSelectedIndex(0);
        monthFromComboBox.setSelectedIndex(0);
        monthToComboBox.setSelectedIndex(0);
        loadTableData(reportController.getRecruitmentPerformanceReport());
    }


    private void goBack() {
    	this.dispose();
        // Mở JFrame khác (ReportEffectWorkView)
        new BaoCaoFrame(employee).setVisible(true);
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

}

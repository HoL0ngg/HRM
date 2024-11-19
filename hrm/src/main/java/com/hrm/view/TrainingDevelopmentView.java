package com.hrm.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.hrm.view.RoundedPanel;

import com.hrm.view.RoundedButton;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.text.Normalizer;

public class TrainingDevelopmentView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton addButton, deleteButton, editButton;

    public TrainingDevelopmentView() {
        // Thiết lập tiêu đề và kích thước cửa sổ
        setTitle("Đào tạo và phát triển");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Căn giữa cửa sổ

        // Tạo mô hình bảng và dữ liệu
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

        // Tạo bảng cuộn để bảng có thể cuộn được
        JScrollPane scrollPane = new JScrollPane(table);

        // Tạo các nút thêm, xóa, sửa với màu và icon đã chỉnh kích thước
        RoundedButton addButton = new RoundedButton("Thêm", 15);
        addButton.setBackground(new Color(68, 183, 254)); // Màu xanh
        ImageIcon addIcon = getResizedIcon("D:/WorkSpace/Java/qlns/src/icons/add_icon.png", 10, 10); //Lay icon resize
        addButton.setIcon(addIcon);
        addButton.setPreferredSize(new Dimension(60, 25)); // Đặt kích thước cố định cho nút
        addButton.setBorder(new LineBorder(Color.BLACK, 2));
        addButton.setContentAreaFilled(false); // Tắt phần nền của nút (tùy chọn)

        RoundedButton deleteButton = new RoundedButton("Xóa", 15);
        deleteButton.setBackground(new Color(244, 67, 54)); // Màu đỏ
        ImageIcon delIcon = getResizedIcon("D:/WorkSpace/Java/qlns/src/icons/delete_icon.png", 10, 10); //Lay icon resize
        deleteButton.setIcon(delIcon);
        deleteButton.setPreferredSize(new Dimension(60, 25)); // Đặt kích thước cố định cho nút
        deleteButton.setBorder(new LineBorder(Color.BLACK, 2));
        deleteButton.setContentAreaFilled(false); // Tắt phần nền của nút (tùy chọn)

        RoundedButton editButton = new RoundedButton("Sửa", 15);
        editButton.setBackground(new Color(255, 255, 255)); // Màu white
        ImageIcon editIcon = getResizedIcon("D:/WorkSpace/Java/qlns/src/icons/edit_icon.png", 10, 10); //Lay icon resize
        editButton.setIcon(editIcon);
        editButton.setPreferredSize(new Dimension(60, 25)); // Đặt kích thước cố định cho nút
        editButton.setBorder(new LineBorder(Color.BLACK, 2));
        editButton.setContentAreaFilled(false); // Tắt phần nền của nút (tùy chọn)
        Image TimKiemIcon = new ImageIcon(
                        new File("hrm/src/main/resources/img/search.png").getAbsolutePath())
                        .getImage()
                        .getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     
        
        // Bố trí các nút trong một hộp ngang với căn phải
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Căn phải
        buttonPanel.setBackground(Color.WHITE);
        
        //Thanh tim kiem
        RoundedPanel TimKiemPanel = new RoundedPanel(20);
        TimKiemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        TimKiemPanel.setBounds(480, 65, 250, 30);
        JLabel timkiemLabel = new JLabel(new ImageIcon(TimKiemIcon));
        timkiemLabel.setPreferredSize(new Dimension(35, 20));
        JTextField TimKiemField = new JTextField();
        TimKiemPanel.setBackground(Color.white);
        TimKiemField.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
        TimKiemField.setPreferredSize(new Dimension(200, 20));
        TimKiemField.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        TimKiemField.setText("Tim kiem");
        TimKiemField.setForeground(Color.gray);
        
                TimKiemPanel.add(timkiemLabel);
                TimKiemPanel.add(TimKiemField);
                TimKiemPanel.setOpaque(true);
                buttonPanel.add(TimKiemPanel);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        buttonPanel.setPreferredSize(new Dimension(800, 35)); // Đặt chiều cao cố định

        // Bố trí bố cục chính
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH); // Đặt các nút ở phía dưới và căn phải
        getContentPane().add(scrollPane, BorderLayout.CENTER); // Đặt bảng ở trung tâm
        
        
    }

    // Hàm sử dụng ClassLoader để tải và thay đổi kích thước icon
 // Hàm sử dụng ClassLoader để tải và thay đổi kích thước icon
    public static ImageIcon getResizedIcon(String imagePath, int width, int height) {
        // Đọc ảnh từ đường dẫn
        ImageIcon icon = new ImageIcon(imagePath);
        
        // Lấy đối tượng Image từ ImageIcon
        Image image = icon.getImage();

        // Điều chỉnh kích thước ảnh
        Image resizedImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        
        // Tạo và trả về ImageIcon mới với kích thước đã điều chỉnh
        return new ImageIcon(resizedImage);
    }

    
    public static void main(String[] args) {
        // Tạo và hiển thị giao diện
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TrainingDevelopmentView view = new TrainingDevelopmentView();
                view.setVisible(true);
            }
        });
    }
}


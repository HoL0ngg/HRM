package com.hrm.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SalaryDetailDialog extends JDialog {

    // Constructor cập nhật giá trị chi tiết lương
    public SalaryDetailDialog(JFrame parent, String employeeName, String position, BigDecimal totalSalary, 
                              BigDecimal overtimeSalary, BigDecimal bonus, int attendance, BigDecimal deductions, 
                              BigDecimal netSalary, LocalDate payday, String note) {
        super(parent, "Chi tiết phiếu lương", true);
        this.setSize(800, 700);
        this.setLocationRelativeTo(parent);

        // Panel để chứa các thành phần giao diện
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);  // Thiết lập màu nền cho panel là trắng
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = 2;
        gbc.anchor = 17;

        // Tạo font chữ lớn cho tên nhân viên và vị trí
        Font headerFont = new Font("Arial", Font.BOLD, 18);

        // Hiển thị tên nhân viên và vị trí với font chữ lớn
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblEmployeePosition = new JLabel(employeeName + " - " + position);
        lblEmployeePosition.setFont(headerFont);  // Cập nhật font chữ cho tên nhân viên và vị trí
        panel.add(lblEmployeePosition, gbc);
        
        // Hiển thị các thông tin chi tiết
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Tổng lương theo giờ"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new JLabel(totalSalary.toString()), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Tổng lương tăng ca"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(new JLabel(overtimeSalary.toString()), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Chuyên cần"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(new JLabel(String.valueOf(attendance)), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Thưởng"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(new JLabel(bonus.toString()), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Khấu trừ"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(new JLabel(deductions.toString()), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Tổng lương (Net Salary)"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(new JLabel(netSalary.toString()), gbc);

        // Hiển thị ngày hiệu lực (payday)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedPayday = payday.format(formatter);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Ngày hiệu lực"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(new JLabel(formattedPayday), gbc);

        // Hiển thị ghi chú
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(new JLabel("Ghi chú"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        JTextArea txtGhiChu = new JTextArea(5, 20);
        txtGhiChu.setText(note);
        JScrollPane scrollPane = new JScrollPane(txtGhiChu);
        panel.add(scrollPane, gbc);
        
        // Thêm nút cập nhật phiếu lương
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.anchor = 13;
        JButton btnUpdate = new JButton("Cập nhật phiếu lương");
        panel.add(btnUpdate, gbc);
        
        this.getContentPane().add(panel);
        this.pack();
    }

    // Phương thức hiển thị hộp thoại chi tiết lương
    public static void showSalaryDetailDialog(JFrame parent, String employeeName, String position, BigDecimal totalSalary, 
                                               BigDecimal overtimeSalary, BigDecimal bonus, int attendance, 
                                               BigDecimal deductions, BigDecimal netSalary, LocalDate payday, String note) {
        SalaryDetailDialog dialog = new SalaryDetailDialog(parent, employeeName, position, totalSalary, overtimeSalary, 
                                                            bonus, attendance, deductions, netSalary, payday, note);
        dialog.setVisible(true);
    }
}

package com.hrm.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.hrm.controller.ReportController;


import java.util.List;

import com.hrm.dao.ReportDAO;

import java.awt.*;
import java.io.IOException;

public class ReportView3 extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private ReportDAO reportDAO;
    private ReportController reportController;
    
    public ReportView3() {
        setTitle("Đào tạo và phát triển");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Tháng", "Tổng số nhân viên", "Nhân viên mới", "Nhân viên đã nghỉ"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        RoundedButton exportButton = new RoundedButton("Xuất file", 15);
        exportButton.setBackground(new Color(244, 67, 54));
        exportButton.setBorder(new LineBorder(Color.BLACK, 2));
        exportButton.setContentAreaFilled(false);
        
        exportButton.addActionListener(e -> {
            try {
                List<Object[]> data = reportController.getEmployeeReport();
                // Sử dụng columnNames đã khai báo ngoài
                reportController.exportReportToExcel(data, columnNames);
                JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xuất file Excel: " + ex.getMessage());
            }
        });

        RoundedButton saveButton = new RoundedButton("Lưu", 15);
        saveButton.setBackground(new Color(68, 183, 254));
        saveButton.setBorder(new LineBorder(Color.BLACK, 2));
        saveButton.setContentAreaFilled(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(exportButton);
        buttonPanel.add(saveButton);
        buttonPanel.setPreferredSize(new Dimension(800, 35));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        reportDAO = new ReportDAO();
        loadData();
    }

    private void loadData() {
        List<Object[]> reportData = reportDAO.getEmployeeReport();
        tableModel.setRowCount(0); // Xóa dữ liệu cũ
        for (Object[] rowData : reportData) {
            tableModel.addRow(rowData);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReportView3 view = new ReportView3();
            view.setVisible(true);
        });
    }

}

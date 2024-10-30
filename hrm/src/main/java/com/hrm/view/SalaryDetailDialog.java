//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hrm.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SalaryDetailDialog extends JDialog {
    public SalaryDetailDialog(JFrame parent, String employeeName, String position, String totalSalary, String overtimeSalary, String bonus, String deductions, String note) {
        super(parent, "Chi tiết phiếu lương", true);
        this.setSize(500, 400);
        this.setLocationRelativeTo(parent);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = 2;
        gbc.anchor = 17;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel(employeeName + " - " + position), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Tổng lương theo giờ"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new JLabel(totalSalary), gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Tổng lương tăng ca"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(new JLabel(overtimeSalary), gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Chuyên cần"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(new JLabel(bonus), gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Thưởng"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(new JLabel(bonus), gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Khấu trừ"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(new JLabel(deductions), gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Tổng lương"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(new JLabel(totalSalary), gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Ngày hiệu lực"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(new JLabel("20/10/2024"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(new JLabel("Ghi chú"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        JTextArea txtGhiChu = new JTextArea(5, 20);
        txtGhiChu.setText(note);
        JScrollPane scrollPane = new JScrollPane(txtGhiChu);
        panel.add(scrollPane, gbc);
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.anchor = 13;
        JButton btnUpdate = new JButton("Cập nhật phiếu lương");
        panel.add(btnUpdate, gbc);
        this.getContentPane().add(panel);
        this.pack();
    }

    public static void showSalaryDetailDialog(JFrame parent, String employeeName, String position, String totalSalary, String overtimeSalary, String bonus, String deductions, String note) {
        SalaryDetailDialog dialog = new SalaryDetailDialog(parent, employeeName, position, totalSalary, overtimeSalary, bonus, deductions, note);
        dialog.setVisible(true);
    }
}

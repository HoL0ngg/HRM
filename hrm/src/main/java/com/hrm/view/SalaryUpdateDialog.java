package com.hrm.view;

import com.hrm.dao.SalaryChangeHistoryDAO;
import com.hrm.model.SalaryChangeHistory;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class SalaryUpdateDialog extends JDialog {
    private JTextField txtOldSalary;
    private JTextField txtNewSalary;
    private JTextField txtReason;
    private JTextField txtChangeDate;
    private JTextArea txtComment;
    private JTextField txtApprovedById;
    private JButton btnApprove;
    private JButton btnReject;
    private SalaryChangeHistory salaryChangeHistory;

    public SalaryUpdateDialog(Frame parent, SalaryChangeHistory history) {
        super(parent, "Chi tiết phiếu lương", true);
        this.salaryChangeHistory = history;
        this.initComponents();
        this.setFields();
        this.updateButtonState();
    }

    private void initComponents() {
        JLabel lblName = new JLabel(this.salaryChangeHistory.getEmployee().getName());
        JLabel lblOldSalary = new JLabel("Tổng lương:");
        this.txtOldSalary = new JTextField(20);
        this.txtOldSalary.setEditable(false);
        JLabel lblNewSalary = new JLabel("Tổng lương đề xuất:");
        this.txtNewSalary = new JTextField(20);
        JLabel lblReason = new JLabel("Lý do yêu cầu:");
        this.txtReason = new JTextField(20);
        txtReason.setEditable(false);
        JLabel lblChangeDate = new JLabel("Ngày thay đổi:");
        this.txtChangeDate = new JTextField(20);
        this.txtChangeDate.setEditable(false);
        JLabel lblApprovedBy = new JLabel("Người duyệt (ID):");
        this.txtApprovedById = new JTextField(20);
        JLabel lblComment = new JLabel("Phản hồi:");

        // TextArea cho phản hồi
        this.txtComment = new JTextArea(5, 20);
        this.txtComment.setLineWrap(true);
        this.txtComment.setWrapStyleWord(true);
        JScrollPane commentScrollPane = new JScrollPane(this.txtComment);
        
        this.btnApprove = new JButton("Đồng ý");
        this.btnReject = new JButton("Từ chối");
        JButton btnClose = new JButton("Đóng");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(Color.WHITE);
        Font headerFont = new Font("Arial", Font.BOLD, 18);
        lblName.setFont(headerFont);
        
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Căn chỉnh các thành phần trên giao diện
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblOldSalary, gbc);
        gbc.gridx = 1;
        panel.add(this.txtOldSalary, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblNewSalary, gbc);
        gbc.gridx = 1;
        panel.add(this.txtNewSalary, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(lblReason, gbc);
        gbc.gridx = 3;
        panel.add(this.txtReason, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblChangeDate, gbc);
        gbc.gridx = 1;
        panel.add(this.txtChangeDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(lblApprovedBy, gbc);
        gbc.gridx = 1;
        panel.add(this.txtApprovedById, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        panel.add(lblComment, gbc);
        gbc.gridx = 3;
        gbc.gridy = 6;
        panel.add(commentScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(this.btnApprove, gbc);
        gbc.gridx = 1;
        panel.add(this.btnReject, gbc);

        gbc.gridx = 4;
        gbc.gridy = 8;
        panel.add(btnClose, gbc);

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SalaryUpdateDialog.this.dispose();
            }
        });

        this.btnApprove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SalaryUpdateDialog.this.salaryChangeHistory.setStatus("pass");
                SalaryUpdateDialog.this.updateSalaryChangeHistory();
            }
        });

        this.btnReject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SalaryUpdateDialog.this.salaryChangeHistory.setStatus("fail");
                SalaryUpdateDialog.this.updateSalaryChangeHistory();
            }
        });

        this.getContentPane().add(panel);
        this.pack();
        this.setLocationRelativeTo((Component) null);
    }

    private void setFields() {
        this.txtOldSalary.setText(this.salaryChangeHistory.getOldSalary().toString());
        this.txtNewSalary.setText(this.salaryChangeHistory.getNewSalary().toString());
        this.txtReason.setText(this.salaryChangeHistory.getReasons());
        this.txtChangeDate.setText(this.salaryChangeHistory.getchangeDateSend().toString());
        this.txtApprovedById.setText(String.valueOf(this.salaryChangeHistory.getApprovedBy().getId()));
        this.txtComment.setText(this.salaryChangeHistory.getComments());
    }

    private void updateButtonState() {
        String status = this.salaryChangeHistory.getStatus();
        if (status.equals("pass")) {
            this.btnApprove.setEnabled(false);
            this.btnReject.setEnabled(true);
        } else if (status.equals("fail")) {
            this.btnReject.setEnabled(false);
            this.btnApprove.setEnabled(true);
        } else {
            this.btnApprove.setEnabled(true);
            this.btnReject.setEnabled(true);
        }
    }

    private void updateSalaryChangeHistory() {
        // Lấy giá trị từ các trường nhập liệu
        this.salaryChangeHistory.setNewSalary(new BigDecimal(this.txtNewSalary.getText()));
        this.salaryChangeHistory.setReasons(this.txtReason.getText());
        this.salaryChangeHistory.setComments(this.txtComment.getText());
        int approvedById = Integer.parseInt(this.txtApprovedById.getText());
        this.salaryChangeHistory.getApprovedBy().setId(approvedById);

        // Đặt ngày thay đổi là thời gian thực
        this.salaryChangeHistory.setchangeDateBrowse(java.time.LocalDate.now());

        // Gọi DAO để thực hiện cập nhật
        SalaryChangeHistoryDAO salaryChangeHistoryDAO = SalaryChangeHistoryDAO.getInstance();
        boolean success = salaryChangeHistoryDAO.update(this.salaryChangeHistory);

        // Hiển thị thông báo
        if (success) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại.");
        }
    }
}

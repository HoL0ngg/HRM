package com.hrm.view;

import com.hrm.controller.InterviewsBUS;
import com.toedter.calendar.JDateChooser;
import com.hrm.controller.JobOpeningsBUS;
import com.hrm.model.JobOpenings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.border.Border;

public class JobDetailPanel extends JPanel {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    public JTextField positionField;
    public JComboBox<String> departmentComboBox;
    public JComboBox<String> statusComboBox;
    public JDateChooser openDateChooser;
    public JDateChooser endDateChooser;
    public JTextField salaryField;
    public JTextArea descriptionArea;
    public JButton exitButton;
    public JButton completeButton;
    public JButton editButton;
    public JButton deleteButton;
    public JButton suggestButton;

    private JobOpenings job;
    private JobOpeningsBUS jobBus;
    private InterviewsBUS intvBus;

    public JobDetailPanel() {
        init();
    }

    public JobDetailPanel(int jobId) {
        jobBus = new JobOpeningsBUS();
        job = jobBus.get(jobId);
        detail(jobId);
    }

    public JobDetailPanel(int jobId, int flag) {
        jobBus = new JobOpeningsBUS();
        intvBus = new InterviewsBUS();
        info(jobId);
    }

    private void init() {
        //setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = createHeaderPanel("Thêm bài đăng tuyển dụng");
        add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);
    }

    private void detail(int jobId) {
        setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel("Chi tiết tuyển dụng");
        add(headerPanel, BorderLayout.NORTH);

        //phần thông tin
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(255, 204, 153));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 7, 7, 7);

        Font labelFont = new Font("Arial", Font.BOLD, 17);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);

        Border fieldBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);

        JLabel positionLabel = new JLabel("Vị trí");
        positionLabel.setFont(labelFont);
        positionField = new JTextField(job.getPosition());
        positionField.setEditable(true);
        positionField.setFont(fieldFont);
        positionField.setBorder(fieldBorder);
        addField(formPanel, gbc, positionLabel, positionField, 0);

        JLabel departmentLabel = new JLabel("Phòng ban");
        departmentLabel.setFont(labelFont);
        departmentComboBox = new JComboBox<>(new String[]{"Điều hành", "Nhân sự", "Marketing", "Kinh doanh", "Phát triển phần mềm"});
        int id = job.getDepartment_id();
        switch (id) {
            case 1:
                departmentComboBox.setSelectedItem("Điều hành");
                break;
            case 2:
                departmentComboBox.setSelectedItem("Nhân sự");
                break;
            case 3:
                departmentComboBox.setSelectedItem("Marketing");
                break;
            case 4:
                departmentComboBox.setSelectedItem("Kinh doanh");
                break;
            case 5:
                departmentComboBox.setSelectedItem("Phát triển phần mềm");
                break;
        }
        departmentComboBox.setBackground(Color.WHITE);
        departmentComboBox.setFont(fieldFont);
        departmentComboBox.setBorder(fieldBorder);
        addField(formPanel, gbc, departmentLabel, departmentComboBox, 1);

        JLabel statusLabel = new JLabel("Trạng thái");
        statusLabel.setFont(labelFont);
        statusComboBox = new JComboBox<>(new String[]{"opening", "closed"});
        statusComboBox.setSelectedItem(job.getStatus());
        statusComboBox.setBackground(Color.WHITE);
        statusComboBox.setFont(fieldFont);
        statusComboBox.setSelectedIndex(0);
        statusComboBox.setBorder(fieldBorder);
        addField(formPanel, gbc, statusLabel, statusComboBox, 2);

        JLabel openDateLabel = new JLabel("Ngày bắt đầu");
        openDateLabel.setFont(labelFont);
        openDateChooser = new JDateChooser();
        Date dateOpen = java.util.Date.from(job.getOpening_date().atStartOfDay(ZoneId.systemDefault()).toInstant());
        openDateChooser.setDate(dateOpen);
        openDateChooser.setFont(fieldFont);
        addField(formPanel, gbc, openDateLabel, openDateChooser, 3);

        JLabel endDateLabel = new JLabel("Ngày kết thúc");
        endDateLabel.setFont(labelFont);
        endDateChooser = new JDateChooser();
        Date dateClose = java.util.Date.from(job.getClosing_date().atStartOfDay(ZoneId.systemDefault()).toInstant());
        endDateChooser.setDate(dateClose);
        endDateChooser.setFont(fieldFont);
        addField(formPanel, gbc, endDateLabel, endDateChooser, 4);

        JLabel salaryLabel = new JLabel("Lương (VND):");
        salaryLabel.setFont(labelFont);
        salaryField = new JTextField(job.getSalary());
        salaryField.setFont(fieldFont);
        salaryField.setBorder(fieldBorder);
        addField(formPanel, gbc, salaryLabel, salaryField, 5);

        JLabel descriptionLabel = new JLabel("Nội dung:");
        descriptionLabel.setFont(labelFont);
        descriptionArea = new JTextArea(4, 30);
        descriptionArea.setText(job.getDetail());
        descriptionArea.setFont(fieldFont);
        descriptionArea.setBorder(fieldBorder);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(descriptionLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(descriptionArea, gbc);
        add(formPanel, BorderLayout.CENTER);

        //Phần nút sửa - xóa
        editButton = new JButton("Cập nhật");
        editButton.setPreferredSize(new Dimension(120, 30));
        editButton.setBackground(new Color(0, 204, 102));
        editButton.setForeground(Color.white);
        editButton.setFont(new Font("Arial", Font.BOLD, 18));
        //editButton.addActionListener(this::completeButtonActionPerformed);
        //editButton.addActionListener(e -> editButton());

        deleteButton = new JButton("Xóa");
        deleteButton.setPreferredSize(new Dimension(120, 30));
        deleteButton.setBackground(new Color(0, 204, 102));
        deleteButton.setForeground(Color.white);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 18));
        //deleteButton.addActionListener(this::completeButtonActionPerformed);
        //deleteButton.addActionListener(e -> deleteButton());

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        footer.setPreferredSize(new Dimension(800, 50));
        footer.setBackground(new Color(255, 204, 153));
        footer.add(editButton);
        footer.add(deleteButton);
        add(footer, BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel(String title) {
        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setPreferredSize(new Dimension(800, 60));
        headerPanel.setBackground(new Color(255, 204, 153));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLACK);

        exitButton = new JButton("Thoát");
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setBackground(new Color(255, 102, 102)); // Màu nền đỏ
        exitButton.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();

        // Thêm titleLabel vào panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER; // Căn giữa
        gbc.insets = new Insets(15, 0, 15, 0); // Khoảng cách xung quanh nếu cần
        headerPanel.add(titleLabel, gbc);

        // Thêm exitButton vào panel
        gbc.gridx = 1; // Đặt exitButton ở cột kế tiếp
        gbc.gridy = 0;
        gbc.weightx = 0; // Không gian nút không chiếm trọng lượng
        gbc.anchor = GridBagConstraints.EAST; // Căn nút về phía bên phải
        gbc.insets = new Insets(15, 10, 15, 15); // Khoảng cách cho nút

        headerPanel.add(exitButton, gbc);

        return headerPanel;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(255, 204, 153));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 7, 7, 7);

        Font labelFont = new Font("Arial", Font.BOLD, 17);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);

        Border fieldBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);

        JLabel positionLabel = new JLabel("Vị trí (*)");
        positionLabel.setFont(labelFont);
        positionField = new JTextField(30);
        positionField.setFont(fieldFont);
        positionField.setBorder(fieldBorder);
        addField(formPanel, gbc, positionLabel, positionField, 0);

        JLabel departmentLabel = new JLabel("Phòng ban");
        departmentLabel.setFont(labelFont);
        departmentComboBox = new JComboBox<>(new String[]{"Điều hành", "Nhân sự", "Marketing", "Kinh doanh", "Phát triển phần mềm"});
        departmentComboBox.setBackground(Color.WHITE);
        departmentComboBox.setFont(fieldFont);
        departmentComboBox.setBorder(fieldBorder);
        addField(formPanel, gbc, departmentLabel, departmentComboBox, 1);

        JLabel statusLabel = new JLabel("Trạng thái");
        statusLabel.setFont(labelFont);
        statusComboBox = new JComboBox<>(new String[]{"opening", "closed"});
        statusComboBox.setBackground(Color.WHITE);
        statusComboBox.setFont(fieldFont);
        statusComboBox.setSelectedIndex(0);
        statusComboBox.setBorder(fieldBorder);
        addField(formPanel, gbc, statusLabel, statusComboBox, 2);

        JLabel endDateLabel = new JLabel("Ngày kết thúc");
        endDateLabel.setFont(labelFont);
        endDateChooser = new JDateChooser();
        endDateChooser.setFont(fieldFont);
        addField(formPanel, gbc, endDateLabel, endDateChooser, 3);

        JLabel salaryLabel = new JLabel("Lương (VND):");
        salaryLabel.setFont(labelFont);
        salaryField = new JTextField(30);
        salaryField.setFont(fieldFont);
        salaryField.setBorder(fieldBorder);
        addField(formPanel, gbc, salaryLabel, salaryField, 4);

        JLabel descriptionLabel = new JLabel("Nội dung:");
        descriptionLabel.setFont(labelFont);
        descriptionArea = new JTextArea(5, 30);
        descriptionArea.setFont(fieldFont);
        descriptionArea.setBorder(fieldBorder);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(descriptionLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(descriptionArea, gbc);

        return formPanel;
    }

    private void addField(JPanel panel, GridBagConstraints gbc, JLabel label, JComponent field, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(255, 204, 153));
        footerPanel.setPreferredSize(new Dimension(800, 60));

        completeButton = new JButton("Hoàn tất");
        completeButton.setPreferredSize(new Dimension(120, 30));
        completeButton.setBackground(new Color(0, 204, 102));
        completeButton.setForeground(Color.white);
        completeButton.setFont(new Font("Arial", Font.BOLD, 18));
        completeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                completeButton.setBackground(new Color(0, 153, 51)); // Màu khi hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                completeButton.setBackground(new Color(0, 204, 102)); // Màu ban đầu
            }
        });

        footerPanel.add(completeButton);
        return footerPanel;
    }

    private void info(int id) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

        // Header panel (contains job title, salary, experience, deadline, and location)
        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.setBackground(new Color(245, 245, 245)); // Light gray background
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Sample data - replace with data fetching logic based on 'id'
        job = jobBus.get(id);
        String jobTitleText = job.getPosition();
        String salaryText = "Mức lương: " + job.getSalary();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String endDate = "Hạn nộp hồ sơ: " + job.getClosing_date().format(formatter);
        String locationText = "Địa điểm: Hồ Chí Minh";

        // Exit button initialization
        exitButton = new JButton("Thoát");
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setBackground(new Color(255, 102, 102)); // Màu nền đỏ
        exitButton.setForeground(Color.WHITE);

        // Title panel with close button
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(245, 245, 245));

        JLabel jobTitle = new JLabel(jobTitleText, JLabel.LEFT);
        jobTitle.setFont(new Font("Arial", Font.BOLD, 22));

        titlePanel.add(jobTitle, BorderLayout.WEST);
        titlePanel.add(exitButton, BorderLayout.EAST);

        headerPanel.add(titlePanel);

        // Info panel (contains salary, deadline, and location)
        JPanel infoPanel = new JPanel(new GridLayout(1, 3));
        infoPanel.setBackground(new Color(245, 245, 245)); // Same background color
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel salaryLabel = new JLabel(salaryText);
        salaryLabel.setIcon(new ImageIcon("salary_icon.png")); // Icon for salary
        JLabel deadlineLabel = new JLabel(endDate);
        deadlineLabel.setIcon(new ImageIcon("deadline_icon.png")); // Icon for deadline
        JLabel locationLabel = new JLabel(locationText);
        locationLabel.setIcon(new ImageIcon("location_icon.png")); // Icon for location

        infoPanel.add(salaryLabel);
        infoPanel.add(deadlineLabel);
        infoPanel.add(locationLabel);

        headerPanel.add(infoPanel);
        add(headerPanel, BorderLayout.NORTH);

        // Job details section
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel jobDetailsLabel = new JLabel("Chi tiết tuyển dụng", JLabel.LEFT);
        jobDetailsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        jobDetailsLabel.setForeground(new Color(0, 128, 0)); // Green color
        detailsPanel.add(jobDetailsLabel, BorderLayout.NORTH);

        JTextArea jobDescription = new JTextArea(
                "Mô tả công việc\n\n"
                + job.getDetail()
        );
        jobDescription.setFont(new Font("Arial", Font.PLAIN, 16));
        jobDescription.setEditable(false);
        jobDescription.setLineWrap(true);
        jobDescription.setWrapStyleWord(true);
        jobDescription.setBackground(Color.WHITE);

        detailsPanel.add(jobDescription, BorderLayout.CENTER);

        // Button to suggest applicant
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);

        suggestButton = new JButton("Đề xuất ứng viên");
        suggestButton.setFont(new Font("Arial", Font.PLAIN, 14));
        buttonPanel.add(suggestButton);

        detailsPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Only add detailsPanel once to the main panel
        add(detailsPanel, BorderLayout.CENTER);
    }

    public int getDepartmentId(String departmentName) {
        switch (departmentName) {
            case "Điều hành":
                return 1;
            case "Nhân sự":
                return 2;
            case "Marketing":
                return 3;
            case "Kinh doanh":
                return 4;
            case "Phát triển phần mềm":
                return 5;
            default:
                return -1;
        }
    }

    public void exitButton(ActionListener a) {
        exitButton.addActionListener(a);
    }

    public void addButtonListener(ActionListener listener) {
        completeButton.addActionListener(listener);
    }

    public void editButton(ActionListener a) {
        editButton.addActionListener(a);
    }

    public void deleteButton(ActionListener a) {
        deleteButton.addActionListener(a);
    }

    public void applyButton(ActionListener a) {
        suggestButton.addActionListener(a);
    }
}

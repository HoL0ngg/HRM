package com.hrm.view;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class ApplicantPanel extends JPanel {

    // Declare all components
    private JTextArea nameField, phoneField, emailField, departmentField, positionField;
    private JTextArea cvLinkField;
    private JFormattedTextField interviewDateField;
    private JComboBox<String> resultComboBox, interviewer1ComboBox, interviewer2ComboBox, interviewer3ComboBox;
    private JTextArea noteArea;
    public JButton completeButton;
    public JButton exitButton;
    private JSpinner interviewTimeSpinner;

    public ApplicantPanel() {
        init();
    }

    private void init() {
        setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel("Đề xuất ứng viên");
        add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(1,2));

        JPanel left = new JPanel(new GridBagLayout());
        left.setBackground(new Color(255, 204, 153));
        JPanel right = new JPanel(new GridBagLayout());
        right.setBackground(new Color(255, 204, 153));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);
        Border fieldBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);

        // Row 1
        gbc.gridx = 0;
        gbc.gridy = 1;

        JLabel name = new JLabel("Họ và tên");
        name.setFont(labelFont);
        left.add(name, gbc);

        gbc.gridx = 1;
        nameField = new JTextArea();
        nameField.setFont(fieldFont);
        nameField.setBorder(fieldBorder);
        nameField.setRows(1);
        nameField.setColumns(15);
        nameField.setLineWrap(true);
        nameField.setWrapStyleWord(true);
        left.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel dptm = new JLabel("Phòng ban");
        dptm.setFont(labelFont);
        right.add(dptm, gbc);

        gbc.gridx = 1;
        departmentField = new JTextArea();
        departmentField.setFont(fieldFont);
        departmentField.setBorder(fieldBorder);
        departmentField.setRows(1);
        departmentField.setColumns(15);
        departmentField.setLineWrap(true);
        departmentField.setWrapStyleWord(true);
        JScrollPane departmentScrollPane = new JScrollPane(departmentField);
        right.add(departmentScrollPane, gbc);

        // Row 2
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel phone = new JLabel("Số điện thoại");
        phone.setFont(labelFont);
        left.add(phone, gbc);

        gbc.gridx = 1;
        phoneField = new JTextArea();
        phoneField.setFont(fieldFont);
        phoneField.setBorder(fieldBorder);
        phoneField.setRows(1);
        phoneField.setColumns(15);
        left.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel position = new JLabel("Vị trí tuyển dụng");
        position.setFont(labelFont);
        right.add(position, gbc);

        gbc.gridx = 1;
        positionField = new JTextArea();
        positionField.setFont(fieldFont);
        positionField.setBorder(fieldBorder);
        positionField.setRows(1);
        positionField.setColumns(15);
        right.add(positionField, gbc);

        // Row 3
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel email = new JLabel("Email");
        email.setFont(labelFont);
        left.add(email, gbc);

        gbc.gridx = 1;
        emailField = new JTextArea();
        emailField.setFont(fieldFont);
        emailField.setBorder(fieldBorder);
        emailField.setRows(1);
        emailField.setColumns(15);
        left.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel rs = new JLabel("Kết quả");
        rs.setFont(labelFont);
        right.add(rs, gbc);

        gbc.gridx = 1;
        String[] results = {"In progress", "Passed", "Failed"};
        resultComboBox = new JComboBox<>(results);
        right.add(resultComboBox, gbc);

        // Row 4
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel cv = new JLabel("CV");
        cv.setFont(labelFont);
        left.add(cv, gbc);

        gbc.gridx = 1;
        cvLinkField = new JTextArea();
        cvLinkField.setFont(fieldFont);
        cvLinkField.setBorder(fieldBorder);
        cvLinkField.setRows(1);
        cvLinkField.setColumns(15);
        left.add(cvLinkField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel note = new JLabel("Ghi chú");
        note.setFont(labelFont);
        right.add(note, gbc);

        gbc.gridx = 1;
        gbc.gridheight = 3; // Ghi chú chiếm 3 hàng
        noteArea = new JTextArea();
        noteArea.setFont(fieldFont);
        noteArea.setBorder(fieldBorder);
        noteArea.setRows(5);
        noteArea.setColumns(15);
        noteArea.setLineWrap(true);
        noteArea.setWrapStyleWord(true);
        right.add(noteArea, gbc);
        gbc.gridheight = 1; // Reset lại

        // Row 5
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel dl = new JLabel("Lịch phỏng vấn");
        dl.setFont(labelFont);
        left.add(dl, gbc);

        gbc.gridx = 1;
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setPreferredSize(new Dimension(120, 25));
        left.add(dateChooser, gbc);

        // row 6
        gbc.gridx = 1;
        gbc.gridy = 6;

        interviewTimeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(interviewTimeSpinner, "HH:mm");
        interviewTimeSpinner.setEditor(timeEditor);
        interviewTimeSpinner.setValue(new java.util.Date()); // default to current time
        left.add(interviewTimeSpinner, gbc);

        // Row 7
        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel intver = new JLabel("Người phỏng vấn");
        intver.setFont(labelFont);
        left.add(intver, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        String[] interviewers = {"Không có", "Nguyễn Văn B", "Trần Thị C"};
        interviewer1ComboBox = new JComboBox<>(interviewers);
        left.add(interviewer1ComboBox, gbc);

        gbc.gridy = 8;
        interviewer2ComboBox = new JComboBox<>(interviewers);
        left.add(interviewer2ComboBox, gbc);

        gbc.gridy = 9;
        interviewer3ComboBox = new JComboBox<>(interviewers);
        left.add(interviewer3ComboBox, gbc);

        formPanel.add(left);
        formPanel.add(right);
        return formPanel;
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
        gbc.insets = new Insets(0, 0, 0, 15); // Khoảng cách xung quanh nếu cần
        headerPanel.add(titleLabel, gbc);

        // Thêm exitButton vào panel
        gbc.gridx = 1; // Đặt exitButton ở cột kế tiếp
        gbc.gridy = 0;
        gbc.weightx = 0; // Không gian nút không chiếm trọng lượng
        gbc.anchor = GridBagConstraints.EAST; // Căn nút về phía bên phải
        //gbc.insets = new Insets(15, 10, 15, 15); // Khoảng cách cho nút

        headerPanel.add(exitButton, gbc);

        return headerPanel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(255, 204, 153));
        footerPanel.setPreferredSize(new Dimension(800, 40));

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

    private void addField(JPanel panel, GridBagConstraints gbc, JLabel label, JComponent field, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Interview Panel");
        frame.setPreferredSize(new Dimension(800, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ApplicantPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

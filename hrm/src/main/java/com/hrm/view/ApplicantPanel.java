package com.hrm.view;

import com.hrm.controller.ApplicantsBUS;
import com.hrm.controller.DepartmentBUS;
import com.hrm.controller.EmployeeBus;
import com.hrm.controller.InterviewerBUS;
import com.hrm.controller.InterviewsBUS;
import com.hrm.controller.JobOpeningsBUS;
import com.hrm.model.Applicants;
import com.hrm.model.Department;
import com.hrm.model.Interviews;
import com.hrm.model.JobOpenings;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import javax.swing.border.Border;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class ApplicantPanel extends JPanel {

    // Declare all components
    public JTextField positionField, departmentField, nameInfo, phoneInfo, emailInfo, cvInfo;
    public JTextArea nameField, phoneField, emailField;
    public JTextArea cvLinkField;
    public JDateChooser dateChooser;
    public JComboBox<String> resultComboBox, interviewer1ComboBox, interviewer2ComboBox, interviewer3ComboBox;
    public JTextArea noteArea;
    public JButton completeButton;
    public JButton updateButton;
    public JButton exitButton;
    public JSpinner interviewTimeSpinner;

    private InterviewsBUS intvBus;
    private Interviews intv;
    private InterviewerBUS intverBus;
    //private ApplicantsBUS applicantBus;
    private JobOpeningsBUS jobBus;
    private JobOpenings job;
    private EmployeeBus empBus;
    private EmployeeBus emp;
    private DepartmentBUS dptmBus;
    private Applicants appli;
    private ApplicantsBUS appliBus;

    public ApplicantPanel(int id) {
        jobBus = new JobOpeningsBUS();
        intvBus = new InterviewsBUS();
        empBus = new EmployeeBus();
        dptmBus = new DepartmentBUS();
        appliBus = new ApplicantsBUS();
        appli = appliBus.get(id);
        intv = intvBus.getByApplicantId(id);
        init();
    }

    public ApplicantPanel(int id, int flag) {
        jobBus = new JobOpeningsBUS();
        intvBus = new InterviewsBUS();
        dptmBus = new DepartmentBUS();
        job = jobBus.get(id);

        setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel("Đề xuất ứng viên");
        add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = createFormPanel1();
        add(formPanel, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);
    }

    private void init() {
        setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel("Cập nhật ứng viên");
        add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = createFormPanel2();
        add(formPanel, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createFormPanel1() {
        JPanel left = new JPanel(new GridBagLayout());
        left.setBackground(new Color(255, 204, 153));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);
        Border fieldBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel dptm = new JLabel("Phòng ban");
        dptm.setFont(labelFont);
        left.add(dptm, gbc);

        gbc.gridx = 1;
        Department department = dptmBus.get(job.getDepartment_id());
        departmentField = new JTextField(department.getName());
        departmentField.setFont(fieldFont);
        departmentField.setBorder(fieldBorder);
        //departmentField.setRows(1);
        departmentField.setColumns(20);
        left.add(departmentField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel position = new JLabel("Vị trí tuyển dụng");
        position.setFont(labelFont);
        left.add(position, gbc);

        gbc.gridx = 1;
        positionField = new JTextField(job.getPosition());
        positionField.setFont(fieldFont);
        positionField.setBorder(fieldBorder);
        //positionField.setRows(1);
        positionField.setColumns(20);
        left.add(positionField, gbc);
        // Row 1
        gbc.gridx = 0;
        gbc.gridy = 3;

        JLabel name = new JLabel("Họ và tên");
        name.setFont(labelFont);
        left.add(name, gbc);

        gbc.gridx = 1;
        nameField = new JTextArea();
        nameField.setFont(fieldFont);
        nameField.setBorder(fieldBorder);
        nameField.setRows(1);
        nameField.setColumns(20);
        nameField.setLineWrap(true);
        nameField.setWrapStyleWord(true);
        left.add(nameField, gbc);

        // Row 2
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel phone = new JLabel("Số điện thoại");
        phone.setFont(labelFont);
        left.add(phone, gbc);

        gbc.gridx = 1;
        phoneField = new JTextArea();
        phoneField.setFont(fieldFont);
        phoneField.setBorder(fieldBorder);
        phoneField.setRows(1);
        phoneField.setColumns(20);
        left.add(phoneField, gbc);

        // Row 3
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel email = new JLabel("Email");
        email.setFont(labelFont);
        left.add(email, gbc);

        gbc.gridx = 1;
        emailField = new JTextArea();
        emailField.setFont(fieldFont);
        emailField.setBorder(fieldBorder);
        emailField.setRows(1);
        emailField.setColumns(20);
        left.add(emailField, gbc);

        // Row 4
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel cv = new JLabel("CV");
        cv.setFont(labelFont);
        left.add(cv, gbc);

        gbc.gridx = 1;
        cvLinkField = new JTextArea();
        cvLinkField.setFont(fieldFont);
        cvLinkField.setBorder(fieldBorder);
        cvLinkField.setRows(2);
        cvLinkField.setColumns(20);
        left.add(cvLinkField, gbc);

        return left;
    }

    private JPanel createFormPanel2() {
        JPanel formPanel = new JPanel(new GridLayout(1, 2));

        JPanel left = new JPanel(new GridBagLayout());
        left.setBackground(new Color(255, 204, 153));
        JPanel right = new JPanel(new GridBagLayout());
        right.setBackground(new Color(255, 204, 153));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.anchor = GridBagConstraints.WEST;

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
        nameInfo = new JTextField(appli.getFull_name());
        nameInfo.setFont(fieldFont);
        nameInfo.setBorder(fieldBorder);
        nameInfo.setColumns(15);
        left.add(nameInfo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel dptm = new JLabel("Phòng ban");
        dptm.setFont(labelFont);
        right.add(dptm, gbc);

        gbc.gridx = 1;
        String dptmName = intvBus.getDepartmentName(appli.getId());
        departmentField = new JTextField(dptmName);
        departmentField.setFont(fieldFont);
        departmentField.setBorder(fieldBorder);
        //departmentField.setRows(1);
        departmentField.setColumns(15);
        right.add(departmentField, gbc);

        // Row 2
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel phone = new JLabel("Số điện thoại");
        phone.setFont(labelFont);
        left.add(phone, gbc);

        gbc.gridx = 1;
        phoneInfo = new JTextField(appli.getPhone());
        phoneInfo.setFont(fieldFont);
        phoneInfo.setBorder(fieldBorder);
        phoneInfo.setColumns(15);
        left.add(phoneInfo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel position = new JLabel("Vị trí tuyển dụng");
        position.setFont(labelFont);
        right.add(position, gbc);

        gbc.gridx = 1;
        String jobName = intvBus.getPositionByApplicantId(appli.getId());
        positionField = new JTextField(jobName);
        positionField.setFont(fieldFont);
        positionField.setBorder(fieldBorder);
        //positionField.setRows(1);
        positionField.setColumns(15);
        right.add(positionField, gbc);

        // Row 3
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel email = new JLabel("Email");
        email.setFont(labelFont);
        left.add(email, gbc);

        gbc.gridx = 1;
        emailInfo = new JTextField(appli.getEmail());
        emailInfo.setFont(fieldFont);
        emailInfo.setBorder(fieldBorder);
        emailInfo.setColumns(15);
        left.add(emailInfo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel rs = new JLabel("Kết quả");
        rs.setFont(labelFont);
        right.add(rs, gbc);

        gbc.gridx = 1;
        String[] results = {"in progress", "passed", "failed"};
        resultComboBox = new JComboBox<>(results);
        resultComboBox.setSelectedItem(intv.getResult());
        right.add(resultComboBox, gbc);

        // Row 4
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel cv = new JLabel("CV");
        cv.setFont(labelFont);
        left.add(cv, gbc);

        gbc.gridx = 1;
        cvInfo = new JTextField(appli.getResume());
        cvInfo.setFont(fieldFont);
        cvInfo.setBorder(fieldBorder);
        cvInfo.setColumns(15);
        left.add(cvInfo, gbc);

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
        // row 5: Xử lý ngày phỏng vấn (JDateChooser)
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel dl = new JLabel("Lịch phỏng vấn");
        dl.setFont(labelFont);
        left.add(dl, gbc);

        gbc.gridx = 1;
        LocalDate localDate = intv.getInterview_date();
        dateChooser = new JDateChooser();

// Kiểm tra nếu ngày phỏng vấn không phải null trước khi gán giá trị
        if (localDate != null) {
            Date date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            dateChooser.setDate(date1);
        } else {
            dateChooser.setDate(null); // Nếu giá trị là null, để trống
        }

        dateChooser.setPreferredSize(new Dimension(120, 25));
        left.add(dateChooser, gbc);

// row 6: Xử lý giờ phỏng vấn (JSpinner)
        gbc.gridx = 1;
        gbc.gridy = 6;

        LocalTime localTime = intv.getInterview_time();
        interviewTimeSpinner = new JSpinner();

// Kiểm tra nếu giờ phỏng vấn không phải null trước khi gán giá trị
        if (localTime != null) {
            Date date2 = Date.from(localTime.atDate(LocalDate.now()) // Kết hợp với ngày hiện tại
                    .atZone(ZoneId.systemDefault()) // Chuyển sang thời gian theo múi giờ hệ thống
                    .toInstant());
            interviewTimeSpinner.setModel(new SpinnerDateModel(date2, null, null, java.util.Calendar.HOUR_OF_DAY));
        } else {
            // Nếu giờ phỏng vấn là null, bạn có thể thiết lập một giá trị mặc định, ví dụ: giờ hiện tại
            Date currentDate = new Date(); // Thời gian hiện tại
            interviewTimeSpinner.setModel(new SpinnerDateModel(currentDate, null, null, java.util.Calendar.HOUR_OF_DAY));
        }

        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(interviewTimeSpinner, "HH:mm");
        interviewTimeSpinner.setEditor(timeEditor);
        left.add(interviewTimeSpinner, gbc);

        // Row 7
        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel intver = new JLabel("Người phỏng vấn");
        intver.setFont(labelFont);
        left.add(intver, gbc);

        List<String> interviewers = empBus.getManagerNameList(); // Lấy danh sách tên quản lý
        interviewer1ComboBox = new JComboBox<>();
        interviewer2ComboBox = new JComboBox<>();
        interviewer3ComboBox = new JComboBox<>();
        interviewer1ComboBox.addItem("");
        interviewer2ComboBox.addItem("");
        interviewer3ComboBox.addItem("");
        for (String intverName : interviewers) {
            interviewer1ComboBox.addItem(intverName);
            interviewer2ComboBox.addItem(intverName);
            interviewer3ComboBox.addItem(intverName);// Thêm từng tên từ danh sách
        }
        gbc.gridx = 1;
        gbc.gridy = 7;
        left.add(interviewer1ComboBox, gbc);

        gbc.gridy = 8;
        left.add(interviewer2ComboBox, gbc);

        gbc.gridy = 9;
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

    public void addApplicantButton(ActionListener listener) {
        completeButton.addActionListener(listener);
    }

    public void exitButton(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

    public void updateApplicantButton(ActionListener listener) {
        updateButton.addActionListener(listener);
    }
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Interview Panel");
//        frame.setPreferredSize(new Dimension(800, 500));
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(new ApplicantPanel());
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
}

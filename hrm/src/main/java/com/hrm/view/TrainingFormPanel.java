package com.hrm.view;

import com.hrm.controller.EmployeeBus;
import com.hrm.controller.TrainerBUS;
import com.hrm.controller.TrainingDevelopmentBUS;
import com.hrm.model.TrainingDevelopment;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class TrainingFormPanel extends JPanel {

    public JTextField nameField;
    public JTextArea nameArea;
    public JTextField desField;
    public JTextArea desArea;
    public JDateChooser startDateChooser;
    public JDateChooser endDateChooser;
    public JComboBox<String> hostComboBox;
    public JComboBox<String> statusComboBox;
    public JButton exitButton;
    public JButton completeButton;
    public JButton editButton;
    public JButton deleteButton;
    public JButton suggestButton;

    private TrainingDevelopment td;
    private TrainingDevelopmentBUS tdBus;
    private TrainerBUS trainerBus;
    private EmployeeBus eBus;

    public TrainingFormPanel() {
        eBus = new EmployeeBus();
        init();
    }

    public TrainingFormPanel(int id) {
        eBus = new EmployeeBus();
        trainerBus = new TrainerBUS();
        tdBus = new TrainingDevelopmentBUS();
 ////////////       td = tdBus.get(id);
        detail();
    }

    private void init() {
        setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel("Thêm chương trình đào tạo");
        add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.CENTER);

        JPanel footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);
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
        exitButton.setBackground(new Color(255, 102, 102));
        exitButton.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 0, 15, 0);
        headerPanel.add(titleLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(15, 10, 15, 15);

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

        JLabel nameLabel = new JLabel("Chủ đề (*)");
        nameLabel.setFont(labelFont);
        nameField = new JTextField(30);
        nameField.setFont(fieldFont);
        nameField.setBorder(fieldBorder);
        addField(formPanel, gbc, nameLabel, nameField, 0);

        JLabel hostLabel = new JLabel("Người đào tạo (*)");
        hostLabel.setFont(labelFont);
        List<String> hostList = eBus.getList();
        hostComboBox = new JComboBox<>();
        hostComboBox.addItem("");
        for (String name : hostList) {
            hostComboBox.addItem(name);
        }
        addField(formPanel, gbc, hostLabel, hostComboBox, 1);

        JLabel startDateLabel = new JLabel("Ngày bắt đầu");
        startDateLabel.setFont(labelFont);
        startDateChooser = new JDateChooser();
        startDateChooser.setFont(fieldFont);
        addField(formPanel, gbc, startDateLabel, startDateChooser, 2);

        JLabel endDateLabel = new JLabel("Ngày kết thúc");
        endDateLabel.setFont(labelFont);
        endDateChooser = new JDateChooser();
        endDateChooser.setFont(fieldFont);
        addField(formPanel, gbc, endDateLabel, endDateChooser, 3);

        JLabel statusLabel = new JLabel("Trạng thái");
        statusLabel.setFont(labelFont);
        statusComboBox = new JComboBox<>(new String[]{"in progress", "completed"});
        statusComboBox.setBackground(Color.WHITE);
        statusComboBox.setFont(fieldFont);
        statusComboBox.setSelectedIndex(0);
        statusComboBox.setBorder(fieldBorder);
        addField(formPanel, gbc, statusLabel, statusComboBox, 4);

        JLabel desLabel = new JLabel("Miêu tả");
        desLabel.setFont(labelFont);
        desArea = new JTextArea(5, 30);
        desArea.setLineWrap(true);
        desArea.setWrapStyleWord(true);
        desArea.setFont(fieldFont);
        desArea.setBorder(fieldBorder);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(desLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(desArea, gbc);

        return formPanel;
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

    private void detail() {

        setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel("Chi tiết đào tạo");
        add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(255, 204, 153));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 7, 7, 7);

        Font labelFont = new Font("Arial", Font.BOLD, 17);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);

        Border fieldBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);

        JLabel nameLabel = new JLabel("Chủ đề (*)");
        nameLabel.setFont(labelFont);
        nameField = new JTextField(30);
        nameField.setText(td.getName());
        nameField.setFont(fieldFont);
        nameField.setBorder(fieldBorder);
        addField(formPanel, gbc, nameLabel, nameField, 0);

        JLabel hostLabel = new JLabel("Người đào tạo (*)");
        hostLabel.setFont(labelFont);
        List<String> hostList = eBus.getList();
        hostComboBox = new JComboBox<>();
        hostComboBox.addItem(trainerBus.getHostById(td.getId()));
        for (String name : hostList) {
            hostComboBox.addItem(name);
        }
        addField(formPanel, gbc, hostLabel, hostComboBox, 1);

        JLabel startDateLabel = new JLabel("Ngày bắt đầu");
        startDateLabel.setFont(labelFont);
        Date dateOpen = java.util.Date.from(td.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        startDateChooser = new JDateChooser();
        startDateChooser.setDate(dateOpen);
        startDateChooser.setFont(fieldFont);
        addField(formPanel, gbc, startDateLabel, startDateChooser, 2);

        JLabel endDateLabel = new JLabel("Ngày kết thúc");
        startDateLabel.setFont(labelFont);
        Date dateClosing = java.util.Date.from(td.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        endDateChooser = new JDateChooser();
        endDateChooser.setDate(dateClosing);
        endDateChooser.setFont(fieldFont);
        addField(formPanel, gbc, endDateLabel, endDateChooser, 3);

        JLabel statusLabel = new JLabel("Trạng thái");
        statusLabel.setFont(labelFont);
        statusComboBox = new JComboBox<>(new String[]{"in progress", "completed"});
        statusComboBox.setSelectedItem(td.getStatus());
        statusComboBox.setBackground(Color.WHITE);
        statusComboBox.setFont(fieldFont);
        statusComboBox.setSelectedIndex(0);
        statusComboBox.setBorder(fieldBorder);
        addField(formPanel, gbc, statusLabel, statusComboBox, 4);

        JLabel desLabel = new JLabel("Miêu tả");
        desLabel.setFont(labelFont);
        desArea = new JTextArea(5, 30);
        desArea.setText(td.getDescription());
        desArea.setLineWrap(true);
        desArea.setWrapStyleWord(true);
        desArea.setFont(fieldFont);
        desArea.setBorder(fieldBorder);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(desLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(desArea, gbc);
        
        add(formPanel, BorderLayout.CENTER);
        
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(255, 204, 153));
        footerPanel.setPreferredSize(new Dimension(800, 60));

        editButton = button("Cập nhật");
        deleteButton = button("Xóa");
        suggestButton = button("Tham gia");

        footerPanel.add(editButton);
        footerPanel.add(deleteButton);
        footerPanel.add(suggestButton);
        
        add(footerPanel, BorderLayout.SOUTH);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, JLabel label, JComponent field, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private JButton button(String name) {
        JButton Button = new JButton(name);
        Button.setPreferredSize(new Dimension(120, 30));
        Button.setBackground(new Color(0, 204, 102));
        Button.setForeground(Color.white);
        Button.setFont(new Font("Arial", Font.BOLD, 18));
        Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Button.setBackground(new Color(0, 153, 51)); // Màu khi hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                Button.setBackground(new Color(0, 204, 102)); // Màu ban đầu
            }
        });
        return Button;
    }

    public void exitButton(ActionListener a) {
        exitButton.addActionListener(a);
    }

    public void completeButton(ActionListener a) {
        completeButton.addActionListener(a);
    }

    public void editButton(ActionListener a) {
        editButton.addActionListener(a);
    }

    public void deleteButton(ActionListener a) {
        deleteButton.addActionListener(a);
    }

    public void suggestButton(ActionListener a) {
        suggestButton.addActionListener(a);
    }
}

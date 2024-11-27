package com.hrm.view;

import com.hrm.controller.AchievementsBUS;
import com.hrm.controller.EmployeeBus;
import com.hrm.model.Achievements;
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

public class AchievementFormPanel extends JPanel {

    public JComboBox<String> nameComboBox;
    public JTextField titleField;
    public JTextArea desArea;
    public JDateChooser awardDateChooser;
    public JButton exitButton;
    public JButton completeButton;
    public JButton editButton;
    public JButton deleteButton;

    private Achievements a;
    private AchievementsBUS aBus;
    private EmployeeBus eBus;

    public AchievementFormPanel() {
        eBus = new EmployeeBus();
        init();
    }

    public AchievementFormPanel(int id) {
        eBus = new EmployeeBus();
        aBus = new AchievementsBUS();
        a = aBus.get(id);
        detail();
    }

    private void init() {
        setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel("Thêm thành tựu");
        add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(255, 204, 153));
        footerPanel.setPreferredSize(new Dimension(800, 60));
        completeButton = button("Hoàn tất");
        footerPanel.add(completeButton);
        
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

        JLabel nameLabel = new JLabel("Tên nhân viên");
        nameLabel.setFont(labelFont);
        List<String> nameList = eBus.getList();
        nameComboBox = new JComboBox<>();
        nameComboBox.addItem("");
        for (String name : nameList) {
            nameComboBox.addItem(name);
        }
        nameComboBox.setBackground(Color.WHITE);
        nameComboBox.setFont(fieldFont);
        nameComboBox.setBorder(fieldBorder);
        addField(formPanel, gbc, nameLabel, nameComboBox, 0);

        JLabel titleLabel = new JLabel("Thành tích");
        titleLabel.setFont(labelFont);
        titleField = new JTextField(30);
        titleField.setFont(fieldFont);
        titleField.setBorder(fieldBorder);
        addField(formPanel, gbc, titleLabel, titleField, 1);

        JLabel awardDateLabel = new JLabel("Ngày bắt đầu");
        awardDateLabel.setFont(labelFont);
        awardDateChooser = new JDateChooser();
        awardDateChooser.setFont(fieldFont);
        addField(formPanel, gbc, awardDateLabel, awardDateChooser, 2);

        JLabel desLabel = new JLabel("Miêu tả");
        desLabel.setFont(labelFont);
        desArea = new JTextArea(5, 30);
        desArea.setLineWrap(true);
        desArea.setWrapStyleWord(true);
        desArea.setFont(fieldFont);
        desArea.setBorder(fieldBorder);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(desLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(desArea, gbc);

        return formPanel;
    }

    private void detail() {

        setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel("Chi tiết thành tích");
        add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(255, 204, 153));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 7, 7, 7);

        Font labelFont = new Font("Arial", Font.BOLD, 17);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);

        Border fieldBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);

        JLabel nameLabel = new JLabel("Tên nhân viên (*)");
        nameLabel.setFont(labelFont);
        List<String> nameList = eBus.getList();
        nameComboBox = new JComboBox<>();
        nameComboBox.addItem(eBus.getFullName(a.getEmployeeId()));
        for (String name : nameList) {
            nameComboBox.addItem(name);
        }
        nameComboBox.setBackground(Color.WHITE);
        nameComboBox.setFont(fieldFont);
        nameComboBox.setBorder(fieldBorder);
        addField(formPanel, gbc, nameLabel, nameComboBox, 0);

        JLabel titleLabel = new JLabel("Thành tích (*)");
        titleLabel.setFont(labelFont);
        titleField = new JTextField(30);
        titleField.setText(a.getTitle());
        titleField.setFont(fieldFont);
        titleField.setBorder(fieldBorder);
        addField(formPanel, gbc, titleLabel, titleField, 1);

        JLabel awardDateLabel = new JLabel("Ngày xác nhận");
        awardDateLabel.setFont(labelFont);
        Date date = java.util.Date.from(a.getAwardDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        awardDateChooser = new JDateChooser();
        awardDateChooser.setDate(date);
        awardDateChooser.setFont(fieldFont);
        addField(formPanel, gbc, awardDateLabel, awardDateChooser, 2);

        JLabel desLabel = new JLabel("Miêu tả");
        desLabel.setFont(labelFont);
        desArea = new JTextArea(5, 30);
        desArea.setText(a.getDescription());
        desArea.setLineWrap(true);
        desArea.setWrapStyleWord(true);
        desArea.setFont(fieldFont);
        desArea.setBorder(fieldBorder);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(desLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(desArea, gbc);

        add(formPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(255, 204, 153));
        footerPanel.setPreferredSize(new Dimension(800, 60));

        editButton = button("Cập nhật");
        deleteButton = button("Xóa");

        footerPanel.add(editButton);
        footerPanel.add(deleteButton);

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

}

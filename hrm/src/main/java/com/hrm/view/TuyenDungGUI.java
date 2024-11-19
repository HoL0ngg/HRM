package com.hrm.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TuyenDungGUI extends JPanel {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final int JOB_CARD_WIDTH = 150;
    private static final int JOB_CARD_HEIGHT = 100;

    public TuyenDungGUI() {
        init();
    }

    public void init() {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setBackground(Color.white);

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel headBar = new JPanel(new BorderLayout());
        headBar.setBounds(20, 20, WIDTH, 170);

        JPanel toolBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
        toolBar.setBackground(Color.white);
        toolBar.add(createRoundedButton("Thêm bài đăng", "src\\main\\java\\img\\plus.png", 150, 35));
        toolBar.add(createRoundedButton("Danh sách ứng viên", "src\\main\\java\\img\\rectangle-list.png", 200, 35));
        toolBar.add(createRoundedButton("Lịch phỏng vấn", "src\\main\\java\\img\\calendar-day.png", 150, 35));

        JPanel searchField = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        searchField.setBackground(Color.white);
        JTextField search = new JTextField("Search...",30);
        search.setFont(new Font("Arial", Font.PLAIN, 18));
        search.setPreferredSize(new Dimension(300, 40));
        search.setForeground(Color.GRAY);
        search.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (search.getText().equals("Search...")) {
                    search.setText("");
                    search.setForeground(Color.BLACK);  
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (search.getText().isEmpty()) {
                    search.setText("Search...");
                    search.setForeground(Color.GRAY);  
                }
            }
        });

        JButton prevBtn = createIconButton("src\\main\\java\\img\\left-arrow.png");
        JButton nextBtn = createIconButton("src\\main\\java\\img\\right-arrow.png");
        JLabel pageCurrent = new JLabel("9");
        pageCurrent.setFont(new Font("Arial", Font.BOLD, 14));

        searchField.add(search);
        searchField.add(prevBtn);
        searchField.add(pageCurrent);
        searchField.add(nextBtn);
        searchField.add(createIconButton("src\\main\\java\\img\\filter_1.png"));

        headBar.add(toolBar, BorderLayout.NORTH);
        headBar.add(searchField, BorderLayout.CENTER);
        return headBar;
    }

    private JButton createRoundedButton(String text, String iconPath, int width, int height) {
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        JButton button = new RoundedButton(text, 25);
        button.setIcon(icon);
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(new Color(240, 240, 240));
        button.setForeground(Color.BLACK);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        return button;
    }

    private JButton createIconButton(String iconPath) {
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        JButton button = new JButton(icon);
        button.setBackground(Color.white);
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }

    private JPanel createContentPanel() {
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.setBackground(Color.white);

        JPanel contentPanel = new JPanel(new GridLayout(2, 1, 15, 30));
        contentPanel.setBackground(Color.white);
        contentPanel.setPreferredSize(new Dimension(WIDTH - 100, 500));
        contentPanel.add(createJobPanel("Mới nhất", new Color(255, 241, 189)));
        contentPanel.add(createJobPanel("Cũ nhất", new Color(189, 241, 255)));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setPreferredSize(new Dimension(WIDTH - 60, 500));
        scrollPane.setBackground(Color.white);
        mainPanel.add(scrollPane);
        return mainPanel;
    }

    private JPanel createJobPanel(String title, Color headerColor) {
        JPanel jobPanel = new JPanel(new BorderLayout());
        jobPanel.setBorder(BorderFactory.createLineBorder(headerColor.darker(), 3));
        jobPanel.setPreferredSize(new Dimension(WIDTH - 100, 250));

        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(WIDTH - 100, 50));
        header.setBackground(headerColor);

        JLabel titleLabel = new JLabel(title, JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel viewAllLabel = new JLabel("Xem tất cả", JLabel.RIGHT);
        viewAllLabel.setFont(new Font("Arial", Font.BOLD, 12));
        viewAllLabel.setForeground(Color.BLUE);
        viewAllLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        header.add(titleLabel, BorderLayout.WEST);
        header.add(viewAllLabel, BorderLayout.EAST);
        jobPanel.add(header, BorderLayout.NORTH);

        JPanel jobsGrid = new JPanel(new GridLayout(2, 3, 40, 20));
        for (int i = 0; i < 6; i++) {
            jobsGrid.add(createJobCard("Senior Data Analyst", "Opening"));
        }

        jobPanel.add(jobsGrid, BorderLayout.CENTER);
        return jobPanel;
    }

    private JPanel createJobCard(String title, String status) {
        JPanel jobCard = new JPanel(new BorderLayout());
        jobCard.setPreferredSize(new Dimension(JOB_CARD_WIDTH, JOB_CARD_HEIGHT));
        jobCard.setBackground(Color.white);
        jobCard.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel statusLabel = new JLabel(status);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusLabel.setForeground(Color.GRAY);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.add(titleLabel);
        infoPanel.add(statusLabel);

        JButton updateButton = new RoundedButton("Cập nhật", 15);
        updateButton.setBackground(new Color(76, 175, 80));
        updateButton.setForeground(Color.white);
        updateButton.setBorder(BorderFactory.createLineBorder(new Color(56, 142, 60), 1));

        JPanel toolPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        toolPanel.add(updateButton);

        jobCard.add(infoPanel, BorderLayout.CENTER);
        jobCard.add(toolPanel, BorderLayout.SOUTH);
        return jobCard;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Recruitment Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 832);
        frame.add(new TuyenDungGUI());
        frame.setVisible(true);
    }
}

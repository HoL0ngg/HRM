package com.hrm.view;

import com.hrm.controller.JobOpeningsBUS;
import com.hrm.model.JobOpenings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TuyenDungGUI extends JPanel {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final int JOB_CARD_WIDTH = 130;
    private static final int JOB_CARD_HEIGHT = 60;

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JobDetailForm jobDetailForm;
    private JobOpeningsBUS jobBus;

    private ArrayList<JobOpenings> jobList = new ArrayList<>();

    public TuyenDungGUI() {
        jobBus = new JobOpeningsBUS();
        init();
    }

    public void init() {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setBackground(Color.white);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        JPanel recruitmentPanel = createRecruitmentPanel();
        jobDetailForm = new JobDetailForm(this);

        contentPanel.add(recruitmentPanel, "Recruitment");
        contentPanel.add(jobDetailForm, "JobDetail");

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        switchToPanel("Recruitment");
    }

    private JPanel createHeaderPanel() {
        JPanel headBar = new JPanel(new BorderLayout());

        JPanel toolBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
        toolBar.setBackground(Color.white);

        JButton addJobButton = createRoundedButton("Thêm bài đăng", "src\\main\\java\\img\\plus.png", 180, 35);
        addJobButton.addActionListener(e -> switchToPanel("JobDetail"));
        toolBar.add(addJobButton);

        toolBar.add(createRoundedButton("Danh sách ứng viên", "src\\main\\java\\img\\rectangle-list.png", 180, 35));
        toolBar.add(createRoundedButton("Lịch phỏng vấn", "src\\main\\java\\img\\calendar-day.png", 180, 35));

        JPanel searchField = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
        searchField.setBackground(Color.white);
        JTextField search = new JTextField("Search...", 30);
        search.setFont(new Font("Arial", Font.PLAIN, 15));
        search.setPreferredSize(new Dimension(200, 35));
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

        JButton searchBtn = createIconButton("src\\main\\java\\img\\search-interface-symbol.png");
        JButton nextBtn = createIconButton("src\\main\\java\\img\\right-arrow.png");
        JLabel pageCurrent = new JLabel("9");
        pageCurrent.setFont(new Font("Arial", Font.BOLD, 14));

        searchField.add(search);
        searchField.add(searchBtn);
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
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        return button;
    }

    private JButton createIconButton(String iconPath) {
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        JButton button = new JButton(icon);
        button.setBackground(Color.white);
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }

    private JPanel createRecruitmentPanel() {
        JPanel recruitmentPanel = new JPanel(new BorderLayout());
        recruitmentPanel.setBackground(Color.white);
        recruitmentPanel.add(createContentPanel(), BorderLayout.CENTER);
        return recruitmentPanel;
    }

    private JPanel createContentPanel() {
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.setBackground(Color.white);

        JPanel contentPanel = new JPanel(new GridLayout(2, 1, 15, 30));
        contentPanel.setBackground(Color.white);
        contentPanel.setPreferredSize(new Dimension(WIDTH - 100, 650));
        contentPanel.add(createJobPanel("Mới nhất", new Color(255, 204, 0)));
        contentPanel.add(createJobPanel("Cũ nhất", new Color(102, 153, 255)));

        JScrollPane scrollPane = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        scrollPane.setPreferredSize(new Dimension(WIDTH - 80, HEIGHT - 190));
        scrollPane.setBackground(Color.white);
        mainPanel.add(scrollPane);
        return mainPanel;
    }

    private JPanel createJobPanel(String title, Color headerColor) {
        JPanel jobPanel = new JPanel(new BorderLayout());
        jobPanel.setBorder(BorderFactory.createLineBorder(headerColor, 3));
        jobPanel.setPreferredSize(new Dimension(WIDTH - 100, 300));

        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(WIDTH - 100, 50));
        header.setBackground(headerColor);

        JLabel titleLabel = new JLabel(title, JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.BLACK);

        JLabel viewAllLabel = new JLabel("Xem tất cả", JLabel.RIGHT);
        viewAllLabel.setFont(new Font("Arial", Font.BOLD, 12));
        viewAllLabel.setForeground(Color.BLUE);
        viewAllLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        header.add(titleLabel, BorderLayout.WEST);
        header.add(viewAllLabel, BorderLayout.EAST);
        jobPanel.add(header, BorderLayout.NORTH);

        JPanel jobsGrid = new JPanel(new GridLayout(2, 3, 35, 20));
        jobsGrid.setBackground(Color.white);

        jobList = jobBus.getList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ArrayList<JobOpenings> sortedJobList;
        if ("Mới nhất".equals(title)) {
            sortedJobList = jobList.stream()
                    .filter(job -> job.getOpening_date() != null)
                    .sorted(Comparator.comparing(JobOpenings::getOpening_date).reversed())
                    .limit(6)
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            sortedJobList = jobList.stream()
                    .filter(job -> job.getOpening_date() != null)
                    .sorted(Comparator.comparing(JobOpenings::getOpening_date))
                    .limit(6)
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        for (JobOpenings job : sortedJobList) {
            String openingDateString = job.getOpening_date().format(formatter);
            jobsGrid.add(createJobCard(job.getPosition(), "Hồ Chí Minh", openingDateString));
        }

        jobPanel.add(jobsGrid, BorderLayout.CENTER);
        return jobPanel;
    }

    private JPanel createJobCard(String title, String location, String openDate) {
        JPanel jobCard = new JPanel(new BorderLayout());
        jobCard.setPreferredSize(new Dimension(JOB_CARD_WIDTH, JOB_CARD_HEIGHT));
        jobCard.setBackground(Color.WHITE);
        jobCard.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        JLabel titleLabel = new JLabel("  " + title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel locationLabel = new JLabel("  " + location);
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        locationLabel.setForeground(Color.DARK_GRAY);
        JLabel open_dateLabel = new JLabel("  Ngày đăng: " + openDate);
        open_dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        open_dateLabel.setForeground(Color.DARK_GRAY);

        jobCard.add(titleLabel, BorderLayout.NORTH);
        jobCard.add(locationLabel, BorderLayout.CENTER);
        jobCard.add(open_dateLabel, BorderLayout.SOUTH);
        return jobCard;
    }

    public void switchToPanel(String panelName) {
        cardLayout.show(contentPanel, panelName);
    }
    
    void refreshJobList() {
        jobList = jobBus.getList();
        revalidate();
        repaint();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản lý tuyển dụng");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);
            frame.setLocationRelativeTo(null);

            TuyenDungGUI tuyenDungGUI = new TuyenDungGUI();
            frame.add(tuyenDungGUI);
            frame.setVisible(true);
        });
    }

}

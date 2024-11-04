package com.hrm.view;

import com.hrm.controller.ApplicantsBUS;
import com.hrm.controller.JobOpeningsBUS;
import com.hrm.model.Applicants;
import com.hrm.model.JobOpenings;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;

public class TuyenDungGUI extends JPanel {
    private ApplicantsBUS applicantsBus = new ApplicantsBUS();
    private JobOpeningsBUS jobBus = new JobOpeningsBUS();
    
    private ArrayList<JobOpenings> jobList;
    private ArrayList<Applicants> applicantList;
    
    public TuyenDungGUI() {
        jobList = new ArrayList<>();
        applicantList = new ArrayList<>();
        init();
    }

    public void init() {
        jobList = new ArrayList<>();
        applicantList = new ArrayList<>();
        setLayout(new BorderLayout());

        JPanel headBar = new JPanel(new BorderLayout());
        
        JPanel toolBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addPostBtn = new JButton("Thêm bài đăng");
        JButton applicantListBtn = new JButton("Danh sách ứng viên");
        JButton scheduleBtn = new JButton("Lịch phỏng vấn");
        
        JPanel searchField = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField search = new JTextField(20);
        JButton previousBtn = new JButton("<");
        JButton nextBtn = new JButton(">");
        JLabel pageCurrent = new JLabel("9");
        ImageIcon filterIcon = new ImageIcon ("icons8-filter-24.png");
        JButton filterBtn = new JButton("",filterIcon);
        
        toolBar.add(addPostBtn);
        toolBar.add(applicantListBtn);
        toolBar.add(scheduleBtn);
        searchField.add(search);
        searchField.add(previousBtn);
        searchField.add(pageCurrent);
        searchField.add(nextBtn);
        searchField.add(filterBtn);
        
        headBar.add(toolBar,BorderLayout.NORTH);
        headBar.add(searchField,BorderLayout.CENTER);
        JPanel recentPanel = createSectionPanel("Gần đây nhất");
        recentPanel.add(createJobCard("Senior IT Business Analyst", "Hồ Chí Minh", "Thương lượng"));
        
        JPanel olderPanel = createSectionPanel("Cũ nhất");
        olderPanel.add(createJobCard("Senior IT Business Analyst", "Hồ Chí Minh", "Thương lượng"));

        JScrollPane scrollPane = new JScrollPane(new JPanel(new BorderLayout()));
        JPanel contentPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        contentPanel.add(recentPanel);
        contentPanel.add(olderPanel);
        scrollPane.setViewportView(contentPanel);

        add(headBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    public JPanel createSectionPanel(String title, List<JobOpenings> jobs) {
        jobList = new ArrayList<>();
        JPanel sectionPanel = new JPanel(new BorderLayout());
        sectionPanel.setBackground(null);
        JLabel titleLabel = new JLabel(title);
        JButton viewAllButton = new JButton("Xem tất cả");
        JPanel header = new JPanel(new BorderLayout());
        header.add(titleLabel, BorderLayout.WEST);
        header.add(viewAllButton, BorderLayout.EAST);
        header.setBackground(Color.orange);
        sectionPanel.add(header, BorderLayout.NORTH);

        JPanel jobsGrid = new JPanel(new GridLayout(3, 3, 10, 10)); 
        sectionPanel.add(jobsGrid, BorderLayout.CENTER);
        return sectionPanel;
        
        for (JobOpenings job : jobList) {
        JPanel jobCard = createJobCard(job.getPosition(), job.getStatus());
        jobsGrid.add(jobCard);
        }
    }

    public JPanel createJobCard(String title, String status) {
        JPanel jobCard = new JPanel(new BorderLayout());
        jobCard.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        jobCard.setPreferredSize(new Dimension(200, 100));

        JLabel titleLabel = new JLabel(title);
        JLabel locationLabel = new JLabel(location);
        JLabel salaryLabel = new JLabel(salary);
        JButton updateButton = new JButton("Cập nhật thông tin");
        updateButton.setBackground(Color.GREEN);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(titleLabel);
        infoPanel.add(locationLabel);
        infoPanel.add(salaryLabel);
        
        jobCard.add(infoPanel, BorderLayout.CENTER);
        jobCard.add(updateButton, BorderLayout.SOUTH);
        return jobCard;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Recruitment Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new TuyenDungGUI());
        frame.setVisible(true);
    }
}

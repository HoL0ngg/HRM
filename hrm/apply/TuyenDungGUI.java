/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tuyenDung;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;

public class TuyenDungGUI extends JPanel {
    public TuyenDungGUI() {
        init();
    }

    public void init() {
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
        // Phần "Gần đây nhất"
        JPanel recentPanel = createSectionPanel("Gần đây nhất");
        recentPanel.add(createJobCard("Senior IT Business Analyst", "Hồ Chí Minh", "Thương lượng"));
        recentPanel.add(createJobCard("Senior IT Business Analyst", "Hồ Chí Minh", "Thương lượng"));
        recentPanel.add(createJobCard("Senior IT Business Analyst", "Hồ Chí Minh", "Thương lượng"));
        recentPanel.add(createJobCard("Senior IT Business Analyst", "Hồ Chí Minh", "Thương lượng"));
        recentPanel.add(createJobCard("Senior IT Business Analyst", "Hồ Chí Minh", "Thương lượng"));
        recentPanel.add(createJobCard("Senior IT Business Analyst", "Hồ Chí Minh", "Thương lượng"));
        
        // Phần "Cũ nhất"
        JPanel olderPanel = createSectionPanel("Cũ nhất");
        olderPanel.add(createJobCard("Senior IT Business Analyst", "Hồ Chí Minh", "Thương lượng"));

        // Cuộn để xem toàn bộ nội dung
        JScrollPane scrollPane = new JScrollPane(new JPanel(new BorderLayout()));
        JPanel contentPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        contentPanel.add(recentPanel);
        contentPanel.add(olderPanel);
        scrollPane.setViewportView(contentPanel);

        // Thêm các thành phần vào panel chính
        add(headBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    // Tạo một panel cho từng phần (Gần đây nhất/Cũ nhất)
    private JPanel createSectionPanel(String title, List<Job> jobs) {
        JPanel sectionPanel = new JPanel(new BorderLayout());
        sectionPanel.setBackground(null);
        JLabel titleLabel = new JLabel(title);
        JButton viewAllButton = new JButton("Xem tất cả");
        JPanel header = new JPanel(new BorderLayout());
        header.add(titleLabel, BorderLayout.WEST);
        header.add(viewAllButton, BorderLayout.EAST);
        header.setBackground(Color.orange);
        sectionPanel.add(header, BorderLayout.NORTH);

        // Grid panel cho các thẻ công việc
        JPanel jobsGrid = new JPanel(new GridLayout(3, 3, 10, 10)); // 2x2 grid
        sectionPanel.add(jobsGrid, BorderLayout.CENTER);
        return sectionPanel;
        
        for (JobOpenings job : jobs) {
        JPanel jobCard = createJobCard(job.title, job.location, job.salary);
        jobsGrid.add(jobCard);
    }
    }

    // Tạo một thẻ công việc
    private JPanel createJobCard(String title, String location, String salary) {
        JPanel jobCard = new JPanel(new BorderLayout());
        jobCard.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        jobCard.setPreferredSize(new Dimension(200, 100));

        // Nội dung thẻ công việc
        JLabel titleLabel = new JLabel(title);
        JLabel locationLabel = new JLabel(location);
        JLabel salaryLabel = new JLabel(salary);
        JButton updateButton = new JButton("Cập nhật thông tin");
        updateButton.setBackground(Color.GREEN);

        // Sắp xếp nội dung trong thẻ công việc
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

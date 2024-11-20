package com.hrm.view;

import com.hrm.controller.ApplicantsBUS;
import com.hrm.controller.EmployeeBus;
import com.hrm.controller.InterviewerBUS;
import com.hrm.controller.InterviewsBUS;
import com.hrm.controller.JobOpeningsBUS;
import com.hrm.model.Applicants;
import com.hrm.model.Interviewer;
import com.hrm.model.Interviews;
import com.hrm.model.JobOpenings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class TuyenDungGUI extends JPanel {

    private static TuyenDungGUI instance;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final int JOB_CARD_WIDTH = 250;
    private static final int JOB_CARD_HEIGHT = 100;

    public static int tb_y = 0;
    public static int tb_x = 0;
    public static int tb_w = 0;
    public static int tb_h = 0;

    private String currentPanel = "Home";

    private JPanel headBar;
    private JPanel mainPanel;
    private JPanel homePanel;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JPanel viewAllJobPanel;
    private JPanel addJobPanel;
    private JPanel applicantPanel;
    private JPanel interviewPanel;
    private JPanel editPanel;

    private CardLayout cardLayout;
    private JButton addJobBtn;
    private JButton jobOpeningListBtn;
    private JButton interviewListBtn;
    private JButton reloadBtn;
    private JButton xuatExcelBtn;

    private JobDetailPanel jobFormPanel;
    private JobDetailPanel jobDetailPanel;
    private JobDetailPanel jobInfoPanel;
    private ApplicantPanel applicantForm;
    private Table applicantTable;
    private Table interviewTable;

    private JobOpeningsBUS jobBus;
    private ArrayList<JobOpenings> jobList;
    private InterviewsBUS intvBus;
    private ArrayList<Interviews> intvList;
    private InterviewerBUS intverBus;
    private ApplicantsBUS applicantBus;
    private EmployeeBus emplBus;
    private ArrayList<Applicants> applicantList;

    public TuyenDungGUI() {
        jobList = new ArrayList<>();
        jobBus = new JobOpeningsBUS();
        applicantBus = new ApplicantsBUS();
        init();
    }

    public static TuyenDungGUI getInstance() {
        if (instance == null) {
            instance = new TuyenDungGUI();
        }
        return instance;
    }

    public void init() {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setBackground(Color.white);

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        headBar = new JPanel(new BorderLayout());
        //headBar.setBounds(20, 20, WIDTH, 170);
        headBar.setPreferredSize(new Dimension(WIDTH, 130));

        JPanel toolBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        toolBar.setBackground(Color.white);

        addJobBtn = createRoundedButton("Thêm bài đăng", "src\\main\\java\\img\\plus.png", 150, 35);
        addJobBtn.putClientProperty("originalColor", addJobBtn.getBackground());
        addJobBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColorButton(addJobBtn);

                addJobPanel.removeAll();
                addJobPanel = new JPanel();
                addJobPanel.setBackground(Color.WHITE);
                addJobPanel.add(jobFormPanel);
                mainPanel.add(addJobPanel, "AddJob");
                togglePanel("AddJob");
            }
        });
        toolBar.add(addJobBtn);
        applyButtonHoverEffect(addJobBtn);

        jobOpeningListBtn = createRoundedButton("Danh sách ứng viên", "src\\main\\java\\img\\rectangle-list.png", 150, 35);
        jobOpeningListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePanel("Applicants");
            }
        });

        toolBar.add(jobOpeningListBtn);
        applyButtonHoverEffect(jobOpeningListBtn);

        interviewListBtn = createRoundedButton("Lịch phỏng vấn", "src\\main\\java\\img\\calendar-day.png", 150, 35);
        interviewListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePanel("Interviews");
            }
        });
        toolBar.add(interviewListBtn);
        applyButtonHoverEffect(interviewListBtn);

        reloadBtn = createRoundedButton("Chỉnh sửa", "src\\main\\java\\img\\wrench-alt.png", 150, 35);
        reloadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPanel.removeAll();
                editPanel = viewAll("Danh sách công việc", new Color(102, 153, 255), 1);
                mainPanel.add(editPanel, "Edit");
                togglePanel("Edit");
            }
        });
        toolBar.add(reloadBtn);
        applyButtonHoverEffect(reloadBtn);

        JPanel searchField = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        searchField.setBackground(Color.white);
        JTextField search = new JTextField("Tìm kiếm", 30);
        search.setFont(new Font("Arial", Font.PLAIN, 15));
        search.setPreferredSize(new Dimension(200, 35));
        search.setForeground(Color.GRAY);
        search.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (search.getText().equals("Tìm kiếm ...")) {
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

        searchField.add(search);
        searchField.add(searchBtn);
        searchField.add(createIconButton("src\\main\\java\\img\\filter_1.png"));

        headBar.add(toolBar, BorderLayout.NORTH);
        headBar.add(searchField, BorderLayout.CENTER);
        return headBar;
    }

    private JButton createRoundedButton(String text, String iconPath, int width, int height) {
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
        JButton button = new RoundedButton(text, 30);
        button.setIcon(icon);
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(new Color(255, 204, 153));
        button.setForeground(Color.BLACK);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        return button;
    }

    private JButton createIconButton(String iconPath) {
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
        JButton button = new JButton(icon);
        button.setBackground(Color.white);
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }

    private JPanel createContentPanel() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        //mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.setBackground(Color.WHITE);

        homePanel = new JPanel(new GridLayout(2, 1, 0, 30));
        homePanel.setBackground(Color.white);
        homePanel.add(createJobPanel("Mới nhất", new Color(255, 204, 0)));
        homePanel.add(createJobPanel("Cũ nhất", new Color(102, 153, 255)));

        scrollPane1 = new JScrollPane(homePanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane1.getVerticalScrollBar().setUnitIncrement(14);
        scrollPane1.setPreferredSize(new Dimension(WIDTH - 146, HEIGHT - 190));

        jobFormPanel = new JobDetailPanel();
        jobFormPanel.addButtonListener(e -> completeBtn());
        jobFormPanel.exitButton(e -> exitButton(0));
        jobFormPanel.setPreferredSize(new Dimension(800, 500));
        addJobPanel = new JPanel();
        addJobPanel.setBackground(Color.WHITE);
        addJobPanel.add(jobFormPanel);

        applicantBus = new ApplicantsBUS();
        applicantList = applicantBus.getList();
        applicantTable = createApplicantTable(applicantList);
        applicantPanel = new JPanel(new BorderLayout());
        applicantPanel.setBackground(Color.WHITE);
        applicantPanel.add(applicantTable, BorderLayout.CENTER);

        intvBus = new InterviewsBUS();
        intvList = intvBus.getList();
        interviewTable = createInterviewTable(intvList);
        interviewPanel = new JPanel(new BorderLayout());
        interviewPanel.setBackground(Color.WHITE);
        interviewPanel.add(interviewTable, BorderLayout.CENTER);

        editPanel = viewAll("Danh sách công việc", new Color(102, 153, 255), 1);

//        scrollPane2 = new JScrollPane(editPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane2.getVerticalScrollBar().setUnitIncrement(14);
//        scrollPane2.setPreferredSize(new Dimension(WIDTH - 146, HEIGHT - 190));
        mainPanel.add(scrollPane1, "Home");
        //mainPanel.add(addJobPanel, "AddJob");
        mainPanel.add(applicantPanel, "Applicants");
        mainPanel.add(interviewPanel, "Interviews");
        mainPanel.add(editPanel, "Edit");

        return mainPanel;
    }

    private JPanel createJobPanel(String title, Color headerColor) {
        JPanel jobPanel = new JPanel(new BorderLayout());
        jobPanel.setBorder(BorderFactory.createLineBorder(headerColor, 2));
        jobPanel.setPreferredSize(new Dimension(WIDTH - 150, 305));//350

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(new EmptyBorder(0, 10, 0, 10));
        header.setPreferredSize(new Dimension(WIDTH - 150, 50));//w700
        header.setBackground(headerColor);

        JLabel titleLabel = new JLabel(title, JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.BLACK);

        JLabel viewAllLabel = new JLabel("Xem tất cả", JLabel.RIGHT);
        viewAllLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        viewAllLabel.setForeground(Color.BLUE);
        viewAllLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        viewAllLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewAllJobPanel = viewAll(title, headerColor, 0);
                mainPanel.add(viewAllJobPanel, "ViewAll");
                togglePanel("ViewAll");
            }
        });
        header.add(titleLabel, BorderLayout.WEST);
        header.add(viewAllLabel, BorderLayout.EAST);
        jobPanel.add(header, BorderLayout.NORTH);

        JPanel jobsGrid = new JPanel(new GridLayout(2, 3, 35, 20));
        jobsGrid.setBorder(new EmptyBorder(15, 15, 15, 15));
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
        } else if ("Cũ nhất".equals(title)) {
            sortedJobList = jobList.stream()
                    .filter(job -> job.getOpening_date() != null)
                    .sorted(Comparator.comparing(JobOpenings::getOpening_date))
                    .limit(6)
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            sortedJobList = new ArrayList<>();
        }

        for (JobOpenings job : sortedJobList) {
            String openingDateString = job.getOpening_date().format(formatter);
            jobsGrid.add(createInfoJobCard(job.getPosition(), job.getSalary(), openingDateString, job.getId()));
        }

        jobPanel.add(jobsGrid, BorderLayout.CENTER);
        return jobPanel;
    }

    private JPanel createEditJobCard(String title, String salary, String openDate, int jobId) {
        RoundedPanel jobCard = new RoundedPanel(25);
        jobCard.setLayout(new BorderLayout());
        jobCard.setPreferredSize(new Dimension(JOB_CARD_WIDTH, JOB_CARD_HEIGHT));
        jobCard.setBackground(Color.WHITE);

        jobCard.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

        JLabel titleLabel = new JLabel("  " + title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel locationLabel = new JLabel("  " + salary);
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        locationLabel.setForeground(Color.DARK_GRAY);

        JLabel open_dateLabel = new JLabel("  Ngày đăng: " + openDate);
        open_dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        open_dateLabel.setForeground(Color.DARK_GRAY);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(titleLabel);
        infoPanel.add(locationLabel);
        infoPanel.add(open_dateLabel);

        jobCard.add(infoPanel, BorderLayout.CENTER);

        jobCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                jobDetailPanel = new JobDetailPanel(jobId);
                jobDetailPanel.editButton(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        editButton(jobId);
                    }

                });
                jobDetailPanel.deleteButton(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deleteButton(jobId);
                    }

                });
                jobDetailPanel.exitButton(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        exitButton(1);
                    }

                });

                jobDetailPanel.setPreferredSize(new Dimension(800, 500));
                editPanel.removeAll();
                editPanel = new JPanel();
                editPanel.setBackground(Color.WHITE);
                editPanel.add(jobDetailPanel);

                mainPanel.add(editPanel, "JobDetail");
                togglePanel("JobDetail");
            }
        });
        return jobCard;
    }

    private JPanel createInfoJobCard(String title, String salary, String openDate, int jobId) {
        RoundedPanel jobCard = new RoundedPanel(25);
        jobCard.setLayout(new BorderLayout());
        jobCard.setPreferredSize(new Dimension(JOB_CARD_WIDTH, JOB_CARD_HEIGHT));
        jobCard.setBackground(Color.WHITE);

        jobCard.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

        JLabel titleLabel = new JLabel("  " + title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel locationLabel = new JLabel("  " + salary);
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        locationLabel.setForeground(Color.DARK_GRAY);

        JLabel open_dateLabel = new JLabel("  Ngày đăng: " + openDate);
        open_dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        open_dateLabel.setForeground(Color.DARK_GRAY);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(titleLabel);
        infoPanel.add(locationLabel);
        infoPanel.add(open_dateLabel);

        jobCard.add(infoPanel, BorderLayout.CENTER);

        jobCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                jobInfoPanel = new JobDetailPanel(jobId, 1);
                jobInfoPanel.applyButton(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        applyButton(jobId);
                    }

                });
                jobInfoPanel.exitButton(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        exitButton(0);
                    }

                });

                jobInfoPanel.setPreferredSize(new Dimension(800, 500));
                addJobPanel.removeAll();
                addJobPanel = new JPanel();
                addJobPanel.setBackground(Color.WHITE);
                addJobPanel.add(jobInfoPanel);

                mainPanel.add(addJobPanel, "JobInfo");
                togglePanel("JobInfo");
            }
        });
        return jobCard;
    }

    private void applyButtonHoverEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(255, 204, 153));
            }
        });
    }

    private void changeColorButton(JButton button) {
        Color originalColor = (Color) button.getClientProperty("originalColor"); // Lấy màu gốc từ client property
        Color currentColor = button.getBackground(); // Lấy màu hiện tại của nút

        // Nếu màu hiện tại là màu gốc, đổi sang màu mới, ngược lại, quay lại màu gốc
        if (currentColor.equals(originalColor)) {
            button.setBackground(Color.DARK_GRAY); // Đổi sang màu khi nhấn
        } else {
            button.setBackground(originalColor); // Quay lại màu gốc
        }
    }

    // Hàm để bật/tắt panel theo tên
    private void togglePanel(String panelName) {
        if (currentPanel.equals(panelName)) {
            // Nếu đang ở panel này, quay về homePanel
            cardLayout.show(mainPanel, "Home");
            currentPanel = "Home";
        } else {
            // Hiển thị panel được chọn và cập nhật trạng thái
            cardLayout.show(mainPanel, panelName);
            currentPanel = panelName;
        }
    }

// Hàm reload dữ liệu card hiện tại
    private void reloadBtn() {
        // Kiểm tra card hiện tại và làm mới dữ liệu tương ứng
        switch (currentPanel) {
            case "Home":
                // Làm mới danh sách công việc hoặc dữ liệu liên quan
                loadJobList();
                break;
            case "AddJob":
                // Làm mới form thêm công việc (reset form)
                resetAddJobForm();
                break;
            case "Applicants":
                // Làm mới danh sách ứng viên
                loadApplicantList();
                break;
            case "Interviews":
                // Làm mới danh sách lịch phỏng vấn
                loadInterviewSchedule();
                break;
            default:
                break;
        }
    }

// Các hàm làm mới dữ liệu cụ thể cho từng panel
    private void loadJobList() {
        jobList = jobBus.getList();

        homePanel.removeAll();
        homePanel = new JPanel(new GridLayout(2, 1, 0, 30));
        homePanel.setBackground(Color.white);
        homePanel.add(createJobPanel("Mới nhất", new Color(255, 204, 0)));
        homePanel.add(createJobPanel("Cũ nhất", new Color(102, 153, 255)));
        homePanel.revalidate();
        homePanel.repaint();

        //scrollPane.removeAll();
        scrollPane1 = new JScrollPane(homePanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane1.getVerticalScrollBar().setUnitIncrement(14);
        //scrollPane.setPreferredSize(new Dimension(WIDTH - 146, HEIGHT - 190));

        mainPanel.add(scrollPane1, "Home");
        mainPanel.revalidate();
        mainPanel.repaint();

    }

    private void resetAddJobForm() {
        // Code để reset form thêm công việc
        System.out.println("Đang reset form thêm công việc...");
    }

    private void loadApplicantList() {
        // Code để tải lại danh sách ứng viên
        System.out.println("Đang tải lại danh sách ứng viên...");
    }

    private void loadInterviewSchedule() {
        // Code để tải lại lịch phỏng vấn
        System.out.println("Đang tải lại lịch phỏng vấn...");
    }

    private JPanel viewAll(String title, Color headerColor, int flag) {
        jobList = jobBus.getList();

        JPanel jobPanel = new JPanel(new BorderLayout());
        //viewAllJobPanel = new JPanel(new BorderLayout());
        jobPanel.setBorder(BorderFactory.createLineBorder(headerColor, 2));
        jobPanel.setPreferredSize(new Dimension(WIDTH - 146, HEIGHT - 190));

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(new EmptyBorder(0, 10, 0, 10));
        header.setPreferredSize(new Dimension(WIDTH - 146, 50));
        header.setBackground(headerColor);

        JLabel titleLabel = new JLabel(title, JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.BLACK);

        JLabel viewAllLabel = new JLabel("Xem tất cả", JLabel.RIGHT);
        viewAllLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        viewAllLabel.setForeground(Color.BLUE);
        viewAllLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        viewAllLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                togglePanel("Home");
            }
        });
        header.add(titleLabel, BorderLayout.WEST);
        //header.add(viewAllLabel, BorderLayout.EAST);
        jobPanel.add(header, BorderLayout.NORTH);

        ArrayList<JobOpenings> sortedJobList = "Mới nhất".equals(title)
                ? jobList.stream()
                        .sorted(Comparator.comparing(JobOpenings::getOpening_date).reversed())
                        .collect(Collectors.toCollection(ArrayList::new))
                : jobList.stream()
                        .sorted(Comparator.comparing(JobOpenings::getOpening_date))
                        .collect(Collectors.toCollection(ArrayList::new));

        int row;
        if (jobList.size() % 3 != 0) {
            row = jobList.size() / 3 + 1;
        } else {
            row = jobList.size() / 3;
        }
        JPanel jobsGrid = new JPanel(new GridLayout(row, 3, 35, 20));
        jobsGrid.setBorder(new EmptyBorder(15, 15, 15, 15));
        //jobsGrid.setPreferredSize(new Dimension(WIDTH - 80, HEIGHT - 170 - 50));
        jobsGrid.setBackground(Color.white);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (flag == 0) {
            header.add(viewAllLabel, BorderLayout.EAST);

            for (JobOpenings job : sortedJobList) {
                String openingDateString = job.getOpening_date().format(formatter);
                jobsGrid.add(createInfoJobCard(job.getPosition(), job.getSalary(), openingDateString, job.getId()));
            }
        } else if (flag == 1) {
            for (JobOpenings job : sortedJobList) {
                String openingDateString = job.getOpening_date().format(formatter);
                jobsGrid.add(createEditJobCard(job.getPosition(), job.getSalary(), openingDateString, job.getId()));
            }
        }

        JScrollPane jobScrollPane = new JScrollPane(jobsGrid, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jobScrollPane.setPreferredSize(new Dimension(WIDTH - 148, HEIGHT - 190 - 50));
        jobScrollPane.getVerticalScrollBar().setUnitIncrement(14);
        jobScrollPane.setBackground(Color.white);
        jobPanel.add(jobScrollPane, BorderLayout.CENTER);

        return jobPanel;
    }

    public Table createApplicantTable(ArrayList<Applicants> applicantList) {
        Table applicantTable = new Table();

        String[] headers = {"STT", "Họ Tên", "Vị Trí Ứng Tuyển", "Phòng ban", "Ngày Nộp", "Trạng Thái", "Link CV"};
        applicantTable.setHeaders(headers);

        int index = 0;
        intvBus = new InterviewsBUS();

        // Lưu danh sách ứng viên
        ArrayList<Applicants> currentApplicantList = applicantList;

        for (Applicants applicant : applicantList) {
            index++;
            String position = intvBus.getPositionByApplicantId(applicant.getId());
            String department = intvBus.getDepartmentName(applicant.getId());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = applicant.getApplicant_date().format(formatter);

            applicantTable.addRow(new String[]{
                Integer.toString(index), // STT
                applicant.getFull_name(), // Họ Tên
                position, // Vị Trí
                department, // Phòng ban
                date, // Ngày Nộp
                null, // Trạng Thái (có thể cập nhật sau)
                applicant.getResume() // Link CV
            });
        }

        // Tự động điều chỉnh chiều rộng cột
        applicantTable.resizeColumnWidth();

        // Lắng nghe sự kiện nhấn vào hàng
        applicantTable.getTable().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable table = (JTable) evt.getSource();
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy đối tượng ứng viên dựa vào chỉ số dòng
                    Applicants selectedApplicant = currentApplicantList.get(table.convertRowIndexToModel(selectedRow));

                    // Lấy ID bằng getId()
                    int id = selectedApplicant.getId();
                    applicantForm = new ApplicantPanel(id);
                    applicantForm.setPreferredSize(new Dimension(800, 500));
                    applicantForm.addApplicantButton(e -> addApplicant());
                    applicantForm.exitButton(e -> exitButton(2));
                    addJobPanel.removeAll();
                    addJobPanel = new JPanel();
                    addJobPanel.setBackground(Color.WHITE);
                    addJobPanel.add(applicantForm);
                    mainPanel.add(addJobPanel, "Update_Applicant");
                    togglePanel("Update_Applicant");
                }
            }
        });

        return applicantTable;
    }

    public Table createInterviewTable(ArrayList<Interviews> intvList) {
        Table intvTable = new Table();

        String[] headers = {"STT", "Họ Tên", "Vị Trí Ứng Tuyển", "Thời Gian", "Trạng Thái", "Người Phỏng Vấn"};
        intvTable.setHeaders(headers);

        // Duyệt qua danh sách ứng viên và thêm vào bảng
        int index = 0;
        intvBus = new InterviewsBUS();
        intverBus = new InterviewerBUS();
        for (Interviews intv : intvList) {
            index++;
            // Lấy thông tin vị trí và phòng ban từ intvBus
            String applicantName = intvBus.getFullNameById(intv.getId());
            String position = intvBus.getPositionById(intv.getId());
            String intverName = intverBus.getFullNamesById(intv.getId());

            // Định dạng ngày tháng
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = intv.getInterview_date().format(formatter);

            // Thêm hàng dữ liệu vào bảng
            intvTable.addRow(new Object[]{
                Integer.toString(index), // STT
                applicantName, // Họ tên
                position, // Vị trí
                date, // Thời gian
                intv.getInterview_stage(), // Trạng thái (dùng String thay vì JComboBox)
                intverName // Người phỏng vấn
            });
        }

        // Tạo và áp dụng TableCellRenderer cho cột trạng thái (cột thứ 4 trong bảng)
        intvTable.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = (String) value; // Lấy giá trị của trạng thái từ bảng

                // Tùy chỉnh màu nền của ô trạng thái
                if ("In progress".equals(status)) {
                    cell.setBackground(new Color(255, 51, 51)); // Màu đỏ cho "In progress"
                } else if ("Completed".equals(status)) {
                    cell.setBackground(new Color(153, 255, 153)); // Màu xanh lá cho "Completed"
                } else if ("Pending".equals(status)) {
                    cell.setBackground(new Color(51, 153, 255)); // Màu xanh dương cho "Pending"
                } else {
                    cell.setBackground(Color.WHITE); // Màu mặc định
                }

                setHorizontalAlignment(CENTER);

                return cell;
            }
        });

        // Tự động điều chỉnh chiều rộng cột theo nội dung
        intvTable.resizeColumnWidth();

        // Thiết lập chiều cao của từng hàng trong bảng
        // intvTable.setRowHeight(50); // Nếu muốn thay đổi chiều cao
        // Thêm bảng vào mainPanel
        return intvTable;
    }

//   PHẦN XỬ KIẾN SỰ KIỆN NÚT TRONG CÁC CHỨC NĂNG  
//   nút thoát
    private void exitButton(int flag) {
        if (flag == 0) {
            togglePanel("Home");
        } else if (flag == 1) {
            editPanel.removeAll();
            editPanel = viewAll("Danh sách công việc", new Color(102, 153, 255), 1);
            mainPanel.add(editPanel, "Edit");
            togglePanel("Edit");
        } else if (flag == 2) {
            togglePanel("Home");
        }

    }

    //   Nút hoàn tất khi thêm bài đăng
    private void completeBtn() {
        int id = jobBus.getNextId();
        String position = jobFormPanel.positionField.getText().trim();
        String departmentName = (String) jobFormPanel.departmentComboBox.getSelectedItem();
        int departmentId = jobFormPanel.getDepartmentId(departmentName);
        String status = (String) jobFormPanel.statusComboBox.getSelectedItem();
        String salary = jobFormPanel.salaryField.getText().trim();
        String description = jobFormPanel.descriptionArea.getText().trim();

        LocalDate openingDate = LocalDate.now();

        LocalDate endDate = (jobFormPanel.endDateChooser.getDate() != null)
                ? new java.sql.Date(jobFormPanel.endDateChooser.getDate().getTime()).toLocalDate()
                : openingDate.plusMonths(1);
        if (endDate.isBefore(openingDate)) {
            JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau ngày bắt đầu!");
            return;
        }

        if (position.isEmpty() || departmentId == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin bắt buộc.");
            return;
        }

        JobOpenings job = new JobOpenings(id, departmentId, position, salary, openingDate, endDate, status, description);
        jobBus.add(job);
        JOptionPane.showMessageDialog(this, "Thông tin đã được lưu!");
        loadJobList();
        togglePanel("Home");
    }

    private void editButton(int id) {
        JobOpenings job = jobBus.get(id);
        if (job != null) {
            // ngày bắt đầu không thay đổi được
            LocalDate openingDate = new java.sql.Date(jobDetailPanel.openDateChooser.getDate().getTime()).toLocalDate();
            jobDetailPanel.openDateChooser.setDate(java.sql.Date.valueOf(openingDate));

            job.setPosition(jobDetailPanel.positionField.getText().trim());
            String departmentName = (String) jobDetailPanel.departmentComboBox.getSelectedItem();
            int departmentId = jobDetailPanel.getDepartmentId(departmentName);
            job.setDepartment_id(departmentId);
            job.setStatus((String) jobDetailPanel.statusComboBox.getSelectedItem());

            LocalDate endDate = new java.sql.Date(jobDetailPanel.endDateChooser.getDate().getTime()).toLocalDate();
            if (endDate.isBefore(openingDate)) {
                JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau ngày bắt đầu!");
                return;
            }
            job.setClosing_date(endDate);

            job.setSalary(jobDetailPanel.salaryField.getText().trim());
            job.setDetail(jobDetailPanel.descriptionArea.getText().trim());

            jobBus.set(job);

            JOptionPane.showMessageDialog(null, "Cập nhật công việc thành công!");
            loadJobList();
            togglePanel("Home");
        }
    }

    private void deleteButton(int id) {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa công việc này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {

            jobBus.delete(id);

            JOptionPane.showMessageDialog(this, "Xóa công việc thành công!");
        }
        loadJobList();
        togglePanel("Home");
    }

    private void applyButton(int id) {
        applicantForm = new ApplicantPanel(id, 0);
        applicantForm.setPreferredSize(new Dimension(800, 500));
        applicantForm.addApplicantButton(e -> addApplicant());
        applicantForm.exitButton(e -> exitButton(2));
        addJobPanel.removeAll();
        addJobPanel = new JPanel();
        addJobPanel.setBackground(Color.WHITE);
        addJobPanel.add(applicantForm);
        mainPanel.add(addJobPanel, "Suggest_Applicant");
        togglePanel("Suggest_Applicant");
    }

    private void addApplicant() {
        // Lấy thông tin ứng viên
        int applicantId = applicantBus.getNextId();
        String name = applicantForm.nameField.getText().trim();
        String email = applicantForm.emailField.getText().trim();
        String phone = applicantForm.phoneField.getText().trim();
        String resume = applicantForm.cvLinkField.getText().trim();
        LocalDate applicantDate = LocalDate.now();

        // Kiểm tra thông tin ứng viên
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || resume.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Phải điền đầy đủ các thông tin ứng viên!");
            return;
        }

        Applicants applicant = new Applicants(applicantId, name, email, phone, resume, applicantDate);
        applicantBus.add(applicant);

        JOptionPane.showMessageDialog(this, "Thông tin đã được lưu!");
        loadJobList();
        togglePanel("Home");
    }

    private void updateApplicant() {
        // Lấy thông tin ứng viên
        int applicantId = applicantBus.getNextId();
        String name = applicantForm.nameField.getText().trim();
        String email = applicantForm.emailField.getText().trim();
        String phone = applicantForm.phoneField.getText().trim();
        String resume = applicantForm.cvLinkField.getText().trim();
        LocalDate applicantDate = LocalDate.now();

        // Kiểm tra thông tin ứng viên
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || resume.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Phải điền đầy đủ các thông tin ứng viên!");
            return;
        }

        Applicants applicant = new Applicants(applicantId, name, email, phone, resume, applicantDate);
        applicantBus.add(applicant);

        //them lich phong van
        int intvId = intvBus.getNextId();
        String position = applicantForm.positionField.getText().trim();
        int jobId = jobBus.getIdByPosition(position);
        LocalDate intvDate = null;
        if (applicantForm.dateChooser.getDate() != null) {
            intvDate = new java.sql.Date(applicantForm.dateChooser.getDate().getTime()).toLocalDate();
        }

        LocalTime intvTime = null;
        if (applicantForm.interviewTimeSpinner.getValue() != null) {
            Date spinnerValue = (Date) applicantForm.interviewTimeSpinner.getValue();
            intvTime = spinnerValue.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalTime();
        }

        if (intvDate != null && intvDate.isBefore(applicantDate)) {
            JOptionPane.showMessageDialog(null, "Ngày phỏng vấn không được trước ngày ứng tuyển!");
            return;
        }

        if (intvDate != null && intvTime != null) {
            LocalDateTime interviewDateTime = LocalDateTime.of(intvDate, intvTime);
            if (interviewDateTime.isBefore(LocalDateTime.now())) {
                JOptionPane.showMessageDialog(null, "Thời gian phỏng vấn không được trước thời điểm hiện tại!");
                return;
            }
        }

        String stage = "Pending";
        String note = applicantForm.noteArea.getText().trim();
        String result = (String) applicantForm.resultComboBox.getSelectedItem();
        Interviews intv = new Interviews(intvId, jobId, applicantId, intvDate, intvTime, stage, note, result);
        intvBus.add(intv);

        //them nguoi phong van
        String name1 = (String) applicantForm.interviewer1ComboBox.getSelectedItem();
        String name2 = (String) applicantForm.interviewer2ComboBox.getSelectedItem();
        String name3 = (String) applicantForm.interviewer3ComboBox.getSelectedItem();
        name1 = (name1 != null && !name1.trim().isEmpty()) ? name1 : null;
        name2 = (name2 != null && !name2.trim().isEmpty()) ? name2 : null;
        name3 = (name3 != null && !name3.trim().isEmpty()) ? name3 : null;

        if (name1 != null) {
            int id1 = emplBus.getIdByName(name1);
            Interviewer intver = new Interviewer(id1, intvId);
            intverBus.add(intver);
        }
        if (name2 != null) {
            int id2 = emplBus.getIdByName(name2);
            Interviewer intver = new Interviewer(id2, intvId);
            intverBus.add(intver);
        }
        if (name3 != null) {
            int id3 = emplBus.getIdByName(name3);
            Interviewer intver = new Interviewer(id3, intvId);
            intverBus.add(intver);
        }

        JOptionPane.showMessageDialog(this, "Thông tin đã được lưu!");
        loadJobList();
        togglePanel("Home");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Recruitment Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.add(new TuyenDungGUI());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}

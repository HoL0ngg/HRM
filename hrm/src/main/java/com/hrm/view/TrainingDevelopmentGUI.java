package com.hrm.view;

import com.hrm.controller.TrainerBUS;
import com.hrm.controller.TrainingDevelopmentBUS;
import com.hrm.controller.AchievementsBUS;
import com.hrm.controller.EmployeeBus;
import com.hrm.model.Achievements;
import com.hrm.model.Trainer;
import com.hrm.model.TrainingDevelopment;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TrainingDevelopmentGUI extends JPanel {

//    private static final int WIDTH = 1000;
//    private static final int HEIGHT = 700;
//
//    public static int tb_y = 0;
//    public static int tb_x = 0;
//    public static int tb_w = 0;
//    public static int tb_h = 0;
//
//    private String currentPanel = "Train";
//
//    private JPanel switchPanel;
//    private JPanel headPanel;
//    private JPanel mainPanel;
//    private JPanel trainPanel;
//    private JPanel achievementPanel;
//    private JPanel addPanel;
//    private JPanel editPanel;
//
//    private JPanel searchResult;
//    private JPanel searchPanel;
//    private JPanel searchField;
//    private Table trainingTable;
//    private Table achievementTable;
//
//    private CardLayout cardLayout;
//    private JPanel trainBtn;
//    private JPanel achievementBtn;
//    private JButton addBtn;
//    private JButton clear;
//    private JTextField search;
//
//    private ArrayList<TrainingDevelopment> trainingList;
//    private TrainingDevelopmentBUS trainingBus;
//    private TrainerBUS trainerBus;
//    private ArrayList<Achievements> achieveList;
//    private AchievementsBUS achieveBus;
//    private EmployeeBus eBus;
//    private TrainingFormPanel trainForm;
//    private AchievementFormPanel achievementForm;
//
//    public TrainingDevelopmentGUI() {
//        trainingBus = new TrainingDevelopmentBUS();
//        trainerBus = new TrainerBUS();
//        achieveBus = new AchievementsBUS();
//        eBus = new EmployeeBus();
//        init();
//    }
//
//    public void init() {
//        setLayout(new BorderLayout());
//        setSize(WIDTH, HEIGHT);
//        setBackground(Color.white);
//
//        add(createHeaderPanel(), BorderLayout.NORTH);
//        add(createContentPanel(), BorderLayout.CENTER);
//    }
//
//    private JPanel createHeaderPanel() {
//
//        trainBtn = new JPanel(new BorderLayout());
//        trainBtn.setBackground(Color.WHITE);
//        JLabel lb1 = new JLabel("Đào tạo và phát triển", SwingConstants.CENTER);
//        lb1.setFont(new Font("Arial", Font.BOLD, 18));
//        lb1.setForeground(Color.BLACK);
//        trainBtn.add(lb1, BorderLayout.CENTER);
//        trainBtn.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                trainPanel.removeAll();
//                trainPanel = new JPanel(new BorderLayout());
//                trainPanel.add(trainingTable, BorderLayout.CENTER);
//                mainPanel.add(trainPanel, "Train");
//                currentPanel = "Train";
//                toggleButton("Train");
//                cardLayout.show(mainPanel, "Train");
//            }
//        });
//
//        achievementBtn = new JPanel(new BorderLayout());
//        achievementBtn.setBackground(Color.LIGHT_GRAY);
//        JLabel lb2 = new JLabel("Thành tựu", SwingConstants.CENTER);
//        lb2.setFont(new Font("Arial", Font.BOLD, 18));
//        lb2.setForeground(Color.BLACK);
//        achievementBtn.add(lb2, BorderLayout.CENTER);
//        achievementBtn.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                achievementPanel.removeAll();
//                achievementPanel = new JPanel(new BorderLayout());
//                achievementPanel.add(achievementTable, BorderLayout.CENTER);
//                mainPanel.add(achievementPanel, "Achievement");
//                currentPanel = "Achievement";
//                toggleButton("Achievement");
//                cardLayout.show(mainPanel, "Achievement");
//            }
//        });
//
//        switchPanel = new JPanel(new GridLayout(1, 2));
//        switchPanel.setPreferredSize(new Dimension(WIDTH, 50));
//        switchPanel.add(trainBtn);
//        switchPanel.add(achievementBtn);
//
//        search = new JTextField("Tìm kiếm...", 30);
//        search.setFont(new Font("Arial", Font.PLAIN, 15));
//        search.setPreferredSize(new Dimension(150, 35));
//        search.setForeground(Color.GRAY);
//        search.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                if (search.getText().equals("Tìm kiếm...")) {
//                    search.setText("");
//                    search.setForeground(Color.BLACK);
//                }
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                if (search.getText().isEmpty() || search.getText().equals("")) {
//                    search.setText("Tìm kiếm...");
//                    search.setForeground(Color.GRAY);
//                }
//            }
//        });
//
//        search.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                    String searchText = search.getText();
//                    searchAndUpdateTable(searchText);
//                }
//            }
//        });
//
//        clear = new JButton("X");
//        clear.setBackground(new Color(220, 53, 69)); // Màu đỏ tươi hơn
//        clear.setForeground(Color.WHITE);
//        clear.setFont(new Font("Arial", Font.BOLD, 16)); // Tăng kích thước và làm đậm chữ
//        clear.setFocusPainted(false); // Tắt viền khi nhấn vào nút
//        clear.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Khoảng cách trong nút
//        clear.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Đổi con trỏ chuột thành dạng tay
//
//// Hiệu ứng hover
//        clear.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                clear.setBackground(new Color(255, 71, 87)); // Màu sáng hơn khi hover
//            }
//
//            @Override
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                clear.setBackground(new Color(220, 53, 69)); // Quay lại màu ban đầu
//            }
//        });
//
//        clear.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                search.setText("Tìm kiếm...");
//                search.setForeground(Color.GRAY);
//                clearTable();
//            }
//        });
//
//        searchField = new JPanel(new BorderLayout());
//        searchField.setPreferredSize(new Dimension(250, 30));
//        searchField.add(search, BorderLayout.CENTER);
//        searchField.add(clear, BorderLayout.EAST);
//
//        addBtn = new JButton("Thêm");
//        addBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                if (currentPanel.equals("Train")) {
//                    trainForm = new TrainingFormPanel();
//                    trainForm.setPreferredSize(new Dimension(800, 500));
//                    trainForm.completeButton(a -> addButton(1));
//                    trainForm.exitButton(a -> update(1));
//                    addPanel.removeAll();
//                    addPanel = new JPanel();
//                    addPanel.setBackground(Color.WHITE);
//                    addPanel.add(trainForm);
//                    mainPanel.add(addPanel, "Add");
//                    cardLayout.show(mainPanel, "Add");
//                } else {
//                    achievementForm = new AchievementFormPanel();
//                    achievementForm.setPreferredSize(new Dimension(800, 500));
//                    achievementForm.completeButton(a -> addButton(2));
//                    achievementForm.exitButton(a -> update(2));
//                    addPanel.removeAll();
//                    addPanel = new JPanel();
//                    addPanel.setBackground(Color.WHITE);
//                    addPanel.add(achievementForm);
//                    mainPanel.add(addPanel, "Add");
//                    cardLayout.show(mainPanel, "Add");
//                }
//
//            }
//        });
//
//        searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
//        searchPanel.setBackground(Color.WHITE);
//        searchPanel.add(searchField);
//        searchPanel.add(addBtn);
//
//        headPanel = new JPanel(new BorderLayout());
//        headPanel.setPreferredSize(new Dimension(WIDTH, 110));
//        headPanel.add(switchPanel, BorderLayout.NORTH);
//        headPanel.add(searchPanel, BorderLayout.CENTER);
//        return headPanel;
//    }
//
//    private JPanel createContentPanel() {
//
//        trainingList = trainingBus.getList();
//        trainingTable = createTrainingTable(trainingList);
//        trainPanel = new JPanel(new BorderLayout());
//        trainPanel.add(trainingTable, BorderLayout.CENTER);
//
//        achieveList = achieveBus.getList();
//        achievementTable = createAchievementTable(achieveList);
//        achievementPanel = new JPanel(new BorderLayout());
//        achievementPanel.add(achievementTable, BorderLayout.CENTER);
//
//        trainForm = new TrainingFormPanel();
//        trainForm.setPreferredSize(new Dimension(800, 500));
//
//        achievementForm = new AchievementFormPanel();
//        achievementForm.setPreferredSize(new Dimension(800, 500));
//
//        addPanel = new JPanel();
//        addPanel.setBackground(Color.WHITE);
//        addPanel.add(trainForm);
//
//        editPanel = new JPanel();
//
//        cardLayout = new CardLayout();
//        mainPanel = new JPanel(cardLayout);
//        mainPanel.setBackground(Color.WHITE);
//        mainPanel.add(trainPanel, "Train");
//        mainPanel.add(achievementPanel, "Achievement");
//        mainPanel.add(addPanel, "Add");
//        mainPanel.add(editPanel, "Edit");
//
//        return mainPanel;
//    }
//
//    private Table createTrainingTable(ArrayList<TrainingDevelopment> trainingList) {
//        Table table = new Table();
//
//        String[] headers = {"STT", "Chủ đề", "Miêu tả", "Người đào tạo", "Ngày bắt đầu", "Ngày kết thúc", "Trạng Thái"};
//        table.setHeaders(headers);
//
//        int index = 0;
//
//        ArrayList<TrainingDevelopment> list = trainingList;
//
//        for (TrainingDevelopment td : list) {
//            index++;
//            String host = trainerBus.getHostById(td.getId());
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            String startDate = td.getStartDate().format(formatter);
//            String endDate = td.getEndDate().format(formatter);
//
//            table.addRow(new String[]{
//                Integer.toString(index),
//                td.getName(),
//                td.getDescription(),
//                host,
//                startDate,
//                endDate,
//                //td.getStatus()
//            });
//        }
//
//        table.resizeColumnWidth();
//
//        table.getTable().addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                JTable Table = (JTable) evt.getSource();
//                int selectedRow = table.getSelectedRow();
//                if (selectedRow != -1) {
//                    TrainingDevelopment select = list.get(Table.convertRowIndexToModel(selectedRow));
//
//                    int id = select.getId();
//                    trainForm = new TrainingFormPanel(id);
//                    trainForm.setPreferredSize(new Dimension(800, 500));
//                    trainForm.exitButton(e -> update(1));
//                    trainForm.editButton(e -> editButton(id, 1));
//                    trainForm.deleteButton(e -> deleteButton(id, 1));
//
//                    trainPanel.removeAll();
//                    trainPanel = new JPanel();
//                    trainPanel.setBackground(Color.WHITE);
//                    trainPanel.add(trainForm);
//                    mainPanel.add(trainPanel, "UpdateTraining");
//                    cardLayout.show(mainPanel, "UpdateTraining");
//                }
//            }
//        });
//        return table;
//    }
//
//    private Table createAchievementTable(ArrayList<Achievements> achieveList) {
//        Table table = new Table();
//
//        String[] headers = {"STT", "Nhân viên", "Thành tích", "Miêu tả", "Ngày đạt"};
//        table.setHeaders(headers);
//
//        int index = 0;
//
//        ArrayList<Achievements> list = achieveList;
//
//        for (Achievements a : list) {
//            index++;
//            String nv = eBus.getFullName(a.getEmployeeId());
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            String awardDate = a.getAwardDate().format(formatter);
//
//            table.addRow(new String[]{
//                Integer.toString(index),
//                nv,
//                a.getTitle(),
//                a.getDescription(),
//                awardDate
//            });
//        }
//
//        table.resizeColumnWidth();
//
//        table.getTable().addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                JTable Table = (JTable) evt.getSource();
//                int selectedRow = table.getSelectedRow();
//                if (selectedRow != -1) {
//                    Achievements select = list.get(Table.convertRowIndexToModel(selectedRow));
//
//                    int id = select.getId();
//                    achievementForm = new AchievementFormPanel(id);
//                    achievementForm.setPreferredSize(new Dimension(800, 500));
//                    achievementForm.exitButton(e -> update(2));
//                    achievementForm.editButton(e -> editButton(id, 2));
//                    achievementForm.deleteButton(e -> deleteButton(id, 2));
//
//                    achievementPanel.removeAll();
//                    achievementPanel = new JPanel();
//                    achievementPanel.add(achievementForm);
//                    achievementPanel.setBackground(Color.WHITE);
//                    mainPanel.add(achievementPanel, "UpdateAchieve");
//                    cardLayout.show(mainPanel, "UpdateAchieve");
//                }
//            }
//        });
//
//        return table;
//    }
//
//    private void searchAndUpdateTable(String searchText) {
//        switch (currentPanel) {
//
//            case "Train":
//                ArrayList<TrainingDevelopment> result1 = trainingBus.search(searchText);
//
//                trainingTable = createTrainingTable(result1);
//                trainPanel.removeAll();
//                trainPanel = new JPanel(new BorderLayout());
//                trainPanel.add(trainingTable, BorderLayout.CENTER);
//                mainPanel.add(trainPanel, "TrainResult");
//                currentPanel = "TrainResult";
//                cardLayout.show(mainPanel, "TrainResult");
//                break;
//
//            case "Achievement":
//                ArrayList<Achievements> result2 = achieveBus.search(searchText);
//
//                achievementTable = createAchievementTable(result2);
//                achievementPanel.removeAll();
//                achievementPanel = new JPanel(new BorderLayout());
//                achievementPanel.add(achievementTable, BorderLayout.CENTER);
//                mainPanel.add(achievementPanel, "AchievementResult");
//                currentPanel = "AchievementResult";
//                cardLayout.show(mainPanel, "AchievementResult");
//                break;
//        }
//    }
//
//    private void toggleButton(String activePanel) {
//        if (activePanel.equals("Train")) {
//            trainBtn.setBackground(Color.WHITE);
//            achievementBtn.setBackground(Color.LIGHT_GRAY);
//        } else if (activePanel.equals("Achievement")) {
//            achievementBtn.setBackground(Color.WHITE);
//            trainBtn.setBackground(Color.LIGHT_GRAY);
//        }
//    }
//
//    private void clearTable() {
//        switch (currentPanel) {
//            case "TrainResult":
//                trainingList = trainingBus.getList();
//                trainingTable = createTrainingTable(trainingList);
//                trainPanel.removeAll();
//                trainPanel = new JPanel(new BorderLayout());
//                trainPanel.add(trainingTable, BorderLayout.CENTER);
//                mainPanel.add(trainPanel, "Train");
//                currentPanel = "Train";
//                cardLayout.show(mainPanel, "Train");
//                break;
//
//            case "AchievementResult":
//                achieveList = achieveBus.getList();
//                achievementTable = createAchievementTable(achieveList);
//                achievementPanel.removeAll();
//                achievementPanel = new JPanel(new BorderLayout());
//                achievementPanel.add(achievementTable, BorderLayout.CENTER);
//                mainPanel.add(achievementPanel, "Achievement");
//                currentPanel = "Achievement";
//                cardLayout.show(mainPanel, "Achievement");
//                break;
//        }
//    }
//
//    private void update(int flag) {
//        switch (flag) {
//            case 1:
//                trainingList = trainingBus.getList();
//                trainingTable = createTrainingTable(trainingList);
//                trainPanel = new JPanel(new BorderLayout());
//                trainPanel.add(trainingTable, BorderLayout.CENTER);
//                mainPanel.add(trainPanel, "Train");
//                cardLayout.show(mainPanel, "Train");
//                break;
//            case 2:
//                achieveList = achieveBus.getList();
//                achievementTable = createAchievementTable(achieveList);
//                achievementPanel = new JPanel(new BorderLayout());
//                achievementPanel.add(achievementTable, BorderLayout.CENTER);
//                mainPanel.add(achievementPanel, "Achievement");
//                cardLayout.show(mainPanel, "Achievement");
//                break;
//        }
//    }
//
//    private void addButton(int flag) {
//        switch (flag) {
//            case 1:
//                int id1 = trainingBus.getNextId();
//                String name = trainForm.nameField.getText().trim();
//                String des1 = trainForm.desArea.getText().trim();
//                String status = (String) trainForm.statusComboBox.getSelectedItem();
//                LocalDate start = new java.sql.Date(trainForm.startDateChooser.getDate().getTime()).toLocalDate();
//                LocalDate end = new java.sql.Date(trainForm.endDateChooser.getDate().getTime()).toLocalDate();
//
////                TrainingDevelopment td = new TrainingDevelopment(id1, name, des1, start, end, status);
////                trainingBus.add(td);
//
//                int hostId = eBus.getIdByName((String) trainForm.hostComboBox.getSelectedItem());
//                Trainer t = new Trainer(hostId, id1);
//                trainerBus.add(t);
//
//                JOptionPane.showMessageDialog(null, "Them thành công!");
//                update(1);
//                cardLayout.show(mainPanel, "Train");
//                break;
//            case 2:
//                int id2 = achieveBus.getNextId();
//                int empId = eBus.getIdByName((String) achievementForm.nameComboBox.getSelectedItem());
//                String title = achievementForm.titleField.getText().trim();
//                String des2 = achievementForm.desArea.getText().trim();
//                LocalDate date = new java.sql.Date(achievementForm.awardDateChooser.getDate().getTime()).toLocalDate();
//
//                Achievements a = new Achievements(id2, empId, title, des2, date);
//                achieveBus.add(a);
//                JOptionPane.showMessageDialog(null, "Thêm thành công!");
//                update(2);
//                cardLayout.show(mainPanel, "Achievement");
//                break;
//        }
//    }
//
//    private void editButton(int id, int flag) {
//        switch (flag) {
//            case 1:
//                TrainingDevelopment td = trainingBus.get(id);
//                td.setName(trainForm.nameField.getText().trim());
//                td.setDescription(trainForm.desArea.getText().trim());
//                //td.setStatus((String) trainForm.statusComboBox.getSelectedItem());
//                td.setStartDate(new java.sql.Date(trainForm.startDateChooser.getDate().getTime()).toLocalDate());
//                td.setEndDate(new java.sql.Date(trainForm.endDateChooser.getDate().getTime()).toLocalDate());
//                trainingBus.set(td);
//
//                Trainer t = trainerBus.get(id);
//                t.setEmployeeId(eBus.getIdByName((String) trainForm.hostComboBox.getSelectedItem()));
//                trainerBus.set(t);
//
//                JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
//                update(1);
//                cardLayout.show(mainPanel, "Train");
//                break;
//            case 2:
//                Achievements a = achieveBus.get(id);
//                a.setEmployeeId(eBus.getIdByName((String) achievementForm.nameComboBox.getSelectedItem()));
//                a.setTitle(achievementForm.titleField.getText().trim());
//                a.setDescription(achievementForm.desArea.getText().trim());
//                a.setAwardDate(new java.sql.Date(achievementForm.awardDateChooser.getDate().getTime()).toLocalDate());
//
//                achieveBus.set(a);
//                JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
//                update(2);
//                cardLayout.show(mainPanel, "Achievement");
//                break;
//        }
//    }
//
//    private void deleteButton(int id, int flag) {
//        int confirm;
//        switch (flag) {
//            case 1:
//                confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
//                if (confirm == JOptionPane.YES_OPTION) {
//
//                    trainingBus.delete(id);
//
//                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
//                }
//                update(1);
//                cardLayout.show(mainPanel, "Train");
//                break;
//            case 2:
//                confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
//                if (confirm == JOptionPane.YES_OPTION) {
//
//                    achieveBus.delete(id);
//
//                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
//                }
//                update(2);
//                cardLayout.show(mainPanel, "Achievement");
//                break;
//        }
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Training Panel");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1000, 700);
//        frame.add(new TrainingDevelopmentGUI());
//        frame.setVisible(true);
//        frame.setLocationRelativeTo(null);
//    }
}

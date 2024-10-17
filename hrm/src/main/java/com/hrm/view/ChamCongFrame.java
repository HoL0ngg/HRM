package com.hrm.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.hrm.controller.ChamCongController;
import com.hrm.dao.EmployeeDAO;
import com.hrm.dao.TimeKeepingDAO;
import com.hrm.model.Employee;
import com.hrm.model.TimeKeeping;
import com.toedter.calendar.JDateChooser;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.util.ArrayList;
import java.util.Date;

public class ChamCongFrame extends JFrame {

        private JPanel contentPane;
        private JTable table;
        private Employee employee;
        private JFrame chonGio;

        public ChamCongFrame() {
        }

        public ChamCongFrame(Employee employee) {
                this.employee = employee;
                this.init();
        }

        private void init() {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(800, 700);
                setLocationRelativeTo(null);
                contentPane = new JPanel();
                contentPane.setBackground(Color.WHITE);
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

                setContentPane(contentPane);
                contentPane.setLayout(null);

                ChamCongController controller = new ChamCongController(this);

                String[] colName = {
                                "Ma NV", "Ho va ten", "Ngay", "Gio vao", "Gio ra", "Trang thai",
                                "Gio lam them"
                };
                DefaultTableModel tableModel = new DefaultTableModel(colName, 0) {
                        // Khong cho chinh sua du lieu khi hien thi len Table
                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return false;
                        }
                };
                table = new JTable(tableModel);

                // chinh chieu rong cot
                setColumnWidths();

                // doc data tu db
                LoadData(tableModel);

                // can giua cac cot
                centerAlignAllColumns();

                // kh cho di chuyen cac cot trong table
                table.getTableHeader().setReorderingAllowed(false);

                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(0, 150, 800, 550);
                contentPane.add(scrollPane);

                JPanel navBar = new JPanel();
                navBar.setLayout(null);
                navBar.setBackground(new Color(245, 143, 82));
                navBar.setBounds(0, 0, 800, 40);
                contentPane.add(navBar);

                JLabel TenLabel = new JLabel();
                TenLabel.setText(employee.getName());
                TenLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
                TenLabel.setBounds(600, 15, 210, 15);
                navBar.add(TenLabel);

                Image BackBtn = new ImageIcon(new File("hrm/src/main/resources/img/left-arrow.png").getAbsolutePath())
                                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                JLabel BackLabel = new JLabel(new ImageIcon(BackBtn));
                BackLabel.setBounds(10, 5, 30, 30);
                BackLabel.setName("quaylai");
                BackLabel.addMouseListener(controller);
                navBar.add(BackLabel);

                JLabel lblNewLabel = new JLabel("BANG CHAM CONG");
                lblNewLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
                lblNewLabel.setBounds(60, 14, 180, 18);
                navBar.add(lblNewLabel);

                JLabel lblNewLabel_2 = new JLabel("Tu ngay");
                lblNewLabel_2.setBounds(20, 72, 80, 14);
                contentPane.add(lblNewLabel_2);

                JLabel lblNewLabel_3 = new JLabel("Den ngay");
                lblNewLabel_3.setBounds(240, 72, 80, 14);
                contentPane.add(lblNewLabel_3);

                JDateChooser NgayBatDau = new JDateChooser();
                NgayBatDau.setDate(new Date());
                NgayBatDau.setBounds(75, 65, 140, 30);

                contentPane.add(NgayBatDau);

                JDateChooser NgayKetThuc = new JDateChooser();
                NgayKetThuc.setDate(new Date()); // Đặt ngày mặc định
                NgayKetThuc.setBounds(300, 65, 140, 30);

                contentPane.add(NgayKetThuc);

                RoundedPanel TimKiemPanel = new RoundedPanel(20);
                TimKiemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                TimKiemPanel.setBounds(480, 65, 250, 30);
                Image TimKiemIcon = new ImageIcon(
                                new File("hrm/src/main/resources/img/search.png").getAbsolutePath())
                                .getImage()
                                .getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                JLabel timkiemLabel = new JLabel(new ImageIcon(TimKiemIcon));
                timkiemLabel.setPreferredSize(new Dimension(35, 20));
                JTextField TimKiemField = new JTextField();
                TimKiemPanel.setBackground(Color.white);
                TimKiemField.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
                TimKiemField.setPreferredSize(new Dimension(200, 20));
                TimKiemField.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
                TimKiemField.setText("Tim kiem");
                TimKiemField.setForeground(Color.gray);
                TimKiemField.addFocusListener(new FocusListener() {
                        public void focusGained(FocusEvent e) {
                                if (String.valueOf(TimKiemField.getText()).equals("Tim kiem")) {
                                        TimKiemField.setText(""); // Xóa placeholder khi focus
                                        TimKiemField.setForeground(Color.BLACK);
                                }
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                                if (String.valueOf(TimKiemField.getText()).isEmpty()) {
                                        TimKiemField.setText("Tim kiem"); // Hiển thị lại placeholder khi
                                                                          // không có dữ liệu
                                        TimKiemField.setForeground(Color.GRAY);
                                }
                        }

                });

                TimKiemPanel.add(timkiemLabel);
                TimKiemPanel.add(TimKiemField);
                TimKiemPanel.setOpaque(true);
                contentPane.add(TimKiemPanel);

                Image FilterIcon = new ImageIcon(
                                new File("hrm/src/main/resources/img/filter.png").getAbsolutePath())
                                .getImage()
                                .getScaledInstance(20, 20, Image.SCALE_SMOOTH);

                JLabel FilterLabel = new JLabel(new ImageIcon(FilterIcon));
                FilterLabel.setBounds(735, 63, 35, 35);
                FilterLabel.setName("filter");
                FilterLabel.addMouseListener(controller);
                contentPane.add(FilterLabel);

                RoundedPanel xuatFilePanel = new RoundedPanel(20);
                xuatFilePanel.setBounds(660, 110, 100, 25);
                xuatFilePanel.setBackground(Color.white);
                JLabel xuatFile = new JLabel("Xuat file");
                xuatFile.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
                xuatFilePanel.add(xuatFile);
                contentPane.add(xuatFilePanel);

                // Tranh focus vao JTextfield tu ban dau
                JPanel emptyJPanel = new JPanel();
                emptyJPanel.setBounds(0, 0, 0, 0);
                contentPane.add(emptyJPanel);
                setVisible(true);
                emptyJPanel.requestFocusInWindow();
        }

        public Employee getEmployee() {
                return employee;
        }

        public JTable getTable() {
                return table;
        }

        public void showPopUp(JLabel label) {
                ChamCongController controller = new ChamCongController(this);
                JPopupMenu popupMenu = new JPopupMenu();
                popupMenu.setPopupSize(120, 74);
                JPanel panel = new JPanel();
                panel.setLayout(null);
                panel.setBounds(0, 0, 120, 74);
                panel.setBackground(Color.white);
                JLabel TheoGio = new JLabel("Theo gio");
                JLabel TheoTrangThai = new JLabel("Theo trang thai");
                TheoGio.setBounds(10, 10, 98, 16);
                TheoGio.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
                TheoGio.setName("TheoGio");
                TheoGio.addMouseListener(controller);
                TheoTrangThai.setBounds(10, 44, 98, 16);
                TheoTrangThai.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
                TheoTrangThai.setName("TheoTrangThai");
                TheoTrangThai.addMouseListener(controller);

                JSeparator separator = new JSeparator();
                separator.setBounds(8, 34, 100, 12);
                panel.add(TheoGio);
                panel.add(separator);
                panel.add(TheoTrangThai);
                popupMenu.add(panel);
                popupMenu.show(label, -90, label.getHeight());
        }

        public void LoadData(DefaultTableModel tableModel) {
                ArrayList<TimeKeeping> arr = TimeKeepingDAO.getInstance().selectAll();
                tableModel.setRowCount(0);
                for (TimeKeeping time : arr) {
                        Object[] rowdata = {
                                        time.getEmployee_id(),
                                        EmployeeDAO.getInstance().seclectByID(time.getEmployee_id()).getName(),
                                        time.getDate(),
                                        time.getCheck_in_time(),
                                        time.getCheck_out_time(),
                                        time.getStatus()
                        };
                        tableModel.addRow(rowdata);
                }
        }

        private void setColumnWidths() {
                // Thiết lập chiều rộng cho từng cột
                table.getColumnModel().getColumn(0).setPreferredWidth(50); // Ma NV
                table.getColumnModel().getColumn(1).setPreferredWidth(200); // Ho ten
                table.getColumnModel().getColumn(2).setPreferredWidth(100); // Ngay
                table.getColumnModel().getColumn(3).setPreferredWidth(60); // Gio vao
                table.getColumnModel().getColumn(3).setPreferredWidth(60); // Gio ra
                table.getColumnModel().getColumn(3).setPreferredWidth(50); // Trang thai
                table.getColumnModel().getColumn(3).setPreferredWidth(50); // Gio lam them
        }

        private void centerAlignAllColumns() {
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);

                // Lặp qua tất cả các cột và thiết lập renderer
                for (int i = 0; i < table.getColumnCount(); i++) {
                        table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }
        }

        public void chonGio() {
                chonGio = new JFrame();
                chonGio.setLayout(null);
                chonGio.setSize(500, 300);
                chonGio.setLocationRelativeTo(null);
                chonGio.setBackground(Color.white);
                JLabel from = new JLabel("From: ");
                from.setBounds(50, 50, 50, 20);

                JLabel to = new JLabel("To: ");
                to.setBounds(50, 150, 50, 20);

                SpinnerDateModel model = new SpinnerDateModel();
                JSpinner timeSpinner = new JSpinner(model);
                JSpinner.DateEditor editor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
                timeSpinner.setEditor(editor);

                timeSpinner.setBounds(150, 50, 200, 20);

                chonGio.add(from);
                chonGio.add(timeSpinner);
                chonGio.add(to);
                chonGio.setVisible(true);
        }

        public static void main(String[] args) {
                new ChamCongFrame(new Employee());
        }
}

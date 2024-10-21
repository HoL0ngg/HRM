package com.hrm.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.hrm.controller.ChamCongController;
import com.hrm.controller.EnterController;
import com.hrm.dao.EmployeeDAO;
import com.hrm.dao.TimeKeepingDAO;
import com.hrm.model.Employee;
import com.hrm.model.TimeKeeping;
import com.hrm.model.TimeKeeping.Status;
import com.toedter.calendar.JDateChooser;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import java.util.ArrayList;
import java.util.Calendar;
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
                EnterController con = new EnterController();

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

                TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
                table.setRowSorter(sorter);

                // chinh chieu rong cot
                setColumnWidths();

                // can giua cac cot
                centerAlignAllColumns();

                // kh cho di chuyen cac cot trong table
                table.getTableHeader().setReorderingAllowed(false);

                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(0, 150, 800, 514);
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
                con.addTransferFocusListener(NgayBatDau);
                NgayBatDau.setDateFormatString("dd/MM/yyyy");

                // Tính toán ngày mặc định (Ngay dau thang)
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                Date defaultDate = calendar.getTime();

                NgayBatDau.setDate(defaultDate);
                NgayBatDau.setBounds(75, 65, 140, 30);

                contentPane.add(NgayBatDau);

                JDateChooser NgayKetThuc = new JDateChooser();
                con.addTransferFocusListener(NgayKetThuc);
                NgayKetThuc.setDateFormatString("dd/MM/yyyy");
                NgayKetThuc.setDate(new Date()); // Đặt ngày mặc định
                NgayKetThuc.setBounds(300, 65, 140, 30);

                contentPane.add(NgayKetThuc);

                // doc data tu database len JTable
                LoadData(tableModel, NgayBatDau.getDate(), NgayKetThuc.getDate());

                // Cap nhat JTable real-time khi thay doi ngay bat dau
                NgayBatDau.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {

                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                                if (NgayBatDau.getDate() != null) {
                                        // Cập nhật dữ liệu của JTable
                                        LoadData(tableModel, NgayBatDau.getDate(), NgayKetThuc.getDate());
                                }
                        }
                });

                // Cap nhat JTable real-time khi thay doi ngay ket thuc
                NgayKetThuc.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {

                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                                if (NgayKetThuc.getDate() != null) {
                                        // Cập nhật dữ liệu của JTable
                                        LoadData(tableModel, NgayBatDau.getDate(), NgayKetThuc.getDate());
                                }
                        }
                });

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
                TimKiemField.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
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
                                // Bỏ những khoảng trắng
                                String text = TimKiemField.getText().trim();
                                filterTable(text);
                                if (text.length() == 0) {
                                        TimKiemField.setText("Tim kiem"); // Hiển thị lại placeholder khi
                                                                          // không có dữ liệu
                                        TimKiemField.setForeground(Color.GRAY);
                                }
                        }

                        private void filterTable(String text) {
                                text = buildVietnameseRegex(text);
                                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
                        }

                        private String buildVietnameseRegex(String input) {
                                // Loại bỏ dấu khỏi chuỗi nhập để so sánh
                                String normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
                                                .replaceAll("\\p{M}", "");

                                // Tạo biểu thức regex với các chữ cái tiếng Việt có dấu tương ứng
                                String[][] vietnameseCharMap = {
                                                { "a", "[aáàạảãâấầậẩẫăắằặẳẵ]" },
                                                { "d", "[dđ]" },
                                                { "e", "[eéèẹẻẽêếềệểễ]" },
                                                { "i", "[iíìịỉĩ]" },
                                                { "o", "[oóòọỏõôốồộổỗơớờợởỡ]" },
                                                { "u", "[uúùụủũưứừựửữ]" },
                                                { "y", "[yýỳỵỷỹ]" }
                                };

                                // Thay thế các ký tự không dấu bằng các biểu thức regex tương ứng
                                for (String[] map : vietnameseCharMap) {
                                        normalized = normalized.replaceAll(map[0], map[1]);
                                }

                                // Chặn giới hạn 2 đâu
                                return "\\b" + normalized + "\\b";
                        }
                });
                con.addTransferFocusListener(TimKiemField);

                TimKiemPanel.add(timkiemLabel);
                TimKiemPanel.add(TimKiemField);
                TimKiemPanel.setOpaque(true);
                contentPane.add(TimKiemPanel);

                Image FilterIcon = new ImageIcon(
                                new File("hrm/src/main/resources/img/filter.png").getAbsolutePath())
                                .getImage()
                                .getScaledInstance(20, 20, Image.SCALE_SMOOTH);

                JLabel FilterLabel = new JLabel(new ImageIcon(
                                FilterIcon));
                FilterLabel.setBounds(735, 63, 35, 35);
                FilterLabel.setName("filter");
                FilterLabel.addMouseListener(controller);
                contentPane.add(FilterLabel);

                RoundedPanel xuatFilePanel = new RoundedPanel(
                                20);
                xuatFilePanel.setBounds(660, 110, 100, 25);
                xuatFilePanel.setBackground(Color.white);
                JLabel xuatFile = new JLabel(
                                "Xuat file");
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

        public void LoadData(DefaultTableModel tableModel, Date NgayBatDau, Date NgayKetThuc) {
                ArrayList<TimeKeeping> arr = TimeKeepingDAO.getInstance().selectAll();
                tableModel.setRowCount(0);
                for (TimeKeeping time : arr) {
                        // Chuyen du lieu tu kieu Date ve LocalDate
                        LocalDate startLocalDate = NgayBatDau.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate endLocalDate = NgayKetThuc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        Object[] rowdata = {
                                        time.getEmployee_id(),
                                        EmployeeDAO.getInstance().seclectByID(time.getEmployee_id()).getName(),
                                        time.getDate(),
                                        time.getCheck_in_time(),
                                        time.getCheck_out_time(),
                                        time.getStatus(),
                                        time.getEmployee_id()
                        };
                        if ((time.getDate().isEqual(startLocalDate) || time.getDate().isAfter(startLocalDate)) &&
                                        (time.getDate().isEqual(endLocalDate)
                                                        || time.getDate().isBefore(endLocalDate))) {
                                tableModel.addRow(rowdata);
                        }
                }
                table.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
                        @Override
                        public Component getTableCellRendererComponent(JTable table, Object value,
                                        boolean isSelected,
                                        boolean hasFocus, int row, int column) {
                                Component cell = super.getTableCellRendererComponent(table, value,
                                                isSelected, hasFocus,
                                                row,
                                                column);

                                if (value instanceof Status) {
                                        Status status = (Status) value;
                                        // Thay đổi màu nền dựa trên trạng thái
                                        switch (status) {
                                                case Late_arrival_early_departure:
                                                        cell.setBackground(Color.red);
                                                        break;
                                                case Late_arrival_on_time_departure:
                                                case Overtime_late_arrival:
                                                        cell.setBackground(Color.orange);
                                                        break;
                                                case On_time_arrival_early_departure:
                                                        cell.setBackground(Color.yellow);
                                                        break;
                                                case On_time_arrival_on_time_departure:
                                                case Overtime_on_time_arrival:
                                                        cell.setBackground(Color.green);
                                                        break;
                                        }
                                } else {
                                        cell.setBackground(Color.WHITE); // Mặc định là màu trắng
                                }
                                return cell;
                        }
                });
        }

        private void setColumnWidths() {
                // Thiết lập chiều rộng cho từng cột
                table.getColumnModel().getColumn(0).setPreferredWidth(30); // Ma NV
                table.getColumnModel().getColumn(1).setPreferredWidth(120); // Ho ten
                table.getColumnModel().getColumn(2).setPreferredWidth(60); // Ngay
                table.getColumnModel().getColumn(3).setPreferredWidth(50); // Gio vao
                table.getColumnModel().getColumn(4).setPreferredWidth(50); // Gio ra
                table.getColumnModel().getColumn(5).setPreferredWidth(120); // Trang thai
                table.getColumnModel().getColumn(6).setPreferredWidth(50); // Gio lam them
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
                chonGio.setSize(555, 440);
                chonGio.setTitle("Filter");
                JPanel contentPane = new JPanel();
                chonGio.setContentPane(contentPane);
                contentPane.setBackground(new Color(245, 143, 82));
                contentPane.setLayout(null);

                Font font = new Font("Segoe UI Emoji", Font.PLAIN, 14);

                JLabel ChonGioLabel = new JLabel("THOI GIAN");
                ChonGioLabel.setBounds(65, 30, 140, 20);
                ChonGioLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 17));
                contentPane.add(ChonGioLabel);

                JLabel ChonTrangThaiLabel = new JLabel("TRANG THAI");
                ChonTrangThaiLabel.setBounds(65, 200, 160, 20);
                ChonTrangThaiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 17));
                contentPane.add(ChonTrangThaiLabel);

                // Panel chon gio
                JPanel ChonGioPanel = new JPanel();
                ChonGioPanel.setLayout(null);
                ChonGioPanel.setBounds(20, 30, 500, 140);
                ChonGioPanel.setBackground(Color.white);

                JSeparator up1 = new JSeparator();
                up1.setBounds(5, 8, 40, 2);
                JSeparator up2 = new JSeparator();
                up2.setBounds(130, 8, 366, 2);
                JSeparator right = new JSeparator(SwingConstants.VERTICAL);
                right.setBounds(496, 8, 2, 128);
                JSeparator down = new JSeparator();
                down.setBounds(4, 136, 492, 2);
                JSeparator left = new JSeparator(SwingConstants.VERTICAL);
                left.setBounds(4, 8, 2, 128);

                JLabel from = new JLabel("From: ");
                from.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
                from.setBounds(20, 30, 100, 20);

                JSpinner GioBatDau = new JSpinner(new SpinnerDateModel());

                // Định dạng hiển thị cho JSpinner theo kiểu "HH:mm"
                JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(GioBatDau, "HH:mm");
                GioBatDau.setEditor(timeEditor);
                GioBatDau.setBounds(70, 30, 60, 20);
                GioBatDau.setFont(font);

                // Đặt giá trị mặc định là 7g sang
                try {
                        GioBatDau.setValue(new SimpleDateFormat("HH:mm").parse("8:00"));
                } catch (ParseException e) {
                        e.printStackTrace();
                }

                JLabel to = new JLabel("To: ");
                to.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
                to.setBounds(20, 60, 50, 20);

                JSpinner GioKetThuc = new JSpinner(new SpinnerDateModel());

                JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(GioKetThuc, "HH:mm");
                GioKetThuc.setEditor(timeEditor2);
                GioKetThuc.setBounds(70, 60, 60, 20);
                GioKetThuc.setFont(font);

                // Đặt giá trị mặc định là 6g chieu
                try {
                        GioKetThuc.setValue(new SimpleDateFormat("HH:mm").parse("16:00"));
                } catch (ParseException e) {
                        e.printStackTrace();
                }

                ChonGioPanel.add(from);
                ChonGioPanel.add(GioBatDau);
                ChonGioPanel.add(GioKetThuc);
                ChonGioPanel.add(up1);
                ChonGioPanel.add(up2);
                ChonGioPanel.add(right);
                ChonGioPanel.add(down);
                ChonGioPanel.add(left);
                ChonGioPanel.add(to);
                contentPane.add(ChonGioPanel);

                // Panel chon trang thai
                JPanel ChonTrangThaiPanel = new JPanel();
                ChonTrangThaiPanel.setLayout(null);
                ChonTrangThaiPanel.setBounds(20, 200, 500, 140);
                ChonTrangThaiPanel.setBackground(Color.white);

                JCheckBox checkBox1 = new JCheckBox("Di tre ve som");
                checkBox1.setBounds(40, 30, 150, 16);
                checkBox1.setFont(font);
                checkBox1.setBackground(Color.white);
                JCheckBox checkBox2 = new JCheckBox("Di tre ve dung gio");
                checkBox2.setBounds(40, 60, 150, 16);
                checkBox2.setFont(font);
                checkBox2.setBackground(Color.white);
                JCheckBox checkBox3 = new JCheckBox("Di dung gio ve som");
                checkBox3.setBounds(40, 90, 150, 16);
                checkBox3.setFont(font);
                checkBox3.setBackground(Color.white);
                JCheckBox checkBox4 = new JCheckBox("Di dung gio");
                checkBox4.setBounds(300, 30, 150, 16);
                checkBox4.setFont(font);
                checkBox4.setBackground(Color.white);
                JCheckBox checkBox5 = new JCheckBox("Tang ca (di tre)");
                checkBox5.setBounds(300, 60, 150, 16);
                checkBox5.setFont(font);
                checkBox5.setBackground(Color.white);
                JCheckBox checkBox6 = new JCheckBox("Tang ca (di dung gio)");
                checkBox6.setBounds(300, 90, 180, 16);
                checkBox6.setFont(font);
                checkBox6.setBackground(Color.white);

                JSeparator up3 = new JSeparator();
                up3.setBounds(5, 8, 40, 2);
                JSeparator up4 = new JSeparator();
                up4.setBounds(142, 8, 354, 2);
                JSeparator right2 = new JSeparator(SwingConstants.VERTICAL);
                right2.setBounds(496, 8, 2, 128);
                JSeparator down2 = new JSeparator();
                down2.setBounds(4, 136, 492, 2);
                JSeparator left2 = new JSeparator(SwingConstants.VERTICAL);
                left2.setBounds(4, 8, 2, 128);

                ChonTrangThaiPanel.add(checkBox1);
                ChonTrangThaiPanel.add(checkBox2);
                ChonTrangThaiPanel.add(checkBox3);
                ChonTrangThaiPanel.add(checkBox4);
                ChonTrangThaiPanel.add(checkBox5);
                ChonTrangThaiPanel.add(checkBox6);
                ChonTrangThaiPanel.add(up3);
                ChonTrangThaiPanel.add(up4);
                ChonTrangThaiPanel.add(right2);
                ChonTrangThaiPanel.add(down2);
                ChonTrangThaiPanel.add(left2);
                contentPane.add(ChonTrangThaiPanel);

                // Nut reset
                RoundedPanel ResetPanel = new RoundedPanel(20);
                ResetPanel.setBackground(Color.white);
                ResetPanel.setBounds(350, 350, 80, 30);
                ResetPanel.add(new JLabel("DAT LAI"));
                contentPane.add(ResetPanel);

                // Nut dong y
                RoundedPanel DongYPanel = new RoundedPanel(20);
                DongYPanel.setBackground(Color.white);
                DongYPanel.setBounds(440, 350, 80, 30);
                DongYPanel.add(new JLabel("DONG Y"));
                contentPane.add(DongYPanel);

                chonGio.setLocationRelativeTo(null);

                // Tranh focus vao JTextfield tu ban dau
                JPanel emptyJPanel = new JPanel();
                emptyJPanel.setBounds(0, 0, 0, 0);
                contentPane.add(emptyJPanel);
                chonGio.setVisible(true);
                emptyJPanel.requestFocusInWindow();
        }

        public static void main(String[] args) {
                new ChamCongFrame(new Employee());
        }
}

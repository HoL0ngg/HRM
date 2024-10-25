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
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
        private DefaultTableModel tableModel;
        private Employee employee;
        private JFrame chonGio;
        private JCheckBox checkBox1;
        private JCheckBox checkBox2;
        private JCheckBox checkBox3;
        private JCheckBox checkBox4;
        private JCheckBox checkBox5;
        private JCheckBox checkBox6;
        private JSpinner GioVaoBatDau;
        private JSpinner GioRaBatDau;
        private JSpinner GioVaoKetThuc;
        private JSpinner GioRaKetThuc;

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
                tableModel = new DefaultTableModel(colName, 0) {
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
                scrollPane.setBounds(0, 150, 790, 514);
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
                xuatFilePanel.setName("XuatFile");
                xuatFilePanel.addMouseListener(controller);
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

                ChamCongController controller = new ChamCongController(this);

                Font font = new Font("Segoe UI Emoji", Font.PLAIN, 14);

                JLabel ChonGioLabel = new JLabel("THOI GIAN");
                ChonGioLabel.setBounds(65, 34, 140, 20);
                ChonGioLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 17));
                contentPane.add(ChonGioLabel);

                JLabel ChonTrangThaiLabel = new JLabel("TRANG THAI");
                ChonTrangThaiLabel.setBounds(65, 204, 160, 20);
                ChonTrangThaiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 17));
                contentPane.add(ChonTrangThaiLabel);

                // Panel chon gio
                JPanel ChonGioPanel = new JPanel();
                ChonGioPanel.setLayout(null);
                ChonGioPanel.setBounds(20, 30, 500, 140);
                ChonGioPanel.setBackground(Color.white);

                JSeparator up1 = new JSeparator();
                up1.setBounds(5, 12, 40, 2);
                JSeparator up2 = new JSeparator();
                up2.setBounds(130, 12, 366, 2);
                JSeparator right = new JSeparator(SwingConstants.VERTICAL);
                right.setBounds(496, 12, 2, 124);
                JSeparator down = new JSeparator();
                down.setBounds(4, 136, 492, 2);
                JSeparator left = new JSeparator(SwingConstants.VERTICAL);
                left.setBounds(4, 12, 2, 124);

                JLabel GioVao = new JLabel("Gio vao: ");
                GioVao.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
                GioVao.setBounds(20, 42, 100, 20);

                GioVaoBatDau = new JSpinner(new SpinnerDateModel());

                // Định dạng hiển thị cho JSpinner theo kiểu "HH:mm"
                JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(GioVaoBatDau, "HH:mm");
                GioVaoBatDau.setEditor(timeEditor);
                GioVaoBatDau.setBounds(90, 40, 60, 22);
                GioVaoBatDau.setFont(font);

                GioVaoKetThuc = new JSpinner(new SpinnerDateModel());

                JSpinner.DateEditor timeEditor3 = new JSpinner.DateEditor(GioVaoKetThuc, "HH:mm");
                GioVaoKetThuc.setEditor(timeEditor3);
                GioVaoKetThuc.setBounds(180, 40, 60, 22);
                GioVaoKetThuc.setFont(font);

                // Đặt giá trị mặc định là 7g30 den 8g30 sang
                try {
                        GioVaoBatDau.setValue(new SimpleDateFormat("HH:mm").parse("7:30"));
                        GioVaoKetThuc.setValue(new SimpleDateFormat("HH:mm").parse("8:30"));
                } catch (ParseException e) {
                        e.printStackTrace();
                }

                JLabel jdo = new JLabel("-");
                jdo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
                jdo.setBounds(160, 42, 20, 20);

                JLabel GioRa = new JLabel("Gio ra: ");
                GioRa.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
                GioRa.setBounds(20, 82, 60, 20);

                GioRaBatDau = new JSpinner(new SpinnerDateModel());

                JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(GioRaBatDau, "HH:mm");
                GioRaBatDau.setEditor(timeEditor2);
                GioRaBatDau.setBounds(90, 80, 60, 22);
                GioRaBatDau.setFont(font);

                GioRaKetThuc = new JSpinner(new SpinnerDateModel());

                JSpinner.DateEditor timeEditor4 = new JSpinner.DateEditor(GioRaKetThuc, "HH:mm");
                GioRaKetThuc.setEditor(timeEditor4);
                GioRaKetThuc.setBounds(180, 80, 60, 22);
                GioRaKetThuc.setFont(font);

                // Đặt giá trị mặc định là 6g chieu
                try {
                        GioRaBatDau.setValue(new SimpleDateFormat("HH:mm").parse("16:00"));
                        GioRaKetThuc.setValue(new SimpleDateFormat("HH:mm").parse("17:00"));
                } catch (ParseException e) {
                        e.printStackTrace();
                }

                JLabel aibic = new JLabel("-");
                aibic.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
                aibic.setBounds(160, 82, 20, 20);

                ChonGioPanel.add(GioVao);
                ChonGioPanel.add(GioVaoBatDau);
                ChonGioPanel.add(jdo);
                ChonGioPanel.add(GioVaoKetThuc);
                ChonGioPanel.add(GioRaBatDau);
                ChonGioPanel.add(aibic);
                ChonGioPanel.add(GioRaKetThuc);
                ChonGioPanel.add(up1);
                ChonGioPanel.add(up2);
                ChonGioPanel.add(right);
                ChonGioPanel.add(down);
                ChonGioPanel.add(left);
                ChonGioPanel.add(GioRa);
                contentPane.add(ChonGioPanel);

                // Panel chon trang thai
                JPanel ChonTrangThaiPanel = new JPanel();
                ChonTrangThaiPanel.setLayout(null);
                ChonTrangThaiPanel.setBounds(20, 200, 500, 140);
                ChonTrangThaiPanel.setBackground(Color.white);

                checkBox1 = new JCheckBox("Di tre ve som");
                checkBox1.setBounds(40, 35, 150, 14);
                checkBox1.setFont(font);
                checkBox1.setBackground(Color.white);
                checkBox2 = new JCheckBox("Di tre ve dung gio");
                checkBox2.setBounds(40, 65, 150, 14);
                checkBox2.setFont(font);
                checkBox2.setBackground(Color.white);
                checkBox3 = new JCheckBox("Di dung gio ve som");
                checkBox3.setBounds(40, 95, 150, 14);
                checkBox3.setFont(font);
                checkBox3.setBackground(Color.white);
                checkBox4 = new JCheckBox("Di dung gio");
                checkBox4.setBounds(300, 35, 150, 14);
                checkBox4.setFont(font);
                checkBox4.setBackground(Color.white);
                checkBox5 = new JCheckBox("Tang ca (di tre)");
                checkBox5.setBounds(300, 65, 150, 14);
                checkBox5.setFont(font);
                checkBox5.setBackground(Color.white);
                checkBox6 = new JCheckBox("Tang ca (di dung gio)");
                checkBox6.setBounds(300, 95, 180, 14);
                checkBox6.setFont(font);
                checkBox6.setBackground(Color.white);

                JSeparator up3 = new JSeparator();
                up3.setBounds(5, 12, 40, 2);
                JSeparator up4 = new JSeparator();
                up4.setBounds(142, 12, 354, 2);
                JSeparator right2 = new JSeparator(SwingConstants.VERTICAL);
                right2.setBounds(496, 12, 2, 124);
                JSeparator down2 = new JSeparator();
                down2.setBounds(4, 136, 492, 2);
                JSeparator left2 = new JSeparator(SwingConstants.VERTICAL);
                left2.setBounds(4, 12, 2, 124);

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
                ResetPanel.setName("Reset");
                ResetPanel.addMouseListener(controller);
                contentPane.add(ResetPanel);

                // Nut dong y
                RoundedPanel DongYPanel = new RoundedPanel(20);
                DongYPanel.setBackground(Color.white);
                DongYPanel.setBounds(440, 350, 80, 30);
                DongYPanel.add(new JLabel("DONG Y"));
                DongYPanel.setName("DongY");
                DongYPanel.addMouseListener(controller);
                contentPane.add(DongYPanel);

                chonGio.setLocationRelativeTo(null);

                // Tranh focus vao JTextfield tu ban dau
                JPanel emptyJPanel = new JPanel();
                emptyJPanel.setBounds(0, 0, 0, 0);
                contentPane.add(emptyJPanel);
                chonGio.setVisible(true);
                emptyJPanel.requestFocusInWindow();
        }

        // Nut Reset
        public void ResetButton() {
                try {
                        GioVaoBatDau.setValue(new SimpleDateFormat("HH:mm").parse("7:30"));
                        GioVaoKetThuc.setValue(new SimpleDateFormat("HH:mm").parse("8:30"));

                        GioRaBatDau.setValue(new SimpleDateFormat("HH:mm").parse("16:00"));
                        GioRaKetThuc.setValue(new SimpleDateFormat("HH:mm").parse("17:00"));
                } catch (ParseException e) {
                        e.printStackTrace();
                }

                checkBox1.setSelected(false);
                checkBox2.setSelected(false);
                checkBox3.setSelected(false);
                checkBox4.setSelected(false);
                checkBox5.setSelected(false);
                checkBox6.setSelected(false);
        }

        // Xuat file Excel
        public void xuatFileExcel(JTable table, String filePath) {
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Data");

                TableModel model = table.getModel();

                // Ghi tiêu đề cột
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < model.getColumnCount(); i++) {
                        Cell cell = headerRow.createCell(i);
                        cell.setCellValue(model.getColumnName(i));
                }

                // Ghi dữ liệu vào các hàng
                for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
                        Row row = sheet.createRow(rowIndex + 1);
                        for (int colIndex = 0; colIndex < model.getColumnCount(); colIndex++) {
                                Cell cell = row.createCell(colIndex);
                                Object value = model.getValueAt(rowIndex, colIndex);
                                cell.setCellValue(value != null ? value.toString() : "");
                        }
                }

                // Ghi file Excel ra đĩa
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                        workbook.write(fileOut);
                        JOptionPane.showMessageDialog(null, "Xuất file Excel thành công!");
                } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi xuất file Excel: " + e.getMessage());
                } finally {
                        try {
                                workbook.close();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }

        public void filterTable(JTable table, DefaultTableModel tableModel) {
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
                table.setRowSorter(sorter);

                RowFilter<DefaultTableModel, Object> filter = new RowFilter<DefaultTableModel, Object>() {

                        @Override
                        public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                                LocalTime giovao = (LocalTime) entry.getValue(3); // Cột thứ 4 la gio vao
                                LocalTime giora = (LocalTime) entry.getValue(4); // Cột thứ 5 la gio ra
                                Status status = (Status) entry.getValue(5);

                                Date VaobatdauDate = (Date) GioVaoBatDau.getValue();
                                LocalTime VaoBatDauTime = VaobatdauDate.toInstant()
                                                .atZone(ZoneId.systemDefault())
                                                .toLocalTime();
                                Date VaokethucDate = (Date) GioVaoKetThuc.getValue();
                                LocalTime VaoKetThucTime = VaokethucDate.toInstant()
                                                .atZone(ZoneId.systemDefault())
                                                .toLocalTime();

                                Date RabatdauDate = (Date) GioVaoBatDau.getValue();
                                LocalTime RaBatDauTime = RabatdauDate.toInstant()
                                                .atZone(ZoneId.systemDefault())
                                                .toLocalTime();
                                Date RakethucDate = (Date) GioVaoKetThuc.getValue();
                                LocalTime RaKetThucTime = RakethucDate.toInstant()
                                                .atZone(ZoneId.systemDefault())
                                                .toLocalTime();

                                boolean time = (!giovao.isBefore(VaoBatDauTime)
                                                && !giovao.isAfter(VaoKetThucTime))
                                                || (!giora.isBefore(RaBatDauTime)
                                                                && !giora.isAfter(RaKetThucTime));

                                boolean trangthai = false;
                                int cnt = 0;
                                if (checkBox1.isSelected()) {
                                        trangthai |= status == Status.Late_arrival_early_departure;
                                        ++cnt;
                                }
                                if (checkBox2.isSelected()) {
                                        trangthai |= status == Status.Late_arrival_on_time_departure;
                                        ++cnt;
                                }
                                if (checkBox3.isSelected()) {
                                        trangthai |= status == Status.On_time_arrival_early_departure;
                                        ++cnt;
                                }
                                if (checkBox4.isSelected()) {
                                        trangthai |= status == Status.On_time_arrival_on_time_departure;
                                        ++cnt;
                                }
                                if (checkBox5.isSelected()) {
                                        trangthai |= status == Status.Overtime_late_arrival;
                                        ++cnt;
                                }
                                if (checkBox6.isSelected()) {
                                        trangthai |= status == Status.Overtime_on_time_arrival;
                                        ++cnt;
                                }

                                if (cnt == 0)
                                        trangthai = true;

                                return time && trangthai;
                        }

                };

                sorter.setRowFilter(filter);
        }

        public static void main(String[] args) {
                new ChamCongFrame(new Employee());
        }

        public DefaultTableModel getTableModel() {
                return tableModel;
        }

        public JFrame getChonGio() {
                return chonGio;
        }
}

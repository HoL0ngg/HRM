/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.hrm.view;


import com.hrm.dao.DepartmentDAO;
import com.hrm.dao.EmployeeDAO;
import com.hrm.dao.SalaryChangeHistoryDAO;
import com.hrm.dao.SalaryDAO;
import com.hrm.model.Department;
import com.hrm.model.Employee;
import com.hrm.model.Salary;
import com.hrm.model.SalaryChangeHistory;
import com.hrm.utils.StatusRenderer;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hoang Vu
 */

public class SalaryFrame extends javax.swing.JFrame {
      private JPopupMenu filterMenu;
      private Employee employee;
      private SalaryFrame Mainframe;
    public Employee getEmployee() {
                return employee;
    }
//    public SalaryFrame(SalaryFrame Mainframe) {
//        this.Mainframe = Mainframe;
//        init();
//    }
     
      public JTextField getTxtSearch() {
        return txtSearch;
    }
    
    public SalaryFrame(Employee employee) {
            this.employee = employee;
            init();
    }
    private void init(){
        initComponents();
        addEvents();
        setVisible(true);
        
           
                loadListSalariesToTable();
                loadDataToYeuCauTangLuongTable();
                loadDataToYeuCauTangLuongDaXemTable();
                
                Image FilterIcon = new ImageIcon(
                new File("../hrm/src/main/resources/img/filter.png").getAbsolutePath())
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                this.jLabel20.setIcon(new ImageIcon(FilterIcon));
                
                
                Image reset = new ImageIcon(
                new File("../hrm/src/main/resources/img/refresh_icon.png").getAbsolutePath())
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                this.jblDanhSachLuong.setIcon(new ImageIcon(reset));
                this.sapxep1.setIcon(new ImageIcon(FilterIcon));
                this.sapxep2.setIcon(new ImageIcon(FilterIcon));
                this.rs1.setIcon(new ImageIcon(reset));
                this.rs2.setIcon(new ImageIcon(reset));
                addEvents();
    }
    private void addEvents() {
        
        
        jblback.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainFrame mainframe = new MainFrame(employee);
                mainframe.setVisible(true);
                dispose();
            }
        }
        );
    }
    
    public JTable getTblDanhSachLuong() {
        return tblDanhSachLuong;
    }

    public JPanel getPanel() {
        return jPanel1;
    }
    
  
   
    

    // Biến lưu giá trị lọc
    private String selectedDepartment = null; // Phòng ban
    //private String selectedStatus = null;     // Trạng thái (On/Off)
    private String selectedWorkType = null;   // Hình thức làm việc (Part-time/Full-time)
    private BigDecimal  selecedSalaryMin = null;
    private BigDecimal  selecedSalaryMax = null;
    
    
        
        /*
         * Creates new form Salarytest
         */
        
        public SalaryFrame() {
                init();
           
                loadListSalariesToTable();
                loadDataToYeuCauTangLuongTable();
                loadDataToYeuCauTangLuongDaXemTable();
                
                Image FilterIcon = new ImageIcon(
                new File("../hrm/src/main/resources/img/filter.png").getAbsolutePath())
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                this.jLabel20.setIcon(new ImageIcon(FilterIcon));
                
                
                Image reset = new ImageIcon(
                new File("../hrm/src/main/resources/img/refresh_icon.png").getAbsolutePath())
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                this.jblDanhSachLuong.setIcon(new ImageIcon(reset));
                addEvents();
        }
        
        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jblback = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtIDEmployee = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblPhongBan = new javax.swing.JLabel();
        lblViTri = new javax.swing.JLabel();
        lblHinhThucLamViec = new javax.swing.JLabel();
        lblHoVaTen = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblTongLuongTheoGio = new javax.swing.JLabel();
        lblTongLuongTangCa = new javax.swing.JLabel();
        lblChuyenCan = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblTongLuong = new javax.swing.JLabel();
        dataChooseNgayHieuLuc = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        txtThuong = new javax.swing.JTextField();
        txtKhauTru = new javax.swing.JTextField();
        lblTax = new javax.swing.JLabel();
        lblSocial_insurance = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCapNhapPhieuLuong = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDanhSachLuong = new javax.swing.JTable();
        cbbThang = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        lableFilter = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        TangDan = new javax.swing.JLabel();
        GiamDan = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jblDanhSachLuong = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblYeuCauTangLuong = new javax.swing.JTable();
        txtSearch1 = new javax.swing.JTextField();
        cbbThang1 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        tangdan1 = new javax.swing.JLabel();
        giamdan1 = new javax.swing.JLabel();
        rs1 = new javax.swing.JLabel();
        sapxep1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        txtSearch2 = new javax.swing.JTextField();
        cbbThang2 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblYeuCauTangLuong1 = new javax.swing.JTable();
        sapxep2 = new javax.swing.JLabel();
        rs2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        tangdan2 = new javax.swing.JLabel();
        giamdan2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnMenu.setBackground(new java.awt.Color(245, 143, 82));

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel1.setText("Luong va phuc loi");

        jblback.setText("Quay lại");
        jblback.setName("quaylai"); // NOI18N
        jblback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jblbackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jblback)
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jblback))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel2.setText("Thông tin lương");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Mã nhân viên");

        txtIDEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDEmployeeActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Họ và tên");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Phòng ban");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Vị trí");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Hình thức làm việc");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHoVaTen)
                    .addComponent(txtIDEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblViTri)
                    .addComponent(lblPhongBan))
                .addGap(80, 80, 80)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(lblHinhThucLamViec)
                .addContainerGap(327, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtIDEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(lblPhongBan)
                    .addComponent(lblHinhThucLamViec))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(lblViTri)
                    .addComponent(lblHoVaTen))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel8.setText("Lương và phúc lợi");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Tổng lương theo giờ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Tổng lương tăng ca");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Chuyên cần");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Thưởng");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Khấu trừ");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Tổng lương");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Ngày hiệu lực");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        jButton1.setText("Lưu");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTongLuongTangCa)
                            .addComponent(lblTongLuongTheoGio)
                            .addComponent(lblChuyenCan)
                            .addComponent(txtThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(409, 409, 409)
                                .addComponent(jButton1))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel25)
                                            .addComponent(lblTax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel26)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtKhauTru, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblSocial_insurance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblTongLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(85, 85, 85)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel17))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dataChooseNgayHieuLuc, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel17)
                        .addComponent(lblTongLuongTheoGio)
                        .addComponent(jLabel26)
                        .addComponent(lblSocial_insurance)
                        .addComponent(jLabel15)
                        .addComponent(txtKhauTru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dataChooseNgayHieuLuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel18)
                            .addComponent(lblTongLuongTangCa)
                            .addComponent(jLabel25))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lblChuyenCan)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblTax)
                        .addGap(22, 22, 22)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(lblTongLuong))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtThuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        tblCapNhapPhieuLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Vị trí", "Lương theo giờ", "Lương tăng ca", "Chuyên cần", "Thưởng", "Khấu trừ", "Tổng lương"
            }
        ));
        jScrollPane1.setViewportView(tblCapNhapPhieuLuong);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addGap(0, 806, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tạo phiếu lương", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblDanhSachLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDanhSachLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachLuongMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDanhSachLuong);

        cbbThang.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbbThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", " " }));
        cbbThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThangActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel19.setText("Tháng");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        lableFilter.setText("Lọc");
        lableFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lableFilterMouseClicked(evt);
            }
        });
        lableFilter.setVisible(false);

        jPanel7.setVisible(false);

        TangDan.setText("Tăng dần");
        TangDan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TangDanMouseClicked(evt);
            }
        });

        GiamDan.setText("Giảm dần");
        GiamDan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GiamDanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TangDan, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GiamDan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(TangDan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GiamDan))
        );

        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        jblDanhSachLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblDanhSachLuongMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lableFilter)
                        .addGap(207, 207, 207)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(jblDanhSachLuong)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jblDanhSachLuong))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lableFilter)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh sách nhân viên", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblYeuCauTangLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblYeuCauTangLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblYeuCauTangLuongMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblYeuCauTangLuong);

        txtSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch1ActionPerformed(evt);
            }
        });

        cbbThang1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbbThang1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", " " }));
        cbbThang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThang1ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel21.setText("Tháng");

        jPanel10.setVisible(false);
        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        tangdan1.setText("Tăng dần ");
        tangdan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tangdan1MouseClicked(evt);
            }
        });

        giamdan1.setText("Giảm dần");
        giamdan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                giamdan1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tangdan1)
                    .addComponent(giamdan1)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tangdan1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(giamdan1)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        rs1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rs1MouseClicked(evt);
            }
        });

        sapxep1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sapxep1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbThang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sapxep1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rs1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbThang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sapxep1)
                            .addComponent(rs1)
                            .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Yêu cầu thay đổi", jPanel3);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        txtSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch2ActionPerformed(evt);
            }
        });

        cbbThang2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbbThang2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", " " }));
        cbbThang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThang2ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel22.setText("Tháng");

        tblYeuCauTangLuong1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblYeuCauTangLuong1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblYeuCauTangLuong1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblYeuCauTangLuong1);

        sapxep2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sapxep2MouseClicked(evt);
            }
        });

        rs2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rs2MouseClicked(evt);
            }
        });

        jPanel11.setVisible(false);
        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        tangdan2.setText("Tăng dần");
        tangdan2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tangdan2MouseClicked(evt);
            }
        });

        giamdan2.setText("Giảm dần");
        giamdan2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                giamdan2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tangdan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(giamdan2, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(tangdan2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(giamdan2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbThang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(sapxep2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rs2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(323, Short.MAX_VALUE))
            .addComponent(jScrollPane5)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sapxep2)
                                    .addComponent(rs2)))
                            .addComponent(cbbThang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lịch sử thay đổi", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lableFilterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lableFilterMouseClicked

        if (filterMenu == null) {
            initializeFilterMenu();
        }
        // Hiển thị menu ngay dưới nút bộ lọc
        filterMenu.show(lableFilter, 0, lableFilter.getHeight());
        System.out.println("Filter menu displayed");
    }//GEN-LAST:event_lableFilterMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        SalaryDAO salaryDAO = SalaryDAO.getInstance();
        int selectedid = Integer.parseInt(txtSearch.getText().trim());
        ArrayList<Salary> salaryList = salaryDAO.selectByEmployeeIdforDanhSachLuong(selectedid);
        // Sử dụng phương thức selectByEmployeeID thay vì selectByID
        //        Salary salary = salaryDAO.selectByEmployeeId(selectedid);

        // Nếu tìm thấy Salary, cập nhật bảng dữ liệu
        if (salaryList != null) {
            Object[][] tableData = new Object[1][9]; // Chỉ có một dòng vì chỉ tìm một nhân viên

            for (int i = 0; i < salaryList.size(); ++i) {
                Salary salary = (Salary) salaryList.get(i);
                tableData[i][0] = salary.getEmployee().getId();
                tableData[i][1] = salary.getEmployee().getName();
                tableData[i][2] = salary.getPosition().getName();
                tableData[i][3] = salary.getPositionSalary();
                tableData[i][4] = salary.getOvertimeSalary();
                tableData[i][5] = salary.getAttendance();
                tableData[i][6] = salary.getBonus();
                tableData[i][7] = salary.getDeductions();
                tableData[i][8] = salary.getnet_salary();
            }
            // Tạo model cho bảng và cập nhật
            DefaultTableModel model = new DefaultTableModel(tableData,
                new String[] { "Mã nhân viên", "Họ và tên", "Vị trí",
                    "Lương theo giờ", "Lương tăng ca", "Chuyên cần", "Thưởng", "Khấu trừ",
                    "Tổng lương" });
            this.tblDanhSachLuong.setModel(model);
        } else {
            // Nếu không tìm thấy thông tin lương, có thể hiển thị thông báo hoặc để trống bảng
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin lương cho nhân viên này!");
        }
    }//GEN-LAST:event_txtSearchActionPerformed

    private void cbbThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThangActionPerformed
        int selectedMonth = this.cbbThang.getSelectedIndex() + 1;
                SalaryDAO salaryDAO = SalaryDAO.getInstance();
                ArrayList<Salary> salaryList = salaryDAO.selectByMonth(selectedMonth);
                Object[][] tableData = new Object[salaryList.size()][9];

                for (int i = 0; i < salaryList.size(); ++i) {
                        Salary salary = (Salary) salaryList.get(i);
                        tableData[i][0] = salary.getEmployee().getId();
                        tableData[i][1] = salary.getEmployee().getName();
                        tableData[i][2] = salary.getPosition().getName();
                        tableData[i][3] = salary.getPositionSalary();
                        tableData[i][4] = salary.getOvertimeSalary();
                        tableData[i][5] = salary.getAttendance();
                        tableData[i][6] = salary.getBonus();
                        tableData[i][7] = salary.getDeductions();
                        tableData[i][8] = salary.getnet_salary();
                }

                DefaultTableModel model = new DefaultTableModel(tableData,
                                new String[] { "Mã nhân viên", "Họ và tên", "Vị trí",
                                                "Lương theo giờ", "Lương tăng ca", "Chuyên cần", "Thưởng", "Khấu trừ",
                                                "Tổng lương" });
                this.tblDanhSachLuong.setModel(model);
    }//GEN-LAST:event_cbbThangActionPerformed

    private void tblDanhSachLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachLuongMouseClicked
        int row = tblDanhSachLuong.rowAtPoint(evt.getPoint());
        if (row >= 0) {
            // Lấy employeeId từ hàng đã chọn
            int employeeId = (int) tblDanhSachLuong.getValueAt(row, 0);  // Giả sử cột 0 là employee_id
            
            // Gọi phương thức để lấy thông tin lương chi tiết từ database
            SalaryDAO salaryDAO = SalaryDAO.getInstance();
            ArrayList<Salary> salaryDetails = salaryDAO.selectByEmployeeIdforDanhSachLuong(employeeId);

            // Kiểm tra nếu danh sách có lương cho nhân viên
            if (!salaryDetails.isEmpty()) {
                // Lấy thông tin chi tiết từ đối tượng Salary
                Salary salaryDetail = salaryDetails.get(0);  // Lấy thông tin lương chi tiết (nếu có)
                
                String employeeName = salaryDetail.getEmployee().getName();
                String position = salaryDetail.getPosition().getName();
                BigDecimal totalSalary = salaryDetail.getHourly_salary();  // Lương theo giờ
                BigDecimal overtimeSalary = salaryDetail.getOvertimeSalary();  // Lương tăng ca
                BigDecimal bonus = salaryDetail.getBonus();
                int attendance = salaryDetail.getAttendance();
                BigDecimal deductions = salaryDetail.getDeductions();
                BigDecimal netSalary = salaryDetail.getnet_salary();  // Tổng lương
                LocalDate payday = salaryDetail.getPayday();
                String note = salaryDetail.getNote();
                
                // Gọi phương thức để hiển thị thông tin chi tiết trong SalaryDetailDialog
                SalaryDetailDialog.showSalaryDetailDialog(
                    (JFrame) null,  // parent frame có thể là null
                    employeeName,   // Tên nhân viên
                    position,       // Vị trí công việc
                    totalSalary,    // Tổng lương theo giờ
                    overtimeSalary, // Tổng lương tăng ca
                    bonus,          // Thưởng
                    attendance,     // Chuyên cần
                    deductions,     // Khấu trừ
                    netSalary,      // Tổng lương (net salary)
                    payday,         // Ngày hiệu lực
                    note            // Ghi chú
                );
            } else {
                // Xử lý trường hợp không có dữ liệu (nếu cần)
                JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin lương.");
            }
        }
    }//GEN-LAST:event_tblDanhSachLuongMouseClicked

    private void TangDanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TangDanMouseClicked
        SalaryDAO salaryDAO = SalaryDAO.getInstance();
    ArrayList<Salary> salaryList = salaryDAO.selectAll(); // selectAll thay vì selectByMonth
    
    // Sắp xếp danh sách salaryList theo netSalary tăng dần
    Collections.sort(salaryList, (Salary s1, Salary s2) -> s1.getnet_salary().compareTo(s2.getnet_salary()) // So sánh getnet_salary theo thứ tự tăng dần
        );
    
    // Tạo dữ liệu cho bảng
    Object[][] tableData = new Object[salaryList.size()][9];
    for (int i = 0; i < salaryList.size(); ++i) {
        Salary salary = salaryList.get(i);
        tableData[i][0] = salary.getEmployee().getId();
        tableData[i][1] = salary.getEmployee().getName();
        tableData[i][2] = salary.getPosition().getName();
        tableData[i][3] = salary.getPositionSalary();
        tableData[i][4] = salary.getOvertimeSalary();
        tableData[i][5] = salary.getAttendance();
        tableData[i][6] = salary.getBonus();
        tableData[i][7] = salary.getDeductions();
        tableData[i][8] = salary.getnet_salary();
    }

    // Cập nhật lại bảng với dữ liệu đã sắp xếp
    DefaultTableModel model = new DefaultTableModel(tableData,
            new String[] { "Mã nhân viên", "Họ và tên", "Vị trí", 
                          "Lương theo giờ", "Lương tăng ca", "Chuyên cần", 
                          "Thưởng", "Khấu trừ", "Tổng lương" });
    this.tblDanhSachLuong.setModel(model);
    }//GEN-LAST:event_TangDanMouseClicked

    private void GiamDanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GiamDanMouseClicked
        SalaryDAO salaryDAO = SalaryDAO.getInstance();
    ArrayList<Salary> salaryList = salaryDAO.selectAll(); // selectAll thay vì selectByMonth
    
    // Sắp xếp danh sách salaryList theo netSalary giảm dần
    Collections.sort(salaryList, (Salary s1, Salary s2) -> s2.getnet_salary().compareTo(s1.getnet_salary()) // So sánh netSalary theo thứ tự giảm dần
        );
    
    // Tạo dữ liệu cho bảng
    Object[][] tableData = new Object[salaryList.size()][9];
    for (int i = 0; i < salaryList.size(); ++i) {
        Salary salary = salaryList.get(i);
        tableData[i][0] = salary.getEmployee().getId();
        tableData[i][1] = salary.getEmployee().getName();
        tableData[i][2] = salary.getPosition().getName();
        tableData[i][3] = salary.getPositionSalary();
        tableData[i][4] = salary.getOvertimeSalary();
        tableData[i][5] = salary.getAttendance();
        tableData[i][6] = salary.getBonus();
        tableData[i][7] = salary.getDeductions();
        tableData[i][8] = salary.getnet_salary();
    }

    // Cập nhật lại bảng với dữ liệu đã sắp xếp
    DefaultTableModel model = new DefaultTableModel(tableData,
            new String[] { "Mã nhân viên", "Họ và tên", "Vị trí", 
                          "Lương theo giờ", "Lương tăng ca", "Chuyên cần", 
                          "Thưởng", "Khấu trừ", "Tổng lương" });
    this.tblDanhSachLuong.setModel(model);
    }//GEN-LAST:event_GiamDanMouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        if(jPanel7.isVisible()){
            jPanel7.setVisible(false);
        } else {
            jPanel7.setVisible(true);
        }
        revalidate();
        repaint();
    }//GEN-LAST:event_jLabel20MouseClicked

    private void txtSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch1ActionPerformed
        SalaryChangeHistoryDAO salaryChangeHistoryDAO = SalaryChangeHistoryDAO.getInstance();
        int selectedid = Integer.parseInt(txtSearch1.getText().trim());
        ArrayList<SalaryChangeHistory> salaryList = salaryChangeHistoryDAO.selectByEmployeeId1(selectedid);

        // Nếu danh sách không rỗng, cập nhật bảng dữ liệu
        if (salaryList != null && !salaryList.isEmpty()) {
            Object[][] tableData = new Object[salaryList.size()][8]; // Tạo mảng phù hợp với số dòng dữ liệu

            for (int i = 0; i < salaryList.size(); ++i) {
                SalaryChangeHistory history = salaryList.get(i);
                tableData[i][0] = history.getId();
                tableData[i][1] = selectedid;
                tableData[i][2] = history.getEmployeeName();
                tableData[i][3] = history.getOldSalary();
                tableData[i][4] = history.getNewSalary();
                tableData[i][5] = history.getchangeDateSend();
                tableData[i][6] = history.getReasons();
                tableData[i][7] = history.getStatus();
            }

            // Tạo model cho bảng và cập nhật
            DefaultTableModel model = new DefaultTableModel(tableData,
                new String[] { "Mã","Mã nhân viên", "Họ và tên", "Lương cũ", "Lương mới", "Ngày gửi yêu cầu", "Lý do", "Trạng thái" });
            this.tblYeuCauTangLuong.setModel(model);
            this.tblYeuCauTangLuong.getColumnModel().getColumn(7).setCellRenderer(new StatusRenderer());
        } else {
            // Nếu không tìm thấy thông tin, hiển thị thông báo
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin thay đổi lương cho nhân viên này!");
        }
    }//GEN-LAST:event_txtSearch1ActionPerformed

    private void cbbThang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThang1ActionPerformed
               int selectedMonth = this.cbbThang1.getSelectedIndex() + 1;

                this.tblYeuCauTangLuong.setRowHeight(30);

                SalaryChangeHistoryDAO salaryChangeHistoryDAO = SalaryChangeHistoryDAO.getInstance();
                // Sử dụng đúng kiểu dữ liệu cho danh sách SalaryChangeHistory
                ArrayList<SalaryChangeHistory> salaryChangeList = salaryChangeHistoryDAO.selectByMonthDaXem(selectedMonth); 
                Object[][] tableData = new Object[salaryChangeList.size()][8];

                for (int i = 0; i < salaryChangeList.size(); ++i) {
                    SalaryChangeHistory history = salaryChangeList.get(i);// Không cần ép kiểu nữa vì đã khai báo đúng kiểu
                    tableData[i][0] = history.getId();
                    tableData[i][1] = history.getEmployee().getId();
                    tableData[i][2] = history.getEmployee().getName();  // Sử dụng employeeName thay vì getEmployee().getName()
                    tableData[i][3] = history.getOldSalary();
                    tableData[i][4] = history.getNewSalary();
                    tableData[i][5] = history.getchangeDateSend();
                    tableData[i][6] = history.getReasons();
                    tableData[i][7] = history.getStatus();
                }

                DefaultTableModel model = new DefaultTableModel(tableData,
                    new String[] { "Mã","Mã nhân viên", "Nhân viên", "Lương cũ", "Lương mới", "Ngày gửi yêu cầu", "Lý do", "Trạng thái" });
                this.tblYeuCauTangLuong.setModel(model);
                this.tblYeuCauTangLuong.getColumnModel().getColumn(7).setCellRenderer(new StatusRenderer());

    }//GEN-LAST:event_cbbThang1ActionPerformed

    private void txtSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch2ActionPerformed
        SalaryChangeHistoryDAO salaryChangeHistoryDAO = SalaryChangeHistoryDAO.getInstance();
        int selectedid = Integer.parseInt(txtSearch2.getText().trim());
        ArrayList<SalaryChangeHistory> salaryList = salaryChangeHistoryDAO.selectByEmployeeId2(selectedid);

        // Nếu danh sách không rỗng, cập nhật bảng dữ liệu
        if (salaryList != null && !salaryList.isEmpty()) {
            Object[][] tableData = new Object[salaryList.size()][8]; // Tạo mảng phù hợp với số dòng dữ liệu

            for (int i = 0; i < salaryList.size(); ++i) {
                SalaryChangeHistory history = salaryList.get(i);
                
                tableData[i][0] = selectedid;
                tableData[i][1] = history.getEmployee().getName();
                tableData[i][2] = history.getOldSalary();
                tableData[i][3] = history.getNewSalary();
                tableData[i][4] = history.getReasons();
                tableData[i][5] = history.getchangeDateBrowse();
                tableData[i][6] = history.getApprovedBy().getName();
                tableData[i][7] = history.getComments();
            }

            // Tạo model cho bảng và cập nhật
            DefaultTableModel model = new DefaultTableModel(tableData,
                new String[] { "Mã nhân viên", "Họ và tên", "Lương hiện tại", "Lương đề xuất", "Ngày thay đổi", "Người duyệt", "Phản hồi" });
            this.tblYeuCauTangLuong1.setModel(model);
            
        } else {
            // Nếu không tìm thấy thông tin, hiển thị thông báo
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin thay đổi lương cho nhân viên này!");
        }
    }//GEN-LAST:event_txtSearch2ActionPerformed

    private void cbbThang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThang2ActionPerformed
         int selectedMonth = this.cbbThang2.getSelectedIndex() + 1;
    this.tblYeuCauTangLuong1.setRowHeight(30);

    // Lấy danh sách yêu cầu tăng lương theo tháng
    SalaryChangeHistoryDAO salaryChangeHistoryDAO = SalaryChangeHistoryDAO.getInstance();
    ArrayList<SalaryChangeHistory> salaryChangeList = salaryChangeHistoryDAO.selectByMonthDaXem1(selectedMonth);

    // Tạo bảng dữ liệu cho JTable
    Object[][] tableData = new Object[salaryChangeList.size()][8];

    for (int i = 0; i < salaryChangeList.size(); ++i) {
        SalaryChangeHistory history = salaryChangeList.get(i);

        // Gán dữ liệu vào các cột của bảng
        tableData[i][0] = history.getEmployee().getId();
        tableData[i][1] = history.getEmployee().getName();  // Họ và tên
        tableData[i][2] = history.getOldSalary();     // Lương hiện tại
        tableData[i][3] = history.getNewSalary();     // Lương đề xuất
        tableData[i][4] = history.getReasons();       // Lý do yêu cầu
        tableData[i][5] = history.getchangeDateBrowse();    // Ngày thay đổi
        tableData[i][6] = history.getApprovedBy().getName();    // Người duyệt
        tableData[i][7] = history.getComments();      // Phản hồi
    }

    // Tạo model cho bảng và hiển thị trên JTable
    DefaultTableModel model = new DefaultTableModel(tableData, new String[] {
        "Mã nhân viên", "Họ và tên", "Lương hiện tại", "Lương đề xuất", 
        "Lý do yêu cầu", "Ngày thay đổi", "Người duyệt", "Phản hồi"
    });

    // Cập nhật bảng với model mới
    this.tblYeuCauTangLuong1.setModel(model);

    }//GEN-LAST:event_cbbThang2ActionPerformed

    private void tblYeuCauTangLuong1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblYeuCauTangLuong1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblYeuCauTangLuong1MouseClicked

    private void jblDanhSachLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblDanhSachLuongMouseClicked
        loadListSalariesToTable();
    }//GEN-LAST:event_jblDanhSachLuongMouseClicked

    private void jblbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jblbackActionPerformed
   
    }//GEN-LAST:event_jblbackActionPerformed

    private void sapxep1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sapxep1MouseClicked
        if(jPanel10.isVisible()){
            jPanel10.setVisible(false);
        } else {
            jPanel10.setVisible(true);
        }
        revalidate();
        repaint();
              
    }//GEN-LAST:event_sapxep1MouseClicked

    private void tangdan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tangdan1MouseClicked
        SalaryChangeHistoryDAO salaryChangeHistoryDAO = SalaryChangeHistoryDAO.getInstance();
        ArrayList<SalaryChangeHistory> salaryList = salaryChangeHistoryDAO.selectAllWithEmployee();

        // Sắp xếp danh sách theo oldSalary và newSalary tăng dần
        Collections.sort(salaryList, (SalaryChangeHistory s1, SalaryChangeHistory s2) -> {
            int compareOldSalary = s1.getOldSalary().compareTo(s2.getOldSalary());
            if (compareOldSalary != 0) {
                return compareOldSalary;
            }
            return s1.getNewSalary().compareTo(s2.getNewSalary());
        });

        // Tạo dữ liệu cho bảng
        Object[][] tableData = new Object[salaryList.size()][8];
        for (int i = 0; i < salaryList.size(); ++i) {
            SalaryChangeHistory salary = salaryList.get(i);
            tableData[i][0] = salary.getId();
            tableData[i][1] = salary.getEmployee().getId();
            tableData[i][2] = salary.getEmployee().getName(); // Thêm giới tính nếu cần
            tableData[i][3] = salary.getOldSalary();
            tableData[i][4] = salary.getNewSalary();
            tableData[i][5] = salary.getchangeDateSend();
            
            tableData[i][6] = salary.getReasons();
            tableData[i][7] = salary.getStatus();
        }

        // Cập nhật lại bảng với dữ liệu đã sắp xếp
        DefaultTableModel model = new DefaultTableModel(tableData,
                new String[] { "Mã","Mã nhân viên", "Nhân viên", 
                              "Lương cũ", "Lương mới", "Ngày gửi yêu cầu", 
                              "Lý do", "Trạng thái" });
        this.tblYeuCauTangLuong.setModel(model);
        this.tblYeuCauTangLuong.getColumnModel().getColumn(7).setCellRenderer(new StatusRenderer());
    
    }//GEN-LAST:event_tangdan1MouseClicked

    private void giamdan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giamdan1MouseClicked
        SalaryChangeHistoryDAO salaryChangeHistoryDAO = SalaryChangeHistoryDAO.getInstance();
        ArrayList<SalaryChangeHistory> salaryList = salaryChangeHistoryDAO.selectAllWithEmployee();

        // Sắp xếp danh sách theo oldSalary và newSalary tăng dần
        Collections.sort(salaryList, (SalaryChangeHistory s1, SalaryChangeHistory s2) -> {
        int compareOldSalary = s2.getOldSalary().compareTo(s1.getOldSalary()); // Đổi thứ tự để giảm dần
        if (compareOldSalary != 0) {
            return compareOldSalary; // Nếu khác nhau, trả về kết quả so sánh
        }
        return s2.getNewSalary().compareTo(s1.getNewSalary()); // So sánh newSalary giảm dần
        });

        // Tạo dữ liệu cho bảng
        Object[][] tableData = new Object[salaryList.size()][8];
        for (int i = 0; i < salaryList.size(); ++i) {
            SalaryChangeHistory salary = salaryList.get(i);
            tableData[i][0] = salary.getId();
            tableData[i][1] = salary.getEmployee().getId();
            tableData[i][2] = salary.getEmployee().getName(); // Thêm giới tính nếu cần
            tableData[i][3] = salary.getOldSalary();
            tableData[i][4] = salary.getNewSalary();
            tableData[i][5] = salary.getchangeDateSend();
            
            tableData[i][6] = salary.getReasons();
            tableData[i][7] = salary.getStatus();
        }

        // Cập nhật lại bảng với dữ liệu đã sắp xếp
        DefaultTableModel model = new DefaultTableModel(tableData,
                new String[] { "Mã","Mã nhân viên", "Nhân viên", 
                              "Lương cũ", "Lương mới", "Ngày gửi yêu cầu", 
                              "Lý do", "Trạng thái" });
        this.tblYeuCauTangLuong.setModel(model);
        this.tblYeuCauTangLuong.getColumnModel().getColumn(7).setCellRenderer(new StatusRenderer());
    }//GEN-LAST:event_giamdan1MouseClicked

    private void rs1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rs1MouseClicked
        loadDataToYeuCauTangLuongTable();
    }//GEN-LAST:event_rs1MouseClicked

    private void sapxep2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sapxep2MouseClicked
        if(jPanel11.isVisible()){
            jPanel11.setVisible(false);
        } else {
            jPanel11.setVisible(true);
        }
        revalidate();
        repaint();
    }//GEN-LAST:event_sapxep2MouseClicked

    private void tangdan2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tangdan2MouseClicked
        SalaryChangeHistoryDAO salaryChangeHistoryDAO = SalaryChangeHistoryDAO.getInstance();
        ArrayList<SalaryChangeHistory> salaryChangeList = salaryChangeHistoryDAO.selectAllWithEmployee();

        // Sắp xếp danh sách theo oldSalary và newSalary tăng dần
        Collections.sort(salaryChangeList, (SalaryChangeHistory s1, SalaryChangeHistory s2) -> {
            int compareOldSalary = s1.getOldSalary().compareTo(s2.getOldSalary());
            if (compareOldSalary != 0) {
                return compareOldSalary;
            }
            return s1.getNewSalary().compareTo(s2.getNewSalary());
        });

        // Tạo dữ liệu cho bảng
        Object[][] tableData = new Object[salaryChangeList.size()][8];

    for (int i = 0; i < salaryChangeList.size(); ++i) {
        SalaryChangeHistory history = salaryChangeList.get(i);

        // Gán dữ liệu vào các cột của bảng
        tableData[i][0] = history.getEmployee().getId();
        tableData[i][1] = history.getEmployee().getName();  // Họ và tên
        tableData[i][2] = history.getOldSalary();     // Lương hiện tại
        tableData[i][3] = history.getNewSalary();     // Lương đề xuất
        tableData[i][4] = history.getReasons();       // Lý do yêu cầu
        tableData[i][5] = history.getchangeDateBrowse();    // Ngày thay đổi
        tableData[i][6] = history.getApprovedBy().getName();    // Người duyệt
        tableData[i][7] = history.getComments();      // Phản hồi
    }

    // Tạo model cho bảng và hiển thị trên JTable
    DefaultTableModel model = new DefaultTableModel(tableData, new String[] {
        "Mã nhân viên", "Họ và tên", "Lương hiện tại", "Lương đề xuất", 
        "Lý do yêu cầu", "Ngày thay đổi", "Người duyệt", "Phản hồi"
    });

    // Cập nhật bảng với model mới
    this.tblYeuCauTangLuong1.setModel(model);
    }//GEN-LAST:event_tangdan2MouseClicked

    private void giamdan2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giamdan2MouseClicked
        SalaryChangeHistoryDAO salaryChangeHistoryDAO = SalaryChangeHistoryDAO.getInstance();
        ArrayList<SalaryChangeHistory> salaryList = salaryChangeHistoryDAO.selectAllWithEmployee();

        // Sắp xếp danh sách theo oldSalary và newSalary tăng dần
        Collections.sort(salaryList, (SalaryChangeHistory s1, SalaryChangeHistory s2) -> {
        int compareOldSalary = s2.getOldSalary().compareTo(s1.getOldSalary()); // Đổi thứ tự để giảm dần
        if (compareOldSalary != 0) {
            return compareOldSalary; // Nếu khác nhau, trả về kết quả so sánh
        }
        return s2.getNewSalary().compareTo(s1.getNewSalary()); // So sánh newSalary giảm dần
        });

        // Tạo dữ liệu cho bảng
        Object[][] tableData = new Object[salaryList.size()][8];

    for (int i = 0; i < salaryList.size(); ++i) {
        SalaryChangeHistory history = salaryList.get(i);

        // Gán dữ liệu vào các cột của bảng
        tableData[i][0] = history.getEmployee().getId();
        tableData[i][1] = history.getEmployee().getName();  // Họ và tên
        tableData[i][2] = history.getOldSalary();     // Lương hiện tại
        tableData[i][3] = history.getNewSalary();     // Lương đề xuất
        tableData[i][4] = history.getReasons();       // Lý do yêu cầu
        tableData[i][5] = history.getchangeDateBrowse();    // Ngày thay đổi
        tableData[i][6] = history.getApprovedBy().getName();    // Người duyệt
        tableData[i][7] = history.getComments();      // Phản hồi
    }

    // Tạo model cho bảng và hiển thị trên JTable
    DefaultTableModel model = new DefaultTableModel(tableData, new String[] {
        "Mã nhân viên", "Họ và tên", "Lương hiện tại", "Lương đề xuất", 
        "Lý do yêu cầu", "Ngày thay đổi", "Người duyệt", "Phản hồi"
    });
        this.tblYeuCauTangLuong1.setModel(model);
    }//GEN-LAST:event_giamdan2MouseClicked

    private void rs2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rs2MouseClicked
        loadDataToYeuCauTangLuongDaXemTable();
    }//GEN-LAST:event_rs2MouseClicked
    
    
                                          
    private void initializeFilterMenu() {
    filterMenu = new JPopupMenu();

    // Tạo các radio button
    JRadioButtonMenuItem rbPhongBan = new JRadioButtonMenuItem("Phòng ban");
    JRadioButtonMenuItem rbMucLuong = new JRadioButtonMenuItem("Mức lương");
    JRadioButtonMenuItem rbHinhThucLamViec = new JRadioButtonMenuItem("Hình thức làm việc");

    // Thêm radio button vào menu
    filterMenu.add(rbPhongBan);
    filterMenu.add(rbMucLuong);
    filterMenu.add(rbHinhThucLamViec);

    // Đảm bảo chỉ chọn được một radio button tại một thời điểm
    ButtonGroup group = new ButtonGroup();
    group.add(rbPhongBan);
    group.add(rbMucLuong);
    group.add(rbHinhThucLamViec);

    // Gán sự kiện cho từng radio button
    rbPhongBan.addActionListener(e -> showDialogWithDepartments("Chọn Phòng Ban"));
    rbMucLuong.addActionListener(e -> showSalaryDialog("Chọn Mức Lương"));
    rbHinhThucLamViec.addActionListener(e -> showDialog("Chọn Hình Thức Làm Việc", new String[]{"Part-time", "Full-time"}));
}

// Hàm hiển thị dialog cho mức lương
private void showSalaryDialog(String title) {
    JDialog dialog = new JDialog(this, title, true);
    dialog.setSize(400, 200);
    dialog.setLocationRelativeTo(null);

    JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
    JLabel lblFrom = new JLabel("Từ mức lương:");
    JTextField txtFrom = new JTextField();
    JLabel lblTo = new JLabel("Đến mức lương:");
    JTextField txtTo = new JTextField();

    JButton btnOK = new JButton("Xác nhận");
    btnOK.addActionListener(e -> {
        try {
            // Chuyển đổi đầu vào thành BigDecimal thay vì double
            BigDecimal fromSalary = new BigDecimal(txtFrom.getText());
            BigDecimal toSalary = new BigDecimal(txtTo.getText());

            if (fromSalary.compareTo(toSalary) > 0) {
                JOptionPane.showMessageDialog(dialog, "Mức lương 'Từ' không được lớn hơn 'Đến'.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                selecedSalaryMin = fromSalary;
                selecedSalaryMax = toSalary;
                dialog.dispose();
                // Gọi lại loadListSalariesToTableforFilter với các tham số đã chọn
                loadListSalariesToTableforFilter(selectedDepartment, selecedSalaryMin, selecedSalaryMax, selectedWorkType);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(dialog, "Vui lòng nhập số hợp lệ cho mức lương.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    });

    panel.add(lblFrom);
    panel.add(txtFrom);
    panel.add(lblTo);
    panel.add(txtTo);
    panel.add(new JLabel()); // Ô trống
    panel.add(btnOK);

    dialog.add(panel);
    dialog.setVisible(true);
}

private void showDialogWithDepartments(String title) {
    // Lấy danh sách phòng ban từ cơ sở dữ liệu
    ArrayList<Department> departmentList = DepartmentDAO.getInstance().selectAll();

    // Chuyển danh sách phòng ban thành mảng String để đổ vào JComboBox
    String[] options = departmentList.stream()
            .map(Department::getName) // Lấy tên phòng ban
            .toArray(String[]::new);

    // Gọi hàm showDialog để hiển thị dialog
    showDialog(title, options);
}

// Hàm hiển thị chung cho ComboBox (Phòng ban, Hình thức làm việc)
private void showDialog(String title, String[] options) {
    JDialog dialog = new JDialog(this, title, true);
    dialog.setSize(300, 150);
    dialog.setLocationRelativeTo(null);

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    JComboBox<String> comboBox = new JComboBox<>(options);
    JButton btnOK = new JButton("Xác nhận");

    btnOK.addActionListener(e -> {
        String selectedValue = (String) comboBox.getSelectedItem();
        switch (title) {
            case "Chọn Phòng Ban":
                selectedDepartment = selectedValue;  // Lưu phòng ban đã chọn
                break;
            
            case "Chọn Hình Thức Làm Việc":
                selectedWorkType = selectedValue.equals("Part-time") ? "part_time" : "full_time";
                break;
        }
        dialog.dispose();
        // Gọi lại loadListSalariesToTableforFilter với các tham số đã chọn
        loadListSalariesToTableforFilter(selectedDepartment, selecedSalaryMin, selecedSalaryMax, selectedWorkType);
    });

    panel.add(comboBox, BorderLayout.CENTER);
    panel.add(btnOK, BorderLayout.SOUTH);

    dialog.add(panel);
    dialog.setVisible(true);
}

private void loadListSalariesToTableforFilter(String selectedDepartment, BigDecimal selectedSalaryMin, 
                                               BigDecimal selectedSalaryMax, String selectedWorkType) {
    // Lấy danh sách lương từ database
    SalaryDAO salaryDAO = SalaryDAO.getInstance();
    ArrayList<Salary> salaryList = salaryDAO.selectAll();
    
    // Lọc các dữ liệu dựa trên các tiêu chí đã chọn
    Object[][] tableData = new Object[salaryList.size()][9];
    int index = 0; // Biến đếm vị trí trong mảng tableData

    for (Salary salary : salaryList) {
        boolean matchesFilter = true;

        // Lọc theo phòng ban
        if (selectedDepartment != null && !salary.getEmployee().getDepartment().getName().equals(selectedDepartment)) {
            matchesFilter = false;
        }

        // Lọc theo mức lương
        if (selectedSalaryMin != null && salary.getnet_salary().compareTo(selectedSalaryMin) < 0) {
            matchesFilter = false;
        }
        if (selectedSalaryMax != null && salary.getnet_salary().compareTo(selectedSalaryMax) > 0) {
            matchesFilter = false;
        }

        // Lọc theo loại hình làm việc
        if (selectedWorkType != null) {
            String workTypeString = selectedWorkType.equals("full-time") ? "full_time" : 
                                    (selectedWorkType.equals("part-time") ? "part_time" : null);
            if (workTypeString != null && !salary.getEmployee().getWork_type().name().equals(workTypeString)) {
                matchesFilter = false;
            }
        }

        // Nếu thỏa mãn tất cả các điều kiện lọc, thêm vào dữ liệu bảng
        if (matchesFilter) {
            tableData[index][0] = salary.getEmployee().getId();
            tableData[index][1] = salary.getEmployee().getName();
            tableData[index][2] = salary.getPosition().getName();
            tableData[index][3] = salary.getPositionSalary();
            tableData[index][4] = salary.getOvertimeSalary();
            tableData[index][5] = salary.getAttendance();
            tableData[index][6] = salary.getBonus();
            tableData[index][7] = salary.getDeductions();
            tableData[index][8] = salary.getnet_salary();
            index++;
        }
    }

    // Tạo model bảng với dữ liệu đã lọc
    DefaultTableModel model = new DefaultTableModel(tableData,
            new String[] { "Mã nhân viên", "Họ và tên", "Vị trí", "Lương theo giờ", "Lương tăng ca", 
                          "Chuyên cần", "Thưởng", "Khấu trừ", "Tổng lương" });

    this.tblDanhSachLuong.setModel(model);
}






private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
                 try {
        // Lấy dữ liệu từ giao diện
        int employeeID = Integer.parseInt(txtIDEmployee.getText());
        BigDecimal bonus = new BigDecimal(txtThuong.getText());
        BigDecimal attendance = new BigDecimal(lblChuyenCan.getText());
        BigDecimal deductions = new BigDecimal(txtKhauTru.getText());
        String note = txtGhiChu.getText();
        
        // Lấy thông tin lương theo giờ, tăng ca, thuế và bảo hiểm xã hội
        BigDecimal hourlySalary = new BigDecimal(lblTongLuongTheoGio.getText()); // Lương theo giờ
        BigDecimal overtimeSalary = new BigDecimal(lblTongLuongTangCa.getText()); // Lương tăng ca
        

        // Tính tổng lương
        BigDecimal net_salary = hourlySalary.add(overtimeSalary).add(bonus).subtract(deductions);
        
        // Lấy ngày hiệu lực từ JDateChooser
        java.util.Date date = dataChooseNgayHieuLuc.getDate();
        LocalDate payday = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        lblTongLuong.setText(net_salary.toString());
        // Tạo đối tượng SalaryDAO và gọi phương thức updateSalary
        SalaryDAO salaryDAO = new SalaryDAO();
        boolean updated = salaryDAO.updateSalary(employeeID, bonus, attendance, deductions, note, payday, net_salary ,hourlySalary,overtimeSalary);

        if (updated) {
            JOptionPane.showMessageDialog(this, "Dữ liệu đã được cập nhật thành công!");
            
            // Lấy thông tin nhân viên từ EmployeeDAO
            EmployeeDAO employeeDao = EmployeeDAO.getInstance();
            Employee employee = employeeDao.selectByID(employeeID);  // Lấy thông tin nhân viên

            // Lấy thông tin lương từ SalaryDAO
            Salary salary = salaryDAO.selectByEmployeeID(employeeID);

            if (employee != null && salary != null) {
                // Cập nhật bảng JTable với thông tin của nhân viên và lương
                DefaultTableModel model = (DefaultTableModel) tblCapNhapPhieuLuong.getModel();
                
                // Xóa tất cả các dòng hiện tại trong bảng trước khi thêm mới
                model.setRowCount(0);
                
                // Thêm dòng mới vào bảng với thông tin nhân viên và lương
                model.addRow(new Object[]{
                    employeeID,
                    employee.getName(), // Tên nhân viên
                    employee.getPosition().getName(), // Vị trí công việc
                    salary.getPositionSalary(), // Lương cơ bản
                    salary.getOvertimeSalary(), // Lương tăng ca
                    attendance, // Chuyên cần
                    bonus, // Thưởng
                    deductions, // Khấu trừ
                    net_salary // Tổng lương sau khi tính toán
                });
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin lương cho nhân viên với ID: " + employeeID, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên với ID: " + employeeID, "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ cho Thưởng và Chuyên cần.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
        }// GEN-LAST:event_jButton1ActionPerformed

        private void txtIDEmployeeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtIDEmployeeActionPerformed
                try {
                        int id = Integer.parseInt(this.txtIDEmployee.getText());
                        EmployeeDAO employeeDao = new EmployeeDAO();
                        Employee employee = employeeDao.selectByID(id);
                        if (employee == null) {
                                JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên với ID: " + id, "Lỗi", 0);
                        } else {
                                SalaryDAO salaryDAO = new SalaryDAO();
                                Salary salary = salaryDAO.selectByEmployeeID(employee.getId());
                                this.lblPhongBan.setText(employee.getDepartment().getName());
                                this.lblViTri.setText(employee.getPosition().getName());
                                this.lblHinhThucLamViec.setText("" + String.valueOf(employee.getWork_type()));
                                this.lblHoVaTen.setText(employee.getName());
                                // Lấy các giá trị cần thiết
                                BigDecimal positionSalary = salary.getPositionSalary(); // position_salary từ cơ sở dữ liệu
                                float totalHourlyWorkFloat = salary.getTotal_hourly_work(); // total_hourly_work (float)

                                // Chuyển đổi float sang BigDecimal
                                BigDecimal totalHourlyWork = BigDecimal.valueOf(totalHourlyWorkFloat);

                                // Chia position_salary cho 26 và 8
                                BigDecimal hourlyRate = positionSalary.divide(BigDecimal.valueOf(26), 2, RoundingMode.HALF_UP)
                                                                       .divide(BigDecimal.valueOf(8), 2, RoundingMode.HALF_UP);

                                // Tính tổng lương theo giờ
                                BigDecimal totalHourlySalary = hourlyRate.multiply(totalHourlyWork);

                                // Làm tròn 2 chữ số thập phân
                                BigDecimal roundedSalary = totalHourlySalary.setScale(2, RoundingMode.HALF_UP);

                                // Hiển thị kết quả
                                this.lblTongLuongTheoGio.setText(roundedSalary.toPlainString());
//                                this.lblTongLuongTheoGio.setText(salary.getPositionSalary() + "");
//                                this.txtGhiChu.setText(salary.getNote());
//                                this.txtKhauTru.setText(salary.getDeductions() + "");
//                                this.txtThuong.setText(salary.getBonus() + "");
                                this.lblChuyenCan.setText(salary.getAttendance() + "");
                                //this.lblTongLuong.setText(salary.getNetSalary() + "");
//                                this.lblTax.setText(salary.gettax()+ "");
//                                this.lblSocial_insurance.setText(salary.getsocial_insurance()+ "");
//                                this.lblTongLuongTangCa.setText(salary.getOvertimeSalary() + "");
//                                this.lblTax.setText(salary.getTax()+"");
//                                this.lblSocial_insurance.setText(salary.getSocialInsurance()+"");
//                                this.lblTongLuong.setText (salary.getnet_salary()+"");
                                BigDecimal overtimeSalary = salary.getOvertimeSalary(); // overtime_salary từ cơ sở dữ liệu
                                BigDecimal totalOvertimeShifts = salary.getTotal_overtime_shifts(); // total_overtime_shifts (float)

                                // Tính tổng lương tăng ca
                                BigDecimal totalOvertimeSalary = overtimeSalary.multiply(totalOvertimeShifts);

                                // Làm tròn 2 chữ số thập phân
                                BigDecimal roundedOvertimeSalary = totalOvertimeSalary.setScale(2, RoundingMode.HALF_UP);

                                // Hiển thị kết quả
                                this.lblTongLuongTangCa.setText(roundedOvertimeSalary.toPlainString());
                                  
                                LocalDate payday = salary.getPayday();
                                Date date = java.util.Date
                                                .from(payday.atStartOfDay(ZoneId.systemDefault()).toInstant());
                                // Thiết lập ngày cho JDateChooser
//                                this.dataChooseNgayHieuLuc.setDate(date);
                        }
                } catch (NumberFormatException var7) {
                        JOptionPane.showMessageDialog(this, "ID không hợp lệ. Vui lòng nhập số nguyên.", "Lỗi", 0);
                } catch (Exception var8) {
                        Exception ex = var8;
                        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", 0);
                        ex.printStackTrace();
                }
        }// GEN-LAST:event_txtIDEmployeeActionPerformed

       
        

        private void btnRefreshDataYeuCauThayDoiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRefreshDataYeuCauThayDoiActionPerformed
                loadDataToYeuCauTangLuongTable();
        }// GEN-LAST:event_btnRefreshDataYeuCauThayDoiActionPerformed

        private void tblYeuCauTangLuongMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblYeuCauTangLuongMouseClicked
              int selectedRow = this.tblYeuCauTangLuong.getSelectedRow();
                if (selectedRow != -1) {
                        int salaryChangeId = (Integer) this.tblYeuCauTangLuong.getValueAt(selectedRow, 0);
                        SalaryChangeHistoryDAO salaryChangeHistoryDAO = SalaryChangeHistoryDAO.getInstance();
                        SalaryChangeHistory salaryChangeHistory = salaryChangeHistoryDAO.selectByID(salaryChangeId);
                        SalaryUpdateDialog dialog = new SalaryUpdateDialog((Frame) null, salaryChangeHistory);
                        dialog.setVisible(true);
                }
        }// GEN-LAST:event_tblYeuCauTangLuongMouseClicked

       // GEN-LAST:event_btnRefreshDataYeuCauDaXemActionPerformed

        // GEN-LAST:event_tblDanhSachLuongMouseClicked

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                /* Set the Nimbus look and feel */
                // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
                // (optional) ">
                /*
                 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
                 * look and feel.
                 * For details see
                 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
                 */
                try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                                        .getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(SalaryFrame.class.getName()).log(
                                        java.util.logging.Level.SEVERE, null,
                                        ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(SalaryFrame.class.getName()).log(
                                        java.util.logging.Level.SEVERE, null,
                                        ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(SalaryFrame.class.getName()).log(
                                        java.util.logging.Level.SEVERE, null,
                                        ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(SalaryFrame.class.getName()).log(
                                        java.util.logging.Level.SEVERE, null,
                                        ex);
                }
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>
                // </editor-fold>

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new SalaryFrame().setVisible(true);
                        }
                });
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel GiamDan;
    private javax.swing.JLabel TangDan;
    private javax.swing.JComboBox<String> cbbThang;
    private javax.swing.JComboBox<String> cbbThang1;
    private javax.swing.JComboBox<String> cbbThang2;
    private com.toedter.calendar.JDateChooser dataChooseNgayHieuLuc;
    private javax.swing.JLabel giamdan1;
    private javax.swing.JLabel giamdan2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jblDanhSachLuong;
    private javax.swing.JButton jblback;
    private javax.swing.JLabel lableFilter;
    private javax.swing.JLabel lblChuyenCan;
    private javax.swing.JLabel lblHinhThucLamViec;
    private javax.swing.JLabel lblHoVaTen;
    private javax.swing.JLabel lblPhongBan;
    private javax.swing.JLabel lblSocial_insurance;
    private javax.swing.JLabel lblTax;
    private javax.swing.JLabel lblTongLuong;
    private javax.swing.JLabel lblTongLuongTangCa;
    private javax.swing.JLabel lblTongLuongTheoGio;
    private javax.swing.JLabel lblViTri;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JLabel rs1;
    private javax.swing.JLabel rs2;
    private javax.swing.JLabel sapxep1;
    private javax.swing.JLabel sapxep2;
    private javax.swing.JLabel tangdan1;
    private javax.swing.JLabel tangdan2;
    private javax.swing.JTable tblCapNhapPhieuLuong;
    private javax.swing.JTable tblDanhSachLuong;
    private javax.swing.JTable tblYeuCauTangLuong;
    private javax.swing.JTable tblYeuCauTangLuong1;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtIDEmployee;
    private javax.swing.JTextField txtKhauTru;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtSearch2;
    private javax.swing.JTextField txtThuong;
    // End of variables declaration//GEN-END:variables
        
        private void loadListSalariesToTable() {
            
                SalaryDAO salaryDAO = SalaryDAO.getInstance();
                ArrayList<Salary> salaryList = salaryDAO.selectAll();
                Object[][] tableData = new Object[salaryList.size()][9];

                for (int i = 0; i < salaryList.size(); ++i) {
                        Salary salary = (Salary) salaryList.get(i);
                        tableData[i][0] = salary.getEmployee().getId();
                        tableData[i][1] = salary.getEmployee().getName();
                        tableData[i][2] = salary.getPosition().getName();
                        tableData[i][3] = salary.getPositionSalary();
                        tableData[i][4] = salary.getOvertimeSalary();
                        tableData[i][5] = salary.getAttendance();
                        tableData[i][6] = salary.getBonus();
                        tableData[i][7] = salary.getDeductions();
                        tableData[i][8] = salary.getnet_salary();
                }

                DefaultTableModel model = new DefaultTableModel(tableData,
                                new String[] { "Mã nhân viên", "Họ và tên", "Vị trí",
                                                "Lương theo giờ", "Lương tăng ca", "Chuyên cần", "Thưởng", "Khấu trừ",
                                                "Tổng lương" });
                this.tblDanhSachLuong.setModel(model);
        }
        
        private void loadDataSalariesToTable(int employeeId) {
            DefaultTableModel model = (DefaultTableModel) tblDanhSachLuong.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

            SalaryDAO salaryDAO = new SalaryDAO();
            Salary salary = salaryDAO.selectByEmployeeID(employeeId); // Lấy thông tin lương của nhân viên theo ID

            if (salary != null) {
                // Thêm dữ liệu vào bảng
                model.addRow(new Object[]{
                    salary.getEmployee().getId(),
                    salary.getEmployee().getName(),
                    salary.getPositionSalary(),
                    salary.getBonus(),
                    salary.getDeductions(),
                    salary.getnet_salary(),
                    salary.getOvertimeSalary(),
                    salary.getPayday(),
                    salary.getNote(),
                    salary.getAttendance()
                });
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin lương của nhân viên với ID: " + employeeId, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }


        private void loadDataToYeuCauTangLuongTable() {
                this.tblYeuCauTangLuong.setRowHeight(30);
                SalaryChangeHistoryDAO salaryChangeHistoryDAO = SalaryChangeHistoryDAO.getInstance();
                ArrayList<SalaryChangeHistory> salaryChangeList = salaryChangeHistoryDAO.selectAllWithEmployee();
                Object[][] tableData = new Object[salaryChangeList.size()][8];

                for (int i = 0; i < salaryChangeList.size(); ++i) {
                        SalaryChangeHistory history = (SalaryChangeHistory) salaryChangeList.get(i);
                        tableData[i][0] = history.getId();
                        tableData[i][1] = history.getEmployee().getId();
                        tableData[i][2] = history.getEmployee().getName();
                        tableData[i][3] = history.getOldSalary();
                        tableData[i][4] = history.getNewSalary();
                        tableData[i][5] = history.getchangeDateSend();
                        tableData[i][6] = history.getReasons();
                        tableData[i][7] = history.getStatus();
                }

                DefaultTableModel model = new DefaultTableModel(tableData,
                                new String[] { "Mã","Mã nhân viên", "Nhân viên", "Lương cũ", "Lương mới", "Ngày gửi yêu cầu", "Lý do",
                                                "Trạng thái" });
                this.tblYeuCauTangLuong.setModel(model);
                this.tblYeuCauTangLuong.getColumnModel().getColumn(7).setCellRenderer(new StatusRenderer());
        }

        private void loadDataToYeuCauTangLuongDaXemTable() {
                SalaryChangeHistoryDAO salaryChangeHistoryDAO = SalaryChangeHistoryDAO.getInstance();
                ArrayList<SalaryChangeHistory> salaryChangeList = salaryChangeHistoryDAO.selectReviewed();
                Object[][] tableData = new Object[salaryChangeList.size()][8];

                for (int i = 0; i < salaryChangeList.size(); ++i) {
                        SalaryChangeHistory history = (SalaryChangeHistory) salaryChangeList.get(i);
                        Object[] var10000 = tableData[i];
//                        Object[] var10003 = new Object[] { history.getEmployee().getId() };
//                        var10000[0] = "NV" + String.format("%03d", var10003);
//                        tableData[i][0] = history.getId();
                        tableData[i][0] = history.getEmployee().getId();
                        tableData[i][1] = history.getEmployee().getName();
                        tableData[i][2] = history.getOldSalary();
                        tableData[i][3] = history.getNewSalary();
                        tableData[i][4] = history.getReasons();
                        tableData[i][5] = history.getchangeDateBrowse();
                        tableData[i][6] = history.getApprovedBy().getName();
                        tableData[i][7] = history.getComments();
                }

                DefaultTableModel model = new DefaultTableModel(tableData, new String[] {"Mã nhân viên", "Họ và tên",
                                "Lương hiện tại", "Lương đề xuất", "Lý do yêu cầu", "Ngày thay đổi", "Người duyệt",
                                "Phản hồi" });
                this.tblYeuCauTangLuong1.setModel(model);
        }
        private void goBack() {
    	// Đóng JFrame hiện tại
        SwingUtilities.getWindowAncestor(this).dispose();

        // Mở MainFrame (hoặc JFrame chính của bạn)
        new MainFrame(employee).setVisible(true);
    }
}
/*
 * private void cbbThangActionPerformed(ActionEvent evt) {
 * int selectedMonth = this.cbbThang.getSelectedIndex() + 1;
 * SalaryDAO salaryDAO = SalaryDAO.getInstance();
 * ArrayList<Salary> salaryList = salaryDAO.selectByMonth(selectedMonth);
 * Object[][] tableData = new Object[salaryList.size()][9];
 * 
 * for(int i = 0; i < salaryList.size(); ++i) {
 * Salary salary = (Salary)salaryList.get(i);
 * tableData[i][0] = salary.getEmployee().getId();
 * tableData[i][1] = salary.getEmployee().getName();
 * tableData[i][2] = salary.getPosition().getName();
 * tableData[i][3] = salary.getPositionSalary();
 * tableData[i][4] = salary.getOvertimeSalary();
 * tableData[i][5] = salary.getAttendance();
 * tableData[i][6] = salary.getBonus();
 * tableData[i][7] = salary.getDeductions();
 * tableData[i][8] = salary.getNetSalary();
 * }
 * 
 * DefaultTableModel model = new DefaultTableModel(tableData, new
 * String[]{"Mã nhân viên", "Họ và tên", "Vị trí", "Lương theo giờ",
 * "Lương tăng ca", "Chuyên cần", "Thưởng", "Khấu trừ", "Tổng lương"});
 * this.tblDanhSachLuong.setModel(model);
 * }
 * 
 * private void tblDanhSachLuongMouseClicked(MouseEvent evt) {
 * int selectedRow = this.tblDanhSachLuong.getSelectedRow();
 * if (selectedRow >= 0) {
 * String employeeName = this.tblDanhSachLuong.getValueAt(selectedRow,
 * 1).toString();
 * String position = this.tblDanhSachLuong.getValueAt(selectedRow,
 * 2).toString();
 * String totalSalary = this.tblDanhSachLuong.getValueAt(selectedRow,
 * 8).toString();
 * String overtimeSalary = this.tblDanhSachLuong.getValueAt(selectedRow,
 * 4).toString();
 * String bonus = this.tblDanhSachLuong.getValueAt(selectedRow, 6).toString();
 * String deductions = this.tblDanhSachLuong.getValueAt(selectedRow,
 * 7).toString();
 * String note = "";
 * SalaryDetailDialog.showSalaryDetailDialog((JFrame)null, employeeName,
 * position, totalSalary, overtimeSalary, bonus, deductions, note);
 * }
 * 
 * }
 * 
 * private void cbHinhThucLamViecActionPerformed(ActionEvent evt) {
 * }
 * 
 * private void btnMoCheckBoxActionPerformed(ActionEvent evt) {
 * if (this.pnCheckbox.isVisible()) {
 * this.pnCheckbox.setVisible(false);
 * } else {
 * this.pnCheckbox.setVisible(true);
 * }
 * 
 * }
 * 
 * private void cbPhongBanActionPerformed(ActionEvent evt) {
 * DepartmentDAO departmentDAO = DepartmentDAO.getInstance();
 * ArrayList<Department> departmentList = departmentDAO.seclectAll();
 * String[] columnNames = new String[]{"ID", "Tên phòng ban"};
 * Object[][] data = new Object[departmentList.size()][2];
 * 
 * for(int i = 0; i < departmentList.size(); ++i) {
 * Department dept = (Department)departmentList.get(i);
 * data[i][0] = dept.getId();
 * data[i][1] = dept.getName();
 * }
 * 
 * final JTable departmentTable = new JTable(data, columnNames);
 * departmentTable.setSelectionMode(0);
 * JScrollPane scrollPane = new JScrollPane(departmentTable);
 * final JDialog dialog = new JDialog();
 * dialog.setTitle("Chọn Phòng Ban");
 * dialog.setSize(400, 300);
 * dialog.setLocationRelativeTo((Component)null);
 * dialog.add(scrollPane, "Center");
 * JButton btnChonPhong = new JButton("Chọn Phòng");
 * dialog.add(btnChonPhong, "South");
 * btnChonPhong.addActionListener(new ActionListener() {
 * public void actionPerformed(ActionEvent evt) {
 * int selectedRow = departmentTable.getSelectedRow();
 * if (selectedRow >= 0) {
 * int departmentId = (Integer)departmentTable.getValueAt(selectedRow, 0);
 * String departmentName = (String)departmentTable.getValueAt(selectedRow, 1);
 * JOptionPane.showMessageDialog((Component)null, "Bạn đã chọn phòng ban: " +
 * departmentName + " (ID: " + departmentId + ")");
 * dialog.dispose();
 * } else {
 * JOptionPane.showMessageDialog((Component)null,
 * "Vui lòng chọn một phòng ban");
 * }
 * 
 * }
 * });
 * dialog.setVisible(true);
 * }
 * 
 * private void tblYeuCauTangLuongMouseClicked(MouseEvent evt) {
 * int selectedRow = this.tblYeuCauTangLuong.getSelectedRow();
 * if (selectedRow != -1) {
 * int salaryChangeId = (Integer)this.tblYeuCauTangLuong.getValueAt(selectedRow,
 * 0);
 * SalaryChangeHistoryDAO salaryChangeHistoryDAO =
 * SalaryChangeHistoryDAO.getInstance();
 * SalaryChangeHistory salaryChangeHistory =
 * salaryChangeHistoryDAO.seclectByID(salaryChangeId);
 * SalaryUpdateDialog dialog = new SalaryUpdateDialog((Frame)null,
 * salaryChangeHistory);
 * dialog.setVisible(true);
 * }
 * 
 * }
 * 
 * private void btnRefreshDSYeuCauTangLuongActionPerformed(ActionEvent evt) {
 * this.loadDataToYeuCauTangLuongTable();
 * }
 * 
 * private void btnRefreshLichSuYeuCauActionPerformed(ActionEvent evt) {
 * this.loadDataToYeuCauTangLuongDaXemTable();
 * }
 * 
 * public static void main(String[] args) {
 * try {
 * UIManager.LookAndFeelInfo[] var12 = UIManager.getInstalledLookAndFeels();
 * int var2 = var12.length;
 * 
 * for(int var3 = 0; var3 < var2; ++var3) {
 * UIManager.LookAndFeelInfo info = var12[var3];
 * if ("Nimbus".equals(info.getName())) {
 * UIManager.setLookAndFeel(info.getClassName());
 * break;
 * }
 * }
 * } catch (ClassNotFoundException var5) {
 * Logger.getLogger(SalaryFrame1.class.getName()).log(Level.SEVERE,
 * (String)null, var5);
 * } catch (InstantiationException var6) {
 * Logger.getLogger(SalaryFrame1.class.getName()).log(Level.SEVERE,
 * (String)null, var6);
 * } catch (IllegalAccessException var7) {
 * Logger.getLogger(SalaryFrame1.class.getName()).log(Level.SEVERE,
 * (String)null, var7);
 * } catch (UnsupportedLookAndFeelException var8) {
 * UnsupportedLookAndFeelException ex = var8;
 * Logger.getLogger(SalaryFrame1.class.getName()).log(Level.SEVERE,
 * (String)null, ex);
 * }
 * 
 * EventQueue.invokeLater(new Runnable() {
 * public void run() {
 * (new SalaryFrame1()).setVisible(true);
 * }
 * });
 * }
 * 
 * private void loadListSalariesToTable() {
 * SalaryDAO salaryDAO = SalaryDAO.getInstance();
 * ArrayList<Salary> salaryList = salaryDAO.seclectAll();
 * Object[][] tableData = new Object[salaryList.size()][9];
 * 
 * for(int i = 0; i < salaryList.size(); ++i) {
 * Salary salary = (Salary)salaryList.get(i);
 * tableData[i][0] = salary.getEmployee().getId();
 * tableData[i][1] = salary.getEmployee().getName();
 * tableData[i][2] = salary.getPosition().getName();
 * tableData[i][3] = salary.getPositionSalary();
 * tableData[i][4] = salary.getOvertimeSalary();
 * tableData[i][5] = salary.getAttendance();
 * tableData[i][6] = salary.getBonus();
 * tableData[i][7] = salary.getDeductions();
 * tableData[i][8] = salary.getNetSalary();
 * }
 * 
 * DefaultTableModel model = new DefaultTableModel(tableData, new
 * String[]{"Mã nhân viên", "Họ và tên", "Vị trí", "Lương theo giờ",
 * "Lương tăng ca", "Chuyên cần", "Thưởng", "Khấu trừ", "Tổng lương"});
 * this.tblDanhSachLuong.setModel(model);
 * }
 * 
 * private void loadDataToYeuCauTangLuongTable() {
 * this.tblYeuCauTangLuong.setRowHeight(30);
 * SalaryChangeHistoryDAO salaryChangeHistoryDAO =
 * SalaryChangeHistoryDAO.getInstance();
 * ArrayList<SalaryChangeHistory> salaryChangeList =
 * salaryChangeHistoryDAO.selectAllWithEmployee();
 * Object[][] tableData = new Object[salaryChangeList.size()][7];
 * 
 * for(int i = 0; i < salaryChangeList.size(); ++i) {
 * SalaryChangeHistory history = (SalaryChangeHistory)salaryChangeList.get(i);
 * tableData[i][0] = history.getId();
 * tableData[i][1] = history.getEmployee().getName();
 * tableData[i][2] = history.getOldSalary();
 * tableData[i][3] = history.getNewSalary();
 * tableData[i][4] = history.getChangeDate();
 * tableData[i][5] = history.getReasons();
 * tableData[i][6] = history.getStatus();
 * }
 * 
 * DefaultTableModel model = new DefaultTableModel(tableData, new String[]{"ID",
 * "Nhân viên", "Lương cũ", "Lương mới", "Ngày gửi yêu cầu", "Lý do",
 * "Trạng thái"});
 * this.tblYeuCauTangLuong.setModel(model);
 * this.tblYeuCauTangLuong.getColumnModel().getColumn(6).setCellRenderer(new
 * StatusRenderer());
 * }
 * 
 * private void loadDataToYeuCauTangLuongDaXemTable() {
 * SalaryChangeHistoryDAO salaryChangeHistoryDAO =
 * SalaryChangeHistoryDAO.getInstance();
 * ArrayList<SalaryChangeHistory> salaryChangeList =
 * salaryChangeHistoryDAO.selectReviewed();
 * Object[][] tableData = new Object[salaryChangeList.size()][8];
 * 
 * for(int i = 0; i < salaryChangeList.size(); ++i) {
 * SalaryChangeHistory history = (SalaryChangeHistory)salaryChangeList.get(i);
 * Object[] var10000 = tableData[i];
 * Object[] var10003 = new Object[]{history.getEmployee().getId()};
 * var10000[0] = "NV" + String.format("%03d", var10003);
 * tableData[i][1] = history.getEmployee().getName();
 * tableData[i][2] = history.getOldSalary();
 * tableData[i][3] = history.getNewSalary();
 * tableData[i][4] = history.getReasons();
 * tableData[i][5] = history.getChangeDate();
 * tableData[i][6] = history.getApprovedBy().getName();
 * tableData[i][7] = history.getComments();
 * }
 * 
 * DefaultTableModel model = new DefaultTableModel(tableData, new
 * String[]{"Mã nhân viên", "Họ và tên", "Lương hiện tại", "Lương đề xuất",
 * "Lý do yêu cầu", "Ngày thay đổi", "Người duyệt", "Phản hồi"});
 * this.tblYeuCauTangLuongDaXem.setModel(model);
 * }
 * }
 */
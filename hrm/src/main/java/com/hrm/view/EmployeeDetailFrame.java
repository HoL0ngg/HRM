/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.hrm.view;

import com.hrm.dao.AchievementDAO;
import com.hrm.dao.DepartmentDAO;
import com.hrm.dao.EmployeeDAO;
import com.hrm.dao.PositionDAO;
import com.hrm.dao.SalaryDAO;
import com.hrm.dao.TrainDAO;
import com.hrm.dao.TrainingDevelopmentDAO;
import com.hrm.model.Achievement;
import com.hrm.model.Department;
import com.hrm.model.Employee;
import com.hrm.model.Position;
import com.hrm.model.Salary;
import com.hrm.model.Train;
import com.hrm.model.TrainingDevelopment;
import com.hrm.utils.SSLUtilities;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ...
 */
public class EmployeeDetailFrame extends javax.swing.JFrame {

    private Employee employee;
    
    private javax.swing.ButtonGroup groupTrangThai;
    private javax.swing.ButtonGroup groupCapBac;

    public EmployeeDetailFrame(Employee employee) {
        this.employee = employee;
        init();
    }

    public EmployeeDetailFrame() {
        init();
    }

    private void init() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Khởi tạo và nhóm các RadioButton
        groupTrangThai = new ButtonGroup();
        groupTrangThai.add(rbDangLamViec);
        groupTrangThai.add(rbNghiViec);

        groupCapBac = new ButtonGroup();
        groupCapBac.add(rbNhanVien);
        groupCapBac.add(rbQuanLy);
        
        initData();
        
        // gán sự kiện
        addEvents();
    }
    
    private void addEvents() {
        btnEditThongTinCaNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditThongTinCaNhanActionPerformed(evt);
            }
        });

        btnHoanTatCSThongTinCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanTatCSThongTinCNActionPerformed(evt);
            }
        });
        
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new EmployeeManagerFrame(employee).setVisible(true);
                dispose();
            }
        });
        
        btnXemThongTinLoTrinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hienThiAnh();
            }
        });
    }
    
    private void initData() {
        if (employee != null) {
            this.tvFullname.setText(employee.getName());
            Image AvatarIcon = new ImageIcon(new File("../hrm/src/main/resources/img/profile.png").getAbsolutePath())
                                .getImage()
                                .getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            this.tvFullname.setIcon(new ImageIcon(AvatarIcon));
            this.txtFullname.setText(employee.getName());
            System.out.println("check: " + employee.getPosition());
            if ("male".equals(employee.getGender().name())) {
                this.txtGender.setText("Nam");
            } else {
                this.txtGender.setText("Nữ");
            }
            this.txtEmail.setText(employee.getEmail());
//            this.txtDepartment.setText(employee.getDepartment().getName());
            this.txtPosition.setText(employee.getPosition().getName());
            
            this.txtPhone.setText(employee.getPhone_mumber());
            this.txtAddress.setText(employee.getAddress());
            this.txtIndentifyCard.setText(String.valueOf(employee.getIndentify_card()));
            this.txtBHXH.setText(String.valueOf(employee.getSocial_insurance_code()));
            
            // Cập nhật trạng thái làm việc
            if (employee.getStatus() == Employee.Status.on) {
                rbDangLamViec.setSelected(true);
            } else {
                rbNghiViec.setSelected(true);
            }

            // Cập nhật cấp bậc
            if (employee.getId() == employee.getDepartment().getManagerId()) {
                rbQuanLy.setSelected(true);
            } else {
                rbNhanVien.setSelected(true);
            }
            
            this.txtStartDate.setText(employee.getHire_date().format(DateTimeFormatter.ISO_DATE));
            this.txtHinhThuc.setText(employee.getWork_type().name());
            this.txtThue.setText(employee.getTax_code() + "");
            
            txtSoTaiKhoan.setText(String.valueOf(employee.getAccount_bank()));
            this.txtBHXH.setText(String.valueOf(employee.getSocial_insurance_code()));
//            this.lblViTri.setText(employee.getPosition().getName());

                
            // Load danh sách phòng ban
            loadDepartmentComboBox();

               // đóng lại ko cho chỉnh sửa
            enableEditPersonalInfo(false); 
            
            // Gọi hàm load thông tin lương cho tab Thông tin lương
            loadSalaryData();

            populateTrainingAndAchievementsTable();
        }
       
    }
    
    private void loadDepartmentComboBox() {
        ArrayList<Department> departments = DepartmentDAO.getInstance().selectAll();
        cbPhongBan.removeAllItems(); // Xóa các mục hiện tại trong ComboBox
        for (Department department : departments) {
            // Thêm chuỗi "id - tên phòng" vào ComboBox
            cbPhongBan.addItem(department.getId() + " - " + department.getName());
        }

        // Nếu có thông tin nhân viên, chọn phòng ban tương ứng
        if (employee != null && employee.getDepartment() != null) {
            String selectedItem = employee.getDepartment().getId() + " - " + employee.getDepartment().getName();
            cbPhongBan.setSelectedItem(selectedItem);
        }
    }
    
    private void loadSalaryData() {
        // Sử dụng SalaryDAO để lấy dữ liệu lương
        Salary salary = SalaryDAO.getInstance().selectByEmployeeID2(employee.getId());

        if (salary != null) {
            // Gán thông tin lương vào các trường
            this.txtSoTaiKhoan.setText(employee.getAccount_bank() + ""); // Số tài khoản
            this.txtLuongCoBan.setText(salary.getPositionSalary().toString()); // Lương cơ bản
            
            // Giả sử salary.getPositionSalary() trả về kiểu BigDecimal
            BigDecimal positionSalary = salary.getPositionSalary(); // Lương theo vị trí
            BigDecimal workingDays = new BigDecimal(26); // 26 ngày làm việc
            BigDecimal workingHours = new BigDecimal(8); // 8 giờ mỗi ngày
            BigDecimal factor = new BigDecimal(1.5); // Hệ số nhân 1.5

            // Tính lương theo giờ: (positionSalary / 26 / 8) * 1.5
            BigDecimal luongTheoGio = positionSalary
                    .divide(workingDays, 2, RoundingMode.HALF_UP) // positionSalary / 26
                    .divide(workingHours, 2, RoundingMode.HALF_UP) // / 8
                    .multiply(factor); // * 1.5

            this.txtLuongTheoGio.setText(luongTheoGio + "");
            this.txtLuongTheoGio.setEditable(false); // Không cho phép chỉnh sửa nội dung
            this.txtLuongTheoGio.setFocusable(false); // Không cho phép người dùng focus vào ô
            
            
            this.txtBHXH.setText(employee.getSocial_insurance_code() + ""); // BHXH (khấu trừ)
            this.txtThue.setText(employee.getTax_code() + ""); // Thuế (bonus)
        } else {
            // Xử lý nếu không có dữ liệu lương
            System.out.println("Không tìm thấy thông tin lương cho nhân viên ID: " + employee.getId());
        }
    }

    private final String[] menu = new String[]{"Mã nhân viên", "Họ và tên", "Trạng thái làm việc", "Email", "Vị trí", "Phòng ban"};

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        txtIndentifyCard = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rbQuanLy = new javax.swing.JRadioButton();
        rbNhanVien = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        rbDangLamViec = new javax.swing.JRadioButton();
        rbNghiViec = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        txtStartDate = new javax.swing.JTextField();
        txtHinhThuc = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtSoTaiKhoan = new javax.swing.JTextField();
        txtLuongCoBan = new javax.swing.JTextField();
        txtBHXH = new javax.swing.JTextField();
        txtThue = new javax.swing.JTextField();
        txtLuongTheoGio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChuongTrinhDaoTaoThanhTu = new javax.swing.JTable();
        btnXemThongTinLoTrinh = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtFullname = new javax.swing.JTextField();
        txtGender = new javax.swing.JTextField();
        txtPosition = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnEditThongTinCaNhan = new javax.swing.JButton();
        btnHoanTatCSThongTinCN = new javax.swing.JButton();
        cbPhongBan = new javax.swing.JComboBox<>();
        pnMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tvFullname = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chương trình quản lý nhân viên");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setText("Họ và tên");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Số điện thoại");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Địa chỉ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Indentify card");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPhone)
                    .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                .addGap(133, 133, 133)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtIndentifyCard, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIndentifyCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(316, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông tin cá nhân", jPanel1);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Cấp bậc");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Ngày bắt đầu");

        rbQuanLy.setText("Quản lý");
        rbQuanLy.setEnabled(false);

        rbNhanVien.setSelected(true);
        rbNhanVien.setText("Nhân viên");
        rbNhanVien.setEnabled(false);
        rbNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNhanVienActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Trạng thái");

        rbDangLamViec.setText("Đang làm việc");
        rbDangLamViec.setToolTipText("");
        rbDangLamViec.setEnabled(false);
        rbDangLamViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDangLamViecActionPerformed(evt);
            }
        });

        rbNghiViec.setText("Nghỉ việc");
        rbNghiViec.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Hình thức");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(rbDangLamViec)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbNghiViec, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(rbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(rbQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(488, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(rbNhanVien)
                    .addComponent(rbQuanLy))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(rbDangLamViec)
                    .addComponent(rbNghiViec))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(257, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông tin công việc", jPanel3);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Thông tin ngân hàng");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Số tài khoản");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Tên ngân hàng");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("TPBank");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("BHXH");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Lương cơ bản");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setText("Thông tin lương");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Thuế");

        txtLuongTheoGio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLuongTheoGioActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Lương tăng ca");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(58, 58, 58)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtLuongTheoGio)
                            .addComponent(txtLuongCoBan)
                            .addComponent(txtBHXH)
                            .addComponent(txtThue, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel21))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel20)
                    .addComponent(txtSoTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLuongCoBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(txtLuongTheoGio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtBHXH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addContainerGap(189, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông tin lương", jPanel4);

        tblChuongTrinhDaoTaoThanhTu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblChuongTrinhDaoTaoThanhTu);

        btnXemThongTinLoTrinh.setText("Xem thông tin lộ trình");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(btnXemThongTinLoTrinh)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnXemThongTinLoTrinh)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông tin đào tạo và phát triển", jPanel7);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setText("Giới tính");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("Email");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Phòng ban");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("Vị trí");

        btnEditThongTinCaNhan.setText("Chỉnh sửa thông tin");

        btnHoanTatCSThongTinCN.setText("Hoàn tất");

        cbPhongBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel23))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtGender)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28))
                        .addGap(29, 29, 29))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnEditThongTinCaNhan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnHoanTatCSThongTinCN)
                        .addGap(135, 135, 135))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(120, 120, 120))))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 868, Short.MAX_VALUE)
                    .addGap(11, 11, 11)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel27)
                    .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel28)
                    .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 483, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditThongTinCaNhan)
                    .addComponent(btnHoanTatCSThongTinCN))
                .addGap(55, 55, 55))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(123, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(87, 87, 87)))
        );

        pnMenu.setBackground(new java.awt.Color(245, 143, 82));

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel1.setText("Quan lý nhân viên");

        tvFullname.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        btnBack.setText("Trở về");

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnBack)
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 358, Short.MAX_VALUE)
                .addComponent(tvFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tvFullname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(676, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 45, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbNhanVienActionPerformed

    private void rbDangLamViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDangLamViecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbDangLamViecActionPerformed

    private void txtLuongTheoGioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLuongTheoGioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLuongTheoGioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeDetailFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEditThongTinCaNhan;
    private javax.swing.JButton btnHoanTatCSThongTinCN;
    private javax.swing.JButton btnXemThongTinLoTrinh;
    private javax.swing.JComboBox<String> cbPhongBan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JRadioButton rbDangLamViec;
    private javax.swing.JRadioButton rbNghiViec;
    private javax.swing.JRadioButton rbNhanVien;
    private javax.swing.JRadioButton rbQuanLy;
    private javax.swing.JTable tblChuongTrinhDaoTaoThanhTu;
    private javax.swing.JLabel tvFullname;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtBHXH;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullname;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtHinhThuc;
    private javax.swing.JTextField txtIndentifyCard;
    private javax.swing.JTextField txtLuongCoBan;
    private javax.swing.JTextField txtLuongTheoGio;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPosition;
    private javax.swing.JTextField txtSoTaiKhoan;
    private javax.swing.JTextField txtStartDate;
    private javax.swing.JTextField txtThue;
    // End of variables declaration//GEN-END:variables

    private void populateTrainingAndAchievementsTable() {
        // Xác định cột trong bảng
        DefaultTableModel model = new DefaultTableModel(new String[]{"Chương trình đào tạo", "Thành tựu"}, 0);
        tblChuongTrinhDaoTaoThanhTu.setModel(model);

        // Lấy danh sách train (chương trình đào tạo + nhân viên)
        ArrayList<Train> trains = TrainDAO.getInstance().getTrainsByEmployeeId(employee.getId());

        // Lấy danh sách thành tựu của nhân viên
        ArrayList<Achievement> achievements = AchievementDAO.getInstance().getAchievementsByEmployeeId(employee.getId());

        // Tìm số dòng tối đa để đồng bộ dữ liệu giữa hai danh sách
        int maxRows = Math.max(trains.size(), achievements.size());

        // Điền dữ liệu vào bảng theo từng hàng
        for (int i = 0; i < maxRows; i++) {
            String trainingName = i < trains.size() ? trains.get(i).getTrainingDevelopment().getName() : ""; // Lấy tên chương trình đào tạo hoặc để trống
            String achievementTitle = i < achievements.size() ? achievements.get(i).getTitle() : ""; // Lấy tiêu đề thành tựu hoặc để trống
            model.addRow(new Object[]{trainingName, achievementTitle});
        }
    }
    
    private void enableEditPersonalInfo(boolean enable) {
        // Tab Thông tin cá nhân
        txtPhone.setEditable(enable);
        txtIndentifyCard.setEditable(enable);
        txtAddress.setEditable(enable);
        txtBHXH.setEditable(enable);
        txtFullname.setEditable(enable);
        txtGender.setEditable(enable);
        txtEmail.setEditable(enable);
        cbPhongBan.setEditable(enable);
        txtPosition.setEditable(enable);

        // Tab Thông tin công việc
        txtStartDate.setEditable(enable);
        txtHinhThuc.setEditable(enable);
        rbNhanVien.setEnabled(enable);
        rbQuanLy.setEnabled(enable);
        rbDangLamViec.setEnabled(enable);
        rbNghiViec.setEnabled(enable);

        // Tab Thông tin lương
        txtSoTaiKhoan.setEditable(enable); // Số tài khoản
        txtLuongCoBan.setEditable(enable);
        txtBHXH.setEditable(enable);
        txtThue.setEditable(enable);

        // Nút hoàn tất chỉ bật khi đang ở chế độ chỉnh sửa
        btnHoanTatCSThongTinCN.setEnabled(enable);
        cbPhongBan.setEnabled(false);
    }


    
    // Sự kiện cho nút "Chỉnh sửa thông tin"
    private void btnEditThongTinCaNhanActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        enableEditPersonalInfo(true); // Cho phép chỉnh sửa
    }

    private void btnHoanTatCSThongTinCNActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Lấy thông tin từ các trường nhập liệu
            String name = txtFullname.getText().trim();
            String phone = txtPhone.getText().trim();
            String address = txtAddress.getText().trim();
            String email = txtEmail.getText().trim();
            String indentifyCardStr = txtIndentifyCard.getText().trim();
            String bhxhStr = txtBHXH.getText().trim();
            String startDateStr = txtStartDate.getText().trim();
            String luongCoBanStr = txtLuongCoBan.getText().trim();
            String hinhThuc = txtHinhThuc.getText().trim();
            String thueStr = txtThue.getText().trim();

            // Kiểm tra điều kiện đầu vào
        if (name.isEmpty() || name.length() > 50) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống hoặc vượt quá 50 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải gồm đúng 10 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (address.isEmpty() || address.length() > 50) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống hoặc vượt quá 50 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (email.isEmpty() || email.length() > 50 || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ hoặc vượt quá 50 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!indentifyCardStr.matches("\\d{1,40}")) {
            JOptionPane.showMessageDialog(this, "CMND/CCCD phải là số và không vượt quá 50 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!bhxhStr.matches("\\d{1,40}")) {
            JOptionPane.showMessageDialog(this, "Mã BHXH phải là số và không vượt quá 50 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!startDateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải theo định dạng YYYY-MM-DD!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!luongCoBanStr.matches("\\d{1,8}(\\.\\d{1,2})?")) {
            JOptionPane.showMessageDialog(this, "Lương cơ bản phải là số và không vượt quá 8 chữ số phần nguyên và 2 chữ số phần thập phân!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!thueStr.matches("\\d{1,8}(\\.\\d{1,2})?")) {
            JOptionPane.showMessageDialog(this, "Thuế phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

//        if (!hinhThuc.matches("full_time|part_time|internship|on_board|work_from_home")) {
//            JOptionPane.showMessageDialog(this, "Hình thức làm việc không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        }

            // Tiếp tục xử lý logic cập nhật sau khi kiểm tra dữ liệu
            employee.setName(name);
            employee.setPhone_mumber(phone);
            employee.setAddress(address);
            employee.setEmail(email);
            // Lấy chuỗi được chọn từ ComboBox
            String selectedItem = (String) cbPhongBan.getSelectedItem();
            if (selectedItem != null) {
                // Tách chuỗi "id - tên phòng" để lấy id
                String[] parts = selectedItem.split(" - ");
                int departmentId = Integer.parseInt(parts[0]); // Lấy id phòng ban

                // Gán id phòng ban vào nhân viên
                employee.setDepartment_id(departmentId);
            }
            employee.setIndentify_card(Integer.parseInt(indentifyCardStr));
            employee.setSocial_insurance_code(Integer.parseInt(bhxhStr));
            employee.setHire_date(LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE));
            employee.setStatus(rbDangLamViec.isSelected() ? Employee.Status.on : Employee.Status.off);
            employee.setAccount_bank(Integer.parseInt(txtSoTaiKhoan.getText()));
            employee.setTax_code(Integer.parseInt(thueStr)); 
            // employee.setSocial_insurance_code(Integer.parseInt(txtBHXH.getText()));
            // Lấy thông tin giới tính
            String genderInput = txtGender.getText().trim();
            if (genderInput.isEmpty() || (!genderInput.equalsIgnoreCase("Nam") && !genderInput.equalsIgnoreCase("Nữ"))) {
                JOptionPane.showMessageDialog(this, "Giới tính phải là 'Nam' hoặc 'Nữ'!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Chuyển đổi giới tính sang enum
            Employee.Gender genderEnum = genderInput.equalsIgnoreCase("Nam") ? Employee.Gender.male : Employee.Gender.female;
            // Gán giới tính vào employee
            employee.setGender(genderEnum);
            
             // Lấy thông tin hình thức làm việc
            String workTypeInput = txtHinhThuc.getText().trim();
                if (workTypeInput.isEmpty() || (!workTypeInput.equalsIgnoreCase("full_time") && !workTypeInput.equalsIgnoreCase("part_time"))) {
                    JOptionPane.showMessageDialog(this, "Hình thức làm việc phải là 'full_time' hoặc 'part_time'!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
            }
            // Chuyển đổi hình thức làm việc sang enum
            Employee.Work_type workTypeEnum = workTypeInput.equalsIgnoreCase("full_time")
                    ? Employee.Work_type.full_time
                    : Employee.Work_type.part_time;
            // Gán hình thức làm việc vào employee
            employee.setWork_type(workTypeEnum);

            // Xử lý logic cập nhật cấp bậc
            if (rbQuanLy.isSelected()) {
                employee.setLevel("Quản lý");
            } else {
                employee.setLevel("Nhân viên");
            }

            // Cập nhật Salary
            Salary salary = SalaryDAO.getInstance().selectByEmployeeID(employee.getId());
            if (salary != null) {
                salary.setPositionSalary(new BigDecimal(txtLuongCoBan.getText()));
            }

            // Gọi DAO để cập nhật dữ liệu vào cơ sở dữ liệu
            boolean employeeUpdated = EmployeeDAO.getInstance().capnhat(employee);
            boolean salaryUpdated = salary != null && SalaryDAO.getInstance().capnhat(salary);
            
            // cập nhật vị trí
            Position position = employee.getPosition();
            Department department = employee.getDepartment();
            
            position.setDepartmentId(department.getId());
            position.setName(txtPosition.getText()); // cập nhật tên vị trí mới
            boolean positionUpted = PositionDAO.getInstance().update(position);

            // Hiển thị thông báo
            if (employeeUpdated && salaryUpdated && positionUpted) {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin cá nhân, lương và vị trí thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ và hiển thị thông báo lỗi
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Đóng chế độ chỉnh sửa
            enableEditPersonalInfo(false);
        }
    }

    private void hienThiAnh() {
        String imageUrl = employee.getImage(); // URL ảnh của nhân viên

        // Hiển thị hộp thoại
        showTrainingDetailsDialog(imageUrl);
    }
    
    private void showTrainingDetailsDialog(String imageUrl) {
    // Khởi tạo SSLUtilities để bỏ qua xác minh SSL
    new SSLUtilities();

    // Kiểm tra URL
    if (imageUrl == null || imageUrl.isEmpty()) {
        JOptionPane.showMessageDialog(this, "URL ảnh không hợp lệ hoặc không có.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Chuyển đổi URL nếu là Google Drive
    if (imageUrl.contains("drive.google.com")) {
        try {
            String fileId = imageUrl.split("/d/")[1].split("/")[0];
            imageUrl = "https://drive.google.com/uc?export=view&id=" + fileId;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "URL Google Drive không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    // Tạo JDialog
    JDialog dialog = new JDialog(this, "Thông tin Lộ trình", true);
    dialog.setSize(500, 800); // Đặt kích thước
    dialog.setLayout(new BorderLayout());
    dialog.setLocationRelativeTo(this);

    // Tạo JLabel cho URL
    JTextField txtUrl = new JTextField(imageUrl);
    txtUrl.setEditable(false); // Không cho chỉnh sửa URL
    dialog.add(txtUrl, BorderLayout.NORTH); // Thêm URL ở phía Bắc

    // Tạo JLabel cho ảnh
    JLabel lblImage = new JLabel();
    lblImage.setHorizontalAlignment(SwingConstants.CENTER);

    // Tải ảnh từ URL
    try {
        ImageIcon icon = new ImageIcon(new java.net.URL(imageUrl));
        if (icon.getIconWidth() > 0 && icon.getIconHeight() > 0) { // Kiểm tra kích thước ảnh
            int width = 400;
            int height = (int) (width * 1.7); // Chiều cao gấp 1.7 lần chiều rộng
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(scaledImage));
        } else {
            lblImage.setText("Không thể tải ảnh: Kích thước không hợp lệ.");
        }
    } catch (Exception e) {
        lblImage.setText("Không thể tải ảnh từ URL: " + e.getMessage());
        e.printStackTrace();
    }
    dialog.add(lblImage, BorderLayout.CENTER); // Thêm ảnh ở trung tâm

    // Nút đóng
    JButton btnClose = new JButton("Đóng");
    btnClose.addActionListener(e -> dialog.dispose());
    JPanel southPanel = new JPanel(); // Tạo JPanel để đặt nút đóng ở phía Nam
    southPanel.add(btnClose);
    dialog.add(southPanel, BorderLayout.SOUTH); // Thêm nút đóng ở phía Nam

    // Hiển thị dialog
    dialog.setVisible(true);
}



}

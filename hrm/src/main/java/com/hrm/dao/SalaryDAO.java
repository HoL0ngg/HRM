//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hrm.dao;

import com.hrm.db.JDBCUtil;
import com.hrm.model.Department;
import com.hrm.model.Employee;
import com.hrm.model.Position;
import com.hrm.model.Salary;
import com.hrm.model.Employee.Gender;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SalaryDAO implements DAOInterface<Salary> {
    public SalaryDAO() {
    }

    public static SalaryDAO getInstance() {
        return new SalaryDAO();
    }

    @Override
    public int them(Salary salary) {
        String sql = "INSERT INTO salaries (employee_id, position_salary, bonus, deductions, net_salary, overtime_salary, payday, note, attendance) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection con = JDBCUtil.createConnection();
        int result = 0;

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            // Thiết lập các tham số cho câu lệnh SQL
            pst.setInt(1, salary.getEmployee().getId()); // ID nhân viên
            pst.setBigDecimal(2, salary.getPositionSalary()); // Lương cơ bản
            pst.setBigDecimal(3, salary.getBonus()); // Thưởng
            pst.setBigDecimal(4, salary.getDeductions()); // Khấu trừ
            pst.setBigDecimal(5, salary.getNetSalary()); // Lương thực nhận
            pst.setBigDecimal(6, salary.getOvertimeSalary()); // Lương ngoài giờ
            pst.setObject(7, salary.getPayday()); // Ngày trả lương
            pst.setString(8, salary.getNote()); // Ghi chú
            pst.setInt(9, salary.getAttendance()); // Số ngày công

            // Thực thi câu lệnh và nhận số hàng bị ảnh hưởng
            result = pst.executeUpdate();

            if (result > 0) {
                System.out.println("Thêm mới thông tin lương thành công cho nhân viên ID: " + salary.getEmployee().getId());
            } else {
                System.out.println("Thêm mới thông tin lương thất bại.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return result;
    }

    @Override
    public boolean xoa(Salary object) {
        throw new UnsupportedOperationException("Unimplemented method 'xoa'");
    }

    @Override
    public boolean capnhat(Salary salary) {
        String sql = "UPDATE salaries " +
                 "SET position_salary = ?, bonus = ?, deductions = ?, net_salary = ?, overtime_salary = ?, payday = ?, note = ?, attendance = ? " +
                 "WHERE id = ?";
        Connection con = JDBCUtil.createConnection();
        boolean isUpdated = false;

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            // Gán các giá trị từ đối tượng Salary
            pst.setBigDecimal(1, salary.getPositionSalary());
            pst.setBigDecimal(2, salary.getBonus());
            pst.setBigDecimal(3, salary.getDeductions());
            pst.setBigDecimal(4, salary.getNetSalary());
            pst.setBigDecimal(5, salary.getOvertimeSalary());
            pst.setObject(6, salary.getPayday()); // Sử dụng `setObject` cho kiểu LocalDate
            pst.setString(7, salary.getNote());
            pst.setInt(8, salary.getAttendance());
            pst.setInt(9, salary.getId());

            // Thực thi câu lệnh SQL
            int rowsAffected = pst.executeUpdate();
            isUpdated = rowsAffected > 0; // Nếu có ít nhất 1 dòng được cập nhật thì trả về true

            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return isUpdated;
    }

    @Override
    public ArrayList<Salary> selectAll() {
        String sql = "SELECT s.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, e.email, p.id as position_id, d.id as departments_id, d.name as departments_name, p.name as position_name FROM salaries s JOIN employee e ON s.employee_id = e.id JOIN departments d ON e.departments_id = d.id JOIN position p ON e.position_id = p.id";
        Connection con = JDBCUtil.createConnection();
        ArrayList<Salary> salaryList = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                ResultSet rs = pst.executeQuery();

                while(rs.next()) {
                    int id = rs.getInt("id");
                    BigDecimal positionSalary = rs.getBigDecimal("position_salary");
                    BigDecimal bonus = rs.getBigDecimal("bonus");
                    BigDecimal deductions = rs.getBigDecimal("deductions");
                    BigDecimal netSalary = rs.getBigDecimal("net_salary");
                    BigDecimal overtimeSalary = rs.getBigDecimal("overtime_salary");
                    LocalDate payday = (LocalDate)rs.getObject("payday", LocalDate.class);
                    String note = rs.getString("note");
                    int attendance = rs.getInt("attendance");
                    int employeeId = rs.getInt("employee_id");
                    String employeeName = rs.getString("employee_name");
                    String email = rs.getString("email");
                    String genderStr = rs.getString("gender").toLowerCase();
                    Employee.Gender gender = Gender.valueOf(genderStr);
                    String phoneNumber = rs.getString("phone_number");
                    Employee employee = new Employee();
                    employee.setId(employeeId);
                    employee.setEmail(email);
                    employee.setName(employeeName);
                    employee.setGender(gender);
                    employee.setPhone_mumber(phoneNumber);
                    Department department = new Department();
                    department.setId(rs.getInt("departments_id"));
                    department.setName(rs.getString("departments_name"));
                    employee.setDepartment(department);
                    int positionId = rs.getInt("position_id");
                    String positionName = rs.getString("position_name");
                    Position position = new Position(positionId, 0, positionName);
                    Salary salary = new Salary(id, employee, positionSalary, bonus, deductions, netSalary, overtimeSalary, payday, note, attendance, position);
                    salaryList.add(salary);
                }
            } catch (Throwable var31) {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (Throwable var30) {
                        var31.addSuppressed(var30);
                    }
                }

                throw var31;
            }

            if (pst != null) {
                pst.close();
            }
        } catch (SQLException var32) {
            SQLException e = var32;
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return salaryList;
    }

    public Salary selectByEmployeeID(int employeeId) {
        String sql = "SELECT * FROM salaries WHERE employee_id = ?";
        Connection con = JDBCUtil.createConnection();
        Salary salary = null;

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                pst.setInt(1, employeeId);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    BigDecimal positionSalary = rs.getBigDecimal("position_salary");
                    BigDecimal bonus = rs.getBigDecimal("bonus");
                    BigDecimal deductions = rs.getBigDecimal("deductions");
                    BigDecimal netSalary = rs.getBigDecimal("net_salary");
                    BigDecimal overtimeSalary = rs.getBigDecimal("overtime_salary");
                    LocalDate payday = (LocalDate)rs.getObject("payday", LocalDate.class);
                    String note = rs.getString("note");
                    int attendance = rs.getInt("attendance");
                    salary = new Salary(id, employeeId, positionSalary, bonus, deductions, netSalary, overtimeSalary, payday, note, attendance);
                }
            } catch (Throwable var22) {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (Throwable var21) {
                        var22.addSuppressed(var21);
                    }
                }

                throw var22;
            }

            if (pst != null) {
                pst.close();
            }
        } catch (SQLException var23) {
            SQLException e = var23;
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return salary;
    }

    @Override
    public Salary selectByID(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<Salary> selectByMonth(int month) {
        String sql = "SELECT s.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, p.id as position_id, p.name as position_name FROM salaries s JOIN employee e ON s.employee_id = e.id JOIN position p ON e.position_id = p.id WHERE MONTH(s.payday) = ?";
        Connection con = JDBCUtil.createConnection();
        ArrayList<Salary> salaryList = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                pst.setInt(1, month);
                ResultSet rs = pst.executeQuery();

                while(rs.next()) {
                    int id = rs.getInt("id");
                    BigDecimal positionSalary = rs.getBigDecimal("position_salary");
                    BigDecimal bonus = rs.getBigDecimal("bonus");
                    BigDecimal deductions = rs.getBigDecimal("deductions");
                    BigDecimal netSalary = rs.getBigDecimal("net_salary");
                    BigDecimal overtimeSalary = rs.getBigDecimal("overtime_salary");
                    LocalDate payday = (LocalDate)rs.getObject("payday", LocalDate.class);
                    String note = rs.getString("note");
                    int attendance = rs.getInt("attendance");
                    int employeeId = rs.getInt("employee_id");
                    String employeeName = rs.getString("employee_name");
                    String genderStr = rs.getString("gender").toLowerCase();
                    Employee.Gender gender = Gender.valueOf(genderStr);
                    String phoneNumber = rs.getString("phone_number");
                    Employee employee = new Employee();
                    employee.setId(employeeId);
                    employee.setName(employeeName);
                    employee.setGender(gender);
                    employee.setPhone_mumber(phoneNumber);
                    int positionId = rs.getInt("position_id");
                    String positionName = rs.getString("position_name");
                    Position position = new Position(positionId, 0, positionName);
                    Salary salary = new Salary(id, employee, positionSalary, bonus, deductions, netSalary, overtimeSalary, payday, note, attendance, position);
                    salaryList.add(salary);
                }
            } catch (Throwable var32) {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (Throwable var31) {
                        var32.addSuppressed(var31);
                    }
                }

                throw var32;
            }

            if (pst != null) {
                pst.close();
            }
        } catch (SQLException var33) {
            SQLException e = var33;
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return salaryList;
    }
    
    public Salary selectByEmployeeID2(int employeeId) {
        String sql = "SELECT s.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, " +
                     "p.id as position_id, p.name as position_name " +
                     "FROM salaries s " +
                     "JOIN employee e ON s.employee_id = e.id " +
                     "JOIN position p ON e.position_id = p.id " +
                     "WHERE s.employee_id = ?";
        Connection con = JDBCUtil.createConnection();
        Salary salary = null;

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, employeeId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                BigDecimal positionSalary = rs.getBigDecimal("position_salary");
                BigDecimal bonus = rs.getBigDecimal("bonus");
                BigDecimal deductions = rs.getBigDecimal("deductions");
                BigDecimal netSalary = rs.getBigDecimal("net_salary");
                BigDecimal overtimeSalary = rs.getBigDecimal("overtime_salary");
                LocalDate payday = rs.getObject("payday", LocalDate.class);
                String note = rs.getString("note");
                int attendance = rs.getInt("attendance");

                // Tạo đối tượng Employee
                Employee employee = new Employee();
                employee.setId(rs.getInt("employee_id"));
                employee.setName(rs.getString("employee_name"));
                employee.setGender(Employee.Gender.valueOf(rs.getString("gender").toLowerCase()));
                employee.setPhone_mumber(rs.getString("phone_number"));

                // Tạo đối tượng Position
                Position position = new Position();
                position.setId(rs.getInt("position_id"));
                position.setName(rs.getString("position_name"));

                // Khởi tạo Salary
                salary = new Salary(id, employee, positionSalary, bonus, deductions, netSalary, overtimeSalary, payday, note, attendance, position);
            }

            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return salary;
    }
    
    public int themLuongCoBan(int id, int employeeId, BigDecimal positionSalary) {
        String sql = "INSERT INTO salaries (id, employee_id, position_salary) VALUES (?, ?, ?)";
        Connection con = JDBCUtil.createConnection();
        int result = 0;

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            // Gán các tham số vào câu lệnh SQL
            pst.setInt(1, id); // ID lương
            pst.setInt(2, employeeId); // ID nhân viên
            pst.setBigDecimal(3, positionSalary); // Lương cơ bản

            // Thực thi câu lệnh và nhận số hàng bị ảnh hưởng
            result = pst.executeUpdate();

            if (result > 0) {
                System.out.println("Thêm lương cơ bản thành công với ID lương: " + id);
            } else {
                System.out.println("Thêm lương cơ bản thất bại.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return result;
    }
    
    public int findMaxId() {
        String sql = "SELECT MAX(id) AS max_id FROM salaries";
        Connection con = JDBCUtil.createConnection();
        int maxId = -1; // Giá trị mặc định nếu không tìm thấy

        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                maxId = rs.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return maxId;
    }
}

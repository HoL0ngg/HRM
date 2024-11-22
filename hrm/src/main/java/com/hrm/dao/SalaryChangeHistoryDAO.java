//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hrm.dao;

import com.hrm.db.JDBCUtil;
import com.hrm.model.Employee;
import com.hrm.model.SalaryChangeHistory;
import com.hrm.model.Employee.Gender;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SalaryChangeHistoryDAO implements DAOInterface<SalaryChangeHistory> {
    public SalaryChangeHistoryDAO() {
    }

    public static SalaryChangeHistoryDAO getInstance() {
        return new SalaryChangeHistoryDAO();
    }

    @Override
    public int them(SalaryChangeHistory object) {
        throw new UnsupportedOperationException("Unimplemented method 'them'");
    }

    @Override
    public boolean xoa(SalaryChangeHistory object) {
        throw new UnsupportedOperationException("Unimplemented method 'xoa'");
    }

    @Override
    public boolean capnhat(SalaryChangeHistory object) {
        throw new UnsupportedOperationException("Unimplemented method 'capnhat'");
    }

    @Override
    public ArrayList<SalaryChangeHistory> selectAll() {
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }

    @Override
    public SalaryChangeHistory selectByID(int id) {
        String sql = "SELECT sch.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, approved.id as approved_by_id, approved.name as approved_by_name FROM salary_change_history sch JOIN employee e ON sch.employee_id = e.id JOIN employee approved ON sch.approved_by = approved.id WHERE sch.id = ?";
        Connection con = JDBCUtil.createConnection();
        SalaryChangeHistory salaryChangeHistory = null;

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    int employeeId = rs.getInt("employee_id");
                    String employeeName = rs.getString("employee_name");
                    Employee employee = new Employee(employeeId, employeeName);
                    int approvedById = rs.getInt("approved_by_id");
                    String approvedByName = rs.getString("approved_by_name");
                    Employee approvedBy = new Employee(approvedById, approvedByName);
                    salaryChangeHistory = new SalaryChangeHistory(rs.getInt("id"), employee,
                            rs.getBigDecimal("old_salary"), rs.getBigDecimal("new_salary"), rs.getString("reasons"),
                            (LocalDate) rs.getObject("change_date", LocalDate.class), approvedBy,
                            rs.getString("comments"), rs.getString("status"));
                }
            } catch (Throwable var19) {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (Throwable var18) {
                        var19.addSuppressed(var18);
                    }
                }

                throw var19;
            }

            if (pst != null) {
                pst.close();
            }
        } catch (SQLException var20) {
            SQLException e = var20;
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return salaryChangeHistory;
    }

    public ArrayList<SalaryChangeHistory> selectAllWithEmployee() {
        String sql = "SELECT sch.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, approved.id as approved_by_id, approved.name as approved_by_name FROM salary_change_history sch JOIN employee e ON sch.employee_id = e.id JOIN employee approved ON sch.approved_by = approved.id";
        Connection con = JDBCUtil.createConnection();
        ArrayList<SalaryChangeHistory> salaryChangeHistoryList = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    BigDecimal oldSalary = rs.getBigDecimal("old_salary");
                    BigDecimal newSalary = rs.getBigDecimal("new_salary");
                    String reasons = rs.getString("reasons");
                    LocalDate changeDate = (LocalDate) rs.getObject("change_date", LocalDate.class);
                    String comments = rs.getString("comments");
                    String status = rs.getString("status");
                    int employeeId = rs.getInt("employee_id");
                    String employeeName = rs.getString("employee_name");
                    Employee.Gender gender = Gender.valueOf(rs.getString("gender").toLowerCase());
                    String phoneNumber = rs.getString("phone_number");
                    Employee employee = new Employee();
                    employee.setId(employeeId);
                    employee.setName(employeeName);
                    employee.setGender(gender);
                    employee.setPhone_mumber(phoneNumber);
                    int approvedById = rs.getInt("approved_by_id");
                    String approvedByName = rs.getString("approved_by_name");
                    Employee approvedBy = new Employee();
                    approvedBy.setId(approvedById);
                    approvedBy.setName(approvedByName);
                    SalaryChangeHistory salaryChangeHistory = new SalaryChangeHistory(id, employee, oldSalary,
                            newSalary, reasons, changeDate, approvedBy, comments, status);
                    salaryChangeHistoryList.add(salaryChangeHistory);
                }
            } catch (Throwable var28) {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (Throwable var27) {
                        var28.addSuppressed(var27);
                    }
                }

                throw var28;
            }

            if (pst != null) {
                pst.close();
            }
        } catch (SQLException var29) {
            SQLException e = var29;
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return salaryChangeHistoryList;
    }

    public ArrayList<SalaryChangeHistory> selectReviewed() {
        String sql = "SELECT sch.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, approved.id as approved_by_id, approved.name as approved_by_name FROM salary_change_history sch JOIN employee e ON sch.employee_id = e.id JOIN employee approved ON sch.approved_by = approved.id WHERE sch.status IN ('pass', 'fail')";
        Connection con = JDBCUtil.createConnection();
        ArrayList<SalaryChangeHistory> salaryChangeHistoryList = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    BigDecimal oldSalary = rs.getBigDecimal("old_salary");
                    BigDecimal newSalary = rs.getBigDecimal("new_salary");
                    String reasons = rs.getString("reasons");
                    LocalDate changeDate = (LocalDate) rs.getObject("change_date", LocalDate.class);
                    String comments = rs.getString("comments");
                    String status = rs.getString("status");
                    int employeeId = rs.getInt("employee_id");
                    String employeeName = rs.getString("employee_name");
                    Employee employee = new Employee();
                    employee.setId(employeeId);
                    employee.setName(employeeName);
                    int approvedById = rs.getInt("approved_by_id");
                    String approvedByName = rs.getString("approved_by_name");
                    Employee approvedBy = new Employee();
                    approvedBy.setId(approvedById);
                    approvedBy.setName(approvedByName);
                    SalaryChangeHistory salaryChangeHistory = new SalaryChangeHistory(id, employee, oldSalary,
                            newSalary, reasons, changeDate, approvedBy, comments, status);
                    salaryChangeHistoryList.add(salaryChangeHistory);
                }
            } catch (Throwable var26) {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (Throwable var25) {
                        var26.addSuppressed(var25);
                    }
                }

                throw var26;
            }

            if (pst != null) {
                pst.close();
            }
        } catch (SQLException var27) {
            SQLException e = var27;
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return salaryChangeHistoryList;
    }

    public boolean update(SalaryChangeHistory history) {
        String sql = "UPDATE salary_change_history SET new_salary = ?, reasons = ?, comments = ?, approved_by = ?, status = ? WHERE id = ?";
        Connection con = JDBCUtil.createConnection();
        boolean updated = false;

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                pst.setBigDecimal(1, history.getNewSalary());
                pst.setString(2, history.getReasons());
                pst.setString(3, history.getComments());
                pst.setInt(4, history.getApprovedBy().getId());
                pst.setString(5, history.getStatus());
                pst.setInt(6, history.getId());
                updated = pst.executeUpdate() > 0;
            } catch (Throwable var14) {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (Throwable var13) {
                        var14.addSuppressed(var13);
                    }
                }

                throw var14;
            }

            if (pst != null) {
                pst.close();
            }
        } catch (SQLException var15) {
            SQLException e = var15;
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return updated;
    }

    public ArrayList<SalaryChangeHistory> selectByMonthdaxem(int month) {
        String sql = "SELECT sch.id, e.id AS employee_id, e.name AS employee_name, sch.old_salary, " +
                "sch.new_salary, sch.change_date, sch.reasons, sch.approved_by, sch.comments " +
                "FROM salary_change_history sch " +
                "JOIN employee e ON sch.employee_id = e.id " +
                "LEFT JOIN employee e2 ON sch.approved_by = e2.id " + // LEFT JOIN để lấy tên người duyệt
                "WHERE MONTH(sch.change_date) = ?"; // Lọc theo tháng

        Connection con = JDBCUtil.createConnection();
        ArrayList<SalaryChangeHistory> historyList = new ArrayList<>();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, month); // Truyền tháng vào tham số
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String employeeName = rs.getString("employee_name");
                BigDecimal oldSalary = rs.getBigDecimal("old_salary");
                BigDecimal newSalary = rs.getBigDecimal("new_salary");
                LocalDate changeDate = rs.getDate("change_date").toLocalDate();
                String reasons = rs.getString("reasons");
                String approvedBy = rs.getString("e2.name"); // Lấy tên người duyệt
                String comments = rs.getString("comments");

                // Tạo đối tượng SalaryChangeHistory và gán giá trị
                SalaryChangeHistory history = new SalaryChangeHistory();
                history.setId(id);
                history.setEmployeeName(employeeName);
                history.setOldSalary(oldSalary);
                history.setNewSalary(newSalary);
                history.setChangeDate(changeDate);
                history.setReasons(reasons);
                history.setApprovedBy(approvedBy); // Người duyệt
                history.setComments(comments); // Phản hồi

                // Thêm vào danh sách kết quả
                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return historyList;
    }

    public ArrayList<SalaryChangeHistory> selectByMonth(int month) {
        String sql = "SELECT sch.id, e.name AS employee_name, sch.old_salary, sch.new_salary, sch.change_date, " +
                "sch.reasons, sch.status " +
                "FROM salary_change_history sch " +
                "JOIN employee e ON sch.employee_id = e.id " +
                "WHERE MONTH(sch.change_date) = ?"; // Chỉ lọc theo tháng

        Connection con = JDBCUtil.createConnection();
        ArrayList<SalaryChangeHistory> historyList = new ArrayList<>();

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                pst.setInt(1, month); // Chỉ truyền tháng vào tham số
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String employeeName = rs.getString("employee_name");
                    BigDecimal oldSalary = rs.getBigDecimal("old_salary");
                    BigDecimal newSalary = rs.getBigDecimal("new_salary");
                    LocalDate changeDate = rs.getDate("change_date").toLocalDate();
                    String reasons = rs.getString("reasons");
                    String status = rs.getString("status");

                    // Tạo đối tượng SalaryChangeHistory với thông tin cần thiết
                    SalaryChangeHistory history = new SalaryChangeHistory();
                    history.setId(id);
                    history.setEmployeeName(employeeName);
                    history.setOldSalary(oldSalary);
                    history.setNewSalary(newSalary);
                    history.setChangeDate(changeDate);
                    history.setReasons(reasons);
                    history.setStatus(status);

                    // Thêm vào danh sách kết quả
                    historyList.add(history);
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
            var33.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return historyList;
    }

}

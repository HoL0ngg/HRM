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
        String sql = "SELECT sch.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, approved.id as approved_by_id, approved.name as approved_by_name "
                + "FROM salary_change_history sch "
                + "JOIN employee e ON sch.employee_id = e.id "
                + "JOIN employee approved ON sch.approved_by = approved.id "
                + "WHERE sch.id = ?";
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
                    salaryChangeHistory = new SalaryChangeHistory(
                        rs.getInt("id"),
                        employee,
                        rs.getBigDecimal("old_salary"),
                        rs.getBigDecimal("new_salary"),
                        rs.getString("reasons"),
                        (LocalDate) rs.getObject("change_date_send", LocalDate.class), // change_date_send
                        (LocalDate) rs.getObject("change_date_browse", LocalDate.class), // change_date_browse
                        approvedBy,
                        rs.getString("comments"),
                        rs.getString("status")
                );                }
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
        String sql = "SELECT sch.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, approved.id as approved_by_id, approved.name as approved_by_name "
                + "FROM salary_change_history sch "
                + "JOIN employee e ON sch.employee_id = e.id "
                + "JOIN employee approved ON sch.approved_by = approved.id";
        Connection con = JDBCUtil.createConnection();
        ArrayList<SalaryChangeHistory> salaryChangeHistoryList = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                ResultSet rs = pst.executeQuery();

                while(rs.next()) {
                    int id = rs.getInt("id");
                    BigDecimal oldSalary = rs.getBigDecimal("old_salary");
                    BigDecimal newSalary = rs.getBigDecimal("new_salary");
                    String reasons = rs.getString("reasons");
                    LocalDate changeDateSend = (LocalDate)rs.getObject("change_date_send", LocalDate.class);
                    LocalDate changeDateBrowse= (LocalDate)rs.getObject("change_date_browse", LocalDate.class);
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
                    SalaryChangeHistory salaryChangeHistory = new SalaryChangeHistory(
                        id,
                        employee,
                        oldSalary,
                        newSalary,
                        reasons,
                        changeDateSend,
                        changeDateBrowse,
                        approvedBy,
                        comments,
                        status
                );                    salaryChangeHistoryList.add(salaryChangeHistory);
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
        String sql = "SELECT sch.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, approved.id as approved_by_id, approved.name as approved_by_name "
                + "FROM salary_change_history sch "
                + "JOIN employee e ON sch.employee_id = e.id "
                + "JOIN employee approved ON sch.approved_by = approved.id "
                + "WHERE sch.status IN ('pass', 'fail')";
        Connection con = JDBCUtil.createConnection();
        ArrayList<SalaryChangeHistory> salaryChangeHistoryList = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                ResultSet rs = pst.executeQuery();

                while(rs.next()) {
                    int id = rs.getInt("id");
                    BigDecimal oldSalary = rs.getBigDecimal("old_salary");
                    BigDecimal newSalary = rs.getBigDecimal("new_salary");
                    String reasons = rs.getString("reasons");
                    LocalDate changeDateSend = (LocalDate)rs.getObject("change_date_send", LocalDate.class);
                    LocalDate changeDateBrowse= (LocalDate)rs.getObject("change_date_browse", LocalDate.class);
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
                    SalaryChangeHistory salaryChangeHistory = new SalaryChangeHistory(id, employee, oldSalary, newSalary, reasons, changeDateSend,changeDateBrowse, approvedBy, comments, status);
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
    String sql = "UPDATE salary_change_history "
            + "SET comments = ?, approved_by = ?, status = ?, change_date_browse = ? "
            + "WHERE id = ?";
    Connection con = JDBCUtil.createConnection();
    boolean updated = false;

    try (PreparedStatement pst = con.prepareStatement(sql)) {
        // Gán các tham số vào câu lệnh SQL
//        pst.setBigDecimal(1, history.getNewSalary());  // new_salary
//        pst.setString(2, history.getReasons());        // reasons
        pst.setString(1, history.getComments());       // comments
        pst.setInt(2, history.getApprovedBy().getId());// approved_by
        pst.setString(3, history.getStatus());         // status
        pst.setDate(4, java.sql.Date.valueOf(history.getchangeDateBrowse())); // change_date
        pst.setInt(5, history.getId());                // id

        // Thực hiện cập nhật
        updated = pst.executeUpdate() > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        JDBCUtil.closeConnection(con);
    }

    return updated;
}


    public ArrayList<SalaryChangeHistory> selectByMonthDaXem1(int month) {
    String sql = "SELECT sch.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, approved.id as approved_by_id, approved.name as approved_by_name "
                + "FROM salary_change_history sch "
                + "JOIN employee e ON sch.employee_id = e.id "
                + "JOIN employee approved ON sch.approved_by = approved.id "
                + "WHERE sch.status IN ('pass', 'fail')"
               + "AND MONTH(sch.change_date_browse) = ?";  // Thêm điều kiện lọc theo tháng

    ArrayList<SalaryChangeHistory> salaryChangeHistoryList = new ArrayList<>();

    try (Connection con = JDBCUtil.createConnection(); 
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setInt(1, month);  // Truyền tháng vào tham số

        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                    BigDecimal oldSalary = rs.getBigDecimal("old_salary");
                    BigDecimal newSalary = rs.getBigDecimal("new_salary");
                    String reasons = rs.getString("reasons");
                    LocalDate changeDateSend = (LocalDate)rs.getObject("change_date_send", LocalDate.class);
                    LocalDate changeDateBrowse= (LocalDate)rs.getObject("change_date_browse", LocalDate.class);
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
                    SalaryChangeHistory salaryChangeHistory = new SalaryChangeHistory(id, employee, oldSalary, newSalary, reasons, changeDateSend,changeDateBrowse, approvedBy, comments, status);
                    salaryChangeHistoryList.add(salaryChangeHistory);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return salaryChangeHistoryList;
}
    public ArrayList<SalaryChangeHistory> selectByMonthDaXem(int month) {
    String sql = "SELECT sch.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, approved.id as approved_by_id, approved.name as approved_by_name "
                + "FROM salary_change_history sch "
                + "JOIN employee e ON sch.employee_id = e.id "
                + "JOIN employee approved ON sch.approved_by = approved.id "
                + "WHERE sch.status IN ('pass', 'fail')"
               + "AND MONTH(sch.change_date_send) = ?";  // Thêm điều kiện lọc theo tháng

    ArrayList<SalaryChangeHistory> salaryChangeHistoryList = new ArrayList<>();

    try (Connection con = JDBCUtil.createConnection(); 
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setInt(1, month);  // Truyền tháng vào tham số

        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                    BigDecimal oldSalary = rs.getBigDecimal("old_salary");
                    BigDecimal newSalary = rs.getBigDecimal("new_salary");
                    String reasons = rs.getString("reasons");
                    LocalDate changeDateSend = (LocalDate)rs.getObject("change_date_send", LocalDate.class);
                    LocalDate changeDateBrowse= (LocalDate)rs.getObject("change_date_browse", LocalDate.class);
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
                    SalaryChangeHistory salaryChangeHistory = new SalaryChangeHistory(id, employee, oldSalary, newSalary, reasons, changeDateSend,changeDateBrowse, approvedBy, comments, status);
                    salaryChangeHistoryList.add(salaryChangeHistory);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return salaryChangeHistoryList;
}
    public ArrayList<SalaryChangeHistory> selectByMonth(int month) {
    String sql = "SELECT sch.id, e.name AS employee_name, sch.old_salary, sch.new_salary, sch.change_date_send ,sch.change_date_browse , " +
                 "sch.reasons, sch.status " +
                 "FROM salary_change_history sch " +
                 "JOIN employee e ON sch.employee_id = e.id " +
                 "WHERE MONTH(sch.change_date) = ?";  // Chỉ lọc theo tháng

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
                LocalDate changeDateSend = rs.getDate("change_date_send").toLocalDate();
                LocalDate changeDateBrowse = rs.getDate("change_date_Browse").toLocalDate();
                String reasons = rs.getString("reasons");
                String status = rs.getString("status");

                // Tạo đối tượng SalaryChangeHistory với thông tin cần thiết
                SalaryChangeHistory history = new SalaryChangeHistory();
                history.setId(id);
                history.setEmployeeName(employeeName);
                history.setOldSalary(oldSalary);
                history.setNewSalary(newSalary);
                history.setchangeDateSend(changeDateSend);
                history.setchangeDateBrowse(changeDateBrowse);
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

public ArrayList<SalaryChangeHistory> selectByEmployeeId1(int employeeId) {
    String sql = "SELECT sch.*, e.name AS employee_name, sch.old_salary, sch.new_salary, sch.change_date_send, sch.change_date_browse, approved.id as approved_by_id, approved.name as approved_by_name, " +
                 "sch.reasons, sch.status " +
                 "FROM salary_change_history sch " +
                 "JOIN employee e ON sch.employee_id = e.id " +
                 "JOIN employee approved ON sch.approved_by = approved.id " +
                 "WHERE sch.employee_id = ? ";  // Lọc theo ID nhân viên

    Connection con = JDBCUtil.createConnection();
    ArrayList<SalaryChangeHistory> historyList = new ArrayList<>();
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        pst = con.prepareStatement(sql);
        pst.setInt(1, employeeId); // Gán ID nhân viên vào tham số
        rs = pst.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String employeeName = rs.getString("employee_name");
            BigDecimal oldSalary = rs.getBigDecimal("old_salary");
            BigDecimal newSalary = rs.getBigDecimal("new_salary");

            // Lấy và xử lý các trường DATE với việc kiểm tra null
            LocalDate changeDateSend = rs.getDate("change_date_send") != null ? rs.getDate("change_date_send").toLocalDate() : null;
            LocalDate changeDateBrowse = rs.getDate("change_date_browse") != null ? rs.getDate("change_date_browse").toLocalDate() : null;

            String reasons = rs.getString("reasons");
            String status = rs.getString("status");
            int approvedById = rs.getInt("approved_by_id");
                    String approvedByName = rs.getString("approved_by_name");
                    Employee approvedBy = new Employee();
                    approvedBy.setId(approvedById);
                    approvedBy.setName(approvedByName);
            // Tạo đối tượng SalaryChangeHistory và gán giá trị
            SalaryChangeHistory history = new SalaryChangeHistory();
            history.setId(id);
            history.setEmployeeName(employeeName);
            history.setOldSalary(oldSalary);
            history.setNewSalary(newSalary);
            history.setchangeDateSend(changeDateSend);
            history.setchangeDateBrowse(changeDateBrowse);
            history.setReasons(reasons);
            history.setStatus(status);

            // Thêm đối tượng vào danh sách kết quả
            historyList.add(history);
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi truy vấn dữ liệu SalaryChangeHistory: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();  // Đảm bảo đóng ResultSet
            }
            if (pst != null) {
                pst.close(); // Đảm bảo đóng PreparedStatement
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi đóng tài nguyên: " + e.getMessage());
        }
        JDBCUtil.closeConnection(con); // Đóng kết nối
    }

    return historyList;
}

public ArrayList<SalaryChangeHistory> selectByEmployeeId2(int employeeId) {
    String sql = "SELECT sch.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, approved.id as approved_by_id, approved.name as approved_by_name "
                + "FROM salary_change_history sch "
                + "JOIN employee e ON sch.employee_id = e.id "
                + "JOIN employee approved ON sch.approved_by = approved.id "
                + "WHERE sch.employee_id = ?";
    Connection con = JDBCUtil.createConnection();
    ArrayList<SalaryChangeHistory> salaryChangeHistoryList = new ArrayList<>();

    try (PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setInt(1, employeeId); // Đặt tham số employeeId vào câu truy vấn
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            BigDecimal oldSalary = rs.getBigDecimal("old_salary");
            BigDecimal newSalary = rs.getBigDecimal("new_salary");
            String reasons = rs.getString("reasons");
            LocalDate changeDateSend = rs.getObject("change_date_send", LocalDate.class);
            LocalDate changeDateBrowse = rs.getObject("change_date_browse", LocalDate.class);
            String comments = rs.getString("comments");
            String status = rs.getString("status");

            String employeeName = rs.getString("employee_name");
            Employee employee = new Employee();
            employee.setId(employeeId);
            employee.setName(employeeName);

            int approvedById = rs.getInt("approved_by_id");
            String approvedByName = rs.getString("approved_by_name");
            Employee approvedBy = new Employee();
            approvedBy.setId(approvedById);
            approvedBy.setName(approvedByName);

            SalaryChangeHistory salaryChangeHistory = new SalaryChangeHistory(id, employee, oldSalary, newSalary, reasons, changeDateSend, changeDateBrowse, approvedBy, comments, status);
            salaryChangeHistoryList.add(salaryChangeHistory);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        JDBCUtil.closeConnection(con);
    }

    return salaryChangeHistoryList;
}

}

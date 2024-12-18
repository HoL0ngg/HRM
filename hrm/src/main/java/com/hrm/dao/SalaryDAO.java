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
import com.hrm.model.SalaryChangeHistory;
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
            pst.setBigDecimal(5, salary.getnet_salary()); // Lương thực nhận
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
            pst.setBigDecimal(4, salary.getnet_salary());
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
        String sql = "SELECT s.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, " +
             "p.id as position_id, p.name as position_name, sch.reasons AS salary_change_reasons " +
             "FROM salaries s " +
             "JOIN employee e ON s.employee_id = e.id " +
             "JOIN position p ON e.position_id = p.id " +
             "LEFT JOIN salary_change_history sch ON sch.employee_id = e.id ";
             
        Connection con = JDBCUtil.createConnection();
        ArrayList<Salary> salaryList = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    BigDecimal positionSalary = rs.getBigDecimal("position_salary");
                    BigDecimal bonus = rs.getBigDecimal("bonus");
                    BigDecimal deductions = rs.getBigDecimal("deductions");
                    BigDecimal net_salary = rs.getBigDecimal("net_salary");
                    BigDecimal overtimeSalary = rs.getBigDecimal("overtime_salary");
                    LocalDate payday = (LocalDate) rs.getObject("payday", LocalDate.class);
                    String note = rs.getString("note");
                    int attendance = rs.getInt("attendance");
                    int employeeId = rs.getInt("employee_id");
                    String employeeName = rs.getString("employee_name");
                    String reasons = rs.getString("salary_change_reasons");
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
                    BigDecimal hourly_salary = rs.getBigDecimal("hourly_salary");
                    BigDecimal overtime_hourly_salary = rs.getBigDecimal("overtime_hourly_salary");
                    BigDecimal total_overtime_shifts = rs.getBigDecimal("total_overtime_shifts");
                    float total_hourly_work = rs.getFloat("total_hourly_work");
                    SalaryChangeHistory salaryChangeHistory = new SalaryChangeHistory();
                    salaryChangeHistory.setReasons(reasons);
                    Salary salary = new Salary(id, employee, salaryChangeHistory, positionSalary, bonus, deductions, net_salary,
                            overtimeSalary, payday, note, attendance, position,hourly_salary,overtime_hourly_salary,total_overtime_shifts,total_hourly_work);
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
                    BigDecimal net_salary = rs.getBigDecimal("net_salary");
                    BigDecimal overtimeSalary = rs.getBigDecimal("overtime_salary");
                    LocalDate payday = (LocalDate) rs.getObject("payday", LocalDate.class);
                    String note = rs.getString("note");
                    int attendance = rs.getInt("attendance");
                    BigDecimal hourly_salary = rs.getBigDecimal("hourly_salary");
                    BigDecimal overtime_hourly_salary = rs.getBigDecimal("overtime_hourly_salary");
                    BigDecimal total_overtime_shifts = rs.getBigDecimal("total_overtime_shifts");
                    float total_hourly_work = rs.getFloat("total_hourly_work");
                    salary = new Salary(id, employeeId, positionSalary, bonus, deductions, net_salary, overtimeSalary,
                            payday, note, attendance,hourly_salary,overtime_hourly_salary,total_overtime_shifts,total_hourly_work);
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
        String sql = "SELECT s.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, " +
             "p.id as position_id, p.name as position_name, sch.reasons AS salary_change_reasons " +
             "FROM salaries s " +
             "JOIN employee e ON s.employee_id = e.id " +
             "JOIN position p ON e.position_id = p.id " +
             "LEFT JOIN salary_change_history sch ON sch.employee_id = e.id "
             + "WHERE MONTH(s.payday) = ?";
        Connection con = JDBCUtil.createConnection();
        ArrayList<Salary> salaryList = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                pst.setInt(1, month);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    BigDecimal positionSalary = rs.getBigDecimal("position_salary");
                    BigDecimal bonus = rs.getBigDecimal("bonus");
                    BigDecimal deductions = rs.getBigDecimal("deductions");
                    BigDecimal net_salary = rs.getBigDecimal("net_salary");
                    BigDecimal overtimeSalary = rs.getBigDecimal("overtime_salary");
                    LocalDate payday = (LocalDate) rs.getObject("payday", LocalDate.class);
                    String note = rs.getString("note");
                    int attendance = rs.getInt("attendance");
                    int employeeId = rs.getInt("employee_id");
                    String employeeName = rs.getString("employee_name");
                    String reasons = rs.getString("salary_change_reasons");
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
                    BigDecimal hourly_salary = rs.getBigDecimal("hourly_salary");
                    BigDecimal overtime_hourly_salary = rs.getBigDecimal("overtime_hourly_salary");
                    BigDecimal total_overtime_shifts = rs.getBigDecimal("total_overtime_shifts");
                    float total_hourly_work = rs.getFloat("total_hourly_work");
                    Position position = new Position(positionId, 0, positionName);
                    SalaryChangeHistory salaryChangeHistory = new SalaryChangeHistory();
                    salaryChangeHistory.setReasons(reasons);
                    Salary salary = new Salary(id, employee,salaryChangeHistory ,positionSalary, bonus, deductions, net_salary,
                            overtimeSalary, payday, note, attendance, position,hourly_salary,overtime_hourly_salary,total_overtime_shifts,total_hourly_work);
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
public boolean updateSalary(int employeeID, BigDecimal bonus, BigDecimal attendance, BigDecimal deductions, String note, LocalDate payday, BigDecimal net_salary,BigDecimal hourly_salary , BigDecimal overtime_hourly_salary) {
    // Câu lệnh SQL để cập nhật thông tin lương
    String sql = "UPDATE salaries SET bonus = ?, attendance = ?, deductions = ?, note = ?, payday = ?, net_salary = ? , hourly_salary = ? , overtime_hourly_salary = ?  "
            + "WHERE employee_id = ?";
    Connection con = JDBCUtil.createConnection(); // Kết nối đến cơ sở dữ liệu
    boolean updated = false;

    try {
        // Chuẩn bị câu truy vấn
        PreparedStatement pst = con.prepareStatement(sql);
        try {
            // Thiết lập các tham số cho câu truy vấn
            pst.setBigDecimal(1, bonus); // Thưởng
            pst.setBigDecimal(2, attendance); // Chuyên cần
            pst.setBigDecimal(3, deductions); // Khấu trừ
            pst.setString(4, note); // Ghi chú
            pst.setDate(5, java.sql.Date.valueOf(payday)); // Ngày hiệu lực (payday)
            pst.setBigDecimal(6, net_salary); // Tổng lương sau khi tính toán
            pst.setBigDecimal(7,hourly_salary);
            pst.setBigDecimal(8,overtime_hourly_salary);
           pst.setInt(9, employeeID); // ID của nhân viên
            // Thực thi câu truy vấn và kiểm tra xem có bản ghi nào bị ảnh hưởng không
            updated = pst.executeUpdate() > 0;
        } catch (Throwable ex) {
            // Đảm bảo rằng PreparedStatement được đóng đúng cách nếu có lỗi xảy ra
            if (pst != null) {
                try {
                    pst.close();
                } catch (Throwable suppressed) {
                    ex.addSuppressed(suppressed);
                }
            }
            throw ex;
        }

        // Đảm bảo đóng PreparedStatement nếu không có lỗi
        if (pst != null) {
            pst.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đóng kết nối sau khi hoàn thành
        JDBCUtil.closeConnection(con);
    }

    // Trả về true nếu cập nhật thành công, ngược lại trả về false
    return updated;
}



public ArrayList<Salary> selectByEmployeeIdforDanhSachLuong(int employeeId) {
    // Câu truy vấn SQL
    String sql = "SELECT s.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, " +
             "p.id as position_id, p.name as position_name, sch.reasons AS salary_change_reasons ,sch.comments AS Comment " +
             "FROM salaries s " +
             "JOIN employee e ON s.employee_id = e.id " +
             "JOIN position p ON e.position_id = p.id " +
             "LEFT JOIN salary_change_history sch ON sch.employee_id = e.id " +
             
             "WHERE s.employee_id = ?";


    // Khởi tạo kết nối và danh sách kết quả
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ArrayList<Salary> salaryList = new ArrayList<>();

    try {
        // Tạo kết nối
        con = JDBCUtil.createConnection();

        // Chuẩn bị câu truy vấn
        pst = con.prepareStatement(sql);
        pst.setInt(1, employeeId); // Thiết lập employeeId vào câu truy vấn

        // Thực thi truy vấn
        rs = pst.executeQuery();

        // Lặp qua kết quả
        while (rs.next()) {
            // Lấy thông tin từ ResultSet
            int id = rs.getInt("id");
            BigDecimal positionSalary = rs.getBigDecimal("position_salary");
            BigDecimal bonus = rs.getBigDecimal("bonus");
            BigDecimal deductions = rs.getBigDecimal("deductions");
            BigDecimal net_salary = rs.getBigDecimal("net_salary");
            BigDecimal overtimeSalary = rs.getBigDecimal("overtime_salary");
            LocalDate payday = rs.getObject("payday", LocalDate.class);
            String note = rs.getString("note");
            int attendance = rs.getInt("attendance");
            int employeeIdResult = rs.getInt("employee_id");
            String employeeName = rs.getString("employee_name");
            String reasons = rs.getString("salary_change_reasons");
            if (reasons == null) {
                reasons = "Không có lý do thay đổi lương"; // Giá trị mặc định nếu reasons là NULL
            }
            String Comment = rs.getString("Comment");
            SalaryChangeHistory salaryChangeHistory = new SalaryChangeHistory();
            salaryChangeHistory.setReasons(reasons);
            salaryChangeHistory.setComments(Comment);
            String genderStr = rs.getString("gender").toLowerCase();
            Employee.Gender gender = Employee.Gender.valueOf(genderStr); // Chuyển đổi giới tính
            String phoneNumber = rs.getString("phone_number");
            
            BigDecimal hourly_salary = rs.getBigDecimal("hourly_salary");
            BigDecimal overtime_hourly_salary = rs.getBigDecimal("overtime_hourly_salary");
            BigDecimal total_overtime_shifts = rs.getBigDecimal("total_overtime_shifts");
            float total_hourly_work = rs.getFloat("total_hourly_work");
            // Tạo đối tượng Employee
            Employee employee = new Employee();
            employee.setId(employeeIdResult);
            employee.setName(employeeName);
            employee.setGender(gender);
            employee.setPhone_mumber(phoneNumber);

            // Tạo đối tượng Position
            int positionId = rs.getInt("position_id");
            String positionName = rs.getString("position_name");
            Position position = new Position(positionId, 0, positionName);
                System.out.println("Comment: " + Comment);

            
            

// Tạo đối tượng SalaryChangeHistory
            
            // Tạo đối tượng Salary và thêm vào danh sách
            Salary salary = new Salary(id, employee, salaryChangeHistory,positionSalary, bonus, deductions, net_salary, overtimeSalary, payday, note, attendance, position, hourly_salary,overtime_hourly_salary,total_overtime_shifts,total_hourly_work);
            salaryList.add(salary);
        }
    } catch (SQLException ex) {
        ex.printStackTrace(); // In ra thông báo lỗi SQL nếu có
    } finally {
        // Đóng kết nối và các đối tượng liên quan
        JDBCUtil.closeConnection(con);
        closeResources(pst, rs);
    }

    System.out.println("Query: " + pst.toString());
    System.out.println("Reasons: " + employeeId);
    return salaryList;
}

// Hàm đóng PreparedStatement và ResultSet
private void closeResources(PreparedStatement pst, ResultSet rs) {
    try {
        if (rs != null) {
            rs.close();
        }
        if (pst != null) {
            pst.close();
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
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
                salary = new Salary(id, employee, null, positionSalary, bonus, deductions, netSalary, overtimeSalary, payday, note, attendance, position, null, null, null, 0);            }

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
    public ArrayList<Salary> selectByEmployeeNameforDanhSachLuong(String employeeName) {
    // Câu truy vấn SQL
    String sql = """
        SELECT s.*, 
               e.id AS employee_id, 
               e.name AS employee_name, 
               e.gender, 
               e.phone_number, 
               p.id AS position_id, 
               p.name AS position_name, 
               sch.reasons AS salary_change_reasons, 
               sch.comments AS Comment 
        FROM salaries s
        JOIN employee e ON s.employee_id = e.id
        JOIN position p ON e.position_id = p.id
        LEFT JOIN salary_change_history sch ON sch.employee_id = e.id
        WHERE e.name LIKE ?
    """;

    ArrayList<Salary> salaryList = new ArrayList<>();
    try (Connection con = JDBCUtil.createConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        // Thiết lập tham số
        pst.setString(1, "%" + employeeName + "%"); // Tìm kiếm theo tên với LIKE

        // Thực thi câu truy vấn
        ResultSet rs = pst.executeQuery();

        // Lặp qua kết quả
        while (rs.next()) {
            // Lấy thông tin từ ResultSet
            int id = rs.getInt("id");
            BigDecimal positionSalary = rs.getBigDecimal("position_salary");
            BigDecimal bonus = rs.getBigDecimal("bonus");
            BigDecimal deductions = rs.getBigDecimal("deductions");
            BigDecimal net_salary = rs.getBigDecimal("net_salary");
            BigDecimal overtimeSalary = rs.getBigDecimal("overtime_salary");
            LocalDate payday = rs.getObject("payday", LocalDate.class);
            String note = rs.getString("note");
            int attendance = rs.getInt("attendance");
            int employeeId = rs.getInt("employee_id");
            String employeeNameResult = rs.getString("employee_name");
            String reasons = rs.getString("salary_change_reasons");
            if (reasons == null) {
                reasons = "Không có lý do thay đổi lương"; // Giá trị mặc định nếu reasons là NULL
            }
            String comment = rs.getString("Comment");
            SalaryChangeHistory salaryChangeHistory = new SalaryChangeHistory();
            salaryChangeHistory.setReasons(reasons);
            salaryChangeHistory.setComments(comment);
            String genderStr = rs.getString("gender").toLowerCase();
            Employee.Gender gender = Employee.Gender.valueOf(genderStr); // Chuyển đổi giới tính
            String phoneNumber = rs.getString("phone_number");

            BigDecimal hourly_salary = rs.getBigDecimal("hourly_salary");
            BigDecimal overtime_hourly_salary = rs.getBigDecimal("overtime_hourly_salary");
            BigDecimal total_overtime_shifts = rs.getBigDecimal("total_overtime_shifts");
            float total_hourly_work = rs.getFloat("total_hourly_work");

            // Tạo đối tượng Employee
            Employee employee = new Employee();
            employee.setId(employeeId);
            employee.setName(employeeNameResult);
            employee.setGender(gender);
            employee.setPhone_mumber(phoneNumber);

            // Tạo đối tượng Position
            int positionId = rs.getInt("position_id");
            String positionName = rs.getString("position_name");
            Position position = new Position(positionId, 0, positionName);

            // Tạo đối tượng Salary và thêm vào danh sách
            Salary salary = new Salary(id, employee, salaryChangeHistory, positionSalary, bonus, deductions, net_salary, overtimeSalary, payday, note, attendance, position, hourly_salary, overtime_hourly_salary, total_overtime_shifts, total_hourly_work);
            salaryList.add(salary);
        }
    } catch (SQLException ex) {
        ex.printStackTrace(); // In ra thông báo lỗi SQL nếu có
    }
    return salaryList;
}
}
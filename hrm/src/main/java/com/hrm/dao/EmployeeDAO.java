//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package com.hrm.dao;

import com.hrm.db.JDBCUtil;
import com.hrm.model.Department;
import com.hrm.model.Employee;
import com.hrm.model.Employee.Gender;
import com.hrm.model.Employee.Status;
import com.hrm.model.Employee.Work_type;
import com.hrm.model.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeDAO implements DAOInterface<Employee> {

    public EmployeeDAO() {
    }

    public static EmployeeDAO getInstance() {
        return new EmployeeDAO();
    }

    @Override
    public int them(Employee employee) {
        Connection con = JDBCUtil.createConnection();
        int result = 0;

        try {
            // Kiểm tra trạng thái quản lý của phòng ban
            String selectManagerSql = "SELECT manager_id FROM departments WHERE id = ?";
            PreparedStatement selectManagerPst = con.prepareStatement(selectManagerSql);
            selectManagerPst.setInt(1, employee.getDepartment_id());
            ResultSet rs = selectManagerPst.executeQuery();

            Integer currentManagerId = null;
            if (rs.next()) {
                currentManagerId = rs.getObject("manager_id", Integer.class);
            }
            rs.close();
            selectManagerPst.close();

            // Nếu nhân viên được thêm với vai trò quản lý
            if ("Quản lý".equals(employee.getLevel())) {
                if (currentManagerId != null) {
                    // Nếu đã có quản lý, hạ cấp quản lý hiện tại xuống "Nhân viên"
                    updateEmployeeLevel(con, currentManagerId, "Nhân viên");
                }
                // Cập nhật phòng ban với quản lý mới
                updateDepartmentManager(con, employee.getDepartment_id(), employee.getId());
            }

            // Thêm mới nhân viên
            String sql = "INSERT INTO employee (id, name, position_id, departments_id, previous_position, date_of_birth, gender, phone_number, address, email, hire_date, status, account_bank, identify_card, tax_code, social_insurance_code, work_type, level) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, employee.getId());
            pst.setString(2, employee.getName());
            pst.setInt(3, employee.getPosition().getId());
            pst.setInt(4, employee.getDepartment_id());
            pst.setString(5, employee.getPrevious_position());
            pst.setObject(6, employee.getDob());
            pst.setString(7, employee.getGender().name().toLowerCase());
            pst.setString(8, employee.getPhone_mumber());
            pst.setString(9, employee.getAddress());
            pst.setString(10, employee.getEmail());
            pst.setObject(11, employee.getHire_date());
            pst.setString(12, employee.getStatus().name().toLowerCase());
            pst.setInt(13, employee.getAccount_bank());
            pst.setInt(14, employee.getIndentify_card());
            pst.setInt(15, employee.getTax_code());
            pst.setInt(16, employee.getSocial_insurance_code());
            pst.setString(17, employee.getWork_type().name().toLowerCase());
            pst.setString(18, employee.getLevel());

            result = pst.executeUpdate();
            pst.close();

            if (result > 0) {
                System.out.println("Thêm mới nhân viên thành công: " + employee.getName());
            } else {
                System.out.println("Không thêm được nhân viên. Hãy kiểm tra lại dữ liệu.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return result;
    }

    // Hàm cập nhật cấp bậc nhân viên
    private void updateEmployeeLevel(Connection con, int employeeId, String newLevel) throws SQLException {
        String sql = "UPDATE employee SET level = ? WHERE id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, newLevel);
        pst.setInt(2, employeeId);
        pst.executeUpdate();
        pst.close();
    }

    // Hàm cập nhật quản lý phòng ban
    private void updateDepartmentManager(Connection con, int departmentId, Integer newManagerId) throws SQLException {
        String sql = "UPDATE departments SET manager_id = ? WHERE id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        if (newManagerId != null) {
            pst.setInt(1, newManagerId);
        } else {
            pst.setNull(1, java.sql.Types.INTEGER);
        }
        pst.setInt(2, departmentId);
        pst.executeUpdate();
        pst.close();
    }


    @Override
    public boolean xoa(Employee employee) {
        String sql = "UPDATE employee SET isDeleted = 1 WHERE id = ?";
        Connection con = JDBCUtil.createConnection();
        boolean isDeleted = false;

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, employee.getId()); // Gán giá trị ID của nhân viên cần xóa
            int rowsAffected = pst.executeUpdate();
            isDeleted = rowsAffected > 0; // Kiểm tra xem có hàng nào bị ảnh hưởng không

            if (isDeleted) {
                System.out.println("Xóa thành công nhân viên với ID: " + employee.getId());
            } else {
                System.out.println("Không tìm thấy nhân viên với ID: " + employee.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return isDeleted;
    }

    @Override
    public boolean capnhat(Employee employee) {
        Connection con = JDBCUtil.createConnection();
        boolean isUpdated = false;

        try {
            // Lấy ID phòng ban của nhân viên
            int departmentId = employee.getDepartment().getId();

            // Kiểm tra quản lý hiện tại của phòng ban
            String selectManagerSql = "SELECT manager_id FROM departments WHERE id = ?";
            PreparedStatement selectManagerPst = con.prepareStatement(selectManagerSql);
            selectManagerPst.setInt(1, departmentId);
            ResultSet rs = selectManagerPst.executeQuery();

            int currentManagerId = -1;
            if (rs.next()) {
                currentManagerId = rs.getInt("manager_id");
            }
            rs.close();
            selectManagerPst.close();

            // Nếu nhân viên được cập nhật lên quản lý
            if ("Quản lý".equals(employee.getLevel())) {
                if (currentManagerId == -1) {
                    // Phòng chưa có quản lý, cập nhật trực tiếp
                    updateEmployeeLevel(con, employee.getId(), "Quản lý");
                    updateDepartmentManager(con, departmentId, employee.getId());
                } else if (currentManagerId != employee.getId()) {
                    // Phòng có quản lý khác, hạ cấp quản lý hiện tại xuống nhân viên
                    updateEmployeeLevel(con, currentManagerId, "Nhân viên");

                    // Cập nhật nhân viên này thành quản lý
                    updateEmployeeLevel(con, employee.getId(), "Quản lý");
                    updateDepartmentManager(con, departmentId, employee.getId());
                }
            } else if ("Nhân viên".equals(employee.getLevel()) && currentManagerId == employee.getId()) {
                // Nếu nhân viên này là quản lý hiện tại nhưng bị hạ xuống nhân viên
                updateDepartmentManager(con, departmentId, null);
            }

            // Cập nhật thông tin khác của nhân viên
            String sql = "UPDATE employee SET name = ?, date_of_birth = ?, gender = ?, phone_number = ?, address = ?, email = ?, " +
                    "hire_date = ?, status = ?, account_bank = ?, identify_card = ?, tax_code = ?, social_insurance_code = ?, " +
                    "work_type = ?, position_id = ?, departments_id = ?, level = ? WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, employee.getName());
            pst.setObject(2, employee.getDob());
            pst.setString(3, employee.getGender().name().toLowerCase());
            pst.setString(4, employee.getPhone_mumber());
            pst.setString(5, employee.getAddress());
            pst.setString(6, employee.getEmail());
            pst.setObject(7, employee.getHire_date());
            pst.setString(8, employee.getStatus().name().toLowerCase());
            pst.setInt(9, employee.getAccount_bank());
            pst.setInt(10, employee.getIndentify_card());
            pst.setInt(11, employee.getTax_code());
            pst.setInt(12, employee.getSocial_insurance_code());
            pst.setString(13, employee.getWork_type().name().toLowerCase());
            pst.setInt(14, employee.getPosition().getId());
            pst.setInt(15, departmentId);
            pst.setString(16, employee.getLevel());
            pst.setInt(17, employee.getId());

            int rowsAffected = pst.executeUpdate();
            isUpdated = rowsAffected > 0;

            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return isUpdated;
    }

    @Override
    public ArrayList<Employee> selectAll() {
        // Câu truy vấn SQL với join 2 bảng position và departments
        String sql = "SELECT e.*, p.id as position_id, p.name as position_name, "
           + "d.id as department_id, d.name as department_name, d.manager_id "
           + "FROM employee e "
           + "JOIN position p ON e.position_id = p.id "
           + "JOIN departments d ON e.departments_id = d.id "
           + "WHERE e.isDeleted = 0";
        
        Connection con = JDBCUtil.createConnection();
        ArrayList<Employee> employeeList = new ArrayList<>();

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                // Lấy thông tin Employee
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDate dob = rs.getObject("date_of_birth", LocalDate.class);
                Gender gender = Gender.valueOf(rs.getString("gender").toLowerCase());
                String phone = rs.getString("phone_number");
                String address = rs.getString("address");
                String email = rs.getString("email");
                LocalDate hire_date = rs.getObject("hire_date", LocalDate.class);
                Status status = Status.valueOf(rs.getString("status").toLowerCase());
                int account_bank = rs.getInt("account_bank");
                int indentify_card = rs.getInt("identify_card");
                int tax_code = rs.getInt("tax_code");
                int social_insurance_code = rs.getInt("social_insurance_code");
                String prevPosition = rs.getString("previous_position");

                // Xử lý work_type
                Work_type work_type = null;
                String workTypeStr = rs.getString("work_type").toLowerCase().replace("-", "_");
                try {
                    work_type = Work_type.valueOf(workTypeStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid work type: " + workTypeStr);
                }

                // Lấy thông tin Position
                int position_id = rs.getInt("position_id");
                String position_name = rs.getString("position_name");
                Position position = new Position(position_id, 0, position_name);

                // Lấy thông tin Department
                int department_id = rs.getInt("department_id");
                String department_name = rs.getString("department_name");
                int manager_id = rs.getInt("manager_id");
                Department department = new Department(department_id, manager_id, department_name);

                // Tạo đối tượng Employee với đối tượng Position và Department
                Employee employee = new Employee(id, name, dob, position, department, prevPosition, gender, phone, address,
                        email, hire_date, status, account_bank, indentify_card, tax_code, social_insurance_code, work_type);

                // Thêm Employee vào danh sách
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return employeeList;
    }

    @Override
    public Employee selectByID(int id) {
        // Câu truy vấn SQL join cả 2 bảng position và departments, sửa lại tên cột và thêm cột image
        String sql = "SELECT e.*, p.id as position_id, p.name as position_name, "
                + "d.id as department_id, d.name as department_name, d.manager_id, "
                + "e.image " // Thêm cột image
                + "FROM employee e "
                + "JOIN position p ON e.position_id = p.id "
                + "JOIN departments d ON e.departments_id = d.id "
                + "WHERE e.id = ?";

        Connection con = JDBCUtil.createConnection();
        Employee employee = null;

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Lấy thông tin Employee
                String name = rs.getString("name");
                LocalDate dob = rs.getObject("date_of_birth", LocalDate.class);
                Gender gender = Gender.valueOf(rs.getString("gender").toLowerCase());
                String phone = rs.getString("phone_number");
                String address = rs.getString("address");
                String email = rs.getString("email");
                LocalDate hire_date = rs.getObject("hire_date", LocalDate.class);
                Status status = Status.valueOf(rs.getString("status").toLowerCase());
                int account_bank = rs.getInt("account_bank");
                int indentify_card = rs.getInt("identify_card");
                int tax_code = rs.getInt("tax_code");
                int social_insurance_code = rs.getInt("social_insurance_code");
                String prevPosition = rs.getString("previous_position");

                // Lấy thông tin image
                String image = rs.getString("image");

                // Xử lý work_type
                Work_type work_type = null;
                String workTypeStr = rs.getString("work_type");
                try {
                    work_type = Work_type.valueOf(workTypeStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid work type: " + workTypeStr);
                }

                // Lấy thông tin Position
                int position_id = rs.getInt("position_id");
                String position_name = rs.getString("position_name");
                Position position = new Position(position_id, 0, position_name); // department_id trong Position không dùng, có thể để 0

                // Lấy thông tin Department
                int department_id = rs.getInt("department_id");
                String department_name = rs.getString("department_name");
                int manager_id = rs.getInt("manager_id");
                Department department = new Department(department_id, manager_id, department_name);

                // Tạo đối tượng Employee với đối tượng Position, Department, và image
                employee = new Employee(id, name, dob, position, department, prevPosition, gender, phone, address,
                        email, hire_date, status, account_bank, indentify_card, tax_code, social_insurance_code, work_type);

                // Gán giá trị image vào Employee
                employee.setImage(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return employee;
    }

    
    public ArrayList<Employee> searchByIdOrName(Object searchValue) {
        // Câu truy vấn SQL với điều kiện tìm kiếm theo ID hoặc Tên
        String sql = "SELECT e.*, p.id as position_id, p.name as position_name, "
               + "d.id as department_id, d.name as department_name, d.manager_id "
               + "FROM employee e "
               + "JOIN position p ON e.position_id = p.id "
               + "JOIN departments d ON e.departments_id = d.id "
               + "WHERE (e.id = ? OR e.name LIKE ?) AND e.isDeleted = 0"; // Thêm điều kiện isDeleted = 0

        Connection con = JDBCUtil.createConnection();
        ArrayList<Employee> employeeList = new ArrayList<>();

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            // Kiểm tra giá trị tìm kiếm là ID (int) hay tên (String)
            if (searchValue instanceof Integer) {
                // Nếu là số nguyên, gán giá trị tìm kiếm vào ID
                pst.setInt(1, (Integer) searchValue);
                pst.setString(2, ""); // Để trống giá trị tên vì không dùng trong trường hợp này
            } else if (searchValue instanceof String) {
                // Nếu là chuỗi, tìm kiếm theo tên
                pst.setInt(1, -1); // Giá trị không hợp lệ để không ảnh hưởng đến tìm kiếm ID
                pst.setString(2, "%" + searchValue + "%");
            } else {
                throw new IllegalArgumentException("searchValue phải là Integer (ID) hoặc String (Tên)");
            }

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                // Lấy thông tin Employee
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDate dob = rs.getObject("date_of_birth", LocalDate.class);
                Gender gender = Gender.valueOf(rs.getString("gender").toLowerCase());
                String phone = rs.getString("phone_number");
                String address = rs.getString("address");
                String email = rs.getString("email");
                LocalDate hire_date = rs.getObject("hire_date", LocalDate.class);
                Status status = Status.valueOf(rs.getString("status").toLowerCase());
                int account_bank = rs.getInt("account_bank");
                int indentify_card = rs.getInt("identify_card");
                int tax_code = rs.getInt("tax_code");
                int social_insurance_code = rs.getInt("social_insurance_code");
                String prevPosition = rs.getString("previous_position");

                // Xử lý work_type
                Work_type work_type = null;
                String workTypeStr = rs.getString("work_type").toLowerCase().replace("-", "_");
                try {
                    work_type = Work_type.valueOf(workTypeStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid work type: " + workTypeStr);
                }

                // Lấy thông tin Position
                int position_id = rs.getInt("position_id");
                String position_name = rs.getString("position_name");
                Position position = new Position(position_id, 0, position_name);

                // Lấy thông tin Department
                int department_id = rs.getInt("department_id");
                String department_name = rs.getString("department_name");
                int manager_id = rs.getInt("manager_id");
                Department department = new Department(department_id, manager_id, department_name);

                // Tạo đối tượng Employee với đối tượng Position và Department
                Employee employee = new Employee(id, name, dob, position, department, prevPosition, gender, phone, address,
                        email, hire_date, status, account_bank, indentify_card, tax_code, social_insurance_code, work_type);

                // Thêm Employee vào danh sách
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return employeeList;
    }
    
    public boolean updateLevel(int employeeId, String level) {
        String sql = "UPDATE employee SET level = ? WHERE id = ?";
        try (Connection con = JDBCUtil.createConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, level);
            pst.setInt(2, employeeId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean doesIdExist(int id) {
        String sql = "SELECT 1 FROM employee WHERE id = ?";
        try (Connection con = JDBCUtil.createConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            return rs.next(); // Nếu có kết quả trả về thì ID đã tồn tại
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public int generateRandomId() {
        int id;
        do {
            id = (int) (Math.random() * 1000) + 1; // Sinh số ngẫu nhiên trong khoảng 1-1000
        } while (doesIdExist(id)); // Kiểm tra nếu ID đã tồn tại thì sinh lại
        return id;
    }
}

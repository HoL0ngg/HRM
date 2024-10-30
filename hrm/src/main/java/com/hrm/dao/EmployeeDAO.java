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
    public int them(Employee object) {
        throw new UnsupportedOperationException("Unimplemented method 'them'");
    }

    @Override
    public boolean xoa(Employee object) {
        throw new UnsupportedOperationException("Unimplemented method 'xoa'");
    }

    @Override
    public boolean capnhat(Employee object) {
        throw new UnsupportedOperationException("Unimplemented method 'capnhat'");
    }

    @Override
    public ArrayList<Employee> selectAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'seclectAll'");
    }

    @Override
    public Employee selectByID(int id) {
        // Câu truy vấn SQL join cả 2 bảng position và departments, sửa lại tên cột
        String sql = "SELECT e.*, p.id as position_id, p.name as position_name, "
                + "d.id as department_id, d.name as department_name, d.manager_id "
                + "FROM employee e "
                + "JOIN position p ON e.position_id = p.id "
                + "JOIN departments d ON e.departments_id = d.id " // Đổi e.department_id thành e.departments_id
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
                Position position = new Position(position_id, 0, position_name); // department_id trong Position không
                                                                                 // dùng, có thể để 0

                // Lấy thông tin Department
                int department_id = rs.getInt("department_id");
                String department_name = rs.getString("department_name");
                int manager_id = rs.getInt("manager_id");
                Department department = new Department(department_id, manager_id, department_name);

                // Tạo đối tượng Employee với đối tượng Position và Department
                employee = new Employee(id, name, dob, position, department, prevPosition, gender, phone, address,
                        email, hire_date, status, account_bank, indentify_card, tax_code, social_insurance_code,
                        work_type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBCUtil.closeConnection(con);
        return employee;
    }
}

package com.hrm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.hrm.db.JDBCUtil;
import com.hrm.model.Employee;
import com.hrm.model.Employee.Gender;
import com.hrm.model.Employee.Status;
import com.hrm.model.Employee.Work_type;;

public class EmployeeDAO implements DAOInterface<Employee> {

    public static EmployeeDAO getInstance() {
        return new EmployeeDAO();
    }

    @Override
    public int them(Employee object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'them'");
    }

    @Override
    public boolean xoa(Employee object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'xoa'");
    }

    @Override
    public boolean capnhat(Employee object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'capnhat'");
    }

    @Override
    public ArrayList<Employee> selectAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'seclectAll'");
    }

    @Override
    public Employee selectByID(int id) {
        String sql = "select * from employee where id = ?";
        Connection con = JDBCUtil.createConnection();
        Employee employee = null;
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int position_id = rs.getInt("position_id");
                int department_id = rs.getInt("departments_id");
                String prevPosition = rs.getString("previous_position");
                LocalDate dob = rs.getObject("date_of_birth", LocalDate.class);
                Gender gender = Gender.valueOf(rs.getString("gender"));
                String phone = rs.getString("phone_number");
                String address = rs.getString("address");
                String email = rs.getString("email");
                LocalDate hire_date = rs.getObject("hire_date", LocalDate.class);
                Status status = Status.valueOf(rs.getString("status"));
                int account_bank = rs.getInt("account_bank");
                int indentify_card = rs.getInt("identify_card");
                int tax_code = rs.getInt("tax_code");
                int social_insurance_code = rs.getInt("social_insurance_code");
                Work_type work_type = Work_type.valueOf(rs.getString("work_type"));
                employee = new Employee(id, name, dob, position_id, department_id, prevPosition, gender, phone, address,
                        email,
                        hire_date, status, account_bank, indentify_card, tax_code, social_insurance_code,
                        work_type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.closeConnection(con);
        return employee;
    }

}

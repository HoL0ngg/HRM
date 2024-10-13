package com.hrm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hrm.db.JDBCUtil;
import com.hrm.model.Employee;
import com.hrm.model.Employee.Gender;
import com.hrm.model.Employee.Status;;

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
    public ArrayList<Employee> seclectAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'seclectAll'");
    }

    @Override
    public Employee seclectByID(String id) {
        String sql = "select * from employee where employee_id = ?";
        Connection con = JDBCUtil.createConnection();
        Employee employee = null;
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int positionID = rs.getInt("position_id");
                int department_id = rs.getInt("department_id");
                Date dob = Date.valueOf(rs.getString("date_of_birth"));
                Gender gender = Gender.valueOf(rs.getString("gender"));
                String phone = rs.getString("phone_number");
                String address = rs.getString("address");
                String email = rs.getString("email");
                Date hire_date = Date.valueOf(rs.getString("hire_date"));
                Status status = Status.valueOf(rs.getString("status"));
                int account_bank = rs.getInt("account_bank");
                int indentity_card = rs.getInt("identity_card");
                employee = new Employee(id, name, dob, positionID, department_id, gender, phone, address, email,
                        hire_date, status, account_bank, indentity_card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

}

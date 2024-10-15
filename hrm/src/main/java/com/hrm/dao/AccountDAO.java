package com.hrm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hrm.db.JDBCUtil;
import com.hrm.model.Employee;
import com.hrm.model.Account;

public class AccountDAO implements DAOInterface<Account> {

    public static AccountDAO getInstance() {
        return new AccountDAO();
    }

    @Override
    public int them(Account object) {
        return 0;
    }

    @Override
    public boolean xoa(Account object) {
        return true;
    }

    @Override
    public boolean capnhat(Account object) {
        return true;
    }

    @Override
    public Account seclectByID(int id) {
        String sql = "select * from account where emloyee_id = ?";
        Connection con = JDBCUtil.createConnection();
        Account user = null;
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
                user = new Account(rs.getInt("emloyee_id"), rs.getString("username"), rs.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.closeConnection(con);
        return user;
    }

    @Override
    public ArrayList<Account> seclectAll() {
        return null;
    }

    public Employee DangNhap(String user, String password) {
        String sql = "select * from account where username = ?";

        Connection con = JDBCUtil.createConnection();
        Employee employee = null;
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, user);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                if (password.equals(rs.getString("password"))) {
                    employee = EmployeeDAO.getInstance().seclectByID(rs.getInt("employee_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.closeConnection(con);
        return employee;
    }

}

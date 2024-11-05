//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hrm.dao;

import com.hrm.db.JDBCUtil;
import com.hrm.model.Account;
import com.hrm.model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAO implements DAOInterface<Account> {
    public AccountDAO() {
    }

    public static AccountDAO getInstance() {
        return new AccountDAO();
    }

    public int them(Account object) {
        return 0;
    }

    public boolean xoa(Account object) {
        return true;
    }

    public boolean capnhat(Account object) {
        return true;
    }

    @Override
    public Account selectByID(int id) {
        String sql = "select * from account where emloyee_id = ?";
        Connection con = JDBCUtil.createConnection();
        Account user = null;

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    user = new Account(rs.getInt("emloyee_id"), rs.getString("username"), rs.getString("password"));
                }
            } catch (Throwable var9) {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                }

                throw var9;
            }

            if (pst != null) {
                pst.close();
            }
        } catch (SQLException var10) {
            SQLException e = var10;
            e.printStackTrace();
        }

        JDBCUtil.closeConnection(con);
        return user;
    }

    @Override
    public ArrayList<Account> selectAll() {
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
                    employee = EmployeeDAO.getInstance().getNamebyId(rs.getInt("employee_id"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBCUtil.closeConnection(con);
        return employee;
    }

}

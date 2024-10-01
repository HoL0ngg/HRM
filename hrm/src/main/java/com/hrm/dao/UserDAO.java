package com.hrm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hrm.db.JDBCUtil;
import com.hrm.model.User;

public class UserDAO implements DAOInterface<User> {

    public static UserDAO getInstance() {
        return new UserDAO();
    }

    @Override
    public int them(User object) {
        return 0;
    }

    @Override
    public boolean xoa(User object) {
        return true;
    }

    @Override
    public boolean capnhat(User object) {
        return true;
    }

    @Override
    public User seclectByID(User object) {
        String sql = "select * from account where emloyee_id = ?";
        Connection con = JDBCUtil.createConnection();
        User user = null;
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, object.getUser_id());
            ResultSet rs = pst.executeQuery();
            if (rs.next())
                user = new User(rs.getString(1), rs.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public ArrayList<User> seclectAll() {
        return null;
    }

    public boolean DangNhap(String user, String password) {
        String sql = "select employee_password from account where employee_id = ?";

        Connection con = JDBCUtil.createConnection();

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, user);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

package com.hrm.dao;

import com.hrm.db.JDBCUtil;
import com.hrm.model.Achievement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AchievementDAO implements DAOInterface<Achievement> {

    // Singleton instance
    public static AchievementDAO getInstance() {
        return new AchievementDAO();
    }

    @Override
    public int them(Achievement object) {
        throw new UnsupportedOperationException("Unimplemented method 'them'");
    }

    @Override
    public boolean xoa(Achievement object) {
        throw new UnsupportedOperationException("Unimplemented method 'xoa'");
    }

    @Override
    public boolean capnhat(Achievement object) {
        throw new UnsupportedOperationException("Unimplemented method 'capnhat'");
    }

    @Override
    public ArrayList<Achievement> selectAll() {
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }

    @Override
    public Achievement selectByID(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'selectByID'");
    }

    // Hàm lấy danh sách thành tựu theo ID nhân viên
    public ArrayList<Achievement> getAchievementsByEmployeeId(int employeeId) {
        String sql = "SELECT * FROM achievements WHERE employee_id = ?";
        Connection con = JDBCUtil.createConnection();
        ArrayList<Achievement> achievements = new ArrayList<>();

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, employeeId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                LocalDate createdDate = rs.getObject("date_awarded", LocalDate.class);
                String description = rs.getString("description");

                Achievement achievement = new Achievement(id, employeeId, title, createdDate, description);
                achievements.add(achievement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return achievements;
    }
}

package com.hrm.dao;

import com.hrm.db.JDBCUtil;
import com.hrm.model.TrainingDevelopment;
import com.hrm.model.TrainingDevelopment.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TrainingDevelopmentDAO implements DAOInterface<TrainingDevelopment> {

    // Singleton Instance
    public static TrainingDevelopmentDAO getInstance() {
        return new TrainingDevelopmentDAO();
    }

    @Override
    public int them(TrainingDevelopment object) {
        throw new UnsupportedOperationException("Unimplemented method 'them'");
    }

    @Override
    public boolean xoa(TrainingDevelopment object) {
        throw new UnsupportedOperationException("Unimplemented method 'xoa'");
    }

    @Override
    public boolean capnhat(TrainingDevelopment object) {
        throw new UnsupportedOperationException("Unimplemented method 'capnhat'");
    }

    @Override
    public ArrayList<TrainingDevelopment> selectAll() {
        String sql = "SELECT * FROM trainings_development";
        Connection con = JDBCUtil.createConnection();
        ArrayList<TrainingDevelopment> trainingList = new ArrayList<>();

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                // Lấy dữ liệu từ ResultSet
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                LocalDate startDate = rs.getObject("start_date", LocalDate.class);
                LocalDate endDate = rs.getObject("end_date", LocalDate.class);
                Status status = Status.fromValue(rs.getString("status"));

                // Tạo đối tượng TrainingDevelopment
                TrainingDevelopment training = new TrainingDevelopment(id, name, description, startDate, endDate, status);

                // Thêm vào danh sách
                trainingList.add(training);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return trainingList;
    }

    @Override
    public TrainingDevelopment selectByID(int id) {
        String sql = "SELECT * FROM trainings_development WHERE id = ?";
        Connection con = JDBCUtil.createConnection();
        TrainingDevelopment training = null;

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Lấy dữ liệu từ ResultSet
                String name = rs.getString("name");
                String description = rs.getString("description");
                LocalDate startDate = rs.getObject("start_date", LocalDate.class);
                LocalDate endDate = rs.getObject("end_date", LocalDate.class);
                Status status = Status.fromValue(rs.getString("status"));

                // Tạo đối tượng TrainingDevelopment
                training = new TrainingDevelopment(id, name, description, startDate, endDate, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return training;
    }
}

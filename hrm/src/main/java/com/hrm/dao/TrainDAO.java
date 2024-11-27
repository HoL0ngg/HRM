package com.hrm.dao;

import com.hrm.db.JDBCUtil;
import com.hrm.model.Employee;
import com.hrm.model.Train;
import com.hrm.model.TrainingDevelopment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TrainDAO {

    public static TrainDAO getInstance() {
        return new TrainDAO();
    }

    /**
     * Lấy danh sách thông tin train theo ID nhân viên
     * @param employeeId ID nhân viên
     * @return Danh sách Train
     */
    public ArrayList<Train> getTrainsByEmployeeId(int employeeId) {
        String sql = "SELECT t.trainings_development_id, td.name AS training_name, td.description, " +
                     "td.start_date, td.end_date, td.status, " +
                     "e.id AS employee_id, e.name AS employee_name, e.email AS employee_email, e.image " + // Thêm cột image
                     "FROM train t " +
                     "JOIN trainings_development td ON t.trainings_development_id = td.id " +
                     "JOIN employee e ON t.employee_id = e.id " +
                     "WHERE e.id = ?";

        ArrayList<Train> trainList = new ArrayList<>();
        Connection con = JDBCUtil.createConnection();

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, employeeId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                // Tạo đối tượng TrainingDevelopment
                TrainingDevelopment training = new TrainingDevelopment();
                training.setId(rs.getInt("trainings_development_id"));
                training.setName(rs.getString("training_name"));
                training.setDescription(rs.getString("description"));
                training.setStartDate(rs.getObject("start_date", LocalDate.class));
                training.setEndDate(rs.getObject("end_date", LocalDate.class));
                training.setStatus(TrainingDevelopment.Status.fromValue(rs.getString("status")));

                // Tạo đối tượng Employee
                Employee employee = new Employee();
                employee.setId(rs.getInt("employee_id"));
                employee.setName(rs.getString("employee_name"));
                employee.setEmail(rs.getString("employee_email"));
                employee.setImage(rs.getString("image")); // Set thuộc tính image

                // Tạo đối tượng Train
                Train train = new Train(employee, training);
                trainList.add(train);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return trainList;
    }


    // Các phương thức CRUD cơ bản có thể triển khai tối giản
    public int them(Train train) {
        return 0;
    }

    public boolean xoa(Train train) {
        return false;
    }

    public boolean capnhat(Train train) {
        return false;
    }

    public ArrayList<Train> selectAll() {
        return new ArrayList<>();
    }

    public Train selectByID(int id) {
        return null;
    }
}

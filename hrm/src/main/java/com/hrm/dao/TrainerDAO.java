/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hrm.dao;

import com.hrm.model.Trainer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
public class TrainerDAO {
    private MySQLConnect mySQL = new MySQLConnect();

    public TrainerDAO() {

    }

    public ArrayList<Trainer> list() {
        ArrayList<Trainer> trainers = new ArrayList<>();

        String sql = "SELECT * FROM train WHERE 1";
        try (ResultSet rs = mySQL.executeQuery(sql)) {
            while (rs.next()) {
                int empId = rs.getInt("employee_id");
                int trainId = rs.getInt("trainings_development_id");
               
                Trainer t = new Trainer(empId, trainId);
                trainers.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
        return trainers;
    }

    public void set(Trainer t) {
        String sql = "UPDATE train SET "
                + "employee_id = ? "
                + "WHERE trainings_development_id = ?";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, t.getEmployeeId());
            pstmt.setInt(2, t.getTrainId());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }

    public void add(Trainer t) {
        String sql = "INSERT INTO train (employee_id, trainings_development_id) "
                + "VALUES (?, ?)";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, t.getEmployeeId());
            pstmt.setInt(2, t.getTrainId());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM train WHERE trainings_development_id = ?";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Xoa thanh cong");
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }
}

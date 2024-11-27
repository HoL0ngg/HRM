/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hrm.dao;

import com.hrm.model.Achievements;
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
public class AchievementsDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public AchievementsDAO() {

    }

    public ArrayList<Achievements> list() {
        ArrayList<Achievements> aList = new ArrayList<>();

        String sql = "SELECT * FROM achievements WHERE 1";
        try (ResultSet rs = mySQL.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int employeeId = rs.getInt("employee_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                java.sql.Date date = rs.getDate("date_awarded");
                LocalDate date_awarded = (date != null) ? date.toLocalDate() : null;

                Achievements a = new Achievements(id, employeeId, title, description, date_awarded);
                aList.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AchievementsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
        return aList;
    }

    public void set(Achievements a) {
        String sql = "UPDATE achievements SET "
                + "employee_id = ?, title = ?, description = ?, date_awarded = ?"
                + "WHERE id = ?";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, a.getEmployeeId());
            pstmt.setString(2, a.getTitle());
            pstmt.setString(3, a.getDescription());
            pstmt.setDate(4, a.getAwardDate() != null ? java.sql.Date.valueOf(a.getAwardDate()) : null);
            pstmt.setInt(6, a.getId());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AchievementsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }

    public void add(Achievements a) {
        String sql = "INSERT INTO achievements (id, employee_id, title, description, date_awarded) "
                + "VALUES (?, ?, ?, ?, ? )";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, a.getId());
            pstmt.setInt(2, a.getEmployeeId());
            pstmt.setString(3, a.getTitle());
            pstmt.setString(4, a.getDescription());
            pstmt.setDate(5, a.getAwardDate() != null ? java.sql.Date.valueOf(a.getAwardDate()) : null);

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AchievementsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM achievements WHERE id = ?";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Xoa thanh cong");
        } catch (SQLException ex) {
            Logger.getLogger(TrainingDevelopmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }
}

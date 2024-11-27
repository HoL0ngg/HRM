package com.hrm.dao;

import com.hrm.model.TrainingDevelopment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainingDevelopmentDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public TrainingDevelopmentDAO() {

    }

    public ArrayList<TrainingDevelopment> list() {
        ArrayList<TrainingDevelopment> TDlist = new ArrayList<>();

        String sql = "SELECT * FROM trainings_development WHERE 1";
        try (ResultSet rs = mySQL.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                java.sql.Date opendate = rs.getDate("start_date");
                LocalDate opening_date = (opendate != null) ? opendate.toLocalDate() : null;
                java.sql.Date closedate = rs.getDate("end_date");
                LocalDate closing_date = (closedate != null) ? closedate.toLocalDate() : null;
                String status = rs.getString("status");

                TrainingDevelopment td = new TrainingDevelopment(id, name, description, opening_date, closing_date, status);
                TDlist.add(td);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JobOpeningsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
        return TDlist;
    }

    public void set(TrainingDevelopment td) {
        String sql = "UPDATE trainings_development SET "
                + "name = ?, description = ?, start_date = ?, end_date = ?, status = ?"
                + "WHERE id = ?";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, td.getName());
            pstmt.setString(2, td.getDescription());
            pstmt.setDate(3, td.getStartDate() != null ? java.sql.Date.valueOf(td.getStartDate()) : null);
            pstmt.setDate(4, td.getEndDate() != null ? java.sql.Date.valueOf(td.getEndDate()) : null);
            pstmt.setString(5, td.getStatus());
            pstmt.setInt(6, td.getId());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrainingDevelopmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }

    public void add(TrainingDevelopment td) {
        String sql = "INSERT INTO trainings_development (id, name, description, start_date, end_date, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, td.getId());
            pstmt.setString(2, td.getName());
            pstmt.setString(3, td.getDescription());
            pstmt.setDate(4, td.getStartDate() != null ? java.sql.Date.valueOf(td.getStartDate()) : null);
            pstmt.setDate(5, td.getEndDate() != null ? java.sql.Date.valueOf(td.getEndDate()) : null);
            pstmt.setString(6, td.getStatus());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrainingDevelopmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM trainings_development WHERE id = ?";
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

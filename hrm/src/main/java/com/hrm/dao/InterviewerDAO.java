package com.hrm.dao;

import com.hrm.model.Applicants;
import com.hrm.model.Interviewer;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterviewerDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public InterviewerDAO() {

    }

    public ArrayList<Interviewer> list() {
        ArrayList<Interviewer> intverList = new ArrayList<>();
        String sql = "SELECT * FROM interviewer WHERE 1";
        try (ResultSet rs = mySQL.executeQuery(sql)) {
            while (rs.next()) {
                int employeeId = rs.getInt("employee_id");
                int intvId = rs.getInt("interview_id");

                Interviewer intver = new Interviewer(employeeId, intvId);
                intverList.add(intver);
            }
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            Logger.getLogger(InterviewerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return intverList;
    }

    public void set(Interviewer intver) {
        String sql = "UPDATE interviewer SET "
                + "employee_id = ?"
                + "WHERE interview_id = ?";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, intver.getEmployee_id());
            pstmt.setInt(2, intver.getInterview_id());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InterviewerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }

public void add(Interviewer intver) {
        String sql = "INSERT INTO interviewer (employee_id, interview_id) " +
                     "VALUES (?, ?)";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, intver.getEmployee_id());
            pstmt.setInt(2, intver.getInterview_id());
            
            pstmt.executeUpdate();
            System.out.println("Thêm thành công");
        } catch (SQLException ex) {
            Logger.getLogger(InterviewerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM interviewer WHERE interview_id = ?";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Xoa thanh cong");
        } catch (SQLException ex) {
            Logger.getLogger(JobOpeningsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }
}

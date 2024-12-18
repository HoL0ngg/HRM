package com.hrm.dao;

import com.hrm.model.Interviews;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterviewsDAO {

    private MySQLConnect mySQL;

    public InterviewsDAO() {
        mySQL = new MySQLConnect();
    }

    public ArrayList<Interviews> list() {
        ArrayList<Interviews> intvs = new ArrayList<>();

        String sql = "SELECT * FROM interviews WHERE 1";
        try (ResultSet rs = mySQL.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int jobId = rs.getInt("job_open_id");
                int aplId = rs.getInt("applicants_id");
                Date intvdate = rs.getDate("interview_date");
                LocalDate intvDate = (intvdate != null) ? intvdate.toLocalDate() : null;
                String intvStage = rs.getString("interview_stage");
                Time time = rs.getTime("interview_time");
                LocalTime intvTime = (time != null) ? time.toLocalTime() : null;
                String note = rs.getString("note");
                String result = rs.getString("result");

                Interviews intv = new Interviews(id, jobId, aplId, intvDate, intvTime, intvStage, note, result);
                intvs.add(intv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterviewsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
        return intvs;
    }

    public void set(Interviews intv) {
        String sql = "UPDATE interviews SET "
                + "job_open_id = ?, applicants_id = ?, interview_date = ?, interview_time = ?, interview_stage = ?, note = ?, result = ? "
                + "WHERE id = ?";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, intv.getJob_open_id());
            pstmt.setInt(2, intv.getApplicants_id());
            pstmt.setDate(3, intv.getInterview_date() != null ? java.sql.Date.valueOf(intv.getInterview_date()) : null);
            pstmt.setTime(4, intv.getInterview_time() != null ? java.sql.Time.valueOf(intv.getInterview_time()) : null);
            pstmt.setString(5, intv.getInterview_stage());
            pstmt.setString(6, intv.getNote());
            pstmt.setString(7, intv.getResult());
            pstmt.setInt(8, intv.getId());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InterviewsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }

    public void setStage(Interviews intv) {
        String sql = "UPDATE interviews SET "
                + "interview_stage = ?, note = ?, result = ? "
                + "WHERE id = ?";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, intv.getInterview_stage());
            pstmt.setString(2, intv.getNote());
            pstmt.setString(3, intv.getResult());
            pstmt.setInt(4, intv.getId());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InterviewsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }

    public void add(Interviews intv) {
        String sql = "INSERT INTO interviews (id, job_open_id, applicants_id, interview_date, interview_time, interview_stage, note, result) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, intv.getId());
            pstmt.setInt(2, intv.getJob_open_id());
            pstmt.setInt(3, intv.getApplicants_id());
            pstmt.setDate(4, intv.getInterview_date() != null ? java.sql.Date.valueOf(intv.getInterview_date()) : null);
            pstmt.setTime(5, intv.getInterview_time() != null ? java.sql.Time.valueOf(intv.getInterview_time()) : null);
            pstmt.setString(6, intv.getInterview_stage());
            pstmt.setString(7, intv.getNote());
            pstmt.setString(8, intv.getResult());

            pstmt.executeUpdate();
            System.out.println("Thêm thành công");
        } catch (SQLException ex) {
            Logger.getLogger(JobOpeningsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }

//    public void delete(int id) {
//        String sql = "DELETE FROM job_openings WHERE id = ?";
//        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
//            pstmt.setInt(1, id);
//            pstmt.executeUpdate();
//            System.out.println("Xoa thanh cong");
//        } catch (SQLException ex) {
//            Logger.getLogger(JobOpeningsDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            mySQL.disConnect();
//        }
//    }
}

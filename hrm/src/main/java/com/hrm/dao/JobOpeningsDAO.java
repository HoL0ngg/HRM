package com.hrm.dao;

import com.hrm.dao.MySQLConnect;
import com.hrm.model.JobOpenings;
import java.io.ObjectInputFilter.Status;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shadow
 */
public class JobOpeningsDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    
    public JobOpeningsDAO(){
    }
    
    public ArrayList<JobOpenings> list(){
        ArrayList<JobOpenings> dsJob = new ArrayList<>();
        
        String sql = "SELECT * FROM job_openings WHERE 1";
        try (ResultSet rs = mySQL.executeQuery(sql)) {
            while(rs.next())
            {
                int id = rs.getInt("id");
                int department_id = rs.getInt("department_id");
                String position = rs.getString("position");
                java.sql.Date opendate = rs.getDate("opening_date");
                LocalDate opening_date = (opendate != null) ? opendate.toLocalDate() : null;
                java.sql.Date closedate = rs.getDate("closing_date");
                LocalDate closing_date = (closedate != null) ? closedate.toLocalDate() : null;    
                String status = rs.getString("status");
             
                JobOpenings job = new JobOpenings(id,department_id,position,opening_date,closing_date,status);
                dsJob.add(job);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JobOpeningsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
        return dsJob;
    }
    
    public void set(JobOpenings job) {
        String sql = "UPDATE job_openings SET " +
                     "department_id = ?, position = ?, opening_date = ?, closing_date = ?, status = ? " +
                     "WHERE id = ?";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, job.getDepartment_id());
            pstmt.setString(2, job.getPosition());
            pstmt.setDate(3, job.getOpening_date() != null ? java.sql.Date.valueOf(job.getOpening_date()) : null);
            pstmt.setDate(4, job.getClosing_date() != null ? java.sql.Date.valueOf(job.getClosing_date()) : null);
            pstmt.setString(5, job.getStatus());
            pstmt.setInt(6, job.getId());
            
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JobOpeningsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }

    public void add(JobOpenings job) {
        String sql = "INSERT INTO job_openings (id, department_id, position, opening_date, closing_date, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, job.getId());
            pstmt.setInt(2, job.getDepartment_id());
            pstmt.setString(3, job.getPosition());
            pstmt.setDate(4, job.getOpening_date() != null ? java.sql.Date.valueOf(job.getOpening_date()) : null);
            pstmt.setDate(5, job.getClosing_date() != null ? java.sql.Date.valueOf(job.getClosing_date()) : null);
            pstmt.setString(6, job.getStatus());
            
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JobOpeningsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM job_openings WHERE id = ?";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JobOpeningsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }
//    public void set(JobOpenings job){
//        MySQLConnect mySQL = new MySQLConnect();
//        String sql = "UPDATE job_openings SET";
//        sql += "department_id='" + job.getDepartment_id() + "',";
//        sql += "position='" + job.getPosition() + "',";
//        sql += "opening_date='" + job.getOpening_date() + "',";
//        sql += "closing_date='" + job.getClosing_date() + "',";
//        sql += "status='" + job.getStatus() + "',";
//        sql += "WHERE id='" + job.getId() + "'";
//        System.out.println(sql);
//        mySQL.executeUpdate(sql);
//    }
//
//    public void add(JobOpenings job){
//        MySQLConnect mySQL = new MySQLConnect();
//        String sql = "INSERT INTO job_openings VALUES (";
//        sql += "'" + job.getId() + "',";
//        sql += "'" + job.getDepartment_id() + "',";
//        sql += "'" + job.getPosition() + "',";
//        sql += "'" + job.getOpening_date() + "',";
//        sql += "'" + job.getClosing_date() + "',";
//        sql += "'" + job.getStatus() + "'";
//        System.out.println(sql);
//        mySQL.executeUpdate(sql);
//    }
//    
//    public void delete(int id){
//        MySQLConnect mySQL = new MySQLConnect();
//        String sql = "DELETE FROM job_openings WHERE id='"+id+"'";
//        System.out.println(sql);
//        mySQL.executeUpdate(sql);
//    }
}
package com.hrm.dao;

import com.hrm.model.JobOpenings;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobOpeningsDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    
    public JobOpeningsDAO() {
    }
    
    public ArrayList<JobOpenings> list() {
        ArrayList<JobOpenings> dsJob = new ArrayList<>();
        
        String sql = "SELECT * FROM job_openings WHERE 1";
        try (ResultSet rs = mySQL.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int department_id = rs.getInt("department_id");
                String position = rs.getString("position");
                java.sql.Date opendate = rs.getDate("opening_date");
                LocalDate opening_date = (opendate != null) ? opendate.toLocalDate() : null;
                java.sql.Date closedate = rs.getDate("closing_date");
                LocalDate closing_date = (closedate != null) ? closedate.toLocalDate() : null;    
                String status = rs.getString("status");
                String salary = rs.getString("salary");
                String detail = rs.getString("detail");
             
                JobOpenings job = new JobOpenings(id, department_id, position, salary, opening_date, closing_date, status, detail) ;
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
                     "department_id = ?, position = ?, salary = ?, opening_date = ?, closing_date = ?, status = ?, detail = ? " +
                     "WHERE id = ?";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, job.getDepartment_id());
            pstmt.setString(2, job.getPosition());
            pstmt.setString(3, job.getSalary());
            pstmt.setDate(4, job.getOpening_date() != null ? java.sql.Date.valueOf(job.getOpening_date()) : null);
            pstmt.setDate(5, job.getClosing_date() != null ? java.sql.Date.valueOf(job.getClosing_date()) : null);
            pstmt.setString(6, job.getStatus());
            pstmt.setString(7, job.getDetail());
            pstmt.setInt(8, job.getId());
            
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JobOpeningsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }

    public void add(JobOpenings job) {
        String sql = "INSERT INTO job_openings (id, department_id, position, salary, opening_date, closing_date, status, detail) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = mySQL.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, job.getId());
            pstmt.setInt(2, job.getDepartment_id());
            pstmt.setString(3, job.getPosition());
            pstmt.setString(4, job.getSalary());
            pstmt.setDate(5, job.getOpening_date() != null ? java.sql.Date.valueOf(job.getOpening_date()) : null);
            pstmt.setDate(6, job.getClosing_date() != null ? java.sql.Date.valueOf(job.getClosing_date()) : null);
            pstmt.setString(7, job.getStatus());
            pstmt.setString(8, job.getDetail());
            
            pstmt.executeUpdate();
            System.out.println("Thêm thành công");
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
            System.out.println("Xoa thanh cong");
        } catch (SQLException ex) {
            Logger.getLogger(JobOpeningsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQL.disConnect();
        }
    }

    public static void main(String[] args) {
    JobOpeningsDAO jobDao = new JobOpeningsDAO();

    ArrayList<JobOpenings> list = jobDao.list();
    if (list.isEmpty()) {
        System.out.println("Không có dữ liệu hoặc kết nối thất bại.");
    } else {
        System.out.println("Danh sách Job Openings:");
        for (JobOpenings job : list) {
            System.out.println("ID: " + job.getId());
            System.out.println("Department ID: " + job.getDepartment_id());
            System.out.println("Position: " + job.getPosition());
            System.out.println("Opening Date: " + job.getOpening_date());
            System.out.println("Closing Date: " + job.getClosing_date());
            System.out.println("Status: " + job.getStatus());
            System.out.println("Salary: " + job.getSalary());
            System.out.println("Detail: " + job.getDetail());
            System.out.println("------------");
        }
    }

    jobDao.delete(39);
    
}

}

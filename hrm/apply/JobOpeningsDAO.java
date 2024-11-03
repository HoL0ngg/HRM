/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tuyenDung;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
        
        try {
           
            String sql = "SELECT * FROM job_openigs WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt("id");
                int department_id = rs.getInt("department_id");
                String position = rs.getString("TENKH");
                java.sql.Date opendate = rs.getDate("opening_date");
                LocalDate opening_date = (opendate != null) ? opendate.toLocalDate() : null;
                java.sql.Date closedate = rs.getDate("closing_date");
                LocalDate closing_date = (closedate != null) ? closedate.toLocalDate() : null;    
                String status = rs.getString("status");
             
                JobOpenings job = new JobOpenings(id,department_id,position,opening_date,closing_date,status);
                dsJob.add(job);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            //Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsJob;
    }
    
    public void set(JobOpenings job)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE job_openings SET";
        sql += "department_id='" + job.getDepartment_id() + "',";
        sql += "position='" + job.getDepartment_id() + "',";
        sql += "department_id='" + job.getDepartment_id() + "',";
        sql += "department_id='" + job.getDepartment_id() + "',";
        sql += "department_id='" + job.getDepartment_id() + "',";
        sql += "department_id='" + job.getDepartment_id() + "',";
    }

}
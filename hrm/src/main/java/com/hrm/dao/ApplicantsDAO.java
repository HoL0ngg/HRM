package com.hrm.dao;

import com.hrm.model.Applicants;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicantsDAO {
    private MySQLConnect mySQL = new MySQLConnect();

    public ApplicantsDAO() {
    }
    
    public ArrayList<Applicants> list()
    {
        ArrayList<Applicants> applicants = new ArrayList<>();
        try {
            String sql = "SELECT * FROM applicants WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt("id");
                String full_name = rs.getString("full_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String resume = rs.getString("resume");
                Date applicantdate = rs.getDate("applicant_date");
                LocalDate applicantDate = (applicantdate != null) ? applicantdate.toLocalDate() : null;

                Applicants applicant = new Applicants(id, full_name, email, phone, resume, applicantDate);
                applicants.add(applicant);
            }
            rs.close();
            mySQL.disConnect();
        } catch(SQLException ex) {
            Logger.getLogger(ApplicantsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return applicants;
    }
    
    public void set(Applicants applicant) 
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE applicants SET";
        sql += "full_name='" + applicant.getFull_name() + "', ";
        sql += "email='" + applicant.getEmail() + "', ";
        sql += "phone='" + applicant.getPhone() + "', ";
        sql += "resume='" + applicant.getResume() + "', ";
        sql += "applicant_date='" + applicant.getApplicant_date() + "', ";
        sql += "WHERE id='" + applicant.getId() + "'";
        System.out.println(sql);
        
        mySQL.executeUpdate(sql);
    }
    
    public void add(Applicants applicant)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO applicants VALUES (";
        sql += "'" + applicant.getId() + "', ";
        sql += "'" + applicant.getFull_name() + "', ";
        sql += "'" + applicant.getEmail() + "', ";
        sql += "'" + applicant.getPhone() + "', ";
        sql += "'" + applicant.getResume() + "', ";
        sql += "'" + applicant.getApplicant_date() + "')";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    
    public void delete(int id)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM applicants WHERE id='"+id+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    
    public static void main(String[] args){
        ApplicantsDAO applicantDao = new ApplicantsDAO();
        ArrayList<Applicants> applicantlist = applicantDao.list();
        for(Applicants a : applicantlist){
            System.out.println("id: " + a.getId());
            System.out.println("full name: " + a.getFull_name());
            System.out.println("------------");
        }
    }
}

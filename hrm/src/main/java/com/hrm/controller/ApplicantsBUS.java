package com.hrm.controller;

import com.hrm.dao.ApplicantsDAO;
import com.hrm.model.Applicants;
import java.util.ArrayList;

public class ApplicantsBUS {
    private ArrayList<Applicants> applicants ;

    public ApplicantsBUS() { 
        list();
    }

    public Applicants get (int id)
    {
        for(Applicants a : applicants)
        {
            if(a.getId()==(id))
            {
                return a;
            }
        }
        return null;
    }
    
    public void list() 
    {
        ApplicantsDAO applicantsDao = new ApplicantsDAO();
        applicants = new ArrayList<>();
        applicants = applicantsDao.list();
    }
    
    public void add(Applicants applicant)
    {
        applicants.add(applicant);
        ApplicantsDAO applicantsDAO = new ApplicantsDAO();
        applicantsDAO.add(applicant);
    }
    
    public void delete(int id)
    {
        for(Applicants applicant : applicants)
        {
            if(applicant.getId()==(id));
            {
                applicants.remove(id);
                ApplicantsDAO applicantsDAO = new ApplicantsDAO();
                applicantsDAO.delete(id);
                return;
            }
        }
    }
    
    public void set(Applicants applicant)
    {
        for(int i = 0 ; i < applicants.size() ; i++)
        {
            if(applicants.get(i).getId()==(applicant.getId()))
            {
                applicants.set(i, applicant);
                ApplicantsDAO applicantsDAO = new ApplicantsDAO();
                applicantsDAO.set(applicant);
                return;
            }
            
        }
    }
    
    public boolean check(int id)
    {
        for(Applicants applicant : applicants)
        {
            if(applicant.getId()==(id))
            {
                return true;
            }
        } return false;
    }
    
//    public ArrayList<Applicants> search(int id, String fullName)
//    {
//        ArrayList<Applicants> search = new ArrayList<>();
//        id = id.isEmpty() ? id="" : id;
//        fullName = fullName.isEmpty() ? fullName="" : fullName;
//        
//        for(Applicants applicant : applicants){
//            if(applicant.getId()==(id) && 
//                    applicant.getFull_name().contains(fullName)){
//                search.add(applicant);
//            }
//        }
//        return search;
//    }
    
    public ArrayList<Applicants> getList(){
        return applicants;
    }
}


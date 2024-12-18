package tuyenDung;

import java.util.ArrayList;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MSI
 */
public class ApplicantsBUS {
    private ArrayList<Applicants> applicants;

    public ApplicantsBUS() { 
    }

    public Applicants get (int id)
    {
        for(Applicants a : applicants)
        {
            if(a.getId().equals(id))
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
    
    public void delete(String id)
    {
        for(Applicants applicant : applicants)
        {
            if(applicant.getId().equals(id));
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
            if(applicants.get(i).getId().equals(applicant.getId()))
            {
                applicants.set(i, applicant);
                ApplicantsDAO applicantsDAO = new ApplicantsDAO();
                applicantsDAO.set(applicant);
                return;
            }
            
        }
    }
    
    public boolean check(String id)
    {
        for(Applicants applicant : applicants)
        {
            if(applicant.getId().equals(id))
            {
                return true;
            }
        } return false;
    }
    
    public ArrayList<Applicants> search(String id, String fullName)
    {
        ArrayList<Applicants> search = new ArrayList<>();
        id = id.isEmpty() ? id="" : id;
        fullName = fullName.isEmpty() ? fullName="" : fullName;
        
        for(Applicants applicant : applicants){
            if(applicant.getId().contains(id) && 
                    applicant.getFull_name().contains(fullName)){
                search.add(applicant);
            }
        }
        return search;
    }
    
    public ArrayList<Applicants> getList(){
        return applicants;
    }
}


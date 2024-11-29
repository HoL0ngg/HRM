package com.hrm.controller;

import com.hrm.dao.ApplicantsDAO;
import com.hrm.model.Applicants;
import com.hrm.model.Employee;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ApplicantsBUS {

    private ArrayList<Applicants> applicants;
    private ArrayList<Employee> employeeList;
    private InterviewsBUS intvBus;

    public ApplicantsBUS() {
        list();
    }

    public Applicants get(int id) {
        for (Applicants a : applicants) {
            if (a.getId() == (id)) {
                return a;
            }
        }
        return null;
    }

    public void list() {
        ApplicantsDAO applicantsDao = new ApplicantsDAO();
        applicants = new ArrayList<>();
        applicants = applicantsDao.list();
    }

    public void add(Applicants applicant) {
        applicants.add(applicant);
        ApplicantsDAO applicantsDAO = new ApplicantsDAO();
        applicantsDAO.add(applicant);
    }

    public void delete(int id) {
        for (Applicants applicant : applicants) {
            if (applicant.getId() == (id))
                ;
            {
                applicants.remove(id);
                ApplicantsDAO applicantsDAO = new ApplicantsDAO();
                applicantsDAO.delete(id);
                return;
            }
        }
    }

    public void set(Applicants applicant) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getId() == (applicant.getId())) {
                applicants.set(i, applicant);
                ApplicantsDAO applicantsDAO = new ApplicantsDAO();
                applicantsDAO.set(applicant);
                return;
            }

        }
    }

    public boolean check(int id) {
        for (Applicants applicant : applicants) {
            if (applicant.getId() == (id)) {
                return true;
            }
        }
        return false;
    }

    // public ArrayList<Applicants> search(String id, String fullName, String
    // position) {
    // ArrayList<Applicants> searchResults = new ArrayList<>();
    //
    // String idString = (id != null) ? id : null;
    // String fullNameString = (fullName != null) ? fullName : "";
    // String positionString = (position != null) ? position : "";
    //
    // searchResults = applicants.stream()
    // .filter(apl -> (idString == null ||
    // String.valueOf(apl.getId()).contains(idString))
    // && (positionString.isEmpty() ||
    // intvBus.getPositionByApplicantId(apl.getId()).toLowerCase().contains(positionString.toLowerCase()))
    // && (fullNameString.isEmpty() ||
    // apl.getFull_name().toLowerCase().contains(fullNameString.toLowerCase())))
    // .collect(Collectors.toCollection(ArrayList::new));
    //
    // return searchResults;
    // }
    public ArrayList<Applicants> search(String searchText) {
        intvBus = new InterviewsBUS();

        ArrayList<Applicants> searchResults = new ArrayList<>();

        String searchTextLower = searchText != null ? searchText.toLowerCase() : "";

        searchResults = applicants.stream()
                .filter(apl -> // Tìm theo ID
                (String.valueOf(apl.getId()).contains(searchText))
                        || // Tìm theo Full Name
                        apl.getFull_name().toLowerCase().contains(searchTextLower)
                        || // Tìm theo Position
                        intvBus.getPositionByApplicantId(apl.getId()).toLowerCase().contains(searchTextLower))
                .collect(Collectors.toCollection(ArrayList::new));

        return searchResults;
    }

    public ArrayList<Applicants> getList() {
        return applicants;
    }

    public int getNextId() {
        int maxId = applicants.stream().mapToInt(Applicants::getId).max().orElse(0);
        return maxId + 1;
    }

    public static void main(String[] args) {
        ApplicantsBUS a = new ApplicantsBUS();
        ArrayList<Applicants> applicantResults = a.search("NGuyễn");
        for (Applicants apl : applicantResults) {
            System.out.println("- " + apl.getFull_name() + " " + apl.getPhone());
        }
    }
}

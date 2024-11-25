package com.hrm.controller;

import com.hrm.dao.InterviewsDAO;
import com.hrm.model.Applicants;
import com.hrm.model.Department;
import com.hrm.model.Interviews;
import com.hrm.model.JobOpenings;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InterviewsBUS {

    private ArrayList<Interviews> intvList;
    private InterviewsDAO intvDao;
    private JobOpeningsBUS jobBus;
    private DepartmentBUS dptmBus;
    private ApplicantsBUS applicantBus;
    private InterviewerBUS intverBus;

    public InterviewsBUS() {
        intvList = new ArrayList<>();
        intvDao = new InterviewsDAO();
        jobBus = new JobOpeningsBUS();
        dptmBus = new DepartmentBUS();
        applicantBus = new ApplicantsBUS();
        intverBus = new InterviewerBUS();
        list();
    }

    public Interviews get(int id) {
        if (intvList == null) {
            return null;
        }
        for (Interviews j : intvList) {
            if (j.getId() == id) {
                return j;
            }
        }
        return null;
    }
    
    public Interviews getByJobId(int id){
        if (intvList == null) {
            return null;
        }
        for (Interviews j : intvList) {
            if (j.getJob_open_id() == id) {
                return j;
            }
        }
        return null;
    }

    public Interviews getByApplicantId(int id) {
        if (intvList == null) {
            return null;
        }
        for (Interviews j : intvList) {
            if (j.getApplicants_id() == id) {
                return j;
            }
        }
        return null;
    }

    public String getPositionNameById(int id) {
        if (intvList == null) {
            return null;
        }
        for (Interviews j : intvList) {
            if (j.getId() == id) {
                JobOpenings job = jobBus.get(j.getJob_open_id());
                if (job != null) {
                    return job.getPosition();
                }
            }
        }
        return null;
    }

    public String getFullNameById(int id) {
        if (intvList == null) {
            return null;
        }
        for (Interviews j : intvList) {
            if (j.getId() == id) {
                Applicants applicant = applicantBus.get(j.getApplicants_id());
                if (applicant != null) {
                    return applicant.getFull_name();
                }
            }
        }
        return null;
    }

    public String getPositionByApplicantId(int id) {
        if (intvList == null) {
            return null;
        }
        for (Interviews j : intvList) {
            if (j.getApplicants_id() == id) {
                JobOpenings job = jobBus.get(j.getJob_open_id());
                if (job != null) {
                    return job.getPosition();
                }
            }
        }
        return null;
    }

    public String getPositionById(int id) {
        if (intvList == null) {
            return null;
        }
        for (Interviews j : intvList) {
            if (j.getId() == id) {
                JobOpenings job = jobBus.get(j.getJob_open_id());
                if (job != null) {
                    return job.getPosition();
                }
            }
        }
        return null;
    }

    public String getDepartmentName(int id) {
        if (intvList == null) {
            return null;
        }
        for (Interviews j : intvList) {
            if (j.getApplicants_id() == id) {
                JobOpenings job = jobBus.get(j.getJob_open_id());
                if (job != null) {
                    Department dptm = dptmBus.get(job.getDepartment_id());
                    if (dptm != null) {
                        return dptm.getName();
                    }
                }
            }
        }
        return null;
    }

    public String getStageById(int id) {
        if (intvList == null) {
            return null;
        }
        for (Interviews j : intvList) {
            if (j.getApplicants_id() == id) {
                return j.getResult();
            }
        }
        return null;
    }

    public void list() {
        intvList = intvDao.list();
    }

    public void add(Interviews intv) {
        if (!check(intv.getId())) {
            intvList.add(intv);
            intvDao.add(intv);
        }
    }

//    public void delete(int id){
//        for(Interviews intv : intvList){
//            if(intv.getId()==id){
//                intvList.remove(id);
//                intvDao = new InterviewsDAO();
//                intvDao.delete(id);
//                return;
//            }
//        }
//    }
    public void set(Interviews intv) {
        for (int i = 0; i < intvList.size(); i++) {
            if (intvList.get(i).getId() == intv.getId()) {
                intvList.set(i, intv);
                intvDao.set(intv);
                return;
            }
        }
    }

    public void setStage(Interviews intv) {
        for (int i = 0; i < intvList.size(); i++) {
            if (intvList.get(i).getId() == intv.getId()) {
                intvList.set(i, intv);
                intvDao.setStage(intv);
                return;
            }
        }
    }

    public boolean check(int id) {
        for (Interviews intv : intvList) {
            if (intv.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Interviews> search(String searchText) {
        ArrayList<Interviews> searchResults = new ArrayList<>();

        // Chuẩn hóa searchText
        String searchString = (searchText != null) ? searchText.trim() : "";

        // Lọc danh sách intvList
        searchResults = intvList.stream()
                .filter(intv
                        -> (searchString.isEmpty() || String.valueOf(intv.getId()).contains(searchString)) // Kiểm tra ID
                || (searchString.isEmpty() || getPositionById(intv.getId()).toLowerCase().contains(searchString.toLowerCase())) // Kiểm tra Position
                || (searchString.isEmpty() || intverBus.getFullNamesById(intv.getId()).toLowerCase().contains(searchString.toLowerCase()))
                ) .collect(Collectors.toCollection(ArrayList::new));

        return searchResults;
    }

    public ArrayList<Interviews> getList() {
        return intvList;
    }

    public int getNextId() {
        int maxId = intvList.stream().mapToInt(Interviews::getId).max().orElse(0);
        return maxId + 1;
    }

}

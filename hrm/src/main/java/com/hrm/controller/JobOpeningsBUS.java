package com.hrm.controller;

import com.hrm.dao.JobOpeningsDAO;
import com.hrm.model.JobOpenings;
import java.util.ArrayList;
import java.util.Iterator;

public class JobOpeningsBUS {

    private ArrayList<JobOpenings> jobList;
    private JobOpeningsDAO jobDao;

    public JobOpeningsBUS() {
        jobList = new ArrayList<>();
        jobDao = new JobOpeningsDAO();
        list();
    }

    public JobOpenings get(int id) {
        if (jobList == null) {
            return null;
        }
        for (JobOpenings j : jobList) {
            if (j.getId() == id) {
                return j;
            }
        }
        return null;
    }

    public void list() {
        jobList = jobDao.list();
    }

    public void add(JobOpenings job) {
        if (!check(job.getId())) {
            jobList.add(job);
            jobDao.add(job);
        }
    }

    public void delete(int id) {
        for (JobOpenings job : jobList) {
            if (job.getId() == id) {
                jobList.remove(id);
                jobDao = new JobOpeningsDAO();
                jobDao.delete(id);
                return;
            }
        }
    }

    public void set(JobOpenings job) {
        for (int i = 0; i < jobList.size(); i++) {
            if (jobList.get(i).getId() == job.getId()) {
                jobList.set(i, job);
                jobDao.set(job);
                return;
            }
        }
    }

    public boolean check(int id) {
        for (JobOpenings job : jobList) {
            if (job.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<JobOpenings> search(Integer id, String position) {
        ArrayList<JobOpenings> searchResults = new ArrayList<>();

        String idString = (id != null) ? String.valueOf(id) : null;

        for (JobOpenings job : jobList) {
            boolean matchId = (idString == null || String.valueOf(job.getId()).contains(idString));
            boolean matchPosition = (position == null || position.isEmpty() || job.getPosition().contains(position));

            if (matchId && matchPosition) {
                searchResults.add(job);
            }
        }
        return searchResults;
    }

    public ArrayList<JobOpenings> getList() {
        jobList = jobDao.list();
        return jobList;
    }

    public int getNextId() {
        int maxId = jobList.stream().mapToInt(JobOpenings::getId).max().orElse(0);
        return maxId + 1;
    }
    
    public int getIdByPosition(String position){
        for(JobOpenings job : jobList){
            if(job.getPosition().equals(position)){
                return job.getId();
            }
        }
        return 0;
    }
}

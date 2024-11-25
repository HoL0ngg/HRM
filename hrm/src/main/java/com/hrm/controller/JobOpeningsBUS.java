package com.hrm.controller;

import com.hrm.dao.InterviewsDAO;
import com.hrm.dao.JobOpeningsDAO;
import com.hrm.model.Interviews;
import com.hrm.model.JobOpenings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class JobOpeningsBUS {

    private ArrayList<JobOpenings> jobList;
    private JobOpeningsDAO jobDao;
    private ArrayList<Interviews> intvList;
    private InterviewsDAO intvDao;

    public JobOpeningsBUS() {
        jobList = new ArrayList<>();
        jobDao = new JobOpeningsDAO();
        intvDao = new InterviewsDAO();
        intvList = new ArrayList<>();
        list();
    }

    public JobOpenings get(int id) {
        return jobList.stream().filter(job -> job.getId() == id).findFirst().orElse(null);
    }

    public void list() {
        jobList = jobDao.list();
    }

    public void add(JobOpenings job) {
        if (!check(job.getId())) {
            jobList.add(job);
            jobDao.add(job);
        } else {
            System.out.println("Job ID already exists!");
        }
    }

    public void delete(int id) {

        boolean hasInterviewScheduled = false;
        intvList = intvDao.list();
        for (Interviews intv : intvList) {
            if (intv.getJob_open_id() == id) { 
                hasInterviewScheduled = true; 
                break;
            }
        }

        if (hasInterviewScheduled == false) {
            Iterator<JobOpenings> iterator = jobList.iterator();
            while (iterator.hasNext()) {
                JobOpenings job = iterator.next();
                if (job.getId() == id) {
                    iterator.remove();
                    jobDao.delete(id);
                    System.out.println("Công việc đã được xóa");
                    return;
                }
            }
        }
        System.out.println("Không thể xóa công việc vì có lịch phỏng vấn");
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
        return jobList.stream().anyMatch(job -> job.getId() == id);
    }

    public ArrayList<JobOpenings> search(String searchText) {
        // Nếu searchText không phải là null và không phải là chuỗi rỗng, ta sẽ sử dụng nó để tìm kiếm
        String searchString = (searchText != null) ? searchText.trim() : "";

        return jobList.stream()
                .filter(job -> (searchString.isEmpty()
                || String.valueOf(job.getId()).contains(searchString)
                || // Tìm kiếm trong id
                job.getPosition().toLowerCase().contains(searchString.toLowerCase()))) // Tìm kiếm trong position
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<JobOpenings> getList() {
        return jobList;
    }

    public int getNextId() {
        int maxId = jobList.stream().mapToInt(JobOpenings::getId).max().orElse(0);
        return maxId + 1;
    }

    public int getIdByPosition(String position) {
        return jobList.stream().filter(job -> job.getPosition().equals(position))
                .map(JobOpenings::getId).findFirst().orElse(0);
    }
}

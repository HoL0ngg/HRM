package com.hrm.controller;

import com.hrm.dao.InterviewerDAO;
import com.hrm.model.Interviewer;
import java.util.ArrayList;
import java.util.List;

public class InterviewerBUS {

    private ArrayList<Interviewer> intverList;
    private InterviewerDAO intverDao;
    private EmployeeBus empBus;

    public InterviewerBUS() {
        intverList = new ArrayList<>();
        intverDao = new InterviewerDAO();
        empBus = new EmployeeBus();
        list();
    }

    public void list() {
        intverList = intverDao.list();
    }

    public void add(Interviewer intver) {
        intverList.add(intver);
        intverDao.add(intver);
    }

    public boolean check(int id) {
        for (Interviewer intver : intverList) {
            if (intver.getEmployee_id() == id) {
                return true;
            }
        }
        return false;
    }
    // public String[] getFullNameById(int id) {
    // List<String> list = new ArrayList<>();
    // if (intverList == null) {
    // return null;
    // }
    // for (Interviewer j : intverList) {
    // if (j.getInterview_id() == id) {
    // String emp = empBus.getFullName(j.getEmployee_id());
    // list.add(emp);
    // }
    // }
    // return list.toArray(new String[0]);
    // }

    public String getFullNamesById(int id) {
        List<String> list = new ArrayList<>();
        if (intverList == null) {
            return "";
        }
        for (Interviewer j : intverList) {
            if (j.getInterview_id() == id) {
                String emp = empBus.getFullName(j.getEmployee_id());
                list.add(emp);
            }
        }
        return String.join(", ", list);
    }

}

package com.hrm.controller;

import com.hrm.dao.DepartmentDAO;
import com.hrm.model.Department;
import java.util.ArrayList;

public class DepartmentBUS {
    private ArrayList<Department> dptmList;
    private DepartmentDAO departmentDao;
    
    public DepartmentBUS(){
        dptmList = new ArrayList<>();
        departmentDao = new DepartmentDAO();
        list();
    }

    public void list() {
        dptmList = departmentDao.selectAll();
    }
    
    public Department get(int id) {
        if (dptmList == null) return null;
        for (Department dptm : dptmList) {
            if (dptm.getId() == id) {
                return dptm;
            }
        }
        return null;
    }   

    public ArrayList<Department> getList(){
        return dptmList;
    }
}

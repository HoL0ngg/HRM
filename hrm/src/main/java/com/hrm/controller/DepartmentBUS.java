package com.hrm.controller;

import com.hrm.dao.DepartmentDAO;
import com.hrm.model.Department;
import java.util.ArrayList;

public class DepartmentBUS {
    private ArrayList<Department> dptmList;
    private DepartmentDAO departmentDao;
    private Department dptm;
    
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
    
    public boolean check(int id){
        for(Department dptm : dptmList){
            if(dptm.getManagerId() == id){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Department> getList(){
        return dptmList;
    }
}

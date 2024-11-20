/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hrm.controller;

import com.hrm.dao.EmployeeDAO;
import com.hrm.model.Employee;
import com.hrm.model.Interviews;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class EmployeeBus {

    private ArrayList<Employee> empList;
    private EmployeeDAO empDao;
    private DepartmentBUS dptmBus;

    public EmployeeBus() {
        empList = new ArrayList<>();
        empDao = new EmployeeDAO();
        empList = empDao.getNameList();
        dptmBus = new DepartmentBUS();
    }

    public int getIdByName(String name){
        for(Employee e : empList){
            if(e.getName().equals(name)){
                return e.getId();
            }
        }
        return 0;
    }
    public List<String> getManagerNameList() {
        List<String> list = new ArrayList<>();
        if (empList == null) {
            return list;
        }
        for (Employee j : empList) {
            if (dptmBus.check(j.getId())) {
                String emp = j.getName();
                list.add(emp);
            }
        }
        return list;
    }

    public String getFullName(int id) {
        if (empList == null) {
            return null;
        }
        for (Employee emp : empList) {
            if (emp.getId() == id) {
                return emp.getName();
            }
        }
        return null;
    }
}

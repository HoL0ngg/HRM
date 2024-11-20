/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hrm.controller;

import com.hrm.dao.EmployeeDAO;
import com.hrm.model.Employee;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class EmployeeBus {
    private ArrayList<Employee> empList;
    private EmployeeDAO empDao;
    
    public EmployeeBus(){
        empList = new ArrayList<>();
        empDao = new EmployeeDAO();
//        empList = empDao.fullNameList();
    }
    
    public String getFullName (int id){
        if(empList == null){
            return null;
        }
        for(Employee emp : empList){
            if(emp.getId() == id){
                return emp.getName();
            }
        }
        return null;
    }
}

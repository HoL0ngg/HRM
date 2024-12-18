package com.hrm.model;

import java.time.LocalDate;

public class JobOpenings {
    private int id;
    private int department_id;
    private String position;
    private LocalDate opening_date;
    private LocalDate closing_date;
    private String salary,status,detail;

    public JobOpenings(int id, int department_id, String position,String salary, LocalDate opening_date, LocalDate closing_date, String status,String detail) {
        this.id = id;
        this.department_id = department_id;
        this.position = position;
        this.opening_date = opening_date;
        this.closing_date = closing_date;
        this.status = status;
        this.salary = salary;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getOpening_date() {
        return opening_date;
    }

    public void setOpening_date(LocalDate opening_date) {
        this.opening_date = opening_date;
    }

    public LocalDate getClosing_date() {
        return closing_date;
    }

    public void setClosing_date(LocalDate closing_date) {
        this.closing_date = closing_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hrm.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SalaryChangeHistory {
    private int id;
    private Employee employee;
    private BigDecimal oldSalary;
    private BigDecimal newSalary;
    private String reasons;
    private LocalDate changeDate;
    private Employee approvedBy;
    private String comments;
    private String status;

    public SalaryChangeHistory() {
    }

    public SalaryChangeHistory(int id, Employee employee, BigDecimal oldSalary, BigDecimal newSalary, String reasons, LocalDate changeDate, Employee approvedBy, String comments, String status) {
        this.id = id;
        this.employee = employee;
        this.oldSalary = oldSalary;
        this.newSalary = newSalary;
        this.reasons = reasons;
        this.changeDate = changeDate;
        this.approvedBy = approvedBy;
        this.comments = comments;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public BigDecimal getOldSalary() {
        return this.oldSalary;
    }

    public void setOldSalary(BigDecimal oldSalary) {
        this.oldSalary = oldSalary;
    }

    public BigDecimal getNewSalary() {
        return this.newSalary;
    }

    public void setNewSalary(BigDecimal newSalary) {
        this.newSalary = newSalary;
    }

    public String getReasons() {
        return this.reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public LocalDate getChangeDate() {
        return this.changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public Employee getApprovedBy() {
        return this.approvedBy;
    }

    public void setApprovedBy(Employee approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        int var10000 = this.id;
        return "SalaryChangeHistory{id=" + var10000 + ", employee=" + this.employee.getName() + ", oldSalary=" + String.valueOf(this.oldSalary) + ", newSalary=" + String.valueOf(this.newSalary) + ", reasons='" + this.reasons + "', changeDate=" + String.valueOf(this.changeDate) + ", approvedBy=" + this.approvedBy.getName() + ", comments='" + this.comments + "', status='" + this.status + "'}";
    }
}

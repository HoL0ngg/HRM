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
    private Employee approvedBy;  // Thay đổi từ int sang Employee để lưu thông tin người duyệt
    private String comments;
    private String status;
    private String employeeName;

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

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return this.employeeName;
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
        return "SalaryChangeHistory{" +
                "id=" + id +
                ", oldSalary=" + oldSalary +
                ", newSalary=" + newSalary +
                ", reasons='" + reasons + '\'' +
                ", changeDate=" + changeDate +
                ", employee=" + employee.getName() +
                ", approvedBy=" + (approvedBy != null ? approvedBy.getName() : "N/A") + // Lấy tên người duyệt
                ", comments='" + comments + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

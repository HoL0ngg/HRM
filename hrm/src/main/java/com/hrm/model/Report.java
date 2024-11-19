package com.hrm.model;

import java.util.List;

public class Report {

    private int year;
    private int month;
    private int totalEmployees;
    private int newEmployees;
    private int resignedEmployees;

    public Report(int year, int month, int totalEmployees, int newEmployees, int resignedEmployees) {
        this.year = year;
        this.month = month;
        this.totalEmployees = totalEmployees;
        this.newEmployees = newEmployees;
        this.resignedEmployees = resignedEmployees;
    }

    // Getter và Setter
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public int getNewEmployees() {
        return newEmployees;
    }

    public void setNewEmployees(int newEmployees) {
        this.newEmployees = newEmployees;
    }

    public int getResignedEmployees() {
        return resignedEmployees;
    }

    public void setResignedEmployees(int resignedEmployees) {
        this.resignedEmployees = resignedEmployees;
    }

    public static List<Report> generateEmployeeReport(List<Employee> employees) {
        // Giả sử bạn sẽ nhóm nhân viên theo tháng và năm, sau đó tạo báo cáo tổng hợp
        // Đoạn mã này cần kết nối với cơ sở dữ liệu hoặc xử lý logic tính toán báo cáo
        return null;
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hrm.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Salary {
    private int id;
    private int employeeId;
    private Employee employee;
    private BigDecimal positionSalary;
    private BigDecimal bonus;
    private BigDecimal deductions;
    private BigDecimal net_salary;
    private BigDecimal overtimeSalary;
    private LocalDate payday;
    private String note;
    private int attendance;
    private Position position;
    private SalaryChangeHistory salaryChangeHistory;
    private BigDecimal hourly_salary ;
    private BigDecimal overtime_hourly_salary;
    private BigDecimal total_overtime_shifts;
    private float total_hourly_work;
    public Salary() {
    }

    public Salary(int id, int employeeId, BigDecimal positionSalary, BigDecimal bonus, BigDecimal deductions, BigDecimal net_salary, BigDecimal overtimeSalary, LocalDate payday, String note, int attendance, BigDecimal hourly_salary, BigDecimal overtime_hourly_salary, BigDecimal total_overtime_shifts, float total_hourly_work) {
        this.id = id;
        this.employeeId = employeeId;
        this.positionSalary = positionSalary;
        this.bonus = bonus;
        this.deductions = deductions;
        this.net_salary = net_salary;
        this.overtimeSalary = overtimeSalary;
        this.payday = payday;
        this.note = note;
        this.attendance = attendance;
        this.hourly_salary = hourly_salary;
        this.overtime_hourly_salary = overtime_hourly_salary;
        this.total_overtime_shifts = total_overtime_shifts;
        this.total_hourly_work = total_hourly_work;
        
    }

    public Salary(int id, Employee employee,SalaryChangeHistory salaryChangeHistory, BigDecimal positionSalary, BigDecimal bonus, BigDecimal deductions, BigDecimal net_salary, BigDecimal overtimeSalary, LocalDate payday, String note, int attendance, Position position,BigDecimal hourly_salary, BigDecimal overtime_hourly_salary, BigDecimal total_overtime_shifts, float total_hourly_work) {
        this.id = id;
        this.employee = employee;
        this.employeeId = employee.getId();
        this.positionSalary = positionSalary;
        this.bonus = bonus;
        this.deductions = deductions;
        this.net_salary = net_salary;
        this.overtimeSalary = overtimeSalary;
        this.payday = payday;
        this.note = note;
        this.attendance = attendance;
        this.position = position;
        this.hourly_salary = hourly_salary;
        this.overtime_hourly_salary = overtime_hourly_salary;
        this.total_overtime_shifts = total_overtime_shifts;
        this.total_hourly_work = total_hourly_work;
        this.salaryChangeHistory = salaryChangeHistory;
    }
    public BigDecimal getHourly_salary() {
        return hourly_salary;
    }

    public void setHourly_salary(BigDecimal hourly_salary) {
        this.hourly_salary = hourly_salary;
    }

    // Getter and Setter for overtime_hourly_salary
    public BigDecimal getOvertime_hourly_salary() {
        return overtime_hourly_salary;
    }

    public void setOvertime_hourly_salary(BigDecimal overtime_hourly_salary) {
        this.overtime_hourly_salary = overtime_hourly_salary;
    }

    // Getter and Setter for total_overtime_shifts
    public BigDecimal getTotal_overtime_shifts() {
        return total_overtime_shifts;
    }

    public void setTotal_overtime_shifts(BigDecimal total_overtime_shifts) {
        this.total_overtime_shifts = total_overtime_shifts;
    }

    // Getter and Setter for total_hourly_work
    public float getTotal_hourly_work() {
        return total_hourly_work;
    }

    public void setTotal_hourly_work(float total_hourly_work) {
        this.total_hourly_work = total_hourly_work;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return this.employeeId;
    }
    public void setSalaryChangeHistory(SalaryChangeHistory salaryChangeHistory){
        this.salaryChangeHistory = salaryChangeHistory;
    }
    public SalaryChangeHistory getsalaryChangeHistory(){
        return salaryChangeHistory;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        this.employeeId = employee.getId();
    }

    public BigDecimal getPositionSalary() {
        return this.positionSalary;
    }

    public void setPositionSalary(BigDecimal positionSalary) {
        this.positionSalary = positionSalary;
    }

    public BigDecimal getBonus() {
        return this.bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getDeductions() {
        return this.deductions;
    }

    public void setDeductions(BigDecimal deductions) {
        this.deductions = deductions;
    }

    public BigDecimal getnet_salary() {
        return this.net_salary;
    }

    public void setset_salary(BigDecimal net_salary) {
        this.net_salary = net_salary;
    }

    public BigDecimal getOvertimeSalary() {
        return this.overtimeSalary;
    }

    public void setOvertimeSalary(BigDecimal overtimeSalary) {
        this.overtimeSalary = overtimeSalary;
    }

    public LocalDate getPayday() {
        return this.payday;
    }

    public void setPayday(LocalDate payday) {
        this.payday = payday;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAttendance() {
        return this.attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        int var10000 = this.id;
        return "Salary [id=" + var10000 + ", employeeId=" + this.employeeId + ", positionSalary=" + String.valueOf(this.positionSalary) + ", bonus=" + String.valueOf(this.bonus) + ", deductions=" + String.valueOf(this.deductions) + ", netSalary=" + String.valueOf(this.net_salary) + ", overtimeSalary=" + String.valueOf(this.overtimeSalary) + ", payday=" + String.valueOf(this.payday) + ", note=" + this.note + ", attendance=" + this.attendance + "]";
    }

//    public Salary get(int i) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}

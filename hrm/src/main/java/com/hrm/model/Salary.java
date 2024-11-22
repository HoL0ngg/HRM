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
    private BigDecimal tax;
    private BigDecimal social_insurance;
    private int attendance;
    private Position position;

    public Salary() {
    }

    public Salary(int id, int employeeId, BigDecimal positionSalary, BigDecimal bonus, BigDecimal deductions,
            BigDecimal net_salary, BigDecimal overtimeSalary, LocalDate payday, String note, int attendance,
            BigDecimal tax, BigDecimal social_insurance) {
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
        this.tax = tax;
        this.social_insurance = social_insurance;
    }

    public Salary(int id, Employee employee, BigDecimal positionSalary, BigDecimal bonus, BigDecimal deductions,
            BigDecimal net_salary, BigDecimal overtimeSalary, LocalDate payday, String note, int attendance,
            Position position, BigDecimal tax, BigDecimal social_insurance) {
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
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    // Getter và Setter cho social_insurance
    public BigDecimal getSocialInsurance() {
        return social_insurance;
    }

    public void setSocialInsurance(BigDecimal social_insurance) {
        this.social_insurance = social_insurance;
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
        return "Salary [id=" + var10000 + ", employeeId=" + this.employeeId + ", positionSalary="
                + String.valueOf(this.positionSalary) + ", bonus=" + String.valueOf(this.bonus) + ", deductions="
                + String.valueOf(this.deductions) + ", net_salary=" + String.valueOf(this.net_salary)
                + ", overtimeSalary=" + String.valueOf(this.overtimeSalary) + ", payday=" + String.valueOf(this.payday)
                + ", note=" + this.note + ", attendance=" + this.attendance + "]";
    }
}

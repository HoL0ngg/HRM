package com.hrm.model;

import java.time.LocalDate;

public class Employee {

    public enum Gender {
        male,
        female
    }

    public enum Status {
        on,
        off
    }

    public enum Work_type {
        full_time,
        part_time,
        internship,
        on_board,
        work_from_home
    }

    private String id;
    private String name;
    private LocalDate dob;
    private int positionID;
    private int department_id;
    private Gender gender;
    private String phone_mumber;
    private String address;
    private String email;
    private LocalDate hire_date;
    private Status status;
    private int account_bank;
    private int indentity_card;
    private int tax_code;
    private int social_insurance_code;

    public Employee() {
    }

    public Employee(String id, String name, LocalDate dob, int positionID, int department_id, Gender gender,
            String phone_mumber, String address, String email, LocalDate hire_date, Status status, int account_bank,
            int indentity_card) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.positionID = positionID;
        this.department_id = department_id;
        this.gender = gender;
        this.phone_mumber = phone_mumber;
        this.address = address;
        this.email = email;
        this.hire_date = hire_date;
        this.status = status;
        this.account_bank = account_bank;
        this.indentity_card = indentity_card;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getPositionID() {
        return positionID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone_mumber() {
        return phone_mumber;
    }

    public void setPhone_mumber(String phone_mumber) {
        this.phone_mumber = phone_mumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getAccount_bank() {
        return account_bank;
    }

    public void setAccount_bank(int account_bank) {
        this.account_bank = account_bank;
    }

    public int getIndentity_card() {
        return indentity_card;
    }

    public void setIndentity_card(int indentity_card) {
        this.indentity_card = indentity_card;
    }

    public int getTax_code() {
        return tax_code;
    }

    public void setTax_code(int tax_code) {
        this.tax_code = tax_code;
    }

    public int getSocial_insurance_code() {
        return social_insurance_code;
    }

    public void setSocial_insurance_code(int social_insurance_code) {
        this.social_insurance_code = social_insurance_code;
    }
}

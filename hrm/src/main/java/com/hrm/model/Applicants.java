/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hrm.model;

import java.time.LocalDate;

/**
 *
 * @author MSI
 */
public class Applicants {
    private int id;
    private String full_name, email, phone, resume;
    private LocalDate applicant_date;

    public Applicants(int id, String full_name, String email, String phone, String resume, LocalDate applicant_date) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.resume = resume;
        this.applicant_date = applicant_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public LocalDate getApplicant_date() {
        return applicant_date;
    }

    public void setApplicant_date(LocalDate applicant_date) {
        this.applicant_date = applicant_date;
    }

    
}

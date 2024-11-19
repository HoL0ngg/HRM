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
public class Interviews {
    private int id;
    private int job_open_id;
    private int applicants_id;
    private LocalDate interviews_date;

    private enum interviews_stage {
    };

    private String note;

    public Interviews(int id, int job_open_id, int applicants_id, LocalDate interviews_date, String note) {
        this.id = id;
        this.job_open_id = job_open_id;
        this.applicants_id = applicants_id;
        this.interviews_date = interviews_date;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJob_open_id() {
        return job_open_id;
    }

    public void setJob_open_id(int job_open_id) {
        this.job_open_id = job_open_id;
    }

    public int getApplicants_id() {
        return applicants_id;
    }

    public void setApplicants_id(int applicants_id) {
        this.applicants_id = applicants_id;
    }

    public LocalDate getInterviews_date() {
        return interviews_date;
    }

    public void setInterviews_date(LocalDate interviews_date) {
        this.interviews_date = interviews_date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}

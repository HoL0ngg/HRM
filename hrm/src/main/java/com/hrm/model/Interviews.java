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
    private LocalDate interview_date;
    private String interview_stage, note, result ;

    public Interviews(int id, int job_open_id, int applicants_id, LocalDate interview_date, String interview_stage, String note, String result) {
        this.id = id;
        this.job_open_id = job_open_id;
        this.applicants_id = applicants_id;
        this.interview_date = interview_date;
        this.interview_stage = interview_stage;
        this.note = note;
        this.result = result;
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
        return interview_date;
    }

    public void setInterviews_date(LocalDate interviews_date) {
        this.interview_date = interviews_date;
    }

    public String getInterviews_stage() {
        return interview_stage;
    }

    public void setInterviews_stage(String interviews_stage) {
        this.interview_stage = interviews_stage;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}

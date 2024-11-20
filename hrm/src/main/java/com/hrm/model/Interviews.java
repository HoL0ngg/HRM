/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hrm.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author MSI
 */
public class Interviews {

    private int id;
    private int job_open_id;
    private int applicants_id;
    private LocalDate interview_date;
    private LocalTime interview_time;
    private String interview_stage, note, result;

    public Interviews(int id, int job_open_id, int applicants_id, LocalDate interview_date, LocalTime interview_time, String interview_stage, String note, String result) {
        this.id = id;
        this.job_open_id = job_open_id;
        this.applicants_id = applicants_id;
        this.interview_date = interview_date;
        this.interview_stage = interview_stage;
        this.interview_time = interview_time;
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

    public LocalDate getInterview_date() {
        return interview_date;
    }

    public void setInterview_date(LocalDate interview_date) {
        this.interview_date = interview_date;
    }

    public LocalTime getInterview_time() {
        return interview_time;
    }

    public void setInterview_time(LocalTime interview_time) {
        this.interview_time = interview_time;
    }

    public String getInterview_stage() {
        return interview_stage;
    }

    public void setInterview_stage(String interview_stage) {
        this.interview_stage = interview_stage;
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

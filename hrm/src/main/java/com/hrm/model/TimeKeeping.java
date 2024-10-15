package com.hrm.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeKeeping {

    public enum Status {
        Late_arrival_early_departure,
        Late_arrival_on_time_departure,
        On_time_arrival_early_departure,
        On_time_arrival_on_time_departure,
        Overtime_late_arrival,
        Overtime_on_time_arrival
    }

    private int id;
    private int employee_id;
    private LocalDate date;
    private LocalTime check_in_time;
    private LocalTime check_out_time;
    private Status status;

    public TimeKeeping(int id, int employee_id, LocalDate date, LocalTime check_in_time, LocalTime check_out_time,
            Status status) {
        this.id = id;
        this.employee_id = employee_id;
        this.date = date;
        this.check_in_time = check_in_time;
        this.check_out_time = check_out_time;
        this.status = status;
    }

    public TimeKeeping() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getCheck_in_time() {
        return check_in_time;
    }

    public void setCheck_in_time(LocalTime check_in_time) {
        this.check_in_time = check_in_time;
    }

    public LocalTime getCheck_out_time() {
        return check_out_time;
    }

    public void setCheck_out_time(LocalTime check_out_time) {
        this.check_out_time = check_out_time;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}

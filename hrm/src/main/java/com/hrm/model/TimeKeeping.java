//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hrm.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeKeeping {
<<<<<<< HEAD
=======

    public enum Status {
        di_tre_ve_som,
        di_tre_ve_dung_gio,
        di_dung_gio_ve_dung_gio,
        di_dung_gio_ve_som,
        tang_ca_di_dung_gio,
        tang_ca_di_tre
    }

>>>>>>> 484d70c9ef6a49ac09b9838e97d19b1e1452577f
    private int id;
    private int employee_id;
    private LocalDate date;
    private LocalTime check_in_time;
    private LocalTime check_out_time;
    private Status status;

    public TimeKeeping(int id, int employee_id, LocalDate date, LocalTime check_in_time, LocalTime check_out_time, Status status) {
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
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee_id() {
        return this.employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getCheck_in_time() {
        return this.check_in_time;
    }

    public void setCheck_in_time(LocalTime check_in_time) {
        this.check_in_time = check_in_time;
    }

    public LocalTime getCheck_out_time() {
        return this.check_out_time;
    }

    public void setCheck_out_time(LocalTime check_out_time) {
        this.check_out_time = check_out_time;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static enum Status {
        Late_arrival_early_departure,
        Late_arrival_on_time_departure,
        On_time_arrival_early_departure,
        On_time_arrival_on_time_departure,
        Overtime_late_arrival,
        Overtime_on_time_arrival;

        private Status() {
        }
    }
}

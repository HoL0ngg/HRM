/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tuyenDung;

import java.time.LocalDate;

/**
 *
 * @author MSI
 */
public class JobOpenings {
    private int id;
    private int department_id;
    private String position;
    private LocalDate opening_date;
    private LocalDate closing_date;
    private String status;
    
    private enum Status{};

    public JobOpenings(int id, int department_id, String position, LocalDate opening_date, LocalDate closing_date, String status) {
        this.id = id;
        this.department_id = department_id;
        this.position = position;
        this.opening_date = opening_date;
        this.closing_date = closing_date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getOpening_date() {
        return opening_date;
    }

    public void setOpening_date(LocalDate opening_date) {
        this.opening_date = opening_date;
    }

    public LocalDate getClosing_date() {
        return closing_date;
    }

    public void setClosing_date(LocalDate closing_date) {
        this.closing_date = closing_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}

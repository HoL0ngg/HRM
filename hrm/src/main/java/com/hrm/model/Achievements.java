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
public class Achievements {
    private int id, employeeId;
    private String title, description;
    private LocalDate awardDate;

    public Achievements(int id, int employeeId, String title, String description, LocalDate awardDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.title = title;
        this.description = description;
        this.awardDate = awardDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getAwardDate() {
        return awardDate;
    }

    public void setAwardDate(LocalDate awardDate) {
        this.awardDate = awardDate;
    }
    
}

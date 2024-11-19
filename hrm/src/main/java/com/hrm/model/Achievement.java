package com.hrm.model;

import java.time.LocalDate;

public class Achievement {
    private int id;
    private int employeeId;
    private String title;
    private LocalDate dateAwarded;
    private String description;

    public Achievement() {}

    public Achievement(int id, int employeeId, String title, LocalDate dateAwarded, String description) {
        this.id = id;
        this.employeeId = employeeId;
        this.title = title;
        this.dateAwarded = dateAwarded;
        this.description = description;
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

    public LocalDate getDateAwarded() {
        return dateAwarded;
    }

    public void setDateAwarded(LocalDate dateAwarded) {
        this.dateAwarded = dateAwarded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", title='" + title + '\'' +
                ", dateAwarded=" + dateAwarded +
                ", results='" + description + '\'' +
                '}';
    }
}

package com.hrm.model;

<<<<<<< HEAD
import java.time.LocalDate;

public class TrainingDevelopment {

    private int id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;

    // Enum cho trạng thái của chương trình đào tạo
    public enum Status {
        COMPLETED("completed"),
        IN_PROGRESS("in progress"),
        DELETED("deleted");

        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Status fromValue(String value) {
            for (Status status : Status.values()) {
                if (status.getValue().equalsIgnoreCase(value)) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Invalid status value: " + value);
        }
    }

    // Constructor không tham số
    public TrainingDevelopment() {
    }

    // Constructor đầy đủ tham số
    public TrainingDevelopment(int id, String name, String description, LocalDate startDate, LocalDate endDate, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TrainingDevelopment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                '}';
=======
public class TrainingDevelopment {
	private String trainingDevelopment;
    private String achievement;

    public TrainingDevelopment(String trainingDevelopment, String achievement) {
        this.trainingDevelopment = trainingDevelopment;
        this.achievement = achievement;
    }

    public String getTrainingDevelopment() {
        return trainingDevelopment;
    }

    public void setTrainingDevelopment(String trainingDevelopment) {
        this.trainingDevelopment = trainingDevelopment;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
>>>>>>> 484d70c9ef6a49ac09b9838e97d19b1e1452577f
    }
}

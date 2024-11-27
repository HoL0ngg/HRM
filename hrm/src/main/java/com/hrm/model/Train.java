package com.hrm.model;

public class Train {

    private Employee employee; // Đối tượng Employee
    private TrainingDevelopment trainingDevelopment; // Đối tượng TrainingDevelopment

    // Constructor không tham số
    public Train() {
    }

    // Constructor với tham số
    public Train(Employee employee, TrainingDevelopment trainingDevelopment) {
        this.employee = employee;
        this.trainingDevelopment = trainingDevelopment;
    }

    // Getter và Setter
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TrainingDevelopment getTrainingDevelopment() {
        return trainingDevelopment;
    }

    public void setTrainingDevelopment(TrainingDevelopment trainingDevelopment) {
        this.trainingDevelopment = trainingDevelopment;
    }

    // Override phương thức toString() để hiển thị thông tin
    @Override
    public String toString() {
        return "Train{" +
                "employee=" + employee +
                ", trainingDevelopment=" + trainingDevelopment +
                '}';
    }
}

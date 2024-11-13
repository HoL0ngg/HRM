package com.hrm.model;

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
    }
}

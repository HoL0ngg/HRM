package com.hrm.controller;

import com.hrm.model.Achievements;
import com.hrm.dao.AchievementsDAO;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AchievementsBUS {

    private AchievementsDAO aDao;
    private ArrayList<Achievements> achievementsList;
    private EmployeeBus eBus;

    public AchievementsBUS() {
        aDao = new AchievementsDAO();
        eBus = new EmployeeBus();
        list(); 
    }

    public void list() {
        achievementsList = aDao.list();
    }

    public void add(Achievements achievement) {
        achievementsList.add(achievement); 
        aDao.add(achievement); 
    }

    public void delete(int id) {
        for (Achievements achievement : achievementsList) {
            if (achievement.getId() == id) {
                achievementsList.remove(achievement); 
                aDao.delete(id);
                return;
            }
        }
    }

    public Achievements get(int id) {
        return achievementsList.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    public void set(Achievements achievement) {
        for (int i = 0; i < achievementsList.size(); i++) {
            if (achievementsList.get(i).getId() == achievement.getId()) {
                achievementsList.set(i, achievement); 
                aDao.set(achievement); 
                return;
            }
        }
    }

    public ArrayList<Achievements> search(String searchText) {
        String searchString = (searchText != null) ? searchText.trim() : "";
        return achievementsList.stream()
                .filter(a -> searchString.isEmpty()
                || String.valueOf(a.getId()).contains(searchString)
                || eBus.getFullName(a.getEmployeeId()).toLowerCase().contains(searchString.toLowerCase())
                || a.getTitle().toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Achievements> getAchievementsList() {
        return achievementsList;
    }

    public boolean check(int id) {
        return achievementsList.stream().anyMatch(a -> a.getId() == id);
    }

    public int getNextId() {
        int maxId = achievementsList.stream().mapToInt(Achievements::getId).max().orElse(0);
        return maxId + 1;
    }
    
    public ArrayList<Achievements> getList(){
        return achievementsList;
    }
}

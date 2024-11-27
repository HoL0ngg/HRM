package com.hrm.controller;

import com.hrm.dao.TrainerDAO;
import com.hrm.dao.TrainingDevelopmentDAO;
import com.hrm.model.Trainer;
import com.hrm.model.TrainingDevelopment;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TrainingDevelopmentBUS {

    private TrainingDevelopmentDAO tdDao;
    private TrainerDAO trainerDao;

    public TrainingDevelopmentBUS() {
        tdDao = new TrainingDevelopmentDAO();
        trainerDao = new TrainerDAO();
    }

    public ArrayList<TrainingDevelopment> getList() {
        return new ArrayList<>(tdDao.list());
    }

    public void add(TrainingDevelopment td) {
        tdDao.add(td);
    }
//    public boolean add(TrainingDevelopment td) {
//        try {
//            tdDao.add(td);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Hoặc ghi log nếu bạn có hệ thống log
//            System.err.println("Lỗi khi thêm TrainingDevelopment: " + e.getMessage());
//            return false;
//        }
//    }

    public void delete(int id) {
        tdDao.delete(id);
    }

    public TrainingDevelopment get(int id) {
        return new ArrayList<>(tdDao.list()).stream()
                .filter(td -> td.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void set(TrainingDevelopment td) {
        tdDao.set(td);
    }

    public ArrayList<TrainingDevelopment> search(String searchText) {
        String searchString = (searchText != null) ? searchText.trim() : "";
        return tdDao.list().stream()
                .filter(td -> searchString.isEmpty()
                || String.valueOf(td.getId()).contains(searchString)
                || td.getName().toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean check(int id) {
        return new ArrayList<>(tdDao.list()).stream().anyMatch(td -> td.getId() == id);
    }

    public int getNextId() {
        int maxId = new ArrayList<>(tdDao.list()).stream()
                .mapToInt(TrainingDevelopment::getId)
                .max()
                .orElse(0);
        return maxId + 1;
    }
}

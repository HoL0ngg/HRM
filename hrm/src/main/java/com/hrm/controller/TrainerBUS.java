package com.hrm.controller;

import com.hrm.dao.TrainerDAO;
import com.hrm.model.Trainer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrainerBUS {

    private ArrayList<Trainer> trainers;
    private TrainerDAO trainerDao;
    private EmployeeBus eBus;

    public TrainerBUS() {
        trainerDao = new TrainerDAO();
        eBus = new EmployeeBus();
        list();
    }

    public void list() {
        trainers = trainerDao.list();
    }

    public void add(Trainer trainer) {
        trainers.add(trainer);
        trainerDao.add(trainer);
    }

    public void delete(int id) {
        trainers.removeIf(trainer -> trainer.getTrainId() == id);
        trainerDao.delete(id);
    }

    public Trainer get(int id) {
        return trainers.stream().filter(t -> t.getTrainId() == id).findFirst().orElse(null);
    }

    public void set(Trainer trainer) {
        for (int i = 0; i < trainers.size(); i++) {
            if (trainers.get(i).getTrainId() == trainer.getTrainId()) {
                trainers.set(i, trainer);
                trainerDao.set(trainer);
                return;
            }
        }
    }

    public ArrayList<Trainer> search(String searchText) {
        String searchString = (searchText != null) ? searchText.trim() : "";
        return trainers.stream()
                .filter(t -> searchString.isEmpty()
                || String.valueOf(t.getTrainId()).contains(searchString)
                || eBus.getFullName(t.getEmployeeId()).toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public boolean check(int id) {
        return trainers.stream().anyMatch(t -> t.getTrainId() == id);
    }

    public String getHostById(int id) {
        List<String> list = new ArrayList<>();
        if (trainers == null) {
            return "";
        }
        for (Trainer t : trainers) {
            if (t.getTrainId() == id) {
                String host = eBus.getFullName(t.getEmployeeId());
                list.add(host);
            }
        }
        return String.join(", ", list);
    }
}

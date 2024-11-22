//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hrm.model;

public class Department {
    private int id;
    private int managerId;
    private String name;

    public Department() {
    }

    public Department(int id, int managerId, String name) {
        this.id = id;
        this.managerId = managerId;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManagerId() {
        return this.managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department [id=" + this.id + ", managerId=" + this.managerId + ", name=" + this.name + "]";
    }
}

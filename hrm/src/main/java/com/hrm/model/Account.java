package com.hrm.model;

public class Account {
    private int emloyee_id;
    private String username;
    private String password;

    public Account() {
    }

    public Account(int emloyee_id, String username, String password) {
        this.emloyee_id = emloyee_id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmloyee_id() {
        return this.emloyee_id;
    }

    public void setEmloyee_id(int emloyee_id) {
        this.emloyee_id = emloyee_id;
    }
}

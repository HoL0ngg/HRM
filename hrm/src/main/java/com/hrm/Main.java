package com.hrm;

import com.hrm.model.Employee;
import com.hrm.view.LoginFrame;
import com.hrm.view.MainFrame;

public class Main {
    public static void main(String[] args) {
        // new LoginFrame();
        new MainFrame(new Employee("NV001", "Long cute"));
    }
}
package com.hrm.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.hrm.model.Employee;
import com.hrm.view.LoginFrame;
import com.hrm.view.MainFrame;

public class LoginController implements ActionListener {
    private LoginFrame log;

    public LoginController(LoginFrame log) {
        this.log = log;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("Dang nhap")) {
            Employee employee = log.DangNhap();
            if (employee == null)
                JOptionPane.showMessageDialog(log, "Ten dang nhap hoac mat khau khong dung", "Thong bao",
                        JOptionPane.ERROR_MESSAGE);
            else {
                new MainFrame(employee);
                log.dispose();
            }
        }
    }
}
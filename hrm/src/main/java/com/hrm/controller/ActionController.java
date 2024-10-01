package com.hrm.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.hrm.view.LoginFrame;

public class ActionController implements ActionListener {
    private LoginFrame log;

    public ActionController(LoginFrame log) {
        this.log = log;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("Dang nhap")) {
            boolean check = log.DangNhap();
            if (!check)
                JOptionPane.showMessageDialog(log, "Ten dang nhap hoac mat khau khong dung", "Thong bao",
                        JOptionPane.ERROR_MESSAGE);
            else {
                // new MainFrame();
                log.dispose();
            }
        }
    }
}
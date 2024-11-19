package com.hrm.controller;

import com.hrm.model.Employee;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.hrm.dao.EmployeeDAO;
import com.hrm.view.ChamCongFrame;
<<<<<<< HEAD
import com.hrm.view.EmployeeDetailFrame;
import com.hrm.view.EmployeeManagerFrame;
import com.hrm.view.MainFrame;
import javax.swing.JOptionPane;
=======
import com.hrm.view.CongViecFrame;
import com.hrm.view.LoginFrame;
import com.hrm.view.MainFrame;
import com.hrm.view.ReportView1;
import com.hrm.view.SalaryFrame;
>>>>>>> 484d70c9ef6a49ac09b9838e97d19b1e1452577f

public class MainController implements MouseListener {
    private MainFrame mainFrame;

    public MainController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel src = (JPanel) e.getSource();
<<<<<<< HEAD
        if (src.getName().equals("ChamCongPanel")) {
            new ChamCongFrame(mainFrame.getEmployee());
            mainFrame.setVisible(false);
        } else if (src.getName().equals("NhanVienPanel")) {
            System.out.println("Employee: " + mainFrame.getEmployee());
            
            Employee e1 = mainFrame.getEmployee();
            if (e1.getId() == e1.getDepartment().getManagerId()) {
                // admin
                new EmployeeManagerFrame();
            } else {
                // normal
                new EmployeeDetailFrame(e1);
            }
            mainFrame.setVisible(false);
=======
        String name = src.getName();
        // Employee employee =
        // EmployeeDAO.getInstance().selectByID(mainFrame.getEmployee().getId());
        switch (name) {
            case "ChamCongPanel":
                new ChamCongFrame(mainFrame.getEmployee());
                mainFrame.dispose();
                break;
            case "DangXuatPanel":
                new LoginFrame();
                mainFrame.dispose();
                break;
            case "LuongPanel":
                new SalaryFrame().setVisible(true);
                mainFrame.dispose();
                break;
            case "CongViecPanel":
                new CongViecFrame(EmployeeDAO.getInstance().selectByID(mainFrame.getEmployee().getId()));
                mainFrame.dispose();
                break;
            case "BaoCaoPanel":
                new ReportView1(EmployeeDAO.getInstance().selectByID(mainFrame.getEmployee().getId()));
                mainFrame.dispose();
                break;   
           
>>>>>>> 484d70c9ef6a49ac09b9838e97d19b1e1452577f
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}

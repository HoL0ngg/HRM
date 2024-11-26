package com.hrm.controller;

import com.hrm.model.Employee;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.hrm.dao.EmployeeDAO;
import com.hrm.view.ChamCongFrame;
import com.hrm.view.CongViecFrame;
import com.hrm.view.LoginFrame;
import com.hrm.view.MainFrame;
import com.hrm.view.BaoCaoFrame;
import com.hrm.view.SalaryFrame;
import com.hrm.view.SalaryNhanVien;

public class MainController implements MouseListener {
    private MainFrame mainFrame;

    public MainController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel src = (JPanel) e.getSource();
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
                if (mainFrame.getEmployee().getId()==27 || mainFrame.getEmployee().getId()== 421 ){
                new SalaryFrame(mainFrame.getEmployee());
                mainFrame.dispose();
                }
                else {
                    new SalaryNhanVien(mainFrame.getEmployee());
                }
                
                break;
            case "CongViecPanel":
                new CongViecFrame(EmployeeDAO.getInstance().selectByID(mainFrame.getEmployee().getId()));
                mainFrame.dispose();
                break;
            case "BaoCaoPanel":
                new BaoCaoFrame(mainFrame.getEmployee());
                mainFrame.dispose();
                break;
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

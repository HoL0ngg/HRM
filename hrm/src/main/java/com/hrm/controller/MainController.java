package com.hrm.controller;

import com.hrm.model.Employee;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.hrm.view.ChamCongFrame;
import com.hrm.view.EmployeeDetailFrame;
import com.hrm.view.EmployeeManagerFrame;
import com.hrm.view.MainFrame;
import javax.swing.JOptionPane;

public class MainController implements MouseListener {
    private MainFrame mainFrame;

    public MainController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel src = (JPanel) e.getSource();
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

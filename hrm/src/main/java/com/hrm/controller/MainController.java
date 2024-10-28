package com.hrm.controller;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.hrm.view.ChamCongFrame;
import com.hrm.view.LoginFrame;
import com.hrm.view.MainFrame;

public class MainController implements MouseListener {
    private MainFrame mainFrame;

    public MainController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel src = (JPanel) e.getSource();
        String name = src.getName();
        switch (name) {
            case "ChamCongPanel":
                new ChamCongFrame(mainFrame.getEmployee());
                mainFrame.dispose();
                ;
                break;
            case "DangXuatPanel":
                new LoginFrame();
                mainFrame.dispose();
            default:
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

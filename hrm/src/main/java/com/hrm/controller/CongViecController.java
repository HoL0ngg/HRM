package com.hrm.controller;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.hrm.view.CongViecFrame;
import com.hrm.view.MainFrame;

public class CongViecController implements MouseListener {
    private CongViecFrame frame;

    public CongViecController(CongViecFrame frame) {
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String str = e.getComponent().getName();
        switch (str) {
            case "quaylai":
                new MainFrame(frame.getEmployee());
                frame.dispose();
                break;
            case "taolichhop":
                System.out.println("hihi");
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
        e.getComponent().setBackground(e.getComponent().getBackground().darker());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        e.getComponent().setBackground(e.getComponent().getBackground().brighter());
    }

}

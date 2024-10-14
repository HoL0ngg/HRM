package com.hrm.controller;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import com.hrm.view.ChamCongFrame;
import com.hrm.view.MainFrame;

public class ChamCongController implements MouseListener {
    private ChamCongFrame frame;

    public ChamCongController(ChamCongFrame frame) {
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String str = e.getComponent().getName();
        if (str.equals("quaylai")) {
            new MainFrame(frame.getEmployee());
            frame.dispose();
        } else if (str.equals("filter")) {
            frame.showPopUp((JLabel) e.getComponent());
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

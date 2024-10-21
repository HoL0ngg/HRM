package com.hrm.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class EnterController implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComponent) {
            ((JComponent) e.getSource()).transferFocus();
        }
    }

    // Phương thức để thêm ActionListener vào JTextField
    public void addTransferFocusListener(JTextField textField) {
        textField.addActionListener(this);
    }

    // Phương thức để thêm ActionListener vào JDateChooser
    public void addTransferFocusListener(JDateChooser dateChooser) {
        // Lắng nghe sự kiện từ editor của JDateChooser
        JTextField editor = ((JTextField) dateChooser.getDateEditor().getUiComponent());
        editor.addActionListener(this);
    }

}

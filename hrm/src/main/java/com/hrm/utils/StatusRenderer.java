//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hrm.utils;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class StatusRenderer extends DefaultTableCellRenderer {
    public StatusRenderer() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Color dotColor;
        if (value.equals("pass")) {
            dotColor = Color.GREEN;
        } else if (value.equals("fail")) {
            dotColor = Color.RED;
        } else {
            dotColor = Color.GRAY;
        }

        SalaryStatusRenderer panel = new SalaryStatusRenderer(dotColor);
        return panel;
    }
}

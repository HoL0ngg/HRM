//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hrm.utils;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.hrm.model.TimeKeeping.Status;

public class StatusRenderer extends DefaultTableCellRenderer {
    public StatusRenderer() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        Color dotColor = Color.white;
        if (value instanceof Status) {
            Status status = (Status) value;
            // Thay đổi màu nền dựa trên trạng thái
            switch (status) {
                case di_tre_ve_som:
                    dotColor = Color.red;
                    break;
                case di_tre_ve_dung_gio:
                    dotColor = Color.orange;
                    break;
                case tang_ca_di_tre:
                    dotColor = Color.orange;
                    break;
                case di_dung_gio_ve_som:
                    dotColor = Color.yellow;
                    break;
                case di_dung_gio_ve_dung_gio:
                    dotColor = Color.green;
                    break;
                case tang_ca_di_dung_gio:
                    dotColor = Color.green;
                    break;
            }
        } else {
            if (value.equals("pass")) {
                dotColor = Color.GREEN;
            } else if (value.equals("fail")) {
                dotColor = Color.RED;
            } else {
                dotColor = Color.GRAY;
            }
        }

        SalaryStatusRenderer panel = new SalaryStatusRenderer(dotColor);
        return panel;
    }
}

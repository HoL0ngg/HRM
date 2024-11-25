package com.hrm.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import com.hrm.view.Table;

public class ExcelExporter {

    public void exportTableToExcel(Table table, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        // Lấy dữ liệu từ Table
        String[] columnNames = new String[table.getColumnModel().getColumnCount()];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = table.getColumnModel().getColumn(i).getHeaderValue().toString();
        }
        
        Object[][] data = new Object[table.getModel().getRowCount()][table.getModel().getColumnCount()];
        for (int row = 0; row < table.getModel().getRowCount(); row++) {
            for (int col = 0; col < table.getModel().getColumnCount(); col++) {
                data[row][col] = table.getValueAt(row, col);
            }
        }

        // Tạo hàng đầu tiên là tiêu đề
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnNames[i]);
        }

        // Thêm dữ liệu từ table
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i + 1); // Hàng tiếp theo sau header
            for (int j = 0; j < data[i].length; j++) {
                Cell cell = row.createCell(j);
                if (data[i][j] != null) {
                    cell.setCellValue(data[i][j].toString());
                } else {
                    cell.setCellValue("");
                }
            }
        }

        // Ghi dữ liệu ra file Excel
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.hrm.controller;

import com.hrm.dao.ReportDAO;
import java.io.IOException;
import java.util.List;

public class ReportController {
    private ReportDAO reportDAO;

    public ReportController() {
        reportDAO = new ReportDAO();
    }

    // Phương thức lấy dữ liệu báo cáo
    public List<Object[]> getEmployeeReport() {
        return reportDAO.getEmployeeReport();
    }

    // Phương thức xuất dữ liệu ra file Excel
    public void exportReportToExcel(List<Object[]> data, String[] columnNames) throws IOException {
        reportDAO.exportToExcel(data, columnNames);
    }
}

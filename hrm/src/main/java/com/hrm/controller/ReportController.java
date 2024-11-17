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
    
    // Phương thức lấy dữ liệu báo cáo theo phạm vi tháng
    public List<Object[]> getEmployeeReportByMonthRange(int year, int fromMonth, int toMonth) {
        return reportDAO.getEmployeeReportByMonthRange(year, fromMonth, toMonth);
    }

    // Phương thức xuất dữ liệu ra file Excel
    public void exportReportToExcel(List<Object[]> data, String[] columnNames, String reportName) throws IOException {
        reportDAO.exportToExcel(data, columnNames, reportName);
    }

    // Phương thức tìm kiếm báo cáo trong phạm vi năm và tháng
    public List<Object[]> searchReports(int year, int fromMonth, int toMonth) {
        if (fromMonth > toMonth) {
            throw new IllegalArgumentException("Tháng bắt đầu không thể lớn hơn tháng kết thúc.");
        }
        return getEmployeeReportByMonthRange(year, fromMonth, toMonth);
    }
}

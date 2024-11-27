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
    
    public List<Object[]> getEmployeeWorkEffect() {
        return reportDAO.getEmployeeWorkEffect();
    }
    
    // Phương thức lấy dữ liệu báo cáo theo phạm vi tháng
    public List<Object[]> getEmployeeReportByMonthRange(int year, int fromMonth, int toMonth) {
        return reportDAO.getEmployeeReportByMonthRange(year, fromMonth, toMonth);
    }
    
    public List<Object[]> getRecruitmentPerformanceReport(){
    	return reportDAO.getRecruitmentPerformanceReport();
    }
    
    public List<Object[]> getRecruitmentPerformanceReport(int year, int fromMonth, int toMonth){
    	return reportDAO.getRecruitmentPerformanceReport(year, fromMonth, toMonth);
    }
    
    public List<Object[]> getAllReports(){
    	return reportDAO.getAllReports();
    }
    
    public List<Object[]> getAllReports(int year, int monthFrom, int monthTo){
    	return reportDAO.getAllReports(year, monthFrom, monthTo);
    }
    
    public void saveReport(int createdBy, String reportTitle, String results) {
        ReportDAO reportDAO = new ReportDAO();

        boolean isSaved = reportDAO.saveReport(createdBy, reportTitle, results);

        if (isSaved) {
            System.out.println("Lưu báo cáo thành công!");
        } else {
            System.out.println("Lưu báo cáo thất bại!");
        }
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
    
    public List<Object[]> searchReports2(int year, int fromMonth, int toMonth) {
        if (fromMonth > toMonth) {
            throw new IllegalArgumentException("Tháng bắt đầu không thể lớn hơn tháng kết thúc.");
        }
        return getRecruitmentPerformanceReport(year, fromMonth, toMonth);
    }
    
    public List<Object[]> searchReports3(int year, int fromMonth, int toMonth) {
        if (fromMonth > toMonth) {
            throw new IllegalArgumentException("Tháng bắt đầu không thể lớn hơn tháng kết thúc.");
        }
        return getAllReports(year, fromMonth, toMonth);
    }
}

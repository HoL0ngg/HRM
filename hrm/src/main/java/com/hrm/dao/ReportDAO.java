package com.hrm.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class ReportDAO {
    private MySQLConnect mySQL = new MySQLConnect();

    // Phương thức lấy danh sách dữ liệu
    public List<Object[]> getEmployeeReport() {
        List<Object[]> reportData = new ArrayList<>();
        String query = "SELECT YEAR(hire_date) AS year, " +
                       "MONTH(hire_date) AS month, " +
                       "COUNT(*) AS total_employees, " +
                       "SUM(CASE WHEN hire_date >= CURDATE() - INTERVAL 1 MONTH THEN 1 ELSE 0 END) AS new_employees, " +
                       "SUM(CASE WHEN status = 'off' THEN 1 ELSE 0 END) AS resigned_employees " +
                       "FROM employee " +
                       "GROUP BY YEAR(hire_date), MONTH(hire_date) " +  // Nhóm theo năm và tháng
                       "ORDER BY year, month";  // Sắp xếp theo năm và tháng

        try (Connection connection = mySQL.getConnection(); 
             Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int year = rs.getInt("year");
                int month = rs.getInt("month");
                int totalEmployees = rs.getInt("total_employees");
                int newEmployees = rs.getInt("new_employees");
                int resignedEmployees = rs.getInt("resigned_employees");
                reportData.add(new Object[]{year, month, totalEmployees, newEmployees, resignedEmployees});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportData;
    }
    
    public List<Object[]> getEmployeeWorkEffect() {
        List<Object[]> reportData = new ArrayList<>();
        String query = "SELECT e.id, e.name, d.name AS department_name, " +  // Thay 'd.department_name' bằng 'd.name'
                       "   (SELECT AVG(t.task_score) FROM tasks t WHERE t.employee_id = e.id) AS completion_rate " +
                       "FROM employee e " +
                       "JOIN departments d ON e.department_id = d.id";

        try (Connection connection = mySQL.getConnection();  // Thay 'mySQL.getConnection()' bằng phương thức kết nối của bạn
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String department = rs.getString("department_name");
                double completionRate = rs.getDouble("completion_rate");

                // Thêm dữ liệu vào danh sách
                reportData.add(new Object[]{id, name, department, completionRate});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportData;
    }


    // Phương thức lấy dữ liệu báo cáo theo năm và tháng
    public List<Object[]> getEmployeeReportByMonthRange(int year, int monthFrom, int monthTo) {
        List<Object[]> reportData = new ArrayList<>();
        String query = "SELECT YEAR(hire_date) AS year, " +
                       "MONTH(hire_date) AS month, " +
                       "COUNT(*) AS total_employees, " +
                       "SUM(CASE WHEN hire_date >= CURDATE() - INTERVAL 1 MONTH THEN 1 ELSE 0 END) AS new_employees, " +
                       "SUM(CASE WHEN status = 'off' THEN 1 ELSE 0 END) AS resigned_employees " +
                       "FROM employee " +
                       "WHERE YEAR(hire_date) = ? AND MONTH(hire_date) BETWEEN ? AND ? " +
                       "GROUP BY YEAR(hire_date), MONTH(hire_date) " +
                       "ORDER BY year, month";

        try (Connection connection = mySQL.getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, year);
            stmt.setInt(2, monthFrom);
            stmt.setInt(3, monthTo);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int yearResult = rs.getInt("year");
                    int month = rs.getInt("month");
                    int totalEmployees = rs.getInt("total_employees");
                    int newEmployees = rs.getInt("new_employees");
                    int resignedEmployees = rs.getInt("resigned_employees");
                    reportData.add(new Object[]{yearResult, month, totalEmployees, newEmployees, resignedEmployees});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportData;
    }
    


    public void exportToExcel(List<Object[]> data, String[] columnNames, String fileName) throws IOException {
        // Định dạng tên file
        String path = "D:/" + fileName;
        
        // Tiến hành xuất dữ liệu ra file Excel (sử dụng thư viện Apache POI hoặc các công cụ khác để thực hiện xuất dữ liệu)
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Report");

        // Tiến hành ghi dữ liệu vào sheet (ví dụ dùng Apache POI)
        XSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < columnNames.length; i++) {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(columnNames[i]);
        }

        // Ghi dữ liệu vào bảng
        int rowNum = 1;
        for (Object[] rowData : data) {
            XSSFRow row = sheet.createRow(rowNum++);
            for (int i = 0; i < rowData.length; i++) {
                row.createCell(i).setCellValue(rowData[i].toString());
            }
        }

        // Lưu file
        try (FileOutputStream fileOut = new FileOutputStream(path)) {
            workbook.write(fileOut);
        }

        workbook.close();
    }

    public boolean saveReport(int createdBy, String title, String results) {
        String sql = "INSERT INTO reports (created_by, title, created_date, results) VALUES (?, ?, ?, ?)";

        try (Connection conn = mySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, createdBy); // ID người tạo
            stmt.setString(2, title); // Tiêu đề báo cáo
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis())); // Ngày tạo
            stmt.setString(4, results); // Kết quả (ví dụ: "Đã lưu")

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu lưu thành công
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Trả về false nếu có lỗi
        }
    }


    
    public List<Object[]> getRecruitmentPerformanceReport() {
        List<Object[]> reportData = new ArrayList<>();
        String query = """
            SELECT 
                YEAR(i.interview_date) AS year,
                MONTH(i.interview_date) AS month,
                COUNT(i.id) AS total_applicants,
                SUM(CASE WHEN i.result = 'passed' THEN 1 ELSE 0 END) AS total_passed,
                SUM(CASE WHEN i.result = 'failed' THEN 1 ELSE 0 END) AS total_failed,
                AVG(DATEDIFF(jo.closing_date, jo.opening_date)) AS average_hiring_time
            FROM interviews i
            JOIN job_openings jo ON i.job_open_id = jo.id
            GROUP BY YEAR(i.interview_date), MONTH(i.interview_date)
            ORDER BY year, month
        """;

        try (Connection connection = mySQL.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

        	while (rs.next()) {
        	    int yearResult = rs.getInt("year");
        	    int month = rs.getInt("month");
        	    int totalApplicants = rs.getInt("total_applicants");
        	    int totalPassed = rs.getInt("total_passed");
        	    int totalFailed = rs.getInt("total_failed");
        	    double averageHiringTime = rs.getDouble("average_hiring_time");

        	    // Tính tỷ lệ chuyển đổi và tỷ lệ từ chối với điều kiện chặt chẽ
        	    double passRate = (totalApplicants > 0 && totalPassed <= totalApplicants) 
        	    	    ? (double) totalPassed / totalApplicants * 100 
        	    	    : 0;

        	    	double rejectRate = (totalApplicants > 0 && totalFailed <= totalApplicants) 
        	    	    ? (double) totalFailed / totalApplicants * 100 
        	    	    : 0;

        	    	// Đảm bảo tỷ lệ không bao giờ vượt quá 100%
        	    	passRate = Math.min(passRate, 100);
        	    	rejectRate = Math.min(rejectRate, 100);


        	    reportData.add(new Object[]{
        	        yearResult, month, totalApplicants, passRate, rejectRate, averageHiringTime
        	    });
        	}

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportData;
    }
    
    public List<Object[]> getRecruitmentPerformanceReport(int year, int fromMonth, int toMonth) {
        List<Object[]> reportData = new ArrayList<>();
        String query = """
            SELECT 
                YEAR(i.interview_date) AS year,
                MONTH(i.interview_date) AS month,
                COUNT(i.id) AS total_applicants,
                SUM(CASE WHEN i.result = 'passed' THEN 1 ELSE 0 END) AS total_passed,
                SUM(CASE WHEN i.result = 'failed' THEN 1 ELSE 0 END) AS total_failed,
                AVG(DATEDIFF(jo.closing_date, jo.opening_date)) AS average_hiring_time
            FROM interviews i
            JOIN job_openings jo ON i.job_open_id = jo.id
            WHERE YEAR(i.interview_date) = ? AND MONTH(i.interview_date) BETWEEN ? AND ?
            GROUP BY YEAR(i.interview_date), MONTH(i.interview_date)
            ORDER BY year, month
        """;

        try (Connection connection = mySQL.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            // Thiết lập các tham số
            stmt.setInt(1, year);        // Năm
            stmt.setInt(2, fromMonth);    // Tháng bắt đầu
            stmt.setInt(3, toMonth);      // Tháng kết thúc

            try (ResultSet rs = stmt.executeQuery()) {
            	while (rs.next()) {
            	    int yearResult = rs.getInt("year");
            	    int month = rs.getInt("month");
            	    int totalApplicants = rs.getInt("total_applicants");
            	    int totalPassed = rs.getInt("total_passed");
            	    int totalFailed = rs.getInt("total_failed");
            	    double averageHiringTime = rs.getDouble("average_hiring_time");

            	    // Tính tỷ lệ chuyển đổi và tỷ lệ từ chối với điều kiện chặt chẽ
            	    double passRate = (totalApplicants > 0 && totalPassed <= totalApplicants) 
            	    	    ? (double) totalPassed / totalApplicants * 100 
            	    	    : 0;

            	    	double rejectRate = (totalApplicants > 0 && totalFailed <= totalApplicants) 
            	    	    ? (double) totalFailed / totalApplicants * 100 
            	    	    : 0;

            	    	// Đảm bảo tỷ lệ không bao giờ vượt quá 100%
            	    	passRate = Math.min(passRate, 100);
            	    	rejectRate = Math.min(rejectRate, 100);


            	    reportData.add(new Object[]{
            	        yearResult, month, totalApplicants, passRate, rejectRate, averageHiringTime
            	    });
            	}

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportData;
    }

 // Phương thức lấy tất cả báo cáo từ cơ sở dữ liệu
    public List<Object[]> getAllReports() {
        List<Object[]> reportData = new ArrayList<>();
        String query = "SELECT id, created_by, title, created_date, results FROM reports";

        try (Connection connection = mySQL.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int createdBy = rs.getInt("created_by");
                String title = rs.getString("title");
                Date createdDate = rs.getDate("created_date");
                String results = rs.getString("results");

                // Thêm dữ liệu vào danh sách
                reportData.add(new Object[]{createdDate, createdBy, title, results});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportData;
    }
    
    public List<Object[]> getAllReports(int year, int monthFrom, int monthTo) {
        List<Object[]> reportData = new ArrayList<>();
        String query = """
            SELECT id, created_by, title, created_date, results 
            FROM reports 
            WHERE YEAR(created_date) = ? 
            AND MONTH(created_date) BETWEEN ? AND ?
            ORDER BY created_date
        """;

        try (Connection connection = mySQL.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            // Thiết lập các tham số
            stmt.setInt(1, year);         // Năm
            stmt.setInt(2, monthFrom);    // Tháng bắt đầu
            stmt.setInt(3, monthTo);      // Tháng kết thúc

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int createdBy = rs.getInt("created_by");
                    String title = rs.getString("title");
                    Date createdDate = rs.getDate("created_date");
                    String results = rs.getString("results");

                    // Thêm dữ liệu vào danh sách
                    reportData.add(new Object[]{createdDate, createdBy, title, results});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportData;
    }


    // Đóng kết nối
    public void closeConnection() throws SQLException {
        if (mySQL != null && !mySQL.isConnect()) {
		    mySQL.disConnect();
		}
    }
}

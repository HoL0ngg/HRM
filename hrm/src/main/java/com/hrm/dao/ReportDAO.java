package com.hrm.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
public class ReportDAO {
    private MySQLConnect mySQL = new MySQLConnect();

    // Phương thức lấy danh sách dữ liệu
    public List<Object[]> getEmployeeReport() {
        List<Object[]> reportData = new ArrayList<>();
        String query = "SELECT DATE_FORMAT(hire_date, '%M') AS month, " +
                       "COUNT(*) AS total_employees, " +
                       "SUM(CASE WHEN hire_date >= CURDATE() - INTERVAL 1 MONTH THEN 1 ELSE 0 END) AS new_employees, " +
                       "SUM(CASE WHEN status = 'off' THEN 1 ELSE 0 END) AS resigned_employees " +
                       "FROM employee " +
                       "GROUP BY DATE_FORMAT(hire_date, '%M')";  // Sửa chỗ này

        try (Connection connection = mySQL.getConnection(); 
             Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String month = rs.getString("month");
                int totalEmployees = rs.getInt("total_employees");
                int newEmployees = rs.getInt("new_employees");
                int resignedEmployees = rs.getInt("resigned_employees");
                reportData.add(new Object[]{month, totalEmployees, newEmployees, resignedEmployees});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportData;
    }


    public void exportToExcel(List<Object[]> data, String[] columnNames) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employee Report");

        // Tạo tiêu đề cột
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnNames[i]);
        }

        // Thêm dữ liệu vào sheet
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Object[] rowData = data.get(i);
            for (int j = 0; j < rowData.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(rowData[j].toString());
            }
        }

        // Lưu file
        try (FileOutputStream fileOut = new FileOutputStream("EmployeeReport.xlsx")) {
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }
    }
    
 // Phương thức để lưu báo cáo vào CSDL
    public boolean saveReportToDatabase(String month, int totalEmployees, int newEmployees, int resignedEmployees) {
        String sql = "INSERT INTO employee_report (month, total_employees, new_employees, resigned_employees) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = mySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, month);
            stmt.setInt(2, totalEmployees);
            stmt.setInt(3, newEmployees);
            stmt.setInt(4, resignedEmployees);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    

    // Đóng kết nối
    public void closeConnection() throws SQLException {
        if (mySQL != null && !mySQL.isConnect()) {
		    mySQL.disConnect();
		}
    }
}

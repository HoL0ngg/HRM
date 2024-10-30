//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hrm.dao;

import com.hrm.db.JDBCUtil;
import com.hrm.model.Employee;
import com.hrm.model.Position;
import com.hrm.model.Salary;
import com.hrm.model.Employee.Gender;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SalaryDAO implements DAOInterface<Salary> {
    public SalaryDAO() {
    }

    public static SalaryDAO getInstance() {
        return new SalaryDAO();
    }

    @Override
    public int them(Salary object) {
        throw new UnsupportedOperationException("Unimplemented method 'them'");
    }

    @Override
    public boolean xoa(Salary object) {
        throw new UnsupportedOperationException("Unimplemented method 'xoa'");
    }

    @Override
    public boolean capnhat(Salary object) {
        throw new UnsupportedOperationException("Unimplemented method 'capnhat'");
    }

    @Override
    public ArrayList<Salary> selectAll() {
        String sql = "SELECT s.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, p.id as position_id, p.name as position_name FROM salaries s JOIN employee e ON s.employee_id = e.id JOIN position p ON e.position_id = p.id";
        Connection con = JDBCUtil.createConnection();
        ArrayList<Salary> salaryList = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    BigDecimal positionSalary = rs.getBigDecimal("position_salary");
                    BigDecimal bonus = rs.getBigDecimal("bonus");
                    BigDecimal deductions = rs.getBigDecimal("deductions");
                    BigDecimal netSalary = rs.getBigDecimal("net_salary");
                    BigDecimal overtimeSalary = rs.getBigDecimal("overtime_salary");
                    LocalDate payday = (LocalDate) rs.getObject("payday", LocalDate.class);
                    String note = rs.getString("note");
                    int attendance = rs.getInt("attendance");
                    int employeeId = rs.getInt("employee_id");
                    String employeeName = rs.getString("employee_name");
                    String genderStr = rs.getString("gender").toLowerCase();
                    Employee.Gender gender = Gender.valueOf(genderStr);
                    String phoneNumber = rs.getString("phone_number");
                    Employee employee = new Employee();
                    employee.setId(employeeId);
                    employee.setName(employeeName);
                    employee.setGender(gender);
                    employee.setPhone_mumber(phoneNumber);
                    int positionId = rs.getInt("position_id");
                    String positionName = rs.getString("position_name");
                    Position position = new Position(positionId, 0, positionName);
                    Salary salary = new Salary(id, employee, positionSalary, bonus, deductions, netSalary,
                            overtimeSalary, payday, note, attendance, position);
                    salaryList.add(salary);
                }
            } catch (Throwable var31) {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (Throwable var30) {
                        var31.addSuppressed(var30);
                    }
                }

                throw var31;
            }

            if (pst != null) {
                pst.close();
            }
        } catch (SQLException var32) {
            SQLException e = var32;
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return salaryList;
    }

    public Salary selectByEmployeeID(int employeeId) {
        String sql = "SELECT * FROM salaries WHERE employee_id = ?";
        Connection con = JDBCUtil.createConnection();
        Salary salary = null;

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                pst.setInt(1, employeeId);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    BigDecimal positionSalary = rs.getBigDecimal("position_salary");
                    BigDecimal bonus = rs.getBigDecimal("bonus");
                    BigDecimal deductions = rs.getBigDecimal("deductions");
                    BigDecimal netSalary = rs.getBigDecimal("net_salary");
                    BigDecimal overtimeSalary = rs.getBigDecimal("overtime_salary");
                    LocalDate payday = (LocalDate) rs.getObject("payday", LocalDate.class);
                    String note = rs.getString("note");
                    int attendance = rs.getInt("attendance");
                    salary = new Salary(id, employeeId, positionSalary, bonus, deductions, netSalary, overtimeSalary,
                            payday, note, attendance);
                }
            } catch (Throwable var22) {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (Throwable var21) {
                        var22.addSuppressed(var21);
                    }
                }

                throw var22;
            }

            if (pst != null) {
                pst.close();
            }
        } catch (SQLException var23) {
            SQLException e = var23;
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return salary;
    }

    @Override
    public Salary selectByID(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<Salary> selectByMonth(int month) {
        String sql = "SELECT s.*, e.id as employee_id, e.name as employee_name, e.gender, e.phone_number, p.id as position_id, p.name as position_name FROM salaries s JOIN employee e ON s.employee_id = e.id JOIN position p ON e.position_id = p.id WHERE MONTH(s.payday) = ?";
        Connection con = JDBCUtil.createConnection();
        ArrayList<Salary> salaryList = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                pst.setInt(1, month);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    BigDecimal positionSalary = rs.getBigDecimal("position_salary");
                    BigDecimal bonus = rs.getBigDecimal("bonus");
                    BigDecimal deductions = rs.getBigDecimal("deductions");
                    BigDecimal netSalary = rs.getBigDecimal("net_salary");
                    BigDecimal overtimeSalary = rs.getBigDecimal("overtime_salary");
                    LocalDate payday = (LocalDate) rs.getObject("payday", LocalDate.class);
                    String note = rs.getString("note");
                    int attendance = rs.getInt("attendance");
                    int employeeId = rs.getInt("employee_id");
                    String employeeName = rs.getString("employee_name");
                    String genderStr = rs.getString("gender").toLowerCase();
                    Employee.Gender gender = Gender.valueOf(genderStr);
                    String phoneNumber = rs.getString("phone_number");
                    Employee employee = new Employee();
                    employee.setId(employeeId);
                    employee.setName(employeeName);
                    employee.setGender(gender);
                    employee.setPhone_mumber(phoneNumber);
                    int positionId = rs.getInt("position_id");
                    String positionName = rs.getString("position_name");
                    Position position = new Position(positionId, 0, positionName);
                    Salary salary = new Salary(id, employee, positionSalary, bonus, deductions, netSalary,
                            overtimeSalary, payday, note, attendance, position);
                    salaryList.add(salary);
                }
            } catch (Throwable var32) {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (Throwable var31) {
                        var32.addSuppressed(var31);
                    }
                }

                throw var32;
            }

            if (pst != null) {
                pst.close();
            }
        } catch (SQLException var33) {
            SQLException e = var33;
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return salaryList;
    }

}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hrm.dao;

import com.hrm.db.JDBCUtil;
import com.hrm.model.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartmentDAO implements DAOInterface<Department> {
    public DepartmentDAO() {
    }

    public static DepartmentDAO getInstance() {
        return new DepartmentDAO();
    }

    public int them(Department object) {
        throw new UnsupportedOperationException("Unimplemented method 'them'");
    }

    public boolean xoa(Department object) {
        throw new UnsupportedOperationException("Unimplemented method 'xoa'");
    }

    public boolean capnhat(Department object) {
        throw new UnsupportedOperationException("Unimplemented method 'capnhat'");
    }

    public ArrayList<Department> selectAll() {
        String sql = "SELECT * FROM departments";
        Connection con = JDBCUtil.createConnection();
        ArrayList<Department> departmentList = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                ResultSet rs = pst.executeQuery();

                while(rs.next()) {
                    int id = rs.getInt("id");
                    int managerId = rs.getInt("manager_id");
                    String name = rs.getString("name");
                    Department department = new Department(id, managerId, name);
                    departmentList.add(department);
                }
            } catch (Throwable var16) {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (Throwable var15) {
                        var16.addSuppressed(var15);
                    }
                }

                throw var16;
            }

            if (pst != null) {
                pst.close();
            }
        } catch (SQLException var17) {
            SQLException e = var17;
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return departmentList;
    }

    @Override
    public Department selectByID(int id) {
        String sql = "SELECT * FROM departments WHERE id = ?";
        Connection con = JDBCUtil.createConnection();
        Department department = null;

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            try {
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    // Lấy giá trị từ ResultSet
                    String name = rs.getString("name");

                    // Tạo đối tượng Department với id và name
                    department = new Department();
                }
            } catch (Throwable var24) {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (Throwable var23) {
                        var24.addSuppressed(var23);
                    }
                }

                throw var24;
            }

            if (pst != null) {
                pst.close();
            }
        } catch (SQLException var25) {
            var25.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }

        return department;
    }
    
    public boolean updateManager(int departmentId, int managerId) {
        String sql = "UPDATE departments SET manager_id = ? WHERE id = ?";
        try (Connection conn = JDBCUtil.createConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, managerId);
            pst.setInt(2, departmentId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeManager(int departmentId) {
        String sql = "UPDATE departments SET manager_id = NULL WHERE id = ?";
        try (Connection conn = JDBCUtil.createConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, departmentId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Integer getManagerIdByDepartment(int departmentId) {
        String sql = "SELECT manager_id FROM departments WHERE id = ?";
        try (Connection con = JDBCUtil.createConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, departmentId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("manager_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

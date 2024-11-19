package com.hrm.dao;

import com.hrm.db.JDBCUtil;
import com.hrm.model.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PositionDAO {

    // Singleton pattern
    private static PositionDAO instance;

    private PositionDAO() {}

    public static PositionDAO getInstance() {
        if (instance == null) {
            instance = new PositionDAO();
        }
        return instance;
    }

    /**
     * Thêm vị trí mới vào cơ sở dữ liệu.
     * @param position Đối tượng Position cần thêm.
     * @return true nếu thêm thành công, ngược lại false.
     */
    public boolean add(Position position) {
        String sql = "INSERT INTO position (id, department_id, name) VALUES (?, ?, ?)";
        try (Connection con = JDBCUtil.createConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, position.getId());
            pst.setInt(2, position.getDepartmentId());
            pst.setString(3, position.getName());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Lấy danh sách tất cả các vị trí.
     * @return ArrayList chứa các đối tượng Position.
     */
    public ArrayList<Position> getAll() {
        ArrayList<Position> positions = new ArrayList<>();
        String sql = "SELECT * FROM position";
        try (Connection con = JDBCUtil.createConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Position position = new Position(
                        rs.getInt("id"),
                        rs.getInt("department_id"),
                        rs.getString("name")
                );
                positions.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positions;
    }

    /**
     * Lấy danh sách vị trí theo ID phòng ban.
     * @param departmentId ID của phòng ban.
     * @return ArrayList chứa các vị trí trong phòng ban đó.
     */
    public ArrayList<Position> getPositionsByDepartmentId(int departmentId) {
        ArrayList<Position> positions = new ArrayList<>();
        String sql = "SELECT * FROM position WHERE department_id = ?";
        try (Connection con = JDBCUtil.createConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, departmentId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Position position = new Position(
                        rs.getInt("id"),
                        rs.getInt("department_id"),
                        rs.getString("name")
                );
                positions.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positions;
    }

    /**
     * Kiểm tra xem ID vị trí có tồn tại hay không.
     * @param positionId ID của vị trí.
     * @return true nếu tồn tại, ngược lại false.
     */
    public boolean isPositionExists(int positionId) {
        String sql = "SELECT COUNT(*) FROM position WHERE id = ?";
        try (Connection con = JDBCUtil.createConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, positionId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(Position position) {
        String sql = "UPDATE position SET department_id = ?, name = ? WHERE id = ?";
        try (Connection con = JDBCUtil.createConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, position.getDepartmentId());
            pst.setString(2, position.getName());
            pst.setInt(3, position.getId());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

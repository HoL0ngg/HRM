package com.hrm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.hrm.db.JDBCUtil;
import com.hrm.model.TimeKeeping;
import com.hrm.model.TimeKeeping.Status;

public class TimeKeepingDAO {
    public static TimeKeepingDAO getInstance() {
        return new TimeKeepingDAO();
    }

    public ArrayList<TimeKeeping> selectAll() {
        ArrayList<TimeKeeping> arr = new ArrayList<>();
        String sql = "select * from timekeeping";
        Connection con = JDBCUtil.createConnection();
        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int employee_id = rs.getInt("emloyee_id");
                LocalDate date = rs.getObject("date", LocalDate.class);
                LocalTime check_in = rs.getObject("check_in_time", LocalTime.class);
                LocalTime check_out = rs.getObject("check_out_time", LocalTime.class);
                Status status = Status.valueOf(rs.getString("status"));
                arr.add(new TimeKeeping(id, employee_id, date, check_in, check_out, status));
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        JDBCUtil.closeConnection(con);
        return arr;
    }
}

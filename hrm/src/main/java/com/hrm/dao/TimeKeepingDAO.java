//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hrm.dao;

import com.hrm.db.JDBCUtil;
import com.hrm.model.TimeKeeping;
import com.hrm.model.TimeKeeping.Status;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TimeKeepingDAO {
    public static TimeKeepingDAO getInstance() {
        return new TimeKeepingDAO();
    }

    public ArrayList<TimeKeeping> selectAll() {
<<<<<<< HEAD
        ArrayList<TimeKeeping> arr = new ArrayList();
=======
        ArrayList<TimeKeeping> arr = new ArrayList<TimeKeeping>();
>>>>>>> 484d70c9ef6a49ac09b9838e97d19b1e1452577f
        String sql = "select * from timekeeping";
        Connection con = JDBCUtil.createConnection();

        try {
            Statement st = con.createStatement();

            try {
                ResultSet rs = st.executeQuery(sql);

<<<<<<< HEAD
                while(true) {
=======
                while (true) {
>>>>>>> 484d70c9ef6a49ac09b9838e97d19b1e1452577f
                    if (!rs.next()) {
                        rs.close();
                        break;
                    }

                    int id = rs.getInt("id");
                    int employee_id = rs.getInt("employee_id");
<<<<<<< HEAD
                    LocalDate date = (LocalDate)rs.getObject("date", LocalDate.class);
                    LocalTime check_in = (LocalTime)rs.getObject("check_in_time", LocalTime.class);
                    LocalTime check_out = (LocalTime)rs.getObject("check_out_time", LocalTime.class);
=======
                    LocalDate date = (LocalDate) rs.getObject("date", LocalDate.class);
                    LocalTime check_in = (LocalTime) rs.getObject("check_in_time", LocalTime.class);
                    LocalTime check_out = (LocalTime) rs.getObject("check_out_time", LocalTime.class);
>>>>>>> 484d70c9ef6a49ac09b9838e97d19b1e1452577f
                    TimeKeeping.Status status = Status.valueOf(rs.getString("status"));
                    arr.add(new TimeKeeping(id, employee_id, date, check_in, check_out, status));
                }
            } catch (Throwable var13) {
                if (st != null) {
                    try {
                        st.close();
                    } catch (Throwable var12) {
                        var13.addSuppressed(var12);
                    }
                }

                throw var13;
            }

            if (st != null) {
                st.close();
            }
        } catch (Exception var14) {
            Exception e = var14;
            e.printStackTrace();
        }

        JDBCUtil.closeConnection(con);
        return arr;
    }
}

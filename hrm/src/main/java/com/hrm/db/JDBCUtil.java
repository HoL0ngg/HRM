package com.hrm.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// import com.mysql.cj.jdbc.Driver;

public class JDBCUtil {
    public static Connection createConnection() {
        Connection con = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mySQL://localhost:3306/testjava";
            String user = "root";
            String pass = "";
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
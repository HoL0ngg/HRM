package com.hrm.db;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public JDBCUtil() {
    }

    public static Connection createConnection() {
        Connection con = null;

        try {
            DriverManager.registerDriver(new Driver());
            String url = "jdbc:mySQL://localhost:3306/quanlynhansuvu";
            String user = "root";
            String pass = "";
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException var4) {
            SQLException e = var4;
            e.printStackTrace();

            System.out.println("3igZeus: connect faild " + var4);
        }

        return con;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException var2) {
            SQLException e = var2;
            e.printStackTrace();
        }

    }
}

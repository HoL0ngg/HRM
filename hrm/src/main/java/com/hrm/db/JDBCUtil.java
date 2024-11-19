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
<<<<<<< HEAD
            String url = "jdbc:mySQL://localhost:3306/quanlynhansu?useUnicode=true&characterEncoding=UTF-8";
=======
            String url = "jdbc:mySQL://localhost:3306/quanlynhansu";
>>>>>>> 484d70c9ef6a49ac09b9838e97d19b1e1452577f
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

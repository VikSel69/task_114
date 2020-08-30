package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getMySQLConnection() throws SQLException {
        String hostName = "localhost";
        String dbName = "testbd";
        String userName = "root";
        String password = "root";
        return getMySQLConnection(hostName, dbName, userName, password);
    }
    private static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException {
        String conUrl = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?verifyServerCertificate=false&useSSL=false&requireSSL=false&serverTimezone=UTC";
        return DriverManager.getConnection(conUrl, userName, password);
    }

}

package com.news.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=WebNewsProject;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
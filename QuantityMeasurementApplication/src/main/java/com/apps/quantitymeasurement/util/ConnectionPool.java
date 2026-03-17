package com.apps.quantitymeasurement.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
    public static final String URL = "jdbc:mysql://localhost:3306/quantityMeasurementDB";
    public static final String USER = "root";
    public static final String PASSWORD = "12345678";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}

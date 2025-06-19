package com.JobPortal.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/job_portal";
    private static final String USER = "root";
    private static final String PASSWORD = "Water@123";

    static {
        try {
            //Step - 1 loading the driver.
            //Class.forName loads the class provided in parameters.
            //'com.mysql.jdbc.driver' class contains a static method which registers mysql driver.
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver once
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        //Step - 2 Establishing connection with database.
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

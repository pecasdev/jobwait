package com.jobwait.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLExample {
    public static boolean dbTest(String jdbcUrl, String dbUser, String dbPassword) throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT USER");

        // while (resultSet.next())
        // {
        //     String columnValue = resultSet.getString("column_name");
        //     System.out.println("Column Value: " + columnValue);
        // }

        // resultSet.close();
        // statement.close();
        // connection.close();

        //resultSet.next(); // uncomment this to verify that database failure results in error message
        Boolean rowIsValid = resultSet.next();
        if (rowIsValid) {
            System.out.println("database works fine :)");
        } else {
            System.err.println("DATABASE PROBLEM, FIX IT >:(");
        }    
        return rowIsValid;
    }
}

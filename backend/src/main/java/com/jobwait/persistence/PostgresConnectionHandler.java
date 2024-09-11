package com.jobwait.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jobwait.fault.FaultException;

public class PostgresConnectionHandler {
    private String dbUser = "postgres";
    private String dbPassword = "password";

    private static ArrayList<String> urls = new ArrayList<String>(List.of(
            "jdbc:postgresql://database:5432/mydatabase", // inside docker-compose
            "jdbc:postgresql://localhost:5432/mydatabase" // outside docker-compose
    ));

    private static String knownWorkingUrl = urls.getFirst();

    public Connection getConnection() throws FaultException {
        try {
            return DriverManager.getConnection(knownWorkingUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            return getFirstConnectionFromJdbcUrls(new ArrayList<String>(urls));
        }
    }

    private Connection getFirstConnectionFromJdbcUrls(ArrayList<String> urls) throws FaultException {
        if (urls.isEmpty()) {
            throw DatabaseFaults.DatabaseGetConnectionFault();
        }

        String jdbcUrl = urls.getFirst();

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            knownWorkingUrl = jdbcUrl;
            return connection;
        } catch (SQLException e) {
            urls.remove(0);
            return getFirstConnectionFromJdbcUrls(urls);
        }
    }
}

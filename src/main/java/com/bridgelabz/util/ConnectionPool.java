package com.bridgelabz.util;

import com.bridgelabz.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    private static ConnectionPool instance;

    private final String url;
    private final String username;
    private final String password;

    private ConnectionPool() {
        try {
            String driver = ApplicationConfig.getProperty("db.driver");
            Class.forName(driver);

            this.url = ApplicationConfig.getProperty("db.url");
            this.username = ApplicationConfig.getProperty("db.username");
            this.password = ApplicationConfig.getProperty("db.password");

        } catch (ClassNotFoundException e) {
            throw new DatabaseException("Database driver not found", e);
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to get database connection", e);
        }
    }
}
package com.ecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections.
 */

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Walexsai.00";

    /**
     * Retrieves a connection to the database.
     *
     * @return a {@link Connection} object for the database.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
package org.example

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/linketinder"
    private static final String USER = "postgres"
    private static final String PASSWORD = "senha"
    private static Connection connection

    static {
        try {
            // Carregar o driver JDBC
            Class.forName("org.postgresql.Driver")
        } catch (ClassNotFoundException e) {
            e.printStackTrace()
        }
    }

    static Connection getConnection() {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD)
            } catch (SQLException e) {
                e.printStackTrace()
            }
        }
        return connection
    }

    static void closeConnection() {
        if (connection != null && !connection.isClosed()) {
            try {
                connection.close()
            } catch (SQLException e) {
                e.printStackTrace()
            }
        }
    }
}

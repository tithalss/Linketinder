package org.example.ClassesDAO

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/linketinder"
    private static final String USER = "postgres"
    private static final String PASSWORD = "senha"
    private static final String H2_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"
    private static Connection connection

    static {
        try {
            // Carrega os drivers do BD
            Class.forName("org.postgresql.Driver")
            Class.forName("org.h2.Driver")
        } catch (ClassNotFoundException e) {
            e.printStackTrace()
        }
    }

    static void setTestDatabase() {
        connection = null
        connection = DriverManager.getConnection(H2_URL, "sa", "")
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

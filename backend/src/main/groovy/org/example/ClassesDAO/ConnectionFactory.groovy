package org.example.ClassesDAO

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/linketinder"
    private static final String USER = "postgres"
    private static final String PASSWORD = "senha"

    private static Connection connection

    private ConnectionFactory() {}

    static {
        try {
            Class.forName("org.postgresql.Driver")
        } catch (ClassNotFoundException e) {
            e.printStackTrace()
            throw new RuntimeException("Driver do PostgreSQL não encontrado!", e)
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

    static Connection connectionFactory(String url, String user, String password) {
        Connection newConnection = null
        try {
            newConnection = DriverManager.getConnection(url, user, password)
        } catch (SQLException e) {
            e.printStackTrace()
        }
        return newConnection
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

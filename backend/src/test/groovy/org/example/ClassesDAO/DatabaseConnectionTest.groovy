package org.example.ClassesDAO

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.Connection

class DatabaseConnectionTest {

    private Connection connection

    @BeforeEach
    void setUp() {
        connection = DatabaseConnection.getConnection()
    }

    @AfterEach
    void tearDown() {
        DatabaseConnection.closeConnection()
    }

    @Test
    void testConnectionIsNotNull() {
        Assertions.assertNotNull(connection, "A conexão não deve ser nula.")
    }

    @Test
    void testConnectionIsOpen() {
        Assertions.assertFalse(connection.isClosed(), "A conexão deve estar aberta.")
    }

    @Test
    void testCloseConnection() {
        DatabaseConnection.closeConnection()
        Assertions.assertTrue(connection.isClosed(), "A conexão deve estar fechada após ser fechada.")
    }

    @Test
    void testMultipleConnections() {
        DatabaseConnection.closeConnection()

        Connection newConnection = DatabaseConnection.getConnection()
        Assertions.assertNotEquals(connection, newConnection, "As conexões devem ser diferentes após a primeira conexão ser fechada.")

        Assertions.assertFalse(newConnection.isClosed(), "A nova conexão deve estar aberta.")

        DatabaseConnection.closeConnection()
    }
}

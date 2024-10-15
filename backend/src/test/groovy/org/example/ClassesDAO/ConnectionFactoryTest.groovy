package org.example.ClassesDAO

import org.junit.jupiter.api.*

import java.sql.Connection

class ConnectionFactoryTest {

    private Connection connection

    @BeforeEach
    void setUp() {
        connection = ConnectionFactory.getConnection()
    }

    @AfterEach
    void tearDown() {
        ConnectionFactory.closeConnection()
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
        ConnectionFactory.closeConnection()
        Assertions.assertTrue(connection.isClosed(), "A conexão deve estar fechada após ser fechada.")
    }

    @Test
    void testMultipleConnections() {
        ConnectionFactory.closeConnection()

        Connection newConnection = ConnectionFactory.getConnection()
        Assertions.assertNotEquals(connection, newConnection, "As conexões devem ser diferentes após a primeira conexão ser fechada.")

        Assertions.assertFalse(newConnection.isClosed(), "A nova conexão deve estar aberta.")

        ConnectionFactory.closeConnection()
    }
}

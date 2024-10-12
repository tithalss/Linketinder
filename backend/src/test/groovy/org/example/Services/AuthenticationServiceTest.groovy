package org.example.Services

import org.example.ClassesDAO.DatabaseConnection
import org.junit.jupiter.api.*
import java.sql.*

import static org.junit.jupiter.api.Assertions.*

class AuthenticationServiceTest {

    private Connection connection
    private AuthenticationService authenticationService
    private final String testEmail = "test@example.com"
    private final String testPassword = "password123"

    @BeforeAll
    static void setupAll() {
        DatabaseConnection.setTestDatabase()
    }

    @BeforeEach
    void setUp() {
        connection = DatabaseConnection.getConnection()
        createTable()
        cleanUpTestUser(testEmail)
        createTestUser(testEmail, testPassword)
        authenticationService = new AuthenticationService(connection)
    }

    @AfterEach
    void tearDown() {
        cleanUpTestUser(testEmail)
        if (connection != null && !connection.isClosed()) {
            DatabaseConnection.closeConnection()
        } else {
            System.out.println("Connection is closed or null.")
        }
    }

    @Test
    void testAuthenticateSuccess() {
        Integer userId = authenticationService.authenticate(testEmail, testPassword)
        assertNotNull(userId, "O ID do usuário não deve ser nulo em caso de autenticação bem-sucedida.")
    }

    @Test
    void testAuthenticateFailure() {
        Integer userId = authenticationService.authenticate(testEmail, "wrongpassword")
        assertNull(userId, "O ID do usuário deve ser nulo em caso de falha na autenticação.")
    }

    @Test
    void testAuthenticateWithNonexistentUser() {
        Integer userId = authenticationService.authenticate("nonexistent@example.com", testPassword)
        assertNull(userId, "O ID do usuário deve ser nulo para um usuário inexistente.")
    }

    private void createTable() {
        String sql = """
        CREATE TABLE IF NOT EXISTS candidatos (
            id SERIAL PRIMARY KEY,
            nome VARCHAR(100),
            data_nascimento DATE,
            email VARCHAR(100) UNIQUE,
            cpf VARCHAR(11),
            pais VARCHAR(50),
            cep VARCHAR(10),
            cargo VARCHAR(50),
            descricao TEXT,
            senha VARCHAR(100)
        )
    """
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    private void createTestUser(String email, String senha) {
        String sql = "INSERT INTO candidatos (nome, data_nascimento, email, cpf, pais, cep, cargo, descricao, senha) " +
                "VALUES ('Test User', '1990-01-01', ?, '12345678901', 'Brazil', '12345678', 'Developer', 'A test user.', ?)"
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, email)
            preparedStatement.setString(2, senha)
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    private void cleanUpTestUser(String email) {
        String sql = "DELETE FROM candidatos WHERE email = ?"
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, email)
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }
}

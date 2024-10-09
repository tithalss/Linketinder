package org.example.Services

import org.example.ClassesDAO.DatabaseConnection
import org.junit.jupiter.api.*
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

import static org.junit.jupiter.api.Assertions.*

class AuthenticationServiceTest {

    private Connection connection
    private final String testEmail = "test@example.com"
    private final String testPassword = "password123"

    @BeforeEach
    void setUp() {
        connection = DatabaseConnection.getConnection()
        cleanUpTestUser(testEmail)
        createTestUser(testEmail, testPassword)
    }

    @AfterEach
    void tearDown() {
        Connection connection = DatabaseConnection.getConnection()
        if (connection != null && !connection.isClosed()) {
            DatabaseConnection.closeConnection()
        } else {
            System.out.println("Connection is closed or null.")
        }
    }

    @Test
    void testAuthenticateSuccess() {
        Integer userId = AuthenticationService.authenticate(testEmail, testPassword)
        assertNotNull(userId, "O ID do usuário não deve ser nulo em caso de autenticação bem-sucedida.")
    }

    @Test
    void testAuthenticateFailure() {
        Integer userId = AuthenticationService.authenticate(testEmail, "wrongpassword")
        assertNull(userId, "O ID do usuário deve ser nulo em caso de falha na autenticação.")
    }

    @Test
    void testAuthenticateWithNonexistentUser() {
        Integer userId = AuthenticationService.authenticate("nonexistent@example.com", testPassword)
        assertNull(userId, "O ID do usuário deve ser nulo para um usuário inexistente.")
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

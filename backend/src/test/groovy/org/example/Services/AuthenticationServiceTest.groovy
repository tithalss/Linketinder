package org.example.Services


import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import static org.junit.jupiter.api.Assertions.assertNotNull
import static org.junit.jupiter.api.Assertions.assertNull
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.*

class AuthenticationServiceTest {

    private Connection mockConnection
    private AuthenticationService authenticationService
    private PreparedStatement mockPreparedStatement
    private ResultSet mockResultSet

    private final String testEmail = "test@example.com"
    private final String testPassword = "senha"

    @BeforeEach
    void setUp() {
        mockConnection = mock(Connection.class)
        mockPreparedStatement = mock(PreparedStatement.class)
        mockResultSet = mock(ResultSet.class)
        authenticationService = new AuthenticationService(mockConnection)
    }

    @Test
    void testAuthenticateSuccess() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement)
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet)
        when(mockResultSet.next()).thenReturn(true)
        when(mockResultSet.getInt("id")).thenReturn(1)

        Integer userId = authenticationService.authenticate(testEmail, testPassword)
        assertNotNull(userId, "O ID do usuário não deve ser nulo em caso de autenticação bem-sucedida.")

        verify(mockPreparedStatement).setString(1, testEmail)
        verify(mockPreparedStatement).setString(2, testPassword)
        verify(mockPreparedStatement).executeQuery()
    }

    @Test
    void testAuthenticateFailure() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement)
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet)
        when(mockResultSet.next()).thenReturn(false)

        Integer userId = authenticationService.authenticate(testEmail, "senhaerrada")
        assertNull(userId, "O ID do usuário deve ser nulo em caso de falha na autenticação.")

        verify(mockPreparedStatement).setString(1, testEmail)
        verify(mockPreparedStatement).setString(2, "senhaerrada")
        verify(mockPreparedStatement).executeQuery()
    }

    @Test
    void testAuthenticateWithNonexistentUser() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement)
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet)
        when(mockResultSet.next()).thenReturn(false)

        Integer userId = authenticationService.authenticate("naoexiste@example.com", testPassword)
        assertNull(userId, "O ID do usuário deve ser nulo para um usuário inexistente.")

        verify(mockPreparedStatement).setString(1, "naoexiste@example.com")
        verify(mockPreparedStatement).setString(2, testPassword)
        verify(mockPreparedStatement).executeQuery()
    }
}

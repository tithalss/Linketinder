package org.example.Controllers

import org.example.ClassesDAO.CandidateDAO
import org.example.Models.Candidate
import org.example.Services.AuthenticationService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.time.LocalDate

import static org.mockito.Mockito.*

class LoginControllerTest {

    @Mock
    AuthenticationService authenticationService

    @Mock
    CandidateDAO candidateDAO

    @InjectMocks
    LoginController loginController

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    void testHandleLogin_ValidCredentials() {
        String email = "candidate@example.com"
        String senha = "password123"
        int userId = 1
        Candidate candidate = new Candidate(1, "João Silva", LocalDate.of(1990, 5, 20), email, "12345678900", "Brasil", "12345-678", "Desenvolvedor", "Software developer", "password123")

        when(authenticationService.authenticate(email, senha)).thenReturn(userId)
        when(candidateDAO.getById(userId)).thenReturn(candidate)

        Candidate result = loginController.handleLogin(email, senha)

        assert result == candidate
        verify(authenticationService, times(1)).authenticate(email, senha)
        verify(candidateDAO, times(1)).getById(userId)
    }

    @Test
    void testHandleLogin_InvalidCredentials() {
        String email = "candidate@example.com"
        String senha = "wrongpassword"

        when(authenticationService.authenticate(email, senha)).thenReturn(null)

        Candidate result = loginController.handleLogin(email, senha)

        assert result == null
        verify(authenticationService, times(1)).authenticate(email, senha)
        verify(candidateDAO, never()).getById(anyInt())
    }
}

package org.example.Views

import java.time.LocalDate
import org.example.Controllers.LoginController
import org.example.Models.Candidate
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class LoginViewTest {

    private LoginController loginController
    private LoginView loginView

    @BeforeEach
    void setup() {
        loginController = Mockito.mock(LoginController)
        loginView = new LoginView(loginController)
    }

    @Test
    void testDisplayLoginSuccessful() {
        String email = "test@example.com"
        String senha = "senha123"
        Candidate candidate = new Candidate(1, "Junin Souza", LocalDate.of(1902, 03, 12), email, "07054867218", "Austrália", "71996242", "Biólogo marinho", "O pai nada com tubarão", senha)

        Mockito.when(loginController.handleLogin(email, senha)).thenReturn(candidate)

        Integer id = loginView.displayLogin(email, senha)

        assert id == candidate.getId()
        Mockito.verify(loginController, Mockito.times(1)).handleLogin(email, senha)
    }

    @Test
    void testDisplayLoginFailed() {
        String email = "wrong@example.com"
        String senha = "wrongpassword"

        Mockito.when(loginController.handleLogin(email, senha)).thenReturn(null)

        Integer id = loginView.displayLogin(email, senha)

        assert id == null
        Mockito.verify(loginController, Mockito.times(1)).handleLogin(email, senha)
    }
}

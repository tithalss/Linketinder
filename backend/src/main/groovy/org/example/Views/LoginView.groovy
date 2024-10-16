package org.example.Views

import org.example.Controllers.LoginController
import org.example.Models.Candidate

class LoginView {

    private final LoginController loginController

    LoginView(LoginController loginController) {
        this.loginController = loginController
    }

    Integer displayLogin(String email, String senha) {
        Candidate candidate = loginController.handleLogin(email, senha)

        if (candidate != null) {
            System.out.println("Login efetuado!\n Bem-vindo, " + candidate.getNomeCompleto())
            return candidate.getId()
        } else {
            System.out.println("Credenciais inválida. Por favor, tente novamente.")
            return null
        }
    }
}

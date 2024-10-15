package org.example.Controllers

import org.example.ClassesDAO.CandidateDAO
import org.example.Models.Candidate
import org.example.Services.AuthenticationService

public class LoginController {
    private final AuthenticationService authenticationService
    private final CandidateDAO candidateDAO

    LoginController(AuthenticationService authenticationService, CandidateDAO candidateDAO) {
        this.authenticationService = authenticationService
        this.candidateDAO = candidateDAO
    }

    Candidate handleLogin(String email, String senha) {
        Integer userId = authenticationService.authenticate(email, senha)

        if (userId != null) {
            return candidateDAO.getById(userId)
        } else {
            return null
        }
    }
}

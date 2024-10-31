package org.example.Controllers

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.ClassesDAO.CandidateDAO
import org.example.ClassesDAO.ConnectionFactory
import org.example.Models.Candidate
import org.example.Services.AuthenticationService

class LoginController extends HttpServlet {
    private final AuthenticationService authenticationService
    private final CandidateDAO candidateDAO

    LoginController() {
        this.authenticationService = new AuthenticationService(ConnectionFactory.getConnection())
        this.candidateDAO = new CandidateDAO(ConnectionFactory.getConnection())
    }

    LoginController(AuthenticationService authenticationService, CandidateDAO candidateDAO) {
        this.authenticationService = authenticationService
        this.candidateDAO = candidateDAO
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String json = request.reader.text
            def jsonMap = new JsonSlurper().parseText(json)

            String email = jsonMap.email
            String senha = jsonMap.senha

            Integer userId = authenticationService.authenticate(email, senha)

            if (userId != null) {
                Candidate candidate = candidateDAO.getById(userId)

                response.contentType = "application/json"
                response.status = HttpServletResponse.SC_OK
                response.writer.write(JsonOutput.toJson(candidate))
            } else {
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.writer.write("{\"error\":\"Email ou senha inválidos\"}")
            }
        } catch (Exception e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("Erro ao processar login: ${e.message}")
        }
    }
}

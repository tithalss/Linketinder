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

import java.time.LocalDate

class CandidateController extends HttpServlet {
    private final CandidateDAO candidateDAO

    CandidateController() {
        this.candidateDAO = new CandidateDAO(ConnectionFactory.getConnection())
    }

    CandidateController(CandidateDAO candidateDAO) {
        this.candidateDAO = candidateDAO
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String json = request.reader.text
            def jsonMap = new JsonSlurper().parseText(json)

            String nomeCompleto = jsonMap.nomeCompleto
            LocalDate dataNascimento = LocalDate.parse(jsonMap.dataNascimento)
            String email = jsonMap.email
            String cpf = jsonMap.cpf
            String pais = jsonMap.pais
            String cep = jsonMap.cep
            String cargo = jsonMap.cargo
            String descricao = jsonMap.descricao
            String senha = jsonMap.senha

            Candidate candidate = new Candidate(nomeCompleto, dataNascimento, email, cpf, pais, cep, cargo, descricao, senha)

            candidateDAO.create(candidate)
            response.status = HttpServletResponse.SC_CREATED
            response.writer.write("Candidato criado com sucesso.")

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("Erro ao criar o candidato: ${e.message}")
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id")
            if (!idParam) {
                response.status = HttpServletResponse.SC_BAD_REQUEST
                response.writer.write("ID do candidato não fornecido.")
                return
            }

            int id = Integer.parseInt(idParam)
            Candidate candidate = candidateDAO.getById(id)

            if (candidate != null) {
                response.contentType = "application/json"
                response.writer.write(JsonOutput.toJson(candidate))
            } else {
                response.status = HttpServletResponse.SC_NOT_FOUND
                response.writer.write("Candidato não encontrado.")
            }

        } catch (NumberFormatException e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("ID inválido: ${e.message}")

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            response.writer.write("Erro ao buscar candidato: ${e.message}")
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String json = request.reader.text
            def jsonMap = new JsonSlurper().parseText(json)

            int id = jsonMap.id
            String nomeCompleto = jsonMap.nomeCompleto
            LocalDate dataNascimento = LocalDate.parse(jsonMap.dataNascimento)
            String email = jsonMap.email
            String cpf = jsonMap.cpf
            String pais = jsonMap.pais
            String cep = jsonMap.cep
            String cargo = jsonMap.cargo
            String descricao = jsonMap.descricao
            String senha = jsonMap.senha

            Candidate candidate = new Candidate(id, nomeCompleto, dataNascimento, email, cpf, pais, cep, cargo, descricao, senha)
            Candidate existingCandidate = candidateDAO.getById(id)

            if (existingCandidate != null) {
                candidateDAO.update(candidate)
                response.status = HttpServletResponse.SC_OK
                response.writer.write("Candidato atualizado com sucesso.")

            } else {
                response.status = HttpServletResponse.SC_NOT_FOUND
                response.writer.write("Candidato não encontrado.")
            }

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("Erro ao atualizar candidato: ${e.message}")
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"))
            if (!id) {
                response.status = HttpServletResponse.SC_BAD_REQUEST
                response.writer.write("ID do candidato não fornecido.")
                return
            }

            Candidate existingCandidate = candidateDAO.getById(id)

            if (existingCandidate != null){
                candidateDAO.delete(id)
                response.status = HttpServletResponse.SC_OK
                response.writer.write("Candidato excluído com sucesso.")
            } else {
                response.status = HttpServletResponse.SC_NOT_FOUND
                response.writer.write("Candidato não encontrado para exclusão.")
            }

        } catch (NumberFormatException e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("ID inválido: ${e.message}")

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            response.writer.write("Erro ao excluir candidato: ${e.message}")
        }
    }
}

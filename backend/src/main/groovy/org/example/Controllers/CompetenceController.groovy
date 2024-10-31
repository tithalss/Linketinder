package org.example.Controllers

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.ClassesDAO.CompetenceDAO
import org.example.ClassesDAO.ConnectionFactory
import org.example.Models.Competence

class CompetenceController extends HttpServlet {
    private final CompetenceDAO competenceDAO

    CompetenceController() {
        this.competenceDAO = new CompetenceDAO(ConnectionFactory.getConnection())
    }

    CompetenceController(CompetenceDAO competenceDAO) {
        this.competenceDAO = competenceDAO
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String json = request.reader.text
            Map jsonMap = new JsonSlurper().parseText(json)

            String nome = jsonMap.nome

            Competence competence = new Competence(nome)

            competenceDAO.create(competence)
            response.status = HttpServletResponse.SC_CREATED
            response.writer.write("Competência criada com sucesso.")
        } catch (Exception e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("Erro ao criar competêcnia: ${e.message}")
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id")
            if (!idParam) {
                response.status = HttpServletResponse.SC_BAD_REQUEST
                response.writer.write("ID da competência não fornecida")
                return
            }

            int id = Integer.parseInt(idParam)
            Competence competence = competenceDAO.getById(id)

            if (competence != null) {
                response.contentType = "application/json"
                response.writer.write(JsonOutput.toJson(competence))
            } else {
                response.status = HttpServletResponse.SC_NOT_FOUND
                response.writer.write("Competência não encontrada")
            }

        } catch (NumberFormatException e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("ID inválido: ${e.message}")
        }  catch (Exception e) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            response.writer.write("Erro ao buscar competência ${e.message}")
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String json = request.reader.text
            Map jsonMap = new JsonSlurper().parseText(json)

            int id = jsonMap.id
            String nome = jsonMap.nome

            Competence competence = new Competence(id, nome)
            Competence existingCompetence = competenceDAO.getById(id)

            if (existingCompetence != null) {
                competenceDAO.update(competence)
                response.status = HttpServletResponse.SC_OK
                response.writer.write("Competência atualizada com sucesso.")

            } else {
                response.status = HttpServletResponse.SC_NOT_FOUND
                response.writer.write("Competência não encontrada.")
            }

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("Erro ao atualizar competência: ${e.message}")
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"))
            if (!id) {
                response.status = HttpServletResponse.SC_BAD_REQUEST
                response.writer.write("ID da competência não fornecido.")
                return
            }

            Competence existingCompetence = competenceDAO.getById(id)

            if (existingCompetence != null){
                competenceDAO.delete(id)
                response.status = HttpServletResponse.SC_OK
                response.writer.write("Competência excluída com sucesso.")
            } else {
                response.status = HttpServletResponse.SC_NOT_FOUND
                response.writer.write("Competência não encontrada para exclusão.")
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

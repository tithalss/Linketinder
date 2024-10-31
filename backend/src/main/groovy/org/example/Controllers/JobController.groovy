package org.example.Controllers

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.ClassesDAO.JobDAO
import org.example.ClassesDAO.ConnectionFactory
import org.example.Models.Job

import java.time.LocalDate

class JobController extends HttpServlet {
    private final JobDAO jobDAO

    JobController() {
        this.jobDAO = new JobDAO(ConnectionFactory.getConnection())
    }

    JobController(JobDAO jobDAO) {
        this.jobDAO = jobDAO
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String json = request.reader.text
            Map jsonMap = new JsonSlurper().parseText(json)

            String cargo = jsonMap.cargo
            String descricao = jsonMap.descricao
            String local = jsonMap.local
            int idEmpresa = Integer.parseInt(jsonMap.idEmpresa)

            Job job = new Job(cargo, descricao, local, idEmpresa)

            jobDAO.create(job)
            response.status = HttpServletResponse.SC_CREATED
            response.writer.write("Vaga criada com sucesso.")

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("Erro ao criar a vaga: ${e.message}")
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id")
            if (!idParam) {
                response.status = HttpServletResponse.SC_BAD_REQUEST
                response.writer.write("ID da vaga não fornecido.")
                return
            }

            int id = Integer.parseInt(idParam)
            Job job = jobDAO.getById(id)

            if (job != null) {
                response.contentType = "application/json"
                response.writer.write(JsonOutput.toJson(job))
            } else {
                response.status = HttpServletResponse.SC_NOT_FOUND
                response.writer.write("Vaga não encontrada.")
            }

        } catch (NumberFormatException e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("ID inválido: ${e.message}")

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            response.writer.write("Erro ao buscar vaga: ${e.message}")
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String json = request.reader.text
            Map jsonMap = new JsonSlurper().parseText(json)

            int id = Integer.parseInt(jsonMap.id)
            String cargo = jsonMap.cargo
            String descricao = jsonMap.descricao
            String local = jsonMap.local
            int idEmpresa = Integer.parseInt(jsonMap.idEmpresa)

            Job job = new Job(id, cargo, descricao, local, idEmpresa)
            Job existingJob = jobDAO.getById(id)

            if (existingJob != null) {
                jobDAO.update(job)
                response.status = HttpServletResponse.SC_OK
                response.writer.write("Vaga atualizada com sucesso.")

            } else {
                response.status = HttpServletResponse.SC_NOT_FOUND
                response.writer.write("Vaga não encontrada.")
            }

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("Erro ao atualizar vaga: ${e.message}")
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"))
            if (!id) {
                response.status = HttpServletResponse.SC_BAD_REQUEST
                response.writer.write("ID do vaga não fornecida.")
                return
            }

            Job existingJob = jobDAO.getById(id)

            if (existingJob != null){
                jobDAO.delete(id)
                response.status = HttpServletResponse.SC_OK
                response.writer.write("Vaga excluída com sucesso.")
            } else {
                response.status = HttpServletResponse.SC_NOT_FOUND
                response.writer.write("Vaga não encontrada para exclusão.")
            }

        } catch (NumberFormatException e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("ID inválido: ${e.message}")

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            response.writer.write("Erro ao excluir vaga: ${e.message}")
        }
    }
}

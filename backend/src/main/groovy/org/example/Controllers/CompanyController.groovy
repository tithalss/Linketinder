package org.example.Controllers

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.ClassesDAO.CompanyDAO
import org.example.ClassesDAO.ConnectionFactory
import org.example.Models.Company

import java.time.LocalDate

class CompanyController extends HttpServlet {
    private final CompanyDAO companyDAO

    CompanyController() {
        this.companyDAO = new CompanyDAO(ConnectionFactory.getConnection())
    }

    CompanyController(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String json = request.reader.text
            def jsonMap = new JsonSlurper().parseText(json)

            String nome = jsonMap.nome
            String email = jsonMap.email
            String cnpj = jsonMap.cnpj
            String pais = jsonMap.pais
            String cep = jsonMap.cep
            String descricao = jsonMap.descricao
            String senha = jsonMap.senha

            Company company = new Company(nome, email, cnpj, pais, cep, descricao, senha)

            companyDAO.create(company)
            response.status = HttpServletResponse.SC_CREATED
            response.writer.write("Empresa criada com sucesso.")

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("Erro ao criar a empresa: ${e.message}")
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id")
            if (!idParam) {
                response.status = HttpServletResponse.SC_BAD_REQUEST
                response.writer.write("ID da empresa não fornecido.")
                return
            }

            int id = Integer.parseInt(idParam)
            Company company = companyDAO.getById(id)

            if (company != null) {
                response.contentType = "application/json"
                response.writer.write(JsonOutput.toJson(company))
            } else {
                response.status = HttpServletResponse.SC_NOT_FOUND
                response.writer.write("Empresa não encontrada.")
            }

        } catch (NumberFormatException e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("ID inválido: ${e.message}")

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            response.writer.write("Erro ao buscar empresa: ${e.message}")
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String json = request.reader.text
            def jsonMap = new JsonSlurper().parseText(json)

            int id = jsonMap.id
            String nome = jsonMap.nome
            String email = jsonMap.email
            String cnpj = jsonMap.cnpj
            String pais = jsonMap.pais
            String cep = jsonMap.cep
            String descricao = jsonMap.descricao
            String senha = jsonMap.senha

            Company company = new Company(id, nome, email, cnpj, pais, cep, descricao, senha)
            Company existingCompany = companyDAO.getById(id)

            if (existingCompany != null) {
                companyDAO.update(company)
                response.status = HttpServletResponse.SC_OK
                response.writer.write("Empresa atualizada com sucesso.")

            } else {
                response.status = HttpServletResponse.SC_NOT_FOUND
                response.writer.write("Empresa não encontrada.")
            }

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("Erro ao atualizar empresa: ${e.message}")
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"))
            if (!id) {
                response.status = HttpServletResponse.SC_BAD_REQUEST
                response.writer.write("ID do empresa não fornecida.")
                return
            }

            Company existingCompany = companyDAO.getById(id)

            if (existingCompany != null){
                companyDAO.delete(id)
                response.status = HttpServletResponse.SC_OK
                response.writer.write("Empresa excluída com sucesso.")
            } else {
                response.status = HttpServletResponse.SC_NOT_FOUND
                response.writer.write("Empresa não encontrada para exclusão.")
            }

        } catch (NumberFormatException e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("ID inválido: ${e.message}")

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            response.writer.write("Erro ao excluir empresa: ${e.message}")
        }
    }
}

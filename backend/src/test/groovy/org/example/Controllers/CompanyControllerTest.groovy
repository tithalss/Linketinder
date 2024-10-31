package org.example.Controllers

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.ClassesDAO.CompanyDAO
import org.example.Models.Company
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*

class CompanyControllerTest {

    @Mock
    private CompanyDAO companyDAO

    @Mock
    private HttpServletRequest request

    @Mock
    private HttpServletResponse response

    @InjectMocks
    private CompanyController companyController

    private StringWriter responseWriter

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this)
        responseWriter = new StringWriter()
        PrintWriter writer = new PrintWriter(responseWriter)
        when(response.getWriter()).thenReturn(writer)
    }

    @Test
    void testDoPost() throws Exception {
        String jsonInput = JsonOutput.toJson([
                nome: "Empresa XYZ",
                email: "contato@empresaxyz.com",
                cnpj: "12.345.678/0001-99",
                pais: "Brasil",
                cep: "12345-678",
                descricao: "Uma descrição da Empresa XYZ",
                senha: "senhaSegura"
        ])

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(jsonInput)))

        companyController.doPost(request, response)

        verify(companyDAO, times(1)).create(any(Company.class))
        assertTrue(responseWriter.toString().contains("Empresa criada com sucesso."))
    }

    @Test
    void testDoGet() throws Exception {
        int id = 1
        when(request.getParameter("id")).thenReturn(String.valueOf(id))

        Company company = new Company(id, "Empresa XYZ", "contato@empresaxyz.com", "12.345.678/0001-99", "Brasil", "12345-678", "Uma descrição da Empresa XYZ", "senhaSegura")
        when(companyDAO.getById(id)).thenReturn(company)

        companyController.doGet(request, response)

        verify(companyDAO, times(1)).getById(id)

        String jsonResponse = responseWriter.toString()
        println("JSON Response: $jsonResponse")

        def jsonParsed = new JsonSlurper().parseText(jsonResponse)
        assertEquals("Empresa XYZ", jsonParsed.nome)
    }

    @Test
    void testDoPut() throws Exception {
        int id = 1
        String jsonInput = JsonOutput.toJson([
                id: id,
                nome: "Empresa XYZ",
                email: "contato@empresaxyz.com",
                cnpj: "12.345.678/0001-99",
                pais: "Brasil",
                cep: "12345-678",
                descricao: "Uma descrição da Empresa XYZ",
                senha: "senhaSegura"
        ])
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(jsonInput)))

        Company company = new Company(id, "Empresa XYZ", "contato@empresaxyz.com", "12.345.678/0001-99", "Brasil", "12345-678", "Uma descrição da Empresa XYZ", "senhaSegura")
        when(companyDAO.getById(id)).thenReturn(company)

        companyController.doPut(request, response)

        verify(companyDAO, times(1)).update(any(Company.class))
        assertTrue(responseWriter.toString().contains("Empresa atualizada com sucesso."))
    }

    @Test
    void testDoDelete() throws Exception {
        int id = 1
        when(request.getParameter("id")).thenReturn(String.valueOf(id))
        Company company = new Company(id, "Empresa XYZ", "contato@empresaxyz.com", "12.345.678/0001-99", "Brasil", "12345-678", "Uma descrição da Empresa XYZ", "senhaSegura")
        when(companyDAO.getById(id)).thenReturn(company)

        companyController.doDelete(request, response)

        verify(companyDAO, times(1)).delete(id)
        assertTrue(responseWriter.toString().contains("Empresa excluída com sucesso."))
    }
}

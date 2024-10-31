package org.example.Controllers

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.ClassesDAO.CompetenceDAO
import org.example.Models.Competence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import java.io.BufferedReader
import java.io.PrintWriter
import java.io.StringReader
import java.io.StringWriter

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*

class CompetenceControllerTest {

    @Mock
    private CompetenceDAO competenceDAO

    @Mock
    private HttpServletRequest request

    @Mock
    private HttpServletResponse response

    @InjectMocks
    private CompetenceController competenceController

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
                nome: "Java"
        ])

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(jsonInput)))

        competenceController.doPost(request, response)

        verify(competenceDAO, times(1)).create(any(Competence.class))
        assertTrue(responseWriter.toString().contains("Competência criada com sucesso."))
    }

    @Test
    void testDoGet() throws Exception {
        int id = 1
        when(request.getParameter("id")).thenReturn(String.valueOf(id))

        Competence competence = new Competence(id, "Java")
        when(competenceDAO.getById(id)).thenReturn(competence)

        competenceController.doGet(request, response)

        verify(competenceDAO, times(1)).getById(id)

        String jsonResponse = responseWriter.toString()
        println("JSON Response: $jsonResponse")

        def jsonParsed = new JsonSlurper().parseText(jsonResponse)
        assertEquals("Java", jsonParsed.nome)
    }

    @Test
    void testDoPut() throws Exception {
        int id = 1
        String jsonInput = JsonOutput.toJson([
                id: id,
                nome: "Java Avançado"
        ])
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(jsonInput)))

        Competence competence = new Competence(id, "Java")
        when(competenceDAO.getById(id)).thenReturn(competence)

        competenceController.doPut(request, response)

        verify(competenceDAO, times(1)).update(any(Competence.class))
        assertTrue(responseWriter.toString().contains("Competência atualizada com sucesso."))
    }

    @Test
    void testDoDelete() throws Exception {
        int id = 1
        when(request.getParameter("id")).thenReturn(String.valueOf(id))
        Competence competence = new Competence(id, "Java")
        when(competenceDAO.getById(id)).thenReturn(competence)

        competenceController.doDelete(request, response)

        verify(competenceDAO, times(1)).delete(id)
        assertTrue(responseWriter.toString().contains("Competência excluída com sucesso."))
    }
}

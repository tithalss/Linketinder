package org.example.Controllers

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.ClassesDAO.JobDAO
import org.example.Models.Job
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*

class JobControllerTest {

    @Mock
    private JobDAO jobDAO

    @Mock
    private HttpServletRequest request

    @Mock
    private HttpServletResponse response

    @InjectMocks
    private JobController jobController

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
                cargo: "Desenvolvedor",
                descricao: "Desenvolver sistemas",
                local: "Remoto",
                idEmpresa: "1"
        ])

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(jsonInput)))

        jobController.doPost(request, response)

        // Verifica se o método create foi chamado
        verify(jobDAO, times(1)).create(any(Job.class))
        assertTrue(responseWriter.toString().contains("Vaga criada com sucesso."))
    }

    @Test
    void testDoGet() throws Exception {
        int id = 1
        when(request.getParameter("id")).thenReturn(String.valueOf(id))

        Job job = new Job(id, "Desenvolvedor", "Desenvolver sistemas", "Remoto", 1)
        when(jobDAO.getById(id)).thenReturn(job)

        jobController.doGet(request, response)

        verify(jobDAO, times(1)).getById(id)

        String jsonResponse = responseWriter.toString()
        println("JSON Response: $jsonResponse")

        def jsonParsed = new JsonSlurper().parseText(jsonResponse)
        assertEquals("Desenvolvedor", jsonParsed.cargo)
    }

    @Test
    void testDoDelete() throws Exception {
        int id = 1
        when(request.getParameter("id")).thenReturn(String.valueOf(id))
        Job job = new Job(id, "Desenvolvedor", "Desenvolver sistemas", "Remoto", 1)
        when(jobDAO.getById(id)).thenReturn(job)

        jobController.doDelete(request, response)

        verify(jobDAO, times(1)).delete(id)
        assertTrue(responseWriter.toString().contains("Vaga excluída com sucesso."))
    }
}

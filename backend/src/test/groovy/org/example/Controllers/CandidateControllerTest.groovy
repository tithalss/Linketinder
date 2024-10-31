package org.example.Controllers

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.ClassesDAO.CandidateDAO
import org.example.Models.Candidate
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*

class CandidateControllerTest {

    @Mock
    private CandidateDAO candidateDAO

    @Mock
    private HttpServletRequest request

    @Mock
    private HttpServletResponse response

    @InjectMocks
    private CandidateController candidateController

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
                nomeCompleto: "João Silva",
                dataNascimento: "1990-01-01",
                email: "joao@example.com",
                cpf: "12345678900",
                pais: "Brasil",
                cep: "12345-678",
                cargo: "Desenvolvedor",
                descricao: "Profissional de TI",
                senha: "senhaSegura"
        ])

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(jsonInput)))

        candidateController.doPost(request, response)

        verify(candidateDAO, times(1)).create(any(Candidate.class))
        assertTrue(responseWriter.toString().contains("Candidato criado com sucesso."))
    }

    @Test
    void testDoGet() throws Exception {
        int id = 1
        when(request.getParameter("id")).thenReturn(String.valueOf(id))

        Candidate candidate = new Candidate(id, "João Silva", LocalDate.of(1990, 1, 1), "joao@example.com", "12345678900", "Brasil", "12345-678", "Desenvolvedor", "Profissional de TI", "senhaSegura")
        when(candidateDAO.getById(id)).thenReturn(candidate)

        candidateController.doGet(request, response)

        verify(candidateDAO, times(1)).getById(id)

        String jsonResponse = responseWriter.toString()
        println("JSON Response: $jsonResponse")

        def jsonParsed = new JsonSlurper().parseText(jsonResponse)
        assertEquals("João Silva", jsonParsed.nomeCompleto)
    }

    @Test
    void testDoPut() throws Exception {
        int id = 1
        String jsonInput = JsonOutput.toJson([
                id: id,
                nomeCompleto: "João Silva",
                dataNascimento: "1990-01-01",
                email: "joao@example.com",
                cpf: "12345678900",
                pais: "Brasil",
                cep: "12345-678",
                cargo: "Desenvolvedor",
                descricao: "Profissional de TI",
                senha: "senhaSegura"
        ])
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(jsonInput)))

        Candidate candidate = new Candidate(id, "João Silva", LocalDate.of(1990, 1, 1), "joao@example.com", "12345678900", "Brasil", "12345-678", "Desenvolvedor", "Profissional de TI", "senhaSegura")
        when(candidateDAO.getById(id)).thenReturn(candidate)

        candidateController.doPut(request, response)

        verify(candidateDAO, times(1)).update(any(Candidate.class))
        assertTrue(responseWriter.toString().contains("Candidato atualizado com sucesso."))
    }

    @Test
    void testDoDelete() throws Exception {
        int id = 1
        when(request.getParameter("id")).thenReturn(String.valueOf(id))
        Candidate candidate = new Candidate(id, "João Silva", LocalDate.of(1990, 1, 1), "joao@example.com", "12345678900", "Brasil", "12345-678", "Desenvolvedor", "Profissional de TI", "senhaSegura")
        when(candidateDAO.getById(id)).thenReturn(candidate)

        candidateController.doDelete(request, response)

        verify(candidateDAO, times(1)).delete(id)
        assertTrue(responseWriter.toString().contains("Candidato excluído com sucesso."))
    }
}

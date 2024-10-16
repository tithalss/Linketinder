package org.example.Controllers

import org.example.ClassesDAO.CandidateDAO
import org.example.Models.Candidate
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import java.time.LocalDate

import static org.mockito.Mockito.*

class CandidateControllerTest {

    @Mock
    CandidateDAO candidateDAO

    @InjectMocks
    CandidateController candidateController

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    void testCreateCandidate() {
        String nomeCompleto = "João da Silva"
        LocalDate dataNascimento = LocalDate.of(1990, 5, 15)
        String email = "joao.silva@example.com"
        String cpf = "12345678900"
        String pais = "Brasil"
        String cep = "12345678"
        String cargo = "Desenvolvedor"
        String descricao = "Desenvolvedor de software"
        String senha = "senha123"

        candidateController.createCandidate(nomeCompleto, dataNascimento, email, cpf, pais, cep, cargo, descricao, senha)

        verify(candidateDAO, times(1)).create(any(Candidate))
    }

    @Test
    void testGetCandidateById() {
        Candidate candidate = new Candidate(1, "João da Silva", LocalDate.of(1990, 5, 15), "joao.silva@example.com", "12345678900", "Brasil", "12345-678", "Desenvolvedor", "Desenvolvedor de software", "senha123")
        when(candidateDAO.getById(1)).thenReturn(candidate)

        Candidate result = candidateController.getCandidateById(1)

        assert result == candidate
        verify(candidateDAO, times(1)).getById(1)
    }

    @Test
    void testUpdateCandidate() {
        int id = 1
        String nomeCompleto = "João da Silva"
        LocalDate dataNascimento = LocalDate.of(1990, 05, 15)
        String email = "joao.silva@example.com"
        String cpf = "12345678900"
        String pais = "Brasil"
        String cep = "12345678"
        String cargo = "Desenvolvedor"
        String descricao = "Desenvolvedor de software"
        String senha = "senha123"

        Candidate candidate = new Candidate(id, nomeCompleto, dataNascimento, email, cpf, pais, cep, cargo, descricao, senha)

        candidateController.updateCandidate(candidate)

        verify(candidateDAO, times(1)).update(any(Candidate))
    }

    @Test
    void testDeleteCandidate() {
        int id = 1

        candidateController.deleteCandidate(id)

        verify(candidateDAO, times(1)).delete(id)
    }
}

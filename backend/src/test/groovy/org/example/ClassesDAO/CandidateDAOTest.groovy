package org.example.ClassesDAO

import org.example.Candidate
import org.junit.jupiter.api.*
import java.time.LocalDate
import static org.junit.jupiter.api.Assertions.*

class CandidateDAOTest {

    @BeforeEach
    void setUp() {
        DatabaseConnection.getConnection().prepareStatement("DELETE FROM candidatos").executeUpdate()
    }

    @Test
    void testCreateCandidate() {
        // Configurar dados de teste
        Candidate candidate = new Candidate("Joana Pereira", LocalDate.of(1992, 8, 14), "joan@gmail.com", "45678912355", "Espanha", "67890123", "Arquiteta", "Arquiteta de Software", "senha789")

        // Criar o candidato
        Candidate createdCandidate = CandidateDAO.createCandidate(candidate);

        // Log para verificar o candidato criado
        System.out.println("Created Candidate: " + createdCandidate);

        // Verificar se o candidato não é nulo
        assertNotNull(createdCandidate);
        // Verifique se o ID foi gerado
        assertNotNull(createdCandidate.getId());
    }


    @Test
    void testGetCandidateById() {
        CandidateDAO.createCandidate("Carlos Souza", LocalDate.of(1985, 3, 20), "carlos@gmail.com", "98765432100", "Portugal", "54321987", "Gerente", "Gerente de Projetos", "senha456")

        Candidate candidate = CandidateDAO.getCandidateById(1)

        assertNotNull(candidate)
        assertEquals(1, candidate.getId())
        assertEquals("Carlos Souza", candidate.getNomeCompleto())
        assertEquals("Gerente", candidate.getCargo())
    }

    @Test
    void testUpdateCandidate() {
        CandidateDAO.createCandidate("Ana Pereira", LocalDate.of(1992, 8, 15), "ana@gmail.com", "45678912300", "Espanha", "67890123", "Arquiteta", "Arquiteta de Software", "senha789")

        CandidateDAO.updateCandidate(1, "Ana Beatriz", LocalDate.of(1992, 8, 15), "anabeatriz@gmail.com", "45678912300", "Espanha", "67890123", "Engenheira de Software", "Trabalho com DevOps", "senha987")

        Candidate updatedCandidate = CandidateDAO.getCandidateById(1)
        assertNotNull(updatedCandidate)
        assertEquals("Ana Beatriz", updatedCandidate.getNomeCompleto())
        assertEquals("anabeatriz@gmail.com", updatedCandidate.getEmail())
        assertEquals("Engenheira de Software", updatedCandidate.getCargo())
        assertEquals("Trabalho com DevOps", updatedCandidate.getDescricao())
    }

    @Test
    void testDeleteCandidate() {
        CandidateDAO.createCandidate("João Nogueira", LocalDate.of(1980, 12, 30), "joao@gmail.com", "32165498700", "Brasil", "32145987", "Analista de Sistemas", "Analista Sênior", "senha321")

        CandidateDAO.deleteCandidate(1)

        Candidate candidate = CandidateDAO.getCandidateById(1)
        assertNull(candidate)
    }
}

package org.example.Controllers

import org.example.ClassesDAO.CandidateDAO
import org.example.Models.Candidate
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.time.LocalDate

import static org.mockito.Mockito.*

class CandidateControllerTest {

    CandidateDAO candidateDAO
    CandidateController candidateController

    @BeforeEach
    void setUp() {
        candidateDAO = mock(CandidateDAO)
        candidateController = new CandidateController(candidateDAO)
    }

    @Test
    void testCreateCandidate() {
        Candidate candidate = new Candidate(1, "Cristiano Ronaldo", LocalDate.of(1985, 02, 05), "cr7@siu.com", "77777777777", "Portugal", "77777777", "Striker", "O Milhor", "papaicris")

        candidateController.createCandidate(candidate)

        verify(candidateDAO).create(candidate)
    }

    @Test
    void testGetCandidateById() {
        Candidate candidate = new Candidate(1, "Cristiano Ronaldo", LocalDate.of(1985, 02, 05), "cr7@siu.com", "77777777777", "Portugal", "77777777", "Striker", "O Milhor", "papaicris")
        when(candidateDAO.getById(1)).thenReturn(candidate)

        Candidate result = candidateController.getCandidateById(1)

        assert result == candidate
        verify(candidateDAO).getById(1)
    }

    @Test
    void testGetCandidateByIdNotFound() {
        when(candidateDAO.getById(1)).thenReturn(null)

        Candidate result = candidateController.getCandidateById(1)

        assert result == null
        verify(candidateDAO).getById(1)
    }

    @Test
    void testUpdateCandidate() {
        Candidate candidate = new Candidate(1, "Cristiano Ronaldo", LocalDate.of(1985, 02, 05), "cr7@siu.com", "77777777777", "Portugal", "77777777", "Striker", "O Milhor", "papaicris")

        candidateController.updateCandidate(candidate)

        verify(candidateDAO).update(candidate)
    }

    @Test
    void testDeleteCandidate() {
        candidateController.deleteCandidate(1)

        verify(candidateDAO).delete(1)
    }
}

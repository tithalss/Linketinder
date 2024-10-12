package org.example.ClassesDAO

import org.example.Models.Candidate
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertNotNull
import static org.mockito.ArgumentMatchers.*
import static org.mockito.Mockito.*

class CandidateDAOTest {

    private CandidateDAO candidateDAO
    private Candidate mockCandidate

    @BeforeEach
    void setUp() {
        candidateDAO = mock(CandidateDAO.class)
        mockCandidate = new Candidate(1, "Joana Pereira", LocalDate.of(1992, 8, 14), "joan@gmail.com", "45678912355", "Espanha", "67890123", "Arquiteta", "Arquiteta de Software", "senha789")
    }

    @Test
    void testCreateCandidate() {
        doNothing().when(candidateDAO).create(any(Candidate.class))

        candidateDAO.create(mockCandidate)
        verify(candidateDAO).create(mockCandidate)
    }

    @Test
    void testGetCandidateById() {
        when(candidateDAO.getById(1)).thenReturn(mockCandidate)

        Candidate candidate = candidateDAO.getById(1)

        assertNotNull(candidate)
        assertEquals(1, candidate.getId())
        assertEquals("Joana Pereira", candidate.getNomeCompleto())
        assertEquals("Arquiteta", candidate.getCargo())
    }

    @Test
    void testUpdateCandidate() {
        doNothing().when(candidateDAO).update(any(Candidate.class))

        candidateDAO.update(mockCandidate)
        verify(candidateDAO).update(mockCandidate)
    }

    @Test
    void testDeleteCandidate() {
        doNothing().when(candidateDAO).delete(1)

        candidateDAO.delete(1)
        verify(candidateDAO).delete(1)
    }
}

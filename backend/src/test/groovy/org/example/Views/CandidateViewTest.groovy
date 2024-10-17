package org.example.Views

import org.example.Controllers.CandidateController
import org.example.Models.Candidate
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import java.time.LocalDate

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*

class CandidateViewTest {

    @Mock
    CandidateController candidateController

    @InjectMocks
    CandidateView candidateView

    Scanner mockScanner

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this)
        mockScanner = mock(Scanner)
        candidateView = new CandidateView(candidateController)
    }

    @Test
    void testCreateCandidate() {
        when(mockScanner.nextLine()).thenReturn(
                "João Silva",
                "1990-01-01",
                "joao@example.com",
                "12345678900",
                "Brasil",
                "12345-678",
                "Desenvolvedor",
                "Descrição do candidato",
                "senha123"
        )

        candidateView.createCandidate(mockScanner)

        verify(candidateController, times(1)).createCandidate(any(Candidate))
    }

    @Test
    void testGetCandidateById() {
        def candidate = new Candidate(1, "João Silva", LocalDate.parse("1990-01-01"), "joao@example.com", "12345678900", "Brasil", "12345-678", "Desenvolvedor", "Descrição do candidato", "senha123")
        when(mockScanner.nextLine()).thenReturn("1")
        when(candidateController.getCandidateById(1)).thenReturn(candidate)

        candidateView.getCandidateById(mockScanner)

        verify(candidateController, times(1)).getCandidateById(1)
    }

    @Test
    void testUpdateCandidate() {
        Candidate existingCandidate = new Candidate(1, "João Silva", LocalDate.parse("1990-01-01"), "joao@example.com", "12345678900", "Brasil", "12345-678", "Desenvolvedor", "Descrição do candidato", "senha123")
        when(mockScanner.nextLine()).thenReturn("1",
                "João Silva Atualizado",
                "1990-01-01",
                "joao.atualizado@example.com",
                "12345678900",
                "Brasil",
                "12345-678",
                "Desenvolvedor",
                "Nova descrição do candidato",
                "novaSenha123"
        )
        when(candidateController.getCandidateById(1)).thenReturn(existingCandidate)

        candidateView.updateCandidate(mockScanner)

        verify(candidateController, times(1)).updateCandidate(any(Candidate))
    }

    @Test
    void testDeleteCandidate() {
        when(mockScanner.nextLine()).thenReturn("1")

        candidateView.deleteCandidate(mockScanner)

        verify(candidateController, times(1)).deleteCandidate(1)
    }
}

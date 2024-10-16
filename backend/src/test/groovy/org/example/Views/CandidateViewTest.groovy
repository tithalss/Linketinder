package org.example.Views

import org.example.Controllers.CandidateController
import org.example.Models.Candidate
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.time.LocalDate

import static org.mockito.Mockito.*

class CandidateViewTest {

    CandidateController candidateController
    CandidateView candidateView
    Scanner scanner

    @BeforeEach
    void setUp() {
        candidateController = mock(CandidateController)
        scanner = mock(Scanner)
        candidateView = new CandidateView(candidateController)
    }

    @Test
    void testCreateCandidate() {
        when(scanner.nextLine())
                .thenReturn("Cristiano Ronaldo")
                .thenReturn("1996-03-02")
                .thenReturn("cr7@gmail.com")
                .thenReturn("07489856445")
                .thenReturn("Portugal")
                .thenReturn("78888546")
                .thenReturn("Atacante")
                .thenReturn("Melhor do mundo")
                .thenReturn("senha123")

        candidateView.createCandidate(scanner)

        verify(candidateController).createCandidate(
                "Cristiano Ronaldo",
                LocalDate.of(1996, 3, 2),
                "cr7@gmail.com",
                "07489856445",
                "Portugal",
                "78888546",
                "Atacante",
                "Melhor do mundo",
                "senha123"
        )
    }

    @Test
    void testUpdateCandidate() {
        when(scanner.nextInt()).thenReturn(1)
        when(scanner.nextLine())
                .thenReturn("")
                .thenReturn("Cristiano Ronaldo")
                .thenReturn("1996-03-02")
                .thenReturn("cr7@gmail.com")
                .thenReturn("07489856445")
                .thenReturn("Portugal")
                .thenReturn("78888546")
                .thenReturn("Striker")
                .thenReturn("Melhor do mundo")
                .thenReturn("papaicris")

        candidateView.updateCandidate(scanner)

        Candidate candidate = new Candidate(1, "Cristiano Ronaldo", LocalDate.of(1996-03-02), "cr7@gmail.com", "07489856445", "Portugal", "78888546", "Striker", "Melhor do munndo", "papaicris")

        verify(candidateController).updateCandidate(candidate)
    }

    @Test
    void testGetCandidateById() {
        Candidate candidate = new Candidate(1, "Cristiano Ronaldo", LocalDate.of(1996, 3, 2), "cr7@gmail.com", "07489856445", "Portugal", "78888546", "Atacante", "Melhor do mundo", "senha123")
        when(scanner.nextInt()).thenReturn(1)
        when(candidateController.getCandidateById(1)).thenReturn(candidate)

        candidateView.getCandidateById(scanner)

        verify(candidateController).getCandidateById(1)
    }

    @Test
    void testDeleteCandidate() {
        when(scanner.nextInt()).thenReturn(1)

        candidateView.deleteCandidate(scanner)

        verify(candidateController).deleteCandidate(1)
    }
}

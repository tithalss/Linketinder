package org.example.Views

import org.example.Controllers.CompetenceController
import org.example.Models.Competence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CompetenceViewTest {

    private CompetenceController competenceController
    private CompetenceView competenceView
    private Scanner scanner

    @BeforeEach
    void setup() {
        competenceController = Mockito.mock(CompetenceController)
        scanner = Mockito.mock(Scanner)
        competenceView = new CompetenceView(competenceController)
    }

    @Test
    void testCreateCompetence() {
        Mockito.when(scanner.nextLine()).thenReturn("Programação")

        competenceView.createCompetence(scanner)

        Mockito.verify(competenceController, Mockito.times(1)).createCompetence("Programação")
    }

    @Test
    void testUpdateCompetence() {
        Mockito.when(scanner.nextInt()).thenReturn(1)
        Mockito.when(scanner.nextLine()).thenReturn("Programação Avançada")

        competenceView.updateCompetence(scanner)

        Mockito.verify(competenceController, Mockito.times(1)).updateCompetence(1, "Programação Avançada")
    }

    @Test
    void testGetCompetenceById() {
        Competence competence = new Competence(nome: "Programação")
        Mockito.when(scanner.nextInt()).thenReturn(1)
        Mockito.when(competenceController.getCompetenceById(1)).thenReturn(competence)

        competenceView.getCompetenceById(scanner)

        Mockito.verify(competenceController, Mockito.times(1)).getCompetenceById(1)
    }

    @Test
    void testDeleteCompetence() {
        Mockito.when(scanner.nextInt()).thenReturn(1)

        competenceView.deleteCompetence(scanner)

        Mockito.verify(competenceController, Mockito.times(1)).deleteCompetence(1)
    }
}

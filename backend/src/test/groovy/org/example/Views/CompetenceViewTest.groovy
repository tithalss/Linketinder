package org.example.Views

import org.example.Controllers.CompetenceController
import org.example.Models.Competence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*

class CompetenceViewTest {

    @Mock
    CompetenceController competenceController

    @InjectMocks
    CompetenceView competenceView

    Scanner mockScanner

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this)
        mockScanner = mock(Scanner)
        competenceView = new CompetenceView(competenceController)
    }

    @Test
    void testCreateCompetence() {
        when(mockScanner.nextLine()).thenReturn("Java")

        competenceView.createCompetence(mockScanner)

        verify(competenceController, times(1)).createCompetence(any(Competence))
    }

    @Test
    void testGetCompetenceById() {
        Competence competence = new Competence(1, "Java")
        when(mockScanner.nextLine()).thenReturn("1")
        when(competenceController.getCompetenceById(1)).thenReturn(competence)

        competenceView.getCompetenceById(mockScanner)

        verify(competenceController, times(1)).getCompetenceById(1)
    }

    @Test
    void testUpdateCompetence() {
        Competence competence = new Competence(1, "Java")
        when(mockScanner.nextLine()).thenReturn("1", "Kotlin")
        when(competenceController.getCompetenceById(1)).thenReturn(competence)

        competenceView.updateCompetence(mockScanner)

        verify(competenceController, times(1)).updateCompetence(any(Competence))
    }

    @Test
    void testDeleteCompetence() {
        Mockito.when(mockScanner.nextInt()).thenReturn(1)

        competenceView.deleteCompetence(mockScanner)

        Mockito.verify(competenceController, Mockito.times(1)).deleteCompetence(1)
    }
}

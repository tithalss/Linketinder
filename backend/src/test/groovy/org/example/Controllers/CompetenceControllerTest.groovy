package org.example.Controllers

import org.example.ClassesDAO.CompetenceDAO
import org.example.Models.Competence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.mockito.Mockito.*

class CompetenceControllerTest {

    CompetenceDAO competenceDAO
    CompetenceController competenceController

    @BeforeEach
    void setUp() {
        competenceDAO = mock(CompetenceDAO)
        competenceController = new CompetenceController(competenceDAO)
    }

    @Test
    void testCreateCompetence() {
        Competence competence = new Competence(1, "Java Programming")

        competenceController.createCompetence(competence)

        verify(competenceDAO).create(competence)
    }

    @Test
    void testUpdateCompetence() {
        Competence competence = new Competence(1, "Advanced Java Programming")

        competenceController.updateCompetence(competence)

        verify(competenceDAO).update(competence)
    }

    @Test
    void testGetCompetenceById() {
        Competence competence = new Competence(1, "Java Programming")
        when(competenceDAO.getById(1)).thenReturn(competence)

        Competence result = competenceController.getCompetenceById(1)

        assert result == competence
        verify(competenceDAO).getById(1)
    }

    @Test
    void testGetCompetenceByIdNotFound() {
        when(competenceDAO.getById(1)).thenReturn(null)

        Competence result = competenceController.getCompetenceById(1)

        assert result == null
        verify(competenceDAO).getById(1)
    }

    @Test
    void testDeleteCompetence() {
        competenceController.deleteCompetence(1)

        verify(competenceDAO).delete(1)
    }
}

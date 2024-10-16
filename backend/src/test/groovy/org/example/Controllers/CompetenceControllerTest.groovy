package org.example.Controllers

import org.example.ClassesDAO.CompetenceDAO
import org.example.Models.Competence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import static org.mockito.Mockito.*

class CompetenceControllerTest {

    @Mock
    CompetenceDAO competenceDAO

    @InjectMocks
    CompetenceController competenceController

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    void testCreateCompetence() {
        String nome = "Java"

        competenceController.createCompetence(nome)

        verify(competenceDAO, times(1)).create(any(Competence))
    }

    @Test
    void testUpdateCompetence() {
        int id = 1
        String nome = "Advanced Java Programming"

        competenceController.updateCompetence(id, nome)

        verify(competenceDAO, times(1)).update(any(Competence))
    }

    @Test
    void testGetCompetenceById_ExistingCompetence() {
        Competence competence = new Competence(1, "Java Programming")
        when(competenceDAO.getById(1)).thenReturn(competence)

        Competence result = competenceController.getCompetenceById(1)

        assert result == competence
        verify(competenceDAO, times(1)).getById(1)
    }

    @Test
    void testGetCompetenceById_NonExistingCompetence() {
        when(competenceDAO.getById(1)).thenReturn(null)

        Competence result = competenceController.getCompetenceById(1)

        assert result == null
        verify(competenceDAO, times(1)).getById(1)
    }

    @Test
    void testDeleteCompetence() {
        int id = 1

        competenceController.deleteCompetence(id)

        verify(competenceDAO, times(1)).delete(id)
    }
}

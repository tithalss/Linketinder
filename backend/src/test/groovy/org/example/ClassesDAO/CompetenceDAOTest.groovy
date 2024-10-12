package org.example.ClassesDAO

import org.example.Models.Competence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import static org.junit.jupiter.api.Assertions.*

class CompetenceDAOTest {

    private CompetenceDAO competenceDAO

    @BeforeEach
    void setUp() {
        competenceDAO = Mockito.mock(CompetenceDAO.class)
    }

    @Test
    void testCreateCompetence() {
        Competence competence = new Competence("Java")

        Mockito.doNothing().when(competenceDAO).create(competence)

        competenceDAO.create(competence)
        Mockito.verify(competenceDAO).create(competence)
    }

    @Test
    void testGetCompetenceById() {
        Competence competence = new Competence("Python")
        competence.setId(1)

        Mockito.when(competenceDAO.getById(1)).thenReturn(competence)

        Competence result = competenceDAO.getById(1)

        assertNotNull(result)
        assertEquals(1, result.getId())
        assertEquals("Python", result.getNome())
    }

    @Test
    void testUpdateCompetence() {
        Competence competence = new Competence("JavaScript")
        competence.setId(1)

        Mockito.doNothing().when(competenceDAO).update(competence)

        competenceDAO.update(competence)
        Mockito.verify(competenceDAO).update(competence)
    }

    @Test
    void testDeleteCompetence() {
        Mockito.doNothing().when(competenceDAO).delete(1)

        competenceDAO.delete(1)
        Mockito.verify(competenceDAO).delete(1)
    }
}

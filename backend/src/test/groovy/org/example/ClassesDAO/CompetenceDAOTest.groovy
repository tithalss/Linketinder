package org.example.ClassesDAO

import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.*

class CompetenceDAOTest {

    @BeforeEach
    void setUp() {
        DatabaseConnection.getConnection().prepareStatement("TRUNCATE TABLE competencias RESTART IDENTITY CASCADE;").executeUpdate()
    }

    @Test
    void testCreateCompetence() {
        CompetenceDAO.createCompetence("Java")

        Map<String, Object> createdCompetence = CompetenceDAO.getCompetenceById(1)

        assertNotNull(createdCompetence)
        assertNotNull(createdCompetence.get("id"))
        assertEquals("Java", createdCompetence.get("nome"))
    }

    @Test
    void testGetCompetenceById() {
        CompetenceDAO.createCompetence("Python")

        Map<String, Object> competence = CompetenceDAO.getCompetenceById(1)

        assertNotNull(competence)
        assertEquals(1, competence.get("id"))
        assertEquals("Python", competence.get("nome"))
    }

    @Test
    void testUpdateCompetence() {
        CompetenceDAO.createCompetence("JavaScript")

        CompetenceDAO.updateCompetence(1, "JavaScript ES6")

        Map<String, Object> updatedCompetence = CompetenceDAO.getCompetenceById(1)
        assertNotNull(updatedCompetence)
        assertEquals("JavaScript ES6", updatedCompetence.get("nome"))
    }

    @Test
    void testDeleteCompetence() {
        CompetenceDAO.createCompetence("C++")

        CompetenceDAO.deleteCompetence(1)

        Map<String, Object> competence = CompetenceDAO.getCompetenceById(1)
        assertNull(competence)
    }

    @Test
    void testGetCompetences() {
        CompetenceDAO.createCompetence("Ruby")
        CompetenceDAO.createCompetence("Go")

        List<Map<String, Object>> competences = CompetenceDAO.getCompetences()

        assertNotNull(competences)
        assertEquals(2, competences.size())
        assertEquals("Ruby", competences.get(0).get("nome"))
        assertEquals("Go", competences.get(1).get("nome"))
    }
}

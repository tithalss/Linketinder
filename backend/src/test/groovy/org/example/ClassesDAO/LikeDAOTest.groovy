package org.example.ClassesDAO

import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.*

class LikeDAOTest {

    @BeforeEach
    void setUp() {
        DatabaseConnection.getConnection().prepareStatement("TRUNCATE TABLE like_empresa RESTART IDENTITY CASCADE;").executeUpdate()
        DatabaseConnection.getConnection().prepareStatement("TRUNCATE TABLE like_candidato RESTART IDENTITY CASCADE;").executeUpdate()
    }

    @Test
    void testGetMatchsForCompany() {
        LikeDAO.likeFromCompany(1, 1)
        LikeDAO.likeFromCandidate(1, 1)

        List<String> matchs = LikeDAO.getMatchsForCompany(1)
        assertFalse(matchs.isEmpty())
        assertTrue(matchs.get(0).contains("Match encontrado! Vaga ID:"))
    }

    @Test
    void testGetMatchsForCandidate() {
        LikeDAO.likeFromCompany(1, 1)
        LikeDAO.likeFromCandidate(1, 1)

        List<String> matchs = LikeDAO.getMatchsForCandidate(1)
        assertFalse(matchs.isEmpty())
        assertTrue(matchs.get(0).contains("Match encontrado! Vaga ID:"))
    }
}

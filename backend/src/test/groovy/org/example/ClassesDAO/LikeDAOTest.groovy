package org.example.ClassesDAO

import org.junit.jupiter.api.*
import org.mockito.*
import static org.mockito.Mockito.*
import static org.junit.jupiter.api.Assertions.*

class LikeDAOTest {

    @Mock
    private LikeDAO likeDAO

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    void testGetMatchsForCompany() {
        doNothing().when(likeDAO).likeFromCompany(1, 1)
        doNothing().when(likeDAO).likeFromCandidate(1, 1)
        when(likeDAO.getMatchsForCompany(1)).thenReturn(["Match encontrado! Vaga ID: 1"])

        likeDAO.likeFromCompany(1, 1)
        likeDAO.likeFromCandidate(1, 1)

        List<String> matchs = likeDAO.getMatchsForCompany(1)

        assertFalse(matchs.isEmpty())
        assertTrue(matchs.get(0).contains("Match encontrado! Vaga ID:"))
    }

    @Test
    void testGetMatchsForCandidate() {
        doNothing().when(likeDAO).likeFromCompany(1, 1)
        doNothing().when(likeDAO).likeFromCandidate(1, 1)
        when(likeDAO.getMatchsForCandidate(1)).thenReturn(["Match encontrado! Vaga ID: 1"])

        likeDAO.likeFromCompany(1, 1)
        likeDAO.likeFromCandidate(1, 1)

        List<String> matchs = likeDAO.getMatchsForCandidate(1)

        assertFalse(matchs.isEmpty())
        assertTrue(matchs.get(0).contains("Match encontrado! Vaga ID:"))
    }
}

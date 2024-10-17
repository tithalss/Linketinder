package org.example.Models

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.*

class CompetenceTest {

    private Competence competence

    @BeforeEach
    void setUp() {
        competence = new Competence()
    }

    @Test
    void testConstructorWithIdAndName() {
        competence = new Competence(1, "Programação")
        assertEquals(1, competence.getId())
        assertEquals("Programação", competence.getNome())
    }

    @Test
    void testConstructorWithName() {
        competence = new Competence("Testes")
        assertEquals("Testes", competence.getNome())
        assertEquals(0, competence.getId()) // O id deve ser 0 se não for setado
    }

    @Test
    void testSetId() {
        competence.setId(2)
        assertEquals(2, competence.getId())
    }

    @Test
    void testSetNome() {
        competence.setNome("Análise")
        assertEquals("Análise", competence.getNome())
    }

    @Test
    void testToString() {
        competence.setId(3)
        competence.setNome("Desenvolvimento Web")
        String expectedOutput = "ID: 3\nNome da Competência: Desenvolvimento Web"
        assertEquals(expectedOutput, competence.toString())
    }
}

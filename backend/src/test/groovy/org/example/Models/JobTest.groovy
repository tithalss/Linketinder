package org.example.Models


import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.*

class JobTest {

    @Test
    void testGettersAndSetters() {
        Job job = new Job(
                1,
                "Desenvolvedor Backend",
                "Responsável pelo desenvolvimento de APIs",
                "Remoto",
                101
        )

        assertEquals(1, job.getId())
        assertEquals("Desenvolvedor Backend", job.getCargo())
        assertEquals("Responsável pelo desenvolvimento de APIs", job.getDescricao())
        assertEquals("Remoto", job.getLocal())
        assertEquals(101, job.getIdEmpresa())

        job.setId(2)
        job.setCargo("Desenvolvedor Frontend")
        job.setLocal("Remoto")
        job.setIdEmpresa(102)

        assertEquals(2, job.getId())
        assertEquals("Desenvolvedor Frontend", job.getCargo())
        assertEquals("Remoto", job.getLocal())
        assertEquals(102, job.getIdEmpresa())
    }

    @Test
    void testToString() {
        Job job = new Job(
                1,
                "Desenvolvedor Backend",
                "Responsável pelo desenvolvimento de APIs",
                "Remoto",
                101
        )

        String expected = "ID: 1\n" +
                "Cargo: Desenvolvedor Backend\n" +
                "Descrição da vaga: Responsável pelo desenvolvimento de APIs\n" +
                "Local da vaga: Remoto\n" +
                "ID da empresa: 101"

        assertEquals(expected, job.toString())
    }
}

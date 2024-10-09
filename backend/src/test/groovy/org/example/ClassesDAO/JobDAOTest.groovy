package org.example.ClassesDAO

import org.example.Models.Job
import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.*

class JobDAOTest {

    @BeforeEach
    void setUp() {
        DatabaseConnection.getConnection().prepareStatement("TRUNCATE TABLE vagas RESTART IDENTITY CASCADE;").executeUpdate()
    }

    @Test
    void testCreateJob() {
        JobDAO.createJob("Desenvolvedor Java", "Desenvolver aplicações usando Java e seus frameworks.", "Home Office", 1)

        Job createdJob = JobDAO.getJobById(1)

        assertNotNull(createdJob)
        assertNotNull(createdJob.getId())
        assertEquals("Desenvolvedor Java", createdJob.getCargo())
    }

    @Test
    void testGetJobById() {
        JobDAO.createJob("Analista de Sistemas", "Análise de sistemas.", "Escritório", 1)

        Job job = JobDAO.getJobById(1)

        assertNotNull(job)
        assertEquals(1, job.getId())
        assertEquals("Analista de Sistemas", job.getCargo())
    }

    @Test
    void testUpdateJob() {
        JobDAO.createJob("Desenvolvedor Frontend", "Desenvolvimento de interfaces.", "Remoto", 1)

        JobDAO.updateJob(1, "Desenvolvedor Frontend Sênior", "Desenvolvimento de interfaces com foco em usabilidade.", "Híbrido", 1)

        Job updatedJob = JobDAO.getJobById(1)
        assertNotNull(updatedJob)
        assertEquals("Desenvolvedor Frontend Sênior", updatedJob.getCargo())
        assertEquals("Desenvolvimento de interfaces com foco em usabilidade.", updatedJob.getDescricao())
        assertEquals("Híbrido", updatedJob.getLocal())
    }

    @Test
    void testDeleteJob() {
        JobDAO.createJob("Gerente de Projetos", "Gerenciamento de projetos.", "Escritório", 1)

        JobDAO.deleteJob(1)

        Map<String, Object> job = JobDAO.getJobById(1)
        assertNull(job)
    }
}

package org.example.ClassesDAO

import org.example.Models.Job
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.*
import static org.mockito.ArgumentMatchers.*
import static org.mockito.Mockito.*

class JobDAOTest {

    private JobDAO jobDAO
    private Job mockJob

    @BeforeEach
    void setUp() {
        jobDAO = mock(JobDAO.class)
        mockJob = new Job(1, "Desenvolvedor Java", "Desenvolver aplicações usando Java e seus frameworks.", "Home Office", 1)
    }

    @Test
    void testCreateJob() {
        doNothing().when(jobDAO).create(any(Job.class))

        jobDAO.create(mockJob)
        verify(jobDAO).create(mockJob)
    }

    @Test
    void testGetJobById() {
        when(jobDAO.getById(1)).thenReturn(mockJob)

        Job job = jobDAO.getById(1)

        assertNotNull(job)
        assertEquals(1, job.getId())
        assertEquals("Desenvolvedor Java", job.getCargo())
    }

    @Test
    void testUpdateJob() {
        doNothing().when(jobDAO).update(any(Job.class))

        jobDAO.update(mockJob)
        verify(jobDAO).update(mockJob)
    }

    @Test
    void testDeleteJob() {
        doNothing().when(jobDAO).delete(1)

        jobDAO.delete(1)
        verify(jobDAO).delete(1)
    }
}

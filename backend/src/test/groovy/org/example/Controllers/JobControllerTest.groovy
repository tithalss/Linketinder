package org.example.Controllers

import org.example.ClassesDAO.JobDAO
import org.example.Models.Job
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import static org.mockito.Mockito.*

class JobControllerTest {

    @Mock
    JobDAO jobDAO

    @InjectMocks
    JobController jobController

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    void testCreateJob() {
        Job job = new Job(1, "Desenvolvedor", "Desenvolver software", "São Paulo", 123)

        jobController.createJob(job)

        verify(jobDAO, times(1)).create(job)
    }

    @Test
    void testGetJobById() {
        Job job = new Job(1, "Desenvolvedor", "Desenvolver software", "São Paulo", 123)
        when(jobDAO.getById(1)).thenReturn(job)

        Job result = jobController.getJobById(1)

        assert result == job
        verify(jobDAO, times(1)).getById(1)
    }

    @Test
    void testUpdateJob() {
        Job job = new Job(1, "Desenvolvedor", "Desenvolver software atualizado", "São Paulo", 123)

        jobController.updateJob(job)

        verify(jobDAO, times(1)).update(job)
    }

    @Test
    void testDeleteJob() {
        int id = 1

        jobController.deleteJob(id)

        verify(jobDAO, times(1)).delete(id)
    }
}

package org.example.Views

import org.example.Controllers.JobController
import org.example.Models.Job
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*

class JobViewTest {

    @Mock
    JobController jobController

    @InjectMocks
    JobView jobView

    Scanner mockScanner

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this)
        mockScanner = mock(Scanner)
        jobView = new JobView(jobController)
    }

    @Test
    void testCreateJob() {
        when(mockScanner.nextLine()).thenReturn("Desenvolvedor", "Desenvolver aplicações", "São Paulo", "123")

        jobView.createJob(mockScanner)

        verify(jobController, times(1)).createJob(any(Job))
    }

    @Test
    void testGetJobById() {
        def job = new Job(1, "Desenvolvedor", "Desenvolver aplicações", "São Paulo", 123)
        when(mockScanner.nextLine()).thenReturn("1")
        when(jobController.getJobById(1)).thenReturn(job)

        jobView.getJobById(mockScanner)

        verify(jobController, times(1)).getJobById(1)
    }

    @Test
    void testUpdateJob() {
        Job job = new Job(1, "Desenvolvedor", "Desenvolver aplicações", "São Paulo", 123)
        when(mockScanner.nextLine()).thenReturn("1", "Novo Cargo", "Nova Descrição", "Novo Local", "456")
        when(jobController.getJobById(1)).thenReturn(job)

        jobView.updateJob(mockScanner)

        verify(jobController, times(1)).updateJob(any(Job))
    }

    @Test
    void testDeleteJob() {
        when(mockScanner.nextLine()).thenReturn("1")

        jobView.deleteJob(mockScanner)

        verify(jobController, times(1)).deleteJob(1)
    }
}

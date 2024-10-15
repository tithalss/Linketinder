package org.example.Controllers

import org.example.ClassesDAO.JobDAO
import org.example.Models.Job

class JobController {

    private final JobDAO jobDAO

    JobController(JobDAO jobDAO) {
        this.jobDAO = jobDAO
    }

    void createJob(Job job) {
        jobDAO.create(job)
    }

    Job getJobById(int id) {
        return jobDAO.getById(id)
    }

    void updateJob(Job job) {
        jobDAO.update(job)
    }

    void deleteJob(int id) {
        jobDAO.delete(id)
    }
}

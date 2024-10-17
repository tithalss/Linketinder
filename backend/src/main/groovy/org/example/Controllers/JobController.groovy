package org.example.Controllers

import org.example.ClassesDAO.JobDAO
import org.example.Models.Candidate
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
        Job job = jobDAO.getById(id)
        if (job != null) {
            return job
        } else {
            return null
        }
    }

    void updateJob(Job job) {
        jobDAO.update(job)
    }

    void deleteJob(int id) {
        jobDAO.delete(id)
    }
}

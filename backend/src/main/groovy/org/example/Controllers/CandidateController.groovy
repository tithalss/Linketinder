package org.example.Controllers

import org.example.ClassesDAO.CandidateDAO
import org.example.Models.Candidate

class CandidateController {
    private final CandidateDAO candidateDAO

    CandidateController(CandidateDAO candidateDAO) {
        this.candidateDAO = candidateDAO
    }

    void createCandidate(Candidate candidate) {
        candidateDAO.create(candidate)
    }

    Candidate getCandidateById(int id) {
        Candidate candidate = candidateDAO.getById(id)
        if (candidate != null) {
            return candidate
        } else {
            return null
        }
    }

    void updateCandidate(Candidate candidate) {
        candidateDAO.update(candidate)
    }

    void deleteCandidate(int id) {
        candidateDAO.delete(id)
    }
}

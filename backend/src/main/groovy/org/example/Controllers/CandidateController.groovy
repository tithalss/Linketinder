package org.example.Controllers

import org.example.ClassesDAO.CandidateDAO
import org.example.Models.Candidate

import java.time.LocalDate

class CandidateController {
    private final CandidateDAO candidateDAO

    CandidateController(CandidateDAO candidateDAO) {
        this.candidateDAO = candidateDAO
    }

    void createCandidate(String nomeCompleto, LocalDate dataNascimento, String email, String cpf, String pais, String cep, String cargo, String descricao, String senha) {
        Candidate candidate = new Candidate(nomeCompleto, dataNascimento, email, cpf, pais, cep, cargo, descricao, senha)
        candidateDAO.create(candidate)
    }

    Candidate getCandidateById(int id) {
        return candidateDAO.getById(id)
    }

    void updateCandidate(Candidate candidate) {
        candidateDAO.update(candidate)
    }

    void deleteCandidate(int id) {
        candidateDAO.delete(id)
    }
}

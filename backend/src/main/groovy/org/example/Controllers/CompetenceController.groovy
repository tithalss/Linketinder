package org.example.Controllers

import org.example.ClassesDAO.CompetenceDAO
import org.example.Models.Competence

class CompetenceController {

    private final CompetenceDAO competenceDAO

    CompetenceController(CompetenceDAO competenceDAO) {
        this.competenceDAO = competenceDAO
    }

    void createCompetence(String nome) {
        Competence competence = new Competence(nome)
        competenceDAO.create(competence)
    }

    void updateCompetence(int id, String nome) {
        Competence competence = new Competence(id, nome)
        competenceDAO.update(competence)
    }

    Competence getCompetenceById(int id) {
        Competence competence = competenceDAO.getById(id)
        if (competence != null) {
            return competence
        } else {
            return null
        }
    }

    void deleteCompetence(int id) {
        competenceDAO.delete(id)
    }
}

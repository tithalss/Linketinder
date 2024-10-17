package org.example.Controllers

import org.example.ClassesDAO.CompetenceDAO
import org.example.Models.Competence

class CompetenceController {

    private final CompetenceDAO competenceDAO

    CompetenceController(CompetenceDAO competenceDAO) {
        this.competenceDAO = competenceDAO
    }

    void createCompetence(Competence competence) {
        competenceDAO.create(competence)
    }

    void updateCompetence(Competence competence) {
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

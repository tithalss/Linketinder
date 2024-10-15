package org.example.Controllers

import org.example.ClassesDAO.LikeDAO

class LikeController {

    private final LikeDAO likeDAO

    LikeController(LikeDAO likeDAO) {
        this.likeDAO = likeDAO
    }

    void likeFromCompany(int idEmpresa, int idCandidato) {
        likeDAO.likeFromCompany(idEmpresa, idCandidato)
        println "Empresa ID: ${idEmpresa} curtiu o candidato ID: ${idCandidato}."
    }

    void likeFromCandidate(int idCandidato, int idVaga) {
        likeDAO.likeFromCandidate(idCandidato, idVaga)
        println "Candidato ID: ${idCandidato} curtiu a vaga ID: ${idVaga}."
    }

    void getMatchsForCompany(int idEmpresa) {
        List<String> matchs = likeDAO.getMatchsForCompany(idEmpresa)
        if (matchs.isEmpty()) {
            println "Nenhum match encontrado para a empresa ID: ${idEmpresa}."
        } else {
            matchs.each { match ->
                println match
            }
        }
    }

    void getMatchsForCandidate(int idCandidato) {
        List<String> matchs = likeDAO.getMatchsForCandidate(idCandidato)
        if (matchs.isEmpty()) {
            println "Nenhum match encontrado para o candidato ID: ${idCandidato}."
        } else {
            matchs.each { match ->
                println match
            }
        }
    }
}

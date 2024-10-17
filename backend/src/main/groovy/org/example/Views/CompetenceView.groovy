package org.example.Views

import org.example.Controllers.CompetenceController
import org.example.Models.Competence

class CompetenceView {

    private final CompetenceController competenceController

    CompetenceView(CompetenceController competenceController) {
        this.competenceController = competenceController
    }

    void displayMenu() {
        Scanner scanner = new Scanner(System.in)

        while (true) {
            println "==== Menu de Candidatos ===="
            println "1. Criar nova competência"
            println "2. Atualizar competência"
            println "3. Buscar competência por ID"
            println "4. Deletar competência"
            println "5. Voltar"
            print "Escolha uma opção: "
            Integer option = scanner.nextInt()
            scanner.nextLine() // Consumir a quebra de linha

            switch (option) {
                case 1:
                    createCompetence(scanner)
                    break
                case 2:
                    updateCompetence(scanner)
                    break
                case 3:
                    getCompetenceById(scanner)
                    break
                case 4:
                    deleteCompetence(scanner)
                    break
                case 5:
                    println "Saindo..."
                    return
                default:
                    println "Opção inválida!"
            }
        }
    }

    void createCompetence(Scanner scanner) {
        println "Digite o nome da nova competência:"
        String nome = scanner.nextLine()

        Competence competence = new Competence(nome)
        competenceController.createCompetence(competence)
    }

    void updateCompetence(Scanner scanner) {
        println "Digite o ID da competência que deseja atualizar:"
        Integer id = Integer.parseInt(scanner.nextLine())
        Competence existingCompetence = competenceController.getCompetenceById(id)
        if (existingCompetence) {
            println "Digite o novo nome da competência (atual: ${existingCompetence.nome}):"
            String nome = scanner.nextLine()

            Competence updatedCompetence = new Competence(id, nome)
            competenceController.updateCompetence(updatedCompetence)
            println "Competência atualizada com sucesso!"
        } else {
            println "Competência não encontrada."
        }
    }

    void getCompetenceById(Scanner scanner) {
        println "Digite o ID da competência que deseja buscar:"
        Integer id = Integer.parseInt(scanner.nextLine())
        Competence competence = competenceController.getCompetenceById(id)
        if (competence) {
            println "Competência: ${competence.nome}"
        } else {
            println "Competência não encontrada."
        }
    }

    void deleteCompetence(Scanner scanner) {
        println "Digite o ID da competência que deseja remover:"
        Integer id = scanner.nextInt()
        competenceController.deleteCompetence(id)
    }
}

package org.example.Views

import org.example.Controllers.CandidateController
import org.example.Models.Candidate

import java.time.LocalDate

class CandidateView {
    private final CandidateController candidateController

    CandidateView(CandidateController candidateController) {
        this.candidateController = candidateController
    }

    void displayMenu() {
        Scanner scanner = new Scanner(System.in)

        while (true) {
            println "==== Menu de Candidatos ===="
            println "1. Criar novo candidato"
            println "2. Atualizar candidato"
            println "3. Buscar candidato por ID"
            println "4. Deletar candidato"
            println "5. Voltar"
            print "Escolha uma opção: "
            def option = scanner.nextInt()
            scanner.nextLine() // Consumir a quebra de linha

            switch (option) {
                case 1:
                    createCandidate(scanner)
                    break
                case 2:
                    updateCandidate(scanner)
                    break
                case 3:
                    getCandidateById(scanner)
                    break
                case 4:
                    deleteCandidate(scanner)
                    break
                case 5:
                    println "Saindo..."
                    return
                default:
                    println "Opção inválida!"
            }
        }
    }

    void createCandidate(Scanner scanner) {
        println "== Criar Novo Candidato =="
        print "Nome: "
        String nome = scanner.nextLine()

        print "Data de Nascimento (YYYY-MM-DD): "
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine())

        print "Email: "
        String email = scanner.nextLine()

        print "CPF: "
        String cpf = scanner.nextLine()

        print "País: "
        String pais = scanner.nextLine()

        print "CEP: "
        String cep = scanner.nextLine()

        print "Cargo: "
        String cargo = scanner.nextLine()

        print "Descrição: "
        String descricao = scanner.nextLine()

        print "Senha: "
        String senha = scanner.nextLine()

        Candidate candidate = new Candidate(nome, dataNascimento, email, cpf, pais, cep, cargo, descricao, senha)

        candidateController.createCandidate(candidate)

        println "Candidato criado com sucesso."
    }

    void updateCandidate(Scanner scanner) {
        println "== Atualizar Candidato =="
        print "ID do Candidato: "
        int id = Integer.parseInt(scanner.nextLine())

        Candidate existingCandidate = candidateController.getCandidateById(id)
        if (existingCandidate) {
            print "Nome: "
            String nome = scanner.nextLine()

            print "Data de Nascimento (YYYY-MM-DD): "
            LocalDate dataNascimento = LocalDate.parse(scanner.nextLine())

            print "Email: "
            String email = scanner.nextLine()

            print "CPF: "
            String cpf = scanner.nextLine()

            print "País: "
            String pais = scanner.nextLine()

            print "CEP: "
            String cep = scanner.nextLine()

            print "Cargo: "
            String cargo = scanner.nextLine()

            print "Descrição: "
            String descricao = scanner.nextLine()

            print "Senha: "
            String senha = scanner.nextLine()

            Candidate updatedCandidate = new Candidate(id, nome, dataNascimento, email, cpf, pais, cep, cargo, descricao, senha)

            candidateController.updateCandidate(updatedCandidate)

            println "Candidato atualizado com sucesso."
        } else {
            println "Candidato não encontrado."
        }
    }

    void getCandidateById(Scanner scanner) {
        println "== Buscar Candidato por ID =="
        print "ID do Candidato: "
        Integer id = Integer.parseInt(scanner.nextLine())

        Candidate candidate = candidateController.getCandidateById(id)
        if (candidate) {
            println "Nome: ${candidate.nomeCompleto}"
            println "Cargo: ${candidate.cargo}"
        } else {
            println "Candidato não encontrado."
        }
    }

    void deleteCandidate(Scanner scanner) {
        println "== Deletar Candidato =="
        print "ID do Candidato: "
        int id = Integer.parseInt(scanner.nextLine())
        candidateController.deleteCandidate(id)
        println "Candidato excluído com sucesso."
    }
}

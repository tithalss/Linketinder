package org.example.Views

import org.example.Controllers.CandidateController
import org.example.Models.Candidate

import java.time.LocalDate
import java.util.Scanner

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
        def nome = scanner.nextLine()
        print "Data de Nascimento (YYYY-MM-DD): "
        def dataNascimento = LocalDate.parse(scanner.nextLine())
        print "Email: "
        def email = scanner.nextLine()
        print "CPF: "
        def cpf = scanner.nextLine()
        print "País: "
        def pais = scanner.nextLine()
        print "CEP: "
        def cep = scanner.nextLine()
        print "Cargo: "
        def cargo = scanner.nextLine()
        print "Descrição: "
        def descricao = scanner.nextLine()
        print "Senha: "
        def senha = scanner.nextLine()

        candidateController.createCandidate(nome, dataNascimento, email, cpf, pais, cep, cargo, descricao, senha)
    }

    void updateCandidate(Scanner scanner) {
        println "== Atualizar Candidato =="
        print "ID do Candidato: "
        def id = scanner.nextInt()
        scanner.nextLine()
        print "Nome: "
        def nome = scanner.nextLine()
        print "Data de Nascimento (YYYY-MM-DD): "
        def dataNascimento = LocalDate.parse(scanner.nextLine())
        print "Email: "
        def email = scanner.nextLine()
        print "CPF: "
        def cpf = scanner.nextLine()
        print "País: "
        def pais = scanner.nextLine()
        print "CEP: "
        def cep = scanner.nextLine()
        print "Cargo: "
        def cargo = scanner.nextLine()
        print "Descrição: "
        def descricao = scanner.nextLine()
        print "Senha: "
        def senha = scanner.nextLine()

        candidateController.updateCandidate(id, nome, dataNascimento, email, cpf, pais, cep, cargo, descricao, senha)
    }

    void getCandidateById(Scanner scanner) {
        println "== Buscar Candidato por ID =="
        print "ID do Candidato: "
        Integer id = scanner.nextInt()
        scanner.nextLine()
        Candidate candidate = candidateController.getCandidateById(id)
        if (candidate) {
            println "Nome: ${candidate.nomeCompleto}"
            println ""
        }
    }

    void deleteCandidate(Scanner scanner) {
        println "== Deletar Candidato =="
        print "ID do Candidato: "
        def id = scanner.nextInt()
        scanner.nextLine()
        candidateController.deleteCandidate(id)
    }
}

package org.example

import org.example.ClassesDAO.*
import org.example.Services.AuthenticationService

import java.time.LocalDate

class Main {
    static void main(String[] args) {
        def scanner = new Scanner(System.in)
        boolean exit = false

        while (!exit) {
            printMenu()

            String input = scanner.nextLine().trim()

            switch (input) {
                case "1":
                    handleLogin(scanner)
                    break

                case "2":
                    handleCandidateCRUD(scanner)
                    break

                case "3":
                    handleCompanyCRUD(scanner)
                    break

                case "4":
                    handleJobCRUD(scanner)
                    break

                case "5":
                    println "Saindo..."
                    exit = true
                    break

                default:
                    println "Opção inválida. Tente novamente."
                    break
            }
        }
    }

    static void printMenu() {
        println """
        1 - Login
        2 - CRUD Candidato
        3 - CRUD Empresa
        4 - CRUD Vaga
        5 - Sair
        Escolha uma opção: 
        """
    }

    static void handleLogin(Scanner scanner) {
        println "Email: "
        String userEmail = scanner.nextLine().trim()
        println "Senha: "
        String userPassword = scanner.nextLine().trim()

        def userId = AuthenticationService.authenticate(userEmail, userPassword)

        if (userId != null) {
            println "Login efetuado.\n"
            List matchs = LikeDAO.getMatchsForCompany(userId)
            matchs.each { println(it) }
        } else {
            println "Email ou senha inválidos. Tente novamente."
        }
    }

    static void handleCandidateCRUD(Scanner scanner) {
        println "Digite qual operação deseja realizar (Create, Read, Update, Delete):"
        String opcao = scanner.nextLine().trim().toUpperCase()

        switch (opcao) {
            case "CREATE":
                createCandidate(scanner)
                break

            case "READ":
                readCandidate(scanner)
                break

            case "UPDATE":
                updateCandidate(scanner)
                break

            case "DELETE":
                deleteCandidate(scanner)
                break

            default:
                println "Opção inválida."
                break
        }
    }

    static void createCandidate(Scanner scanner) {
        println "Nome completo: "
        String nome = scanner.nextLine().trim()
        println "Data de nascimento (AAAA-MM-DD): "
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine().trim())
        println "Email: "
        String email = scanner.nextLine().trim()
        println "CPF: "
        String cpf = scanner.nextLine().trim()
        println "País: "
        String pais = scanner.nextLine().trim()
        println "CEP: "
        String cep = scanner.nextLine().trim()
        println "Cargo desejado: "
        String cargo = scanner.nextLine().trim()
        println "Descrição pessoal/profissional: "
        String descricao = scanner.nextLine().trim()
        println "Senha: "
        String senha = scanner.nextLine().trim()

        new CandidateDAO().createCandidate(nome, dataNascimento, email, cpf, pais, cep, cargo, descricao, senha)
    }

    static void readCandidate(Scanner scanner) {
        println "Informe o ID do candidato: "
        int idCandidato = scanner.nextInt()
        scanner.nextLine()

        Candidate candidato = new CandidateDAO().getCandidateById(idCandidato)
        println(candidato)
    }

    static void updateCandidate(Scanner scanner) {
        println "Informe o ID do candidato a ser atualizado: "
        int idCandidato = scanner.nextInt()
        scanner.nextLine()

        println "Novo nome completo: "
        String nome = scanner.nextLine().trim()
        println "Nova data de nascimento (AAAA-MM-DD): "
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine().trim())
        println "Novo email: "
        String email = scanner.nextLine().trim()
        println "Novo CPF: "
        String cpf = scanner.nextLine().trim()
        println "Novo país: "
        String pais = scanner.nextLine().trim()
        println "Novo CEP: "
        String cep = scanner.nextLine().trim()
        println "Novo cargo desejado: "
        String cargo = scanner.nextLine().trim()
        println "Nova descrição pessoal/profissional: "
        String descricao = scanner.nextLine().trim()
        println "Nova senha: "
        String senha = scanner.nextLine().trim()

        new CandidateDAO().updateCandidate(idCandidato, nome, dataNascimento, email, cpf, pais, cep, cargo, descricao, senha)
    }

    static void deleteCandidate(Scanner scanner) {
        println "Informe o ID do candidato a ser deletado: "
        int idCandidato = scanner.nextInt()
        scanner.nextLine()

        new CandidateDAO().deleteCandidate(idCandidato)
    }

    static void handleCompanyCRUD(Scanner scanner) {
        println "Digite qual operação deseja realizar (Create, Read, Update, Delete):"
        String opcao = scanner.nextLine().trim().toUpperCase()

        switch (opcao) {
            case "CREATE":
                createCompany(scanner)
                break

            case "READ":
                readCompany(scanner)
                break

            case "UPDATE":
                updateCompany(scanner)
                break

            case "DELETE":
                deleteCompany(scanner)
                break

            default:
                println "Opção inválida."
                break
        }
    }

    static void createCompany(Scanner scanner) {
        println "Nome da empresa: "
        String nome = scanner.nextLine().trim()
        println "CNPJ: "
        String cnpj = scanner.nextLine().trim()
        println "Email: "
        String email = scanner.nextLine().trim()
        println "Descrição da empresa: "
        String descricao = scanner.nextLine().trim()
        println "País: "
        String pais = scanner.nextLine().trim()
        println "CEP: "
        String cep = scanner.nextLine().trim()
        println "Senha: "
        String senha = scanner.nextLine().trim()

        new CompanyDAO().createCompany(nome, cnpj, email, descricao, pais, cep, senha)
    }

    static void readCompany(Scanner scanner) {
        println "Informe o ID da empresa: "
        int idEmpresa = scanner.nextInt()
        scanner.nextLine()

        Company empresa = new CompanyDAO().getCompanyById(idEmpresa)
        println(empresa)
    }

    static void updateCompany(Scanner scanner) {
        println "Informe o ID da empresa a ser atualizada: "
        int idEmpresa = scanner.nextInt()
        scanner.nextLine()

        println "Novo nome da empresa: "
        String nome = scanner.nextLine().trim()
        println "Novo CNPJ: "
        String cnpj = scanner.nextLine().trim()
        println "Novo email: "
        String email = scanner.nextLine().trim()
        println "Nova descrição da empresa: "
        String descricao = scanner.nextLine().trim()
        println "Novo país: "
        String pais = scanner.nextLine().trim()
        println "Novo CEP: "
        String cep = scanner.nextLine().trim()
        println "Nova senha: "
        String senha = scanner.nextLine().trim()

        new CompanyDAO().updateCompany(idEmpresa, nome, cnpj, email, descricao, pais, cep, senha)
    }

    static void deleteCompany(Scanner scanner) {
        println "Informe o ID da empresa a ser deletada: "
        int idEmpresa = scanner.nextInt()
        scanner.nextLine()

        new CompanyDAO().deleteCompany(idEmpresa)
    }

    static void handleJobCRUD(Scanner scanner) {
        println "Digite qual operação deseja realizar (Create, Read, Update, Delete):"
        String opcao = scanner.nextLine().trim().toUpperCase()

        switch (opcao) {
            case "CREATE":
                createJob(scanner)
                break

            case "READ":
                readJob(scanner)
                break

            case "UPDATE":
                updateJob(scanner)
                break

            case "DELETE":
                deleteJob(scanner)
                break

            default:
                println "Opção inválida."
                break
        }
    }

    static void createJob(Scanner scanner) {
        println "Cargo: "
        String cargo = scanner.nextLine().trim()
        println "Descrição da vaga: "
        String descricao = scanner.nextLine().trim()
        println "Local de trabalho: "
        String local = scanner.nextLine().trim()
        println "ID da empresa: "
        int idEmpresa = scanner.nextInt()
        scanner.nextLine()

        new JobDAO().createJob(cargo, descricao, local, idEmpresa)
    }

    static void readJob(Scanner scanner) {
        println "Informe o ID da vaga: "
        int idVaga = scanner.nextInt()
        scanner.nextLine()

        def vaga = new JobDAO().getJobById(idVaga)
        println(vaga)
    }

    static void updateJob(Scanner scanner) {
        println "Informe o ID da vaga a ser atualizada: "
        int idVaga = scanner.nextInt()
        scanner.nextLine()

        println "Novo cargo: "
        String cargo = scanner.nextLine().trim()
        println "Nova descrição da vaga: "
        String descricao = scanner.nextLine().trim()
        println "Novo local de trabalho: "
        String local = scanner.nextLine().trim()
        println "ID da empresa: "
        int idEmpresa = scanner.nextInt()
        scanner.nextLine()

        new JobDAO().updateJob(idVaga, cargo, descricao, local, idEmpresa)
    }

    static void deleteJob(Scanner scanner) {
        println "Informe o ID da vaga a ser deletada: "
        int idVaga = scanner.nextInt()
        scanner.nextLine()

        new JobDAO().deleteJob(idVaga)
    }
}

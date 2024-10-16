package org.example

import org.example.ClassesDAO.*
import org.example.Controllers.*
import org.example.Services.AuthenticationService
import org.example.Views.*

import java.sql.Connection

class Main {
    static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection()
        def scanner = new Scanner(System.in)
        boolean exit = false

        while (!exit) {
            printMenu()

            String input = scanner.nextLine().trim()

            switch (input) {
                case "1":
                    CandidateDAO candidateDAO = new CandidateDAO(connection)

                    AuthenticationService authService = new AuthenticationService(connection)

                    LoginController loginController = new LoginController(authService, candidateDAO)

                    LoginView loginView = new LoginView(loginController)

                    println "Digite seu email:"
                    String email = scanner.nextLine().trim()

                    println "Digite sua senha:"
                    String senha = scanner.nextLine().trim()

                    loginView.displayLogin(email, senha)
                    break

                case "2":
                    CandidateDAO candidateDAO = new CandidateDAO(connection)

                    CandidateController candidateController = new CandidateController(candidateDAO)

                    CandidateView candidateView = new CandidateView(candidateController)

                    candidateView.displayMenu()
                    break

                case "3":
                    CompanyDAO companyDAO = new CompanyDAO(connection)

                    CompanyController companyController = new CompanyController(companyDAO)

                    CompanyView companyView = new CompanyView(companyController)

                    companyView.displayMenu()
                    break

                case "4":
                    JobDAO jobDAO = new JobDAO(connection)

                    JobController jobController = new JobController(jobDAO)

                    JobView jobView = new JobView(jobController)

                    jobView.displayMenu()
                    break

                case "5":
                    CompetenceDAO competenceDAO = new CompetenceDAO(connection)

                    CompetenceController competenceController = new CompetenceController(competenceDAO)

                    CompetenceView competenceView = new CompetenceView(competenceController)

                    competenceView.displayMenu()
                    break

                case "6":
                    println "Saindo..."
                    exit = true
                    ConnectionFactory.closeConnection()
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
        5 - CRUD Competência
        6 - Sair
        Escolha uma opção: 
        """
    }
}

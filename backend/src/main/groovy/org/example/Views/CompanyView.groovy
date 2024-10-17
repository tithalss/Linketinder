package org.example.Views

import org.example.Controllers.CompanyController
import org.example.Models.Company

class CompanyView {
    private final CompanyController companyController

    CompanyView(CompanyController companyController) {
        this.companyController = companyController
    }

    void displayMenu() {
        Scanner scanner = new Scanner(System.in)

        while (true) {
            println "==== Menu de Empresas ===="
            println "1. Criar nova empresa"
            println "2. Atualizar empresa"
            println "3. Buscar empresa por ID"
            println "4. Deletar empresa"
            println "5. Voltar"
            print "Escolha uma opção: "
            def option = scanner.nextInt()
            scanner.nextLine() // Consumir a quebra de linha

            switch (option) {
                case 1:
                    createCompany(scanner)
                    break
                case 2:
                    updateCompany(scanner)
                    break
                case 3:
                    getCompanyById(scanner)
                    break
                case 4:
                    deleteCompany(scanner)
                    break
                case 5:
                    println "Saindo..."
                    return
                default:
                    println "Opção inválida!"
            }
        }
    }

    void createCompany(Scanner scanner) {
        println "== Criar Nova Empresa =="
        print "Nome: "
        String nome = scanner.nextLine()

        print "CNPJ: "
        String cnpj = scanner.nextLine()

        print "Email: "
        String email = scanner.nextLine()

        print "Descrição: "
        String descricao = scanner.nextLine()

        print "País: "
        String pais = scanner.nextLine()

        print "CEP: "
        String cep = scanner.nextLine()

        print "Senha: "
        String senha = scanner.nextLine()

        Company company = new Company(nome, cnpj, email, descricao, pais, cep, senha)

        companyController.createCompany(company)

        println "Empresa criada com sucesso."
    }

    void updateCompany(Scanner scanner) {
        println "== Atualizar Empresa =="
        print "ID da Empresa: "
        int id = Integer.parseInt(scanner.nextLine())

        Company existingCompany = companyController.getCompanyById(id)
        if (existingCompany) {
            print "Nome: "
            String nome = scanner.nextLine()

            print "CNPJ: "
            String cnpj = scanner.nextLine()

            print "Email: "
            String email = scanner.nextLine()

            print "Descrição: "
            String descricao = scanner.nextLine()

            print "País: "
            String pais = scanner.nextLine()

            print "CEP: "
            String cep = scanner.nextLine()

            print "Senha: "
            String senha = scanner.nextLine()

            Company updatedCompany = new Company(nome, cnpj, email, descricao, pais, cep, senha)

            companyController.updateCompany(updatedCompany)

            println "Empresa atualizada com sucesso."
        } else {
            println "Empresa não encontrada."
        }
    }

    void getCompanyById(Scanner scanner) {
        println "== Buscar Empresa por ID =="
        print "ID da Empresa: "
        int id = Integer.parseInt(scanner.nextLine())

        Company company = companyController.getCompanyById(id)
        if (company) {
            println "Nome: ${company.nome}"
            println "Descrição: ${company.descricao}"
        } else {
            println "Empresa não encontrada."
        }
    }

    void deleteCompany(Scanner scanner) {
        println "== Deletar Empresa =="
        print "ID da Empresa: "
        int id = Integer.parseInt(scanner.nextLine())
        companyController.deleteCompany(id)
        println "Empresa excluída com sucesso."
    }
}

package org.example.Views

import org.example.Controllers.CompanyController

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
        def nome = scanner.nextLine()
        print "CNPJ: "
        def cnpj = scanner.nextLine()
        print "Email: "
        def email = scanner.nextLine()
        print "Descrição: "
        def descricao = scanner.nextLine()
        print "País: "
        def pais = scanner.nextLine()
        print "CEP: "
        def cep = scanner.nextLine()
        print "Senha: "
        def senha = scanner.nextLine()

        companyController.createCompany(nome, cnpj, email, descricao, pais, cep, senha)
    }

    void updateCompany(Scanner scanner) {
        println "== Atualizar Empresa =="
        print "ID da Empresa: "
        def id = scanner.nextInt()
        scanner.nextLine() // Consumir a quebra de linha
        print "Nome: "
        def nome = scanner.nextLine()
        print "CNPJ: "
        def cnpj = scanner.nextLine()
        print "Email: "
        def email = scanner.nextLine()
        print "Descrição: "
        def descricao = scanner.nextLine()
        print "País: "
        def pais = scanner.nextLine()
        print "CEP: "
        def cep = scanner.nextLine()
        print "Senha: "
        def senha = scanner.nextLine()

        companyController.updateCompany(id, nome, cnpj, email, descricao, pais, cep, senha)
    }

    void getCompanyById(Scanner scanner) {
        println "== Buscar Empresa por ID =="
        print "ID da Empresa: "
        def id = scanner.nextInt()
        scanner.nextLine() // Consumir a quebra de linha
        companyController.getCompanyById(id)
    }

    void deleteCompany(Scanner scanner) {
        println "== Deletar Empresa =="
        print "ID da Empresa: "
        def id = scanner.nextInt()
        scanner.nextLine() // Consumir a quebra de linha
        companyController.deleteCompany(id)
    }
}

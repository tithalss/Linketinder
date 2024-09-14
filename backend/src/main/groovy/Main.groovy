import java.time.LocalDate

class Main {
    static void main(String[] args) {
        def scanner = new Scanner(System.in)

        while (true) {
            println "1 - Login"
            println "2 - CRUD candidato"
            println "3 - CRUD empresa"
            println "4 - CRUD vaga"
            println "5 - Sair"
            println "Escolha uma opção: "

            def input = scanner.nextLine()

            switch (input) {
                case "1":
                    println "Email: "
                    def userEmail = scanner.nextLine()
                    println "Senha: "
                    def userPassword = scanner.nextLine()
                    def userId = AuthenticationService.authenticate(userEmail, userPassword)
                    if (userId != null) {
                        println "Login efetuado."
                    } else {
                        println "Email ou senha inválidos. Tente novamente."
                    }
                    break

                //CRUD do candidato
                case "2":
                    println "Digite qual operação deseja realizar Create, Read, Update ou Delete"
                    def opcao = scanner.nextLine().toUpperCase()

                    switch (opcao) {
                        case "CREATE":
                            println "Nome completo: "
                            String nome = scanner.nextLine()
                            println "Data de nascimento: "
                            LocalDate dataNascimento = LocalDate.parse(scanner.nextLine())
                            println "email: "
                            String email = scanner.nextLine()
                            println "CPF: "
                            String cpf = scanner.nextLine()
                            println "País: "
                            String pais = scanner.nextLine()
                            println "CEP: "
                            String cep = scanner.nextLine()
                            println "Cargo desejado: "
                            String cargo = scanner.nextLine()
                            println "Descrição pessoal/profissional: "
                            String descricao = scanner.nextLine()
                            println "Senha: "
                            String senha = scanner.nextLine()

                            new CandidateDAO().createCandidate(nome, dataNascimento, email, cpf, pais, cep, cargo, descricao, senha)

                            break

                        case "READ":
                            println "Informe o id do candidato: "
                            Integer id_candidato = scanner.nextInt()
                            scanner.nextLine()

                            def candidato = new CandidateDAO().getCandidateById(id_candidato)

                            println(candidato)

                            break

                        case "UPDATE":
                            println "Informe o id do cadidato a ser atualizado: "
                            Integer id_candidato = scanner.nextInt()
                            scanner.nextLine()
                            println "Novo nome completo: "
                            String nome = scanner.nextLine()
                            println "Nova data de nascimento: "
                            LocalDate dataNascimento = LocalDate.parse(scanner.nextLine())
                            println "Novo email: "
                            String email = scanner.nextLine()
                            println "Novo CPF: "
                            String cpf = scanner.nextLine()
                            println "Novo país: "
                            String pais = scanner.nextLine()
                            println "Novo CEP: "
                            String cep = scanner.nextLine()
                            println "Novo cargo desejado: "
                            String cargo = scanner.nextLine()
                            println "Nova descrição pessoal/profissional: "
                            String descricao = scanner.nextLine()
                            println "Nova senha: "
                            String senha = scanner.nextLine()

                            new CandidateDAO().updateCandidate(id_candidato, nome, dataNascimento, email, cpf, pais, cep, cargo, descricao, senha)

                            break

                        case "DELETE":
                            println "Informe o ID do candidato que será deletado: "
                            Integer id_candidato = scanner.nextInt()
                            scanner.nextLine()

                            new CandidateDAO().deleteCandidate(id_candidato)

                            break

                        default:
                            println "Opção inválida."
                            break
                    }

                    break

                // CRUD da empresa
                case "3":
                    println "Digite qual operação deseja realizar Create, Read, Update ou Delete"
                    def opcao = scanner.nextLine().toUpperCase()

                    switch (opcao) {
                        case "CREATE":
                            println "Nome da empresa: "
                            String nome = scanner.nextLine()
                            println "CNPJ: "
                            String cnpj = scanner.nextLine()
                            println "email: "
                            String email = scanner.nextLine()
                            println "Descrição da empresa: "
                            String descricao = scanner.nextLine()
                            println "País: "
                            String pais = scanner.nextLine()
                            println "CEP: "
                            String cep = scanner.nextLine()
                            println "Senha: "
                            String senha = scanner.nextLine()

                            new CompanyDAO().createCompany(nome, cnpj, email, descricao, pais, cep, senha)

                            break

                        case "READ":
                            println "Informe o ID da empresa: "
                            Integer id_empresa = scanner.nextInt()
                            scanner.nextLine()

                            def empresa = new CompanyDAO().getCompanyById(id_empresa)

                            println(empresa)

                            break

                        case "UPDATE":
                            println "Informe o id do cadidato a ser atualizado: "
                            Integer id_empresa = scanner.nextInt()
                            scanner.nextLine()
                            println "Novo nome da empresa: "
                            String nome = scanner.nextLine()
                            println "Novo CNPJ: "
                            String cnpj = scanner.nextLine()
                            println "Novo email: "
                            String email = scanner.nextLine()
                            println "Nova descrição da empresa: "
                            String descricao = scanner.nextLine()
                            println "Novo país: "
                            String pais = scanner.nextLine()
                            println "Novo CEP: "
                            String cep = scanner.nextLine()
                            println "Nova senha: "
                            String senha = scanner.nextLine()

                            new CompanyDAO().updateCompany(id_empresa, nome, cnpj, email, descricao, pais, cep, senha)

                            break

                        case "DELETE":
                            println "Informe o ID da empresa que será deletada: "
                            Integer id_empresa = scanner.nextInt()
                            scanner.nextLine()

                            new CompanyDAO().deleteCompany(id_empresa)

                            break

                        default:
                            println "Opção inválida."
                            break
                    }

                // CRUD da vaga
                case "4":
                    println "Digite qual operação deseja realizar Create, Read, Update ou Delete"
                    def opcao = scanner.nextLine().toUpperCase()

                    switch (opcao) {
                        case "CREATE":
                            println "Nome da empresa: "
                            String nome = scanner.nextLine()
                            println "CNPJ: "
                            String cnpj = scanner.nextLine()
                            println "email: "
                            String email = scanner.nextLine()
                            println "Descrição da empresa: "
                            String descricao = scanner.nextLine()
                            println "País: "
                            String pais = scanner.nextLine()
                            println "CEP: "
                            String cep = scanner.nextLine()
                            println "Senha: "
                            String senha = scanner.nextLine()

                            new CompanyDAO().createCompany(nome, cnpj, email, descricao, pais, cep, senha)

                            break

                        case "READ":
                            println "Informe o id do candidato: "
                            Integer id_candidato = scanner.nextInt()
                            scanner.nextLine()

                            def candidato = new CandidateDAO().getCandidateById(id_candidato)

                            println(candidato)

                            break

                        case "UPDATE":
                            println "Informe o id do cadidato a ser atualizado: "
                            Integer id_candidato = scanner.nextInt()
                            scanner.nextLine()
                            println "Novo nome completo: "
                            String nome = scanner.nextLine()
                            println "Nova data de nascimento: "
                            LocalDate dataNascimento = LocalDate.parse(scanner.nextLine())
                            println "Novo email: "
                            String email = scanner.nextLine()
                            println "Novo CPF: "
                            String cpf = scanner.nextLine()
                            println "Novo país: "
                            String pais = scanner.nextLine()
                            println "Novo CEP: "
                            String cep = scanner.nextLine()
                            println "Novo cargo desejado: "
                            String cargo = scanner.nextLine()
                            println "Nova descrição pessoal/profissional: "
                            String descricao = scanner.nextLine()
                            println "Nova senha: "
                            String senha = scanner.nextLine()

                            new CandidateDAO().updateCandidate(id_candidato, nome, dataNascimento, email, cpf, pais, cep, cargo, descricao, senha)

                            break

                        case "DELETE":
                            println "Informe o ID do candidato que será deletado: "
                            Integer id_candidato = scanner.nextInt()
                            scanner.nextLine()

                            new CandidateDAO().deleteCandidate(id_candidato)

                            break

                        default:
                            println "Opção inválida."
                            break
                    }

                case "5":
                    println "Saindo..."
                    System.exit(0)

                default:
                    println "Opção inválida."
            }
        }
    }
}

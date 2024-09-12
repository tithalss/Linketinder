import java.time.LocalDate

class Main {
    static void main(String[] args) {
        def scanner = new Scanner(System.in)

        while (true) {
            println "1 - Login"
            println "2 - Cadastro de usuário"
            println "3 - Cadastro de empresa"
            println "4 - Sair"
            println "Escolha uma opção: "

            def input = scanner.nextLine()
            switch (input) {
                case "1":
                    println "Email: "
                    def userLogin = scanner.nextLine()
                    println "Senha: "
                    def userPassword = scanner.nextLine()
                    login(userLogin, userPassword)
                    break

                case "2":
                    println "Nome completo: "
                    String nome = scanner.nextLine()
                    println "Date de nascimento: "
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
                case "3":
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
                case "4":
                    println "Saindo..."
                    System.exit(0)
                default:
                    println "Opção inválida."
            }
        }
    }
}

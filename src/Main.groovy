class Main {
    static def listaCandidatos = [
            new Candidato("Cristiano Ronaldo", "cr7@outlook.com", "777.777.777-77", 39, "PT", "01234-567", "Python Developer", ["Python", "Django", "Flask", "Tensorflow"]),
            new Candidato("Michael Corleone", "thegodfather@acelerazg.com.br", "123.777.777-77", 59, "IT", "01235-568", "Data Scientist", ["Python", "R", "Machine Learning", "Tensorflow"]),
            new Candidato("Tony Stark", "ironman@starkindustries.com", "222.333.444-55", 48, "US", "56789-012", "Software Engineer", ["C++", "Java", "Artificial Intelligence", "Quantum Computing"]),
            new Candidato("Walter Kovacs", "rorsh@watch.com", "888.999.000-11", 52, "US", "34567-890", "Chemistry Expert", ["Chemistry", "Python", "Data Analysis", "Pharmaceuticals"]),
            new Candidato("Robert Plant", "robertoplanta@led.edu", "555.666.777-88", 35, "UK", "23456-789", "Full Stack Developer", ["JavaScript", "Java", "React", "Spring"])
    ]

    static def listaEmpresas = [
            new Empresa("Microsoft", "microsoft@outlook.com", "11.111.111/1111-11", "US", "WA", "01234-569", "Tech Company", ["C#", ".NET", "Azure", "SQL Server"]),
            new Empresa("Wayne Enterprises", "wayne@wayneenterprises.com", "22.222.222/2222-22", "US", "Gotham", "67890-123", "Advanced Tech and R&D", ["Java", "Python", "AI", "Cybersecurity"]),
            new Empresa("Stark Industries", "info@starkindustries.com", "33.333.333/3333-33", "US", "NY", "34567-890", "Engineering and Defense", ["C++", "Mechanical Engineering", "AI", "Robotics"]),
            new Empresa("Umbrella Corporation", "umbrella@corporation.com", "44.444.444/4444-44", "US", "Raccoon City", "98765-432", "Pharmaceuticals", ["Biotechnology", "Pharmaceuticals", "AI", "Data Science"]),
            new Empresa("Acme Corp", "acme@cartoon.com", "55.555.555/5555-55", "FC", "Unknown", "54321-098", "Deep trip", ["Java", "Spring", "Oracle", "Cryptography"])
    ]

    static def listarCandidatos() {
        println "Candidatos: "
        listaCandidatos.each { println it }
    }

    static def listarEmpresas() {
        println "Empresas: "
        listaEmpresas.each { println it }
    }

    static void main(String[] args) {
        def scanner = new Scanner(System.in)

        while (true) {
            println "1 - Listar candidatos"
            println "2 - Listar empresas"
            println "3 - Sair"
            println "Escolha uma opção: "

            def input = scanner.nextLine()
            switch (input) {
                case "1":
                    listarCandidatos()
                    break
                case "2":
                    listarEmpresas()
                    break
                case "3":
                    println "Saindo..."
                    System.exit(0)
                default:
                    println "Opção inválida."
            }
        }
    }
}

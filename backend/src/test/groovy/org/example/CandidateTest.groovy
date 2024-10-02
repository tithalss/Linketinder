package org.example

class CandidateTest {

    static void main (String [] args) {
        metodoDeTesteCandidato()
        println "Os testes passaram."
    }

    static void metodoDeTesteCandidato() {
        String nomeCompleto = "Thales Martins"
        String email = "thales.martins@outlook.com"
        String cpf = "123.456.789-10"
        int idade = 24
        String estado = "DF"
        String cep = "71996-242"
        String descricao = "Back-end Developer"
        List<String> competencias = ["Java", "Groovy", "Postgre"]

        Candidate candidato = new Candidate(nomeCompleto, email, cpf, idade, estado, cep, descricao, competencias)

        assert candidato.nomeCompleto == nomeCompleto : "Falha no teste. Nome não atribuído corretamente"
        assert candidato.email == email : "Falha no teste. Email não atribuído corretamente"
        assert candidato.cpf == cpf : "Falha no teste. CPF não atribuído corretamente"
        assert candidato.idade == idade : "Falha no teste. Idade não atribuída corretamente"
        assert candidato.estado == estado : "Falha no teste. Estado não atribuído corretamente"
        assert candidato.cep == cep : "Falha no teste. CEP não atribuído corretamente"
        assert candidato.descricao == descricao : "Falha no teste. Descrição não atribuída corretamente"
        assert candidato.competencias == competencias : "Falha no teste. Competências não atribuídas corretamente"

        String resultado = candidato.toString()

        String esperado = "Nome: Thales Martins, Email: thales.martins@outlook.com, CPF: 123.456.789-10, Idade: 24, Estado: DF, CEP: 71996-242, Descrição: Back-end Developer, Habilidades: Java, Groovy, Postgre"
        assert resultado == esperado : "O método 'toString' não retornou o valor esperado"
    }
}

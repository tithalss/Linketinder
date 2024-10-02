package org.example

class CompanyTest {

    static void main (String[] args) {
        metodoDeTesteEmpresa()
        println "Os testes passaram."
    }

    static void metodoDeTesteEmpresa() {
        String nome = "ZG Soluçoes"
        String email = "timecomercial@zgsolucoes.com.br"
        String cnpj = "14.488.144/0001-43"
        String pais = "Brasil"
        String estado = "GO"
        String cep = "74070-040"
        String descricao = "Serviços de tecnologia da informação"
        List<String> competencias = ["Java", "Groovy", "Postgre"]

        Company empresa = new Company(nome, email, cnpj, pais, estado, cep, descricao, competencias)

        assert empresa.nome == nome : "Falha no teste. Nome não atribuído corretamente"
        assert empresa.email == email : "Falha no teste. Email não atribuído corretamente"
        assert empresa.cnpj == cnpj : "Falha no teste. CNPJ não atribuído corretamente"
        assert empresa.pais == pais : "Falha no teste. Pais não atribuído corretamente"
        assert empresa.estado == estado : "Falha no teste. Estado não atribuído corretamente"
        assert empresa.cep == cep : "Falha no teste. CEP não atribuído corretamente"
        assert empresa.descricao == descricao : "Falha no teste. Descrição não atribuída corretamente"
        assert empresa.competencias == competencias : "Falha no teste. Competências não atribuídas corretamente"

        String resultado = empresa.toString()

        String esperado = "Nome: ZG Soluções, Email: timecomercial@zgsolucoes.com.br, CNPJ: 14.488.144/0001-43, País: Brasil, Estado: GO, CEP: 74070-040, Descrição: Serviços de tecnologia da informação, Competências desejadas: Java, Groovy, Postgre"
        assert resultado == esperado : "O método 'toString' não retornou o valor esperado"
    }
}

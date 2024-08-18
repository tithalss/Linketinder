class Empresa {
    String nome
    String email
    String cnpj
    String pais
    String estado
    String cep
    String descricao
    List<String> competencias

    Empresa(String nome, String email, String cnpj, String pais, String estado, String cep, String descricao, List<String> competencias) {
        this.nome = nome
        this.email = email
        this.cnpj = cnpj
        this.pais = pais
        this.estado = estado
        this.cep = cep
        this.descricao = descricao
        this.competencias = competencias
    }

    @Override
    String toString() {
        return "Nome: ${nome}, Email: ${email}, CNPJ: ${cnpj}, País: ${pais}, Estado: ${estado}, CEP: ${cep}, Descrição: ${descricao}, Competências desejadas: ${competencias.join(', ')}"
    }
}

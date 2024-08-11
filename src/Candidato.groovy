class Candidato {
    String nomeCompleto
    String email
    String cpf
    int idade
    String estado
    String cep
    String descricao
    List<String> competencias

    Candidato (String nomeCompleto, String email, String cpf, int idade, String estado ,String cep, String descricao, List<String> competencias) {
        this.nomeCompleto = nomeCompleto
        this.email = email
        this.cpf = cpf
        this.idade = idade
        this.estado = estado
        this.cep = cep
        this.descricao = descricao
        this.competencias = competencias
    }

    @Override
    String toString() {
        return "Nome: ${nomeCompleto}, Email: ${email}, CPF: ${cpf}, Idade: ${idade}, Estado: ${estado}, CEP: ${cep}, Descrição: ${descricao}, Habilidades: ${competencias.join(', ')}"
    }
}

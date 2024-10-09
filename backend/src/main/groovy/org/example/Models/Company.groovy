package org.example.Models

class Company {
    private int id
    private String nome
    private String email
    private String cnpj
    private String pais
    private String cep
    private String descricao
    private String senha

    Company(int id, String nome, String email, String cnpj, String pais, String cep, String descricao, String senha) {
        this.id = id
        this.nome = nome
        this.email = email
        this.cnpj = cnpj
        this.pais = pais
        this.cep = cep
        this.descricao = descricao
        this.senha = senha
    }

    int getId() {
        return id
    }

    void setId(int id) {
        this.id = id
    }

    String getNome() {
        return nome
    }

    void setNome(String nome) {
        this.nome = nome
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getCnpj() {
        return cnpj
    }

    void setCnpj(String cnpj) {
        this.cnpj = cnpj
    }

    String getPais() {
        return pais
    }

    void setPais(String pais) {
        this.pais = pais
    }

    String getCep() {
        return cep
    }

    void setCep(String cep) {
        this.cep = cep
    }

    String getDescricao() {
        return descricao
    }

    void setDescricao(String descricao) {
        this.descricao = descricao
    }

    String getSenha() {
        return senha
    }

    void setSenha(String senha) {
        this.senha = senha
    }

    @Override
    String toString() {
        return "Company ID: $id\n" + "Nome: $nome\n" + "Email: $email\n" + "CNPJ: $cnpj\n" + "País: $pais\n" + "CEP: $cep\n" + "Descrição: $descricao"
    }
}

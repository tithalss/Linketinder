package org.example.Models

import java.time.LocalDate

class Candidate {
    private int id
    private String nomeCompleto
    private LocalDate dataNascimento
    private String email
    private String cpf
    private String pais
    private String cep
    private String cargo
    private String descricao
    private String senha

    Candidate () {}

    Candidate(int id, String nomeCompleto, LocalDate dataNascimento, String email, String cpf, String pais, String cep, String cargo, String descricao, String senha) {
        this.id = id
        this.nomeCompleto = nomeCompleto
        this.dataNascimento = dataNascimento
        this.email = email
        this.cpf = cpf
        this.pais = pais
        this.cep = cep
        this.cargo = cargo
        this.descricao = descricao
        this.senha = senha
    }

    Candidate(String nomeCompleto, LocalDate dataNascimento, String email, String cpf, String pais, String cep, String cargo, String descricao, String senha) {
        this.nomeCompleto = nomeCompleto
        this.dataNascimento = dataNascimento
        this.email = email
        this.cpf = cpf
        this.pais = pais
        this.cep = cep
        this.cargo = cargo
        this.descricao = descricao
        this.senha = senha
    }

    int getId() {
        return id
    }

    void setId(int id) {
        this.id = id
    }

    String getNomeCompleto() {
        return nomeCompleto
    }

    void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto
    }

    LocalDate getDataNascimento() {
        return dataNascimento
    }

    void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getCpf() {
        return cpf
    }

    void setCpf(String cpf) {
        this.cpf = cpf
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

    String getCargo() {
        return cargo
    }

    void setCargo(String cargo) {
        this.cargo = cargo
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
        return "ID: $id\nNome Completo: $nomeCompleto\nData de Nascimento: $dataNascimento\nEmail: $email\nCPF: $cpf\nPaís: $pais\nCEP: $cep\nCargo: $cargo\nDescrição: $descricao"
    }
}

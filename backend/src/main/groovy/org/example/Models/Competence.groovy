package org.example.Models

class Competence {
    private int id
    private String nome

    Competence(int id, String nome) {
        this.id = id
        this.nome = nome
    }

    Competence(String nome) {
        this.nome = nome
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

    @Override
    String toString() {
        return "ID: $id\nNome da Competência: $nome"
    }
}

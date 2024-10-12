package org.example.Models

class Job {
    private int id
    private String cargo
    private String descricao
    private String local
    private int id_empresa

    Job(int id, String cargo, String descricao, String local, int id_empresa) {
        this.id = id
        this.cargo = cargo
        this.descricao = descricao
        this.local = local
        this.id_empresa = id_empresa
    }

    Job(String cargo, String descricao, String local, int id_empresa) {
        this.cargo = cargo
        this.descricao = descricao
        this.local = local
        this.id_empresa = id_empresa
    }

    int getId() {
        return id
    }

    void setId(int id) {
        this.id = id
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

    String getLocal() {
        return local
    }

    void setLocal(String local) {
        this.local = local
    }

    int getIdEmpresa() {
        return id_empresa
    }

    void setIdEmpresa(int id_empresa) {
        this.id_empresa = id_empresa
    }

    @Override
    String toString() {
        return "ID: $id\n" +
                "Cargo: $cargo\n" +
                "Descrição da vaga: $descricao\n" +
                "Local da vaga: $local\n" +
                "ID da empresa: $id_empresa"
    }
}

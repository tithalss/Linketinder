package org.example.Models

class LoginRequest {
    private String email
    private String senha

    LoginRequest(String email, String senha) {
        this.email = email
        this.senha = senha
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getSenha() {
        return senha
    }

    void setSenha(String senha) {
        this.senha = senha
    }

    @Override
    String toString() {
        return "Email: $email, Senha: $senha"
    }
}

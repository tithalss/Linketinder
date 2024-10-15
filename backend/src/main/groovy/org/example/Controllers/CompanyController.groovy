package org.example.Controllers

import org.example.ClassesDAO.CompanyDAO
import org.example.Models.Company

class CompanyController {
    private final CompanyDAO companyDAO

    CompanyController(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO
    }

    void createCompany(String nome, String cnpj, String email, String descricao, String pais, String cep, String senha) {
        def company = new Company(nome, cnpj, email, descricao, pais, cep, senha)
        companyDAO.create(company)
        println "Empresa criada com sucesso!"
    }

    void updateCompany(int id, String nome, String cnpj, String email, String descricao, String pais, String cep, String senha) {
        def company = new Company(id, nome, cnpj, email, descricao, pais, cep, senha)
        companyDAO.update(company)
        println "Empresa atualizada com sucesso!"
    }

    void getCompanyById(int id) {
        def company = companyDAO.getById(id)
        if (company != null) {
            println "Empresa encontrada: ${company}"
        } else {
            println "Empresa não encontrada."
        }
    }

    void deleteCompany(int id) {
        companyDAO.delete(id)
        println "Empresa removida com sucesso!"
    }
}

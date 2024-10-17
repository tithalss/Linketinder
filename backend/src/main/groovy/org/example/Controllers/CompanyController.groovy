package org.example.Controllers

import org.example.ClassesDAO.CompanyDAO
import org.example.Models.Company

class CompanyController {
    private final CompanyDAO companyDAO

    CompanyController(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO
    }

    void createCompany(Company company) {
        companyDAO.create(company)
    }

    void updateCompany(Company company) {
        companyDAO.update(company)
    }

    Company getCompanyById(int id) {
        Company company = companyDAO.getById(id)
        if (company != null) {
            return company
        } else {
            return null
        }
    }

    void deleteCompany(int id) {
        companyDAO.delete(id)
    }
}

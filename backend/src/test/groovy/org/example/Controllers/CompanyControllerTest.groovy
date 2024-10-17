package org.example.Controllers

import org.example.ClassesDAO.CompanyDAO
import org.example.Models.Company
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.mockito.Mockito.*

class CompanyControllerTest {

    CompanyDAO companyDAO
    CompanyController companyController

    @BeforeEach
    void setUp() {
        companyDAO = mock(CompanyDAO)
        companyController = new CompanyController(companyDAO)
    }

    @Test
    void testCreateCompany() {
        Company company = new Company(1, "Tech Corp", "techcorp@gmail.com", "11254655234512", "Brasil", "95995245", "T.I.", "senhatech")

        companyController.createCompany(company)

        verify(companyDAO).create(company)
    }

    @Test
    void testUpdateCompany() {
        Company company = new Company(1, "Tech Corp", "techcorp@gmail.com", "11254655234512", "Brasil", "95995245", "T.I.", "senhatech")

        companyController.updateCompany(company)

        verify(companyDAO).update(company)
    }

    @Test
    void testGetCompanyById() {
        Company company = new Company(1, "Tech Corp", "techcorp@gmail.com", "11254655234512", "Brasil", "95995245", "T.I.", "senhatech")
        when(companyDAO.getById(1)).thenReturn(company)

        Company result = companyController.getCompanyById(1)

        assert result == company
        verify(companyDAO).getById(1)
    }

    @Test
    void testGetCompanyByIdNotFound() {
        when(companyDAO.getById(1)).thenReturn(null)

        Company result = companyController.getCompanyById(1)

        assert result == null
        verify(companyDAO).getById(1)
    }

    @Test
    void testDeleteCompany() {
        companyController.deleteCompany(1)

        verify(companyDAO).delete(1)
    }
}

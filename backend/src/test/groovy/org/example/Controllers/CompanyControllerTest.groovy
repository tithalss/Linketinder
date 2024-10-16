package org.example.Controllers

import org.example.ClassesDAO.CompanyDAO
import org.example.Models.Company
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import static org.mockito.Mockito.*

class CompanyControllerTest {

    @Mock
    CompanyDAO companyDAO

    @InjectMocks
    CompanyController companyController

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    void testCreateCompany() {
        String nome = "Tech Company"
        String cnpj = "12345678000100"
        String email = "contato@techcompany.com"
        String descricao = "Desenvolvimento de software"
        String pais = "Brasil"
        String cep = "12345678"
        String senha = "senhaSegura123"

        companyController.createCompany(nome, cnpj, email, descricao, pais, cep, senha)

        verify(companyDAO, times(1)).create(any(Company))
    }

    @Test
    void testUpdateCompany() {
        int id = 1
        String nome = "Tech Company Updated"
        String cnpj = "12345678000100"
        String email = "updated@techcompany.com"
        String descricao = "Desenvolvimento de software atualizado"
        String pais = "Brasil"
        String cep = "12345678"
        String senha = "novaSenhaSegura123"

        companyController.updateCompany(id, nome, cnpj, email, descricao, pais, cep, senha)

        verify(companyDAO, times(1)).update(any(Company))
    }

    @Test
    void testGetCompanyById() {
        def company = new Company(1, "Tech Company", "12345678000100", "contato@techcompany.com", "Desenvolvimento de software", "Brasil", "12345-678", "senhaSegura123")
        when(companyDAO.getById(1)).thenReturn(company)

        companyController.getCompanyById(1)

        verify(companyDAO, times(1)).getById(1)
    }

    @Test
    void testDeleteCompany() {
        def id = 1

        companyController.deleteCompany(id)

        verify(companyDAO, times(1)).delete(id)
    }
}

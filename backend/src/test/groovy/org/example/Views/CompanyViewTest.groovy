package org.example.Views

import org.example.Controllers.CompanyController
import org.example.Models.Company
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*

class CompanyViewTest {

    @Mock
    CompanyController companyController

    @InjectMocks
    CompanyView companyView

    Scanner mockScanner

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this)
        mockScanner = mock(Scanner)
        companyView = new CompanyView(companyController)
    }

    @Test
    void testCreateCompany() {
        when(mockScanner.nextLine()).thenReturn(
                "Empresa XYZ",
                "12.345.678/0001-99",
                "contato@empresa.com",
                "Descrição da empresa",
                "Brasil",
                "12345-678",
                "senha123"
        )

        companyView.createCompany(mockScanner)

        verify(companyController, times(1)).createCompany(any(Company))
    }

    @Test
    void testGetCompanyById() {
        def company = new Company(1, "Empresa XYZ", "12.345.678/0001-99", "contato@empresa.com", "Descrição da empresa", "Brasil", "12345-678", "senha123")
        when(mockScanner.nextLine()).thenReturn("1")
        when(companyController.getCompanyById(1)).thenReturn(company)

        companyView.getCompanyById(mockScanner)

        verify(companyController, times(1)).getCompanyById(1)
    }

    @Test
    void testUpdateCompany() {
        Company existingCompany = new Company(1, "Empresa XYZ", "12.345.678/0001-99", "contato@empresa.com", "Descrição da empresa", "Brasil", "12345-678", "senha123")
        when(mockScanner.nextLine()).thenReturn("1",
                "Empresa Atualizada",
                "12.345.678/0001-99",
                "atualizado@empresa.com",
                "Nova descrição da empresa",
                "Brasil",
                "12345-678",
                "novaSenha123"
        )
        when(companyController.getCompanyById(1)).thenReturn(existingCompany)

        companyView.updateCompany(mockScanner)

        verify(companyController, times(1)).updateCompany(any(Company))
    }

    @Test
    void testDeleteCompany() {
        when(mockScanner.nextLine()).thenReturn("1")

        companyView.deleteCompany(mockScanner)

        verify(companyController, times(1)).deleteCompany(1)
    }
}

package org.example.Views

import org.example.Controllers.CompanyController
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

class CompanyViewTest {

    CompanyView companyView
    CompanyController companyController
    Scanner scanner

    @BeforeEach
    void setup() {
        companyController = Mockito.mock(CompanyController)
        scanner = Mockito.mock(Scanner)
        companyView = new CompanyView(companyController)
    }

    @Test
    void testCreateCompany() {
        when(scanner.nextLine())
                .thenReturn("Empresa X")
                .thenReturn("12345678901234")
                .thenReturn("empresa@x.com")
                .thenReturn("Empresa de TI")
                .thenReturn("Brasil")
                .thenReturn("12345678")
                .thenReturn("senha123")

        companyView.createCompany(scanner)

        verify(companyController).createCompany(
                "Empresa X",
                "12345678901234",
                "empresa@x.com",
                "Empresa de TI",
                "Brasil",
                "12345678",
                "senha123"
        )
    }

    @Test
    void testUpdateCompany() {
        when(scanner.nextInt()).thenReturn(1)
        when(scanner.nextLine())
                .thenReturn("")
                .thenReturn("Empresa Y")
                .thenReturn("98765432101234")
                .thenReturn("empresa@y.com")
                .thenReturn("Empresa de Logística")
                .thenReturn("Portugal")
                .thenReturn("87654321")
                .thenReturn("senha321")

        companyView.updateCompany(scanner)

        verify(companyController).updateCompany(
                1,
                "Empresa Y",
                "98765432101234",
                "empresa@y.com",
                "Empresa de Logística",
                "Portugal",
                "87654321",
                "senha321"
        )
    }

    @Test
    void testGetCompanyById() {
        when(scanner.nextInt()).thenReturn(1)
        when(scanner.nextLine()).thenReturn("")

        companyView.getCompanyById(scanner)

        verify(companyController).getCompanyById(1)
    }

    @Test
    void testDeleteCompany() {
        when(scanner.nextInt()).thenReturn(1)
        when(scanner.nextLine()).thenReturn("")

        companyView.deleteCompany(scanner)

        verify(companyController).deleteCompany(1)
    }
}

package org.example.ClassesDAO

import org.example.Models.Company
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import static org.junit.jupiter.api.Assertions.*

class CompanyDAOTest {

    private CompanyDAO companyDAO
    private Company mockCompany

    @BeforeEach
    void setUp() {
        companyDAO = Mockito.mock(CompanyDAO.class)
        mockCompany = new Company(1, "Global Solutions", "info@globalsolutions.com", "12345678000195", "Soluções globais", "Espanha", "02000000", "senha321")
    }

    @Test
    void testCreateCompany() {
        Mockito.doNothing().when(companyDAO).create(Mockito.any(Company.class))

        Company newCompany = new Company(0, "Tech Corp", "contact@techcorp.com", "12345678000195", "Uma empresa de tecnologia", "Brasil", "01000000", "senha123")

        companyDAO.create(newCompany)
        Mockito.verify(companyDAO).create(newCompany)
    }

    @Test
    void testGetCompanyById() {
        Mockito.when(companyDAO.getById(1)).thenReturn(mockCompany)

        Company company = companyDAO.getById(1)

        assertNotNull(company)
        assertEquals(1, company.getId())
        assertEquals("Global Solutions", company.getNome())
        assertEquals("info@globalsolutions.com", company.getEmail())
    }

    @Test
    void testUpdateCompany() {
        Mockito.doNothing().when(companyDAO).update(Mockito.any(Company.class))

        mockCompany.setNome("InovaTech Ltda")
        mockCompany.setEmail("support@inovatech.com")

        companyDAO.update(mockCompany)
        Mockito.verify(companyDAO).update(mockCompany)
    }

    @Test
    void testDeleteCompany() {
        Mockito.doNothing().when(companyDAO).delete(1)

        companyDAO.delete(1)
        Mockito.verify(companyDAO).delete(1)
    }
}

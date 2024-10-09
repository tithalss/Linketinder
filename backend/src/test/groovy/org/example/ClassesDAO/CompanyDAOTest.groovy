package org.example.ClassesDAO

import org.example.Models.Company
import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.*

class CompanyDAOTest {

    @BeforeEach
    void setUp() {
        DatabaseConnection.getConnection().prepareStatement("TRUNCATE TABLE empresas RESTART IDENTITY CASCADE;").executeUpdate()
    }

    @Test
    void testCreateCompany() {
        CompanyDAO.createCompany("Tech Corp", "12345678000195", "contact@techcorp.com", "Uma empresa de tecnologia", "Brasil", "01000000", "senha123")

        Company createdCompany = CompanyDAO.getCompanyById(1)

        assertNotNull(createdCompany)
        assertNotNull(createdCompany.getId())
        assertEquals("Tech Corp", createdCompany.getNome())
    }

    @Test
    void testGetCompanyById() {
        CompanyDAO.createCompany("Global Solutions", "98765432000195", "info@globalsolutions.com", "Soluções globais", "Espanha", "02000000", "senha321")

        Company company = CompanyDAO.getCompanyById(1)

        assertNotNull(company)
        assertEquals(1, company.getId())
        assertEquals("Global Solutions", company.getNome())
        assertEquals("info@globalsolutions.com", company.getEmail())
    }

    @Test
    void testUpdateCompany() {
        CompanyDAO.createCompany("InovaTech", "11111111000111", "support@inovatech.com", "Inovações em tecnologia", "França", "03000000", "senha456")

        CompanyDAO.updateCompany(1, "InovaTech Ltda", "11111111000111", "support@inovatech.com", "Inovações em tecnologia e suporte", "França", "03000000", "novaSenha456")

        Company updatedCompany = CompanyDAO.getCompanyById(1)
        assertNotNull(updatedCompany)
        assertEquals("InovaTech Ltda", updatedCompany.getNome())
        assertEquals("Inovações em tecnologia e suporte", updatedCompany.getDescricao())
    }

    @Test
    void testDeleteCompany() {
        CompanyDAO.createCompany("Startup XYZ", "22222222000122", "info@startupxyz.com", "Uma nova startup", "Alemanha", "04000000", "senha789")

        CompanyDAO.deleteCompany(1)

        Company company = CompanyDAO.getCompanyById(1)
        assertNull(company)
    }
}

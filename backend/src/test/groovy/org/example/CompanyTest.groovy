package org.example

import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.*

class CompanyTest {

    @Test
    void testGettersAndSetters() {
        Company company = new Company(
                1,
                "Tech Solutions",
                "contato@techsolutions.com",
                "12.345.678/0001-90",
                "Brasil",
                "12345-678",
                "Soluções tecnológicas",
                "senha123"
        )
        
        assertEquals(1, company.getId())
        assertEquals("Tech Solutions", company.getNome())
        assertEquals("contato@techsolutions.com", company.getEmail())
        assertEquals("12.345.678/0001-90", company.getCnpj())
        assertEquals("Brasil", company.getPais())
        assertEquals("12345-678", company.getCep())
        assertEquals("Soluções tecnológicas", company.getDescricao())
        assertEquals("senha123", company.getSenha())

        company.setId(1)
        company.setNome("Inovatech")
        company.setEmail("contato@inovatech.com")
        company.setCnpj("98.765.432/0001-10")
        company.setPais("Portugal")
        company.setCep("98765-432")
        company.setDescricao("Inovações tecnológicas")
        company.setSenha("senha456")

        assertEquals(1, company.getId())
        assertEquals("Inovatech", company.getNome())
        assertEquals("contato@inovatech.com", company.getEmail())
        assertEquals("98.765.432/0001-10", company.getCnpj())
        assertEquals("Portugal", company.getPais())
        assertEquals("98765-432", company.getCep())
        assertEquals("Inovações tecnológicas", company.getDescricao())
        assertEquals("senha456", company.getSenha())
    }

    @Test
    void testToString() {
        Company company = new Company(
                1,
                "Tech Solutions",
                "contato@techsolutions.com",
                "12.345.678/0001-90",
                "Brasil",
                "12345-678",
                "Soluções tecnológicas",
                "senha123"
        )

        company.setId(1)

        String expected = "Company ID: 1\n" +
                "Nome: Tech Solutions\n" +
                "Email: contato@techsolutions.com\n" +
                "CNPJ: 12.345.678/0001-90\n" +
                "País: Brasil\n" +
                "CEP: 12345-678\n" +
                "Descrição: Soluções tecnológicas"

        assertEquals(expected, company.toString())
    }
}

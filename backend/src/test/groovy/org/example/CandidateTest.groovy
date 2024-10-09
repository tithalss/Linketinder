package org.example

import org.example.Models.Candidate;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class CandidateTest {

    @Test
    void testGettersAndSetters() {
        Candidate candidate = new Candidate(
                1,
                "João Silva",
                LocalDate.of(1990, 1, 1),
                "joao.silva@example.com",
                "123.456.789-00",
                "Brasil",
                "12345-678",
                "Desenvolvedor",
                "Desenvolvedor Full Stack",
                "senha123"
        );

        assertEquals(1, candidate.getId());
        assertEquals("João Silva", candidate.getNomeCompleto());
        assertEquals(LocalDate.of(1990, 1, 1), candidate.getDataNascimento());
        assertEquals("joao.silva@example.com", candidate.getEmail());
        assertEquals("123.456.789-00", candidate.getCpf());
        assertEquals("Brasil", candidate.getPais());
        assertEquals("12345-678", candidate.getCep());
        assertEquals("Desenvolvedor", candidate.getCargo());
        assertEquals("Desenvolvedor Full Stack", candidate.getDescricao());
        assertEquals("senha123", candidate.getSenha());

        candidate.setNomeCompleto("Maria Souza");
        candidate.setDataNascimento(LocalDate.of(1992, 2, 2));
        candidate.setEmail("maria.souza@example.com");
        candidate.setCpf("987.654.321-00");
        candidate.setPais("Portugal");
        candidate.setCep("87654-321");
        candidate.setCargo("Gerente");
        candidate.setDescricao("Gerente de Projetos");
        candidate.setSenha("senha456");

        assertEquals("Maria Souza", candidate.getNomeCompleto());
        assertEquals(LocalDate.of(1992, 2, 2), candidate.getDataNascimento());
        assertEquals("maria.souza@example.com", candidate.getEmail());
        assertEquals("987.654.321-00", candidate.getCpf());
        assertEquals("Portugal", candidate.getPais());
        assertEquals("87654-321", candidate.getCep());
        assertEquals("Gerente", candidate.getCargo());
        assertEquals("Gerente de Projetos", candidate.getDescricao());
        assertEquals("senha456", candidate.getSenha());
    }

    @Test
    void testToString() {
        Candidate candidate = new Candidate(
                1,
                "João Silva",
                LocalDate.of(1990, 1, 1),
                "joao.silva@example.com",
                "123.456.789-00",
                "Brasil",
                "12345-678",
                "Desenvolvedor",
                "Desenvolvedor Full Stack",
                "senha123"
        );

        String expected = "ID: 1\n" +
                "Nome Completo: João Silva\n" +
                "Data de Nascimento: 1990-01-01\n" +
                "Email: joao.silva@example.com\n" +
                "CPF: 123.456.789-00\n" +
                "País: Brasil\n" +
                "CEP: 12345-678\n" +
                "Cargo: Desenvolvedor\n" +
                "Descrição: Desenvolvedor Full Stack";

        assertEquals(expected, candidate.toString());
    }
}

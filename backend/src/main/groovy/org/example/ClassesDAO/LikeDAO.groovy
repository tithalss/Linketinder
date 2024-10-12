package org.example.ClassesDAO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class LikeDAO {

    private final Connection connection

    LikeDAO(Connection connection) {
        this.connection = connection
    }

    void likeFromCompany(int idEmpresa, int idCandidato) {
        String query = "INSERT INTO like_empresa (id_empresa, id_candidato) VALUES (?, ?)"
        try {
            PreparedStatement pstmt = connection.prepareStatement(query)
            pstmt.setInt(1, idEmpresa)
            pstmt.setInt(2, idCandidato)
            pstmt.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }

    void likeFromCandidate(int idCandidato, int idVaga) {
        String query = "INSERT INTO like_candidato (id_candidato, id_vaga) VALUES (?, ?)"
        try {
            PreparedStatement pstmt = connection.prepareStatement(query)
            pstmt.setInt(1, idCandidato)
            pstmt.setInt(2, idVaga)
            pstmt.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }

    List<String> getMatchsForCompany(int idEmpresa) {
        String query = """
            SELECT lc.id_vaga, c.nome AS candidato_nome, lc.id_candidato
            FROM like_empresa le
            JOIN like_candidato lc ON lc.id_candidato = le.id_candidato
            JOIN vagas v ON lc.id_vaga = v.id
            JOIN candidatos c ON lc.id_candidato = c.id
            WHERE le.id_empresa = v.id_empresa
              AND le.id_empresa = ?;
        """

        List<String> matchs = []

        try {
            PreparedStatement pstmt = connection.prepareStatement(query)
            pstmt.setInt(1, idEmpresa)
            ResultSet rs = pstmt.executeQuery()

            while (rs.next()) {
                int vagaId = rs.getInt("id_vaga")
                String candidatoNome = rs.getString("candidato_nome")
                matchs.add("Match encontrado! Vaga ID: ${vagaId}, Candidato: ${candidatoNome}")
            }

        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
        return matchs
    }

    List<String> getMatchsForCandidate(int idCandidato) {
        String query = """
            SELECT lc.id_vaga, e.nome AS empresa_nome, lc.id_candidato
            FROM like_candidato lc
            JOIN vagas v ON lc.id_vaga = v.id
            JOIN empresas e ON v.id_empresa = e.id
            JOIN like_empresa le ON le.id_empresa = v.id_empresa AND le.id_candidato = lc.id_candidato
            WHERE lc.id_candidato = ?;
        """

        List<String> matchs = []

        try {
            PreparedStatement pstmt = connection.prepareStatement(query)
            pstmt.setInt(1, idCandidato)
            ResultSet rs = pstmt.executeQuery()

            while (rs.next()) {
                int vagaId = rs.getInt("id_vaga")
                String empresaNome = rs.getString("empresa_nome")
                matchs.add("Match encontrado! Vaga ID: ${vagaId}, Empresa: ${empresaNome}")
            }

        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
        return matchs
    }
}

package org.example.ClassesDAO


import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

class LikeDAO {
    static void likeFromCompany(int idEmpresa, int idCandidato) {
        Connection conn = DatabaseConnection.getConnection()
        String query = "INSERT INTO like_empresa (id_empresa, id_candiato) VALUES (?, ?)"
        try {
            PreparedStatement pstmt = conn.prepareStatement(query)
            pstmt.setInt(1, idEmpresa)
            pstmt.setInt(2, idCandidato)
            pstmt.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }

    static void unlikeFromCompany(int idLikeEmpresa) {
        Connection conn = DatabaseConnection.getConnection()
        String query = "DELETE FROM like_empresa WHERE id = ?"
        try {
            PreparedStatement pstmt = conn.prepareStatement(query)
            pstmt.setInt(1, idLikeEmpresa)
            pstmt.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }

    static void likeFromCandidate(int idCandidato, int idVaga) {
        Connection conn = DatabaseConnection.getConnection()
        String query = "INSERT INTO like_candidato (id_candidato, id_vaga) VALUES (?, ?)"
        try {
            PreparedStatement pstmt = conn.prepareStatement(query)
            pstmt.setInt(1, idCandidato)
            pstmt.setInt(2, idVaga)
            pstmt.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }

    static void unlikeFromCandidate(int idLikeCandidato) {
        Connection conn = DatabaseConnection.getConnection()
        String query = "DELETE FROM like_candidato WHERE id = ?"
        try {
            PreparedStatement pstmt = conn.prepareStatement(query)
            pstmt.setInt(1, idLikeCandidato)
            pstmt.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }
}

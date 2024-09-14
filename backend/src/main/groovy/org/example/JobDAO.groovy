package org.example

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class JobDAO {
    static void createJob(String cargo, String descricao, String local, int idEmpresa) {
        Connection conn = DatabaseConnection.getConnection()
        String sql = "INSERT INTO vagas (cargo, descricao, local, id_empresa) VALUES (?, ?, ?, ?)"
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql)
            pstmt.setString(1, cargo)
            pstmt.setString(2, descricao)
            pstmt.setString(3, local)
            pstmt.setInt(4, idEmpresa)
            pstmt.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }

    static Map<String, Object> getJobById(int id) {
        Map<String, Object> job = null
        Connection conn = DatabaseConnection.getConnection()
        String sql = "SELECT * FROM vagas WHERE id = ?"
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql)
            pstmt.setInt(1, id)
            ResultSet rs = pstmt.executesql()
            if (rs.next()) {
                job = new HashMap<>()
                job.put("id", rs.getInt("id"))
                job.put("cargo", rs.getString("cargo"))
                job.put("descricao", rs.getString("descricao"))
                job.put("local", rs.getString("local"))
                job.put("idEmpresa", rs.getInt("id_empresa"))
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
        return job
    }

    static void updateJob(int id, String cargo, String descricao, String local, int idEmpresa) {
        Connection conn = DatabaseConnection.getConnection()
        String sql = "UPDATE vagas SET cargo = ?, descricao = ?, local = ?, id_empresa = ? WHERE id = ?"
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql)
            pstmt.setString(1, cargo)
            pstmt.setString(2, descricao)
            pstmt.setString(3, local)
            pstmt.setInt(4, idEmpresa)
            pstmt.setInt(5, id)
            pstmt.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }

    static void deleteJob(int id) {
        Connection conn = DatabaseConnection.getConnection()
        String sql = "DELETE FROM vagas WHERE id = ?"
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql)
            pstmt.setInt(1, id)
            pstmt.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }
}

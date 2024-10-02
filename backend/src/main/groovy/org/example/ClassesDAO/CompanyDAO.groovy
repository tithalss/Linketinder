package org.example.ClassesDAO

import org.example.Company

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CompanyDAO {
    static void createCompany(String nome, String cnpj, String email, String descricao, String pais, String cep, String senha) {
        Connection conn = DatabaseConnection.getConnection()
        try {
            String sql = "INSERT INTO empresas (nome, cnpj, email, descricao, pais, cep, senha) VALUES (?, ?, ?, ?, ?, ?, ?)"
            PreparedStatement pstmt = conn.prepareStatement(sql)

            pstmt.setString(1, nome)
            pstmt.setString(2, cnpj)
            pstmt.setString(3, email)
            pstmt.setString(4, descricao)
            pstmt.setString(5, pais)
            pstmt.setString(6, cep)
            pstmt.setString(7, senha)
            pstmt.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }

    static Company getCompanyById(int id) {
        Connection connection = DatabaseConnection.getConnection()
        Company company = null
        try {
            String sql = "SELECT * FROM empresas WHERE id = ?"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, id)
            ResultSet resultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
                // Inicialize o objeto Company com os dados recuperados
                company = new Company(
                        resultSet.getString("nome"),
                        resultSet.getString("email"),
                        resultSet.getString("cnpj"),
                        resultSet.getString("pais"),
                        resultSet.getString("cep"),
                        resultSet.getString("descricao"),
                        resultSet.getString("senha")
                )
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
        return company
    }


    static void updateCompany(int id, String nome, String cnpj, String email, String descricao, String pais, String cep, String senha) {
        Connection connection = DatabaseConnection.getConnection()
        try {
            String sql = "UPDATE empresas SET nome = ?, cnpj = ?, email = ?, descricao = ?, pais = ?, cep = ?, senha = ? WHERE id = ?"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)

            preparedStatement.setInt(1, id)
            preparedStatement.setString(2, nome)
            preparedStatement.setString(3, cnpj)
            preparedStatement.setString(4, email)
            preparedStatement.setString(5, descricao)
            preparedStatement.setString(6, pais)
            preparedStatement.setString(7, cep)
            preparedStatement.setString(8, senha)

            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }

    static void deleteCompany(int id) {
        Connection connection = DatabaseConnection.getConnection()
        try {
            String sql = "DELETE FROM empresas WHERE id = ?"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, id)
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }
}

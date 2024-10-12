package org.example.ClassesDAO

import org.example.Interfaces.GenericDAO
import org.example.Models.Company

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CompanyDAO implements GenericDAO<Company>{

    private final Connection connection

    CompanyDAO(Connection connection) {
        this.connection = connection
    }

    void create(Company company) {
        try {
            String sql = "INSERT INTO empresas (nome, cnpj, email, descricao, pais, cep, senha) VALUES (?, ?, ?, ?, ?, ?, ?)"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)

            preparedStatement.setString(1, company.getNome())
            preparedStatement.setString(2, company.getCnpj())
            preparedStatement.setString(3, company.getEmail())
            preparedStatement.setString(4, company.getDescricao())
            preparedStatement.setString(5, company.getPais())
            preparedStatement.setString(6, company.getCep())
            preparedStatement.setString(7, company.getSenha())
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    Company getById(int id) {
        Company company = null
        try {
            String sql = "SELECT * FROM empresas WHERE id = ?"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, id)
            ResultSet resultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
                company = new Company(
                        resultSet.getInt("id"),
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
        }
        return company
    }

    void update(Company company) {
        try {
            String sql = "UPDATE empresas SET nome = ?, cnpj = ?, email = ?, descricao = ?, pais = ?, cep = ?, senha = ? WHERE id = ?"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)

            preparedStatement.setString(1, company.getNome())
            preparedStatement.setString(2, company.getCnpj())
            preparedStatement.setString(3, company.getEmail())
            preparedStatement.setString(4, company.getDescricao())
            preparedStatement.setString(5, company.getPais())
            preparedStatement.setString(6, company.getCep())
            preparedStatement.setString(7, company.getSenha())
            preparedStatement.setInt(8, company.getId())

            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    void delete(int id) {
        try {
            String sql = "DELETE FROM empresas WHERE id = ?"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, id)
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }
}

package org.example.ClassesDAO

import org.example.Candidate

import java.sql.*
import java.time.LocalDate

class CandidateDAO {
    static void createCandidate(String nome, LocalDate dataNascimento, String email, String cpf, String pais, String cep, String cargo, String descricao, String senha) {
        Connection connection = DatabaseConnection.getConnection()
        try {
            String sql = "INSERT INTO candidatos (nome, data_nascimento, email, cpf, pais, cep, cargo, descricao, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)

            // Converte LocalDate para java.sql.Date
            Date sqlDate = Date.valueOf(dataNascimento)

            preparedStatement.setString(1, nome)
            preparedStatement.setDate(2, sqlDate)
            preparedStatement.setString(3, email)
            preparedStatement.setString(4, cpf)
            preparedStatement.setString(5, pais)
            preparedStatement.setString(6, cep)
            preparedStatement.setString(7, cargo)
            preparedStatement.setString(8, descricao)
            preparedStatement.setString(9, senha)

            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            println "Candidato cadastrado com sucesso."
            DatabaseConnection.closeConnection()
        }
    }

    static Candidate getCandidateById(int id) {
        Connection connection = DatabaseConnection.getConnection()
        Candidate candidate = null
        try {
            String sql = "SELECT * FROM candidatos WHERE id = ?"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, id)
            ResultSet resultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
                // Converte java.sql.Date para LocalDate
                LocalDate dataNascimento = resultSet.getDate("data_nascimento").toLocalDate()

                candidate = new Candidate(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        dataNascimento,
                        resultSet.getString("email"),
                        resultSet.getString("cpf"),
                        resultSet.getString("pais"),
                        resultSet.getString("cep"),
                        resultSet.getString("cargo"),
                        resultSet.getString("descricao"),
                        resultSet.getString("senha"),
                )
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
        return candidate
    }

    static void updateCandidate(int id, String nome, LocalDate dataNascimento, String email, String cpf, String pais, String cep, String cargo, String descricao, String senha) {
        Connection connection = DatabaseConnection.getConnection()
        try {
            String sql = "UPDATE candidatos SET nome = ?, data_nascimento = ?, email = ?, cpf = ?, pais = ?, cep = ?, cargo = ?, descricao = ?, senha = ? WHERE id = ?"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)

            // Converte LocalDate para java.sql.Date
            Date sqlDate = Date.valueOf(dataNascimento)

            preparedStatement.setString(1, nome)
            preparedStatement.setDate(2, sqlDate)
            preparedStatement.setString(3, email)
            preparedStatement.setString(4, cpf)
            preparedStatement.setString(5, pais)
            preparedStatement.setString(6, cep)
            preparedStatement.setString(7, cargo)
            preparedStatement.setString(8, descricao)
            preparedStatement.setString(9, senha)
            preparedStatement.setInt(10, id)

            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            println "Candidato atualizado com sucesso."
            DatabaseConnection.closeConnection()
        }
    }

    static void deleteCandidate(int id) {
        Connection connection = DatabaseConnection.getConnection()
        try {
            String sql = "DELETE FROM candidatos WHERE id = ?"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, id)
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            println "Candidato deletado com sucesso."
            DatabaseConnection.closeConnection()
        }
    }
}

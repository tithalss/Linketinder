package org.example.ClassesDAO

import org.example.Interfaces.GenericDAO
import org.example.Models.Candidate
import java.sql.*
import java.time.LocalDate

class CandidateDAO implements GenericDAO<Candidate> {

    private final Connection connection

    CandidateDAO(Connection connection) {
        this.connection = connection
    }

    @Override
    void create(Candidate candidate) {
        try {
            String sql = "INSERT INTO candidatos (nome, data_nascimento, email, cpf, pais, cep, cargo, descricao, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)

            // Converte LocalDate para java.sql.Date
            Date sqlDate = Date.valueOf(candidate.getDataNascimento())

            preparedStatement.setString(1, candidate.getNomeCompleto())
            preparedStatement.setDate(2, sqlDate)
            preparedStatement.setString(3, candidate.getEmail())
            preparedStatement.setString(4, candidate.getCpf())
            preparedStatement.setString(5, candidate.getPais())
            preparedStatement.setString(6, candidate.getCep())
            preparedStatement.setString(7, candidate.getCargo())
            preparedStatement.setString(8, candidate.getDescricao())
            preparedStatement.setString(9, candidate.getSenha())

            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    @Override
    Candidate getById(int id) {
        Candidate candidate = null
        try {
            String sql = "SELECT * FROM candidatos WHERE id = ?"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, id)
            ResultSet resultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
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
                        resultSet.getString("senha")
                )
            }
        } catch (SQLException e) {
            e.printStackTrace()
        }
        return candidate
    }

    @Override
    void update(Candidate candidate) {
        try {
            String sql = "UPDATE candidatos SET nome = ?, data_nascimento = ?, email = ?, cpf = ?, pais = ?, cep = ?, cargo = ?, descricao = ?, senha = ? WHERE id = ?"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)

            Date sqlDate = Date.valueOf(candidate.getDataNascimento())

            preparedStatement.setString(1, candidate.getNomeCompleto())
            preparedStatement.setDate(2, sqlDate)
            preparedStatement.setString(3, candidate.getEmail())
            preparedStatement.setString(4, candidate.getCpf())
            preparedStatement.setString(5, candidate.getPais())
            preparedStatement.setString(6, candidate.getCep())
            preparedStatement.setString(7, candidate.getCargo())
            preparedStatement.setString(8, candidate.getDescricao())
            preparedStatement.setString(9, candidate.getSenha())
            preparedStatement.setInt(10, candidate.getId())

            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    @Override
    void delete(int id) {
        try {
            String sql = "DELETE FROM candidatos WHERE id = ?"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, id)
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }
}

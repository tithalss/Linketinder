package org.example.ClassesDAO

import org.example.Interfaces.GenericDAO
import org.example.Models.Competence
import java.sql.*

class CompetenceDAO implements GenericDAO<Competence>{

    private final Connection connection

    CompetenceDAO(Connection connection) {
        this.connection = connection
    }

    void create(Competence competence) {
        String query = "INSERT INTO competencias (competencia) VALUES (?)"
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query)
            preparedStatement.setString(1, competence.getNome())
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    Competence getById(int id) {
        Competence competence = null
        String query = "SELECT * FROM competencias WHERE id = ?"
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query)
            preparedStatement.setInt(1, id)
            ResultSet rs = preparedStatement.executeQuery()
            if (rs.next()) {
                competence = new Competence(
                        rs.getInt("id"),
                        rs.getString("competencia")
                )
            }
        } catch (SQLException e) {
            e.printStackTrace()
        }
        return competence
    }

    void update(Competence competence) {
        String query = "UPDATE competencias SET competencia = ? WHERE id = ?"
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query)
            preparedStatement.setString(1, competence.getNome())
            preparedStatement.setInt(2, competence.getId())
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    void delete(int id) {
        String query = "DELETE FROM competencias WHERE id = ?"
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query)
            preparedStatement.setInt(1, id)
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }
}

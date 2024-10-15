package org.example.ClassesDAO

import org.example.Interfaces.GenericDAO
import org.example.Models.Job
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class JobDAO implements GenericDAO<Job>{

    private final Connection connection

    JobDAO(Connection connection) {
        this.connection = connection
    }

    void create(Job job) {
        String sql = "INSERT INTO vagas (cargo, descricao, local, id_empresa) VALUES (?, ?, ?, ?)"
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, job.getCargo())
            preparedStatement.setString(2, job.getDescricao())
            preparedStatement.setString(3, job.getLocal())
            preparedStatement.setInt(4, job.getIdEmpresa())
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    Job getById(int id) {
        Job job = null
        String sql = "SELECT * FROM vagas WHERE id = ?"
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, id)
            ResultSet resultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
                job = new Job(
                        resultSet.getInt("id"),
                        resultSet.getString("cargo"),
                        resultSet.getString("descricao"),
                        resultSet.getInt("id_empresa")
                )
                job.setLocal(resultSet.getString("local"))
            }
        } catch (SQLException e) {
            e.printStackTrace()
        }
        return job
    }

    void update(Job job) {
        String sql = "UPDATE vagas SET cargo = ?, descricao = ?, local = ?, id_empresa = ? WHERE id = ?"
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, job.getCargo())
            preparedStatement.setString(2, job.getDescricao())
            preparedStatement.setString(3, job.getLocal())
            preparedStatement.setInt(4, job.getIdEmpresa())
            preparedStatement.setInt(5, job.getId())
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    void delete(int id) {
        String sql = "DELETE FROM vagas WHERE id = ?"
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, id)
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }
}

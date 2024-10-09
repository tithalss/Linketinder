package org.example.ClassesDAO

import org.example.Job

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class JobDAO {

    static void createJob(String cargo, String descricao, String local, int idEmpresa) {
        Connection connection = DatabaseConnection.getConnection()
        try {
            String sql = "INSERT INTO vagas (cargo, descricao, local, id_empresa) VALUES (?, ?, ?, ?)"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, cargo)
            preparedStatement.setString(2, descricao)
            preparedStatement.setString(3, local)
            preparedStatement.setInt(4, idEmpresa)
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }

    static Job getJobById(int id) {
        Connection connection = DatabaseConnection.getConnection()
        Job job = null
        try {
            String sql = "SELECT * FROM vagas WHERE id = ?"
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
        } finally {
            DatabaseConnection.closeConnection()
        }
        return job
    }

    static void updateJob(int id, String cargo, String descricao, String local, int idEmpresa) {
        Connection connection = DatabaseConnection.getConnection()
        try {
            String sql = "UPDATE vagas SET cargo = ?, descricao = ?, local = ?, id_empresa = ? WHERE id = ?"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, cargo)
            preparedStatement.setString(2, descricao)
            preparedStatement.setString(3, local)
            preparedStatement.setInt(4, idEmpresa)
            preparedStatement.setInt(5, id)
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }

    static void deleteJob(int id) {
        Connection connection = DatabaseConnection.getConnection()
        try {
            String sql = "DELETE FROM vagas WHERE id = ?"
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

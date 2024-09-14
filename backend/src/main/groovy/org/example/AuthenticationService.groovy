package org.example

import java.sql.*

class AuthenticationService {
    static Integer authenticate(String email, String senha) {
        Connection connection = DatabaseConnection.getConnection()
        Integer userId = null
        try {
            String sql = "SELECT id FROM candidatos WHERE email = ? AND senha = ?"
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, email)
            preparedStatement.setString(2, senha)
            ResultSet resultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
                userId = resultSet.getInt("id")
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
        return userId
    }
}


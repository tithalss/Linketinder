package org.example.Services

import java.sql.*

class AuthenticationService {

    private final Connection connection

    AuthenticationService(Connection connection) {
        this.connection = connection
    }

    Integer authenticate(String email, String senha) {
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
        }
        return userId
    }
}

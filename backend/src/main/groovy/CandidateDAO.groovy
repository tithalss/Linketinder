import java.sql.Connection
import java.sql.Date
import java.sql.PreparedStatement
import java.sql.SQLException
import java.time.LocalDate

class CandidateDAO {
    void createCandidate(String nome, LocalDate dataNascimento, String email, String cpf, String pais, String cep, String cargo, String descricao, String senha) {
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
            DatabaseConnection.closeConnection()
        }
    }
}

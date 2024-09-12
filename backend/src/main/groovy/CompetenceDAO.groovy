import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CompetenceDAO {
    static void createCompetence(String nome) {
        Connection conn = DatabaseConnection.getConnection()
        String query = "INSERT INTO competencias (competencia) VALUES (?)"
        try {
            PreparedStatement pstmt = conn.prepareStatement(query)
            pstmt.setString(1, nome)
            pstmt.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }

    static List<Map<String, Object>> getCompetences() {
        List<Map<String, Object>> competences = []
        Connection conn = DatabaseConnection.getConnection()
        String query = "SELECT * FROM competencias"
        try {
            PreparedStatement pstmt = conn.prepareStatement(query)
            ResultSet rs = pstmt.executeQuery()
            while (rs.next()) {
                competences.add([
                        id: rs.getInt("id"),
                        nome: rs.getString("competencia")
                ])
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
        return competences
    }

    // Métodos de update e delete podem ser adicionados aqui
}

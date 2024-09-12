import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class JobDAO {
    static void createJob(String cargo, String descricao, String local, int idEmpresa) {
        Connection conn = DatabaseConnection.getConnection()
        String query = "INSERT INTO vagas (cargo, descricao, local, id_empresa) VALUES (?, ?, ?, ?)"
        try {
            PreparedStatement pstmt = conn.prepareStatement(query)
            pstmt.setString(1, cargo)
            pstmt.setString(2, descricao)
            pstmt.setString(3, local)
            pstmt.setInt(4, idEmpresa)
            pstmt.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }

    static List<Map<String, Object>> getJobs() {
        List<Map<String, Object>> jobs = []
        Connection conn = DatabaseConnection.getConnection()
        String query = "SELECT * FROM vagas"
        try {
            PreparedStatement pstmt = conn.prepareStatement(query)
            ResultSet rs = pstmt.executeQuery()
            while (rs.next()) {
                jobs.add([
                        id: rs.getInt("id"),
                        cargo: rs.getString("cargo"),
                        descricao: rs.getString("descricao"),
                        local: rs.getString("local"),
                        idEmpresa: rs.getInt("id_empresa")
                ])
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
        return jobs
    }

    // Métodos de update e delete podem ser adicionados aqui
}

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CompanyDAO {
    static void createCompany(String nome, String cnpj, String email, String descricao, String pais, String cep, String senha) {
        Connection conn = DatabaseConnection.getConnection()
        String query = "INSERT INTO empresas (nome, cnpj, email, descricao, pais, cep, senha) VALUES (?, ?, ?, ?, ?, ?, ?)"
        try {
            PreparedStatement pstmt = conn.prepareStatement(query)
            pstmt.setString(1, nome)
            pstmt.setString(2, cnpj)
            pstmt.setString(3, email)
            pstmt.setString(4, descricao)
            pstmt.setString(5, pais)
            pstmt.setString(6, cep)
            pstmt.setString(7, senha)
            pstmt.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }

    static List<Map<String, Object>> getCompanies() {
        List<Map<String, Object>> companies = []
        Connection conn = DatabaseConnection.getConnection()
        String query = "SELECT * FROM empresas"
        try {
            PreparedStatement pstmt = conn.prepareStatement(query)
            ResultSet rs = pstmt.executeQuery()
            while (rs.next()) {
                companies.add([
                        id: rs.getInt("id"),
                        nome: rs.getString("nome"),
                        cnpj: rs.getString("cnpj"),
                        email: rs.getString("email"),
                        descricao: rs.getString("descricao"),
                        pais: rs.getString("pais"),
                        cep: rs.getString("cep"),
                        senha: rs.getString("senha")
                ])
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
        return companies
    }

    // Métodos de update e delete podem ser adicionados aqui
}

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map

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
        List<Map<String, Object>> jobs = new ArrayList<>()
        Connection conn = DatabaseConnection.getConnection()
        String query = "SELECT * FROM vagas"
        try {
            PreparedStatement pstmt = conn.prepareStatement(query)
            ResultSet rs = pstmt.executeQuery()
            while (rs.next()) {
                Map<String, Object> job = new HashMap<>()
                job.put("id", rs.getInt("id"))
                job.put("cargo", rs.getString("cargo"))
                job.put("descricao", rs.getString("descricao"))
                job.put("local", rs.getString("local"))
                job.put("idEmpresa", rs.getInt("id_empresa"))
                jobs.add(job)
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
        return jobs
    }

    static Map<String, Object> getJobById(int id) {
        Map<String, Object> job = null
        Connection conn = DatabaseConnection.getConnection()
        String query = "SELECT * FROM vagas WHERE id = ?"
        try {
            PreparedStatement pstmt = conn.prepareStatement(query)
            pstmt.setInt(1, id)
            ResultSet rs = pstmt.executeQuery()
            if (rs.next()) {
                job = new HashMap<>()
                job.put("id", rs.getInt("id"))
                job.put("cargo", rs.getString("cargo"))
                job.put("descricao", rs.getString("descricao"))
                job.put("local", rs.getString("local"))
                job.put("idEmpresa", rs.getInt("id_empresa"))
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
        return job
    }

    static void updateJob(int id, String cargo, String descricao, String local, int idEmpresa) {
        Connection conn = DatabaseConnection.getConnection()
        String query = "UPDATE vagas SET cargo = ?, descricao = ?, local = ?, id_empresa = ? WHERE id = ?"
        try {
            PreparedStatement pstmt = conn.prepareStatement(query)
            pstmt.setString(1, cargo)
            pstmt.setString(2, descricao)
            pstmt.setString(3, local)
            pstmt.setInt(4, idEmpresa)
            pstmt.setInt(5, id)
            pstmt.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }

    static void deleteJob(int id) {
        Connection conn = DatabaseConnection.getConnection()
        String query = "DELETE FROM vagas WHERE id = ?"
        try {
            PreparedStatement pstmt = conn.prepareStatement(query)
            pstmt.setInt(1, id)
            pstmt.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection()
        }
    }
}

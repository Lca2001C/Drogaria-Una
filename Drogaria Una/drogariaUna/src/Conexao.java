import java.sql.*;
public class Conexao {
    static final String DB_URL = "jdbc:mysql://localhost:3306/drogaria_una";
    static final String USER = "root";
    static final String PASS = "teste123!";

    private Connection dbconn = null;
    private Statement sqlmgr = null;

    public Connection openDatabase() {
        try {
            dbconn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Conectado com sucesso");
            sqlmgr = dbconn.createStatement();
        } catch (Exception Error) {
            System.out.println("Erro ao conectar: " + Error.getMessage());
        }
        return dbconn;
    }

    public void closeDatabase() throws SQLException {
        if (sqlmgr != null) {
            sqlmgr.close();
        }
        if (dbconn != null) {
            dbconn.close();
        }
    }

    public int executarQuery(String sql) {
        try {
            return sqlmgr.executeUpdate(sql);
        } catch (Exception Error) {
            System.out.println("Erro ao executar query: " + Error.getMessage());
        }
        return -1;
    }
}
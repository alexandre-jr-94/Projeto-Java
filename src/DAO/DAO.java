package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static Env.Env.*;

public class DAO {

    
    public DAO() {
    }

    public Connection getConexao() {
    
        Connection connection = null;  // Instância da conexão
    
        try {
            // Carregamento do JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Configurar a conexão
            String url = "jdbc:mysql://" + ENV_SERVER + ":3306/" + ENV_DATABASE + "?useTimezone=true&serverTimezone=UTC";
    
            connection = DriverManager.getConnection(url, ENV_USER, ENV_PASSWORD);
    
            // Testando..
            if (connection != null) {
                System.out.println("Status: Conectado!");
            } else {
                System.out.println("Status: N�O CONECTADO!");
            }
    
            return connection;
    
        } catch (ClassNotFoundException e) {  //Driver nao encontrado
            System.out.println("O driver nao foi encontrado. " + e.getMessage() );
            return null;
    
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
    
        } catch (NoClassDefFoundError e) { // Trata exceção se a importação dos arquivos env falhar
            System.err.println("Erro ao importar as variáveis de ambiente: " + e.getMessage());
            System.err.println("Certifique-se de criar o arquivo Env.java, específicado no arquivo README.md");
            return null;
        }
    }
}
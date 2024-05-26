package Migration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import static Env.Env.*;

public class Migration {
    
    private static final String MIGRATION_FOLDER_PATH = "src/Migrations/";

    public static void main(String[] args) {
        Connection connection = null;

        
        // Conecta no database
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
                System.out.println("Status: NÃO CONECTADO!");
            }

            // Valida se precisa criar a tabela migration no bd
            createMigrationTable(connection);

            // Seleciona a pasta no projeto que vai conter os arquivos migrations
            File migrationFolder = new File(MIGRATION_FOLDER_PATH);

            // Seleciona arquivo por arquivo
            String[] migrationFiles = migrationFolder.list();

            // Ordena os arquivos
            Arrays.sort(migrationFiles); 

            // Executa arquivo por arquivo

            System.out.println("Iniciado execução dos migrations");
            int execucoes = 0;
            for (String migrationFile : migrationFiles) {
                
                // Extrai o nome identificador único do arquivo Formato (Ano | Mês | Dia | Hora | Minuto)(tudo junto) da criação 
                String migrationName = migrationFile.split("_")[0]; 

                if (!isMigrationExecuted(connection, migrationName)) { // Verifica se o arquivo não foi executado
                    try {
                        executeMigration(connection, migrationFile);
                        recordMigration(connection, migrationName);
                        execucoes++;
                        System.err.println("Migration " + migrationName + " Executado com sucesso");
                    } catch (IOException e) {
                        System.err.println("Ocorreu um erro para atualizar o migration " + migrationName);
                        System.err.println("Execução dos migrations pausada");
                        // e.printStackTrace();
                        break;
                    }
                }
            }

            if (execucoes == 0) {
                System.err.println("Nenhum Migration para executar");
            } else {
                System.err.println(execucoes + " Executado" + (execucoes == 1 ? " com sucesso" : "s com sucesso"));
            }

        } catch (ClassNotFoundException e) {  //Driver nao encontrado
            System.out.println("O driver nao foi encontrado. " + e.getMessage() );

        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar..." + e.getMessage() );

        } catch (NoClassDefFoundError e) { // Trata exceção se a importação dos arquivos env falhar
            System.err.println("Erro ao importar as variáveis de ambiente: " + e.getMessage());
            System.err.println("Certifique-se de criar o arquivo Env.java na raiz do projeto.");
        }
    }

    // Cria a tabela migration responsável por evitar multiplas execuções dos arquivos .sql
    private static void createMigrationTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS migration (id VARCHAR(255) PRIMARY KEY)";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.executeUpdate();
        }
    }

    // Verifica se o migration já foi executado (estará gravado no banco de dados)
    private static boolean isMigrationExecuted(Connection connection, String migrationName) throws SQLException {
        String query = "SELECT * FROM migration WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, migrationName);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    // Execucta o migration
    private static void executeMigration(Connection connection, String migrationFile) throws IOException, SQLException {
        String filePath = MIGRATION_FOLDER_PATH + migrationFile;
        StringBuilder queryBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                queryBuilder.append(line).append("\n");
            }
        }

        String migrationQuery = queryBuilder.toString();
        try (PreparedStatement statement = connection.prepareStatement(migrationQuery)) {
            statement.executeUpdate();
        }
    }

    // Grava o migration após sua execução
    private static void recordMigration(Connection connection, String migrationName) throws SQLException {
        String insertSQL = "INSERT INTO migration (id) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(insertSQL)) {
            statement.setString(1, migrationName);
            statement.executeUpdate();
        }
    }
}
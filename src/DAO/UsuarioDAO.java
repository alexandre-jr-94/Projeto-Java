package DAO;

import Model.Usuario;
import java.util.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioDAO {
    
    public static ArrayList<Usuario> Lista = new ArrayList<Usuario>();
    private DAO dao;


    public UsuarioDAO() {
        this.dao = new DAO();
    }

    public Boolean validarLogin(String email, String senha) {
        String sql = "SELECT COUNT(*) count FROM usuario WHERE email = ? AND senha = ?";

        try (Connection conn = dao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0; // Se count > 0, usuário encontrado
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retorna a Lista de Usuários(objetos)
    public ArrayList getLista() {
        Lista.clear(); // Limpa ArrayList
        try {
            Statement stmt = this.dao.getConexao().createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM usuario");
            while (res.next()) {

                int id = res.getInt("id");
                String nome = res.getString("nome");
                String email = res.getString("email");
                Date dataCadastro = res.getDate("data_cadastro");

                Usuario objeto = new Usuario(id, nome, email, dataCadastro);
                Lista.add(objeto);
            }
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        return Lista;
    }

    // Cadastra novo usuário
    public boolean InsertUsuario(Usuario objeto) {
        String sql = "INSERT INTO usuario(id,nome,email,senha) VALUES(?,?,?,?)";

        try {
            PreparedStatement stmt = this.dao.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setString(3, objeto.getEmail());
            stmt.setString(4, objeto.getSenha());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    // Deleta um usuario específico pelo seu campo ID
    public boolean DeleteUsuario(int id) {
        try {
            Statement stmt = this.dao.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM usuario WHERE id = " + id);
            stmt.close();
            
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        
        return true;
    }

    // Edita um Usuário específico pelo seu campo ID
    public boolean UpdateUsuario(Usuario objeto) {
        String sql = "UPDATE usuario set nome = ? ,email = ? ,senha = ? WHERE id = ?";

        try {
            PreparedStatement stmt = this.dao.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getEmail());
            stmt.setString(3, objeto.getSenha());
            stmt.setInt(5, objeto.getId());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    // Carrega um usuário específico baseado no Id
    public Usuario carregaUsuario(int id) {
        
        Usuario objeto = new Usuario();
        objeto.setId(id);
        
        try {
            Statement stmt = this.dao.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM usuario WHERE id = " + id);
            res.next();

            objeto.setNome(res.getString("nome"));
            objeto.setEmail(res.getString("email"));

            stmt.close();
            
        } catch (SQLException erro) {
        }
        return objeto;
    }

    // Retorna o maiorID
    public int maiorID() throws SQLException {

        int maiorID = 0;
        try {
            Statement stmt = this.dao.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM usuario");
            res.next();
            maiorID = res.getInt("id");

            stmt.close();

        } catch (SQLException ex) {
        }

        return maiorID;
    }

    public boolean validarEmailExistente(String email) {
        String sql = "SELECT COUNT(*) AS count FROM usuario WHERE email = ?";
        
        try (Connection conn = dao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0; // Se count > 0, o email existe
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
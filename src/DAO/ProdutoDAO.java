package DAO;

import Model.Produto;
import Model.Bebida;
import Model.Comida;
import java.util.*;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProdutoDAO {
    
    public static ArrayList<Produto> Lista = new ArrayList<Produto>();
    private DAO dao;

    public ProdutoDAO() {
        this.dao = new DAO();
    }

    // Retorna a Lista de Produtos(objetos)
    public ArrayList getLista(int tipoDeProduto) {
        Lista.clear(); // Limpa ArrayList
        try {
            Statement stmt = this.dao.getConexao().createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM produto WHERE tipo = " + tipoDeProduto);
            while (res.next()) {

                int id = res.getInt("id");
                String nome = res.getString("nome");
                String descricao = res.getString("descricao");
                float valor = res.getFloat("preco");
                int quantidadeEstoque = res.getInt("quantidade_estoque");
                Date dataCadastro = res.getDate("data_cadastro");

                if (tipoDeProduto == 1) {
                    Comida objeto = new Comida(id, nome, descricao, quantidadeEstoque, valor, dataCadastro);
                    Lista.add(objeto);
                } else {
                    Bebida objeto = new Bebida(id, nome, descricao, quantidadeEstoque, valor, dataCadastro);
                    Lista.add(objeto);
                }
            }
            stmt.close();

        } catch (SQLException ex) {
        }

        return Lista;
    }

    // Cadastra novo produto
    public boolean InsertProduto(Produto objeto, int tipoDeProduto) {
        String sql = "INSERT INTO produto(id,nome,descricao,preco,quantidade_estoque,tipo) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = this.dao.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setString(3, objeto.getDescricao());
            stmt.setFloat(4, objeto.getValor());
            stmt.setInt(5, objeto.getQuantidadeEstoque());
            stmt.setInt(6, tipoDeProduto);

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    // Deleta um produto específico pelo seu campo ID
    public boolean DeleteProduto(int id) {
        try {
            Statement stmt = this.dao.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM produto WHERE id = " + id);
            stmt.close();            
            
        } catch (SQLException erro) {
        }
        
        return true;
    }

    // Edita um Produto específico pelo seu campo ID
    public boolean UpdateProduto(Produto objeto) {
        String sql = "UPDATE produto set nome = ? ,descricao = ? ,preco = ? ,quantidade_estoque = ? WHERE id = ?";

        try {
            PreparedStatement stmt = this.dao.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getDescricao());
            stmt.setFloat(3, objeto.getValor());
            stmt.setInt(4, objeto.getQuantidadeEstoque());
            stmt.setInt(5, objeto.getId());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    // Carrega um produto específico baseado no Id
    public Object carregaProduto(int id, int tipo) {
        Object objeto = null;
        
        try {
            Statement stmt = this.dao.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM produto WHERE id = " + id);
            
            if (res.next()) {
                if (tipo == 1) {
                    Comida comida = new Comida();
                    comida.setId(id);
                    comida.setNome(res.getString("nome"));
                    comida.setDescricao(res.getString("descricao"));
                    comida.setValor(res.getFloat("valor"));
                    comida.setQuantidadeEstoque(res.getInt("quantidade_estoque"));
                    objeto = comida;
                } else {
                    Bebida bebida = new Bebida();
                    bebida.setId(id);
                    bebida.setNome(res.getString("nome"));
                    bebida.setDescricao(res.getString("descricao"));
                    bebida.setValor(res.getFloat("valor"));
                    bebida.setQuantidadeEstoque(res.getInt("quantidade_estoque"));
                    objeto = bebida;
                }
            }
            
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
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM produto");
            res.next();
            maiorID = res.getInt("id");

            stmt.close();

        } catch (SQLException ex) {
        }

        return maiorID;
    }
}

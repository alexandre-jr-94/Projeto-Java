package Model;

import java.util.Date;
import DAO.ProdutoDAO;
import java.util.ArrayList;

public class Comida extends Produto {
    // Número 1 para comidas
    private int tipoDeProduto = 1;
    private ProdutoDAO dao; 

    public Comida() {
        super(0, "", "", 0, 0.0f, new Date());
        this.dao = new ProdutoDAO();
    }

    public Comida(int id, String nome, String descricao, int quantidadeEstoque, float valor, Date dataCadastro) {
        super(id, nome, descricao, quantidadeEstoque, valor, dataCadastro);
        this.dao = new ProdutoDAO();
    }

    public Comida(String nome, String descricao, int quantidadeEstoque, float valor) {
        super(0, nome, descricao, quantidadeEstoque, valor, new Date());
        this.dao = new ProdutoDAO();
    }

    public Comida(int id, String nome, String descricao, int quantidadeEstoque, float valor) {
        super(id ,nome, descricao, quantidadeEstoque, valor, new Date());
        this.dao = new ProdutoDAO();
    }

    public int getTipoDeProduto() {
        return tipoDeProduto;
    }

    public ArrayList getLista() {
        return dao.getLista(this.tipoDeProduto);
    }
    public boolean Add_comida(String nome, String descricao, float valor, int quantidadeEstoque) {
        Comida objeto = new Comida(nome, descricao, quantidadeEstoque, valor);
        ProdutoDAO.Lista.add(objeto);
        dao.InsertProduto(objeto, tipoDeProduto);
        return true;
    }

    public boolean Editar_comida(int id, String nome, String descricao, float valor, int quantidadeEstoque) {
        Comida objeto = new Comida(id, nome, descricao, quantidadeEstoque, valor);
        ProdutoDAO.Lista.add(objeto);
        dao.UpdateProduto(objeto);
        return true;
    }

    public boolean DeleteProduto(int idComida) {
        dao.DeleteProduto(idComida);
        return true;
    }
}
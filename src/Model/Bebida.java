package Model;

import java.util.Date;
import DAO.ProdutoDAO;
import java.util.ArrayList;

public class Bebida extends Produto {
    // Número 2 para bebidas
    private int tipoDeProduto = 2; 
    private ProdutoDAO dao; 

    public Bebida() {
        super(0, "", "", 0, 0.0f, new Date());
        this.dao = new ProdutoDAO();
    }

    public Bebida(int id, String nome, String descricao, int quantidadeEstoque, float valor, Date dataCadastro) {
        super(id, nome, descricao, quantidadeEstoque, valor, dataCadastro);
        this.dao = new ProdutoDAO();
    }

    public Bebida(String nome, String descricao, int quantidadeEstoque, float valor) {
        super(0 ,nome, descricao, quantidadeEstoque, valor, new Date());
        this.dao = new ProdutoDAO();
    }

    public Bebida(int id, String nome, String descricao, int quantidadeEstoque, float valor) {
        super(id ,nome, descricao, quantidadeEstoque, valor, new Date());
        this.dao = new ProdutoDAO();
    }

    public int getTipoDeProduto() {
        return tipoDeProduto;
    }

    public ArrayList getLista() {
        return dao.getLista(this.tipoDeProduto);
    }

    public boolean addBebida(String nome, String descricao, float valor, int quantidadeEstoque) {
        Bebida objeto = new Bebida(nome, descricao, quantidadeEstoque, valor);
        ProdutoDAO.Lista.add(objeto);
        dao.InsertProduto(objeto, tipoDeProduto);
        return true;
    }

    public boolean editBebida(int id, String nome, String descricao, float valor, int quantidadeEstoque) {
        Bebida objeto = new Bebida(id ,nome, descricao, quantidadeEstoque, valor);
        ProdutoDAO.Lista.add(objeto);
        dao.UpdateProduto(objeto);
        return true;
    }

    public boolean DeleteProduto(int idBebida) {
        dao.DeleteProduto(idBebida);
        return true;
    }
}

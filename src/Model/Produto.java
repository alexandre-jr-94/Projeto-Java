package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class Produto {

    private int id;
    private String nome;
    private String descricao;
    private int quantidadeEstoque;
    private float valor;
    private Date dataCadastro;

    
    public Produto(){
    }

    public Produto(int id, String nome, String descricao, int quantidadeEstoque, float valor, Date dataCadastro){

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidadeEstoque = quantidadeEstoque;
        this.valor = valor;
        try {

            SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yy");
            this.dataCadastro = formataData.parse(String.valueOf(dataCadastro));

        } catch (ParseException e){

            e.printStackTrace();

        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
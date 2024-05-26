package Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import DAO.DAO;
import DAO.UsuarioDAO;


public class Usuario {

    private int id;
    private String nome;
    private String email;
    private Date dataCadastro;
    private String senha;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private UsuarioDAO dao;

    public Usuario() {
        this.dao = new UsuarioDAO();
    }


    public Usuario(int id, String nome, String email, Date dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        try {

            SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yy");
            this.dataCadastro = formataData.parse(String.valueOf(dataCadastro));

        } catch (ParseException e){

            e.printStackTrace();
        }
    }
   

    public Usuario(int id, String nome, String email, Date dataCadastro, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        // Converte a senha em SHA-256
        this.senha = Usuario.hashSenha(senha);
        try {

            SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yy");
            this.dataCadastro = formataData.parse(String.valueOf(dataCadastro));

        } catch (ParseException e){

            e.printStackTrace();

        }
    }

     private static String hashSenha(String senha) {
        try {
            // Obtém uma instância de MessageDigest para SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            // Converte a senha em um array de bytes e calcula o hash
            byte[] encodedhash = digest.digest(senha.getBytes());
            
            // Converte o array de bytes em uma string hexadecimal e retorna
            return bytesParaHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesParaHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }



    public boolean addUsuario(String nome, String senha) {
        email = nome + "@NeonNight.com";
        Usuario objeto = new Usuario(id ,nome, email , new Date(), senha);
        UsuarioDAO.Lista.add(objeto);
        usuarioDAO.InsertUsuario(objeto);
        return true;
    }

    public boolean olharUsuario(String nome, String senha) {
        // Para validar verifica se já tem a extensão do email, se não preenche automático.
        email = nome.contains("@") ? nome : nome + "@NeonNight.com";
        Usuario objeto = new Usuario(id, nome, email, new Date(), senha);
        return usuarioDAO.validarLogin(email, objeto.getSenha());
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Object> getLista() {
        return dao.getLista();
    }


    public boolean DeleteUsuario(int idUsuarioInt) {
        dao.DeleteUsuario(idUsuarioInt);
        return true;
    }
}
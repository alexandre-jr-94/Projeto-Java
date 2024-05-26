package Principal;

import javax.swing.JFrame;

import View.Login;

public class Principal {

    public static void main(String[] args) {
        /* Abrir tela do Aplicativo Neon Knight Club */
       JFrame tela = new Login();
        ((Login) tela).Inicio();
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // comportamento ao fechar a janela
        tela.setSize(500, 400);
        tela.setLocationRelativeTo(null); // Centralizar a janela
        tela.setVisible(true);
    }

}
package View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DAO.UsuarioDAO;
import Model.Usuario;


public class Login <usuarioObj> extends JFrame  {
    private Usuario objUsuario;
    private String email;
    public static String loggedUser;


    public Login() {
        Inicio();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     */
                         
     public void Inicio() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usuario_local = new javax.swing.JTextField();
        Senha_local = new javax.swing.JPasswordField();
        Login_botao = new javax.swing.JButton();
        Criar_botao = new javax.swing.JButton();
    
        jLabel1.setText("Usuario");
    
        jLabel2.setText("Senha");
    
        jLabel3.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel3.setText("Favor inserir sua senha");
    
        usuario_local.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuario_localActionPerformed(evt);
            }
        });
    
        Senha_local.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Senha_localActionPerformed(evt);
            }
        });
    
        Login_botao.setText("Fazer Login");
        Login_botao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Login_botaoActionPerformed(evt);
            }
        });
    
        Criar_botao.setText("Criar conta");
        Criar_botao.setVisible(false);
        Criar_botao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Criar_botaoActionPerformed(evt);
            }
        });
    
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane()); // Create a new GroupLayout for the content pane
        this.getContentPane().setLayout(layout); // Set the layout for the content pane
    
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                       .addGroup(layout.createSequentialGroup()
                           .addGap(130, 130, 130)
                           .addComponent(Login_botao)
                           .addGap(18, 18, 18)
                           .addComponent(Criar_botao))
                       .addGroup(layout.createSequentialGroup()
                           .addGap(19, 19, 19)
                           .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                               .addComponent(jLabel3)
                               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(jLabel2)
                                   .addComponent(jLabel1)
                                   .addComponent(usuario_local, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                   .addComponent(Senha_local)))))
                   .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                   .addGap(57, 57, 57)
                   .addComponent(jLabel3)
                   .addGap(56, 56, 56)
                   .addComponent(jLabel1)
                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                   .addComponent(usuario_local, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addGap(14, 14, 14)
                   .addComponent(jLabel2)
                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                   .addComponent(Senha_local, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addGap(18, 18, 18)
                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                       .addComponent(Login_botao)
                       .addComponent(Criar_botao))
                   .addContainerGap(11, Short.MAX_VALUE))
        );
        addAdmin();
    }                    

    private void usuario_localActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void Senha_localActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           
/* --------------------------- ENTRAR COM UM USUÁRIO EXISTENTE ------------------- */
    private void Login_botaoActionPerformed(java.awt.event.ActionEvent evt) {                                            
    String usuario = usuario_local.getText();
    String senha = Senha_local.getText();
    Usuario objUsuario = new Usuario();

    // Validação
        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            return;
        }
        // Verifica se usuário existe
        if (objUsuario.olharUsuario(usuario, senha)) {
            loggedUser = usuario;
            MainFrame tela = new MainFrame();
            tela.iniciar();
            tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // comportamento ao fechar a janela
            tela.setSize(1450, 875);
            tela.setLocationRelativeTo(null); // Centralizar a janela
            tela.setVisible(true);
            dispose();// fecha a janela de login

        } else {
            JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s).");
        }
    }               

    /* ---------------------------- CRIAR NOVO USUÁRIO ---------------------------- */
    private void Criar_botaoActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String usuario = this.usuario_local.getText();
        String senha = this.Senha_local.getText();
        Usuario objUsuario = new Usuario();
    
        // Validação
        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            return;
        }

        // Usa uma expressãozinha regular para não deixar cadastrar caracter especial e dar treta
        if (usuario.matches(".*[^a-zA-Z0-9].*")) {
            JOptionPane.showMessageDialog(null, "Usuário informado não pode conter caracteres especiais");
            return;
        }
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        email = usuario + "@NeonNight.com";
        if (usuarioDAO.validarEmailExistente(email)) {
            JOptionPane.showMessageDialog(null, "Email informado já existe.");
            return;
        }
    

        if (objUsuario.addUsuario(usuario, senha)) {
            this.usuario_local.setText("");
            this.Senha_local.setText("");
            JOptionPane.showMessageDialog(null, "Cadastrado. Seu novo Email da Empresa: " + email);
        }
    }                                           
    public void addAdmin(){
        Usuario objUsuario = new Usuario();
        String admin = "Admin@NeonNight.com";
        String usuario = "Admin";
        String senha = "Admin";
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.validarEmailExistente(admin)) {
            System.out.println("Admin já está criado");
            return;
        } else {
            if (objUsuario.addUsuario(usuario, senha)) {
                System.out.println("Admin criado");
            return;
            }

        }
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton Criar_botao;
    private javax.swing.JButton Login_botao;
    private javax.swing.JTextField Senha_local;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField usuario_local;
    // End of variables declaration                   
}


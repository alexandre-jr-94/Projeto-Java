package View;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import DAO.UsuarioDAO;
import Model.Usuario;

public class abaAdmin extends JFrame {

    private Usuario objUsuario;
    
    public abaAdmin(){
        this.objUsuario = new Usuario();
        iniciarAdmin();
    }


void iniciarAdmin() {

    jScrollPane1 = new javax.swing.JScrollPane();
    Tabela_usuario = new javax.swing.JTable();
    Deletar_usuario = new javax.swing.JButton();
    jSeparator1 = new javax.swing.JSeparator();
    Campo_usuario = new javax.swing.JTextField();
    Senha_Usuario = new javax.swing.JPasswordField();
    Criar_usuario_ = new javax.swing.JButton();



    setBackground(new java.awt.Color(10, 3, 36));

    Tabela_usuario.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null},
            {null, null, null},
            {null, null, null},
            {null, null, null}
        },
        new String [] {
            "ID", "Nome", "Email"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jScrollPane1.setViewportView(Tabela_usuario);

    Deletar_usuario.setBackground(new java.awt.Color(255,255,255));
    Deletar_usuario.setForeground(new java.awt.Color(0, 0, 0));
    Deletar_usuario.setText("Deletar");
    Deletar_usuario.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Deletar_usuarioActionPerformed(evt);
        }
    });

    Campo_usuario.setText("Usuario");
    Campo_usuario.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Campo_usuarioActionPerformed(evt);
        }
    });

    Senha_Usuario.setText("Senha");
    Senha_Usuario.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Senha_UsuarioActionPerformed(evt);
        }
    });

    Criar_usuario_.setBackground(new java.awt.Color(255,255,255));
    Criar_usuario_.setForeground(new java.awt.Color(0, 0, 0));
    Criar_usuario_.setText("Criar");
    Criar_usuario_.setActionCommand("Criar");
    Criar_usuario_.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Criar_usuario_ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Deletar_usuario)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Senha_Usuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                            .addComponent(Campo_usuario, javax.swing.GroupLayout.Alignment.LEADING))
                        .addComponent(Criar_usuario_))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(Deletar_usuario)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(Campo_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(Senha_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(Criar_usuario_)
            .addContainerGap(76, Short.MAX_VALUE))
    );
    carregatabelausuario();
}// </editor-fold>                        

private void Campo_usuarioActionPerformed(java.awt.event.ActionEvent evt) {                                              

}                                             

private void Criar_usuario_ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        String usuario = this.Campo_usuario.getText();
        String senha = this.Senha_Usuario.getText();
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
        String email = usuario + "@NeonNight.com";
        if (usuarioDAO.validarEmailExistente(email)) {
            JOptionPane.showMessageDialog(null, "Email informado já existe.");
            return;
        }
    

        if (objUsuario.addUsuario(usuario, senha)) {
            this.Campo_usuario.setText("");
            this.Senha_Usuario.setText("");
            JOptionPane.showMessageDialog(null, "Cadastrado. Seu novo Email da Empresa: " + email);}
            carregatabelausuario();
}                                              

private void Senha_UsuarioActionPerformed(java.awt.event.ActionEvent evt) {                                              
    // TODO add your handling code here:
}                                             

private void Deletar_usuarioActionPerformed(java.awt.event.ActionEvent evt) {                                                
    // TODO add your handling code here:
    int linha = Tabela_usuario.getSelectedRow();
    if (linha == -1) {
        JOptionPane.showMessageDialog(null, "Selecione um usuário para deletar.");
    } else {
        String idUsuario = Tabela_usuario.getValueAt(linha, 0).toString();
        int idUsuarioInt = Integer.parseInt(idUsuario);
        if (this.objUsuario.DeleteUsuario(idUsuarioInt)) {
        carregatabelausuario();
        }
    }
        
}     

public void carregatabelausuario() {
    if (Tabela_usuario!= null && objUsuario!= null) {
        DefaultTableModel modelo = (DefaultTableModel) this.Tabela_usuario.getModel();
        modelo.setRowCount(0);
        ArrayList<Object> minhaListaUsuario = new ArrayList<>();
        minhaListaUsuario = objUsuario.getLista();
        for (Object obj : minhaListaUsuario) {
            modelo.addRow(new Object[]{((Usuario) obj).getId(), ((Usuario) obj).getNome(), ((Usuario) obj).getEmail()});
        }
    }
}


// Variables declaration - do not modify                     
private javax.swing.JTextField Campo_usuario;
private javax.swing.JButton Criar_usuario_;
private javax.swing.JButton Deletar_usuario;
private javax.swing.JPasswordField Senha_Usuario;
private javax.swing.JTable Tabela_usuario;
private javax.swing.JScrollPane jScrollPane1;
private javax.swing.JSeparator jSeparator1;
// End of variables declaration                   
}
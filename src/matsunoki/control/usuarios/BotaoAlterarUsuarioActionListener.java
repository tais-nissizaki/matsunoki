package matsunoki.control.usuarios;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import matsunoki.view.Principal;

import matsunoki.bean.Usuario;

public class BotaoAlterarUsuarioActionListener implements ActionListener {

    private JPanel panelFormularioUsuarios;
    private JTable tabela;
    private UsuariosTableModel usuarioTableModel;

    public BotaoAlterarUsuarioActionListener(JPanel panelFormularioUsuarios, JTable tabela, UsuariosTableModel usuarioTableModel) {
        this.panelFormularioUsuarios = panelFormularioUsuarios;
        this.tabela = tabela;
        this.usuarioTableModel = usuarioTableModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Nenhum usuario foi selecionado");
        } else {
            SelecionadorJTextField selecionadorJTextField = new SelecionadorJTextField(panelFormularioUsuarios);
            Usuario usuario = ((UsuariosTableModel) tabela.getModel()).getUsuarios().get(selectedRow);
            selecionadorJTextField.obterJTextField("codigoUsuario").setText(String.valueOf(usuario.getIdUsuario()));
            selecionadorJTextField.obterJTextField("nomeUsuario").setText(usuario.getNome());
            selecionadorJTextField.obterJTextField("cpf").setText(usuario.getCpf());
            selecionadorJTextField.obterJTextField("funcao").setText(usuario.getFuncao());
            selecionadorJTextField.obterJTextField("login").setText(usuario.getLogin());
            selecionadorJTextField.obterJTextField("senha").setText(usuario.getSenha());
            panelFormularioUsuarios.setVisible(true);
            usuarioTableModel.fireTableDataChanged();
            Principal.janelaPrincipal.invalidate();
            Principal.janelaPrincipal.validate();
            Principal.janelaPrincipal.repaint();
        }
    }

}

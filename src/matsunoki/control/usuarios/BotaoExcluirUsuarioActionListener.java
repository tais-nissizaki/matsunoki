package matsunoki.control.usuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import matsunoki.view.Principal;
import matsunoki.bean.Usuario;
import matsunoki.dao.UsuarioDAO;

public class BotaoExcluirUsuarioActionListener implements ActionListener {
	
	private UsuariosTableModel usuarioTableModel;
	private JTable tabela;

	public BotaoExcluirUsuarioActionListener(UsuariosTableModel usuarioTableModel, JTable tabela) {
		this.usuarioTableModel = usuarioTableModel;
		this.tabela = tabela;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		int selectedRow = tabela.getSelectedRow();
		if(selectedRow < 0) {
			JOptionPane.showMessageDialog(null, "Nenhum usuario foi selecionado");
		} else {
			Usuario usuario = ((UsuariosTableModel)tabela.getModel()).getUsuarios().get(selectedRow);
			try {
                            UsuarioDAO.excluirUsuario(usuario);                        
                            usuarioTableModel.fireTableDataChanged();
                            Principal.janelaPrincipal.invalidate();
                            Principal.janelaPrincipal.validate();
                            Principal.janelaPrincipal.repaint();
                            JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Usuario Excluído com sucesso");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Falha ao excluir o usuario");
                        }
		}
	}

}

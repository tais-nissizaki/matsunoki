package matsunoki.control.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import matsunoki.view.Principal;
import matsunoki.bean.Cliente;
import matsunoki.dao.ClienteDAO;
import matsunoki.dao.ProdutoDAO;

public class BotaoExcluirClienteActionListener implements ActionListener {
	
	private ClientesTableModel clienteTableModel;
	private JTable tabela;

	public BotaoExcluirClienteActionListener(ClientesTableModel clienteTableModel, JTable tabela) {
		this.clienteTableModel = clienteTableModel;
		this.tabela = tabela;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		int selectedRow = tabela.getSelectedRow();
		if(selectedRow < 0) {
			JOptionPane.showMessageDialog(null, "Nenhum cliente foi selecionado");
		} else {
			Cliente cliente = ((ClientesTableModel)tabela.getModel()).getClientes().get(selectedRow);
			try {
                            ClienteDAO.excluirCliente(cliente);                        
                            clienteTableModel.fireTableDataChanged();
                            Principal.janelaPrincipal.invalidate();
                            Principal.janelaPrincipal.validate();
                            Principal.janelaPrincipal.repaint();
                            JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Cliente Excluído com sucesso");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Falha ao excluir o produto");
                        }
		}
	}

}

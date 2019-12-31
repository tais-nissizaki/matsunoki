package matsunoki.control.produtos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import matsunoki.view.Principal;
import matsunoki.bean.Produto;
import matsunoki.dao.ProdutoDAO;

public class BotaoExcluirProdutoActionListener implements ActionListener {
	
	private ProdutosTableModel produtoTableModel;
	private JTable tabela;
	private JTextField codigoProdutoTextField;
	private JTextField descricaoProdutoTextField; 
	private JTextField precoProdutoTextField;

	public BotaoExcluirProdutoActionListener(ProdutosTableModel produtoTableModel, JTable tabela, JTextField codigoProdutoTextField,
			JTextField descricaoProdutoTextField, JTextField precoProdutoTextField) {
		this.produtoTableModel = produtoTableModel;
		this.codigoProdutoTextField = codigoProdutoTextField;
		this.descricaoProdutoTextField = descricaoProdutoTextField;
		this.precoProdutoTextField = precoProdutoTextField;
		this.tabela = tabela;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		int selectedRow = tabela.getSelectedRow();
		if(selectedRow < 0) {
			JOptionPane.showMessageDialog(null, "Nenhum produto foi selecionado");
		} else {
			Produto produto = ((ProdutosTableModel)tabela.getModel()).getProdutos().get(selectedRow);
                        try {
                            ProdutoDAO.excluirProduto(produto);
                            JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Produto Excluído com sucesso");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Falha ao excluir o produto");
                        }
			produtoTableModel.fireTableDataChanged();
			Principal.janelaPrincipal.invalidate();
			Principal.janelaPrincipal.validate();
			Principal.janelaPrincipal.repaint();
		}
	}

}

package matsunoki.control.produtos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import matsunoki.view.Principal;

public class BotaoCancelarProdutoActionListener implements ActionListener {
	private JPanel panelFormularioProdutos;
	private JTextField codigoProdutoTextField;
	private JTextField descricaoProdutoTextField; 
	private JTextField precoProdutoTextField;

	public BotaoCancelarProdutoActionListener(JPanel panelFormularioProdutos, JTextField codigoProdutoTextField, JTextField descricaoProdutoTextField, JTextField precoProdutoTextField) {
		this.panelFormularioProdutos = panelFormularioProdutos;
		this.codigoProdutoTextField = codigoProdutoTextField;
		this.descricaoProdutoTextField = descricaoProdutoTextField;
		this.precoProdutoTextField = precoProdutoTextField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.codigoProdutoTextField.setText("");
		this.descricaoProdutoTextField.setText("");
		this.precoProdutoTextField.setText("");
		this.panelFormularioProdutos.setVisible(false);
		Principal.janelaPrincipal.invalidate();
		Principal.janelaPrincipal.validate();
		Principal.janelaPrincipal.repaint();
	}

}

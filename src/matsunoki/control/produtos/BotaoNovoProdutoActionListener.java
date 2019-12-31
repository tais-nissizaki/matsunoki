package matsunoki.control.produtos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class BotaoNovoProdutoActionListener implements ActionListener {
	
	private JPanel panelFormularioProdutos;
	private JTextField codigoProdutoTextField;
	private JTextField descricaoProdutoTextField; 
	private JTextField precoProdutoTextField;
        private JLabel imagemLabel;
        
	public BotaoNovoProdutoActionListener(JPanel panelFormularioProdutos, JTextField codigoProdutoTextField,
			JTextField descricaoProdutoTextField, JTextField precoProdutoTextField,
                        JLabel imagemLabel) {
		this.panelFormularioProdutos = panelFormularioProdutos;
		this.codigoProdutoTextField = codigoProdutoTextField;
		this.descricaoProdutoTextField = descricaoProdutoTextField;
		this.precoProdutoTextField = precoProdutoTextField;
                this.imagemLabel = imagemLabel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.panelFormularioProdutos.setVisible(true);
		this.codigoProdutoTextField.setText("");
		this.descricaoProdutoTextField.setText("");
		this.precoProdutoTextField.setText("");
                this.imagemLabel.setText("Imagem");
                this.imagemLabel.setIcon(null);
	}

}

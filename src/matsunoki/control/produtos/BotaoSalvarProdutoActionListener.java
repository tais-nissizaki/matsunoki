package matsunoki.control.produtos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import matsunoki.view.Principal;
import matsunoki.bean.Produto;
import matsunoki.dao.ProdutoDAO;

public class BotaoSalvarProdutoActionListener implements ActionListener {
	private ProdutosTableModel produtoTableModel;
	private JPanel panelFormularioProdutos;
	private JTextField codigoProdutoTextField;
	private JTextField descricaoProdutoTextField; 
	private JTextField precoProdutoTextField;
        private SelecionarArquivoActionListener actionListener;

	public BotaoSalvarProdutoActionListener(ProdutosTableModel produtoTableModel, JPanel panelFormularioProdutos,
			JTextField codigoProdutoTextField, JTextField descricaoProdutoTextField, JTextField precoProdutoTextField,
                        SelecionarArquivoActionListener  actionListener) {
		this.produtoTableModel = produtoTableModel;
		this.panelFormularioProdutos = panelFormularioProdutos;
		this.codigoProdutoTextField = codigoProdutoTextField;
		this.descricaoProdutoTextField = descricaoProdutoTextField;
		this.precoProdutoTextField = precoProdutoTextField;
                this. actionListener =  actionListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Produto produto = new Produto();
		boolean dadosValidos = true;
		if(this.codigoProdutoTextField.getText() != null && !this.codigoProdutoTextField.getText().trim().equals("")) {
			try {
				produto.setCodigoProduto(Integer.parseInt(this.codigoProdutoTextField.getText()));
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Código do produto inválido");
				dadosValidos = false;
			}
		}
		if(this.descricaoProdutoTextField.getText() != null && !this.descricaoProdutoTextField.getText().trim().equals("")) {
			produto.setDescricao(this.descricaoProdutoTextField.getText());
		} else {
			JOptionPane.showMessageDialog(Principal.janelaPrincipal, "A descrição é inválida");
			dadosValidos = false;
		}

		if(this.precoProdutoTextField.getText() != null && !this.precoProdutoTextField.getText().trim().equals("")) {
			try {
				produto.setPreco(Double.parseDouble(this.precoProdutoTextField.getText()));
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Preço do produto inválido");
				dadosValidos = false;
			}
		}
                if(this.actionListener.getImagemSelecionada() != null && this.actionListener.getImagemSelecionada().isFile() && this.actionListener.getImagemSelecionada().exists()) {
                    
                    try {
                        File caminhoDiretorioFinal = new File("images/");
                        if(!caminhoDiretorioFinal.exists()) {
                            caminhoDiretorioFinal.mkdir();
                        }
                        File caminhoFinal = new File(caminhoDiretorioFinal, this.actionListener.getImagemSelecionada().getName());

                        Files.copy(this.actionListener.getImagemSelecionada().toPath(), caminhoFinal.toPath(), StandardCopyOption.REPLACE_EXISTING);

                        produto.setCaminhoImagem(caminhoFinal.getAbsolutePath());
                    } catch (IOException ex)  {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Falha ao mover a imagem para o epositório");
                        dadosValidos = false;
                    }
                }
		if(dadosValidos) {
                        
                    try {
                        ProdutoDAO.salvarProduto(produto);
                        JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Produto Cadastrado com sucesso");
                        this.codigoProdutoTextField.setText("");
                        this.descricaoProdutoTextField.setText("");
                        this.precoProdutoTextField.setText("");
                        this.panelFormularioProdutos.setVisible(false);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Falha ao salvar o produto");
                    }

                    produtoTableModel.fireTableDataChanged();
                    Principal.janelaPrincipal.invalidate();
                    Principal.janelaPrincipal.validate();
                    Principal.janelaPrincipal.repaint();
		}
	}

}

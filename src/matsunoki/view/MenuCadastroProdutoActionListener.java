package matsunoki.view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import matsunoki.bean.Produto;
import matsunoki.control.produtos.BotaoAlterarProdutoActionListener;
import matsunoki.control.produtos.BotaoCancelarProdutoActionListener;
import matsunoki.control.produtos.BotaoExcluirProdutoActionListener;
import matsunoki.control.produtos.BotaoNovoProdutoActionListener;
import matsunoki.control.produtos.BotaoPesquisarProdutoActionListener;
import matsunoki.control.produtos.BotaoSalvarProdutoActionListener;
import matsunoki.control.produtos.ProdutosTableModel;
import matsunoki.control.produtos.SelecionarArquivoActionListener;
import matsunoki.dao.ProdutoDAO;

public class MenuCadastroProdutoActionListener implements ActionListener {
    
        private File imagemSelecionada;

	public MenuCadastroProdutoActionListener() {
		super();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Principal.janelaPrincipal.setTitle("Floricultura Matsu No Ki - Cadastro de Produtos");
	
		JPanel panelCadastroProdutos = new JPanel(new GridLayout(2, 1));
		JPanel panelListagemProdutos = new JPanel();
		panelListagemProdutos.setLayout(null);
		panelCadastroProdutos.add(panelListagemProdutos);
		
		// Cria o painal com o formulario de cadastro de produtos
		JPanel panelFormularioProdutos = new JPanel();
		panelFormularioProdutos.setLayout(null);
		panelFormularioProdutos.setBorder(new LineBorder(Color.BLUE));
		panelFormularioProdutos.setVisible(false);
		panelCadastroProdutos.add(panelFormularioProdutos);
		
		// Label do código
		JLabel codigoProdutolLabel = new JLabel("Código do Produto:  ");
		codigoProdutolLabel.setHorizontalAlignment(JLabel.RIGHT); // Alinha o texto à direita
		codigoProdutolLabel.setBounds(5, 30, 450, 15); // Define a posição x/y e o tamanho largura/altura, nessa ordem
		panelFormularioProdutos.add(codigoProdutolLabel); //adiciona ao painel de formulário de cadastro
		
		// Campo de texto
		JTextField codigoProdutoTextField = new JTextField();
		codigoProdutoTextField.setBounds(460, 26, 300, 20);// Define a posição x/y e o tamanho largura/altura, nessa ordem
		codigoProdutoTextField.setEditable(false);// Não pode alterar, o sistema tem que designar um código
		panelFormularioProdutos.add(codigoProdutoTextField);//adiciona ao painel de formulário de cadastro
		
		JLabel descricaoProdutoLabel = new JLabel("Descrição do Produto:  ");
		descricaoProdutoLabel.setHorizontalAlignment(JLabel.RIGHT);
		descricaoProdutoLabel.setBounds(5, 60, 450, 15);
		panelFormularioProdutos.add(descricaoProdutoLabel);
		
		JTextField descricaoProdutoTextField = new JTextField();
		descricaoProdutoTextField.setBounds(460, 56, 300, 20);
		panelFormularioProdutos.add(descricaoProdutoTextField);
		
		JLabel precoProdutolLabel = new JLabel("Preço do Produto:  ");
		precoProdutolLabel.setHorizontalAlignment(JLabel.RIGHT);
		precoProdutolLabel.setBounds(5, 86, 450, 15);
		panelFormularioProdutos.add(precoProdutolLabel);
		
		JTextField precoProdutoTextField = new JTextField();
		precoProdutoTextField.setBounds(460, 82, 300, 20);
		panelFormularioProdutos.add(precoProdutoTextField);
                
                
                JLabel imagemLabel = new JLabel("Image");
                imagemLabel.setBorder(new LineBorder(Color.BLACK));
                imagemLabel.setBounds(780, 28, 380, 190);
                imagemLabel.setBackground(Color.white);
                imagemLabel.setHorizontalAlignment(JLabel.CENTER);
                imagemLabel.setVerticalAlignment(JLabel.CENTER);
                imagemLabel.setText("Imagem");
		panelFormularioProdutos.add(imagemLabel);
                
                JButton selecionarImagem = new JButton("Selecionar Imagem");
		selecionarImagem.setBounds(890, 230, 180, 20);
                SelecionarArquivoActionListener selecionarArquivoActionListener = new SelecionarArquivoActionListener(imagemLabel);
                selecionarImagem.addActionListener(selecionarArquivoActionListener);

		panelFormularioProdutos.add(selecionarImagem);
		

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(new BotaoCancelarProdutoActionListener(panelFormularioProdutos, codigoProdutoTextField, descricaoProdutoTextField, precoProdutoTextField));
		buttonCancelar.setBounds(590, 230, 100, 20);
		panelFormularioProdutos.add(buttonCancelar);

		JButton buttonNovoProduto = new JButton("Novo Produto");
		buttonNovoProduto.addActionListener(new BotaoNovoProdutoActionListener(panelFormularioProdutos, codigoProdutoTextField, descricaoProdutoTextField, precoProdutoTextField, imagemLabel));
		buttonNovoProduto.setBounds(1035, 245, 150, 20);
		panelListagemProdutos.add(buttonNovoProduto);
		
		// Para exibir a tabela de produtos, � necess�rio utilizar o painel com scroll
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 38, 1180, 200);
		
		// Tabela de listagem de produtos
		ProdutosTableModel produtoTableModel = preencherProdutoTableModel();
		final JTable listagemProdutos = new JTable(produtoTableModel);
		listagemProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// Define o tipo de sele��o para apenas um registro por vez
                
                ListSelectionModel listSelecionModel = listagemProdutos.getSelectionModel();
                listSelecionModel.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        try {
                            int row = listagemProdutos.getSelectedRow();
                            if(row >= 0) {
                                Produto produto = ((ProdutosTableModel)listagemProdutos.getModel()).getProdutos().get(row);
                                if(produto.getCaminhoImagem() != null && !produto.getCaminhoImagem().trim().isEmpty()) {
                                    File file = new File(produto.getCaminhoImagem());
                                    if(file.exists()) {
                                        int canvasWidth = 400;
                                        int canvasHeight = 400;
                                        JDialog d = new JDialog(Principal.janelaPrincipal, Dialog.ModalityType.DOCUMENT_MODAL);
                                        d.setBounds(100, 100, canvasWidth, canvasHeight+30);

                                        BufferedImage resizedImage = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);

                                        JLabel imgLabel = new JLabel();
                                        imgLabel.setBounds(0, 0, canvasWidth, canvasHeight);
                                        imgLabel.setText("Imagem");

                                        imgLabel.setText(null);                                    

                                        BufferedImage image = ImageIO.read(file);
                                        double ratio = image.getHeight()/ image.getWidth();

                                        double labelRatio = canvasHeight / canvasWidth;

                                        int x1 = 0; // top left X position
                                        int y1 = 0; // top left Y position
                                        int x2 = 0; // bottom right X position
                                        int y2 = 0; // bottom right Y position

                                        if (image.getWidth() < canvasWidth && image.getHeight() < canvasHeight) {
                                            // the image is smaller than the canvas
                                            x1 = (canvasWidth - image.getWidth())  / 2;
                                            y1 = (canvasHeight - image.getHeight()) / 2;
                                            x2 = image.getWidth(null) + x1;
                                            y2 = image.getHeight(null) + y1;

                                        } else {
                                            if (labelRatio > ratio) {
                                                y1 = canvasHeight;
                                                // keep image aspect ratio
                                                canvasHeight = (int) (canvasWidth * ratio);
                                                y1 = (y1 - canvasHeight) / 2;
                                            } else {
                                                x1 = canvasWidth;
                                                // keep image aspect ratio
                                                canvasWidth = (int) (canvasHeight / ratio);
                                                x1 = (x1 - canvasWidth) / 2;
                                            }
                                            x2 = canvasWidth + x1;
                                            y2 = canvasHeight + y1;
                                        }

                                        Graphics2D g = resizedImage.createGraphics();
                                        g.drawImage(image, x1, y1, x2, y2, 0, 0, image.getWidth(), image.getHeight(), null);

                                        imgLabel.setIcon(new ImageIcon(resizedImage));

                                        d.add(imgLabel);
                                        d.setVisible(true);
                                    }
                                }
                            }
                        }catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    
                });
                
		scrollPane.setViewportView(listagemProdutos);
		panelListagemProdutos.add(scrollPane);

		// Bot�o Salvar
		JButton botaoSalvar = new JButton("Salvar");
		// Adiciona um processador de a��o (clique) no bot�o
		botaoSalvar.addActionListener(new BotaoSalvarProdutoActionListener(produtoTableModel, panelFormularioProdutos, codigoProdutoTextField, descricaoProdutoTextField, precoProdutoTextField, 
                        selecionarArquivoActionListener));
                System.err.println(selecionarArquivoActionListener);
		botaoSalvar.setBounds(460, 230, 100, 20);
		panelFormularioProdutos.add(botaoSalvar);

		JButton buttonAlterarProduto = new JButton("Alterar Produto");
		buttonAlterarProduto.addActionListener(new BotaoAlterarProdutoActionListener(panelFormularioProdutos, listagemProdutos,
				codigoProdutoTextField, descricaoProdutoTextField, precoProdutoTextField, imagemLabel));
		buttonAlterarProduto.setBounds(835, 245, 150, 20);
		panelListagemProdutos.add(buttonAlterarProduto);

		JButton buttonExcluirProduto = new JButton("Excluir Produto");
		buttonExcluirProduto.addActionListener(new BotaoExcluirProdutoActionListener(produtoTableModel, listagemProdutos,
				codigoProdutoTextField, descricaoProdutoTextField, precoProdutoTextField));
				buttonExcluirProduto.setBounds(635, 245, 150, 20);
		panelListagemProdutos.add(buttonExcluirProduto);
                
                
                //componente de filtro por nome
		JTextField pesquisaPeodutoTextField = new JTextField();
		pesquisaPeodutoTextField.setBounds(700, 8, 300, 20);
		panelListagemProdutos.add(pesquisaPeodutoTextField);
                
                JButton buttonPesquisarProduto = new JButton("Pesquisar");
		buttonPesquisarProduto.setBounds(1035, 8, 150, 20);
                buttonPesquisarProduto.addActionListener(new BotaoPesquisarProdutoActionListener(listagemProdutos, pesquisaPeodutoTextField));
		panelListagemProdutos.add(buttonPesquisarProduto);
                
		JLabel campoPesquisaNomeLabel = new JLabel("Pesquisa por descrição:");
		campoPesquisaNomeLabel.setHorizontalAlignment(JLabel.RIGHT);
		campoPesquisaNomeLabel.setBounds(527, 11, 170, 15);
		panelListagemProdutos.add(campoPesquisaNomeLabel);
		
		
		
//		Principal.janelaPrincipal.removeAll();
		Principal.janelaPrincipal.setContentPane(panelCadastroProdutos);
		Principal.janelaPrincipal.invalidate();
		Principal.janelaPrincipal.validate();
		Principal.janelaPrincipal.repaint();
	}

	// Preenche a tabela de listagem de produtos
	private ProdutosTableModel preencherProdutoTableModel() {
		List<Produto> produtos = ProdutoDAO.obterTodosProdutos();
		return new ProdutosTableModel(produtos, new String[] {"Código do Produto", "Descrição do Produto", "Preço do Produto"});
	}

}

package matsunoki.control.produtos;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import matsunoki.bean.Produto;

public class BotaoAlterarProdutoActionListener implements ActionListener {
	
	private JPanel panelFormularioProdutos;
	private JTable tabela;
	private JTextField codigoProdutoTextField;
	private JTextField descricaoProdutoTextField; 
	private JTextField precoProdutoTextField;
        private JLabel imagemLabel;

	public BotaoAlterarProdutoActionListener(JPanel panelFormularioProdutos, JTable tabela, JTextField codigoProdutoTextField,
			JTextField descricaoProdutoTextField, JTextField precoProdutoTextField, JLabel imagemLabel) {
		this.panelFormularioProdutos = panelFormularioProdutos;
		this.codigoProdutoTextField = codigoProdutoTextField;
		this.descricaoProdutoTextField = descricaoProdutoTextField;
		this.precoProdutoTextField = precoProdutoTextField;
		this.tabela = tabela;
                this.imagemLabel = imagemLabel;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		int selectedRow = tabela.getSelectedRow();
		if(selectedRow < 0) {
			JOptionPane.showMessageDialog(null, "Nenhum produto foi selecionado");
		} else {
			Produto produto = ((ProdutosTableModel)tabela.getModel()).getProdutos().get(selectedRow);
			codigoProdutoTextField.setText(String.valueOf(produto.getCodigoProduto()));
			descricaoProdutoTextField.setText(produto.getDescricao());
			precoProdutoTextField.setText(String.valueOf(produto.getPreco()));
                        try {
                            if(produto.getCaminhoImagem() != null && !produto.getCaminhoImagem().trim().isEmpty()) {
                                File file = new File(produto.getCaminhoImagem());
                                imagemLabel.setText("Imagem");
                                if(file.exists()) {
                                    imagemLabel.setText(null);                                    

                                    BufferedImage image = ImageIO.read(file);
                                    double ratio = image.getHeight()/ image.getWidth();

                                    int canvasWidth = 380;
                                    int canvasHeight = 190;
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

                                    BufferedImage resizedImage = new BufferedImage(380, 190, BufferedImage.TYPE_INT_RGB);
                                    Graphics2D g = resizedImage.createGraphics();
                                    g.drawImage(image, x1, y1, x2, y2, 0, 0, image.getWidth(), image.getHeight(), null);
                
                                    imagemLabel.setIcon(new ImageIcon(resizedImage));
                                } else {
                                    imagemLabel.setIcon(null);
                                }
                            }
                        }catch (IOException ex) {
                            ex.printStackTrace();
                        }
			panelFormularioProdutos.setVisible(true);
		}
	}

}

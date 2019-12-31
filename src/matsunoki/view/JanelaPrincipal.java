package matsunoki.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class JanelaPrincipal extends JFrame {
	
	public JanelaPrincipal() {
		super("Floricultura Matsunoki"); // Mastu= pinho no= de(preposição) Ki = árvore/madeira
		setSize(1200, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void adicionaBarraDeMenu() {
		MenuBar menuBar = new MenuBar();

		adicionaMenuCadastro(menuBar);
		
		setMenuBar(menuBar);
		adicionaLogo();
	}
	
	private void adicionaMenuCadastro(MenuBar menuBar) {
		Menu menu = new Menu("Cadastros");
		
		MenuItem menuItemCadastroProduto = new MenuItem("Cadastro de Produtos");
		menuItemCadastroProduto.addActionListener(new MenuCadastroProdutoActionListener());
		
		menu.add(menuItemCadastroProduto);

		MenuItem menuItemCadastroCliente = new MenuItem("Cadastro de Clientes");
		menuItemCadastroCliente.addActionListener(new MenuCadastroClienteActionListener());
		menu.add(menuItemCadastroCliente);
		
		menuBar.add(menu);
                
                MenuItem menuItemCadastroUsuario = new MenuItem("Cadastro de Usuarios");
		menuItemCadastroUsuario.addActionListener(new MenuCadastroUsuarioActionListener());
		menu.add(menuItemCadastroUsuario);
		
		menuBar.add(menu);
	}
        
        private void adicionaLogo(){
            try {
                JPanel panelPrincipal= new JPanel();
                panelPrincipal.setBounds(0, 0, 1200, 900);
                panelPrincipal.setLayout(new GridLayout(1, 1));
                
                JLabel labelLogo = new JLabel();
                labelLogo.setBounds(0, 0, 1200, 600);
                labelLogo.setIcon(new ImageIcon(ImageIO.read(new File("images/Matsunoki.png"))));
                panelPrincipal.add(labelLogo);
                setContentPane(panelPrincipal);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
	
	
}

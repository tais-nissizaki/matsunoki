package matsunoki.view;

import matsunoki.bean.Cliente;
import matsunoki.bean.Endereco;
import matsunoki.bean.Produto;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import matsunoki.dao.ClienteDAO;
import matsunoki.dao.ProdutoDAO;

public class Principal extends JFrame{
	public static JanelaPrincipal janelaPrincipal;
	
//	private static void carregaProdutos() {
//		ProdutoDAO.salvarProduto(new Produto("Botão de Rosa", 3.5));
//		ProdutoDAO.salvarProduto(new Produto("Dúzia de Botões de Rosa", 38.0));
//		ProdutoDAO.salvarProduto(new Produto("Ciclamen", 15));
//	}
	
	private static void carregaClientes() {
            ClienteDAO.salvarCliente(new Cliente("José da Silva", "02/05/1975", "123.456.789-01", "jose.silva@matsunoki.com.br", new Endereco("Rua das rosas", 100, "", "Bairro das Flores", "Mogi das Cruzes", "SP", "01234-567")));
            ClienteDAO.salvarCliente(new Cliente("João de Souza", "02/10/1988", "987.654.321-09", "joao.souza@matsunoki.com.br", new Endereco("Rua das orquideas", 110, "", "Bairro dos Jardins", "Mogi das Cruzes", "SP", "01234-987")));
            ClienteDAO.salvarCliente(new Cliente("Maria dos Santos", "10/11/1995", "456.123.789-12", "maria.santos@matsunoki.com.br", new Endereco("Rua das Begônias", 680, "Clobo 12, Apartamento 121", "Bairro do Bosque", "Mogi das Cruzes", "SP", "01234-238")));
	}
       
    public static void abrir() {
            janelaPrincipal = new JanelaPrincipal();
            janelaPrincipal.adicionaBarraDeMenu();
            janelaPrincipal.setVisible(true);
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matsunoki.control.produtos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import matsunoki.bean.Produto;
import matsunoki.dao.ProdutoDAO;

/**
 *
 * @author Taís
 */
public class BotaoPesquisarProdutoActionListener implements ActionListener {
    
    private JTable listagemProdutos;
    private JTextField pesquisaPeodutoTextField;

    public BotaoPesquisarProdutoActionListener(JTable listagemProdutos, JTextField pesquisaPeodutoTextField) {
        this.listagemProdutos = listagemProdutos;
        this.pesquisaPeodutoTextField = pesquisaPeodutoTextField;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        List<Produto> produtos = ProdutoDAO.pesquisarPorNome(this.pesquisaPeodutoTextField.getText());
        listagemProdutos.setModel(new ProdutosTableModel(produtos, new String[] {"Código do Produto", "Descrição do Produto", "Preço do Produto"}));
        listagemProdutos.invalidate();
        listagemProdutos.repaint();
    }
    
}

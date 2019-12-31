package matsunoki.dao;

import matsunoki.bd.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import matsunoki.bean.Produto;

public class ProdutoDAO {
	
	public static List<Produto> obterTodosProdutos() {
            List<Produto> produtos = new ArrayList<>();
            Connection conexao = null;
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                conexao = DBHelper.getConnect();
                statement = conexao.createStatement();
                resultSet = statement.executeQuery("select * from produto");
                while(resultSet.next()) {
                    produtos.add(new Produto(resultSet.getInt("idproduto"), resultSet.getString("descricao"), resultSet.getDouble("preco"), resultSet.getString("caminho_imagem")));
                }
                return produtos;
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }  finally { //Sempre executa esse trecho de código, independente de lançar exceção ou não
                DBHelper.closeConnection(conexao);
                DBHelper.closeStatement(statement);
                DBHelper.closeResultSet(resultSet);
            }
            return null;
	}
	
	//Salva o produto
	public static int salvarProduto(Produto produto) {
		//Verifica se o produto já existe na tabela
		Produto produtoCadastradoNaBase = obterProdutoPorCodigo(produto.getCodigoProduto());
		// Se não existir
                Connection conexao = null;
                PreparedStatement statement = null;
                ResultSet tableKeys = null;
                int codigo = -1;
                try {
                    conexao = DBHelper.getConnect();                   
                    
                    if(produtoCadastradoNaBase == null) {
                        //Não colocar a coluna idproduto, pois o banco de dados vai gerar esse valor
                        statement = conexao.prepareStatement("insert into produto (descricao, preco, caminho_imagem) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                        statement.setString(1, produto.getDescricao());
                        statement.setDouble(2, produto.getPreco());
                        statement.setString(3, produto.getCaminhoImagem());
                    
                        statement.executeUpdate();
                        tableKeys = statement.getGeneratedKeys();
                        tableKeys.next();
                        codigo = tableKeys.getInt(1);
                    } else { //Se existir - é uma alteração
                        //Não colocar a coluna idproduto, pois o banco de dados vai gerar esse valor
                        statement = conexao.prepareStatement("update produto set descricao = ?, preco = ?, caminho_imagem = ? where idproduto = ?");
                        statement.setString(1, produto.getDescricao());
                        statement.setDouble(2, produto.getPreco());
                        statement.setString(3, produto.getCaminhoImagem());
                        statement.setInt(4, produto.getCodigoProduto());
                        
                    
                        statement.executeUpdate();
                        codigo = produto.getCodigoProduto();
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }  finally { //Sempre executa esse trecho de código, independente de lançar exceção ou não
                    DBHelper.closeConnection(conexao);
                    DBHelper.closeStatement(statement);
                }
		
		return codigo;
	}
	
	// Obtem o produto pelo código dele
	public static Produto obterProdutoPorCodigo(int codigoProduto) {
            Produto produto = null;
            Connection conexao = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                conexao = DBHelper.getConnect();
                statement = conexao.prepareStatement("select * from produto where idproduto = ?");
                statement.setInt(1, codigoProduto);
                resultSet = statement.executeQuery();
                while(resultSet.next()) {
                    produto = new Produto(resultSet.getInt("idproduto"), resultSet.getString("descricao"), resultSet.getDouble("preco"), resultSet.getString("caminho_imagem"));
                }
                return produto;
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }  finally { //Sempre executa esse trecho de código, independente de lançar exceção ou não
                DBHelper.closeConnection(conexao);
                DBHelper.closeStatement(statement);
                DBHelper.closeResultSet(resultSet);
            }
            return null;
	}
	
	public static void excluirProduto(Produto produto) throws ClassNotFoundException, SQLException {
            Produto produtoCadastradoNaBase = obterProdutoPorCodigo(produto.getCodigoProduto());
            if(produtoCadastradoNaBase != null) {
                Connection conexao = null;
                PreparedStatement statement = null;
                try {
                    conexao = DBHelper.getConnect();
                    statement = conexao.prepareStatement("delete from produto where idproduto = ?");
                    statement.setInt(1, produto.getCodigoProduto());
                    statement.executeUpdate();
                }  finally { //Sempre executa esse trecho de código, independente de lançar exceção ou não
                    DBHelper.closeConnection(conexao);
                    DBHelper.closeStatement(statement);
                }
            }
	}
	
        // Método para pesquisar por parte do nome do produto
	public static List<Produto> pesquisarPorNome(String nomeProduto) {
            List<Produto> produtos = new ArrayList<>();
            Connection conexao = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                conexao = DBHelper.getConnect();
                statement = conexao.prepareStatement("select * from produto where LOWER(descricao) like ?");
                statement.setString(1, "%"+nomeProduto.toLowerCase()+"%");
                resultSet = statement.executeQuery();
                while(resultSet.next()) {
                    produtos.add(new Produto(resultSet.getInt("idproduto"), resultSet.getString("descricao"), resultSet.getDouble("preco"), resultSet.getString("caminho_imagem")));
                }
                return produtos;
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }  finally { //Sempre executa esse trecho de código, independente de lançar exceção ou não
                DBHelper.closeConnection(conexao);
                DBHelper.closeStatement(statement);
                DBHelper.closeResultSet(resultSet);
            }
            return null;
	}
}

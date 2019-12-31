package matsunoki.dao;

import matsunoki.bd.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import matsunoki.bean.Cliente;
import matsunoki.bean.Produto;

public class ClienteDAO {

    private static int codigoCliente = 1;
    private static List<Cliente> clientes = new ArrayList<>();

    public static List<Cliente> obterTodosClientes() {
        List<Cliente> clientes = new ArrayList<>();
        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            conexao = DBHelper.getConnect();
            statement = conexao.createStatement();
            resultSet = statement.executeQuery("select * from cliente");
            while (resultSet.next()) {
                clientes.add(
                        new Cliente(
                                resultSet.getInt("idcliente"),
                                resultSet.getString("nome"),
                                resultSet.getString("dataNascimento"),
                                resultSet.getString("cpf"),
                                resultSet.getString("email"),
                                EnderecoDAO.obterEnderecoPorCodigoCliente(resultSet.getInt("idcliente")))
                );
            }
            return clientes;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally { //Sempre executa esse trecho de código, independente de lançar exceção ou não
            DBHelper.closeConnection(conexao);
            DBHelper.closeStatement(statement);
            DBHelper.closeResultSet(resultSet);
        }
        return null;
    }

    //Salva o produto
    public static int salvarCliente(Cliente cliente) {
        //Verifica se o produto já existe na tabela
        Cliente clienteCadastradoNaBase = obterClientePorCodigo(cliente.getCodigo());
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet tableKeys = null;
        int codigo = -1;
        try {
            conexao = DBHelper.getConnect();
            // Se não existir
            if (clienteCadastradoNaBase == null) {
                statement = conexao.prepareStatement("insert into cliente "
                        + "(nome, dataNascimento, cpf, email) values (?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, cliente.getNome());
                statement.setString(2, cliente.getDataNascimento());
                statement.setString(3, cliente.getCpf());
                statement.setString(4, cliente.getEmail());
                statement.executeUpdate();
                tableKeys = statement.getGeneratedKeys();
                tableKeys.next();
                codigo = tableKeys.getInt(1);
                

            } else { //Se existir
                statement = conexao.prepareStatement("update cliente set nome = ?, dataNascimento = ?, cpf = ?, email = ? where idcliente = ?");

                statement.setString(1, cliente.getNome());
                statement.setString(2, cliente.getDataNascimento());
                statement.setString(3, cliente.getCpf());
                statement.setString(4, cliente.getEmail());
                statement.setInt(5, cliente.getCodigo());

                statement.executeUpdate();
                codigo = cliente.getCodigo();
            }
            EnderecoDAO.salvarEndereco(codigo, cliente.getEndereco());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally { //Sempre executa esse trecho de código, independente de lançar exceção ou não
            DBHelper.closeConnection(conexao);
            DBHelper.closeStatement(statement);
        }

        return codigo;
    }

    // Obtem o produto pelo código dele
    public static Cliente obterClientePorCodigo(int codigocliente) {
        Cliente cliente = null;
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conexao = DBHelper.getConnect();
            statement = conexao.prepareStatement("select * from cliente where idcliente = ?");
            statement.setInt(1, codigocliente);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cliente = new Cliente(
                        resultSet.getInt("idcliente"),
                        resultSet.getString("nome"),
                        resultSet.getString("dataNascimento"),
                        resultSet.getString("cpf"),
                        resultSet.getString("email"),
                        EnderecoDAO.obterEnderecoPorCodigoCliente(resultSet.getInt("idcliente")));
            }
            return cliente;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally { //Sempre executa esse trecho de código, independente de lançar exceção ou não
            DBHelper.closeConnection(conexao);
            DBHelper.closeStatement(statement);
            DBHelper.closeResultSet(resultSet);
        }
        return null;
    }

    public static void excluirCliente(Cliente Cliente) throws ClassNotFoundException, SQLException {
        Cliente clienteCadastradoNaBase = obterClientePorCodigo(Cliente.getCodigo());
        if (clienteCadastradoNaBase != null) {
            clientes.remove(clienteCadastradoNaBase);
            Connection conexao = null;
            PreparedStatement statement = null;
            try {
                conexao = DBHelper.getConnect();
                statement = conexao.prepareStatement("delete from cliente where idcliente = ?");
                statement.setInt(1, Cliente.getCodigo());
                statement.executeUpdate();
            } finally { //Sempre executa esse trecho de código, independente de lançar exceção ou não
                DBHelper.closeConnection(conexao);
                DBHelper.closeStatement(statement);
            }
        }
    }

}

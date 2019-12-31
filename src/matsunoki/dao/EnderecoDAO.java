/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matsunoki.dao;

import matsunoki.bd.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import matsunoki.bean.Cliente;
import matsunoki.bean.Endereco;

/**
 *
 * @author Tais
 */
public class EnderecoDAO {

    public static Endereco obterEnderecoPorCodigoCliente(int codigoCliente) {
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Endereco endereco = null;
        try {
            conexao = DBHelper.getConnect();
            statement = conexao.prepareStatement("select * from endereco where idcliente = ?");
            statement.setInt(1, codigoCliente);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                endereco = new Endereco(
                        resultSet.getInt("idendereco"), codigoCliente,
                        resultSet.getString("logradouro"), resultSet.getInt("numero"),
                        resultSet.getString("complemento"), resultSet.getString("bairro"),
                        resultSet.getString("cidade"),
                        resultSet.getString("estado"),
                        resultSet.getString("cep"));

            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally { //Sempre executa esse trecho de código, independente de lançar exceção ou não
            DBHelper.closeConnection(conexao);
            DBHelper.closeStatement(statement);
            DBHelper.closeResultSet(resultSet);
        }
        return endereco;
    }
    
    
    public static int salvarEndereco(int codigoCliente, Endereco endereco) {
        Endereco enderecoCadastrado = obterEnderecoPorCodigoCliente(codigoCliente);
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet tableKeys = null;
        int codigo = -1;
        try {
            conexao = DBHelper.getConnect();
            // Se não existir
            if (enderecoCadastrado == null) {
                System.out.println("Cadastra o Endereço");
                statement = conexao.prepareStatement("insert into endereco "
                        + "(idcliente, logradouro, numero,complemento"
                        + ", bairro, cidade, estado,cep) values (?, ?, ?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);

                statement.setInt(1, codigoCliente);
                statement.setString(2, endereco.getNomeLogradouro());
                statement.setInt(3, endereco.getNumeroLogradouro());
                statement.setString(4, endereco.getComplemento());
                statement.setString(5, endereco.getBairro());
                statement.setString(6, endereco.getCidade());
                statement.setString(7, endereco.getEstado());
                statement.setString(8, endereco.getCep());
                statement.executeUpdate();
                tableKeys = statement.getGeneratedKeys();
                tableKeys.next();
                codigo = tableKeys.getInt(1);
                endereco.setCodigoEndereco(codigo);

            } else { //Se existir
                System.out.println("Altera o Endereço" + endereco.getCodigoEndereco());
                        
                statement = conexao.prepareStatement("update endereco set "
                        + "logradouro = ?, numero = ?, complemento = ?, bairro = ?, "
                        + "cidade = ?, estado = ?, cep = ? where idcliente = ?");

                statement.setString(1, endereco.getNomeLogradouro());
                statement.setInt(2, endereco.getNumeroLogradouro());
                statement.setString(3, endereco.getComplemento());
                statement.setString(4, endereco.getBairro());
                statement.setString(5, endereco.getCidade());
                statement.setString(6, endereco.getEstado());
                statement.setString(7, endereco.getEstado());
                statement.setInt(8, codigoCliente);

                statement.executeUpdate();
                codigo = endereco.getCodigoEndereco();
            }
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

}

package matsunoki.dao;

import matsunoki.bd.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import matsunoki.bean.Usuario;

public class UsuarioDAO {

    private static int codigoUsuario = 1;
    private static List<Usuario> usuarios = new ArrayList<>();

    public static List<Usuario> obterTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            conexao = DBHelper.getConnect();
            statement = conexao.createStatement();
            resultSet = statement.executeQuery("select * from usuario");
            while (resultSet.next()) {
                usuarios.add(new Usuario(
                                resultSet.getInt("idusuario"),
                                resultSet.getString("nome"),
                                resultSet.getString("cpf"),
                                resultSet.getString("funcao"),
                                resultSet.getString("login"),
                                resultSet.getString("senha"))
                );
            }
            return usuarios;
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

    //Salva o usuario
    public static int salvarUsuario(Usuario usuario) {
        //Verifica se o usuario já existe na tabela
        Usuario usuarioCadastradoNaBase = obterUsuarioPorCodigo(usuario.getIdUsuario());
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet tableKeys = null;
        int codigo = -1;
        try {
            conexao = DBHelper.getConnect();
            // Se não existir
            if (usuarioCadastradoNaBase == null) {
                statement = conexao.prepareStatement("insert into usuario "
                        + "(nome, cpf, funcao, login, senha) values (?, ?, ?, ?, ?)",
                       Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, usuario.getNome());
                statement.setString(2, usuario.getCpf());
                statement.setString(3, usuario.getFuncao());
                statement.setString(4, usuario.getLogin());
                statement.setString(5, usuario.getSenha());
                statement.executeUpdate();
                tableKeys = statement.getGeneratedKeys();
                tableKeys.next();
                codigo = tableKeys.getInt(1);
                

            } else { //Se existir
                statement = conexao.prepareStatement("update usuario set nome = ?, cpf = ?, funcao = ?, login = ?, senha = ? where idusuario = ?");

                statement.setString(1, usuario.getNome());
                statement.setString(2, usuario.getCpf());
                statement.setString(3, usuario.getFuncao());
                statement.setString(4, usuario.getLogin());
                statement.setString(5, usuario.getSenha());
                statement.setInt(6, usuario.getIdUsuario());

                statement.executeUpdate();
                codigo = usuario.getIdUsuario();
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

    // Obtem o produto pelo código dele
    public static Usuario obterUsuarioPorCodigo(int codigousuario) {
        Usuario usuario = null;
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conexao = DBHelper.getConnect();
            statement = conexao.prepareStatement("select * from usuario where idusuario = ?");
            statement.setInt(1, codigousuario);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                usuario = new Usuario(
                        resultSet.getInt("idusuario"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("funcao"),
                        resultSet.getString("login"),
                        resultSet.getString("senha"));
            }
            return usuario;
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

    public static void excluirUsuario(Usuario Usuario) throws ClassNotFoundException, SQLException {
        Usuario usuarioCadastradoNaBase = obterUsuarioPorCodigo(Usuario.getIdUsuario());
        if (usuarioCadastradoNaBase != null) {
            usuarios.remove(usuarioCadastradoNaBase);
            Connection conexao = null;
            PreparedStatement statement = null;
            try {
                conexao = DBHelper.getConnect();
                statement = conexao.prepareStatement("delete from usuario where idusuario = ?");
                statement.setInt(1, Usuario.getIdUsuario());
                statement.executeUpdate();
            } finally { //Sempre executa esse trecho de código, independente de lançar exceção ou não
                DBHelper.closeConnection(conexao);
                DBHelper.closeStatement(statement);
            }
        }
    }
    
    public static Usuario validarLogin(String login, String senha) {
        Usuario usuario = null;
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conexao = DBHelper.getConnect();
            statement = conexao.prepareStatement("select * from usuario where login = ? and senha = ?");
            statement.setString(1, login);
            statement.setString(2, senha);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                usuario = new Usuario(
                resultSet.getInt("idusuario"),
                resultSet.getString("nome"),
                resultSet.getString("cpf"),
                resultSet.getString("funcao"),
                resultSet.getString("login"),
                resultSet.getString("senha"));
            }
            return usuario;
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

}

package matsunoki.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import matsunoki.bean.Usuario;
import matsunoki.control.usuarios.BotaoAlterarUsuarioActionListener;
import matsunoki.control.usuarios.BotaoCancelarUsuarioActionListener;
import matsunoki.control.usuarios.BotaoExcluirUsuarioActionListener;
import matsunoki.control.usuarios.BotaoNovoUsuarioActionListener;
import matsunoki.control.usuarios.BotaoSalvarUsuarioActionListener;
import matsunoki.control.usuarios.UsuariosTableModel;

import matsunoki.dao.UsuarioDAO;

public class MenuCadastroUsuarioActionListener implements ActionListener {
	

	public MenuCadastroUsuarioActionListener() {
		super();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Principal.janelaPrincipal.setTitle("Floricultura Matsunoki - Cadastro de Usuarios");
	
		JPanel panelCadastroUsuarios = new JPanel(new GridLayout(2, 1));
		JPanel panelListagemUsuarios = new JPanel();
		panelListagemUsuarios.setLayout(null);
		panelCadastroUsuarios.add(panelListagemUsuarios);
		
		// Cria o painel com o formulario de cadastro de usuarios
		JPanel panelFormularioUsuarios = new JPanel();
		panelFormularioUsuarios.setLayout(null);
		panelFormularioUsuarios.setBorder(new LineBorder(Color.BLUE));
		panelFormularioUsuarios.setVisible(false);
		panelCadastroUsuarios.add(panelFormularioUsuarios);
		
		// Label do código
		JLabel codigoUsuariolLabel = new JLabel("Código do Usuario:  ");
		codigoUsuariolLabel.setHorizontalAlignment(JLabel.RIGHT); // Alinha o texto à direita
		codigoUsuariolLabel.setBounds(5, 30, 230, 15); // Define a posição x/y e o tamanho largura/altura, nessa ordem
		panelFormularioUsuarios.add(codigoUsuariolLabel); //adiciona ao painel de formulário de cadastro
//		
//		// Campo de texto
		JTextField codigoUsuarioTextField = new JTextField();
		codigoUsuarioTextField.setBounds(240, 26, 300, 20);// Define a posição x/y e o tamanho largura/altura, nessa ordem
		codigoUsuarioTextField.setEditable(false);// Não pode alterar, o sistema tem que designar um código
                codigoUsuarioTextField.setName("codigoUsuario");
		panelFormularioUsuarios.add(codigoUsuarioTextField);//adiciona ao painel de formulário de cadastro
//		
		JLabel nomeUsuarioLabel = new JLabel("Nome Completo:  ");
		nomeUsuarioLabel.setHorizontalAlignment(JLabel.RIGHT);
		nomeUsuarioLabel.setBounds(5, 60, 230, 15);
		panelFormularioUsuarios.add(nomeUsuarioLabel);

		JTextField nomeCompletoUsuarioTextField = new JTextField();
		nomeCompletoUsuarioTextField.setBounds(240, 56, 300, 20);
                nomeCompletoUsuarioTextField.setName("nomeUsuario");
		panelFormularioUsuarios.add(nomeCompletoUsuarioTextField);

		JLabel cpfLabel = new JLabel("CPF:  ");
		cpfLabel.setHorizontalAlignment(JLabel.RIGHT);
		cpfLabel.setBounds(5, 86, 230, 15);
		panelFormularioUsuarios.add(cpfLabel);

		JTextField cpfTextField = new JTextField();
		cpfTextField.setBounds(240, 82, 300, 20);
                cpfTextField.setName("CPF");
		panelFormularioUsuarios.add(cpfTextField);
                
		JLabel funcaoLabel = new JLabel("Função:  ");
		funcaoLabel.setHorizontalAlignment(JLabel.RIGHT);
		funcaoLabel.setBounds(5, 116, 230, 15);
		panelFormularioUsuarios.add(funcaoLabel);

		JTextField funcaoTextField = new JTextField();
		funcaoTextField.setBounds(240, 112, 300, 20);
                funcaoTextField.setName("funcao");
		panelFormularioUsuarios.add(funcaoTextField);

		JLabel loginLabel = new JLabel("Login:  ");
		loginLabel.setHorizontalAlignment(JLabel.RIGHT);
		loginLabel.setBounds(5, 142, 230, 15);
		panelFormularioUsuarios.add(loginLabel);

		JTextField loginField = new JTextField();
		loginField.setBounds(240, 138, 300, 20);
                loginField.setName("login");
		panelFormularioUsuarios.add(loginField);
                
		// Label do código
		JLabel senhaLabel = new JLabel("Senha:  ");
		senhaLabel.setHorizontalAlignment(JLabel.RIGHT); // Alinha o texto à direita
		senhaLabel.setBounds(580, 30, 200, 15); // Define a posição x/y e o tamanho largura/altura, nessa ordem
		panelFormularioUsuarios.add(senhaLabel); //adiciona ao painel de formulário de cadastro
//		
//		// Campo de texto
		JPasswordField senhaTextField = new JPasswordField();
		senhaTextField.setBounds(790, 26, 300, 20);// Define a posição x/y e o tamanho largura/altura, nessa ordem
                senhaTextField.setName("senha");
                panelFormularioUsuarios.add(senhaTextField);//adiciona ao painel de formulário de cadastro
//		
		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setBounds(590, 230, 100, 20);
		buttonCancelar.addActionListener(new BotaoCancelarUsuarioActionListener(panelFormularioUsuarios));
                panelFormularioUsuarios.add(buttonCancelar);
  
		JButton buttonNovoUsuario = new JButton("Novo Usuario");
		buttonNovoUsuario.addActionListener(new BotaoNovoUsuarioActionListener(panelFormularioUsuarios));
		buttonNovoUsuario.setBounds(1035, 245, 150, 20);
		panelListagemUsuarios.add(buttonNovoUsuario);
//		
//		// Para exibir a tabela de usuarios, é necessário utilizar o painel com scroll
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 8, 1180, 230);
//		
//		// Tabela de listagem de usuarios
		UsuariosTableModel usuarioTableModel = preencherUsuarioTableModel();
		JTable listagemUsuarios = new JTable(usuarioTableModel);
		listagemUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// Define o tipo de seleção para apenas um registro por vez
		scrollPane.setViewportView(listagemUsuarios);
		panelListagemUsuarios.add(scrollPane);
//
//		// Botão Salvar
		JButton botaoSalvar = new JButton("Salvar");
//		// Adiciona um processador de ação (clique) no botão
		botaoSalvar.addActionListener(new BotaoSalvarUsuarioActionListener(usuarioTableModel, panelFormularioUsuarios));
		botaoSalvar.setBounds(460, 230, 100, 20);
		panelFormularioUsuarios.add(botaoSalvar);
                
		JButton buttonAlterarUsuario = new JButton("Alterar Usuario");
		buttonAlterarUsuario.addActionListener(new BotaoAlterarUsuarioActionListener(panelFormularioUsuarios, listagemUsuarios, usuarioTableModel));
		buttonAlterarUsuario.setBounds(835, 245, 150, 20);
		panelListagemUsuarios.add(buttonAlterarUsuario);
//
		JButton buttonExcluirUsuario = new JButton("Excluir Usuario");
		buttonExcluirUsuario.addActionListener(new BotaoExcluirUsuarioActionListener(usuarioTableModel, listagemUsuarios));
                buttonExcluirUsuario.setBounds(635, 245, 150, 20);
		panelListagemUsuarios.add(buttonExcluirUsuario);
//		
		Principal.janelaPrincipal.setContentPane(panelCadastroUsuarios);
		Principal.janelaPrincipal.invalidate();
		Principal.janelaPrincipal.validate();
		Principal.janelaPrincipal.repaint();
	}

//	// Preenche a tabela de listagem de clientes
	private UsuariosTableModel preencherUsuarioTableModel() {
		List<Usuario> usuarios = UsuarioDAO.obterTodosUsuarios();
		return new UsuariosTableModel(usuarios);
	}

}

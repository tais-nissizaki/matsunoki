package matsunoki.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import matsunoki.bean.Cliente;
import matsunoki.control.clientes.BotaoAlterarClienteActionListener;
import matsunoki.control.clientes.BotaoCancelarClienteActionListener;
import matsunoki.control.clientes.BotaoExcluirClienteActionListener;
import matsunoki.control.clientes.BotaoNovoClienteActionListener;
import matsunoki.control.clientes.BotaoSalvarClienteActionListener;
import matsunoki.control.clientes.ClientesTableModel;

import matsunoki.dao.ClienteDAO;

public class MenuCadastroClienteActionListener implements ActionListener {
	

	public MenuCadastroClienteActionListener() {
		super();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Principal.janelaPrincipal.setTitle("Floricultura Matsu No Ki - Cadastro de Clientes");
	
		JPanel panelCadastroClientes = new JPanel(new GridLayout(2, 1));
		JPanel panelListagemClientes = new JPanel();
		panelListagemClientes.setLayout(null);
		panelCadastroClientes.add(panelListagemClientes);
		
		// Cria o painal com o formulario de cadastro de clientes
		JPanel panelFormularioClientes = new JPanel();
		panelFormularioClientes.setLayout(null);
		panelFormularioClientes.setBorder(new LineBorder(Color.BLUE));
		panelFormularioClientes.setVisible(false);
		panelCadastroClientes.add(panelFormularioClientes);
		
		// Label do código
		JLabel codigoClientelLabel = new JLabel("Código do Cliente:  ");
		codigoClientelLabel.setHorizontalAlignment(JLabel.RIGHT); // Alinha o texto à direita
		codigoClientelLabel.setBounds(5, 30, 230, 15); // Define a posição x/y e o tamanho largura/altura, nessa ordem
		panelFormularioClientes.add(codigoClientelLabel); //adiciona ao painel de formulário de cadastro
//		
//		// Campo de texto
		JTextField codigoClienteTextField = new JTextField();
		codigoClienteTextField.setBounds(240, 26, 300, 20);// Define a posição x/y e o tamanho largura/altura, nessa ordem
		codigoClienteTextField.setEditable(false);// Não pode alterar, o sistema tem que designar um código
                codigoClienteTextField.setName("codigoCliente");
		panelFormularioClientes.add(codigoClienteTextField);//adiciona ao painel de formulário de cadastro
//		
		JLabel nomeClienteLabel = new JLabel("Nome Completo:  ");
		nomeClienteLabel.setHorizontalAlignment(JLabel.RIGHT);
		nomeClienteLabel.setBounds(5, 60, 230, 15);
		panelFormularioClientes.add(nomeClienteLabel);

		JTextField nomeCompletoClienteTextField = new JTextField();
		nomeCompletoClienteTextField.setBounds(240, 56, 300, 20);
                nomeCompletoClienteTextField.setName("nomeCliente");
		panelFormularioClientes.add(nomeCompletoClienteTextField);

		JLabel dataNascimentoLabel = new JLabel("Data de Nascimento:  ");
		dataNascimentoLabel.setHorizontalAlignment(JLabel.RIGHT);
		dataNascimentoLabel.setBounds(5, 86, 230, 15);
		panelFormularioClientes.add(dataNascimentoLabel);

		JTextField dataNascimentoTextField = new JTextField();
		dataNascimentoTextField.setBounds(240, 82, 300, 20);
                dataNascimentoTextField.setName("dataNascimento");
		panelFormularioClientes.add(dataNascimentoTextField);
                
		JLabel cpfLabel = new JLabel("CPF:  ");
		cpfLabel.setHorizontalAlignment(JLabel.RIGHT);
		cpfLabel.setBounds(5, 116, 230, 15);
		panelFormularioClientes.add(cpfLabel);

		JTextField cpfTextField = new JTextField();
		cpfTextField.setBounds(240, 112, 300, 20);
                cpfTextField.setName("cpf");
		panelFormularioClientes.add(cpfTextField);

		JLabel emailLabel = new JLabel("E-Mail:  ");
		emailLabel.setHorizontalAlignment(JLabel.RIGHT);
		emailLabel.setBounds(5, 142, 230, 15);
		panelFormularioClientes.add(emailLabel);

		JTextField emailField = new JTextField();
		emailField.setBounds(240, 138, 300, 20);
                emailField.setName("email");
		panelFormularioClientes.add(emailField);
                
		// Label do código
		JLabel logradouroLabel = new JLabel("Logradouro:  ");
		logradouroLabel.setHorizontalAlignment(JLabel.RIGHT); // Alinha o texto à direita
		logradouroLabel.setBounds(580, 30, 200, 15); // Define a posição x/y e o tamanho largura/altura, nessa ordem
		panelFormularioClientes.add(logradouroLabel); //adiciona ao painel de formulário de cadastro
//		
//		// Campo de texto
		JTextField logradouroTextField = new JTextField();
		logradouroTextField.setBounds(790, 26, 300, 20);// Define a posição x/y e o tamanho largura/altura, nessa ordem
                logradouroTextField.setName("logradouro");
                panelFormularioClientes.add(logradouroTextField);//adiciona ao painel de formulário de cadastro
//		
                

		JLabel numeroLogradouroLabel = new JLabel("Número:  ");
		numeroLogradouroLabel.setHorizontalAlignment(JLabel.RIGHT); // Alinha o texto à direita
		numeroLogradouroLabel.setBounds(580, 60, 200, 15); // Define a posição x/y e o tamanho largura/altura, nessa ordem
		panelFormularioClientes.add(numeroLogradouroLabel); //adiciona ao painel de formulário de cadastro
//		
//		// Campo de texto
		JTextField numeroLogradouroTextField = new JTextField();
		numeroLogradouroTextField.setBounds(790, 56, 300, 20);// Define a posição x/y e o tamanho largura/altura, nessa ordem
		numeroLogradouroTextField.setName("numeroLogradouro");
                panelFormularioClientes.add(numeroLogradouroTextField);//adiciona ao painel de formulário de cadastro
                
		JLabel complementoLabel = new JLabel("Complemento:  ");
		complementoLabel.setHorizontalAlignment(JLabel.RIGHT); // Alinha o texto à direita
		complementoLabel.setBounds(580, 86, 200, 15); // Define a posição x/y e o tamanho largura/altura, nessa ordem
		panelFormularioClientes.add(complementoLabel); //adiciona ao painel de formulário de cadastro
//		
//		// Campo de texto
		JTextField complementoTextField = new JTextField();
		complementoTextField.setBounds(790, 82, 300, 20);// Define a posição x/y e o tamanho largura/altura, nessa ordem
		complementoTextField.setName("complemento");
                panelFormularioClientes.add(complementoTextField);//adiciona ao painel de formulário de cadastro
                
		JLabel bairroLabel = new JLabel("Bairro:  ");
		bairroLabel.setHorizontalAlignment(JLabel.RIGHT); // Alinha o texto à direita
		bairroLabel.setBounds(580, 116, 200, 15); // Define a posição x/y e o tamanho largura/altura, nessa ordem
		panelFormularioClientes.add(bairroLabel); //adiciona ao painel de formulário de cadastro
//		
//		// Campo de texto
		JTextField bairroTextField = new JTextField();
		bairroTextField.setBounds(790, 112, 300, 20);// Define a posição x/y e o tamanho largura/altura, nessa ordem
		bairroTextField.setName("bairro");
                panelFormularioClientes.add(bairroTextField);//adiciona ao painel de formulário de cadastro
                
		JLabel cidadeLabel = new JLabel("Cidade:  ");
		cidadeLabel.setHorizontalAlignment(JLabel.RIGHT); // Alinha o texto à direita
		cidadeLabel.setBounds(580, 142, 200, 15); // Define a posição x/y e o tamanho largura/altura, nessa ordem
		panelFormularioClientes.add(cidadeLabel); //adiciona ao painel de formulário de cadastro
//		
//		// Campo de texto
		JTextField cidadeTextField = new JTextField();
		cidadeTextField.setBounds(790, 138, 300, 20);// Define a posição x/y e o tamanho largura/altura, nessa ordem
		cidadeTextField.setName("cidade");
                panelFormularioClientes.add(cidadeTextField);//adiciona ao painel de formulário de cadastro
                
		JLabel estadoLabel = new JLabel("Estado:  ");
		estadoLabel.setHorizontalAlignment(JLabel.RIGHT); // Alinha o texto à direita
		estadoLabel.setBounds(580, 168, 200, 15); // Define a posição x/y e o tamanho largura/altura, nessa ordem
		panelFormularioClientes.add(estadoLabel); //adiciona ao painel de formulário de cadastro
//		
//		// Campo de texto
                JComboBox comboEstado = new JComboBox(new String[]{"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"});
                comboEstado.setBounds(790, 164, 300, 20);
                comboEstado.setSelectedIndex(-1);
                comboEstado.setName("estado");
                panelFormularioClientes.add(comboEstado);
//		JTextField estadoTextField = new JTextField();
//		estadoTextField.setBounds(790, 164, 300, 20);// Define a posição x/y e o tamanho largura/altura, nessa ordem
//		estadoTextField.setName("estado");
//                panelFormularioClientes.add(estadoTextField);//adiciona ao painel de formulário de cadastro
                
		JLabel cepLabel = new JLabel("CEP:  ");
		cepLabel.setHorizontalAlignment(JLabel.RIGHT); // Alinha o texto à direita
		cepLabel.setBounds(580, 194, 200, 15); // Define a posição x/y e o tamanho largura/altura, nessa ordem
		panelFormularioClientes.add(cepLabel); //adiciona ao painel de formulário de cadastro
//		
//		// Campo de texto
		JTextField cepTextField = new JTextField();
		cepTextField.setBounds(790, 190, 300, 20);// Define a posição x/y e o tamanho largura/altura, nessa ordem
		cepTextField.setName("cep");
                panelFormularioClientes.add(cepTextField);//adiciona ao painel de formulário de cadastro
//		
//
		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(new BotaoCancelarClienteActionListener(panelFormularioClientes));
		buttonCancelar.setBounds(590, 230, 100, 20);
		panelFormularioClientes.add(buttonCancelar);
//		
//
		JButton buttonNovoCliente = new JButton("Novo Cliente");
		buttonNovoCliente.addActionListener(new BotaoNovoClienteActionListener(panelFormularioClientes));
		buttonNovoCliente.setBounds(1035, 245, 150, 20);
		panelListagemClientes.add(buttonNovoCliente);
//		
//		// Para exibir a tabela de clientes, é necessário utilizar o painel com scroll
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 8, 1180, 230);
//		
//		// Tabela de listagem de produtos
		ClientesTableModel clienteTableModel = preencherClienteTableModel();
		JTable listagemClientes = new JTable(clienteTableModel);
		listagemClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// Define o tipo de seleção para apenas um registro por vez
		scrollPane.setViewportView(listagemClientes);
		panelListagemClientes.add(scrollPane);
//
//		// Botão Salvar
		JButton botaoSalvar = new JButton("Salvar");
//		// Adiciona um processador de ação (clique) no botão
		botaoSalvar.addActionListener(new BotaoSalvarClienteActionListener(clienteTableModel, panelFormularioClientes));
		botaoSalvar.setBounds(460, 230, 100, 20);
		panelFormularioClientes.add(botaoSalvar);
                
		JButton buttonAlterarCliente = new JButton("Alterar Cliente");
		buttonAlterarCliente.addActionListener(new BotaoAlterarClienteActionListener(panelFormularioClientes, listagemClientes, clienteTableModel));
		buttonAlterarCliente.setBounds(835, 245, 150, 20);
		panelListagemClientes.add(buttonAlterarCliente);
//
		JButton buttonExcluirCliente = new JButton("Excluir Cliente");
		buttonExcluirCliente.addActionListener(new BotaoExcluirClienteActionListener(clienteTableModel, listagemClientes));
                buttonExcluirCliente.setBounds(635, 245, 150, 20);
		panelListagemClientes.add(buttonExcluirCliente);
//		
		Principal.janelaPrincipal.setContentPane(panelCadastroClientes);
		Principal.janelaPrincipal.invalidate();
		Principal.janelaPrincipal.validate();
		Principal.janelaPrincipal.repaint();
	}

//	// Preenche a tabela de listagem de clientes
	private ClientesTableModel preencherClienteTableModel() {
		List<Cliente> clientes = ClienteDAO.obterTodosClientes();
		return new ClientesTableModel(clientes);
	}

}

package matsunoki.control.clientes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import matsunoki.view.Principal;
import matsunoki.bean.Cliente;
import matsunoki.bean.Endereco;
import matsunoki.dao.ClienteDAO;
import sun.applet.Main;

public class BotaoSalvarClienteActionListener implements ActionListener {

    private ClientesTableModel clienteTableModel;
    private JPanel panelFormularioClientes;

    public BotaoSalvarClienteActionListener(ClientesTableModel clienteTableModel, JPanel panelFormularioClientes) {
        this.clienteTableModel = clienteTableModel;
        this.panelFormularioClientes = panelFormularioClientes;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Cliente cliente = new Cliente();

        SelecionadorJTextField selecionadorJTextField = new SelecionadorJTextField(panelFormularioClientes);

        boolean dadosValidos = true;
        JTextField codigoClienteTextField = selecionadorJTextField.obterJTextField("codigoCliente");
        if (codigoClienteTextField.getText() != null && !codigoClienteTextField.getText().trim().equals("")) {
            try {
                cliente.setCodigo(Integer.parseInt(codigoClienteTextField.getText()));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Código do cliente inválido");
                dadosValidos = false;
            }
        }
        JTextField nomeClienteTextField = selecionadorJTextField.obterJTextField("nomeCliente");
        if (nomeClienteTextField.getText() != null && !nomeClienteTextField.getText().trim().equals("")) {
            cliente.setNome(nomeClienteTextField.getText());
        } else {
            JOptionPane.showMessageDialog(Principal.janelaPrincipal, "O Nome do cliente é obrigatório");
            dadosValidos = false;
        }

        JTextField dataNascimentoTextField = selecionadorJTextField.obterJTextField("dataNascimento");
        if (dataNascimentoTextField.getText() != null && !dataNascimentoTextField.getText().trim().equals("")) {
            cliente.setDataNascimento(dataNascimentoTextField.getText().trim());
        }

        JTextField cpfTextField = selecionadorJTextField.obterJTextField("cpf");
        if (cpfTextField.getText() != null && !cpfTextField.getText().trim().equals("")) {
            if(isCPFValido(cpfTextField.getText().trim())) {
                cliente.setCpf(cpfTextField.getText().trim());
            } else {
                dadosValidos = false;
                JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Número de CPF inválido.");
            }
        }

        JTextField emailTextField = selecionadorJTextField.obterJTextField("email");
        if (emailTextField.getText() != null && !emailTextField.getText().trim().equals("")) {
            cliente.setEmail(emailTextField.getText().trim());
        }

        //Endereço
        Endereco enderecoCliente = new Endereco();

        JTextField logradouroTextField = selecionadorJTextField.obterJTextField("logradouro");
        if (logradouroTextField.getText() != null && !logradouroTextField.getText().trim().equals("")) {
            enderecoCliente.setNomeLogradouro(logradouroTextField.getText().trim());
        }

        //numeroLogradouro
        JTextField numeroLogradouroTextField = selecionadorJTextField.obterJTextField("numeroLogradouro");
        if (numeroLogradouroTextField.getText() != null && !numeroLogradouroTextField.getText().trim().equals("")) {
            try {
                enderecoCliente.setNumeroLogradouro(Integer.parseInt(numeroLogradouroTextField.getText()));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Número do endereço é inválido");
                dadosValidos = false;
            }
        }

        //complemento        
        JTextField complementoTextField = selecionadorJTextField.obterJTextField("complemento");
        if (complementoTextField.getText() != null && !complementoTextField.getText().trim().equals("")) {
            enderecoCliente.setComplemento(complementoTextField.getText().trim());
        }

        //bairro        
        JTextField bairroTextField = selecionadorJTextField.obterJTextField("bairro");
        if (bairroTextField.getText() != null && !bairroTextField.getText().trim().equals("")) {
            enderecoCliente.setBairro(bairroTextField.getText().trim());
        }

        //cidade        
        JTextField cidadeTextField = selecionadorJTextField.obterJTextField("cidade");
        if (cidadeTextField.getText() != null && !cidadeTextField.getText().trim().equals("")) {
            enderecoCliente.setCidade(cidadeTextField.getText().trim());
        }

        //estado        
        JComboBox estadoTextField = selecionadorJTextField.obterJComboBox("estado");
        if (estadoTextField.getSelectedItem() != null && !((String)estadoTextField.getSelectedItem()).trim().equals("")) {
            enderecoCliente.setEstado(((String)estadoTextField.getSelectedItem()).trim());
        }

        //cep        
        JTextField cepTextField = selecionadorJTextField.obterJTextField("cep");
        if (cepTextField.getText() != null && !cepTextField.getText().trim().equals("")) {
            enderecoCliente.setCep(cepTextField.getText().trim());
        }

        cliente.setEndereco(enderecoCliente);

        if (dadosValidos) {
            ClienteDAO.salvarCliente(cliente);
            
            selecionadorJTextField.limparTodosJTextField();

            this.panelFormularioClientes.setVisible(false);
            clienteTableModel.fireTableDataChanged();
            Principal.janelaPrincipal.invalidate();
            Principal.janelaPrincipal.validate();
            Principal.janelaPrincipal.repaint();
            JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Cliente Cadastrado com sucesso");
        }
    }
    
    private boolean isCPFValido(String cpf) {
        
        final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    
        cpf = cpf.trim().replace(".", "").replace("-", "");
        if ((cpf==null) || (cpf.length()!=11)) return false;

        for (int j = 0; j < 10; j++)
            if (padLeft(Integer.toString(j), Character.forDigit(j, 10)).equals(cpf))
                return false;

        Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }
    

    private int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    private String padLeft(String text, char character) {
        return String.format("%11s", text).replace(' ', character);
    }
}

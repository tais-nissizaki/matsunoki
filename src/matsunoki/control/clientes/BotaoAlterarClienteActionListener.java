package matsunoki.control.clientes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import matsunoki.view.Principal;

import matsunoki.bean.Cliente;
import matsunoki.bean.Endereco;

public class BotaoAlterarClienteActionListener implements ActionListener {

    private JPanel panelFormularioClientes;
    private JTable tabela;
    private ClientesTableModel clienteTableModel;

    public BotaoAlterarClienteActionListener(JPanel panelFormularioClientes, JTable tabela, ClientesTableModel clienteTableModel) {
        this.panelFormularioClientes = panelFormularioClientes;
        this.tabela = tabela;
        this.clienteTableModel = clienteTableModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente foi selecionado");
        } else {
            SelecionadorJTextField selecionadorJTextField = new SelecionadorJTextField(panelFormularioClientes);
            Cliente cliente = ((ClientesTableModel) tabela.getModel()).getClientes().get(selectedRow);
            selecionadorJTextField.obterJTextField("codigoCliente").setText(String.valueOf(cliente.getCodigo()));
            selecionadorJTextField.obterJTextField("nomeCliente").setText(cliente.getNome());
            selecionadorJTextField.obterJTextField("dataNascimento").setText(cliente.getDataNascimento());
            selecionadorJTextField.obterJTextField("cpf").setText(cliente.getCpf());
            selecionadorJTextField.obterJTextField("email").setText(cliente.getEmail());
            if (cliente.getEndereco() != null) {
                
                selecionadorJTextField.obterJTextField("logradouro").setText(cliente.getEndereco().getNomeLogradouro());
                if (cliente.getEndereco().getNumeroLogradouro() > 0) {
                    selecionadorJTextField.obterJTextField("numeroLogradouro").setText(String.valueOf(cliente.getEndereco().getNumeroLogradouro()));
                }
                selecionadorJTextField.obterJTextField("complemento").setText(cliente.getEndereco().getComplemento());
                selecionadorJTextField.obterJTextField("bairro").setText(cliente.getEndereco().getBairro());
                selecionadorJTextField.obterJTextField("cidade").setText(cliente.getEndereco().getCidade());
                selecionadorJTextField.obterJComboBox("estado").setSelectedItem(cliente.getEndereco().getEstado());
                selecionadorJTextField.obterJTextField("cep").setText(cliente.getEndereco().getCep());
            }
            panelFormularioClientes.setVisible(true);
            clienteTableModel.fireTableDataChanged();
            Principal.janelaPrincipal.invalidate();
            Principal.janelaPrincipal.validate();
            Principal.janelaPrincipal.repaint();
        }
    }

}

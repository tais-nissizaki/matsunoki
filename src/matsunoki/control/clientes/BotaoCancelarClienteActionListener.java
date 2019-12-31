package matsunoki.control.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import matsunoki.view.Principal;

public class BotaoCancelarClienteActionListener implements ActionListener {

    private JPanel panelFormularioClientes;

    public BotaoCancelarClienteActionListener(JPanel panelFormularioClientes) {
        this.panelFormularioClientes = panelFormularioClientes;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SelecionadorJTextField selecionadorJTextField = new SelecionadorJTextField(panelFormularioClientes);
        selecionadorJTextField.limparTodosJTextField();
        
        this.panelFormularioClientes.setVisible(false);
        Principal.janelaPrincipal.invalidate();
        Principal.janelaPrincipal.validate();
        Principal.janelaPrincipal.repaint();
    }

}

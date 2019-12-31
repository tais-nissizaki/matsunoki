package matsunoki.control.usuarios;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class BotaoNovoUsuarioActionListener implements ActionListener {

    private JPanel panelFormularioClientes;

    public BotaoNovoUsuarioActionListener(JPanel panelFormularioClientes) {
        this.panelFormularioClientes = panelFormularioClientes;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.panelFormularioClientes.setVisible(true);
        SelecionadorJTextField selecionadorJTextField = new SelecionadorJTextField(panelFormularioClientes);
        selecionadorJTextField.limparTodosJTextField();

    }

}

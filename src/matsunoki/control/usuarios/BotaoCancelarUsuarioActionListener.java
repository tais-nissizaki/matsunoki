package matsunoki.control.usuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import matsunoki.view.Principal;

public class BotaoCancelarUsuarioActionListener implements ActionListener {

    private JPanel panelFormularioUsuarios;

    public BotaoCancelarUsuarioActionListener(JPanel panelFormularioUsuarios) {
        this.panelFormularioUsuarios = panelFormularioUsuarios;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SelecionadorJTextField selecionadorJTextField = new SelecionadorJTextField(panelFormularioUsuarios);
        selecionadorJTextField.limparTodosJTextField();
        
        this.panelFormularioUsuarios.setVisible(false);
        Principal.janelaPrincipal.invalidate();
        Principal.janelaPrincipal.validate();
        Principal.janelaPrincipal.repaint();
    }

}

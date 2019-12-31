package matsunoki.control.usuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import matsunoki.bean.Endereco;
import matsunoki.bean.Usuario;
import matsunoki.dao.ClienteDAO;
import matsunoki.dao.UsuarioDAO;

import matsunoki.view.Principal;

public class BotaoSalvarUsuarioActionListener implements ActionListener {

    private UsuariosTableModel usuariosTableModel;
    private JPanel panelFormularioUsuarios;

    public BotaoSalvarUsuarioActionListener(UsuariosTableModel usuariosTableModel, JPanel panelFormularioUsuarios) {
        this.usuariosTableModel = usuariosTableModel;
        this.panelFormularioUsuarios = panelFormularioUsuarios;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Usuario usuario = new Usuario();

        SelecionadorJTextField selecionadorJTextField = new SelecionadorJTextField(panelFormularioUsuarios);

        boolean dadosValidos = true;
        JTextField codigoUsuarioTextField = selecionadorJTextField.obterJTextField("codigoUsuario");
        if (codigoUsuarioTextField.getText() != null && !codigoUsuarioTextField.getText().trim().equals("")) {
            try {
                usuario.setIdUsuario(Integer.parseInt(codigoUsuarioTextField.getText()));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Código do usuário inválido");
                dadosValidos = false;
            }
        }
        JTextField nomeUsuarioTextField = selecionadorJTextField.obterJTextField("nomeUsuario");
        if (nomeUsuarioTextField.getText() != null && !nomeUsuarioTextField.getText().trim().equals("")) {
            usuario.setNome(nomeUsuarioTextField.getText());
        } else {
            JOptionPane.showMessageDialog(Principal.janelaPrincipal, "O Nome do usuario é obrigatório");
            dadosValidos = false;
        }

        JTextField cpfTextField = selecionadorJTextField.obterJTextField("cpf");
        if (cpfTextField.getText() != null && !cpfTextField.getText().trim().equals("")) {
            if(isCPFValido(cpfTextField.getText().trim())) {
                usuario.setCpf(cpfTextField.getText().trim());
            } else {
                dadosValidos = false;
                JOptionPane.showMessageDialog(Principal.janelaPrincipal, "Número de CPF inválido.");
            }
        } else {
            JOptionPane.showMessageDialog(Principal.janelaPrincipal, "O número do CPF é obrigatório");
            dadosValidos = false;
        }

        JTextField funcaoTextField = selecionadorJTextField.obterJTextField("funcao");
        if (funcaoTextField.getText() != null && !funcaoTextField.getText().trim().equals("")) {
            usuario.setFuncao(funcaoTextField.getText().trim());
        } else {
            JOptionPane.showMessageDialog(Principal.janelaPrincipal, "A função é obrigatória");
            dadosValidos = false;
        }

        JTextField loginTextField = selecionadorJTextField.obterJTextField("login");
        if (loginTextField.getText() != null && !loginTextField.getText().trim().equals("")) {
            usuario.setLogin(loginTextField.getText().trim());
        } else {
            JOptionPane.showMessageDialog(Principal.janelaPrincipal, "O login é obrigatório");
            dadosValidos = false;
        }

        JTextField senhaTextField = selecionadorJTextField.obterJTextField("senha");
        if (senhaTextField.getText() != null && !senhaTextField.getText().trim().equals("")) {
            usuario.setSenha(senhaTextField.getText().trim());
        } else {
            JOptionPane.showMessageDialog(Principal.janelaPrincipal, "A senha é obrigatória");
            dadosValidos = false;
        }

        if (dadosValidos) {
            UsuarioDAO.salvarUsuario(usuario);
            
            selecionadorJTextField.limparTodosJTextField();

            this.panelFormularioUsuarios.setVisible(false);
            usuariosTableModel.fireTableDataChanged();
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

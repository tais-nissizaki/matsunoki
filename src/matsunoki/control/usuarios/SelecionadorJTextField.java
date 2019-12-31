/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matsunoki.control.usuarios;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Tais
 */
public class SelecionadorJTextField {

    private JPanel panelFormulario;

    public SelecionadorJTextField(JPanel panelFormularioClientes) {
        this.panelFormulario = panelFormularioClientes;
    }

    public JComboBox obterJComboBox(String nomeComboBox) {
        Component[] components = panelFormulario.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JComboBox) {
                JComboBox ComboBoxTextField = (JComboBox) components[i];
                if (ComboBoxTextField.getName().equalsIgnoreCase(nomeComboBox)) {
                    return ComboBoxTextField;
                }
            }
        }
        return null;
    }

    public JTextField obterJTextField(String nomeTextField) {
        Component[] components = panelFormulario.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JTextField) {
                JTextField jTextField = (JTextField) components[i];
                if (jTextField.getName().equalsIgnoreCase(nomeTextField)) {
                    return jTextField;
                }
            }
        }
        return null;
    }
    
    public void limparTodosJTextField() {
        Component[] components = this.panelFormulario.getComponents();
         //percorre os componentes
        for(int i=0; i< components.length; i++) {

            // Verifica se o componente é do tipo JTextField (campo de textp)
            if(components[i] instanceof JTextField) {
                // Faz um CAST explícito para o tipo JTextiField
                JTextField textField = (JTextField)components[i];

                //Altera o conteúdo para "" (vazio)
                textField.setText("");
            } else if(components[i] instanceof JComboBox) {
                JComboBox combobox = (JComboBox)components[i];
                combobox.setSelectedIndex(-1);
                
            }
        }
    }

}

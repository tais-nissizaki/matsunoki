package matsunoki.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import matsunoki.dao.UsuarioDAO;

public class Login extends JFrame{
    private JTextField tfLogin;
    private JLabel lbSenha;
    private JLabel lbLogin;
    private JButton btLogar;
    private JButton btCancelar;
    private JPasswordField pfSenha;
    private static Login frame;
    
   public Login(){
    inicializarComponentes();
    definirEventos(); 
   }
    private void inicializarComponentes() {
        setTitle("Login - Mogi das Cruzes");
        setBounds(0,0,250,200);
        setLayout(null);
        tfLogin = new JTextField(5);
        pfSenha = new JPasswordField(5);
        lbSenha = new JLabel("Senha:");
        lbLogin = new JLabel("Login:");
        btLogar = new JButton("Logar");
        btCancelar = new JButton("Cancelar");
        tfLogin.setBounds(100, 30, 120, 25);
        lbLogin.setBounds(30, 30, 80, 25);
        lbSenha.setBounds(30, 75, 80, 25);
        pfSenha.setBounds(100, 75, 120, 25);
        btLogar.setBounds(20, 120, 100, 25);
        btCancelar.setBounds(125, 120, 100, 25);
        add(tfLogin); add(lbSenha);
        add(lbLogin); add(btLogar);
        add(btCancelar); add(pfSenha);
    }
    private void definirEventos() {
        btLogar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                String senha = String.valueOf(pfSenha.getPassword());
                
                if(UsuarioDAO.validarLogin(tfLogin.getText(), senha) != null){
                    frame.setVisible(false);
                    Principal.abrir();
                }else{
                    JOptionPane.showMessageDialog(null,"Login ou Senha incorretas!");
                }
            } 
        });
        btCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            } 
        }); 
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
        public void run(){
            frame = new Login();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
            //Centralização do frame
            frame.setLocation((tela.width - frame.getSize().width) / 2, (tela.height - frame.getSize().height) / 2);
            frame.setVisible(true);
            } 
       }); 
    } 
}
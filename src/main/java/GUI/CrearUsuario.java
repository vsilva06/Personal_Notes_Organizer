package GUI;

import Estructura.Login;
import Estructura.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearUsuario extends JFrame implements ActionListener {
    private JPanel crearUsuario;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton aceptar;

    public CrearUsuario(){
        ImageIcon logo = new ImageIcon("src/main/resources/Logo/Logo.png");
        this.setTitle("PNO");
        this.add(crearUsuario);
        setSize(400, 500);
        setLocationRelativeTo(null);
        this.setIconImage(logo.getImage());
        setResizable(false);
        pack();
        setVisible(true);
        añadirButton();
    }

    private void añadirButton() {
        aceptar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==aceptar){
            Usuario usuario = new Usuario();
            usuario.crearCuenta(textField1.getText(), passwordField1.getText());
            JOptionPane.showMessageDialog(crearUsuario,"jajaja creao");
        }
    }
}

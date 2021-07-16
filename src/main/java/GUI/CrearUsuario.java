package GUI;

import Estructura.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearUsuario extends JFrame implements ActionListener {
    private JPanel crearUsuario;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton aceptar;
    private User user;

    public CrearUsuario(){
        ImageIcon logo = new ImageIcon("src/main/resources/Logo/Logo.png");
        this.setTitle("PNO");
        this.add(crearUsuario);
        setSize(400, 500);
        setLocationRelativeTo(null);
        this.setIconImage(logo.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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

    }
}

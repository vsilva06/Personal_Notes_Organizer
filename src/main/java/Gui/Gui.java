package Gui;

import javax.swing.*;

public class Gui extends JFrame {

    public Gui() {
        setTitle("Personal Notes Organizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(600, 600);
        setLocationRelativeTo(null);
        ImageIcon image = new ImageIcon("src/main/resources/Logo/Logo.png");
        setIconImage(image.getImage());
        iniciarComponentes();
        setVisible(true);


    }

    private void iniciarComponentes() {
        JPanel panel = new JPanel();
        this.getContentPane().add(panel);
        new TextEditor();


    }


}

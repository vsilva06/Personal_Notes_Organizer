package GUI;

import Complementos.FileSystemModel;
import Datos.GestorArchivos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class Principal extends JFrame implements ActionListener {
    private JPanel principal;
    private JTree tree1;
    private JTextArea text;
    private JButton abrirButton;
    private JMenuBar menuBar1;
    private JMenu opcionesMenu;
    private JMenuItem nuevaCarpeta, eliminarCarpeta, nuevaNota, eliminarNota;
    private TextEditor textEditor;
    private GestorArchivos gestorArchivos;


    public Principal() {
        ImageIcon logo = new ImageIcon("src/main/resources/Logo/Logo.png");
        $$$setupUI$$$();
        this.setTitle("PNO");
        this.add(principal);
        setSize(400, 500);
        agregarMenu();
        setLocationRelativeTo(null);
        this.setIconImage(logo.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // setResizable(false);
        pack();
        setVisible(true);


    }

    private void agregarMenu() {
        menuBar1 = new JMenuBar();
        setJMenuBar(menuBar1);

        opcionesMenu = new JMenu("Opciones");
        menuBar1.add(opcionesMenu);

        nuevaCarpeta = new JMenuItem("Nueva carpeta");
        nuevaCarpeta.addActionListener(this);
        opcionesMenu.add(nuevaCarpeta);

        eliminarCarpeta = new JMenuItem("Eliminar carpeta");
        eliminarCarpeta.addActionListener(this);
        opcionesMenu.add(eliminarCarpeta);

        nuevaNota = new JMenuItem("Nueva nota");
        nuevaNota.addActionListener(this);
        opcionesMenu.add(nuevaNota);

        eliminarNota = new JMenuItem("Eliminar nota");
        eliminarNota.addActionListener(this);
        opcionesMenu.add(eliminarNota);

        abrirButton.addActionListener(this);

        tree1.setModel(new FileSystemModel(new File("src/main/resources/Personal Notes Organizer")));

        gestorArchivos = new GestorArchivos();
        textEditor = new TextEditor();


    }


    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == nuevaNota) {

            if (String.valueOf(tree1.getSelectionPath().getLastPathComponent()).equals("src\\main\\resources\\Personal Notes Organizer")) {
                gestorArchivos.crearArchivo(String.valueOf(tree1.getSelectionPath().getLastPathComponent() + "\\" + text.getText()));
                this.tree1.updateUI();
                this.text.setText("");
            } else {
                try {
                    File file = new File(tree1.getSelectionPath().getParentPath().getLastPathComponent() + "\\" + tree1.getSelectionPath().getLastPathComponent());
                    if (!file.isFile()) {
                        gestorArchivos.crearArchivo(file.getPath() + "\\" + text.getText());
                        this.tree1.updateUI();
                        this.text.setText("");
                    }


                } catch (NullPointerException e1) {

                }
            }


        }
        if (e.getSource() == abrirButton) {

            try {
                File file = new File(tree1.getSelectionPath().getParentPath().getLastPathComponent() + "\\" + tree1.getSelectionPath().getLastPathComponent());

                if (file.isFile()) {

                    textEditor.abrirArchivo(file);

                }
            } catch (NullPointerException e1) {

            }
        }
        if (e.getSource() == nuevaCarpeta) {

            if (String.valueOf(tree1.getSelectionPath().getLastPathComponent()).equals("src\\main\\resources\\Personal Notes Organizer")) {
                gestorArchivos.crearDirectorio(String.valueOf(tree1.getSelectionPath().getLastPathComponent() + "\\" + text.getText()));
                this.tree1.updateUI();
                this.text.setText("");
            } else {
                try {
                    File file = new File(tree1.getSelectionPath().getParentPath().getLastPathComponent() + "\\" + tree1.getSelectionPath().getLastPathComponent());

                    if (!file.isFile()) {
                        gestorArchivos.crearDirectorio(file.getPath() + "\\" + text.getText());
                        this.tree1.updateUI();
                        this.text.setText("");
                    }
                } catch (NullPointerException e1) {
                    this.text.setText("");
                }

            }
        }

        if (e.getSource() == eliminarNota) {
            try {
                File file = new File(tree1.getSelectionPath().getParentPath().getLastPathComponent() + "\\" + tree1.getSelectionPath().getLastPathComponent());

                if (file.isFile()) {
                    gestorArchivos.eliminarArchivos(file.getPath());
                    this.tree1.updateUI();

                }
            } catch (NullPointerException e1) {
                System.err.println("No se puede abrir un directorio");
            }

        }

    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        principal = new JPanel();
        principal.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 5, new Insets(0, 0, 0, 0), -1, -1));
        principal.setOpaque(true);
        principal.setPreferredSize(new Dimension(400, 500));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        principal.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        principal.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tree1 = new JTree();
        principal.add(tree1, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 2, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(100, 10), null, 0, false));
        text = new JTextArea();
        principal.add(text, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(150, 25), null, 0, false));
        abrirButton = new JButton();
        abrirButton.setText("Abrir");
        principal.add(abrirButton, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        principal.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        principal.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return principal;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

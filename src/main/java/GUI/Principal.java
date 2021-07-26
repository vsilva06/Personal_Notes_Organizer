package GUI;

import Complementos.FileSystemModel;
import Datos.GestorArchivos;
import Datos.GestorVentanas;
import Estructura.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;


public class Principal extends JFrame implements ActionListener, MouseListener {
    private JPanel principal;
    private JTree tree1;
    private JMenuBar menuBar1;
    private JMenu opcionesMenu, opcionesNotas, opcionesCarpetas;
    private JMenuItem nuevaCarpeta, eliminarCarpeta, nuevaNota, eliminarNota, lCompra, cerrarSesion, eliminarUsuario;
    private GestorArchivos gestorArchivos;
    private String ruta;
    private GestorVentanas gestorVentanas;
    private String usuario;


    public Principal(String usuario) {
        this.usuario = usuario.strip();
        this.ruta = "src/main/resources/Usuarios/" + usuario.strip();
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

        gestorVentanas = new GestorVentanas();


    }

    private void agregarMenu() {
        menuBar1 = new JMenuBar();
        setJMenuBar(menuBar1);

        opcionesMenu = new JMenu("Opciones");
        menuBar1.add(opcionesMenu);

        opcionesNotas = new JMenu("Nota");
        menuBar1.add(opcionesNotas);

        opcionesCarpetas = new JMenu("Carpeta");
        menuBar1.add(opcionesCarpetas);

        nuevaCarpeta = new JMenuItem("Nueva carpeta");
        nuevaCarpeta.addActionListener(this);
        opcionesCarpetas.add(nuevaCarpeta);

        eliminarCarpeta = new JMenuItem("Eliminar carpeta");
        eliminarCarpeta.addActionListener(this);
        opcionesCarpetas.add(eliminarCarpeta);

        nuevaNota = new JMenuItem("Nueva nota");
        nuevaNota.addActionListener(this);
        opcionesNotas.add(nuevaNota);

        eliminarNota = new JMenuItem("Eliminar nota");
        eliminarNota.addActionListener(this);
        opcionesNotas.add(eliminarNota);

        lCompra = new JMenuItem("Lista de compra");
        lCompra.addActionListener(this);
        opcionesNotas.add(lCompra);

        cerrarSesion = new JMenuItem("Cerrar Sesion");
        cerrarSesion.addActionListener(this);
        opcionesMenu.add(cerrarSesion);

        eliminarUsuario = new JMenuItem("Eliminar usuario");
        eliminarUsuario.addActionListener(this);
        opcionesMenu.add(eliminarUsuario);


        tree1.setModel(new FileSystemModel(new File(ruta)));
        tree1.addMouseListener(this);

        gestorArchivos = new GestorArchivos();


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String path = validarPath();

        if (e.getSource() == nuevaNota) {
            gestorArchivos.editar(nota(path), " ");
            this.tree1.updateUI();
        }


        if (e.getSource() == nuevaCarpeta) {

            try {
                File file = new File(path);

                if (!file.isFile()) {
                    String mensaje = JOptionPane.showInputDialog("Nombre Carpeta");
                    gestorArchivos.crearDirectorio(file.getPath() + "\\" + mensaje);
                    this.tree1.updateUI();
                }
            } catch (NullPointerException e1) {
                System.err.println("Error al crear la carpeta");
            }


        }

        if (e.getSource() == eliminarNota) {
            try {
                File file = new File(path);

                if (file.isFile()) {
                    gestorArchivos.eliminarArchivos(file.getPath());
                    this.tree1.updateUI();

                }
            } catch (NullPointerException e1) {
                System.err.println("No se puede eliminar la nota");
            }


        }
        if (e.getSource() == eliminarCarpeta) {
            try {
                File file = new File(path);

                if (file.isDirectory() || path.equals(ruta)) {
                    gestorArchivos.eliminarCarpeta(file.getPath());
                    this.tree1.updateUI();
                }
            } catch (NullPointerException e1) {
                System.err.println("Error al eliminar la carpeta");
            }

        }

        if (e.getSource() == lCompra) {
            String n = nota(path);
            String aux = "Producto" + " " + "Precio" + " ;";
            gestorArchivos.editar(n, aux);
            this.tree1.updateUI();

        }
        if (e.getSource() == cerrarSesion) {
            gestorVentanas.ventanaInicio();
            this.setVisible(false);
        }

        if (e.getSource() == eliminarUsuario) {
            int resp = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el Usuario");
            if (resp == 0) {
                gestorArchivos.eliminarCarpeta(ruta);
                gestorArchivos.borrarLinea(usuario, "src/main/resources/Usuarios/usuarios.txt");
                JOptionPane.showMessageDialog(null, "Usuario eliminado");
                gestorVentanas.ventanaInicio();
                this.setVisible(false);
            }
        }

    }


    private String nota(String path) {
        try {
            File file = new File(path);
            if (!file.isFile()) {
                String mensaje = JOptionPane.showInputDialog("Nombre nota");
                gestorArchivos.crearArchivo(file.getPath() + "\\" + mensaje + ".txt", " ");
                return path + "\\" + mensaje.toString() + ".txt";
            }


        } catch (NullPointerException e1) {
            System.err.println("Error al crear la nota");
        }

        return path;
    }

    private String validarPath() {
        var path = String.valueOf(tree1.getAnchorSelectionPath());
        String aux = path.substring(path.indexOf(",") + 2);


        if (path.contains("]")) {
            path = path.substring(0, path.indexOf("]"));
            aux = aux.substring(0, aux.indexOf("]"));
        }

        if (path.contains("[")) {
            path = path.replace("[", "");
        }
        if (path.contains(",")) {
            path = path.substring(0, path.indexOf(","));

        }
        if (aux.contains(",")) {
            aux = aux.substring(0, aux.indexOf(",")) + "\\" + aux.substring(aux.indexOf(",") + 2);
        }

        if (path.equals(aux)) {
            return path;
        } else {
            path = path + "\\" + aux;
        }

        return path;
    }

    private boolean validarNota(File file) {
        String contenido = this.gestorArchivos.verArchivo(file.getPath());

        if (contenido.contains("Precio") && contenido.contains("Producto")) {
            return true;
        }
        return false;
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
        principal.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        principal.setOpaque(true);
        principal.setPreferredSize(new Dimension(400, 500));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        principal.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        principal.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tree1 = new JTree();
        principal.add(tree1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 2, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(100, 10), null, 0, false));
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

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        String path = validarPath();
        File file = new File(path);
        if (file.isFile()) {
            if (validarNota(file)) {
                gestorVentanas.abrirListaCompra(file.getPath());

            } else {
                gestorVentanas.abrirTextEditor(file.getPath());
            }
        }

    }


    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

}

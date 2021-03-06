package GUI;

import Complementos.Calculadora;
import Datos.GestorArchivos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ListaCompra extends JFrame implements KeyListener {
    private JTextField textProducto;
    private JTextField textPrecio;
    private JList producto;
    private JTextField textTotal;
    private JList precio;
    private JPanel compra;
    private String path;
    private DefaultListModel model1;
    private DefaultListModel model2;
    private GestorArchivos gestorArchivos;
    private Calculadora calculadora;

    public ListaCompra(String path) {
        this.path = path;
        this.textProducto.addKeyListener(this);
        this.textPrecio.addKeyListener(this);
        this.precio.addKeyListener(this);
        this.producto.addKeyListener(this);
        this.model1 = new DefaultListModel();
        this.model2 = new DefaultListModel();
        ImageIcon logo = new ImageIcon("src/main/resources/Logo/Logo.png");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("PNO Lista compra");
        this.add(compra);
        setSize(400, 500);
        setLocationRelativeTo(null);
        this.setIconImage(logo.getImage());
        setResizable(false);
        pack();
        setVisible(true);
        this.gestorArchivos = new GestorArchivos();
        abrirArchivo();


    }

    private void abrirArchivo() {

        this.model1.removeAllElements();
        this.model2.removeAllElements();
        this.producto.removeAll();
        this.precio.removeAll();


        String contenido = this.gestorArchivos.verArchivo(path);

        String[] lineas = contenido.split(" ;");
        for (String linea : lineas) {
            String[] credenciales = linea.split(" ");

            model1.addElement(credenciales[0]);
            model2.addElement(credenciales[1]);

        }
        model1.remove(0);
        model2.remove(0);

        producto.setModel(model1);
        precio.setModel(model2);
        sumarTotal();
    }

    private void sumarTotal() {
        calculadora = new Calculadora();
        double[] numeros = new double[precio.getModel().getSize()];
        for (int i = 0; i < precio.getModel().getSize(); i++) {
            double aux = Double.parseDouble(String.valueOf(precio.getModel().getElementAt(i)));
            numeros[i] = aux;
        }
        textTotal.setText(String.valueOf(calculadora.sumarTotal(numeros)));


    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        compra = new JPanel();
        compra.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 15, new Insets(0, 0, 0, 0), -1, -1));
        compra.setPreferredSize(new Dimension(400, 500));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        compra.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 10, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Producto");
        compra.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        compra.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        compra.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(1, 10, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        textProducto = new JTextField();
        compra.add(textProducto, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 7, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Precio");
        compra.add(label2, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textPrecio = new JTextField();
        compra.add(textPrecio, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 7, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        compra.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        producto = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        producto.setModel(defaultListModel1);
        compra.add(producto, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        textTotal = new JTextField();
        textTotal.setEditable(false);
        compra.add(textTotal, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 9, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Total");
        compra.add(label3, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        precio = new JList();
        final DefaultListModel defaultListModel2 = new DefaultListModel();
        precio.setModel(defaultListModel2);
        compra.add(precio, new com.intellij.uiDesigner.core.GridConstraints(3, 9, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return compra;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            if (!textProducto.getText().equals("") && !textPrecio.getText().equals("")) {
                if (textPrecio.getText().matches("[0-9]+")) {
                    gestorArchivos.editar(path, textProducto.getText() + " " + textPrecio.getText() + " ;");
                    textPrecio.setText("");
                    textProducto.setText("");
                }
                textPrecio.setText("");
                textProducto.setText("");
                abrirArchivo();
            }

        }

        if (e.getKeyCode() == 127) {

            int n = producto.getSelectedIndex();
            model2.remove(n);
            model1.remove(n);
            gestorArchivos.borrarLinea(n + 2, path);

            abrirArchivo();

        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}

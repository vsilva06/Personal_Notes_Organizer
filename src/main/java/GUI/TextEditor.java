package GUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextEditor extends JFrame implements ActionListener{

    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JLabel fontLabel;
    private JSpinner fontSizeSpinner;
    private JButton fontColorButton;
    private JComboBox<String> fontBox;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem abrirItem;
    private JMenuItem guardarItem;
    private JMenuItem salirItem;
    private String path;

    public TextEditor(String path){
        this.path = path;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("PNO Editor de Texto");
        ImageIcon logo = new ImageIcon("src/main/resources/Logo/Logo.png");
        this.setIconImage(logo.getImage());
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial",Font.PLAIN,20));

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450,450));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        fontLabel = new JLabel("Font: ");

        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(new Dimension(50,25));
        fontSizeSpinner.setValue(20);
        fontSizeSpinner.addChangeListener(e -> textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int) fontSizeSpinner.getValue())));

        fontColorButton = new JButton("Color");
        fontColorButton.addActionListener(this);

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        fontBox = new JComboBox<>(fonts);
        fontBox.addActionListener(this);
        fontBox.setSelectedItem("Arial");

        // ------ menubar ------

        menuBar = new JMenuBar();
        fileMenu = new JMenu("Archivo");
        abrirItem = new JMenuItem("Abrir");
        abrirItem.setVisible(false);
        guardarItem = new JMenuItem("Guardar");
        salirItem = new JMenuItem("Salir");

        abrirItem.addActionListener(this);
        guardarItem.addActionListener(this);
        salirItem.addActionListener(this);

        fileMenu.add(abrirItem);
        fileMenu.add(guardarItem);
        fileMenu.add(salirItem);
        menuBar.add(fileMenu);

        // ------ /menubar ------

        this.setJMenuBar(menuBar);
        this.add(fontLabel);
        this.add(fontSizeSpinner);
        this.add(fontColorButton);
        this.add(fontBox);
        this.add(scrollPane);

    }

    public void abrirArchivo(){
            File file = new File(path);
            textArea.setText("");
            try (Scanner fileIn = new Scanner(file)) {
                if (file.isFile() || file.getPath().contains(".txt")) {
                    while (fileIn.hasNextLine()) {
                        String line = fileIn.nextLine() + "\n";
                        textArea.append(line);
                        this.setVisible(true);
                    }
                }
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                System.err.println("No se puede leer el archivo");
            }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==fontColorButton) {

            Color color = JColorChooser.showDialog(null, "Choose a color", Color.black);

            textArea.setForeground(color);
        }

        if(e.getSource()==fontBox) {
            textArea.setFont(new Font((String)fontBox.getSelectedItem(),Font.PLAIN,textArea.getFont().getSize()));
        }

        if(e.getSource()==abrirItem) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("src/main/resources/Personal Notes Organizer"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fileChooser.setFileFilter(filter);

            int response = fileChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try (Scanner fileIn = new Scanner(file)) {
                    if (file.isFile()) {
                        while (fileIn.hasNextLine()) {
                            String line = fileIn.nextLine() + "\n";
                            textArea.append(line);
                        }
                    }
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        if(e.getSource()==guardarItem) {

            File file = new File(path);
            try(PrintWriter fileOut = new PrintWriter(file)){
                fileOut.println(textArea.getText());
            }catch (FileNotFoundException e1){

            }

        }
        if(e.getSource()==salirItem) {
            System.exit(0);
        }

    }
}
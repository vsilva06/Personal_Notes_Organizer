package Datos;

import GUI.Inicio;
import GUI.ListaCompra;
import GUI.Principal;
import GUI.TextEditor;

public class GestorVentanas {

    private Inicio inicio;
    private Principal principal;
    private ListaCompra listaCompra;
    private TextEditor textEditor;

    public GestorVentanas() {

    }

    public void ventanaInicio() {

        this.inicio = new Inicio();
    }

    public void ventanaPrincipal(String s) {

        this.principal = new Principal(s);
    }

    public void abrirListaCompra(String path) {
        this.listaCompra = new ListaCompra(path);
    }

    public void abrirTextEditor(String path) {
        textEditor = new TextEditor(path);
        textEditor.abrirArchivo();
    }


}

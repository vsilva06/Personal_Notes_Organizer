package Datos;

import GUI.Inicio;
import GUI.Principal;

public class GestorVentanas {

    private Inicio inicio;
    private Principal principal;

    public GestorVentanas() {

    }

    public void ventanaInicio() {
        this.inicio = new Inicio();
    }

    public void ventanaPrincipal(String s) {
        this.principal = new Principal(s);
    }

}

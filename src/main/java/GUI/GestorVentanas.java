package GUI;

public class GestorVentanas {

    private Inicio inicio;
    private Principal principal;

    public GestorVentanas(){

    }

    public void ventanaInicio(){
        this.inicio = new Inicio();
    }

    public void ventanaPrincipal(){
        this.principal = new Principal();
    }

}

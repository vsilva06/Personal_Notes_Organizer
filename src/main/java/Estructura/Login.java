package Estructura;

import Datos.GestorCuentas;

public class Login {

    private static final String path = "src/main/resources/Usuarios/usuarios.txt";

    public boolean login(String usuario, int password) {
        String contenido = new GestorCuentas().verArchivo(path);
        String[] lineas = contenido.split(";");
        for (String linea : lineas) {
            String[] credenciales = linea.split(" ");
            if (usuario.equals(credenciales[0]) && String.valueOf(password).equals(credenciales[1])) {
                return true;
            }
        }
        return false;
    }
}

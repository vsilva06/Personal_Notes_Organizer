package Estructura;


import Datos.GestorCuentas;

public class Usuario {

    private static final GestorCuentas gestor = new GestorCuentas();

    private boolean revisarExistencia(String usuario) {
        String ruta = "/src/main/resources/Usuarios/usuarios.txt";
        String[] credenciales = gestor.verArchivo(ruta).split("\n");
        for (String credencial : credenciales) {
            if (credencial.split(" ")[0].equals(usuario)) {
                return true;
            }
        }
        return false;
    }

    public boolean crearCuenta(String usuario, String password) {
        if (revisarExistencia(usuario)) {
            System.err.println("Usuario ya creado");
            return false;
        }

        if (crearDirectorioUsuario(usuario) && crearArchivoBienvenida(usuario) && agregarArchivo()) {
            crearEntradaUsuarios(usuario, password);
            System.out.println("Usuario creado exitosamente");
            return true;
        }

        System.out.println("No se ha podio crear exitosamente al usuario");
        return false;

    }

    private boolean agregarArchivo() {
        String path = "src/main/resources/Usuarios/usuarios.txt";
        if (gestor.verArchivo(path).equals(toString()) != path.equals(toString())) {
            gestor.crearArchivo(path, "");
            return true;
        }
        return true;

    }

    private boolean crearDirectorioUsuario(String usuario) {
        String path = "src/main/resources/Usuarios/" + usuario;
        return gestor.crearDirectorio(path);
    }

    private boolean crearArchivoBienvenida(String usuario) {
        String path = "src/main/resources/Usuarios/" + usuario + "/bienvenida.txt";
        String mensaje = "Bienvenido " + usuario + "! \n Este es tu nuevo Personal Note Organizer!";
        return gestor.crearArchivo(path, mensaje);
    }

    private void crearEntradaUsuarios(String usuario, String password) {

        String path = "src/main/resources/Usuarios/usuarios.txt";
        String entrada = usuario + " " + password.hashCode() + " ;";
        gestor.editar(path, entrada);


    }
}
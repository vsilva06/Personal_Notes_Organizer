package Estructura;

public class Usuario {

    private static final GestorCuentas gestor = new GestorCuentas();
    private boolean revisarExistencia(String usuario){
        String ruta = "/usuarios/usuarios.txt";
        String[] credenciales = gestor.verArchivo(ruta).split("\n");
        for(String credencial : credenciales){
            if (credencial.split(" ")[0].equals(usuario)) {
                return true;
            }
        }
        return false;
    }
    public boolean crearUsuario(String usuario, String password){
        if(revisarExistencia(usuario)){
            return false;
        }
        crearEntradaUsuarios(usuario, password);
        if(crearDirectorioUsuario(usuario) && crearArchivoBienvenida(usuario)){
            System.out.println("Usuario creado exitosamente");
            return true;
        }

        System.out.println("No se ha podio crear exitosamente al usuario");
        return false;

    }
    private boolean crearDirectorioUsuario(String usuario){
        String path = "notas/"+usuario;
        return gestor.crearDirectorio(path);
    }
    private boolean crearArchivoBienvenida(String usuario){
        String path = "notas/"+usuario+"/bienvenida.txt";
        String mensaje = "Bienvenido "+usuario+"! \n Este es tu nuevo Personal Note Organizer!";
        return gestor.crearArchivo(path, mensaje);
    }
    private void crearEntradaUsuarios(String usuario, String password){
        String path = "usuarios/usuarios.txt";
        String entrada = usuario+" "+password+" ;";
        gestor.editar(path, entrada);
    }
}
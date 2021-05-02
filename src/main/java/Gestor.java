import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Gestor {


    public void crearDirectorio() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("");
        String ruta = teclado.nextLine();
        Path directorio = Paths.get(ruta);
        if (Files.exists(directorio)) {
            System.out.println("El directorio ya existe");
        } else {
            try {
                Files.createDirectories(directorio);
                System.out.println("El directorio ya existe");
            } catch (IOException e) {
                System.out.println("El directorio no pudo ser creado");
            }
        }

    }

    public void crearArchivo() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese la ruta completa del archivo");
        String ruta = teclado.nextLine();
        Path archivo = Paths.get(ruta);
        System.out.println("Ingresa el texto a guardar en el archivo");
        String texto = teclado.nextLine();
        try {
            Files.write(archivo, texto.getBytes());
            System.out.println("Se ha guardado el archivo");
        } catch (IOException e) {
            System.out.println("El archivo no puede ser guardado");
        }

    }

    public void leerArchivo() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese la ruta completa del archivo");
        String ruta = teclado.nextLine();
        Path archivo = Paths.get(ruta);
        String texto = "";
        try {
            texto = new String(Files.readAllBytes(archivo));
            System.out.println("El contenido del archvo es:\n" + texto);
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser leido");
        }
    }

    public void copiarArchivo() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese la ruta del archivo original");
        String ruta = teclado.nextLine();
        Path archivo = Paths.get(ruta);
        System.out.println("Ingrese la ruta de destino de la copia");
        String newRuta = teclado.nextLine();
        Path newArchivo = Paths.get(newRuta);
        try {
            Files.copy(archivo, newArchivo, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("El archivo fue copiado exitosamente");
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser copiado");
        }

    }

    public void eliminarArchivo() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese la ruta del archivo");
        String ruta = teclado.nextLine();
        Path archivo = Paths.get(ruta);
        try {
            Files.deleteIfExists(archivo);
            System.out.println("El archivo fue eliminado exitosamente");
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser eliminado");
        }
    }
}

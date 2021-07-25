package Estructura;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestorCuentas {

        public boolean crearDirectorio(String ruta) {
            Path directorio = Paths.get(ruta);
            if (Files.exists(directorio)) {
                System.out.println("El directorio ya existe");
                return false;
            } else {
                try {
                    Files.createDirectories(directorio);
                    System.out.println("El directorio fue creado");
                    return true;
                } catch (IOException e) {
                    System.out.println("El directorio no fue creado");
                    return false;
                }
            }

        }

        public boolean crearArchivo(String ruta, String texto) {
            Path archivo = Paths.get(ruta);
            try {
                Files.write(archivo, texto.getBytes());
                System.out.println("Se ha guardado en el archivo");
                return true;
            } catch (IOException e) {
                System.out.println("El archivo no puede ser guardado");
                return false;
            }
        }

        public String verArchivo(String ruta) {
            Path archivo = Paths.get(ruta);
            String texto = "";
            try {
                texto = new String(Files.readAllBytes(archivo));
                System.out.println("El contenido es: \n"+texto);
            } catch (IOException e) {
                System.out.println("El archivo no puede ser leido");
            }
            return texto;
        }

        public void editar(String ruta, String texto){
            String texto1 = verArchivo(ruta)+"\n";
            crearArchivo(ruta, texto1+texto);
        }
    }

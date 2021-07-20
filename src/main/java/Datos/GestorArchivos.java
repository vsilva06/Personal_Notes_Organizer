package Datos;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;
import java.util.stream.Stream;

public class GestorArchivos {
    public GestorArchivos() {
    }

    public void crearDirectorio(String carpeta) {
        Path directorio = Paths.get(carpeta);
        if (Files.exists(directorio)) {
            //si el directorio existe no hacer nada
        } else {
            try {
                Files.createDirectories(directorio);
                //se crea el directorio
            } catch (IOException e) {
                System.out.println("El directorio no pudo ser creado");
            }
        }
    }


    public void crearArchivo(String donde) {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            String ruta = donde + ".txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            bw.write("");

        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println("La ruta especificada no es correcta");

        } finally {
            try {
                //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {

            }
        }
    }


    public void eliminarArchivos(String donde) {
        Path archivo = Paths.get(donde);

        try {
            Files.deleteIfExists(archivo);
            //se elimina el archivo
        } catch (IOException e) {
            System.out.println("El archivo no  pudo ser eliminado");
        }
    }


}

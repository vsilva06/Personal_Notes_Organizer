package Datos;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
                System.err.println("El directorio no pudo ser creado");
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

            bw.write(" ");

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


    public void eliminarArchivos(String donde) {
        Path archivo = Paths.get(donde);

        try {
            Files.deleteIfExists(archivo);
            //se elimina el archivo
        } catch (IOException e) {
            System.err.println("El archivo no  pudo ser eliminado");
        }
    }

    public void eliminarCarpeta(String donde) {
        File carpeta = new File(donde);
        File[] archivos = carpeta.listFiles();
        for (int i = 0; i < archivos.length; i++) {
            eliminarArchivos(archivos[i].getPath());
        }
        carpeta.delete();
    }

    public void editar(String ruta, String texto) {
        String texto1 = verArchivo(ruta) ;
        crearArchivo(ruta, texto1 + texto);
    }

    public String verArchivo(String ruta) {
        Path archivo = Paths.get(ruta);
        String texto = "";
        try {
            texto = new String(Files.readAllBytes(archivo));
            //System.out.println("El contenido es: \n" + texto);
        } catch (IOException e) {
            System.out.println("El archivo no puede ser leido");
        }
        return texto;
    }


}




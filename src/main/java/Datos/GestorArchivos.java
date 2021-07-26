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
        String texto1 = verArchivo(ruta);
        if (texto1.equals(" ")) {
            crearArchivo(ruta, texto);
        } else {
            crearArchivo(ruta, texto1 + "\n" + texto);
        }
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

    public void borrarLinea(int numero, String ruta) {
        String texto = "";
        Path path = Paths.get(ruta);
        try {

            FileReader fr = new FileReader(String.valueOf(path));
            BufferedReader br = new BufferedReader(fr);
            String linea;
            int cont = 0;
            while ((linea = br.readLine()) != null) {
                cont++;
                if (cont != numero) {
                    if (texto.equals("")) {
                        texto = linea;
                    } else {
                        texto = texto + "\n" + linea;
                    }

                }
            }
            eliminarArchivos(ruta);
            crearArchivo(ruta, " ");
            editar(ruta, texto);

            br.close();
            fr.close();
        } catch (Exception e) {
            System.err.println("No se puede eliminar la linea");
        }


    }


}




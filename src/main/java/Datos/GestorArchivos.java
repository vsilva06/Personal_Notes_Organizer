package Datos;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;
import java.util.stream.Stream;

public class GestorArchivos {
    public GestorArchivos(){
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

//    public void crearArchivo(String donde, String mensaje ) {
//        crearDirectorio();
//        crearArchivo(donde);
//        FileWriter   fw= null;
//        BufferedWriter  bw=null;
//        try{
//            File ruta= new File("src/main/resources/Personal Notes Organizer"+donde+".txt");
//            crearDirectorio();
//            crearArchivo(donde);
//            fw = new FileWriter(ruta.getAbsoluteFile(), true);
//            bw = new BufferedWriter(fw);
//            String linea;
//
//            bw.write(mensaje+"\n");
//        }catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                //Cierra instancias de FileWriter y BufferedWriter
//                if (bw != null)
//                    bw.close();
//                if (fw != null)
//                    fw.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }

    public void crearArchivo(String donde){
        try {
            String ruta = donde+".txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println("La ruta especificada no es correcta");

        }

    }

    public String leerArchivo(String donde) {
        Path archivo = Paths.get("src/main/resources/Personal Notes Organizer"+donde+".txt");
        String texto = "";
        try {
            texto = new String(Files.readAllBytes(archivo));
            System.out.println("El contenido del archivo es:\n" + texto);
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser leido");
        }
        return texto;
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

    public static void guardarArchivo(String donde, Vector lineas){
        try
        {
            FileWriter fichero = new FileWriter("src/main/resources/Personal Notes Organizer"+donde+".txt");
            PrintWriter escribe = new PrintWriter(fichero);
            for(int i=0;i<lineas.size();i++){
                escribe.println(lineas.elementAt(i));
            }
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Vector borrarLinea(int numero, String donde){
        Vector lineas=new Vector();

        try {

            File archivo = new File ("src/main/resources/Personal Notes Organizer"+donde+".txt");
            FileReader fr = new FileReader (archivo);
            BufferedReader  br = new BufferedReader(fr);
            String linea; 
            int cont=0;
            String completo="";
            while((linea=br.readLine())!=null){
                cont++;
                if(cont!=numero){
                    lineas.addElement(linea);//AGREGAR LINEASS A UN VECTOR
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return lineas;
    }

    public int contarLineas(String donde) {
        int contadorL=0;

        File fichero = new File("src/main/resources/Personal Notes Organizer"+donde+".txt");
        try {
            BufferedReader fich = new BufferedReader(new FileReader("src/main/resources/Personal Notes Organizer"+donde+".txt"));
            String linea;
            try {
                while ((linea = fich.readLine()) != null) {
                    contadorL++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try{fich.close();
            }catch(Exception e){
                System.out.println("No se pudo cerrar el BufferedReader");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return contadorL+1;
    }

//    public JTextArea escribirTexto(String donde,JTextArea quecosa ){
//        crearDirectorio();
//        crearArchivo(donde);
//        FileReader fr=null;
//        quecosa.setText("");
//        BufferedReader br=null;
//        try{
//            File archivo=new File("src/main/resources/Personal Notes Organizer"+donde+".txt");
//            fr= new FileReader(archivo);
//            br = new BufferedReader(fr);
//            String linea;
//            while((linea=br.readLine()) != null)
//            {
//                quecosa.setText(quecosa.getText() + linea + "\n");
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }finally{
//            try{
//                if(null != fr){
//                    fr.close();
//                }
//                br.close();
//            }catch(Exception e2){
//                e2.printStackTrace();
//
//            }
//        }
//        return quecosa;
//    }

    public String leerUnaLinea(String donde, int numero){
        String ruta="src/main/resources/Personal Notes Organizer"+donde+".txt";
        Path filePath = Paths.get(ruta);
        String textoLinea="";

        try (Stream<String> lines = Files.lines(filePath)) {
            textoLinea = lines.skip(numero - 1).findFirst().get();
        }catch(Exception e){
            //System.out.println("Error");
        }

        return textoLinea;
    }

    public String quitarNumero(String aque){

        String[] separar=aque.split("\\|");
        return separar[1];
    }

    public void agregarTexto(String donde, String data){
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {

            File file = new File("src/main/resources/Personal Notes Organizer"+donde+".txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(data+"\n");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}

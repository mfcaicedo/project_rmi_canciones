package servidor.Repositorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import servidor.DTO.CancionDTO;
import servidor.utilidades.UtilidadesDirectorio;


public class CancionRepository implements CancionRepositoryInt {
    private final ArrayList<CancionDTO> listaCanciones;

    public CancionRepository() {
        this.listaCanciones = new ArrayList();
    }

    private boolean almacenarArchivo(byte array[], CancionDTO objCancion)
    {
        boolean bandera = true;
        try {
            //Se crea la carpeta de canciones en caso de no existir
            UtilidadesDirectorio.CrearDirectorio();
            String nombreCancion = objCancion.getTitulo();
            File objFile= new File("ServidorDeRespaldo/Canciones/copiaSeguridad_"+nombreCancion + "." + objCancion.getTipo());// archivo donde se almacenara la canción
            OutputStream output= new FileOutputStream(objFile);
            output.write(array);// escribiendo la canción en el archivo
        } catch (FileNotFoundException ex) {
            bandera=false;
        } catch (IOException ex) {
            bandera=false;
        }

        return bandera;
    }

    @Override
    public boolean registrarCancionRespaldo(CancionDTO objCancion) {

        boolean bandera;
        bandera = this.almacenarArchivo(objCancion.getArrayBytes(),objCancion);
        this.listaCanciones.add(objCancion);
        System.out.println("Archivo creado en el servidor de respaldo");
        System.out.println("Metadatos del archivo: ");
        System.out.println("titulo: " + objCancion.getTitulo());
        System.out.println("Artista: " + objCancion.getArtista());
        System.out.println("tamaño en KB: " + objCancion.getTamKB());
        return bandera;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente.utilidades;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import servidor.DTO.CancionDTO;

/**
 *
 * @author LENOVO
 */
public class UtilidadesAudio {
    public static CancionDTO leerMetadatos(String nombreCancion)
    {
        CancionDTO objCancion = null;
        try {
            PrintStream obj;
            obj = new PrintStream(new File("archivoSalida.txt"));
            System.setErr(obj);
            File file=new File(nombreCancion);
            AudioFile f = AudioFileIO.read(file);
            Tag tag = f.getTag();
            int tamMB= (int) (file.length()/1024);           
            String artista=tag.getFirst(FieldKey.ARTIST);            
            String titulo= tag.getFirst(FieldKey.TITLE);
            //saco el tipo
            String nombre = nombreCancion.replace(".", "-");
            String[] partesTipo = nombre.split("-");
            String tipo = partesTipo[1];
            objCancion=  new CancionDTO(artista, titulo, tamMB,tipo);
        } catch (Exception ex) {
            System.out.println("Error al leer los métadatos del archivo");
        }
            
        return objCancion;
    }
    
    public  static byte[] obtenerBytesCancion()
    {
        byte[] arrayBytesCancion=null;
        try {
                File file=new File("cancion.mp3");
                FileInputStream objFileInputStream = new FileInputStream(file);
                BufferedInputStream objBuffer= new BufferedInputStream(objFileInputStream);
                arrayBytesCancion = new byte[(int) file.length()];
                objBuffer.read(arrayBytesCancion, 0, arrayBytesCancion.length); 
        } catch (FileNotFoundException ex) {
                System.out.println("Error, archivo no encontrado");
        } catch (IOException ex) {
                 System.out.println("Error al leer los métadatos del archivo");
        }
          
        return arrayBytesCancion;
    }

}

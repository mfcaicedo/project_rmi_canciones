package servidor.utilidades;
import java.io.File;


public class UtilidadesDirectorio {


    /**
     *
     * @author mfcaicedo, danieleraso
     */
    public static void CrearDirectorio(){
        File directorio = new File("ServidorDeRespaldo/Canciones");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    }

}

package cliente.vista;

import cliente.utilidades.UtilidadesAudio;
import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import servidor.DTO.CancionDTO;
import servidor.controladores.ControladorGestorCancionInt;


public class Menu {

    private final ControladorGestorCancionInt objRemoto;

    public Menu(ControladorGestorCancionInt objRemoto) {
        this.objRemoto = objRemoto;
    }

    public void ejecutarMenuPrincipal() {
        int opcion = 0;
        do {
            System.out.println("======Menu=======");
            System.out.println("1. Ingresar y enviar datos de una canción");
            System.out.println("2. Consultar una canción del servidor");
            System.out.println("3. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1:
                    Opcion1();
                    break;
                case 2:
                    Opcion2();
                    break;

                case 3:
                    System.out.println("Vuelve pronto");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 3);
    }

    private void Opcion1() {
        try {
            System.out.println("Digite el nombre de la  canción: ");
            String nombreCancion = UtilidadesConsola.leerCadena();
            CancionDTO objCancion = UtilidadesAudio.leerMetadatos(nombreCancion);
            byte[] arrayBytesCancion = UtilidadesAudio.obtenerBytesCancion(nombreCancion);
            objCancion.setArrayBytes(arrayBytesCancion);

            boolean valor = objRemoto.registrarCancion(objCancion);//invocación del método remoto
            if (valor)
                System.out.println("Registro realizado satisfactoriamente...");
            else
                System.out.println("no se pudo realizar el registro...");
        } catch (RemoteException e) {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
    }

    /**
     *
     */
    private void Opcion2() {
        try {
        String nombreCancion;
        System.out.println("Digite el título de la canción a consultar:");
        nombreCancion = UtilidadesConsola.leerCadena();
        CancionDTO objCancion;
        objCancion = objRemoto.consultarCancion(nombreCancion);

        if (objCancion != null){
            System.out.println("Resultado obtenido");
            System.out.println("Nombre cancion: " + objCancion.getTitulo());
            System.out.println("Artista: " + objCancion.getArtista());
        }else{
            System.out.println("No se encontro la cancion :(");
        }



         }catch(RemoteException e){
               System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }

    }
}

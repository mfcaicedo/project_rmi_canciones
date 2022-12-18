
package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import servidor.DTO.CancionDTO;
import servidor.DTO.NotificacionDTO;
import servidor.Repositorios.CancionRepositoryInt;


public class ControladorGestorCancionesImpl extends UnicastRemoteObject implements ControladorGestorCancionInt{
    
    private final CancionRepositoryInt objCancionesRepository;
    private final ControladorGestionAdministradoresImpl objReferenciaRemota;

    private final ControladorGestorRespaldoInt objRemoto;

    public ControladorGestorCancionesImpl(CancionRepositoryInt objCancionesRepository,
                                          ControladorGestionAdministradoresImpl objReferenciaRemota,
                                          ControladorGestorRespaldoInt objRemoto) throws RemoteException
    {
        super(); //se asigna un puerto de escucha al OR
        this.objCancionesRepository = objCancionesRepository;
        this.objReferenciaRemota = objReferenciaRemota;
        this.objRemoto = objRemoto;
    }

    @Override
    public boolean registrarCancion(CancionDTO objCancion) throws RemoteException {
        boolean bandera=false;
        if(this.objCancionesRepository.registrarCancion(objCancion))
        {
            bandera=true;
            //Enviar una copia de la cacion al servidor de respaldo
            this.objRemoto.registrarCancionRespaldo(objCancion);
            //Calculos
            ArrayList<CancionDTO> listCanciones = new ArrayList<>();
            int numCancionesMp3 = 0;
            int numCancionesFLAC = 0;
            double tamCancionesMb = 0;
            listCanciones = this.objCancionesRepository.listarCanciones();

            for (int i = 0; i < listCanciones.size() ; i++){
                if (listCanciones.get(i).getTipo().equals("mp3")){
                    numCancionesMp3 ++;
                } else {
                    numCancionesFLAC ++;
                }
                tamCancionesMb += listCanciones.get(i).getTamKB();
            }
            //convierto a MB
            tamCancionesMb = tamCancionesMb / 1000;
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
            String str = formatter.format(date);
            //se crea el objeto de la notificacion
            NotificacionDTO objNotificacion = new NotificacionDTO(str, numCancionesMp3, numCancionesFLAC,tamCancionesMb);
            //Envio notificacion
            this.objReferenciaRemota.notificarAdministradores(objCancion, objNotificacion);
        }
        
        return bandera;
    }

    @Override
    public ArrayList<CancionDTO> listarCanciones() throws RemoteException {
        return this.objCancionesRepository.listarCanciones();
    }

    @Override
    public CancionDTO consultarCancion(String nombreCancion) throws RemoteException {
        return this.objCancionesRepository.consultarCancion(nombreCancion);
    }


}

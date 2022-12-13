
package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import servidor.DTO.CancionDTO;
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
            //Envio notificacion
            this.objReferenciaRemota.notificarAdministradores(objCancion.getTitulo());
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

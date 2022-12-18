package servidor.controladores;

import cliente.controladores.AdministradorCallbackInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.DTO.CancionDTO;
import servidor.DTO.NotificacionDTO;

public class ControladorGestionAdministradoresImpl extends UnicastRemoteObject implements ControladorGestionAdministradoresInt{

    private final List<AdministradorCallbackInt> referenciasAdministradores;
    
    public ControladorGestionAdministradoresImpl() throws RemoteException
    {
        super();
        this.referenciasAdministradores= new ArrayList();
    }

    @Override
    public void registrarReferenciaAdministrador(AdministradorCallbackInt objReferencia) throws RemoteException {
        this.referenciasAdministradores.add(objReferencia);
    }
    
    public void notificarAdministradores(CancionDTO objCancion, NotificacionDTO objNotificacion)
    {
        System.out.println("Notificando administradores");
        this.referenciasAdministradores.forEach(e->{
            try {
                e.notificarRegistroDeNuevaCancion(objCancion, objNotificacion);
            } catch (RemoteException ex) {
                System.out.println("error " + ex);
            }
        });
    }
    
    
}

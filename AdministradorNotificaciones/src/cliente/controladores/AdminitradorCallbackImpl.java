package cliente.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public class AdminitradorCallbackImpl extends UnicastRemoteObject implements AdministradorCallbackInt{
    
    public AdminitradorCallbackImpl() throws RemoteException
    {
        super();
    }
    
    @Override
    public void notificarRegistroDeNuevaCancion(String mensaje) throws RemoteException {
        System.out.println("Nueva canción registrada: " + mensaje);
    }

   

    
}



package cliente.controladores;

import servidor.DTO.CancionDTO;
import servidor.DTO.NotificacionDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;


//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface AdministradorCallbackInt extends Remote
{
    public void notificarRegistroDeNuevaCancion(CancionDTO objCancion, NotificacionDTO objNotificacion) throws RemoteException;
}

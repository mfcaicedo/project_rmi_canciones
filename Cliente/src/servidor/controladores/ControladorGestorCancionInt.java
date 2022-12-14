package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import servidor.DTO.CancionDTO;

//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface ControladorGestorCancionInt extends Remote
{
    //Definicion del primer método remoto
   public boolean registrarCancion(CancionDTO objCancion)  throws RemoteException;
    //cada definición del método debe especificar que puede lanzar la excepción java.rmi.RemoteException
    public CancionDTO consultarCancion(String nombreCancion) throws RemoteException;
    
  
}

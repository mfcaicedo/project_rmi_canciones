package cliente.controladores;

import servidor.DTO.CancionDTO;
import servidor.DTO.NotificacionDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AdminitradorCallbackImpl extends UnicastRemoteObject implements AdministradorCallbackInt{
    
    public AdminitradorCallbackImpl() throws RemoteException
    {
        super();
    }
    
    @Override
    public void notificarRegistroDeNuevaCancion(CancionDTO objCancion, NotificacionDTO objNotificacion) throws RemoteException {
        System.out.println("Nueva canción se ha registrada");
        System.out.println("Título: " + objCancion.getTitulo());
        System.out.println("Artista: " + objCancion.getArtista());
        System.out.println("Tipo: " + objCancion.getTipo());
        System.out.println("Tamaño: " + objCancion.getTamKB() + "KB");
        System.out.println("Fecha de registro de la canción en el servidor: " + objNotificacion.getFechaRegistro());
        System.out.println("Cantidad de canciones tipo .mp3 registradas: " + objNotificacion.getCantidadCancionesMp3());
        System.out.println("Cantidad de canciones tipo .FLAC registradas: " + objNotificacion.getCantidadCancionesfFLAC());
        System.out.println("Espacio utilizado por todas las canciones en MB: " + objNotificacion.getEspacioTotal());

    }

   

    
}


package servidor.controladores;

import servidor.DTO.CancionDTO;
import servidor.Repositorios.CancionRepositoryInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ControladorGestorRespaldoImpl extends UnicastRemoteObject implements ControladorGestorRespaldoInt{

    private final CancionRepositoryInt objCancionesRepository;

    public ControladorGestorRespaldoImpl(CancionRepositoryInt objCancionesRepository) throws RemoteException
    {
        super(); //se asigna un puerto de escucha al OR
        this.objCancionesRepository = objCancionesRepository;
    }

    @Override
    public boolean registrarCancionRespaldo(CancionDTO objCancion) throws RemoteException {
        boolean bandera=false;
        if(this.objCancionesRepository.registrarCancionRespaldo(objCancion))
        {
            bandera = true;
        }

        return bandera;
    }
}

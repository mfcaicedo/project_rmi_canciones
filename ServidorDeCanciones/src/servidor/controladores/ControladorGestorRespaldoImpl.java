package servidor.controladores;

import cliente.controladores.AdministradorCallbackInt;
import servidor.DTO.CancionDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ControladorGestorRespaldoImpl extends UnicastRemoteObject implements ControladorGestorRespaldoInt{

    private static ControladorGestorRespaldoInt objRemoto;
    public ControladorGestorRespaldoImpl() throws RemoteException
    {
        super();
    }

    @Override
    public boolean registrarCancionRespaldo(CancionDTO objCancion) throws RemoteException {
        return objRemoto.registrarCancionRespaldo(objCancion);
    }
}

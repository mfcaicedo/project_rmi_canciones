/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.servicios;


import servidor.controladores.ControladorGestorRespaldoImpl;
import servidor.utilidades.UtilidadesRegistroS;
import servidor.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import servidor.Repositorios.CancionRepository;
import servidor.controladores.ControladorGestionAdministradoresImpl;
import servidor.controladores.ControladorGestorCancionesImpl;


public class ServidorDeObjetos
{
    public static void main(String args[]) throws RemoteException
    {        
         
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";
                       
        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiRegistry ");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiRegistry ");
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero(); 
     
        CancionRepository objRepository = new CancionRepository();
        ControladorGestionAdministradoresImpl objRemotoGestionAdministradores = new ControladorGestionAdministradoresImpl();
        ControladorGestorRespaldoImpl objRemoteGestionRespaldo = new ControladorGestorRespaldoImpl();
        ControladorGestorCancionesImpl objRemotoGestionCanciones = new ControladorGestorCancionesImpl(objRepository,
                objRemotoGestionAdministradores, objRemoteGestionRespaldo);
        
        try
        {
           UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoGestionCanciones, direccionIpRMIRegistry, numPuertoRMIRegistry, "objServicioGestionCanciones");
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoGestionAdministradores, direccionIpRMIRegistry, numPuertoRMIRegistry, "objServicioGestionAdministradores");
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoteGestionRespaldo, direccionIpRMIRegistry, numPuertoRMIRegistry, "objServicioGestionRespaldo");
            
        } catch (Exception e)
        {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" +  e.getMessage());
        }
        
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.servicios;


import servidor.controladores.ControladorGestorRespaldoInt;
import servidor.utilidades.UtilidadesRegistroC;
import servidor.utilidades.UtilidadesRegistroS;
import servidor.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import servidor.Repositorios.CancionRepository;
import servidor.controladores.ControladorGestionAdministradoresImpl;
import servidor.controladores.ControladorGestorCancionesImpl;


public class ServidorDeObjetos
{

    //creo el objeto remoto de la interfaz ControladorGestorRespaldoInt
    private static ControladorGestorRespaldoInt objRemoto;
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
        //consulto el objRemoto del servidor de respaldo
        objRemoto = (ControladorGestorRespaldoInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry, 2021, "objServicioGestionRespaldo");

        ControladorGestorCancionesImpl objRemotoGestionCanciones = new ControladorGestorCancionesImpl(objRepository,
                objRemotoGestionAdministradores, objRemoto);

        try
        {
           UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoGestionCanciones, direccionIpRMIRegistry, numPuertoRMIRegistry, "objServicioGestionCanciones");
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoGestionAdministradores, direccionIpRMIRegistry, numPuertoRMIRegistry, "objServicioGestionAdministradores");

        } catch (Exception e)
        {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" +  e.getMessage());
        }
        
        
    }
}

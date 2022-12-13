/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.servicios;


import servidor.controladores.ControladorGestorRespaldoImpl;
import servidor.controladores.ControladorGestorRespaldoInt;
import servidor.utilidades.UtilidadesRegistroS;
import servidor.utilidades.UtilidadesConsola;

import java.rmi.RemoteException;

import servidor.Repositorios.CancionRepository;


public class ServidorDeObjetos {
    private static ControladorGestorRespaldoInt objRemoto;
    public static void main(String args[]) throws RemoteException {

        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";

        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
        direccionIpRMIRegistry = servidor.utilidades.UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
        numPuertoRMIRegistry = servidor.utilidades.UtilidadesConsola.leerEntero();

        CancionRepository objRepository = new CancionRepository();
        ControladorGestorRespaldoImpl objRemotoGestionRespaldo = new ControladorGestorRespaldoImpl(objRepository);

        try
        {
            UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoGestionRespaldo, direccionIpRMIRegistry, numPuertoRMIRegistry, "objServicioGestionRespaldo");

        } catch (Exception e)
        {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" +  e.getMessage());
        }


    }
}

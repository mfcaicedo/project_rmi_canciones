/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.servicios;


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

        objRemoto = (ControladorGestorRespaldoInt) UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto,direccionIpRMIRegistry,numPuertoRMIRegistry, "objServicioGestionAdministradores");

        AdminitradorCallbackImpl objRemotoAdministrador = new AdminitradorCallbackImpl();

        objRemoto.registrarReferenciaAdministrador(objRemotoAdministrador);

    }
}

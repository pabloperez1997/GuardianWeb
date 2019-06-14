/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Persistencia.persistencia;
        /**
         *
         * @author jp
         */

public class fabricaElGuardian {

    private static fabricaElGuardian instance;

    iControladorCliente iCC = new controladorCliente();
    iControladorVentas iCV= new ControladorVentas();
    IControlerProperties iCP = new ControladorProperties();
    iControladorServicios iCS= new controladorServicios();
    iControladorReservas iCR= new ControladorReservas();
    persistencia perInstance = persistencia.getInstance();

    public static fabricaElGuardian getInstance() {
        if (instance == null) {
            instance = new fabricaElGuardian();
        }
        return instance;
    }

    private fabricaElGuardian() {

    }

    public iControladorCliente getInstanceIControladorCliente() {
        return iCC;
    }

    public persistencia getInstancePersistencia() {
        return perInstance;
    }

    public iControladorVentas getInstanceIControladorVentas() {
        return iCV;
    }

    public IControlerProperties getInstanceIControladorProperties() {
        return iCP;
    }

    public iControladorServicios getInstanceIControladorServicios() {
        return iCS;
    }

    public iControladorReservas getInstanceIControladorReservas() {
        return iCR;
    }

    

    
}

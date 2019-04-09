/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Persistencia.clientePersistencia;
import java.util.HashMap;

/**
 *
 * @author jp
 */
public class controladorCliente implements iControladorCliente {

    /*
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("elGuardianServidorPU");
    
     */
    /////////////Variables///////////////
    private static controladorCliente instance;
    ////////////Arreglos////////////////
    HashMap<String, cliente> clientes;
    HashMap<String, mascota> mascotas;

    private clientePersistencia cPer = new clientePersistencia();

    public static controladorCliente getInstance() {
        if (instance == null) {
            instance = new controladorCliente();
        }
        return instance;
    }

    /*   private static final EntityManager em = Persistencia.persistencia.getInstance().getEm();

    
    public static EntityManager getEm() {
        return em;
    }*/
    @Override
    public boolean altaCliente(cliente clie) {
        try {
            System.out.println("altaCliente inicio");
            clientes.put(clie.getCedula(), clie);

            return cPer.altaCliente(clie);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean modificarCliente(cliente clieMod) {
        try {

            return cPer.modificarCliente(clieMod);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public void modClienteArreglo(cliente clienteMod) {
        if (clientes.containsKey(clienteMod.getCedula())) {
            cliente cli = (cliente) clientes.get(clienteMod.getCedula());
            
        }

    }
}

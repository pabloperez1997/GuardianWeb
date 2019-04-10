/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Persistencia.clientePersistencia;
import java.util.HashMap;
import java.util.List;

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
    private utilidades util = utilidades.getInstance();
    ////////////Arreglos////////////////
    private HashMap<String, cliente> clientes= new HashMap<>();
    private HashMap<String, mascota> mascotas = new HashMap<>();
    
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
 
    
    public void modClienteArreglo(cliente clienteMod) {
        if (clientes.containsKey(clienteMod.getCedula())) {
            cliente cli = (cliente) clientes.get(clienteMod.getCedula());
            
        }
        
    }
    
    public void cargarClientesArreglo() {
        List arregloClientes = (List<cliente>) cPer.getArregloClientes();
        for (int i = 0; i <= arregloClientes.size(); i++) {
            cliente clAr = new cliente();
            clAr = (cliente) arregloClientes.get(i);
            clientes.put(clAr.getCedula(), clAr);
            
        }
    }
    
    @Override
    public boolean emailValido(String email) {
        return util.emailValido(email);
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import javax.persistence.EntityManager;

/**
 *
 * @author jp
 */
public class controladorCliente implements iControladorCliente{
   /*
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("elGuardianServidorPU");
    
    */
    private static controladorCliente instance;

    public static controladorCliente getInstance() {
        if (instance == null) {
            instance = new controladorCliente();
        }
        return instance;
    }

    private static final EntityManager em = Persistencia.persistencia.getInstance().getEm();

    
    public static EntityManager getEm() {
        return em;
    }

    @Override
    public void altaCliente(cliente clie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

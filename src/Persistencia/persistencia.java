/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jp
 */
public class persistencia {

    private static persistencia instance;
    private static String unidadPersistencia = "elGuardianServidorPU";
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(unidadPersistencia);
    private static final EntityManager em = emf.createEntityManager();
/**
 *@return retorna un manejador de entidades
 */
    public EntityManager getEm() {
        return em;
    }

    public static persistencia getInstance() {
        if (instance == null) {
            instance = new persistencia();
        }
        return instance;
    }

    public void persistir(Object object) {

        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            em.getTransaction().rollback();
        }
    }

    public String getUnidadPersistencia() {
        return unidadPersistencia;
    }

    public void setUnidadPersistencia(String unidadPersistencia) {
        persistencia.unidadPersistencia = unidadPersistencia;
    }

}

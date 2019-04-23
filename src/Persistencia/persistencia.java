/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author jp
 */
public class persistencia {

    private static persistencia instance;
    private static String unidadPersistencia = "elGuardianServidorPU";
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(unidadPersistencia);
    private static final clientePersistencia clientePer = new clientePersistencia();
    private static final EntityManager em = emf.createEntityManager();

    /**
     * @return retorna un manejador de entidades
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

    public clientePersistencia getClientePersistencia() {
        return clientePer;
    }

    public String getUnidadPersistencia() {
        return unidadPersistencia;
    }

    public void setUnidadPersistencia(String unidadPersistencia) {
        persistencia.unidadPersistencia = unidadPersistencia;
    }

    public boolean persis(Object obj) {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar: " + e.getMessage() + " Causa: " + e.getCause());
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean modificar(Object obj) {
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public boolean eliminar(Object obj) {
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public List<Object> getListaObjetos(String sql, Class clase) {
        try {
           em.getTransaction().begin();
            List resultList = em.createNativeQuery(sql, clase.getClass()).getResultList();
            em.getTransaction().commit();
            return resultList;

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;

    }
}

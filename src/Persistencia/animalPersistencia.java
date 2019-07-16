/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.fabricaElGuardian;
import Logica.iControladorReservas;
import Logica.mascota;
import Logica.raza;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jp
 */
public class animalPersistencia extends persistencia {

    private static final EntityManager em = Persistencia.persistencia.getInstance().getEm();
    private static animalPersistencia instance;
   

    public static animalPersistencia getInstance() {
        if (instance == null) {
            instance = new animalPersistencia();
        }
        return instance;
    }

    /**
     * *
     * Retorna una lista de mascota en caso de error retorna null
     *
     * @return listaMascota
     */
    public List<mascota> getMascotas() {
        List<mascota> listaMascota = new ArrayList<>();
        try {
            em.getTransaction().begin();
            listaMascota = em.createNativeQuery("select * from mascota", mascota.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error getMascotas: Mensaje: " + e.getMessage() + "Causa: " + e.getCause());

            return null;
        }

        return listaMascota;
    }

    public List<raza> getRazas() {

        List<raza> listaRazas = new ArrayList<>();

        try {
            em.getTransaction().begin();
            listaRazas = em.createNativeQuery("select * from raza", raza.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error getRazas: Mensaje: " + e.getMessage() + "Causa: " + e.getCause());

            return null;
        }
        return listaRazas;
    }

    public mascota getMascota(Long id) {
        mascota obj = null;

        try {
            em.getTransaction().begin();
            obj = (mascota) em.find(mascota.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error getMascota: Mensaje: " + e.getMessage() + "Causa: " + e.getCause());

        }
        return obj;
    }
    
    public List<String> obtenerReservasMascota(Long id){
    List<String> listaid = new ArrayList<>();

        try {
            em.getTransaction().begin();
            listaid= em.createNativeQuery("SELECT id FROM reserva WHERE mascota_id="+id).getResultList();
            em.getTransaction().commit();
            return listaid;
        } catch (Exception e) {
            System.err.println("Error getRazas: Mensaje: " + e.getMessage() + "Causa: " + e.getCause());
            return null;
        }
}
    
    public boolean eliminarReservasMascota(Long id){
         try {
            em.getTransaction().begin();
           em.createNativeQuery("DELETE FROM reserva WHERE mascota_id="+id);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.err.println("Error getRazas: Mensaje: " + e.getMessage() + "Causa: " + e.getCause());
            return false;
        }
    }
    
    public boolean eliminarMascotaCliente(Long id){
         try {
            em.getTransaction().begin();
           em.createNativeQuery("DELETE FROM cliente_mascota WHERE mascotasCliente_id="+id);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.err.println("Error getRazas: Mensaje: " + e.getMessage() + "Causa: " + e.getCause());
            return false;
        }
    }
}

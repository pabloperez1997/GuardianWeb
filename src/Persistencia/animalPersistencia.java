/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.mascota;
import Logica.raza;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jp
 */
public class animalPersistencia extends persistencia {

    private static final EntityManager em = Persistencia.persistencia.getInstance().getEm();

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
            System.err.println(e.getMessage());
            return null;
        }

        return listaMascota;
    }

    public mascota getMascota(String id) {
        mascota mascota = null;
        try {
            mascota = (mascota) getObjeto(id, mascota.class);

        } catch (Exception e) {

            System.err.println(e.getMessage());
            return null;
        }
        return mascota;
    }

    public List<raza> getRazas() {

        List<raza> listaRazas = new ArrayList<>();

        try {
            em.getTransaction().begin();
            listaRazas = em.createNativeQuery("select * from raza", raza.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        return listaRazas;
    }

    public raza getRaza(String raza) {
        raza r = null;
        try {
            r = (raza) getObjeto(raza, raza.class);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        return r;
    }
}

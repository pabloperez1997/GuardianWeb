/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.producto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gabri
 */
public class ventaPersistencia {

    EntityManager em = persistencia.getInstance().getEm();
    private static ventaPersistencia instance;

    public static ventaPersistencia getventaPersisInstace() {
        if (instance == null) {
            return instance = new ventaPersistencia();

        }
        return instance;
    }

    public List<producto> getListaproductos() {
        List<producto> listaproductos = new ArrayList<>();
        try {
            Query qproducto = (Query) em.createQuery("SELECT p FROM producto p");
            listaproductos = (ArrayList<producto>) qproducto.getResultList();

        } catch (Exception e) {
             System.err.println("Error getListaproductos: Mensaje: "+e.getMessage()+"Causa: "+e.getCause());

        }
        return listaproductos;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.producto;
import Logica.venta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gabri
 */
public class ventaPersistencia extends persistencia {

    EntityManager em = persistencia.getInstance().getEm();
    private static ventaPersistencia instance;

    public static ventaPersistencia getventaPersisInstace() {
        if (instance == null) {
            return instance = new ventaPersistencia();

        }
        return instance;
    }

    @Override
    public venta getObjeto(Long id, Class clase) {
        return (venta) super.getObjeto(id, clase); //To change body of generated methods, choose Tools | Templates.
    }

    public List<producto> getListaproductos() {
        List<producto> listaproductos = new ArrayList<>();
        try {
            Query qproducto = (Query) em.createQuery("SELECT p FROM producto p");
            listaproductos = (ArrayList<producto>) qproducto.getResultList();

        } catch (Exception e) {
            System.err.println("Error getListaproductos: Mensaje: " + e.getMessage() + "Causa: " + e.getCause());

        }
        return listaproductos;
    }

}

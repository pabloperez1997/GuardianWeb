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
 * @author PabloP
 */
public class productoPersistencia extends persistencia {

    EntityManager em = persistencia.getInstance().getEm();

    public List<producto> ObtenerListaProductos() {
        List<producto> res = null;
        em.getTransaction().begin();
        try {
            res = em.createNativeQuery("SELECT * from producto", producto.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error obtenerListaProductos: Mensaje: "+e.getMessage()+"Causa: "+e.getCause());
            em.getTransaction().rollback();
        }
        return res;
    }

    public producto ObtenerProducto(Long codigo) {
        producto p = null;
        try {

            p = (producto) getObjeto(codigo, producto.class);

        } catch (Exception e) {
            System.err.println("Error ObtenerProducto: Mensaje: "+e.getMessage()+"Causa: "+e.getCause());
            return null;
        }
        return p;
    }

}

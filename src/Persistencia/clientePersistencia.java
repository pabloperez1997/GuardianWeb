/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.cliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jp
 */
public class clientePersistencia {

    EntityManager em = persistencia.getInstance().getEm();

    /**
     * @Funcion altaCliente(): da de alta un cliente en la BD
     * @param cliente
     * @return true or false
     */
    public boolean altaCliente(cliente cliente) {
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            System.out.println("Cliente agregado: " + cliente.getNombre() + " " + cliente.getApellido());
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar cliente: " + e.getMessage() + " Causa: " + e.getCause());
            em.getTransaction().rollback();
            return false;
        }
    }

    /**
     * @Funcion modificarCliente(): modifica un cliente en la BD
     * @param clienteMod
     * @return true or false
     */
    public boolean modificarCliente(cliente clienteMod) {
        try {

            em.getTransaction().begin();
            em.merge(clienteMod);
            em.getTransaction().commit();
            System.out.println("Cliente: " + clienteMod.getNombre() + " " + clienteMod.getApellido() + " modificado con exito");
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage() + " Causa: " + e.getCause());
            return false;

        }

    }

    /**
     * @Funcion eliminarCliente(): elimina un cliente de la BD
     * @return true or flase
     * @param clienteDel
     */
    public boolean eliminarCliente(cliente clienteDel) {
        try {
            em.getTransaction().begin();
            em.remove(clienteDel);
            em.getTransaction().commit();
            System.out.println("Cliente: " + clienteDel.getNombre() + " " + clienteDel.getApellido() + " eliminado con exito");
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage() + " Causa: " + e.getCause());
            return false;
        }
    }

    public ArrayList<cliente> getArregloClientes() {
        List<cliente> listaClientes = null;

        try {
            em.getTransaction().begin();
            listaClientes = em.createNativeQuery("Select * from cliente", cliente.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error al levantar los clientes:");

            System.err.println(e.getMessage());
        }
        return (ArrayList<cliente>)listaClientes;
    }
}

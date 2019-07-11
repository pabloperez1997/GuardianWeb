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
import org.glassfish.jersey.process.internal.RequestScope.Instance;

/**
 *
 * @author jp
 */
public class clientePersistencia extends persistencia {

    private static clientePersistencia instance;
    EntityManager em = persistencia.getInstance().getEm();

    public static clientePersistencia getInstance() {
        if (instance == null) {
            instance = new clientePersistencia();
        }
        return instance;
    }

    /**
     * @Funcion altaCliente(): da de alta un cliente en la BD
     * @param cliente
     * @return true or false
     */
    public boolean altaCliente(cliente cliente) {
        try {
            persis(cliente);
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

            modificar(clienteMod);
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
            boolean del = eliminar(clienteDel);
            System.out.println("Cliente: " + clienteDel.getNombre() + " " + clienteDel.getApellido() + " eliminado con exito");
            return del;
        } catch (Exception e) {
            System.err.println(e.getMessage() + " Causa: " + e.getCause());
            return false;
        }
    }

    public ArrayList<cliente> getArregloClientes() {
        List<cliente> listaClientes = null;

        try {
            if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
            }
            listaClientes = em.createNativeQuery("Select * from cliente", cliente.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error al levantar los clientes:");

            System.err.println(e.getMessage());
        }
        return (ArrayList<cliente>) listaClientes;
    }

}

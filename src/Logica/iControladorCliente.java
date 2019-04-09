/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;



/**
 *
 * @author jp
 */
public interface iControladorCliente {

    /**
     * Funcion altaCliente
     * Recibe un objeto de tipo cliente
     * @param clie
     * @return true or false
     * 
     */
    public abstract boolean altaCliente(cliente clie);
    public abstract boolean modificarCliente(cliente clieMod);
}

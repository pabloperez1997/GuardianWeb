/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;

/**
 *
 * @author jp
 */
public interface iControladorCliente {

   
    public abstract boolean emailValido(String email);
    public abstract ArrayList getClientes();
    public abstract boolean eliminarCliente(String cedula);
    public abstract String generarPassword();
    public abstract cliente getCliente(String cedula);
    public abstract boolean modificarCliente(cliente clieMod);
    public abstract boolean resetearPassword(String cedula);
    public abstract boolean altaCliente(cliente clienteNuevo);


}

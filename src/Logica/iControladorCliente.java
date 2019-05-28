/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public abstract boolean altaAnimal(mascota mascota);

    public abstract boolean eliminarAnimal(Long id);

    public abstract boolean modificarAnimal(mascota mascota);

    public abstract mascota getMascota(Long id);

    public abstract List<String> getRazasApiRest();

    public abstract boolean nuevaRaza(raza raza);

    public abstract List<String> reloadRazas();

    public abstract boolean eliminarRaza(String raza);

    public abstract boolean actualizarRazas();

    public abstract HashMap getClientesMascota();

    public abstract raza getRaza(String raza);

    public abstract ArrayList getMascotas();
}

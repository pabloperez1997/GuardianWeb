/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import ObjetosParaWeb.clienteWS;
import ObjetosParaWeb.mascotaWS;
import java.io.IOException;
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

    public abstract cliente getCliente(String correo);

    public abstract boolean modificarCliente(cliente clieMod);

    public abstract boolean resetearPassword(String correo);

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

    /////implementar con middleware a futuro
    public abstract List getMascotasWS();

    public abstract mascotaWS getMascotaWS(long id);

    public abstract boolean altaMascotaWS(mascotaWS mascota);

    public abstract boolean modificarMascotaWS(mascotaWS mascotaMod);

    public abstract List getClientesWS();

    public abstract clienteWS getClienteWS(String correo);

    public abstract boolean altaClienteWS(clienteWS cliente);

    public abstract boolean modificarClienteWS(clienteWS clienteMod);

    public abstract void setRutaFotoImagenesMascotaLevantar(String ruta);
    
    public abstract boolean altaClienteWeb(cliente clienteNuevo);
    
    public abstract List<mascota> obtenerMascotasCliente(cliente c);

    public abstract List<mascota> obtenerMascotas();

    public abstract List<String> obtenerRazas();

    public abstract boolean altaAnimal2(mascota m);

    public abstract mascota obtenerMascotaPorId(Long id);

    public abstract boolean ModificarMascota(mascota m);

    public abstract boolean activarusuario(String email, String pass);
    
    public abstract boolean existeMascota(String nombre, String telefono, String Cliente);
    
    public abstract boolean ModificarMascota2(mascota m) throws IOException ;
    
    public abstract String getRutaFotoImagenesWeb();

    
    public abstract void setRutaFotoImagenesWeb(String ruta);
    
    public abstract List<mascota> getMascotasClienteid(String idCliente);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import ListServicios.ListMascota;
import ListServicios.ListRazas;
import Logica.PublicarLista;
import Logica.cliente;
import Logica.fabricaElGuardian;
import Logica.mascota;
import java.io.IOException;
import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceContext;

/**
 *
 * @author jp
 */
@WebService(serviceName = "ServicioContCliente")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WSContCliente {

    @Resource
    private WebServiceContext context;
    private Endpoint endpoint = null;
    private String direccion;
    
    
    public WSContCliente(String direccionV) {
        this.direccion = direccionV;
    }

    public boolean despublicar() {
        try {
            endpoint.stop();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public void publicar() {
        try {
            endpoint = Endpoint.publish(direccion, this);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

////////////////////////////////////WEBMETHOD's/////////////////////////////
    
    
///////////////////////////////CLIENTE//////////////////////////////////////    
    @WebMethod
    public cliente obtenerCliente(@WebParam(name = "email") String email) {
        cliente c= new cliente();
        c= fabricaElGuardian.getInstance().getInstanceIControladorCliente().getCliente(email);
        return c;
    }
    
    @WebMethod
    public boolean modificarCliente(cliente c) {
        
         return (fabricaElGuardian.getInstance().getInstanceIControladorCliente().modificarCliente(c));
       
    }

    @WebMethod
    public ListRazas obtenerRazas() {
        ListRazas lr=new ListRazas();
        lr.setLista(fabricaElGuardian.getInstance().getInstanceIControladorCliente().obtenerRazas());
        return lr;
    }
    @WebMethod
    public ListMascota obtenerMascotasCliente(cliente c) {
        ListMascota lm = new ListMascota();
        lm.setMascotasLista(fabricaElGuardian.getInstance().getInstanceIControladorCliente().obtenerMascotasCliente(c));
        return lm;
    }
    
    @WebMethod
    public PublicarLista obtenerMascotas(){
        return new PublicarLista(fabricaElGuardian.getInstance().getInstanceIControladorCliente().obtenerMascotas());
    }
    
    
    @WebMethod
    public boolean ingresarAnimal(mascota m){
        return fabricaElGuardian.getInstance().getInstanceIControladorCliente().altaAnimal2(m);
    }
      @WebMethod
    public boolean eliminarAnimal(Long id){
        return fabricaElGuardian.getInstance().getInstanceIControladorCliente().eliminarAnimal(id);
    }
    
    @WebMethod
    public boolean modificarAnimal(mascota m) throws IOException{
        return fabricaElGuardian.getInstance().getInstanceIControladorCliente().ModificarMascota2(m);
    }
    
    @WebMethod
    public mascota obtenerMascotaPorId(Long id){
        return fabricaElGuardian.getInstance().getInstanceIControladorCliente().getMascota(id);
    }

    
     @WebMethod
    public boolean activarusuario(@WebParam(name = "email")String email,@WebParam(name = "pass")String pass){
        return  fabricaElGuardian.getInstance().getInstanceIControladorCliente().activarusuario(email,pass);
    }
//    
    @WebMethod
    public void AgregarCliente(@WebParam(name = "cedula") String cedula, @WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "correo") String correo, @WebParam(name = "telefono") String telefono, @WebParam(name = "direccion") String direccion, @WebParam(name = "hash") String hash) {

        cliente clie = new cliente();
        clie.setCedula(cedula);
        clie.setNombre(nombre);
        clie.setApellido(apellido);
        clie.setCorreo(correo);
        clie.setTel_cel(telefono);
        clie.setDireccion(direccion);
        clie.setPassword(hash);

        fabricaElGuardian.getInstance().getInstanceIControladorCliente().altaClienteWeb(clie);
    }
    
    @WebMethod
    public boolean existeMascota(@WebParam(name = "nombre")String nombre, @WebParam(name = "telefono")String telefono,@WebParam(name = "Cliente") String Cliente) {
        return fabricaElGuardian.getInstance().getInstanceIControladorCliente().existeMascota(nombre, telefono, Cliente);
    }

    @WebMethod
    public boolean resetearPassword(@WebParam(name = "email") String email) {
        return fabricaElGuardian.getInstance().getInstanceIControladorCliente().resetearPassword(email);
    }
    ////++++++//////////////+++++++++++++////////////+++++++++++///////////+++++/

    ///////////////////////////////ANIMAL////////////////////////////////////// 
    @WebMethod
    public ListMascota getMascotas(){
    
        return null;
    }
    
    ////++++++//////////////+++++++++++++////////////+++++++++++///////////+++++/
    
    ///////////////////////////////****////////////////////////////////////// 
    ////++++++//////////////+++++++++++++////////////+++++++++++///////////+++++/
    ///////////////////////////////****////////////////////////////////////// 
    ////++++++//////////////+++++++++++++////////////+++++++++++///////////+++++/
    ///////////////////////////////****////////////////////////////////////// 
    ////++++++//////////////+++++++++++++////////////+++++++++++///////////+++++/
    ///////////////////////////////****////////////////////////////////////// 
    ////++++++//////////////+++++++++++++////////////+++++++++++///////////+++++/
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import ListServicios.ListProductos;
import Logica.cliente;
import Logica.fabricaElGuardian;
import Logica.producto;
import javax.annotation.Resource;
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
@WebService(serviceName = "ServicioContVentas")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WSContVentas {
      @Resource
    private WebServiceContext context;
    private Endpoint endpoint = null;
    private String direccion;

    public WSContVentas(String direccionV) {
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
    
//////////////////////////////////////WEBMETHOD'S///////////////////////
    
    
    @WebMethod
    public ListProductos obtenerProductos(){
        return new ListProductos (fabricaElGuardian.getInstance().getInstanceIControladorVentas().ListarProductos());
    }
    
    @WebMethod
    public producto obtenerProducto(@WebParam(name = "Codigo") Long Codigo){
        return (fabricaElGuardian.getInstance().getInstanceIControladorVentas().ObtenerProducto(Codigo));
    }
    
    @WebMethod
    public ListProductos productosaVender(){
        return new ListProductos (fabricaElGuardian.getInstance().getInstanceIControladorVentas().productosaVender());
    }

    @WebMethod 
    public void setaVender(@WebParam(name = "codigo")Long codigo){
        fabricaElGuardian.getInstance().getInstanceIControladorVentas().setaVender(codigo);
    }

    @WebMethod 
    public void eliminaraVender(@WebParam(name = "codigo")Long codigo){
        fabricaElGuardian.getInstance().getInstanceIControladorVentas().eliminaraVender(codigo);
    }


    @WebMethod
    public void limpiarVenta(){
         fabricaElGuardian.getInstance().getInstanceIControladorVentas().limpiarVenta();
    }
    
    @WebMethod
    public boolean finalizarVenta(@WebParam(name = "cliente")cliente cliente){
         return fabricaElGuardian.getInstance().getInstanceIControladorVentas().finalizarVenta(cliente);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import ListServicios.ListMascotaWS;
import ListServicios.ListTipoBanio;
import ListServicios.ListTipoEsquila;
import ListServicios.ListTurnos;
import Logica.ControladorReservas;
import Logica.controladorServicios;
import Logica.fabricaElGuardian;
import Logica.utilidades;
import ObjetosParaWeb.mascotaWS;
import ObjetosParaWeb.reservaWS;
import java.util.List;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceContext;

/**
 *
 * @author jp
 */
@WebService(serviceName = "ServicioContReserva")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WSContReserva {
       @Resource
    private WebServiceContext context;
    private Endpoint endpoint = null;
    private String direccion;
    private ControladorReservas contRes = (ControladorReservas) fabricaElGuardian.getInstance().getInstanceIControladorReservas();
    private controladorServicios contSrv = (controladorServicios) fabricaElGuardian.getInstance().getInstanceIControladorServicios();

    public WSContReserva(String direccionV) {
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

            System.out.println("Publique servicio: WSContReserva: " + direccion);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    ////////////////////////////////////WEBMETHOD's/////////////////////////////

    ////////////////////////////////////abm-Reserva///////////////////////////////////
    @WebMethod
    public boolean altaReserva(@WebParam(name = "reserva") reservaWS reserva) {
        return contRes.altaReservaWS(reserva);
    }

    @WebMethod
    public boolean eliminarReserva(@WebParam(name = "idReserva") long idReserva) {
        return contRes.eliminarReservaWS(idReserva);
    }

    @WebMethod
    public boolean modificarReserva(@WebParam(name = "reserva") reservaWS reserva) {
        return contRes.modificarReservaWS(reserva);
    }
    ////++++++//////////////+++++++++++++////////////+++++++++++///////////+++++/

    /////////////////////////////gestion turnos/////////////////////////////////////////
    @WebMethod

    public ListTurnos getTurnosDisponibles(@WebParam(name = "fecha") String fecha) {

        //   conArr.setLista(contRes.cargarTurnosDisponiblesList(utilidades.fechaDate(fecha, null)));
        ListTurnos lt = new ListTurnos();
        lt.setListTurnosWS(contRes.cargarTurnosDisponiblesList(utilidades.fechaDate(fecha, null)));

        return lt;

    }

    ////++++++//////////////+++++++++++++////////////+++++++++++///////////+++++/
    ///////////////////////////////Tipo de Servicio////////////////////////////////////
    @WebMethod
    //  @WebResult(name = "nuevoContiene")
    public ListTipoEsquila getTipoEsquilas() {
        System.err.println("tipoEsquilas");

        return contSrv.getListaTipoEsquilaWS();

    }

    @WebMethod
    //  @WebResult(name="nuevoContiene")
    public ListTipoBanio getTipoBanio() {
        System.err.println("tipobanio");

        return contSrv.getListaTipoBanioWS();

    }

    @WebMethod
    @WebResult(name = "precio")
    public float getPrecioPaseo() {
        return contSrv.getPrecioPaseo();
    }
    ////++++++//////////////+++++++++++++////////////+++++++++++///////////+++++/

    @WebMethod
    public ListMascotaWS getMascotasCliente(@WebParam(name = "clienteCorreo") String clienteCorreo) {
        System.out.println(clienteCorreo);
        ListMascotaWS nuevaLista = new ListMascotaWS();
        List<mascotaWS> mascotasCliente = contRes.getMascotasCliente(clienteCorreo);
        nuevaLista.setMascotas(mascotasCliente);
        return nuevaLista;
    }
}

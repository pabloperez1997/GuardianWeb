/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Persistencia.ventaPersistencia;
import java.util.Iterator;

/**
 *
 * @author jp
 */
import Persistencia.persistencia;
import Persistencia.productoPersistencia;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class ControladorVentas implements iControladorVentas {

    ventaPersistencia ventapersist = ventaPersistencia.getventaPersisInstace();
    List<producto> productos;
    List<producto> avender = new ArrayList<>();
    private String rutaGuardarimgProductos="C:/Users/PabloP/Documents/NetBeansProjects/GuardianWeb/web/img/imgprod/";

    @Override
    public List<producto> productosaVender() {
        return avender;
    }

    @Override
    public void setaVender(Long codigo) {
        producto p = ObtenerProducto(codigo);
        this.avender.add(p);
    }

    @Override
    public void eliminaraVender(Long codigo) {
        producto p = ObtenerProducto(codigo);
        this.avender.remove(p);
    }

    @Override
    public void limpiarVenta() {
        this.avender.clear();
    }

    @Override
    public List<producto> obtenerProductos() {
        return productos;
    }

    @Override
    public void cargarproductos() {
        productos = ventapersist.getListaproductos();
        for (int x = productos.size() - 1; x >= 0; x--) {
            producto produ = (producto) productos.get(x);
            produ.setCantidad(1);
        }
    }

    public producto obtenerProductoporCodigo(String codigo) {
        producto p = new producto();
        Iterator it = this.productos.iterator();
        while (it.hasNext()) {
            producto p2 = (producto) it.next();
            if (p2.getCodigo().equals(codigo)) {
                p = p2;
                break;
            }
        }
        return p;
    }

    public float calcularpreciototal(List<producto> listaventa) {
        float preciototal = 0;
        Iterator it = listaventa.iterator();
        while (it.hasNext()) {
            producto p = (producto) it.next();
            preciototal = preciototal + (p.getPrecio() * p.getCantidad());
        }
        return preciototal;
    }
    private productoPersistencia prodPer = new productoPersistencia();
    EntityManager em = persistencia.getInstance().getEm();

    @Override
    public List<producto> ListarProductos() {

        List<producto> lista = null;
        lista = prodPer.ObtenerListaProductos();

        return lista;
    }

    @Override
    public boolean EliminarProducto(Long codigo) {

        boolean ok = false;
        producto p = this.ObtenerProducto(codigo);
        ok = persistencia.getInstance().eliminar(p);

        return ok;
    }

    @Override
    public boolean ModificarProducto(producto p) {

        boolean ok = false;
        ok = persistencia.getInstance().modificar(p);

        return ok;
    }

    @Override
    public producto ObtenerProducto(Long codigo) {

        producto p = prodPer.ObtenerProducto(codigo);
        return p;

    }

    public boolean AltaVenta(List<producto> listaventa) {
        float preciototal = this.calcularpreciototal(listaventa);
        int cantidad = 0;
        for (int x = listaventa.size() - 1; x >= 0; x--) {
            producto produ = (producto) listaventa.get(x);
            cantidad = cantidad + produ.getCantidad();
        }
        detalleVenta dv = new detalleVenta();
        dv.setCantidad(cantidad);
        dv.setListaProducto(listaventa);
        dv.setPrecioTotalProductos(preciototal);
        Persistencia.persistencia.getInstance().persis(dv);
        Date fecha = new Date();
        venta v = new venta();
        v.setFecha(fecha);
        v.setDetalles(dv);
        boolean ok = Persistencia.persistencia.getInstance().persis(v);
        return ok;
    }

    public String getRutaGuardarimgProductos() {
        return rutaGuardarimgProductos;
    }

    public void setRutaGuardarimgProductos(String rutaGuardarimgProductos) {
        this.rutaGuardarimgProductos = rutaGuardarimgProductos;
    }
    
    @Override
    public boolean finalizarVenta(cliente c){
        
        try {
            GenerarPDF gpdf= new GenerarPDF();
            gpdf.createPdf(c);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ControladorVentas.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

}

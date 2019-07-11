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
import java.nio.file.Path;
import java.nio.file.Paths;
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
     private String ruta =Paths.get(System.getProperty("user.dir")).getParent().getParent().toString();
    private String rutaGuardarimgProductos=ruta+"/GuardianWeb/GuardianWeb/web/img/imgprod/";
    private String rutaGuardarimgProductosSrv=System.getProperty("user.dir")+"/ImagenesProducto/"; 
    private String rutaGuardarimgGaleria=ruta+"/GuardianWeb/GuardianWeb/web/img/galeria/";

    @Override
    public List<producto> productosaVender() {
        return avender;
    }
    
     @Override
    public String getRutaGuardarimgGaleria() {
        return rutaGuardarimgGaleria;
    }

    @Override
    public void setRutaGuardarimgGaleria(String rutagaleria) {
        this.rutaGuardarimgGaleria = rutagaleria;
    }


    @Override
    public String getRutaGuardarimgProductosSrv() {
        return rutaGuardarimgProductosSrv;
    }

    @Override
    public void setRutaGuardarimgProductosSrv(String rutaGuardarimgProductosSrv) {
        this.rutaGuardarimgProductosSrv = rutaGuardarimgProductosSrv;
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
           // produ.setCantidad(1);
        }
    }

    @Override
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

    @Override
    public boolean AltaVenta(List<detalleVenta> listaventa, cliente c) {
         venta v = new venta();
         Date fecha = new Date();
         v.setFecha(fecha);
       
        for (int x = listaventa.size() - 1; x >= 0; x--) {
            
            detalleVenta dv = (detalleVenta) listaventa.get(x);
            //dv.setCantidad(produ.getCantidad());
            dv.setPrecioTotalProductos(dv.getProducto().getPrecio()*dv.getCantidad());
            v.getDetalles().add(dv);
            producto produ= dv.getProducto();
            
            int stock=produ.getCantidad()-dv.getCantidad();
            produ.setCantidad(stock);
            
            if(stock==0)
            produ.setDisponible(false);
            
            
            Persistencia.persistencia.getInstance().persis(dv);
            Persistencia.productoPersistencia.getInstance().modificar(produ);
        }
      
        boolean ok = Persistencia.persistencia.getInstance().persis(v);
        c.setVenta(v);
        
        try {
            GenerarPDF gpdf= new GenerarPDF(c);
            gpdf.createPdf();
            c.setVenta(null);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ControladorVentas.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }

    @Override
    public String getRutaGuardarimgProductos() {
        return rutaGuardarimgProductos;
    }

    @Override
    public void setRutaGuardarimgProductos(String rutaGuardarimgProductos) {
        this.rutaGuardarimgProductos = rutaGuardarimgProductos;
    }
    
    @Override
    public boolean finalizarVenta(cliente c){
        
        try {
//            List<producto> prodsventa = new ArrayList<>();
            List<detalleVenta> Productosavender= (List<detalleVenta>) c.getCompra().getDetalles();

           
            GenerarPDF gpdf= new GenerarPDF(c);
            gpdf.createPdf();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ControladorVentas.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

}

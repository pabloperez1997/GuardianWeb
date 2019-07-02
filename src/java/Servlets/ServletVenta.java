/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.cliente;
import Logica.configuracion;
import Logica.producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.Cliente;
import servicios.DetalleVenta;
import servicios.Producto;
import servicios.WSContVentas;
import servicios.ServicioContVentas;
import servicios.Venta;

/**
 *
 * @author PabloP
 */
//
//
@WebServlet("/venta")
public class ServletVenta extends HttpServlet {

    private WSContVentas port;
    configuracion conf = new configuracion();
   // List<Producto> Productosavender= new ArrayList<>();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        ServletContext context;
        context = request.getServletContext();
        String ruta = context.getResource("").getPath();
        URL url = new URL("http://" + conf.obtenerServer("servidor", ruta) + conf.leerProp("sVentas", ruta));
        ServicioContVentas webService = new ServicioContVentas(url);
        this.port = webService.getWSContVentasPort();
        
       
        
   request.getRequestDispatcher("/Vistas/Venta.jsp").forward(request, response);
        
        
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
   Cliente c= (Cliente) request.getSession().getAttribute("usuario_logueado");
 
    List<DetalleVenta> Productosavender= (List<DetalleVenta>) c.getCompra().getDetalles();
    List<Producto> prodsventa = new ArrayList<>();
               
            Iterator it1 = Productosavender.iterator();
            while (it1.hasNext()){
                DetalleVenta dv=(DetalleVenta) it1.next();
                Producto pr=dv.getProducto();
                prodsventa.add(pr);
                
            }
   
   
   request.getSession().setAttribute("CantidadProdVend", c.getCompra().getDetalles().size());
   request.setAttribute("ProdsVenta", prodsventa);
   
   request.getRequestDispatcher("/Vistas/Venta.jsp").forward(request, response);
  
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context;
        context = request.getServletContext();
        String ruta = context.getResource("").getPath();
        URL url = new URL("http://" + conf.obtenerServer("servidor", ruta) + conf.leerProp("sVentas", ruta));
        ServicioContVentas webService = new ServicioContVentas(url);
        this.port = webService.getWSContVentasPort();
        
        
        String cod = request.getParameter("codigoprod");
        
        if(cod!=null){
            Long codigoprod = Long.parseLong(cod);
            Producto p = port.obtenerProducto(codigoprod);
            Cliente c= (Cliente) request.getSession().getAttribute("usuario_logueado");
            
            if (c == null) {
            request.setAttribute("mensaje", "No existe una sesion en el sistema, "
                    + "Debe iniciar sesion para poder comprar un producto");
            request.getRequestDispatcher("/Vistas/Mensaje_Recibido.jsp").forward(request, response);
            }
           
                
            
            if(c.getCompra()==null){
                Venta v= new Venta();
                DetalleVenta dv= new DetalleVenta();
                //dv.setCantidad(0);
                dv.setProducto(p);
                List<Producto> listPr= new ArrayList<>();
                c.setCompra(v);
                v.getDetalles().add(dv); 
            }
            else{
                
            }
            
            boolean existelista= false;
            List<DetalleVenta> Productosavender= (List<DetalleVenta>) c.getCompra().getDetalles();
            Iterator it = Productosavender.iterator();
            while (it.hasNext()){
                DetalleVenta dv=(DetalleVenta) it.next();
                if(dv.getProducto().getCodigo().compareTo(codigoprod)==0)
                existelista=true;
                
            }
            if(!existelista){DetalleVenta dv= new DetalleVenta();
                //dv.setCantidad(p.getCantidad());
                dv.setProducto(p);
                c.getCompra().getDetalles().add(dv);}
              // c.getCompra().getDetalles().add(p);
           
              List<Producto> prodsventa = new ArrayList<>();
               
            Iterator it1 = Productosavender.iterator();
            while (it1.hasNext()){
                DetalleVenta dv=(DetalleVenta) it1.next();
                Producto pr=dv.getProducto();
                prodsventa.add(pr);
                
            }
            
            
            request.getSession().setAttribute("CantidadProdVend", c.getCompra().getDetalles().size());
            request.setAttribute("ProdsVenta", prodsventa);
        }

       String codelim = request.getParameter("eliminarprod");
        
        if(codelim!=null){
         Long eliminarprod = Long.parseLong(codelim);
            Producto p = port.obtenerProducto(eliminarprod);
            Cliente c= (Cliente) request.getSession().getAttribute("usuario_logueado");
                               
            //c.getCompra().getDetalles().getListaProducto().remove(p);
             List<DetalleVenta> Productosavender= (List<DetalleVenta>) c.getCompra().getDetalles();
            Iterator it = Productosavender.iterator();
            while (it.hasNext()){
                DetalleVenta dv=(DetalleVenta) it.next();
                if(dv.getProducto().getCodigo().compareTo(eliminarprod)==0)
                it.remove();
                
            }
             List<Producto> prodsventa = new ArrayList<>();
               
            Iterator it1 = Productosavender.iterator();
            while (it1.hasNext()){
                DetalleVenta dv=(DetalleVenta) it1.next();
                Producto pr=dv.getProducto();
                prodsventa.add(pr);
                
            }
            
            
            request.getSession().setAttribute("CantidadProdVend", c.getCompra().getDetalles().size());
            request.setAttribute("ProdsVenta", prodsventa);
        }
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Vistas/Venta.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

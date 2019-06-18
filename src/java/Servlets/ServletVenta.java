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
        
       
        
       RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/Venta.jsp");
       dispatcher.forward(request, response);
        
        
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
        Long codigoprod = Long.parseLong(cod);
        if(codigoprod!=null){
            Producto p = port.obtenerProducto(codigoprod);
            Cliente c= (Cliente) request.getSession().getAttribute("usuario_logueado");
            
            if(c.getCompra()==null){
                Venta v= new Venta();
                DetalleVenta dv= new DetalleVenta();
                List<Producto> listPr= new ArrayList<>();
                c.setCompra(v);
                v.setDetalles(dv); 
            }
            
            List<Producto> Productosavender= (List<Producto>) c.getCompra().getDetalles().getListaProducto();
            c.getCompra().getDetalles().getListaProducto().add(p);
            
            
            request.getSession().setAttribute("CantidadProdVend", c.getCompra().getDetalles().getListaProducto().size());
            request.setAttribute("ProdsVenta", c.getCompra().getDetalles().getListaProducto());
        }

       String codelim = request.getParameter("codigoprod");
       Long eliminarprod = Long.parseLong(codelim);
        
        if(eliminarprod!=null){
        
            Producto p = port.obtenerProducto(eliminarprod);
            Cliente c= (Cliente) request.getSession().getAttribute("usuario_logueado");
                               
            //c.getCompra().getDetalles().getListaProducto().remove(p);
            
            List<Producto> Productosavender= (List<Producto>) c.getCompra().getDetalles().getListaProducto();
            
            Iterator it = Productosavender.iterator();
            int i;
            while (it.hasNext()){
                Producto pr=(Producto) it.next();
                if(pr.getCodigo().compareTo(eliminarprod)==0)
                it.remove();
                
            }
            request.getSession().setAttribute("CantidadProdVend", Productosavender.size());
            request.setAttribute("ProdsVenta", Productosavender);
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

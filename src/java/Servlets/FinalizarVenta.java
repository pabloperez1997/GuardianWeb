/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import clases.configuracion;
import java.io.IOException;
import java.net.URL;
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
import servicios.ServicioContVentas;
import servicios.WSContVentas;

/**
 *
 * @author PabloP
 */
@WebServlet("/finalizarventa")
public class FinalizarVenta extends HttpServlet {
    private WSContVentas port;
    configuracion conf = new configuracion();
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
//        response.setContentType("text/html;charset=UTF-8");
//        ServletContext context;
//        context = request.getServletContext();
//        String ruta = context.getResource("").getPath();
//        URL url = new URL("http://" + conf.obtenerServer("servidor", ruta) + conf.leerProp("sVentas", ruta));
//        ServicioContVentas webService = new ServicioContVentas(url);
//        this.port = webService.getWSContVentasPort();
//        
//        Cliente c= (Cliente) request.getSession().getAttribute("usuario_logueado");
//        
//        
//         List<DetalleVenta> Productosavender= (List<DetalleVenta>) c.getCompra().getDetalles();
//            Iterator it = Productosavender.iterator();
//            while (it.hasNext()){
//                DetalleVenta dv=(DetalleVenta) it.next();
//                Producto pr = dv.getProducto();
//                pr.setCantidad(Integer.parseInt(request.getParameter(pr.getCodigo().toString())));
//                dv.setCantidad(Integer.parseInt(request.getParameter(pr.getCodigo().toString())));
//                               
//            }
//        
//        //String parameter = request.getParameter("8");
////        List<Producto> Productosavender= (List<Producto>) c.getCompra().getDetalles().getListaProducto();
////            Iterator it = Productosavender.iterator();
////            int i;
////            while (it.hasNext()){
////               Producto pr=(Producto) it.next();
////               int cantidad= Integer.parseInt(request.getParameter(pr.getCodigo().toString()));
////               pr.setCantidad(cantidad);
////                
////            }
//            
//        this.port.finalizarVenta(c);
//          
//        
    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
         response.setContentType("text/html;charset=UTF-8");
     
        ServletContext context;
        context = request.getServletContext();
        String ruta = context.getResource("").getPath();
        URL url = new URL("http://" + conf.obtenerServer("servidor", ruta) + conf.leerProp("sVentas", ruta));
        ServicioContVentas webService = new ServicioContVentas(url);
        this.port = webService.getWSContVentasPort();
        
        Cliente c= (Cliente) request.getSession().getAttribute("usuario_logueado");
        
        
         List<DetalleVenta> Productosavender= (List<DetalleVenta>) c.getCompra().getDetalles();
            Iterator it = Productosavender.iterator();
            while (it.hasNext()){
                DetalleVenta dv=(DetalleVenta) it.next();
                Producto pr = dv.getProducto();
                //pr.setCantidad(Integer.parseInt(request.getParameter(pr.getCodigo().toString())));
                dv.setCantidad(Integer.parseInt(request.getParameter(pr.getCodigo().toString())));
                               
            }
       
        this.port.finalizarVenta(c);
        c.setCompra(null);
          
        
        request.getSession().setAttribute("CantidadProdVend", null);
        //request.setAttribute("ProdsVenta", prodsventa);
        request.setAttribute("idVenta",c.getNombre()+c.getApellido());
        request.setAttribute("mensaje", "Compra Realizada con Éxito, Debe asistir a nuestro "
                + "local para retirar los productos y realizar el pago, Haciendo click en el siguiente enlace puede descargar los detalles de su compra: ");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Vistas/Mensaje_Recibido.jsp");
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

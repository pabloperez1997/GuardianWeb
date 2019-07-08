/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;


import clases.Propiedades;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.ListProductos;
import servicios.Producto;
import servicios.ServicioContVentas;
import servicios.WSContVentas;
/**
 *
 * @author PabloP
 */
@WebServlet("/ver-productos")
public class ServletProductos extends HttpServlet {
    Propiedades propiedades = Propiedades.getInstance();
    private WSContVentas port;

    //List<Producto> Productosavender = new ArrayList<>();

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
        URL url = new URL(propiedades.getServicioVenta());
        ServicioContVentas webService = new ServicioContVentas(url);
        this.port = webService.getWSContVentasPort();

        List<Producto> productos = this.port.obtenerProductos().getLista();
        request.setAttribute("Productos", productos);
        
       RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/VerProductos.jsp");
       dispatcher.forward(request, response);
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
        
//     String codigoprod = request.getParameter("codigoprod");
//     Producto p = port.obtenerProducto(codigoprod);
//     
//        Productosavender.add(p);
//        request.setAttribute("CantidadProdVend", Productosavender.size());
//        request.getSession().setAttribute("ProdsVenta", this.Productosavender);
//   
        processRequest(request, response);
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

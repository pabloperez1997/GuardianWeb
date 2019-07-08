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
import java.util.Iterator;
import java.util.List;
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

    Propiedades propiedades = Propiedades.getInstance();
    private WSContVentas port;

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
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context;
        context = request.getServletContext();
        String ruta = context.getResource("").getPath();
        URL url = new URL(propiedades.getServicioUsuario());
        ServicioContVentas webService = new ServicioContVentas(url);
        this.port = webService.getWSContVentasPort();

        Cliente c = (Cliente) request.getSession().getAttribute("usuario_logueado");

        String parameter = request.getParameter("8");
        List<Producto> Productosavender = new ArrayList<>();
        for (Object obj : c.getCompra().getDetalles()) {
            Producto producto = ((DetalleVenta) obj).getProducto();
            Productosavender.add(producto);
        }

        Iterator it = Productosavender.iterator();
        int i;
        while (it.hasNext()) {
            Producto pr = (Producto) it.next();
            int cantidad = Integer.parseInt(request.getParameter(pr.getCodigo().toString()));
            pr.setCantidad(cantidad);

        }

        this.port.finalizarVenta(c);

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
        URL url = new URL(propiedades.getServicioVenta());
        ServicioContVentas webService = new ServicioContVentas(url);
        this.port = webService.getWSContVentasPort();

        Cliente c = (Cliente) request.getSession().getAttribute("usuario_logueado");

        String parameter = request.getParameter("8");
        List<Producto> Productosavender = new ArrayList<>();// (List<Producto>) .getListaProducto();
        List<DetalleVenta> detalles = (List) c.getCompra().getDetalles();
        for (DetalleVenta dtv : detalles) {
            Productosavender.add((Producto) dtv.getProducto());

        }

        Iterator it = Productosavender.iterator();
        while (it.hasNext()) {
            Producto pr = (Producto) it.next();
            int cantidad = Integer.parseInt(request.getParameter(pr.getCodigo().toString()));
            pr.setCantidad(cantidad);

        }

        this.port.finalizarVenta(c);

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

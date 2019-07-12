/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import clases.configuracion;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.Cliente;
import servicios.Mascota;
import servicios.ServicioContCliente;
import servicios.WSContCliente;

/**
 *
 * @author gabri
 */
@WebServlet(name = "ServletEliminarAnimal", urlPatterns = {"/ServletEliminarAnimal"})
public class ServletEliminarAnimal extends HttpServlet {
    private WSContCliente port;
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
ServletContext context;
       context = request.getServletContext();
        String ruta = context.getResource("").getPath();
         URL url = new URL("http://" + conf.obtenerServer("servidor", ruta) + conf.leerProp("sConsultaUsuario", ruta));
        ServicioContCliente webService = new ServicioContCliente(url);
        this.port = webService.getWSContClientePort();
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
        processRequest(request, response);
        
         String sid = request.getParameter("IdMascota");
         Long id=Long.parseLong(sid);
         boolean funciono = this.port.eliminarAnimal(id);
         if(funciono){
              List<String> razas = (List<String>) this.port.obtenerRazas().getLista();
        request.setAttribute("Razas", razas);
        List<Mascota> mascotascliente = (List<Mascota>) this.port.obtenerMascotasCliente((Cliente) request.getSession().getAttribute("usuario_logueado")).getMascotasLista();
        request.setAttribute("MascotasCliente", mascotascliente);
             request.getRequestDispatcher("Vistas/AltaAnimal.jsp").forward(request, response);
         }else{
             request.getRequestDispatcher("Vistas/Inicio.jsp").forward(request, response);
         }
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

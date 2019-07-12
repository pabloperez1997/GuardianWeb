/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import clases.configuracion;
import clases.codificador;
import java.io.IOException;
import java.net.URL;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.Cliente;
import servicios.ServicioContCliente;
import servicios.WSContCliente;


/**
 *
 * @author gabri
 */
@WebServlet(name = "ServletValidarCuenta", urlPatterns = {"/ServletValidarCuenta"})
public class ServletValidarCuenta extends HttpServlet {
configuracion conf = new configuracion();
private WSContCliente port;
boolean validado=false;
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
        request.setAttribute("Validado", validado);
        if(request.getParameter("email") == null){
request.getRequestDispatcher("Vistas/ValidarCuenta.jsp").forward(request, response);
        }
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
        ServletContext context;
        context = request.getServletContext();
        String ruta = context.getResource("").getPath();
         URL url = new URL("http://" + conf.obtenerServer("servidor", ruta) + conf.leerProp("sConsultaUsuario", ruta));
        ServicioContCliente webService = new ServicioContCliente(url);
        this.port = webService.getWSContClientePort();
        String email = request.getParameter("email");
        String token = request.getParameter("token");
        String password = request.getParameter("pass");
        codificador a = new codificador();
        String pass = a.sha1(password);
        Cliente c=this.port.obtenerCliente(email);
        if(c.getToken().equals(token)){
            validado=this.port.activarusuario(email,pass);
            request.setAttribute("Validado", validado);
        }
request.getRequestDispatcher("Vistas/ValidarCuenta.jsp").forward(request, response);
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

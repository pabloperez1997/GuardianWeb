/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.cliente;
import Logica.configuracion;
import clases.codificador;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import servicios.Cliente;
import servicios.PublicadorConsultarUsuario;
import servicios.PublicadorConsultarUsuarioService;

/**
 *
 * @author PabloP
 */
@WebServlet("/registrarse")
public class Registrarse extends HttpServlet {
    
   
        
    private PublicadorConsultarUsuario port;
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
        
       
        Cliente clienteLog = (Cliente) request.getSession().getAttribute("usuario_logueado");
       
        if (clienteLog == null) {
            request.getRequestDispatcher("Vistas/Registrarse.jsp").forward(request, response);
        } else {
            request.setAttribute("mensaje", "Ya existe una sesion en el sistema");
            request.getRequestDispatcher("Vistas/Inicio.jsp").forward(request, response);
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
       
        ServletContext context;
        context = request.getServletContext();
        String ruta = context.getResource("").getPath();
        URL url = new URL("http://" + conf.obtenerServer("servidor", ruta) + conf.leerProp("sConsultaUsuario", ruta));
        PublicadorConsultarUsuarioService webService = new PublicadorConsultarUsuarioService(url);
        this.port = webService.getPublicadorConsultarUsuarioPort();
        
        
        cliente usuLogeado = (cliente) request.getSession().getAttribute("usuario_logueado");
//        URL url = new URL("http://" + conf.obtenerServer("servidor", ruta) + conf.leerProp("sConsultaUsuario", ruta));
       
        codificador a = new codificador();
        Boolean ok = false;
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("email");
        String pass = request.getParameter("pass");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
      

//        if (!pass.equals(pass2)) {
//            request.setAttribute("malPass", "Sus contraseñas no coinciden");
//            request.getRequestDispatcher("/Vistas/altaUsuario.jsp").forward(request, response);
//            return;
//        }
  

        String hash = a.sha1(pass);
        
        port.agregarCliente(cedula, nombre, apellido, correo, telefono, direccion, hash);


//            if (ok) {
                request.setAttribute("mensaje", "Se registro exitosamente");
                Cliente c = port.obtenerCliente(cedula);
                request.getSession().setAttribute("usuario_logueado", c);
//            } else {
//                request.setAttribute("mensaje", "Error al dar registrar este usuario");
////            }
            request.getRequestDispatcher("/Vistas/Inicio.jsp").forward(request, response);
        
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

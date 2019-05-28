/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.cliente;
import Logica.configuracion;
import clases.codificador;
import clases.EstadoSesion;
import java.io.IOException;
import java.net.URL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servicios.PublicadorConsultarUsuario;
import servicios.Cliente;
import servicios.PublicadorConsultarUsuarioService;

/**
 *
 * @author PabloP
 */
@WebServlet("/iniciar-sesion")
public class IniciarSesion extends HttpServlet {

     private PublicadorConsultarUsuario port;
     configuracion conf = new configuracion();
//    private PublicadorConsultarUsuario port;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    
//    public static Usuario getUsuarioSesion(HttpServletRequest request) {
//        return (Usuario) request.getSession().getAttribute("usuario_logueado");
//
//    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context;
        context = request.getServletContext();
        String ruta = context.getResource("").getPath();
        URL url = new URL("http://" + conf.obtenerServer("servidor", ruta) + conf.leerProp("sConsultaUsuario", ruta));
        PublicadorConsultarUsuarioService webService = new PublicadorConsultarUsuarioService(url);
        this.port = webService.getPublicadorConsultarUsuarioPort();
        
        Cliente usuLogeado = (Cliente) request.getSession().getAttribute("usuario_logueado");
        if (usuLogeado == null) {
            request.getSession().setAttribute("estado_sesion", null);
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/Login.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("mensaje", "Ya existe una sesion en el sistema");
            request.getRequestDispatcher("/Vistas/Mensaje_Recibido.jsp").forward(request, response);
        }
        
        request.getRequestDispatcher("Vistas/Login.jsp").forward(request, response);
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
        HttpSession objSesion = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        EstadoSesion nuevoEstado = null;
        codificador a = new codificador();
        Cliente cliente = null;
        
        boolean recordarme = request.getParameter("Recordarme") != null;
        try {
            cliente = this.port.obtenerCliente(login);
        } catch (Exception e) {
                nuevoEstado = EstadoSesion.LOGIN_INCORRECTO;
                objSesion.setAttribute("estado_sesion", nuevoEstado);
                request.getRequestDispatcher("Vistas/Login.jsp").forward(request, response);
            }
            if (cliente != null) {
                String hash = a.sha1(password);
                if (cliente.getPassword().compareTo(hash) != 0) {
                    request.setAttribute("errorContrasenia", "Contraseña Incorrecta.");
                    nuevoEstado = EstadoSesion.CONTRASENIA_INCORRECTA;
                    objSesion.setAttribute("estado_sesion", nuevoEstado);
                    request.getRequestDispatcher("Vistas/Login.jsp").forward(request, response);
                } else {
                    nuevoEstado = EstadoSesion.LOGIN_CORRECTO;
                    request.getSession().setAttribute("usuario_logueado", cliente);// setea el usuario logueado
                    if (recordarme) {
                        Cookie cookieSesion = new Cookie("cookieSesion", cliente.getCorreo());
                        cookieSesion.setMaxAge(60 * 60 * 24);
                        cookieSesion.setPath("/");
                        response.addCookie(cookieSesion);
                    }
                }

            }
            objSesion.setAttribute("estado_sesion", nuevoEstado);
            request.getRequestDispatcher("Vistas/Inicio.jsp").forward(request, response);
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

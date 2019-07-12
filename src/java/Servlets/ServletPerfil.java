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
import javax.servlet.http.HttpSession;
import servicios.Cliente;
import servicios.ServicioContCliente;
import servicios.WSContCliente;

/**
 *
 * @author PabloP
 */
@WebServlet("/perfil")
public class ServletPerfil extends HttpServlet {
    
    private WSContCliente port;
    private configuracion conf = new configuracion();

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
        
        Cliente cliente = null;
        HttpSession session = request.getSession();
        if (session.getAttribute("usuario_logueado") == null) {
           request.getRequestDispatcher("Vistas/Inicio.jsp").forward(request, response);
           
        }
        else{
            request.getRequestDispatcher("Vistas/Perfil.jsp").forward(request, response);
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
        ServicioContCliente webService = new ServicioContCliente(url);
        this.port = webService.getWSContClientePort();
        
        
        Cliente usuLogeado = (Cliente) request.getSession().getAttribute("usuario_logueado");
//        URL url = new URL("http://" + conf.obtenerServer("servidor", ruta) + conf.leerProp("sConsultaUsuario", ruta));
       
        codificador a = new codificador();
        Boolean ok = false;
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        
        usuLogeado.setNombre(nombre);
        usuLogeado.setApellido(apellido);
        usuLogeado.setCedula(cedula);;
        usuLogeado.getTelCel();
        usuLogeado.setDireccion(direccion);
        
      

//        if (!pass.equals(pass2)) {
//            request.setAttribute("malPass", "Sus contraseñas no coinciden");
//            request.getRequestDispatcher("/Vistas/altaUsuario.jsp").forward(request, response);
//            return;
//        }
        
        boolean ok1;
        ok1 = port.modificarCliente(usuLogeado);

            if (ok1) {
            request.setAttribute("mensaje", "Perfil Modificado con Exito");
            request.getRequestDispatcher("/Vistas/Mensaje_Recibido.jsp").forward(request, response);
            }
        request.setAttribute("mensaje", "Ocurrio algun error, reintente luego");
        request.getRequestDispatcher("/Vistas/Mensaje_Recibido.jsp").forward(request, response);
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

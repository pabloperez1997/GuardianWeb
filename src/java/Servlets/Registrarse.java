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
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.Cliente;
import servicios.ListMascota;
import servicios.Mascota;
import servicios.ServicioContCliente;
import servicios.WSContCliente;


/**
 *
 * @author PabloP
 */
@WebServlet("/registrarse")
public class Registrarse extends HttpServlet {
    
   
        
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
       List<Mascota> mascotacliente = this.port.obtenerMascotaCliente().getMascotasLista();
       request.setAttribute("MascotasCliente", mascotacliente);
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
       List<Mascota> mascotacliente = this.port.obtenerMascotaCliente().getMascotasLista();
        
        
        
        Cliente usuLogeado = (Cliente) request.getSession().getAttribute("usuario_logueado");
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
        ListMascota mascotas=new ListMascota();
     if(mascotacliente.size() > 0){
         Iterator it=mascotacliente.iterator();
         while(it.hasNext()){
             Mascota m=(Mascota) it.next();
             mascotas.getMascotasLista().add(m);
         }
         
     }
     
     

//        if (!pass.equals(pass2)) {
//            request.setAttribute("malPass", "Sus contraseñas no coinciden");
//            request.getRequestDispatcher("/Vistas/altaUsuario.jsp").forward(request, response);
//            return;
//        }
  

        String hash = a.sha1(pass);
        
        ok =port.agregarCliente(cedula, nombre, apellido, correo, telefono, direccion, hash,mascotas);

          if (ok) {
                request.setAttribute("mensaje", "Se registro exitosamente");
                Cliente c = port.obtenerCliente(correo);
                request.getSession().setAttribute("usuario_logueado", c);
          } else {
               request.setAttribute("mensaje", "Error al dar registrar este usuario");
           }
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.configuracion;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import servicios.Cliente;
import servicios.IOException_Exception;
import servicios.Mascota;
import servicios.Raza;
import servicios.ServicioContCliente;
import servicios.WSContCliente;

/**
 *
 * @author gabri
 */
@WebServlet(name = "ServletModificarAnimal", urlPatterns = {"/ServletModificarAnimal"})
@MultipartConfig
public class ServletModificarAnimal extends HttpServlet {
    private WSContCliente port;
     configuracion conf = new configuracion();
    Mascota mascotacliente;
    boolean funciono;
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
        List<String> razas = (List<String>) this.port.obtenerRazas().getLista();
        request.setAttribute("Razas", razas);
        String sid = request.getParameter("IdMascota");
        if(sid != null){
        Long id=Long.parseLong(sid);
        mascotacliente=this.port.obtenerMascotaPorId(id);
        
        request.setAttribute("MascotaCliente", mascotacliente);
        
        request.getRequestDispatcher("Vistas/ModificarAnimal.jsp").forward(request, response);
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
         Part filePart = request.getPart("file"); // obtengo <input type="file" name="file">
       String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
               byte[] targetArray;
    try (InputStream fileContent = filePart.getInputStream()) {
        targetArray = new byte[fileContent.available()];
        fileContent.read(targetArray);
    }
        String nombre = request.getParameter("nombre");
        String raza = request.getParameter("raza");
        Raza r = new Raza();
        r.setRaza(raza);
        String desc = request.getParameter("descripcion");
        if(!nombre.equals("")){
        mascotacliente.setNombre(nombre);
        }
        mascotacliente.setRaza(r);
        if(!desc.equals("")){
        mascotacliente.setDescripcion(desc);
        }
        if(!fileName.equals("")){
        mascotacliente.setFoto(fileName);
        }
        mascotacliente.setFoto2(targetArray);
        if(this.port.existeMascota(mascotacliente.getNombre(), mascotacliente.getCliente().getTelCel(), mascotacliente.getCliente().getCorreo())){
             request.getRequestDispatcher("Vistas/ModificarAnimal.jsp").forward(request, response);
        }
        try {
            funciono = this.port.modificarAnimal(mascotacliente);
        } catch (IOException_Exception ex) {
            Logger.getLogger(ServletModificarAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(funciono){
                     Cliente usuLogeado = (Cliente) request.getSession().getAttribute("usuario_logueado");
        usuLogeado.getMascotasCliente().add(mascotacliente);
                      List<String> razas = (List<String>) this.port.obtenerRazas().getLista();
        request.setAttribute("Razas", razas);
        List<Mascota> mascotascliente = (List<Mascota>) this.port.obtenerMascotasCliente((Cliente) request.getSession().getAttribute("usuario_logueado")).getMascotasLista();
        request.setAttribute("MascotasCliente", mascotascliente);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServletModificarAnimal.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("Vistas/AltaAnimal.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("Vistas/ModificarAnimal.jsp").forward(request, response);
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

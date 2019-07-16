/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import clases.configuracion;
import clases.utilidades;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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
import servicios.Mascota;
import servicios.Raza;
import servicios.ServicioContCliente;
import servicios.WSContCliente;

/**
 *
 * @author gabri
 */
@WebServlet(name = "ServletAltaAnimal", urlPatterns = {"/ServletAltaAnimal"})
@MultipartConfig
public class ServletAltaAnimal extends HttpServlet {

    int contador = 0;
    private WSContCliente port;
    configuracion conf = new configuracion();
    utilidades util = new utilidades();

    /* private PublicadorAnimal port;
    configuracion conf = new configuracion();
     */
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
        if (request.getParameter("agregarMascotas") != null) {
            if (request.getParameter("agregarMascotas").equals("1")) {
                request.setAttribute("agregarMascotas", 1);
            }
        } else {
            List<Mascota> mascotascliente = (List<Mascota>) this.port.obtenerMascotasCliente((Cliente) request.getSession().getAttribute("usuario_logueado")).getMascotasLista();
            request.setAttribute("MascotasCliente", mascotascliente);
        }
        if (request.getParameter("descripcion") == null) {
            request.getRequestDispatcher("Vistas/AltaAnimal.jsp").forward(request, response);
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
        //processRequest(request, response);
        ServletContext context;
        context = request.getServletContext();

        String ruta = context.getResource("").getPath();
        URL url = new URL("http://" + conf.obtenerServer("servidor", ruta) + conf.leerProp("sConsultaUsuario", ruta));
        ServicioContCliente webService = new ServicioContCliente(url);
        this.port = webService.getWSContClientePort();

        Part filePart = request.getPart("file"); // obtengo <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        byte[] targetArray;
        try (InputStream fileContent = filePart.getInputStream()) {
            targetArray = new byte[fileContent.available()];
            fileContent.read(targetArray);
        }
        String ruta2 = Paths.get(ruta.substring(1)).getParent().getParent().toString() ;
        String rutafoto = ruta2 + "/web/img/ImagenMascota/";
        String nombre = request.getParameter("nombre");
        String raza = request.getParameter("raza");
        Raza r = new Raza();
        r.setRaza(raza);
        String desc = request.getParameter("descripcion");

        Mascota m = new Mascota();
        m.setNombre(nombre);
        m.setRaza(r);
        m.setDescripcion(desc);
        if (fileName.equals("")) {
            fileName = "default.png";
        }
        m.setFoto(fileName);
        m.setFoto2(targetArray);
        if (request.getParameter("mascotaagregada") == null) {
            Cliente usuLogeado = (Cliente) request.getSession().getAttribute("usuario_logueado");
            m.setCliente(usuLogeado);
            if (this.port.existeMascota(m.getNombre(), m.getCliente().getTelCel(), m.getCliente().getCorreo())) {
                List<Mascota> mascotascliente = (List<Mascota>) this.port.obtenerMascotasCliente((Cliente) request.getSession().getAttribute("usuario_logueado")).getMascotasLista();
                request.setAttribute("MascotasCliente", mascotascliente);
                List<String> razas = (List<String>) this.port.obtenerRazas().getLista();
                request.setAttribute("Razas", razas);
                request.getRequestDispatcher("Vistas/AltaAnimal.jsp").forward(request, response);
            }
            InputStream is = new ByteArrayInputStream(m.getFoto2());
            util.salvarImagenV2(is, rutafoto + util.generarNombreFoto(m.getNombre(), m.getCliente().getTelCel()) + ".png");
            m.setFoto(util.generarNombreFoto(m.getNombre(), m.getCliente().getTelCel()) + ".png");
            boolean funciono = this.port.ingresarAnimal(m);
            
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServletAltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Mascota> mascotascliente = (List<Mascota>) this.port.obtenerMascotasCliente((Cliente) request.getSession().getAttribute("usuario_logueado")).getMascotasLista();
            request.setAttribute("MascotasCliente", mascotascliente);
            List<String> razas = (List<String>) this.port.obtenerRazas().getLista();
            request.setAttribute("Razas", razas);
            request.getRequestDispatcher("Vistas/AltaAnimal.jsp").forward(request, response);
        } else {
            util.salvarImagenV2(filePart.getInputStream(), rutafoto + util.generarNombreFoto(m.getNombre(), m.getCliente().getTelCel()) + ".png");
            m.setFoto(util.generarNombreFoto(m.getNombre(), m.getCliente().getTelCel()) + ".png");
            this.port.agregarMascotaCliente(m);
            
            List<Mascota> mascotacliente = this.port.obtenerMascotaCliente().getMascotasLista();
            request.setAttribute("MascotasCliente", mascotacliente);
            request.getRequestDispatcher("Vistas/Registrarse.jsp").forward(request, response);
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

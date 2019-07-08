/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;


import clases.EstadoSesion;
import clases.Propiedades;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
@WebServlet(name="servletInicio", urlPatterns = {"/inicio"})
public class ServletInicio extends HttpServlet {

    Propiedades propiedades = Propiedades.getInstance();
    ServletContext context;

    private WSContCliente port;

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
        context = request.getServletContext();
        levantarProperties(context.getResource("").getPath());
        URL url = new URL(propiedades.getServicioUsuario());//new URL("http://" + conf.obtenerServer("servidor", ruta) + conf.leerProp("sConsultaUsuario", ruta));
        ServicioContCliente webService = new ServicioContCliente(url);
        this.port = webService.getWSContClientePort();

        Cliente cliente = null;
        HttpSession session = request.getSession();
      //  System.out.println(session.getAttribute("usuario_logueado"));
        if (session.getAttribute("usuario_logueado") == null) {

            boolean haySesion = false;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("cookieSesion")) {
                        if (!cookies[i].getValue().equals("")) {
                            haySesion = true;
                            String correo = cookies[i].getValue();
                            try {
                                cliente = this.port.obtenerCliente(correo);
                            } catch (Exception e) {

                                System.err.println("ERROR: " + e.getMessage() + " CAUSA: " + e.getCause());
                            }
                        }

                        session.setAttribute("estado_sesion", EstadoSesion.LOGIN_CORRECTO);
                        session.setAttribute("usuario_logueado", cliente);
                    }
                }
            }

            if (!haySesion) {
                session.setAttribute("estado_sesion", null);
                session.setAttribute("usuario_logueado", null);
            }
        }

        request.getRequestDispatcher("Vistas/Inicio.jsp").forward(request, response);
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

    private Properties levantarProperties(String path) throws MalformedURLException {
        System.out.println(context.getContextPath() + " getContextPath");
        System.out.println(context.getRealPath("") + " getRealPath");
        System.out.println(context.getResource("") + " getResource");
        System.out.println(System.class.getResource(""));

        InputStream in = null;
        Properties prop = new Properties();
        System.out.println("levantarProperties inicio...");
        String dirUsu = null, dirVen = null, dirRes = null;
        String pathP = generaPathProperties(path);
        try {

            //    in = new FileInputStream(pathP + "configuracionweb.properties");
            //  in = new FileInputStream(caminar(new File(pathP)));
            in = new FileInputStream(dameArchivo(path));
            prop.load(in);
            dirUsu = "http://" + prop.getProperty("Ip") + ":" + prop.getProperty("Porth") + prop.getProperty("sConsultaUsuario");
            dirVen = "http://" + prop.getProperty("Ip") + ":" + prop.getProperty("Porth") + prop.getProperty("sVentas");
            dirRes = "http://" + prop.getProperty("Ip") + ":" + prop.getProperty("Porth") + prop.getProperty("sReserva");
            System.out.println(dirUsu);
            System.out.println(dirVen);
            System.out.println(dirRes);
            propiedades.setPropied(prop);
            propiedades.setServicioUsuario(dirUsu);
            propiedades.setServicioReserva(dirRes);
            propiedades.setServicioVenta(dirVen);

            Iterator it = prop.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                System.out.println("Properties claves cargadas: ");
                System.out.println(key);

            }

            System.out.println("carga finalizada");
        } catch (IOException e) {
            System.err.println("levantarProperties: " + e.getMessage());
        }

        return prop;
    }

    private String generaPathProperties(String cadena) {
        String path = "/";
        try {
            String[] subCadena = cadena.split("/");
            int largo = subCadena.length;
            for (int i = 1; i < subCadena.length - 1; i++) {
                path += subCadena[i] + "/";
                System.out.println(subCadena[i]);
                System.out.println(path);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return path;
    }

    private File dameArchivo(String path) {
        String ruta = generaPathProperties(path);
        File retorno = caminar(new File(path));
        if (retorno != null) {
            return retorno;
        }
        return dameArchivo(ruta);
    }

    private File caminar(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    caminar(listFile[i]);
                }
                if (listFile[i].isFile()) {
                    if (listFile[i].getName().equals("config.properties")) {
                        return (File) listFile[i];
                    }
                }
            }
        }
        return null;
    }
}

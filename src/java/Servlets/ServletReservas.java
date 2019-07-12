/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import clases.configuracion;
import clases.utilidades;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.URL;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;
import servicios.Cliente;
import servicios.ListMascotaWS;
import servicios.ListTipoBanio;
import servicios.ListTipoEsquila;
import servicios.ListTurnos;
import servicios.ReservaWS;

import servicios.ServicioContReserva;
import servicios.TurnoWS;
import servicios.WSContReserva;


/**
 *
 * @author PabloP
 */
@WebServlet("/reserva")
public class ServletReservas extends HttpServlet {
    
utilidades util = utilidades.getInstance();
private WSContReserva port;
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
        response.setContentType("text/html;charset=UTF-8");
        
        
        ServletContext context;
        context = request.getServletContext();
        String ruta = context.getResource("").getPath();
        URL url = new URL("http://" + conf.obtenerServer("servidor", ruta) + conf.leerProp("sReserva", ruta));
        ServicioContReserva webService = new ServicioContReserva(url);
        this.port = webService.getWSContReservaPort();
        setearListas(request, response);

        if (request.getSession().getAttribute("usuario_logueado") == null) {
            request.setAttribute("mensaje", "No existe una sesion en el sistema, "
                    + "Debe iniciar sesion para poder hacer una reserva");
            request.getRequestDispatcher("/Vistas/Mensaje_Recibido.jsp").forward(request, response);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/Reserva.jsp");
        dispatcher.forward(request, response);
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
         PrintWriter out = response.getWriter();

        if (altaReserva(request)) {
            out.write("reservaOK");
        } else {
            out.print("reservaNOok");
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

     private void setearListas(HttpServletRequest request, HttpServletResponse response) {
        /* Collection propuestas = (Collection) colProp.getMyarreglo(); //(String) session.getAttribute("nickusuario")
                request.setAttribute("propuestas", propuestas);
                request.getRequestDispatcher("PRESENTACIONES/favorito.jsp").forward(request, response);
         */
        try {
            HttpSession session = request.getSession();
            Cliente cli = (Cliente) session.getAttribute("usuario_logueado");
            Calendar c = new GregorianCalendar();
            Date d = c.getTime();

            ListTipoBanio tipoBanio = (ListTipoBanio) port.getTipoBanio();
            ListTipoEsquila tipoEsquilas = (ListTipoEsquila) port.getTipoEsquilas();
            System.out.println(tipoEsquilas.getListaTipoEsquila().size());
            ListMascotaWS mascotasClie = (ListMascotaWS) port.getMascotasCliente(cli.getCorreo());
            Collection mascotasCliente = (Collection) mascotasClie.getMascotas();
            ListTurnos turnosDisponibles = port.getTurnosDisponibles(util.getFechaActual());
            Collection colecciontipoBanio = (Collection) tipoBanio.getListaTiposBanios();
            Collection coleccionTipoEsquila = (Collection) tipoEsquilas.getListaTipoEsquila();
            List<TurnoWS> listTurnosWS = turnosDisponibles.getListTurnosWS();
            Collection coleccionTurnos = (Collection) listTurnosWS;
            request.setAttribute("mascotasCliente", mascotasCliente);
            request.setAttribute("tiposBanios", colecciontipoBanio);
            request.setAttribute("tiposEsquila", coleccionTipoEsquila);
            request.setAttribute("turnosDisponibles", coleccionTurnos);

        } catch (HTTPException e) {
            System.err.println("Error: " + e.getMessage() + " Causa: " + e.getCause());
        }

    }
     
      private boolean altaReserva(HttpServletRequest request) {
        ReservaWS nuevaReserva = new ReservaWS();
        HttpSession session = request.getSession();
        try {

            Cliente cli = (Cliente) session.getAttribute("usuario_logueado");
            String idMascota = request.getParameter("idMascota");
            String idTipoServicio = request.getParameter("idTipoServicio");
            String idTurno = request.getParameter("idTurno");
            String fecha = util.getFechaActual().toString();
            String descripcion = (String) request.getParameter("descripcionReserva");
            String tipoServicio = (String) request.getParameter("tipoServicio");
            nuevaReserva.setIdCliente(cli.getCorreo());
            nuevaReserva.setIdMascota(Long.parseLong(idMascota));
            nuevaReserva.setIdTipoServicio(Long.parseLong(idTipoServicio));
            nuevaReserva.setDescripcion(descripcion);
            nuevaReserva.setIdTurno(Long.parseLong(idTurno));
            nuevaReserva.setTipoServicio(tipoServicio);
            nuevaReserva.setFechaReserva(fecha);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + " CAUSA: " + e.getCause());
        }
        return port.altaReserva(nuevaReserva);
    }
}
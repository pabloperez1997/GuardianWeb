<%-- 
    Document   : Reserva
    Created on : 07-jun-2019, 19:18:06
    Author     : PabloP
--%>

<%@page import="servicios.TipoEsquilaWS"%>
<%@page import="servicios.TipoBanioWS"%>
<%@page import="servicios.MascotaWS"%>
<%@page import="servicios.TurnoWS"%>
<%@page import="java.util.Collection"%>
<%@page import="servicios.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guardian:: Nueva Reserva</title>
        <jsp:include page="/Vistas/Cabezal.jsp" />
    </head>
    <body>
        <!-- Default form contact -->
        <form  class="text-center border border-light p-5">

            <p class="h4 mb-4">Hacer una nueva reserva</p>

            <!-- Name -->
            Nombre y Apellido del Cliente:<input type="text" 
                                                 value="<%=((Cliente) request.getSession().getAttribute("usuario_logueado")).getNombre()
                                                         + " " + ((Cliente) request.getSession().getAttribute("usuario_logueado")).getApellido()%>" class="form-control mb-4 disabled" placeholder="">

            <script> var id = '<%=((Cliente) request.getSession().getAttribute("usuario_logueado")).getCorreo()%>';
            
            </script>
          
            <!-- Email -->
            Telefono<input type="text" value="<%=((Cliente) request.getSession().getAttribute("usuario_logueado")).getTelCel()%>" class="form-control mb-4 disabled" placeholder="Telefono">

            <label>Turno</label>
            <%
                Collection<TurnoWS> turnos = (Collection<TurnoWS>) request.getAttribute("turnosDisponibles");
            %>

            <select id="comboTurnos"class="browser-default custom-select mb-4">
                <option value="" disabled>Elige una opcion...</option>
                <%
                    for (TurnoWS t : turnos) {
                %>
                <option value='<%=t.getId()%>'><%=t.getTurno()%></option>
                <%}%>
            </select>



            <!-- Subject -->
            <label>Tipo de Servicio</label>
            <div id="divtipo" >
                <label class="radio-inline"><input id="rdBan" type="radio" value="BANIO" name="optradio" onclick="cargarCombo()" checked>Baño</label>
                <label class="radio-inline"><input id="rdEsq" type="radio" value="ESQUILA" name="optradio" onclick="cargarCombo()" >Esquila</label>
                <label class="radio-inline"><input id="rdPas" type="radio" value="PASEO" name="optradio" onclick="cargarCombo()" >Paseo</label> 

            </div>
            <%
                Collection<MascotaWS> mascotas = (Collection<MascotaWS>) request.getAttribute("mascotasCliente");
            %>


            <label>Elige un tipo servicio</label>


            <select id="servicio" class="browser-default custom-select mb-4">
               

            </select>


            <label>Elige una mascota</label>
            <select id="comboMascotas" class="browser-default custom-select mb-4">
                <option value="" disabled>Elige una opcion...</option>
                <%
                    for (MascotaWS m : mascotas) {
                        String nom = m.getNombre();
                %>
                <option  value='<%=m.getId()%>' ><%=m.getNombre()%></option>
                <%}%>
            </select>

                  <!-- Copy -->
            <div class="custom-control custom-checkbox mb-4">
                <input type="checkbox" class="custom-control-input" id="defaultContactFormCopy">
                <label class="custom-control-label" for="defaultContactFormCopy">Enviarme una copia de esta reserva</label>
            </div>

            <!-- Send button -->
            <input id="altaReserva" type="button" value="Reservar" class="btn btn-dark btn-block my-4">

        </form>
        <jsp:include page="/Vistas/Footer.jsp" />
      
        <script type="text/javascript" src="./JS/reserva.js"></script>
        <script type="text/javascript" src="./JS/altaReserva.js"></script>
        <%
            Collection<TipoBanioWS> listaBanios = (Collection<TipoBanioWS>) request.getAttribute("tiposBanios");

            Collection<TipoEsquilaWS> listaEsquila = (Collection<TipoEsquilaWS>) request.getAttribute("tiposEsquila");

            for (TipoBanioWS ban : listaBanios) {%>
        <script>

            cargarListaBanio('<%=ban.getId()%>', '<%=ban.getTipobanio()%>');

        </script>
        <%}%>
        <% for (TipoEsquilaWS esq : listaEsquila) {%>
        <script>

            cargarListaEsquila('<%=esq.getId()%>', '<%=esq.getTipoEsquila()%>');

        </script>

        <%}%>

    </body>
</html>

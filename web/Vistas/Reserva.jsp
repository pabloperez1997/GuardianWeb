<%-- 
    Document   : Reserva
    Created on : 07-jun-2019, 19:18:06
    Author     : PabloP
--%>

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
<form class="text-center border border-light p-5">

    <p class="h4 mb-4">Hacer una nueva reserva</p>

    <!-- Name -->
    Nombre y Apellido del Cliente:<input type="text" 
    value="<%=((Cliente) request.getSession().getAttribute("usuario_logueado")).getNombre()
    + " "+ ((Cliente) request.getSession().getAttribute("usuario_logueado")).getApellido()%>" class="form-control mb-4 disabled" placeholder="">

    <!-- Email -->
    Telefono<input type="text" value="<%=((Cliente) request.getSession().getAttribute("usuario_logueado")).getTelCel()%>" class="form-control mb-4 disabled" placeholder="Telefono">

    <label>Turno</label>
    <select class="browser-default custom-select mb-4">
        <option value="" disabled>Elige una opcion...</option>
        <option value="1" selected>Hora 10</option>
        <option value="2">Hora 11</option>
        <option value="3">Hora 12</option>
    </select>
   
    <!-- Subject -->
    <label>Tipo de Servicio</label>
    <select class="browser-default custom-select mb-4">
        <option value="" disabled>Elige una opcion...</option>
        <option value="1" selected>Baño</option>
        <option value="2">Esquila</option>
        <option value="3">Paseo</option>
    </select>
   
    <label>Elige una mascota</label>
    <select class="browser-default custom-select mb-4">
        <option value="" disabled>Elige una opcion...</option>
        <option value="1" selected>Mascota 1</option>
        <option value="2">Mascota 2</option>
        <option value="3">Mascota 3</option>
    </select>

    <!-- Message -->
    <div class="form-group">
        <textarea class="form-control rounded-0" id="exampleFormControlTextarea2" rows="3" placeholder="Escriba aqui algun detalle a tener en cuenta si lo desea..."></textarea>
    </div>

    <!-- Copy -->
    <div class="custom-control custom-checkbox mb-4">
        <input type="checkbox" class="custom-control-input" id="defaultContactFormCopy">
        <label class="custom-control-label" for="defaultContactFormCopy">Enviarme una copia de esta reserva</label>
    </div>

    <!-- Send button -->
    <button class="btn btn-dark btn-block my-4" type="submit">Reservar</button>

</form>
<jsp:include page="/Vistas/Footer.jsp" />

    </body>
</html>

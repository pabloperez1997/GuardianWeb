<%-- 
    Document   : Perfil
    Created on : 23-jun-2019, 15:33:20
    Author     : PabloP
--%>

<%@page import="servicios.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Guardian:: Mi Perfil</title>
         <jsp:include page="/Vistas/Cabezal.jsp" />
    </head>
    <body>
        <!-- Default form contact -->
        <form class="text-center border border-light p-5" method="post" action="perfil">

    <p class="h4 mb-4">Mi Perfil</p>

    <div class="form-row mb-4">
        <div class="col">
            <!-- Nombre -->
            Nombre:<input value="<%=((Cliente) request.getSession().getAttribute("usuario_logueado")).getNombre()%>" type="text" name="nombre" id="nombre" class="form-control" placeholder="Nombre" required>
        </div>
        <div class="col">
            <!-- Apellido -->
            Apellido: <input value="<%=((Cliente) request.getSession().getAttribute("usuario_logueado")).getApellido()%>" type="text" name="apellido" id="apellido" class="form-control" placeholder="Apellido" required>
        </div>
    </div>
    
    Cedula: <input value="<%=((Cliente) request.getSession().getAttribute("usuario_logueado")).getCedula()%>" type="text" name="cedula" id="cedula" class="form-control mb-4" placeholder="Cedula" required>

    <!-- E-mail -->
    Email: <input value="<%=((Cliente) request.getSession().getAttribute("usuario_logueado")).getCorreo()%>" type="Email" name="email" id="email" class="form-control mb-4" placeholder="E-mail" required>


    <!-- Telefono -->
    Teléfono: <input value="<%=((Cliente) request.getSession().getAttribute("usuario_logueado")).getTelCel()%>" type="text" name="telefono" id="telefono" class="form-control mb-4" placeholder="Teléfono" required>

    <!-- Direccion -->
    Dirección: <input value="<%=((Cliente) request.getSession().getAttribute("usuario_logueado")).getDireccion()%>" type="text" name="direccion" id="direccion" class="form-control mb-4" placeholder="Dirección" required>
   


    <!-- Send button -->
    <button class="btn btn-dark btn-block my-4" type="submit">Guardar Cambios</button>

</form>
<jsp:include page="/Vistas/Footer.jsp" />

    </body>
</html>

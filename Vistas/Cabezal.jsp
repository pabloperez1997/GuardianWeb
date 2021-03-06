<%-- 
    Document   : Cabezal
    Created on : 25-abr-2019, 14:26:25
    Author     : PabloP
--%>
<%@page import="servicios.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
  <!--Main Navigation-->
<header>

    <nav class="navbar fixed-top navbar-expand-lg navbar-dark black scrolling-navbar">
        <a class="navbar-brand" href="#"><strong>Guardian</strong></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-5" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Servicios </a>
                <div class="dropdown-menu dropdown-menu-right dropdown-info" aria-labelledby="navbarDropdownMenuLink-4">
                <a class="dropdown-item" href="#">Baño</a>
                <a class="dropdown-item" href="#">Esquila</a>
                <a class="dropdown-item" href="#">Paseos</a>
          </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Productos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Galeria de Fotos</a>
                </li>
            </ul>
  
    <ul class="navbar-nav ml-auto">
        <% if ((Cliente) request.getSession().getAttribute("usuario_logueado") == null) {%>
      <li class="nav-item">
        <a class="nav-link" href="iniciar-sesion">
          Iniciar Sesion</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="registrarse">
          Registrarme</a>
      </li>
      <% }else {%>
      <li class="nav-item">
      <a class="nav-link"> Bienvenido 
          <%=((Cliente) request.getSession().getAttribute("usuario_logueado")).getNombre()%>
          <%=((Cliente) request.getSession().getAttribute("usuario_logueado")).getApellido()%>
      </a>
      </li>
        <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-4" data-toggle="dropdown"
          aria-haspopup="true" aria-expanded="false">
          <i class="fas fa-user"></i>Mi cuenta</a>
        <div class="dropdown-menu dropdown-menu-right dropdown-info" aria-labelledby="navbarDropdownMenuLink-4">
          <a class="dropdown-item" href="#">Perfil</a>
          <a class="dropdown-item" href="cerrar-sesion">Cerrar Sesión</a>
        </div>
      </li>
      <%}%>
      
    </ul>
  
            </div>
    </nav>

</header>
<!--Main Navigation-->

<!--Main Layout-->
<main>
    <div class="container" style="height:500px;">
        <div class="row mt-5 pt-5">
            <div class="col text-center">
                <h2>Aca va una foto o algo</h2>
                <br>
            </div>
        </div>
    </div>
</main>
<!--Main Layout-->
    </body>
</html>

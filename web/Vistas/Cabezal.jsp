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
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
        <!-- Bootstrap core CSS -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="bootstrap/css/mdb.min.css" rel="stylesheet">
        <!-- Your custom styles (optional) -->
        <link href="bootstrap/css/style.css" rel="stylesheet">
    </head>
    <body>

<header>
    
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark black scrolling-navbar">
        <a class="navbar-brand" href="inicio"><img src="img/logo2.jpg" alt="Logo" class="img-responsive"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                 <% if ((Cliente) request.getSession().getAttribute("usuario_logueado") != null) {%>
                 <li class="nav-item">
                    <a class="nav-link" href="reserva">Nueva Reserva</a>
                </li>
                <%}%>
                <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-5" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Servicios </a>
                <div class="dropdown-menu dropdown-menu-right dropdown-info" aria-labelledby="navbarDropdownMenuLink-4">
                <a class="dropdown-item" href="#">Baño</a>
                <a class="dropdown-item" href="#">Esquila</a>
                <a class="dropdown-item" href="#">Paseos</a>
                </div>
                </li>
                 <% if ((Cliente) request.getSession().getAttribute("usuario_logueado") != null) {%>
                <li class="nav-item">
                    <a class="nav-link" href="ServletAltaAnimal">Mis mascotas</a>
                </li>
                <%}%>
                <li class="nav-item">
                    <a class="nav-link" href="ver-productos">Productos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="imagenes">Galeria de Fotos</a>
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
      <a class="nav-link"> ¡Hola
          <%=((Cliente) request.getSession().getAttribute("usuario_logueado")).getNombre()%>
          <%=((Cliente) request.getSession().getAttribute("usuario_logueado")).getApellido()%>!
      </a>
      </li>
            <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-4" data-toggle="dropdown"
              aria-haspopup="true" aria-expanded="false">
              <i class="fas fa-user"></i>Mi cuenta</a>
            <div class="dropdown-menu dropdown-menu-right dropdown-info" aria-labelledby="navbarDropdownMenuLink-4">
              <a class="dropdown-item" href="perfil">Perfil</a>
              <a class="dropdown-item" href="cerrar-sesion">Cerrar Sesión</a>
      
            </div>
            </li>
            
      <%}%>
    </ul>
      
<% if (request.getSession().getAttribute("CantidadProdVend")!=null) {%>
<div id="ex2">
   <%int i=(Integer)request.getSession().getAttribute("CantidadProdVend");%>
   <a href="venta"><span class="fa-stack  has-badge" data-count="<%=i%>">
    <i class="fa fa-circle fa-stack-2x"></i>
    <i class="fa fa-shopping-cart fa-stack-1x fa-inverse"></i>
  </span></a>
</div>
    <% }%>

</div>
    
     
    </nav>
    

</header>

<script type="text/javascript" src="bootstrap/js/jquery-3.3.1.min.js"></script>
  <!-- Bootstrap tooltips -->
  <script type="text/javascript" src="bootstrap/js/popper.min.js"></script>
  <!-- Bootstrap core JavaScript -->
  <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
  <!-- MDB core JavaScript -->
  <script type="text/javascript" src="bootstrap/js/mdb.min.js"></script>
    </body>
</html>

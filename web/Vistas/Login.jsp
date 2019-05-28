<%-- 
    Document   : Login
    Created on : 24-abr-2019, 23:29:25
    Author     : PabloP
--%>

<%@page import="clases.EstadoSesion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guardian::Iniciar Sesion</title>
        
        <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="bootstrap/css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="bootstrap/css/style.css" rel="stylesheet">
    
    <jsp:include page="/Vistas/Cabezal.jsp" />
    </head>
    <body>
    <main>
        <br>
        <br>
        <br>
        <br>
        
     
    <div class="d-flex justify-content-center align-items-center h-100"> 
    <div class="container"> 
    <!-- Grid row --> 
    <div class="row">
    <!-- Grid column --> 
    <div class="col-md-12">
      
    <!-- Default form login -->
    <form action="iniciar-sesion" method="post" class="text-center border border-light p-5">

    <p class="h4 mb-4">Iniciar Sesión</p>

    <!-- Email -->
    <input type="email" id="login" name="login" class="form-control mb-4" placeholder="E-mail">

    <!-- Password -->
    <input type="password" id="pass" name="pass" class="form-control mb-4" placeholder="Password">

    <div class="d-flex justify-content-around">
        <div>
            <!-- Remember me -->
            <div class="custom-control custom-checkbox">
                <input type="checkbox" id="Recordarme" name="Recordarme" class="custom-control-input">
                <label class="custom-control-label" for="Recordarme">Recordarme</label>
            </div>
            
        </div>
         
        
        <div>
            <!-- Forgot password -->
            <a href="">Olvidaste la contraseña?</a>
        </div>
    </div>

    <!-- Sign in button -->
    <button class="btn btn-dark btn-block my-4" type="submit">Iniciar Sesion</button>
        <div id="Error_login" style="display: none;" class="alert alert-danger" role="alert">
                <span class="sr-only">Error:</span>
                <% if (request.getAttribute("errorContrasenia") != null) {%>
                Contraseña Incorrecta.
                <%} else {%>                  
                Usuario Incorrecto, reintente o Registrese si aun no tiene una cuenta.                  
                <%}

                %>  
        </div>
    <!-- Register -->
    <p>No eres miembro?
        <a href="registrarse">Registrarme</a>
    </p>

    </form>
    <!-- Default form login -->
    </div>
    </div>
    </div>
    
        
    </div>
        
    </main>

  <script type="text/javascript" src="bootstrap/js/jquery-3.3.1.min.js"></script>
  <!-- Bootstrap tooltips -->
  <script type="text/javascript" src="bootstrap/js/popper.min.js"></script>
  <!-- Bootstrap core JavaScript -->
  <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
  <!-- MDB core JavaScript -->
  <script type="text/javascript" src="bootstrap/js/mdb.min.js"></script>
  <script type="text/javascript">
            <% if (request.getSession().getAttribute("estado_sesion") == EstadoSesion.LOGIN_INCORRECTO
                        || request.getSession().getAttribute("estado_sesion") == EstadoSesion.CONTRASENIA_INCORRECTA) { %>
            var var2 = document.getElementById('Error_login');
            var2.style.display = "block";
           
            <% }%>
        </script>        
  <jsp:include page="/Vistas/Footer.jsp" />
    </body>
</html>

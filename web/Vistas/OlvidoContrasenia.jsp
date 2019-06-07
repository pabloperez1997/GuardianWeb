<%-- 
    Document   : OlvidoContrasenia
    Created on : 28-may-2019, 20:51:30
    Author     : PabloP
--%>

<<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guardian::Olvidaste la contraseña</title> 
    <jsp:include page="/Vistas/Cabezal.jsp" />
    </head>
    <body>
    <main>
        <br>
        <br>
       
     
    <div class="d-flex justify-content-center align-items-center h-100"> 
    <div class="container"> 
    <!-- Grid row --> 
    <div class="row">
    <!-- Grid column --> 
    <div class="col-md-12">
      
    <!-- Default form login -->
    <form action="recuperar-contrasenia" method="post" class="text-center border border-light p-5">

    <p class="h4 mb-4">Olvidaste la Contraseña</p>

    <!-- Email -->
    <input type="email" id="recuperarpass" name="recuperarpass" class="form-control mb-4" placeholder="E-mail">
 
    <!-- Sign in button -->
    <button class="btn btn-dark btn-block my-4" type="submit">Recuperar Contraseña</button>
        <div id="Error_correo" style="display: none;" class="alert alert-danger" role="alert">
                <span class="sr-only">Error:</span>                     
                Ese correo no esta registrado en nuestro sitio. 
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
        <script type="text/javascript">
            <% if (request.getAttribute("email_invalido") != null) { %>
            var var2 = document.getElementById('Error_correo');
            var2.style.display = "block";
           
            <% }%>
        </script>       
  <jsp:include page="/Vistas/Footer.jsp" />
    </body>
</html>

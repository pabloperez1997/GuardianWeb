<%-- 
    Document   : ValidarCuenta
    Created on : 4/06/2019, 04:47:01 PM
    Author     : gabri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
         <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
  <!-- Bootstrap core CSS -->
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Material Design Bootstrap -->
  <link href="bootstrap/css/mdb.min.css" rel="stylesheet">
  <!-- Your custom styles (optional) -->
  <link href="bootstrap/css/style.css" rel="stylesheet">
        <title>Validar Cuenta</title>
        <jsp:include page="/Vistas/Cabezal.jsp" />
    </head>

    <body>
         <%boolean validado = (boolean) request.getAttribute("Validado");
                if (!validado) {%>
        <h2>Validar cuenta</h2>
        <br>
        <div class="d-flex justify-content-around">
    <div class="container"> 
    <!-- Grid row --> 
    <div class="row">
    <!-- Grid column --> 
    <div class="col-md-12">
         <form  action="${pageContext.request.contextPath}/ServletValidarCuenta" method="POST" class="text-center border border-light p-5">
             <div class="col">
            <p class="h4 mb-4">Ingrese su correo</p>
            <input type="text" id="email" name="email" class="form-control">
            </div>
            <div class="col">
                <br>
            <p class="h4 mb-4">Ingrese el codigo de autenticacion</p>
            <input type="text" id="token" name="token" class="form-control">
            </div>
            <div class="col">
                <br>
            <p class="h4 mb-4">Ingrese su nueva contraseña</p>
            <input type="password" id="pass" name="pass" class="form-control">
            </div>
            <div class="col">
            <input type="submit" id="autorizar" name="autorizar" value="autorizar" class="btn btn-dark btn-block my-4" >
            </div>
        </form>
         <%}else{%>
         <br>
     <br>
     <br>
         <br>
     <br>
     <br>
 <form  action="${pageContext.request.contextPath}/ServletValidarCuenta" method="POST" class="text-center border border-light p-5">
     <div class="col">
<p class="h4 mb-4">Cuenta validada!!!</p>
            </div>
        <a href="iniciar-sesion" class="btn btn-dark btn-block my-4">Iniciar sesion</a>
        </form>
     <br>
     <br>
     <br>
     <br>
        <%}%>
    </div> 
    </div>
    </div>
        </div>


  <jsp:include page="/Vistas/Footer.jsp" />
    </body>
</html>

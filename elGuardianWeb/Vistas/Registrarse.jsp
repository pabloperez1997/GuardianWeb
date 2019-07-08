<%-- 
    Document   : Registrarse
    Created on : 07-may-2019, 14:26:54
    Author     : PabloP
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
        <title>Guardian:: Registrarse</title>
    </head>
    <body>
        <!-- Default form register -->
<form method="post" action="registrarse" class="text-center border border-light p-5">

    <p class="h4 mb-4">Registrarse</p>

    <div class="form-row mb-4">
        <div class="col">
            <!-- Nombre -->
            <input type="text" name="nombre" id="nombre" class="form-control" placeholder="Nombre">
        </div>
        <div class="col">
            <!-- Apellido -->
            <input type="text" name="apellido" id="apellido" class="form-control" placeholder="Apellido">
        </div>
    </div>
    <!-- E-mail -->
    <input type="text" name="cedula" id="cedula" class="form-control mb-4" placeholder="Cedula">

    <!-- E-mail -->
    <input type="Email" name="email" id="email" class="form-control mb-4" placeholder="E-mail">

    <!-- Password -->
    <input type="password" name="pass" id="pass" class="form-control mb-3" placeholder="Contraseña">
    <input type="password" name="pass" id="pass" class="form-control" placeholder="Repetir Contraseña">
    <small id="defaultRegisterFormPasswordHelpBlock" class="form-text text-muted mb-4">
        Al menos 6 caracteres
    </small>

    <!-- Telefono -->
    <input type="text" name="telefono" id="telefono" class="form-control mb-4" placeholder="Teléfono" >

    <!-- Direccion -->
    <input type="text" name="direccion" id="direccion" class="form-control mb-4" placeholder="Dirección" >

    <!-- Boton Registrarse -->
    <button class="btn btn-dark my-4 btn-block" type="submit">Registrarme</button>

    <hr>

</form>
<!-- Default form register -->
<script type="text/javascript" src="bootstrap/js/jquery-3.3.1.min.js"></script>
  <!-- Bootstrap tooltips -->
  <script type="text/javascript" src="bootstrap/js/popper.min.js"></script>
  <!-- Bootstrap core JavaScript -->
  <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
  <!-- MDB core JavaScript -->
  <script type="text/javascript" src="bootstrap/js/mdb.min.js"></script>
    </body>
    
  
</html>

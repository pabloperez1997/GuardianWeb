<%-- 
    Document   : Login
    Created on : 24-abr-2019, 23:29:25
    Author     : PabloP
--%>

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
    </head>
    <body>
        <!-- Material form login -->
<div class="card">

  <h5 class="card-header info-color white-text text-center py-4">
    <strong>Iniciar Sesion</strong>
  </h5>

  <!--Card content-->
  <div class="card-body px-lg-5 pt-0">

    <!-- Form -->
    <form class="text-center" style="color: #757575;" action="/IniciarSesion">

      <!-- Email -->
      <div class="md-form">
        <input type="email" name="login" id="materialLoginFormEmail" class="form-control">
        <label for="materialLoginFormEmail">E-mail</label>
      </div>

      <!-- Password -->
      <div class="md-form">
        <input type="password" name="pass" id="materialLoginFormPassword" class="form-control">
        <label for="materialLoginFormPassword">Contraseña</label>
      </div>

      <div class="d-flex justify-content-around">
        <div>
          <!-- Remember me -->
          <div class="form-check">
            <input type="checkbox" class="form-check-input" id="materialLoginFormRemember">
            <label class="form-check-label" for="materialLoginFormRemember">Recordarme</label>
          </div>
        </div>
        <div>
          <!-- Forgot password -->
          <a href="">Olvidaste la contraseña?</a>
        </div>
      </div>

      <!-- Sign in button -->
      <button class="btn btn-outline-info btn-rounded btn-block my-4 waves-effect z-depth-0" type="submit">Iniciar Sesión</button>

      <!-- Register -->
      <p>No eres miembro?
        <a href="">Registrarme</a>
      </p>

    </form>
    <!-- Form -->

  </div>

</div>
<!-- Material form login -->

  <script type="text/javascript" src="bootstrap/js/jquery-3.3.1.min.js"></script>
  <!-- Bootstrap tooltips -->
  <script type="text/javascript" src="bootstrap/js/popper.min.js"></script>
  <!-- Bootstrap core JavaScript -->
  <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
  <!-- MDB core JavaScript -->
  <script type="text/javascript" src="bootstrap/js/mdb.min.js"></script>
    </body>
</html>

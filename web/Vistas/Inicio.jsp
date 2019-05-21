<%-- 
    Document   : Inicio
    Created on : 24-abr-2019, 22:15:36
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
 </head>
    
   <body>
  <jsp:include page="/Vistas/Cabezal.jsp" />
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
  <jsp:include page="/Vistas/Footer.jsp" />
  
  <script type="text/javascript" src="bootstrap/js/jquery-3.3.1.min.js"></script>
  <!-- Bootstrap tooltips -->
  <script type="text/javascript" src="bootstrap/js/popper.min.js"></script>
  <!-- Bootstrap core JavaScript -->
  <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
  <!-- MDB core JavaScript -->
  <script type="text/javascript" src="bootstrap/js/mdb.min.js"></script>
    </body>

</html>

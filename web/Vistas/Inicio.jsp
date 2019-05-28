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
  
 </head>
     <jsp:include page="/Vistas/Cabezal.jsp" />
   <body>
  
  <!--Main Navigation-->
  <br>
  <br>
<!--Main Layout-->
<main>
    
    <div id="carouselExample1" class="carousel slide z-depth-1-half" data-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img class="d-block w-100" src="img/s2.jpg" alt="">
        </div>
        <div class="carousel-item">
          <img class="d-block w-100" src="img/s5.jpg" alt="">
        </div>
        <div class="carousel-item">
          <img class="d-block w-100" src="img/s3.jpg" alt="">
        </div>
          <div class="carousel-item">
          <img class="d-block w-100" src="img/s4.jpg" alt="">
        </div>
      </div>
      <a class="carousel-control-prev" href="#carouselExample1" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Siguiente</span>
      </a>
      <a class="carousel-control-next" href="#carouselExample1" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Anterior</span>
      </a>
    </div>
</main>
<!--Main Layout-->
  
  
    </body>
<jsp:include page="/Vistas/Footer.jsp" />
</html>

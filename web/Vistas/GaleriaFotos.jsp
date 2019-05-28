<%-- 
    Document   : GaleriaFotos
    Created on : 28-may-2019, 13:05:50
    Author     : PabloP
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guardian:: Galeria de Fotos</title>
         
    </head>
    <jsp:include page="/Vistas/Cabezal.jsp" />
    <br>
    <br>
    <br>
    <br>
    <div class="container">

  <h1 class="font-weight-light text-center text-lg-left mt-4 mb-0">Galeria de Fotos</h1>

  <hr class="mt-2 mb-5">

  <div class="row text-center text-lg-left">

      <%List<String> imagenes = (List<String>) request.getAttribute("imageUrlList");
        if (imagenes.size() != 0) {%>
        <%for (String imagen : imagenes) {%>

       <div class="col-lg-3 col-md-4 col-6">
           <a href="img/galeria/<%=imagen%>" class="d-block mb-4 h-100">
            <img class="img-fluid img-responsive img-thumbnail" src="img/galeria/<%=imagen%>" alt="">
           </a>
           </div>
        <br>
        <br>
        <% }%>
        <% } else {%>
        <h1>No hay imagenes por el momento...</h1>
        <%}%>
    </div>
   
    
    
    
  </div>
</div>
<!-- /.container -->

   
    <jsp:include page="/Vistas/Footer.jsp" />
</html>

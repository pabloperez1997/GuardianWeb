<%-- 
    Document   : ModificarAnimal.jsp
    Created on : 11/06/2019, 04:09:52 PM
    Author     : gabri
--%>

<%@page import="java.util.List"%>
<%@page import="servicios.Mascota"%>
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
        <title>Modificar Mascota</title>
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
        
    <!-- Default form register -->
    
    <%Mascota m =new Mascota();
        if(request.getAttribute("MascotaCliente") != null){
        m= (Mascota) request.getAttribute("MascotaCliente");
}%>
    <form method="post" action="${pageContext.request.contextPath}/ServletModificarAnimal" class="text-center border border-light p-5" enctype="multipart/form-data">
        <%if(request.getAttribute("MascotaCliente") != null){%>
    <p class="h4 mb-4">Modifique datos de su mascota</p>

    <div class="form-row mb-4">
        <div class="col">

            <input type="text" name="nombre" id="nombre" class="form-control" placeholder="<%=m.getNombre()%>">
        </div>
        <div class="col">
<select name="raza">
    <%List<String> razas = (List<String>) request.getAttribute("Razas");
    for (String raza : razas) {%>
    <option value="<%=raza%>"><%=raza%></option>
<%}%>
</select>
        </div>
    </div>

    <input type="text" name="descripcion" id="descripcion" class="form-control mb-4" placeholder="<%=m.getDescripcion()%>">
    <%}%>
    <label>Ingresar Foto</label>
    <input type="file" name="file"/>

    <!-- Boton Modificar -->
    <button class="btn btn-dark my-4 btn-block" type="submit">Modificar Animal</button>  
    </form>

    </div>
    </div>
    </div>
    </div>
        
    </main>
<!-- Default form register -->
<script type="text/javascript" src="bootstrap/js/jquery-3.3.1.min.js"></script>
  <!-- Bootstrap tooltips -->
  <script type="text/javascript" src="bootstrap/js/popper.min.js"></script>
  <!-- Bootstrap core JavaScript -->
  <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
  <!-- MDB core JavaScript -->
  <script type="text/javascript" src="bootstrap/js/mdb.min.js"></script>
  <jsp:include page="/Vistas/Footer.jsp" />

    </body>
    
  
</html>

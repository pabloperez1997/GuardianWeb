<%-- 
    Document   : VerProductos
    Created on : 21-may-2019, 14:28:25
    Author     : PabloP
--%>
<%@page import="servicios.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Guardian:: Productos</title>
  
  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/shop-homepage.css" rel="stylesheet">
 <jsp:include page="/Vistas/Cabezal.jsp" />
  
</head>

<body>

  <!-- Page Content -->
  <br>
  <br>
  <br>
  <div class="container">

    <div class="col-lg-12">
      <!-- /.col-lg-3 -->

      <div class="col-sm">

        <div class="row ">

             <%List<Producto> prods = (List<Producto>) request.getAttribute("Productos");
                if (prods.size() != 0) {%>
                
                <%for (Producto producto : prods) {%>
          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
                
             <%if (producto.getFoto().compareTo("sinfoto")!=0){%>
             <img class="card-img-top" src="img/imgprod/<%=producto.getFoto()%>.png" alt="">
             <%}else{%>
             <img class="card-img-top" src="img/default.jpg" alt="">
             <%}%>
             
              <div class="card-body">
                <h4 class="card-title">
                <%=producto.getNombre()%>                 
                </h4>
                <h5>$<%=producto.getPrecio()%></h5>
                <p class="card-text"><%=producto.getDescripcion()%></p>
              </div>
              <div class="card-footer">
                  
                  <%if (producto.isDisponible()==true){%>
                  <p><i class="fa fa-check-circle" style="color:green"></i>Disponible </p>               
                  <div class="row">
                      <div class="col-sm-12">
                        <div class="text-center">
                            <form  method="post"action="venta">
                          <button type="submit" class="btn btn-success" id="codigoprod" 
                                  name="codigoprod" value="<%=producto.getCodigo()%>" >Comprar</button
                        </div>
                        </form>
                      </div>
                    </div>
                   </div>
                  
                   <%}else{%>
                  
                  <p><i class="fa fa-times-circle" style="color:red"></i>No Disponible</p>
                    <div class="row">
                      <div class="col-sm-12">
                        <div class="text-center">
                          <button type="submit" class="btn btn-success disabled" id="codigoprod" 
                                  name="codigoprod" value="<%=producto.getCodigo()%>" method="post" href="ver-productos">Comprar</button
                        </div>
                      </div>
                    </div>
                   </div>                
                  <%}%>
                  
              </div>
            </div>
          </div>
          <% }%>
          <% } else {%>
            <h1>No hay productos por el momento...</h1>
          <%}%>

        </div>
        <!-- /.row -->        
      </div>
      <!-- /.col-lg-9 -->
    </div>
  </div>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
<jsp:include page="/Vistas/Footer.jsp" />
</html>


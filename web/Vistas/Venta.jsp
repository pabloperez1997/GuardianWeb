<%-- 
    Document   : Venta
    Created on : 24-may-2019, 16:06:05
    Author     : PabloP
--%>

<%@page import="servicios.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="bootstrap/css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="bootstrap/css/style.css" rel="stylesheet">
  
  <title>Guardian:: Carro de Compras</title>
  
 
  <jsp:include page="/Vistas/Cabezal.jsp" />
</head>

<body>
    <br>
    <br>
    <br>
    <form id="finalizar" action="finalizarventa" method="post"></form>
    
    <div class="container">      
    <div class="card shopping-cart">
            <div class="card-header bg-dark text-light">
                <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                Carro de Compras
                <div class="clearfix"></div>
            </div>
            <div class="card-body">
                    <!-- PRODUCT -->
                    <% if (request.getAttribute("ProdsVenta")!=null) {%>
                    
                    <%List<Producto> prods = (List<Producto>) request.getAttribute("ProdsVenta");
                    if (prods.size() > 0) {%>

                    <%for (Producto producto : prods) {%>
                    
                    
                    <div class="row">
                        <div class="col-12 col-sm-12 col-md-2 text-center" >
                            <%if (producto.getFoto().compareTo("sinfoto")!=0){%>
             <img class="img-responsive" src="img/imgprod/<%=producto.getFoto()%>.png" alt="imagen del producto" width="120" height="80">
             <%}else{%>
             <img class="img-responsive" src="img/default.jpg" alt="imagen del producto" width="120" height="80">
             <%}%>  
                        </div>
                        <div class="col-12 text-sm-center col-sm-12 text-md-left col-md-6">
                            <h4 class="product-name"><strong><%=producto.getNombre()%></strong></h4>
                            <h4>
                                <small><%=producto.getDescripcion()%></small>
                            </h4>
                        </div>
                        <div class="col-12 col-sm-12 text-sm-center col-md-4 text-md-right row">
                            <div class="col-3 col-sm-3 col-md-6 text-md-right" style="padding-top: 5px">
                                <h6><strong>$<%=Math.round(producto.getPrecio())%><span class="text-muted">x</span></strong></h6>
                            </div>
                            
                            <div class="col-4 col-sm-4 col-md-4">
                                <div class="quantity">
                                    <button type="button" onclick="this.parentNode.querySelector('input[type=number]').stepUp();calcularTotal(<%=producto.getPrecio()%>);" class="plus">+</button>
                                    <input form="finalizar" name="<%=producto.getCodigo()%>" id="<%=producto.getCodigo()%>" type="number" max="99" min="1" value="1" title="Cantidad"  class="qty"
                                           size="5" readonly>
                                   <button type="button" onclick="this.parentNode.querySelector('input[type=number]').stepDown();calcularTotalrestar(<%=producto.getPrecio()%>);" class="minus"><b>-</b></button>
                                </div>
                                
                            <div class="col-3 col-sm-2 col-md-2 text-right">
             
                                <form method="post" action="venta">
                                <button type="submit" id="eliminarprod" 
                                  name="eliminarprod" value="<%=producto.getCodigo()%>" class="btn btn-outline-danger btn-xs" >
                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                </button>
                                </form>
                               

                            </div>
                            </div>
                                  
                                   
                        </div>
                    </div>
                    <hr>
          <%} }else {%>
            <h2>No hay productos en su carro de compras...</h2>
          <%} %>
           
          
          <% } else {%>
            <h1>No hay productos por el momento...</h1>
          <%}%>
                               
                    <div class="float-right">
                    <a href="ver-productos" class="btn btn-secondary float-right">
                        Continuar Comprando...
                    </a>
                </div>
            </div>
            <div class="card-footer">
                <div class="float-right" style="margin: 10px">
                    <a onclick="finalizarventa()" class="btn btn-success float-right">Finalizar compra</a>
                    <div class="float-right" style="margin: 5px">
                    <%List<Producto> prods = (List<Producto>) request.getAttribute("ProdsVenta");
                    float precio=0;
                    if (prods.size() > 0) {
                    for (Producto producto : prods) {
                      precio=precio+producto.getPrecio();
                    }}%>
                    Precio Total: <b>$</b><b id="total"><%=Math.round(precio)%></b>
                    </div>
                </div>
            </div>
        </div>
        </div>
    <br>
    <br>
    <br>
    <script>
        function finalizarventa(){
        document.getElementById("finalizar").submit();
    }
        </script>
        
        
<jsp:include page="/Vistas/Footer.jsp" />
</body>

<script type="text/javascript" src="bootstrap/js/myjs.js"></script>
</html>


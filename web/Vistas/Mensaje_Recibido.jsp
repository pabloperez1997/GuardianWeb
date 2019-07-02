<%-- 
    Document   : Mensaje_Recibido
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Culturarte</title>
       
    </head>
    <jsp:include page="/Vistas/Cabezal.jsp" />
     <body>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>      
    <div class="row d-flex justify-content-center align-middle"> 
    <!-- Grid column --> 
    <div class=" col-md-6 modal-content formulario">

                    <div class="modal-header">
                        <h5 align="center" class="modal-title" id="exampleModalLabel">Mensaje</h5>

                    </div>
                    <div class="modal-body">
                        <%out.print(request.getAttribute("mensaje"));%>
                        <%if(request.getAttribute("idVenta")!=null){%>
                        <a href=" pdfventa/Compra_${idVenta}.pdf" download="Detalles_de_compra.pdf"> click me</a>
                        <%}%>
                    </div>
                    <div class="modal-footer">
                        <a href="javascript:window.history.back();" class="btn btn-primary"> &laquo; Volver</a>
                       
                        <a style="color:#FFFFFF" class="btn btn-primary" href="inicio">Volver al inicio</a> </button>

                    </div>
              
                </div>   
                </div>
               <br>
    </body>
    <br>
    <br>
    <br>
    <br>
    <br>
    <jsp:include page="/Vistas/Footer.jsp" /> 
</html>

<%@page import="servicios.Mascota"%>
<%@page import="java.util.List"%>
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
        <link rel="stylesheet" href="css/AltaAnimal.css" >
        <title>Alta Mascota</title>
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
                            <form method="post" action="${pageContext.request.contextPath}/ServletAltaAnimal" class="text-center border border-light p-5" enctype="multipart/form-data">
                                <p class="h4 mb-4">Ingrese su Mascota</p>

                                <div class="form-row mb-4">
                                    <div class="col">

                                        <input type="text" name="nombre" id="nombre" class="form-control" placeholder="Nombre">
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

                                <input type="text" name="descripcion" id="descripcion" class="form-control mb-4" placeholder="Descripcion">
                                <label>Ingresar Foto</label>
                                <input type="file" name="file"/>
                                <%if (request.getAttribute("agregarMascotas") != null) {%>
                                <input type="hidden" value="1" name="mascotaagregada">
                                <%}%>
                                <!-- Boton Registrarse -->

                                <button class="btn btn-dark my-4 btn-block" type="submit">Registrar Animal</button>  
                            </form>
                            <br>
                            <br>
                            <p class="h4 mb-4 text-center">Tus Mascotas</p>
                            <div class="divTable">
                                <div class="divTableBody">
                                    <div class="divTableRow" >
                                        <div class="divTableHead">Nombre</div>
                                        <div class="divTableHead">Raza</div>
                                        <div class="divTableHead">Foto</div>
                                        <div class="divTableHead">Modificar</div>
                                        <div class="divTableHead">Eliminar</div>
                                    </div>
                                    <%if (request.getAttribute("MascotasCliente") != null) {
                                            List<Mascota> mascotas = (List<Mascota>) request.getAttribute("MascotasCliente");
                                            for (Mascota m : mascotas) {%>
                                    <div class="divTableRow">

                                        <div class="divTableCell"><%=m.getNombre()%></div>
                                        <div class="divTableCell"><%=m.getRaza().getRaza()%></div>
                                        <div class="divTableCell"><img  src="img/ImagenMascota/<%=m.getFoto()%>" alt=""></div>
                                        <div class="divTableCell" style="width: 200px">
                                            <form action="${pageContext.request.contextPath}/ServletModificarAnimal">
                                                <input type="hidden" value="<%=m.getId()%>" name="IdMascota">
                                                <input type="submit" class="btn btn-success my-4 btn-block" value="Modificar">
                                            </form></div>

                                        <div class="divTableCell" style="width: 200px"><form action="${pageContext.request.contextPath}/ServletEliminarAnimal" method="POST">
                                                <input type="hidden" value="<%=m.getId()%>" name="IdMascota">
                                                <input type="submit" class="btn btn-danger my-4 btn-block" value="Eliminar">
                                            </form></div>
                                    </div>
                                    <%}
                                        }%>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

        </main>

        <jsp:include page="/Vistas/Footer.jsp" />
        <style type="text/css">

            .divTable{
                display: table;
                width: 100%;
                border: 1px solid black;
                margin-bottom: 50px;
            }
            .divTableBody {
                display: table-row-group;
            }
            .divTableRow {
                display: table-row;
            }
            .divTableCell{
                display: table-cell;
                padding: 3px 10px;
                color: #013e7f;
                background-color: #ffffff;
                font-weight: bold;
                border: 1px solid black;
                text-align: center;
            }
            .divTableHead{
                display: table-cell;
                padding: 3px 10px;
                color: white;
                background-color: black;
                font-weight: bold;
                border: 1px solid black;
                text-align: center;
            }


        </style>
    </body>


</html>

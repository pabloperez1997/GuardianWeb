<%-- 
    Document   : Registrarse
    Created on : 07-may-2019, 14:26:54
    Author     : PabloP
--%>

<%@page import="servicios.Mascota"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


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
    <jsp:include page="/Vistas/Cabezal.jsp" />
</head>


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
                <p class="h4 mb-4">Registrarse</p>
                <form  action="ServletAltaAnimal" class="text-center p-5">
                    <input type="hidden" value="1" name="agregarMascotas">

                    <input onclick="guardar();submit()" type="button" class="btn btn-success my-4 btn-block" value="AgregarMascotas">
                </form>
                <form method="post" action="registrarse" class="text-center border border-light p-5">



                    <div class="form-row mb-4">
                        <div class="col">
                            <!-- Nombre -->
                            <input type="text" name="nombre" id="nombre" class="form-control" placeholder="Nombre" required>
                        </div>
                        <div class="col">
                            <!-- Apellido -->
                            <input type="text" name="apellido" id="apellido" class="form-control" placeholder="Apellido" required>
                        </div>
                    </div>
                    <!-- E-mail -->
                    <input type="text" name="cedula" id="cedula" class="form-control mb-4" placeholder="Cedula" required>

                    <!-- E-mail -->
                    <input type="Email" name="email" id="email" class="form-control mb-4" placeholder="E-mail" required>

                    <!-- Password -->
                    <input type="password" name="pass" id="pass" class="form-control mb-3" placeholder="Contraseña" required>
                    <input type="password" name="pass" id="pass" class="form-control" placeholder="Repetir Contraseña" required>
                    <small id="defaultRegisterFormPasswordHelpBlock" class="form-text text-muted mb-4">
                        Al menos 6 caracteres
                    </small>

                    <!-- Telefono -->
                    <input type="text" name="telefono" id="telefono" class="form-control mb-4" placeholder="Teléfono" required>

                    <!-- Direccion -->
                    <input type="text" name="direccion" id="direccion" class="form-control mb-4" placeholder="Dirección" required>
                    <p class="h4 mb-4 text-center">Mascotas</p>
                    <div class="divTable">
                        <div class="divTableBody">
                            <div class="divTableRow" >
                                <div class="divTableHead">Nombre</div>
                                <div class="divTableHead">Raza</div>
                            </div>
                            <%if (request.getAttribute("MascotasCliente") != null) {
                                    List<Mascota> mascotas = (List<Mascota>) request.getAttribute("MascotasCliente");
                                    for (Mascota m : mascotas) {%>
                            <div class="divTableRow">
                                <div class="divTableCell"><%=m.getNombre()%></div>
                                <div class="divTableCell"><%=m.getRaza().getRaza()%></div>
                            </div>
                            <%}
                                            }%>
                        </div>
                    </div>

                    <!-- Boton Registrarse -->
                    <button onclick="limpiar();submit()" class="btn btn-dark my-4 btn-block" >Registrarme</button>

                    <p>Ya tienes una cuenta?
                        <a href="iniciar-sesion">Inicia Sesion</a>
                    </p>

                </form>
            </div>
        </div>
    </div>
</div>
<br>


}
<!-- Default form register -->
<script type="text/javascript" src="bootstrap/js/jquery-3.3.1.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="bootstrap/js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="bootstrap/js/mdb.min.js"></script>
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
<script>
                        function guardar() {
                            var nombre = document.getElementById("nombre").value;
                            var apellido = document.getElementById("apellido").value;
                            var email = document.getElementById("email").value;
                            var pass = document.getElementById("pass").value;
                            var cedula = document.getElementById("cedula").value;
                            var direccion = document.getElementById("direccion").value;
                            var telefono = document.getElementById("telefono").value;

                            localStorage.setItem('nombre', nombre);
                            localStorage.setItem('apellido', apellido);
                            localStorage.setItem('email', email);
                            localStorage.setItem('pass', pass);
                            localStorage.setItem('cedula', cedula);
                            localStorage.setItem('direccion', direccion);
                            localStorage.setItem('telefono', telefono);
                        }

                        function cargar() {

                            var nombre = localStorage.getItem('nombre');
                            var apellido = localStorage.getItem('apellido');
                            var email = localStorage.getItem('email');
                            var pass = localStorage.getItem('pass');
                            var cedula = localStorage.getItem('cedula');
                            var direccion = localStorage.getItem('direccion');
                            var telefono = localStorage.getItem('telefono');

                            document.getElementById("nombre").value = nombre;
                            document.getElementById("apellido").value = apellido;
                            document.getElementById("email").value = email;
                            document.getElementById("pass").value = pass;
                            document.getElementById("cedula").value = cedula;
                            document.getElementById("direccion").value = direccion;
                            document.getElementById("telefono").value = telefono;
                        }

                        function limpiar() {
                            localStorage.removeItem('nombre');
                            localStorage.removeItem('apellido');
                            localStorage.removeItem('email');
                            localStorage.removeItem('pass');
                            localStorage.removeItem('cedula');
                            localStorage.removeItem('direccion');
                            localStorage.removeItem('telefono');


                        }



</script>
<script>cargar();</script>




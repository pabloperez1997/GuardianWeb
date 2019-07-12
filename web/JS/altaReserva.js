/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




document.getElementById("altaReserva").addEventListener("click", function () {
    var servicio;
    var turno = document.getElementById("comboTurnos").value;
    console.log(turno);
    var mascota = document.getElementById("comboMascotas").value;
    console.log(mascota);
    var tipoSer = document.getElementById("servicio").value;
    console.log(tipoSer);
    var rdBan = document.getElementById("rdBan");
    console.log(rdBan);
    var rdEsq = document.getElementById("rdEsq");
    console.log(rdEsq);
    var rdPas = document.getElementById("rdPas");
    console.log(rdPas);
    if (rdBan.checked) {
        servicio = "BANIO";
        console.log("BANIO");
    }
    if (rdEsq.checked) {
        servicio = "ESQUILA";
        console.log("ESQUILA");
    }
    if (rdPas.checked) {
        servicio = "PASEO";
        console.log("PASEO");
    }
    altaReserva(turno, mascota, tipoSer, servicio,id);

});
function altaReserva(turnoR, mascotaR, tipoSerID, servicioR, idUsuario) {
    $.ajax({
        url: 'reserva',
        data: {
            idCliente: idUsuario,
            idTurno: turnoR,
            idMascota: mascotaR,
            idTipoServicio: tipoSerID,
            tipoServicio: servicioR},
        type: 'POST',
        success: function (data) {
            if (data) {
                alert("Reserva creada con exito!");
                inicio();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("error" + errorThrown.toString());
        }
    }
    );

}

function inicio() {
    $.ajax({url: 'inicio',
        data: {ok: "ok"}, type: 'GET',
        success: function (data) {
            if (data) {
                window.location.replace("/GuardianWeb/ServletInicio");
            }
        }


    });

}

/*function altaPerfil() {
 var nombre = $('#nombre').val();
 var apellido = $('#apellido').val();
 var nickname = $('#nickname').val();
 var correo = document.getElementById("email");
 var email = correo.value;
 var dt = $('#fechaNac').val();
 var fecha = armaFecha(dt);
 var pass1 = $('#passw').val();
 var pass2 = $('#passwC').val();
 var direccion = null;
 var bio = null;
 var pagWeb = null;
 if (tipo.valueOf() === "Proponente") {
 direccion = $('#direccion').val();
 bio = $('#bioG').val();
 pagWeb = $('#pagWeb').val();
 }
 
 if (pass1 !== pass2) {
 $('#pass1').focus().select();
 alert("Contraseñas no coinciden");
 }
 console.log("PTM");
 $.ajax({
 url: 'servletRegistrarse',
 data: {
 nombre: nombre,
 apellido: apellido,
 nickname: nickname,
 correo: email,
 nacimiento: fecha,
 password: pass1,
 password2: pass2,
 direccion: direccion,
 bio: bio,
 pagWeb: pagWeb, tipo: tipo},
 type: 'POST',
 success: function (data) {
 
 if (data.toString() === "correoE") {
 console.log("correoE");
 alert("Error: ya existe un usuario registrado con ese correo!");
 }
 if (data.toString() === "usuarioE") {
 console.log("usuarioE");
 alert("Error: ya existe un usuario registrado con el mismo nickname");
 } else {
 alert(data.toString());
 inicio();
 }
 
 
 },
 error: function (jqXHR, textStatus, errorThrown) {
 console.log("error" + errorThrown.toString());
 }
 });
 
 
 }*/
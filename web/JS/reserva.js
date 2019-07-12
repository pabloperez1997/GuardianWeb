/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global tipobanios, tipoEsquila */

var tipobanios = [];
var tipoEsquila = [];

function cargarListaBanio(id, tipoBan) {
    console.log(id, tipoBan);
    tipobanios.push([id, tipoBan]);
}
function cargarListaEsquila(id, tipoEsq) {

    tipoEsquila.push([id, tipoEsq]);
}
function cargarCombo() {
    
    var rdBan = document.getElementById("rdBan");
    var rdEsq = document.getElementById("rdEsq");
    var rdPas = document.getElementById("rdPas");
    var select = document.getElementById("servicio");



    if (rdBan.checked) {
        limpiarCombo();
        for (var i = 0; i < tipobanios.length; i++) {
            var option = document.createElement("option");
            option.value = tipobanios[i][0];
            option.text = tipobanios[i][1];
            select.appendChild(option);
        }


    }
    if (rdEsq.checked) {
        limpiarCombo();
        for (var j = 0; j < tipoEsquila.length; j++) {
            var option2 = document.createElement("option");
            option2.value = tipoEsquila[j][0];
            option2.text = tipoEsquila[j][1];
            select.appendChild(option2);
        }



    }

    if (rdPas.checked) {
        limpiarCombo();
        var option3 = document.createElement("option");
        option3.value = "PASEO";
        option3.text = "Paseo";
        select.appendChild(option3);
    }


}

function limpiarCombo() {

    var select = document.getElementById("servicio");
    var hijos = select.childNodes;
    for (var i = 0; i < hijos.length; i++) {
        select.removeChild(hijos[i]);

    }}
    




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



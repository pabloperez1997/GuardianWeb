/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Get the modal
var modal = document.getElementById("myModal");

// Get the image and insert it inside the modal - use its "alt" text as a caption

var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");

var img = document.getElementsByClassName('myImg');
for (var i = 0; i < img.length; i++) {
  img[i].onclick = function(){
  modal.style.display = "block";
  modalImg.src = this.src;
  captionText.innerHTML = this.alt;
} // your index is inside here
}

// Get the <span> element that closes the modal
var span = document.getElementById("close");

// When the user clicks on <span> (x), close the modal
span.onclick = function() { 
  modal.style.display = "none";
}

 function calcularTotal(precio){
            
           var nodoPadre = document.activeElement.parentNode;
           var inputcant = nodoPadre.querySelector('input[type=number]');
           inputcant.setAttribute("value", parseInt(inputcant.getAttribute("value"))+1);
           
           tot=precio;
           var subtotal= document.getElementById("total").innerHTML;
           value = parseFloat(subtotal);
           var subottal1= tot+value;
           
           document.getElementById("total").innerHTML = subottal1;
        }
        function calcularTotalrestar(precio){
           
           var nodoPadre = document.activeElement.parentNode;
           var inputcant = nodoPadre.querySelector('input[type=number]');
           cant = parseInt(inputcant.getAttribute("value"));
           
           if(cant==1)return;
           
           inputcant.setAttribute("value", parseInt(inputcant.getAttribute("value"))-1);   
           var subtotal= document.getElementById("total").innerHTML;
           value = parseFloat(subtotal);
           var subottal1= value-precio;
           
           document.getElementById("total").innerHTML = subottal1;
        }